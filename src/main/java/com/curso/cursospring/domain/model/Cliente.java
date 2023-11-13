package com.curso.cursospring.domain.model;

import com.curso.cursospring.domain.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Cliente {

    @Id
    @NotNull(groups = ValidationGroups.ClienteId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String nome;

    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    @Size(max = 20)
    private String telefone;

//    @OneToMany
//    private List<OrdemServico> ordemServico;

}
