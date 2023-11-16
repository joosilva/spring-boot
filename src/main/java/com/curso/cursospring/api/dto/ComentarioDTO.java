package com.curso.cursospring.api.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ComentarioDTO {

    private String descricao;
    private OffsetDateTime data_envio;

}
