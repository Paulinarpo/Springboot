package com.example.turismoapp.repositorios;

import com.example.turismoapp.modelos.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepositorio extends JpaRepository<Empresa,Integer> {
    Optional<Empresa> findAllById(Integer id);
}
