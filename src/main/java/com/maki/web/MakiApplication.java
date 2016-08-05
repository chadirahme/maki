package com.maki.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MakiApplication
{
    private static final Logger log = LoggerFactory.getLogger(MakiApplication.class);
    public static void main(String[] args) {

        SpringApplication.run(MakiApplication.class, args);
        log.error("Message logged at ERROR level");
        log.warn("Message logged at WARN level");
        log.info("Message logged at INFO level");
        log.debug("Message logged at DEBUG level");

    }
}
