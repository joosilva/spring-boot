package com.curso.cursospring.api.dto;

import com.curso.cursospring.domain.model.Cliente;
import com.curso.cursospring.domain.model.StatusOrdemServico;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class OrdemServicoDTO {

    private Long id;
    private Cliente cliente;
    private String descricao;
    private BigDecimal preco;
    private StatusOrdemServico status;
    private OffsetDateTime dataAbertura;
    private OffsetDateTime dataFechamento;

}
