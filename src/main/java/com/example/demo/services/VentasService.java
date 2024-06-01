package com.example.demo.services;


import com.example.demo.entities.Venta;

import java.util.List;

public interface VentasService {
    List<Venta> getAll();
    Venta getById(Long id);
    Venta create(Venta venta);
    Venta update(Long id, Venta venta);
    void delete(Long id);
}
