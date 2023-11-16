package com.curso.cursospring.api.controller;

import com.curso.cursospring.api.dto.OrdemServicoDTO;
import com.curso.cursospring.api.dto.OrdemServicoInputDTO;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Mapeamento da ordem de serviços e processamento para DTO com o ModelMapper.
    private OrdemServicoDTO toMap(OrdemServico ordemServico) {
        return modelMapper.map(ordemServico, OrdemServicoDTO.class);
    }

    //Mapeamento da ordem de serviços e processamento para DTO com o ModelMapper.
    private List<OrdemServicoDTO> toMapList(List<OrdemServico> ordensServico) {
        return ordensServico.stream().map(ordemServico -> toMap(ordemServico)).collect(Collectors.toList());
    }

    //Mapeamento dos dados de entrada de ordem de serviço para Entity OrdemServico com o ModelMapper
    private OrdemServico toEntity(OrdemServicoInputDTO ordemServicoInputDTO) {
        return modelMapper.map(ordemServicoInputDTO, OrdemServico.class);
    }

    @GetMapping
    public List<OrdemServicoDTO> getOrdensServico() {

        return toMapList(ordemServicoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoDTO> getOrdemServicoById(@PathVariable Long id) {
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(id);

        if(ordemServico.isPresent()) {
            OrdemServicoDTO ordemServicoDTO = toMap(ordemServico.get());
            return ResponseEntity.ok(ordemServicoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServicoDTO save(@Valid @RequestBody OrdemServicoInputDTO ordemServicoInputDTO) {
        OrdemServico ordemServico = toEntity(ordemServicoInputDTO);

        return toMap(ordemServicoService.save(ordemServico));
    }

    @PutMapping("/{id}/finalizacao")
    public ResponseEntity finalizar(@PathVariable Long id) {

        return ordemServicoService.finalizar(id);
    }

    @PutMapping("/{id}/cancelamento")
    public ResponseEntity cancelar(@PathVariable Long id) {

        return ordemServicoService.cancelar(id);
    }

}
