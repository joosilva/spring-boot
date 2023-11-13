package com.curso.cursospring.api.exceptionhandler.resposta;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Resposta {

    private Integer status;
    private OffsetDateTime dataEHora;
    private String titulo;
    private List<Campo> campos;

    @Data
    @AllArgsConstructor
    public static class Campo {

        private String nome;
        private String mensagem;
    }

}
