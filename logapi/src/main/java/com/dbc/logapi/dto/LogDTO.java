package com.dbc.logapi.dto;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

import java.time.LocalDateTime;

@Data
public class LogDTO {
    @Id
    private String id;
    private String descricao;
    private String tipo;
    private LocalDateTime dataLog;
}
