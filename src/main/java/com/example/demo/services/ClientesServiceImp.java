package com.example.demo.services;



import com.example.demo.DAO.ClienteDAO;
import com.example.demo.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesServiceImp implements ClientesService {

    @Autowired
    ClienteDAO clienteDAO;

    @Override
    public List<Cliente> getAll() {
        return clienteDAO.findAll();
    }

    @Override
    public Cliente getById(Long id) {
        return clienteDAO.findById(id).orElse(null);
    }

    @Override
    public Cliente create(Cliente customer) {
        return clienteDAO.save(customer);
    }

    @Override
    public Cliente update(Long id, Cliente customer) {
        if (clienteDAO.existsById(id)) {
            customer.setCliente_id(id);
            return clienteDAO.save(customer);
        }
        return null;
    }
    @Override
    public void delete(Long id) {
        if (clienteDAO.existsById(id)) {
            clienteDAO.deleteById(id);
        }
    }
}
