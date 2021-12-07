package com.dbc.logapi.service;

import com.dbc.logapi.dto.LogDTO;
import com.dbc.logapi.entity.LogEntity;
import com.dbc.logapi.repository.LogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LogService {
    private  final ObjectMapper objectMapper;
    private  final LogRepository logRepository;

    public List<LogDTO> List(){
        List<LogEntity> logEntityList = logRepository.findAll();
        return  logEntityList.stream().map(logEntity -> objectMapper.convertValue(logEntity, LogDTO.class)).collect(Collectors.toList());
    }

}
