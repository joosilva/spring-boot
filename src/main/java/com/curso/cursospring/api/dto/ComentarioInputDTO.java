package com.curso.cursospring.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ComentarioInputDTO {

    @NotBlank
    private String descricao;

}
