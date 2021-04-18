package org.kafkatest.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties("kafka")
@Getter
@RequiredArgsConstructor
@ConstructorBinding
@Validated
public class KafkaProperties {

    @NotBlank
    private final String topicName;

}
