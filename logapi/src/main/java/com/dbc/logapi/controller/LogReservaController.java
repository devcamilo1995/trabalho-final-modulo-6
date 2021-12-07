package com.dbc.logapi.controller;

import com.dbc.logapi.dto.LogDTO;
import com.dbc.logapi.entity.LogEntity;
import com.dbc.logapi.repository.LogRepository;
import com.dbc.logapi.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
@Slf4j
public class LogReservaController {
    private  final LogService logService;

    @GetMapping
    public List<LogDTO>  listAllLog(){
        return logService.List();
    }



}
