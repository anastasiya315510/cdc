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
public class ChangeDataCaptureDTO implements Serializable, Comparable<ChangeDataCaptureDTO>{
     private Long pk;
     private LocalDateTime data;
     private LocalDateTime beforeData;
     private ChangeDataCaptureHeaderDTO headers;


     @Override
     public int compareTo(ChangeDataCaptureDTO record) {
          return Integer.compare(Integer.parseInt(this.getHeaders().getStreamPosition()),
                  Integer.parseInt(record.getHeaders().getStreamPosition()));

     }
}
