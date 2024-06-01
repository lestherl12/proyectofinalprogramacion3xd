package com.example.demo.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "ventas")

public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venta_id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="producto_id")
    private Producto producto;

    private String estado;

    private Integer cantidad;


    private Double total;

    public Long getVenta_id() {
        return venta_id;
    }

    public void setVenta_id(Long venta_id) {
        this.venta_id = venta_id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
