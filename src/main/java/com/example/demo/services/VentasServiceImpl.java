package com.example.demo.services;

import com.example.demo.DAO.VentaDAO;

import com.example.demo.entities.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentasServiceImpl implements VentasService {

    @Autowired
    private VentaDAO ventaDAO;

    @Override
    public List<Venta> getAll() {
        return ventaDAO.findAll();
    }

    @Override
    public Venta getById(Long id) {
        return ventaDAO.findById(id).orElse(null);
    }

    @Override
    public Venta create(Venta venta) {
        return ventaDAO.save(venta);
    }

    @Override
    public Venta update(Long id, Venta venta) {
        if (ventaDAO.existsById(id)) {
            venta.setVenta_id(id);
            return ventaDAO.save(venta);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if (ventaDAO.existsById(id)) {
            ventaDAO.deleteById(id);
        }
    }
}
