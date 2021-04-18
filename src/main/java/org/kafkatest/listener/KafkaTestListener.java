package org.kafkatest.listener;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kafkatest.dto.PersonEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Getter
@RequiredArgsConstructor
public class KafkaTestListener {

    @KafkaListener(topics = "${kafka.topic-name}")
    public void receive(@Payload PersonEvent event) {
        log.info("Receive event [{}],", event);
    }

}
