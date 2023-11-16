package com.curso.cursospring.domain.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ManyToOne
    private Cliente cliente;

    private String descricao;
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

    private OffsetDateTime dataAbertura;
    private OffsetDateTime dataFechamento;

    @OneToMany(mappedBy = "ordemServico")
    private List<Comentario> comentarios;

    public boolean podeSerFinalizada() {

        return StatusOrdemServico.ABERTA.equals(getStatus());
    }

    public boolean naoPodeSerFinalizada() {

        return !podeSerFinalizada();
    }

    public boolean naoPodeSerCancelada() {

        return StatusOrdemServico.FINALIZADA.equals(getStatus());
    }

    public boolean podeSerCancelada() {

        return !naoPodeSerCancelada();
    }

}
