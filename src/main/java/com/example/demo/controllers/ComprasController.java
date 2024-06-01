package com.example.demo.controllers;


import com.example.demo.controllers.util.CompraBody;
import com.example.demo.entities.Compra;
import com.example.demo.entities.Producto;
import com.example.demo.entities.Proveedor;
import com.example.demo.services.ComprasService;
import com.example.demo.services.ProductosService;
import com.example.demo.services.ProveedoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class ComprasController {

    private final ComprasService comprasService;
    private final ProductosService productosService;
    private final ProveedoresService proveedoresService;

    @Autowired
    public ComprasController(ComprasService comprasService, ProductosService productosService, ProveedoresService proveedoresService) {
        this.comprasService = comprasService;
        this.productosService = productosService;
        this.proveedoresService=proveedoresService;
    }


    @GetMapping
    public List<Compra> getAll() {
        return comprasService.getAll();
    }

    @GetMapping("/{id}")
    public Compra getById(@PathVariable Long id) {
        return comprasService.getById(id);
    }

    @PostMapping
    public Compra create(@RequestBody CompraBody body) {

        Producto producto = productosService.getById(body.getProducto_id());
        Proveedor proveedor = proveedoresService.getById(body.getProveedor_id());

        Compra compra= new Compra();
        compra.setProducto(producto);
        compra.setProveedor(proveedor);
        compra.setCantidad(body.getCantidad());
        producto.registrarCompra(compra.getCantidad());
        productosService.update(producto.getProducto_id(),producto);
        return comprasService.create(compra);
    }

    @PutMapping("/{id}")
    public Compra update(@PathVariable Long id, @RequestBody CompraBody body) {
        Producto producto = productosService.getById(body.getProducto_id());
        Proveedor proveedor = proveedoresService.getById(body.getProveedor_id());



        Compra compra= comprasService.getById(id);

        Producto productoAnterior=productosService.getById(compra.getProducto().getProducto_id());
        Integer cantidadAnterior = compra.getCantidad();

        compra.setProducto(producto);
        compra.setProveedor(proveedor);
        compra.setCantidad(body.getCantidad());


        productoAnterior.registrarVenta(cantidadAnterior);
        producto.registrarCompra(compra.getCantidad());

        productosService.update(productoAnterior.getProducto_id(),productoAnterior);
        productosService.update(producto.getProducto_id(),producto);
        return comprasService.update(id, compra);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        comprasService.delete(id);
    }


}
