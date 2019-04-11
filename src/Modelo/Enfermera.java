/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 * Clase que representa una enfermera que a su vez es un empleado
 * @author invitado
 */
public class Enfermera{
    
    //--------------------------------
    // Atributos
    //--------------------------------
    
    /**
     * Anios de experiencia
     */
    private String aniosExperiencia, cedula;
    
    //--------------------------------
    // Constructor
    //--------------------------------

    public Enfermera(String aniosExperiencia, String cedula) {
        
        this.aniosExperiencia = aniosExperiencia;
        this.cedula = cedula;
    }   
    
    
    //--------------------------------
    // GETS & SETS
    //--------------------------------

    public String getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(String aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }   
    
    
}
