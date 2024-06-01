package com.example.demo.services;

import com.example.demo.DAO.CompraDAO;
import com.example.demo.DAO.ProductoDAO;
import com.example.demo.entities.Compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComprasServiceImpl implements ComprasService {

    @Autowired
    private CompraDAO compraDAO;

    @Autowired
    private ProductoDAO productoDAO;

    @Override
    public List<Compra> getAll() {
        return compraDAO.findAll();
    }

    @Override
    public Compra getById(Long id) {
        return compraDAO.findById(id).orElse(null);
    }

    @Override
    public Compra create(Compra compra) {
        return compraDAO.save(compra);
    }

    @Override
    public Compra update(Long id, Compra compra) {
        if (compraDAO.existsById(id)) {
            compra.setCompra_id(id);
            return compraDAO.save(compra);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if (compraDAO.existsById(id)) {
            compraDAO.deleteById(id);
        }
    }

}


