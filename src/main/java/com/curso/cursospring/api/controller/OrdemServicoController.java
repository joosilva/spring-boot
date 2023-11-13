package com.curso.cursospring.api.controller;

import com.curso.cursospring.api.dto.OrdemServicoDTO;
import com.curso.cursospring.domain.model.OrdemServico;
import com.curso.cursospring.domain.repository.OrdemServicoRepository;
import com.curso.cursospring.domain.service.OrdemServicoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<OrdemServico> getOrdensServico() {

        return ordemServicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoDTO> getOrdemServicoById(@PathVariable Long id) {
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(id);

        if(ordemServico.isPresent()) {
            OrdemServicoDTO ordemServicoDTO = modelMapper.map(ordemServico.get(), OrdemServicoDTO.class);
            return ResponseEntity.ok(ordemServicoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico save(@Valid @RequestBody OrdemServico ordemServico) {

        return ordemServicoService.save(ordemServico);
    }

}
