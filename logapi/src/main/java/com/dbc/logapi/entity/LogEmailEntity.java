package com.dbc.logapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "logEmail")
public class LogEmailEntity {
    @NotEmpty
    private String destinatario;
    @NotEmpty
    private String mensagem;
    @NotEmpty
    private String assunto;

    private LocalDateTime data;

}
