package com.example.demo.DAO;


import com.example.demo.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaDAO extends JpaRepository<Venta, Long> {
}
