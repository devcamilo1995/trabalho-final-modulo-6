package com.dbc.logapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @ApiModelProperty(value = "Data atual")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data;
}
