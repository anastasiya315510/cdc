/*
 author Anastasiya
 created on 29/08/2021
 */


package com.bank.cdc.utils;


import com.bank.cdc.model.ChangeDataCaptureDTO;
import com.bank.cdc.model.ChangeDataCaptureOperationType;

public class CDCResolver {
    public static ChangeDataCaptureDTO filterCDCDto(ChangeDataCaptureDTO record) {
        if (record.getHeaders().getOperation().equals(ChangeDataCaptureOperationType.DELETE)) {
            return ChangeDataCaptureDTO.builder().build();
        }
        return record;
    }

}
