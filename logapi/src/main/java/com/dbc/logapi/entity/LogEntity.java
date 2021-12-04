package com.dbc.logapi.entity;

import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "log")
public class LogEntity {
    @Id
    private String id;
    private String descricao;
    private String tipo;
    private LocalDateTime dataLog;

}
