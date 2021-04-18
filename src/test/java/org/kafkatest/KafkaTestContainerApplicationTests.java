package org.kafkatest;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kafkatest.dto.PersonEvent;
import org.kafkatest.listener.KafkaTestListener;
import org.kafkatest.producer.KafkaTestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = KafkaTestContainerApplication.class)
@Import(KafkaTestContainerApplicationTests.KafkaTestConfiguration.class)
@DirtiesContext
public class KafkaTestContainerApplicationTests {

    @ClassRule
    public static final KafkaContainer KAFKA_CONTAINER;

    static {
        KAFKA_CONTAINER = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"));
        KAFKA_CONTAINER.start();
    }

    @Autowired
    private KafkaTestListener kafkaTestListener;

    @Autowired
    private KafkaTestProducer kafkaTestProducer;

    @Test
    void testKafkaTestContainer() {
        var event = PersonEvent.builder()
                .id(UUID.randomUUID())
                .name("ALLA")
                .age(20)
                .build();
        kafkaTestProducer.sendEvent(event);
    }

    @Configuration
    public static class KafkaTestConfiguration {

        @Bean
        public Map<String, Object> consumerConfigs() {
            Map<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_CONTAINER.getBootstrapServers());
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
            return props;
        }

        @Bean
        public ProducerFactory<String, PersonEvent> producerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_CONTAINER.getBootstrapServers());
            return new DefaultKafkaProducerFactory<>(configProps);
        }

        @Bean
        public KafkaTemplate<String, PersonEvent> kafkaTemplate() {
            return new KafkaTemplate<>(producerFactory());
        }
    }

}
