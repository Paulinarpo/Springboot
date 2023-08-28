package com.example.turismoapp.servicios;

import com.example.turismoapp.Validar.EmpresaValidacion;
import com.example.turismoapp.modelos.Empresa;
import com.example.turismoapp.repositorios.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class EmpresaServicio {
    @Autowired
    EmpresaRepositorio empresaRepositorio;
    @Autowired
    EmpresaValidacion empresaValidacion;
    public Empresa registrarEmpresa(Empresa datosARegistrar ) throws Exception{
        try {
            if(!this.empresaValidacion.validarNombre(datosARegistrar.getNombre())){
                throw new Exception("Error en el servicio");
            }

            // falta validar el nit
        //SI PASO TODOS LOS IF ESTOY LISTO PARA LLAMAR A REPOSITORIO
          return(this.empresaRepositorio.save(datosARegistrar));
        } catch (Exception error){
        throw new Exception(error.getMessage());
        }
    }
    public Empresa modificarEmpresa(Integer id, Empresa datosAmodificar) throws  Exception{
        try{
            //validamos la informacion
            if(this.empresaValidacion.validarNombre(datosAmodificar.getNombre())) {
                throw new Exception("Error en el dato entregado");
            }
            //buscar que la empresa que tiene el id que envia el usuario exista en BD
            Optional <Empresa>empresaEncontrada=this.empresaRepositorio.findById(id);
            //pregunto si lo que busque esta vacio (QUE NO ESTA)
            if(empresaEncontrada.isEmpty()){
                throw new Exception("Empresa no encontrada");
            }
            //Rutina POR SI SI LA ENCONTRE
            //1.Convierto el opcional en la entidad respectiva
            Empresa empresaQueEviste=empresaEncontrada.get();
            //2.a la empresa que existe le cambio la informacion que necesite
            empresaQueEviste.setNombre(datosAmodificar.getNombre());
            empresaQueEviste.setDescripcion(datosAmodificar.getDescripcion());
            empresaQueEviste.setUbicacion(datosAmodificar.getUbicacion());
            empresaQueEviste.setNit(datosAmodificar.getNit());

            //3.guardar la informacion que se acaba de modificar(set)
           return(this.empresaRepositorio.save(empresaQueEviste));
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }
    public Empresa buscarEmpresaporId(Integer id) throws Exception{
        try {
          Optional<Empresa> empresaOptional= this.empresaRepositorio.findById(id);
          if(empresaOptional.isEmpty()){
              throw new Exception("Empresa no encontrada");
          }
          return empresaOptional.get();
        } catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }
    public List<Empresa> buscarTodasEmpresas() throws Exception{
        try {
           List<Empresa> listaEmpresa=this.empresaRepositorio.findAll();
           return listaEmpresa;
        } catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }
    public boolean eliminarEmpresa(Integer id)throws Exception{
        try{
            Optional<Empresa>empresaOptional=this.empresaRepositorio.findById(id);
            if(empresaOptional.isPresent()){
                this.empresaRepositorio.deleteById(id);
                return true;
            }else{
                throw new Exception("Empresa no encontrada");
            }
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }

    }
}

