package com.dbc.consumer.service;

import com.dbc.consumer.dto.LogDTO;
import com.dbc.consumer.kafka.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



@Component
@RequiredArgsConstructor
@Slf4j
public class LogService {
    private final Producer producer;

    public void emailComSucesso() throws JsonProcessingException {
        LogDTO logDTO = new LogDTO();
        logDTO.setDescricao("Email enviado");
        logDTO.setTipo("Email");
        logDTO.setDataLog(LocalDateTime.now());
        producer.sendtoLog(logDTO);
    }

    public void emailSemSucesso() throws JsonProcessingException {
        LogDTO logDTO = new LogDTO();
        logDTO.setDescricao("Email não enviado");
        logDTO.setTipo("Email");
        logDTO.setDataLog(LocalDateTime.now());
        producer.sendtoLog(logDTO);
    }



}
