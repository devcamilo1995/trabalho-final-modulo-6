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


    public void mensagemComSucesso() throws JsonProcessingException {
        LogDTO logDTO = new LogDTO();
        logDTO.setLog("Mensagem lida com sucesso");
        logDTO.setHorario(LocalDateTime.now());
        producer.sendtoLog(logDTO);
    }

    public void mensagemSemSucesso() throws JsonProcessingException {
        LogDTO logDTO = new LogDTO();
        logDTO.setLog("Mensagem não lida");
        logDTO.setHorario(LocalDateTime.now());
        producer.sendtoLog(logDTO);
    }

    public void emailComSucesso() throws JsonProcessingException {
        LogDTO logDTO = new LogDTO();
        logDTO.setLog("Email enviado");
        logDTO.setHorario(LocalDateTime.now());
        producer.sendtoLog(logDTO);
    }

    public void emailSemSucesso() throws JsonProcessingException {
        LogDTO logDTO = new LogDTO();
        logDTO.setLog("Email não enviado");
        logDTO.setHorario(LocalDateTime.now());
        producer.sendtoLog(logDTO);
    }



}
