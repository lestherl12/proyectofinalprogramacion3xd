package com.example.demo.services;

import com.example.demo.DAO.ProductoDAO;

import com.example.demo.entities.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosServiceImpl implements ProductosService {

    @Autowired
    private ProductoDAO productoDAO;

    @Override
    public List<Producto> getAll() {
        return productoDAO.findAll();
    }

    public Page<Producto> getPaginate(int page, int size, String category) {
        Pageable pageable = PageRequest.of(page, size);

        if ((category == null || category.isEmpty())) {
            return productoDAO.findAll(pageable);
        } else {
            return productoDAO.findByCategoria(category, pageable);
        }
    }

    @Override
    public Producto getById(Long id) {
        return productoDAO.findById(id).orElse(null);
    }

    @Override
    public Producto create(Producto producto) {
        return productoDAO.save(producto);
    }

    @Override
    public Producto update(Long id, Producto producto) {
        if (productoDAO.existsById(id)) {
            producto.setProducto_id(id);
            return productoDAO.save(producto);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if (productoDAO.existsById(id)) {
            productoDAO.deleteById(id);
        }
    }
}
