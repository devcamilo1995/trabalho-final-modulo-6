package com.dbc.trabalhovemser.service;


import com.dbc.trabalhovemser.dto.HoteisCreateDTO;
import com.dbc.trabalhovemser.entity.HoteisEntity;
import com.dbc.trabalhovemser.repository.HoteisRepository;
import com.dbc.trabalhovemser.repository.ReservaRepository;
import com.dbc.trabalhovemser.repository.UsuarioRepository;
import com.dbc.trabalhovemser.service.HoteisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class TesteMockito {

    @InjectMocks
    private HoteisService hoteisService;

    @Mock
    private HoteisRepository hoteisRepository;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void verificarCEPComSucesso(){
        hoteisService = mock(HoteisService.class);
        when(hoteisService.verificaCEP(anyString())).thenCallRealMethod();
        Assertions.assertTrue(hoteisService.verificaCEP("12345678"));
    }

    @Test
     void createHotel() throws Exception {
        hoteisService = mock(HoteisService.class);
        HoteisCreateDTO hoteisCreateDTO = new HoteisCreateDTO();
        hoteisCreateDTO.setNomeHotel("Plaza São Rafael");
        hoteisCreateDTO.setNomeEstado("Rio Grande do Sul");
        hoteisCreateDTO.setNomeCidade("Porto Alegre");
        hoteisCreateDTO.setCep("12345678");
        hoteisCreateDTO.setNumero(123);
        hoteisCreateDTO.setLogradouro("Rua Alberto Bins");
        HoteisEntity hoteisEntity = new HoteisEntity();
        doReturn(Optional.of(hoteisEntity)).when(hoteisRepository).findById(anyInt());
        hoteisService.create(hoteisCreateDTO);
        verify(hoteisService, Mockito.times(1)).create(hoteisCreateDTO);
    }

    @Test
    public void deletaHotelComSucesso() throws Exception {
        HoteisEntity hoteisEntity = new HoteisEntity();
        doReturn(Optional.of(hoteisEntity)).when(hoteisRepository).findById(2);
        hoteisService.delete(2);
        verify(hoteisRepository, times(1)).delete(hoteisEntity);
    }




}
