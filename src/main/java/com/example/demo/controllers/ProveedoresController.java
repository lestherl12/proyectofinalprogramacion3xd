package com.example.demo.controllers;


import com.example.demo.entities.Proveedor;
import com.example.demo.services.ProveedoresService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedoresController {

    private final ProveedoresService proveedoresService;

    @Autowired
    public ProveedoresController(ProveedoresService proveedoresService) {
        this.proveedoresService = proveedoresService;
    }

    @GetMapping
    public List<Proveedor> getAll() {
        return proveedoresService.getAll();
    }

    @GetMapping("/{id}")
    public Proveedor getById(@PathVariable Long id) {
        return proveedoresService.getById(id);
    }

    @PostMapping
    public Proveedor create(@RequestBody Proveedor proveedor) {
        return proveedoresService.create(proveedor);
    }

    @PutMapping("/{id}")
    public Proveedor update(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        return proveedoresService.update(id, proveedor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        proveedoresService.delete(id);
    }


}
