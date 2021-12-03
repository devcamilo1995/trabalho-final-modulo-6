package com.dbc.logapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class LogEmailDTO {
    @NotEmpty
    private String destinatario;
    @NotEmpty
    private String mensagem;
    @NotEmpty
    private String assunto;

    private LocalDateTime data;
}
