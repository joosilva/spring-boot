package com.curso.cursospring.api.controller;

import com.curso.cursospring.api.dto.ComentarioDTO;
import com.curso.cursospring.api.dto.ComentarioInputDTO;
import com.curso.cursospring.domain.exception.NoFoundException;
import com.curso.cursospring.domain.model.Comentario;
import com.curso.cursospring.domain.model.OrdemServico;
import com.curso.cursospring.domain.repository.OrdemServicoRepository;
import com.curso.cursospring.domain.service.ComentarioService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    private ComentarioDTO toMap(Comentario comentario) {

        return modelMapper.map(comentario, ComentarioDTO.class);
    }

    private List<ComentarioDTO> toMapList(List<Comentario> comentarios) {

        return comentarios.stream().map(comentario -> toMap(comentario)).collect(Collectors.toList());
    }

    @GetMapping
    public List<ComentarioDTO> getComentarios(@PathVariable Long ordemServicoId) {
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId).orElseThrow(() -> new NoFoundException("Ordem de Serviço não encontrada."));

        return toMapList(ordemServico.getComentarios());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioDTO comentar(@PathVariable Long ordemServicoId, @Valid @RequestBody ComentarioInputDTO comentarioInputDTO) {
        Comentario comentario = comentarioService.comentar(ordemServicoId, comentarioInputDTO.getDescricao());

        return toMap(comentario);
    }

}
