package com.bank.cdc.service;


import com.bank.cdc.model.ChangeDataCaptureDTO;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.bank.cdc.constants.Constants.INPUT_TOPIC;

@Service
@AllArgsConstructor
public class ChangeDataCaptureService {

    private final KafkaTemplate<String, ChangeDataCaptureDTO> kafkaTemplate;
    public void sendInputMessage(ChangeDataCaptureDTO request) {
        kafkaTemplate.send(INPUT_TOPIC, request);
    }
}
