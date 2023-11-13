package com.curso.cursospring.api.controller;

import com.curso.cursospring.domain.model.Cliente;
import com.curso.cursospring.domain.repository.ClienteRepository;
import com.curso.cursospring.domain.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> getClientes() {

        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente postCliente(@Valid @RequestBody Cliente cliente) {

        return clienteService.save(cliente);
    }

    @PutMapping("{id}")
    public ResponseEntity<Cliente> putCliente(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
        if(clienteRepository.existsById(id)) {
            cliente.setId(id);
            cliente = clienteService.save(cliente);

            return ResponseEntity.ok(cliente);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCliente (@PathVariable Long id) {
        if(clienteRepository.existsById(id)) {
            clienteService.deleteById(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}