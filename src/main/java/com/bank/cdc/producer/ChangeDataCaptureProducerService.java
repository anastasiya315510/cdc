/*
 author Anastasiya
 created on 29/08/2021
 */


package com.bank.cdc.producer;


import com.bank.cdc.model.ChangeDataCaptureDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@AllArgsConstructor
public class ChangeDataCaptureProducerService {
    private static final String TOPIC_OUTPUT = "my-cdc-output-topic";

    private final KafkaTemplate<String, ChangeDataCaptureDTO> kafkaTemplate;

    public void writeMessage(ChangeDataCaptureDTO changeDataCaptureDTO) {
        this.kafkaTemplate.send(TOPIC_OUTPUT, changeDataCaptureDTO);
        log.info("record create -> {}", changeDataCaptureDTO);

    }
}
