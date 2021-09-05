package com.bank.cdc;


import com.bank.cdc.model.ChangeDataCaptureDTO;
import com.bank.cdc.producer.ChangeDataCaptureProducerService;
import com.bank.cdc.utils.CDCResolver;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Component;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class Runner implements CommandLineRunner {
    private final JavaStreamingContext streamingContext;
    private final ChangeDataCaptureProducerService producer;
    private final KafkaProperties properties;

    private Map<String, Object> mergeMaps(Map<String, Object>... maps) {
        return Arrays.stream(maps)
                .flatMap(x -> x.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a));
    }

    @Override
    public void run(String... args) throws InterruptedException {
        Collection<String> topics = Arrays.asList("my-cdc-input-topic");
        JavaInputDStream<ConsumerRecord<String, ChangeDataCaptureDTO>> messages = KafkaUtils.createDirectStream(
                streamingContext,
                LocationStrategies.PreferConsistent(),
                ConsumerStrategies.Subscribe(topics, mergeMaps(
                        properties.buildStreamsProperties(),
                        properties.buildConsumerProperties(),
                        properties.buildProducerProperties(),
                        properties.buildAdminProperties()
                ))
        );


        messages
                .mapToPair(record -> new Tuple2<>(record.key(), record.value()))
                .map(tuple -> tuple._2)
                .foreachRDD(javaRDD -> {
                            javaRDD
                                    .sortBy(el -> Integer.parseInt(el.getHeaders().getStreamPosition()), true, 1)
                                    .takeOrdered(10)
                                    .stream()
                                    .map(CDCResolver::filterCDCDto)
                                    .forEach(producer::writeMessage);
                        }
                );

        streamingContext.start();
        streamingContext.awaitTermination();
    }
}


