package com.dbc.trabalhovemser;

import com.dbc.trabalhovemser.dto.HoteisDTO;

import org.junit.jupiter.api.Test;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;


class TrabalhoVemserApplicationTests {

	private Validator validator;

	@Test
	public void validarCepInvalidoHoteis() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

		HoteisDTO hoteisDTO = new HoteisDTO();
		hoteisDTO.setCep("123456");
		hoteisDTO.setLogradouro("rua");
		hoteisDTO.setNomeHotel("lua");
		hoteisDTO.setIdHotel(5);
		hoteisDTO.setNomeEstado("pe");
		hoteisDTO.setNumero(8);
		hoteisDTO.setNomeEstado("pernanbuco");

		Set<ConstraintViolation<HoteisDTO>> violations = validator.validate(hoteisDTO);
		assertFalse(violations.isEmpty());

	}

	@Test
	public void validarHoteis() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

		HoteisDTO hoteisDTO = new HoteisDTO();
		hoteisDTO.setCep("12345678");
		hoteisDTO.setLogradouro("rua");
		hoteisDTO.setNomeHotel(null);
		hoteisDTO.setIdHotel(5);
		hoteisDTO.setNomeEstado("pe");
		hoteisDTO.setNumero(8);
		hoteisDTO.setNomeEstado("pernanbuco");

		Set<ConstraintViolation<HoteisDTO>> violations = validator.validate(hoteisDTO);
		assertFalse(violations.isEmpty());

	}

}
