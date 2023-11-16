package com.curso.cursospring.domain.service;

import com.curso.cursospring.domain.exception.NegocioException;
import com.curso.cursospring.domain.exception.NoFoundException;
import com.curso.cursospring.domain.model.Cliente;
import com.curso.cursospring.domain.model.OrdemServico;
import com.curso.cursospring.domain.model.StatusOrdemServico;
import com.curso.cursospring.domain.repository.ClienteRepository;
import com.curso.cursospring.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public OrdemServico save(OrdemServico ordemServico) {
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId()).orElseThrow(() -> new NegocioException("Cliente não encontrado."));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }

    public ResponseEntity cancelar(Long id) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id).orElseThrow(() -> new NoFoundException("Ordem de Serviço não encontrada."));

        if(ordemServico.podeSerCancelada()) {
            throw new NegocioException("Esta ordem de serviço já foi finalizada em " + ordemServico.getDataFechamento() +".");
        }

        ordemServico.setStatus(StatusOrdemServico.CANCELADA);
        ordemServico.setDataFechamento(OffsetDateTime.now());

        ordemServicoRepository.save(ordemServico);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity finalizar(Long id) {
        OrdemServico ordemServico = buscar(id);

        if(ordemServico.naoPodeSerFinalizada()) {
            throw new NegocioException("Esta ordem de serviço não pode ser finalizada.");
        }

        ordemServico.setStatus(StatusOrdemServico.FINALIZADA);
        ordemServico.setDataFechamento(OffsetDateTime.now());

        ordemServicoRepository.save(ordemServico);

         return ResponseEntity.noContent().build();
    }

    public OrdemServico buscar(Long id) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id).orElseThrow(() -> new NoFoundException("Ordem de Serviço não encontrada."));

        return ordemServico;
    }


}
