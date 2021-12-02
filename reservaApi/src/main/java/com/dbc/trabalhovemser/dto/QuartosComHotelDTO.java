package com.dbc.trabalhovemser.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuartosComHotelDTO extends QuartosDTO {

    private HoteisDTO hoteisDTO;

}