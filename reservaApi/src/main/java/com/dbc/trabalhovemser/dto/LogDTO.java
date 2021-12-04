package com.dbc.trabalhovemser.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogDTO {
    private String descricao;
    private String tipo;
    private LocalDateTime dataLog;
}
