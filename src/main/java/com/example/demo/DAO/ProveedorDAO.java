package com.example.demo.DAO;


import com.example.demo.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorDAO extends JpaRepository<Proveedor, Long> {
}
