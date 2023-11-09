package com.curso.cursospringapi.api.exceptionhandler.resposta;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Resposta {

    private Integer status;
    private LocalDateTime dataEHora;
    private String titulo;
    private List<Campo> campos;

    @Data
    @AllArgsConstructor
    public static class Campo {

        private String nome;
        private String mensagem;
    }

}
