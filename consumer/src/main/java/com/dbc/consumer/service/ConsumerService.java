package com.dbc.consumer.service;

import com.dbc.consumer.dto.EmailDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {
    private final KafkaTemplate<String, String> stringKafkaTemplate;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    @KafkaListener(                                           // Ver como o grupo vai chamar os tópicos
            topics = "${kafka.topic.string}",
            groupId = "${kafka.group-id}",
            containerFactory = "listenerContainerFactory"
    )
    public void consume(@Payload String mensagem,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        @Header(KafkaHeaders.OFFSET) Long offset) throws JsonProcessingException, MessagingException {
        EmailDTO emailDTO = objectMapper.readValue(mensagem, EmailDTO.class);
        emailService.enviarEmailTemplate(emailDTO);
        log.info("Email enviado");                     // Ver como enviar esse log
    }



}
