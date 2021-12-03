package com.dbc.logapi.controller;

import com.dbc.logapi.entity.LogEmailEntity;
import com.dbc.logapi.service.LogEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/logEmail")
@RequiredArgsConstructor
@Slf4j
public class LogEmailController {
    private  final LogEmailService logEmailService;

    @GetMapping
    public List<LogEmailEntity> list(){
        return logEmailService.findAll();
    }


}
