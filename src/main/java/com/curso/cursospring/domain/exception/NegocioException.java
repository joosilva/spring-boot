package com.curso.cursospring.domain.exception;

public class NegocioException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public NegocioException(String mensagem) {
        super(mensagem);
    }

}
