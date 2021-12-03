package com.dbc.trabalhovemser.dto;

import lombok.Data;

@Data
public class EmailDTO {
    private String destinatario;
    private String assunto;
    private String texto;
}
