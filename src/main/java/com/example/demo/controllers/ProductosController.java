package com.example.demo.controllers;


import com.example.demo.entities.Producto;
import com.example.demo.services.ProductosService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;



import java.util.List;


@RestController
@RequestMapping("/productos")
public class ProductosController {

    private final ProductosService productosService;

    @Autowired
    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping
    public List<Producto> GetAll() {
        return productosService.getAll();
    }

    @GetMapping("/{id}")
    public Producto GetById(@PathVariable Long id) {
        return productosService.getById(id);
    }

    @PostMapping
    public Producto create( @RequestBody Producto producto) {
        return productosService.create(producto);
    }

    @PutMapping("/{id}")
    public Producto update(@PathVariable Long id, @RequestBody Producto producto) {
        return productosService.update(id, producto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productosService.delete(id);
    }


}
