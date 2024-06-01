package com.example.demo.services;


import com.example.demo.entities.Producto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductosService {
    List<Producto> getAll();
    Producto getById(Long id);
    Producto create(Producto producto);
    Producto update(Long id, Producto producto);
    void delete(Long id);
    Page<Producto> getPaginate(int page, int size, String category);
}
