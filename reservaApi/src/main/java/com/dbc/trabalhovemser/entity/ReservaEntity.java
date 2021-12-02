package com.dbc.trabalhovemser.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity(name="RESERVA")
public class ReservaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESERVA_SEQ")
    @SequenceGenerator(name = "RESERVA_SEQ", sequenceName = "SEQ_RESERVAS", allocationSize = 1)
    @Column(name = "id_reserva")
    private Integer idReserva;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hoteis")
    private HoteisEntity hoteisEntity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_quartos")
    private QuartosEntity quartosEntity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuarioEntity;










}
