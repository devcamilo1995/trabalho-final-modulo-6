package com.dbc.logapi.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
public class KafkaConsumerConfig {
    private static final String EARLIEST = "earliest";
    private static final String LATEST = "latest";
    @Value("${kafka.bootstrap-server}")
    private String bootstrapServer;
    @Value("${kafka.client-id}")
    private String clientId;

    public KafkaConsumerConfig() {
    }

    private <T> Map<String, Object> consumerConfigs(Class<? extends Deserializer<T>> clazz, String autoOffset) {
        Map<String, Object> props = new HashMap();
        props.put("bootstrap.servers", this.bootstrapServer);
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", clazz);
        props.put("client.id", this.clientId);
        props.put("max.poll.records", 10);
        props.put("auto.offset.reset", autoOffset);
        return props;
    }

    private <T> ConsumerFactory<String, T> consumerFactory(Class<? extends Deserializer<T>> clazz, String autoOffset) {
        return new DefaultKafkaConsumerFactory(this.consumerConfigs(clazz, autoOffset));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> listenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(this.consumerFactory(StringDeserializer.class, "earliest"));
        return factory;
    }
}
