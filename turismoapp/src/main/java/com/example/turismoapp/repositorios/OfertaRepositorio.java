package com.example.turismoapp.repositorios;

import com.example.turismoapp.modelos.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaRepositorio extends JpaRepository<Oferta,Integer> {


}
