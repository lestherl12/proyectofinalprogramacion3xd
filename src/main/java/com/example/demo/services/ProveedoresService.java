package com.example.demo.services;


import com.example.demo.entities.Proveedor;

import java.util.List;

public interface ProveedoresService {
    List<Proveedor> getAll();
    Proveedor getById(Long id);
    Proveedor create(Proveedor proveedor);
    Proveedor update(Long id, Proveedor proveedor);
    void delete(Long id);
}
