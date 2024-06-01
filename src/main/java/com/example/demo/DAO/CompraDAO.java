package com.example.demo.DAO;


import com.example.demo.entities.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraDAO extends JpaRepository<Compra, Long> {
}
