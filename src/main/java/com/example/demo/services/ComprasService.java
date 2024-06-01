package com.example.demo.services;


import com.example.demo.entities.Compra;

import java.util.List;

public interface ComprasService {
    List<Compra> getAll();
    Compra getById(Long id);
    Compra create(Compra compra);
    Compra update(Long id, Compra compra);
    void delete(Long id);
}
