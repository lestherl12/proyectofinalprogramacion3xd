package com.example.demo.controllers;


import com.example.demo.controllers.util.VentaBody;
import com.example.demo.entities.*;
import com.example.demo.other.Email;
import com.example.demo.services.ClientesService;
import com.example.demo.services.ProductosService;
import com.example.demo.services.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ventas")
public class VentasController {

    private final VentasService ventasService;
    private final ClientesService clientesService;
    private final ProductosService productosService;
    private final Email emailService;

    @Autowired
    public VentasController(VentasService ventasService, ClientesService clientesService, ProductosService productosService, Email emailService) {
        this.ventasService = ventasService;
        this.clientesService = clientesService;
        this.productosService = productosService;
        this.emailService = emailService;
    }

    @GetMapping
    public List<Venta> GetAll(){
        return ventasService.getAll();
    }


    @GetMapping(value="/{id}")
    public Venta GetById(@PathVariable Long id){
        return ventasService.getById(id);
    }


    @PostMapping
    public Venta create( @RequestBody VentaBody body) {


        Producto producto = productosService.getById(body.getProducto_id());
        Cliente cliente = clientesService.getById(body.getCliente_id());

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setProducto(producto);
        venta.setEstado("Pedido Recibido");
        venta.setCantidad(body.getCantidad());
        venta.setTotal(venta.getCantidad()*producto.getPrecio());
        producto.registrarVenta(body.getCantidad());
        productosService.update(producto.getProducto_id(),producto);

        if (cliente!=null){
            if (cliente.getCorreo()!=null){
                emailService.send(cliente.getCorreo(),"Pedido Recibido", "tu pedido ha sido recibido");
            }
        }

        return ventasService.create(venta);
    }
    @PutMapping("/{id}")
    public Venta update(Long id, @RequestBody VentaBody body){
        Producto producto = productosService.getById(body.getProducto_id());
        Cliente cliente = clientesService.getById(body.getCliente_id());

        Venta venta = ventasService.getById(id);
        Producto productoAnterior=productosService.getById(venta.getProducto().getProducto_id());
        Integer cantidadAnterior = venta.getCantidad();

        venta.setProducto(producto);
        venta.setCliente(cliente);
        venta.setCantidad(body.getCantidad());
        venta.setEstado("Pedido Recibido");
        venta.setCantidad(body.getCantidad());
        venta.setTotal(venta.getCantidad()*producto.getPrecio());
        productoAnterior.registrarCompra(cantidadAnterior);
        producto.registrarVenta(venta.getCantidad());

        productosService.update(productoAnterior.getProducto_id(),productoAnterior);
        productosService.update(producto.getProducto_id(),producto);
        if (cliente!=null){
            if (cliente.getCorreo()!=null){
                emailService.send(cliente.getCorreo(),"Pedido Recibido", "tu pedido ha sido recibido");
            }
        }
        return ventasService.update(venta.getVenta_id(), venta);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ventasService.delete(id);
    }
}
