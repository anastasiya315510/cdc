package com.bank.cdc.consumer;


import com.bank.cdc.model.ChangeDataCaptureDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CDCOutputConsumer {

    @KafkaListener(topics = "my-cdc-output-topic")
    public void receiveData(ChangeDataCaptureDTO message) {
        log.info(">> Streaming topic message: {}", message);
    }
}
