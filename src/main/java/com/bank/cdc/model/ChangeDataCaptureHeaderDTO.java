/*
 author Anastasiya
 created on 29/08/2021
 */


package com.bank.cdc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeDataCaptureHeaderDTO implements Serializable {
    private ChangeDataCaptureOperationType operation;
    private LocalDateTime timestamp;
    private String streamPosition;
}
