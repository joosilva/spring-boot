package com.curso.cursospring.api.controller;

import com.curso.cursospring.api.dto.ClienteDTO;
import com.curso.cursospring.domain.model.Cliente;
import com.curso.cursospring.domain.repository.ClienteRepository;
import com.curso.cursospring.domain.service.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    private ClienteDTO toMap(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    private List<ClienteDTO> toMapList(List<Cliente> clientes) {
        return clientes.stream().map(cliente -> toMap(cliente)).collect(Collectors.toList());
    }

    @GetMapping
    public List<ClienteDTO> getClientes() {

        return toMapList(clienteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            ClienteDTO clienteDTO = toMap(cliente.get());

            return ResponseEntity.ok(clienteDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO postCliente(@Valid @RequestBody Cliente cliente) {

        return toMap(clienteService.save(cliente));
    }

    @PutMapping("{id}")
    public ResponseEntity<ClienteDTO> putCliente(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
        if (clienteRepository.existsById(id)) {
            cliente.setId(id);
            cliente = clienteService.save(cliente);
            ClienteDTO clienteDTO = toMap(cliente);

            return ResponseEntity.ok(clienteDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (clienteRepository.existsById(id)) {
            clienteService.deleteById(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}