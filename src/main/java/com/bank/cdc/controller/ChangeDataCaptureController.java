package com.bank.cdc.controller;

import com.bank.cdc.model.ChangeDataCaptureDTO;
import com.bank.cdc.service.ChangeDataCaptureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ChangeDataCaptureController {

    private final ChangeDataCaptureService changeDataCaptureService;

    @PostMapping("/publish")
    public void createChangeDataCapture(@RequestBody ChangeDataCaptureDTO request) {
        changeDataCaptureService.sendInputMessage(request);
    }
}
