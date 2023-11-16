package com.curso.cursospring.domain.service;

import com.curso.cursospring.domain.exception.NegocioException;
import com.curso.cursospring.domain.exception.NoFoundException;
import com.curso.cursospring.domain.model.Comentario;
import com.curso.cursospring.domain.model.OrdemServico;
import com.curso.cursospring.domain.repository.ComentarioRepositoty;
import com.curso.cursospring.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class ComentarioService {

    @Autowired
    ComentarioRepositoty comentarioRepositoty;

    @Autowired
    OrdemServicoRepository ordemServicoRepository;

    public Comentario comentar(Long ordemServicoId, String descricao) {
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId).orElseThrow(() -> new NoFoundException("Ordem de Serviço não encontrada."));

        Comentario comentario = new Comentario();
        comentario.setData_envio(OffsetDateTime.now());
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServico);

        return comentarioRepositoty.save(comentario);
    }

}
