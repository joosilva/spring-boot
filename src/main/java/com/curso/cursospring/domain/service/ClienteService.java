package com.curso.cursospring.domain.service;

import com.curso.cursospring.domain.exception.NegocioException;
import com.curso.cursospring.domain.model.Cliente;
import com.curso.cursospring.domain.repository.ClienteRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente save(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
        Cliente clienteExistenteTelefone = clienteRepository.findByTelefone(cliente.getTelefone());

        if(clienteExistente != null && !clienteExistente.getId().equals(cliente.getId()) && clienteExistente.getEmail().equals(cliente.getEmail())) {
            throw new NegocioException("Já existe um usuário cadastrado com este e-mail.");
        }
        if(clienteExistenteTelefone != null && !clienteExistenteTelefone.getId().equals(cliente.getId()) && clienteExistenteTelefone.getTelefone().equals(cliente.getTelefone())) {
            throw new NegocioException("Já existe um usuário cadastrado com este telefone.");
        }

        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

}
