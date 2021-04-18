package org.kafkatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class KafkaTestContainerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaTestContainerApplication.class, args);
    }

}
