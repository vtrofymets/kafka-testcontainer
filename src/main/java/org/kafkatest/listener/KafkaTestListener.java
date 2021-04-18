package org.kafkatest.listener;

import lombok.extern.slf4j.Slf4j;
import org.kafkatest.dto.PersonEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaTestListener {

    @KafkaListener(topics = "${kafka.topic-name}")
    public void receive(PersonEvent event) {
        log.info("Receive event [{}],", event);
    }

}
