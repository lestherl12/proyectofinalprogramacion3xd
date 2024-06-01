package com.example.demo.controllers.util;

public class CompraBody {
    private Long producto_id;
    private Long proveedor_id;
    private Integer cantidad;

    public Long getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Long producto_id) {
        this.producto_id = producto_id;
    }

    public Long getProveedor_id() {
        return proveedor_id;
    }

    public void setProveedor_id(Long proveedor_id) {
        this.proveedor_id = proveedor_id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
