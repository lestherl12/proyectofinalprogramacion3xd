package com.example.demo.DAO;


import com.example.demo.entities.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoDAO extends JpaRepository<Producto, Long> {
    Page<Producto> findByCategoria(String category, Pageable pageable);
}
