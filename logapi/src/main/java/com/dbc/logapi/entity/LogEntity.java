package com.dbc.logapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "log")
public class LogEntity {
    private String descricao;
    private String tipo;
    private LocalDateTime datalog;

}
