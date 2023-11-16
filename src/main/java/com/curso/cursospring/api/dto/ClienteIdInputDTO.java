package com.curso.cursospring.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

//Classe para controle da entrada de dados para flexibilizar entrada de dados.
@Data
public class ClienteIdInputDTO {

    @NotNull
    private Long clienteIdInput;

}
