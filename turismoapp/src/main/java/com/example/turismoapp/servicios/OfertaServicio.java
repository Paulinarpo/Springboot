package com.example.turismoapp.servicios;

import com.example.turismoapp.Validar.OfertaValidacion;
import com.example.turismoapp.modelos.Empresa;
import com.example.turismoapp.modelos.Oferta;
import com.example.turismoapp.repositorios.OfertaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OfertaServicio {
    @Autowired
    OfertaRepositorio ofertaRepositorio;

    @Autowired
    OfertaValidacion ofertaValidacion;

    public Oferta registrarOferta(Oferta datosARegistrar ) throws Exception{
        try {

            return (this.ofertaRepositorio.save(datosARegistrar));

        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }


    public Oferta modificarOferta(Integer id, Oferta datosAmodificar) throws  Exception{
        try{

            Optional<Oferta> ofertaEncontrada=this.ofertaRepositorio.findById(id);
            if(ofertaEncontrada.isEmpty()){
                throw new Exception("Oferta no encontrada");
            }
            Oferta OfertaQueExiste=ofertaEncontrada.get();
            OfertaQueExiste.setTitulo(datosAmodificar.getTitulo());
            OfertaQueExiste.setDescripcion(datosAmodificar.getDescripcion());

            return this.ofertaRepositorio.save(OfertaQueExiste);
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public Oferta buscarOfertaporId(Integer id) throws Exception{
        try{
            Optional<Oferta> OfertaOptional= this.ofertaRepositorio.findById(id);
            if(OfertaOptional.isEmpty()){
                throw new Exception("Oferta no encontrada");
            }return OfertaOptional.get();
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }
    public List<Oferta> buscarTodaslasOfertas() throws Exception{
        try{
            List<Oferta> listaOferta=this.ofertaRepositorio.findAll();
            return listaOferta;
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }
    public boolean eliminarOferta(Integer id)throws Exception{
        try {
            Optional<Oferta>ofertaOptional=this.ofertaRepositorio.findById(id);
            if(ofertaOptional.isPresent()){
                this.ofertaRepositorio.deleteById(id);
                return true;
            }else{
                throw new Exception("Oferta no encontrada");
            }
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }
}
