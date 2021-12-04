package com.dbc.trabalhovemser.exceptions;

import com.dbc.trabalhovemser.dto.LogDTO;
import com.dbc.trabalhovemser.kafka.Producer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.security.SignatureException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final Producer producer ;
    private final ObjectMapper objectMapper ;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {


        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        LogDTO logDTO=new LogDTO();
        logDTO.setDataLog(LocalDateTime.now());
        logDTO.setTipo("MethodArgumentNotValidException");
        try {
            logDTO.setDescricao(objectMapper.writeValueAsString(errors));
            producer.sendLog(logDTO);
        }catch (Exception e){
            log.info("Erro ao salvar no kafka: '{}'",e.getMessage());

        }
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleException(ConstraintViolationException exception,
                                                  HttpServletRequest request) {
        LogDTO logDTO=new LogDTO();
        logDTO.setDataLog(LocalDateTime.now());
        logDTO.setDescricao(exception.getMessage());
        logDTO.setTipo("ConstraintViolationException");
        try {
            producer.sendLog(logDTO);
        }catch (Exception e){
            log.info("Erro ao salvar no kafka: '{}'",e.getMessage());

        }

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        body.put("status", badRequest.value());
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, badRequest);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<Object> handleException(RegraDeNegocioException exception,
                                                  HttpServletRequest request) {
        LogDTO logDTO=new LogDTO();
        logDTO.setDataLog(LocalDateTime.now());
        logDTO.setDescricao(exception.getMessage());
        logDTO.setTipo("RegraDeNegocioException");
        try {
            producer.sendLog(logDTO);
        }catch (Exception e){
            log.info("Erro ao salvar no kafka: '{}'",e.getMessage());

        }
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        body.put("status", badRequest.value());
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, badRequest);
    }
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Object> handleException(SignatureException exception,
                                                  HttpServletRequest request) {
        LogDTO logDTO=new LogDTO();
        logDTO.setDataLog(LocalDateTime.now());
        logDTO.setDescricao(exception.getMessage());
        logDTO.setTipo("SignatureException");
        try {
            producer.sendLog(logDTO);
        }catch (Exception e){
            log.info("Erro ao salvar no kafka: '{}'",e.getMessage());

        }
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        final HttpStatus badRequest = HttpStatus.FORBIDDEN;
        body.put("status", badRequest.value());
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, badRequest);
    }
}
