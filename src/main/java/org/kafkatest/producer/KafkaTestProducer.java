package org.kafkatest.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kafkatest.config.KafkaProperties;
import org.kafkatest.dto.PersonEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaTestProducer {

    private final KafkaTemplate<String, PersonEvent> kafkaTemplate;
    private final KafkaProperties properties;

    public void sendEvent(PersonEvent event) {
        log.info("New event [{}]", event);
        kafkaTemplate.send(properties.getTopicName() ,event);
    }
}
