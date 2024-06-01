package com.example.demo.controllers;


import com.example.demo.entities.Cliente;
import com.example.demo.services.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/clientes")
public class ClientesController {

    private final ClientesService clientesService;

    @Autowired
    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping
    public List<Cliente> GetAll() {
        return clientesService.getAll();
    }

    @GetMapping("/{id}")
    public Cliente GetById(@PathVariable Long id) {
        return clientesService.getById(id);
    }

    @PostMapping
    public Cliente create(@RequestBody Cliente cliente) {
        return clientesService.create(cliente);
    }

    @PutMapping("/{id}")
    public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clientesService.update(id, cliente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clientesService.delete(id);
    }

}
