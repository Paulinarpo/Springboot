package com.example.turismoapp.Validar;

import org.springframework.stereotype.Component;
@Component
public class OfertaValidacion {
        public Boolean ValidarTitulo(String titulo) throws Exception{
            if(titulo.length()>30){
                return false;
            }else{
                return true;
            }
        }
    }

