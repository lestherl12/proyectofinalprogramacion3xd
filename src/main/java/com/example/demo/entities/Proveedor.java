package com.example.demo.entities;


import jakarta.persistence.*;


@Entity
@Table(name = "proveedores")

public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proveedor_id;


    private String nombre;


    private String telefono;


    private String direccion;


    public Long getProveedor_id() {
        return proveedor_id;
    }

    public void setProveedor_id(Long proveedor_id) {
        this.proveedor_id = proveedor_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
