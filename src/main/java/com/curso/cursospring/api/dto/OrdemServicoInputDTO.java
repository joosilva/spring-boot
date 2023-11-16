package com.curso.cursospring.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

//Classe para controle da entrada de dados para flexibilizar entrada de dados.
@Data
public class OrdemServicoInputDTO {

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal preco;

    @Valid
    @NotNull
    private ClienteIdInputDTO clienteIdInput;

}
