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
        /*Pensando em uma regra mais geral, seria mais prudente utilizar findById, uma vez que esse método será reutilizado para atualizações.
        Se a alteração for o próprio e-mail, esse usuário não será encontrado, criando um novo ao invés de atualizar um já existente.*/
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

        if(clienteExistente != null && clienteExistente.equals(cliente)) {
            throw new NegocioException("Já existe um cadastro para este em-mail.");
        }

        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

}
