/*
 author Anastasiya
 created on 05/09/2021
 */

package com.bank.cdc.constants;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.connect.json.JsonDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface Constants {
     String INPUT_TOPIC = "my-cdc-input-topic";
     Map<String, Object> KAFKA_PARAMS = new HashMap<String, Object>() {{
        put("bootstrap.servers", "localhost:9092");

        put("key.deserializer", StringDeserializer.class);
        put("value.deserializer", JsonDeserializer.class);
        put("group.id", "my_group_id");
        put("auto.offset.reset", "latest");
        put("enable.auto.commit", false);
    }};
     Collection<String> SOURCE_TOPICS = Arrays.asList("my-cdc-input-topic");
     String DESTINATION_TOPIC = "my-cdc-output-topic";
      JavaStreamingContext STREAMING_CONTEXT = new JavaStreamingContext(
            new SparkConf()
                    .setAppName("GroupActionsJob")
                    .setMaster("local"),
            Durations.seconds(10));
}
