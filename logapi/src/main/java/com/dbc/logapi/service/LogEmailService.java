package com.dbc.logapi.service;

import com.dbc.logapi.entity.LogEmailEntity;
import com.dbc.logapi.repository.LogEmailRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogEmailService {
    private final LogEmailRepository logEmailRepository;
    private final ObjectMapper objectMapper;

    public List<LogEmailEntity> findAll() {
        return logEmailRepository.findAll();
    }



}
