/*
 author Anastasiya
 created on 01/09/2021
 */


package com.bank.cdc.configuration;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public JavaStreamingContext javaStreamingContext() {
        return new JavaStreamingContext(new SparkConf().setAppName("GroupActionsJob").setMaster("local"), Durations.seconds(30));
    }

}
