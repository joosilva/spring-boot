package com.curso.cursospring.api.exceptionhandler;

import com.curso.cursospring.api.exceptionhandler.resposta.Resposta;
import com.curso.cursospring.domain.exception.NegocioException;
import com.curso.cursospring.domain.exception.NoFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocioExceptional(NegocioException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;

        var resposta = new Resposta();
        resposta.setStatus(status.value());
        resposta.setTitulo(ex.getMessage());
        resposta.setDataEHora(OffsetDateTime.now());

        return handleExceptionInternal(ex, resposta, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NoFoundException.class)
    public ResponseEntity<Object> handleNoFoundExceptional(NoFoundException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;

        var resposta = new Resposta();
        resposta.setStatus(status.value());
        resposta.setTitulo(ex.getMessage());
        resposta.setDataEHora(OffsetDateTime.now());

        return handleExceptionInternal(ex, resposta, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var campos = new ArrayList<Resposta.Campo>();
        var resposta = new Resposta();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            campos.add(new Resposta.Campo(nome, mensagem));
        }

        resposta.setStatus(status.value());
        resposta.setTitulo("Um ou mais campos não estão preenchidos da forma correta.");
        resposta.setDataEHora(OffsetDateTime.now());
        resposta.setCampos(campos);

        return super.handleExceptionInternal(ex, resposta, headers, status, request);
    }
}
