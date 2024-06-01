package com.example.demo.services;



import com.example.demo.entities.Cliente;

import java.util.List;

public interface ClientesService {
    List<Cliente> getAll();
    Cliente getById(Long id);
    Cliente create(Cliente cliente);
    Cliente update(Long id, Cliente cliente);
    void delete(Long id);
}