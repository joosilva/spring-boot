package com.curso.cursospring.domain.service;

import com.curso.cursospring.domain.exception.NegocioException;
import com.curso.cursospring.domain.model.Cliente;
import com.curso.cursospring.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente save(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

        if(clienteExistente != null && clienteExistente.getEmail().equals(cliente.getEmail())) {
            throw new NegocioException("Já existe um cadastro para este em-mail.");
        }

        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

}
