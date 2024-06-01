package com.example.demo.services;

import com.example.demo.DAO.ProveedorDAO;

import com.example.demo.entities.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedoresServiceImpl implements ProveedoresService {

    @Autowired
    private ProveedorDAO proveedorDAO;

    @Override
    public List<Proveedor> getAll() {
        return proveedorDAO.findAll();
    }

    @Override
    public Proveedor getById(Long id) {
        return proveedorDAO.findById(id).orElse(null);
    }

    @Override
    public Proveedor create(Proveedor proveedor) {
        return proveedorDAO.save(proveedor);
    }

    @Override
    public Proveedor update(Long id, Proveedor proveedor) {
        if (proveedorDAO.existsById(id)) {
            proveedor.setProveedor_id(id);
            return proveedorDAO.save(proveedor);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if (proveedorDAO.existsById(id)) {
            proveedorDAO.deleteById(id);
        }
    }
}
