package com.example.turismoapp.Validar;

import org.springframework.stereotype.Component;

@Component
public class EmpresaValidacion {
    public Boolean validarNombre(String nombre) throws Exception{
        if(nombre.length()>30){
           return false;
        }else{
            return true;
        }
    }
}
