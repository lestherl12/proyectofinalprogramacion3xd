package com.example.demo.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "compras")

public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long compra_id;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;



    private Integer cantidad;

    public Long getCompra_id() {
        return compra_id;
    }

    public void setCompra_id(Long compra_id) {
        this.compra_id = compra_id;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }


    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
