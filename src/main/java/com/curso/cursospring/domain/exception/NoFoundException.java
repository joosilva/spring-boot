package com.curso.cursospring.domain.exception;

public class NoFoundException extends NegocioException {

    public static final long serialVersionUID = 1L;

    public NoFoundException(String mensagem) {
        super(mensagem);
    }

}