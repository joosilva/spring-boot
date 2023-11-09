package com.curso.cursospringapi.domain.service;

import com.curso.cursospringapi.domain.exception.NegocioException;
import com.curso.cursospringapi.domain.model.Cliente;
import com.curso.cursospringapi.domain.model.OrdemServico;
import com.curso.cursospringapi.domain.model.StatusOrdemServico;
import com.curso.cursospringapi.domain.repository.ClienteRepository;
import com.curso.cursospringapi.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

}
