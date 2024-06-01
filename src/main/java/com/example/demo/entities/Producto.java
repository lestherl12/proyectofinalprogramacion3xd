package com.example.demo.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "productos")

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long producto_id;

    private String nombre;

    private String categoria;

    private Integer existencia=0;

    private Double precio;


    public Long getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Long producto_id) {
        this.producto_id = producto_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getExistencia(){
        return this.existencia;
    }
    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void registrarCompra(Integer nuevoValor) {
        if (this.existencia!=null){
            this.existencia = this.existencia+ nuevoValor;
        }else{
            this.existencia=nuevoValor;
        }
    }
    public void registrarVenta(Integer nuevoValor) {

        if (this.existencia!=null){
            this.existencia = this.existencia- nuevoValor;
        }else{
            this.existencia=nuevoValor;
        }
    }

}
