package org.kafkatest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kafkatest.dto.PersonEvent;
import org.kafkatest.listener.KafkaTestListener;
import org.kafkatest.producer.KafkaTestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {KafkaTestContainerApplication.class})
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class EmbeddedKafkaTest {

//    @Autowired
//    private EmbeddedKafkaBroker broker;

    @Autowired
    private KafkaTestListener kafkaTestListener;

    @Autowired
    private KafkaTestProducer kafkaTestProducer;

    @Test
    public void testListener() throws InterruptedException {
        kafkaTestProducer.sendEvent(PersonEvent.builder()
                .id(UUID.randomUUID())
                .name("ALLA")
                .build());

        Thread.sleep(100);

    }
}
