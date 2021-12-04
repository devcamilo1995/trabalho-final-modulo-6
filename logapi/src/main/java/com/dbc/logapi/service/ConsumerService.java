package com.dbc.logapi.service;

import com.dbc.logapi.entity.LogEntity;
import com.dbc.logapi.repository.LogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {
    private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);
    private final KafkaTemplate<String, String> stringKafkaTemplate;
    private final ObjectMapper objectMapper;
    private final LogRepository logRepository;

    @KafkaListener(
            topics = {"${kafka.topic.log}"},
            groupId = "${kafka.group-id}",
            containerFactory = "listenerContainerFactory"
    )
    public void consumelog(@Payload String mensagem, @Header("kafka_receivedMessageKey") String key, @Header("kafka_offset") Long offset) throws JsonProcessingException {
        log.info("MENSAGEM LIDA: '{}', CHAVE: '{}', OFFSET: '{}'", new Object[]{mensagem, key, offset});
        this.logRepository.save(this.objectMapper.readValue(mensagem, LogEntity.class));
    }
    public ConsumerService(final KafkaTemplate<String, String> stringKafkaTemplate, final ObjectMapper objectMapper, LogRepository logRepository) {
        this.stringKafkaTemplate = stringKafkaTemplate;
        this.objectMapper = objectMapper;
        this.logRepository = logRepository;
    }
}
