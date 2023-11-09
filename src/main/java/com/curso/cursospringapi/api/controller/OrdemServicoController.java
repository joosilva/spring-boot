package com.curso.cursospringapi.api.controller;

import com.curso.cursospringapi.domain.model.Cliente;
import com.curso.cursospringapi.domain.model.OrdemServico;
import com.curso.cursospringapi.domain.repository.OrdemServicoRepository;
import com.curso.cursospringapi.domain.service.OrdemServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @GetMapping
    public List<OrdemServico> getOrdensServico() {

        return ordemServicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> getClienteById(@PathVariable Long id) {
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(id);

        if(ordemServico.isPresent()) {
            return ResponseEntity.ok(ordemServico.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico save(@Valid @RequestBody OrdemServico ordemServico) {

        return ordemServicoService.save(ordemServico);
    }

}
