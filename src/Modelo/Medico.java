/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 * Clase que representa un medico que a su vez es un empleado
 * @author invitado
 */
public class Medico{
    
    //--------------------------------
    // Atributos
    //--------------------------------
    
    /**
     * Especialidad del medico
     */
    private String especialidad;
    
    /**
     * Numero de la licencia del medico
     */
    private String numeroLicencia;
    
    /**
     * Universidad de la que se graduo el medico
     */
    private String universidad;
    
    private String cedula;
    
    
    //--------------------------------
    // Constructor
    //--------------------------------

    public Medico(String especialidad, String numeroLicencia, String universidad, String cedula) {
        
        this.especialidad = especialidad;
        this.numeroLicencia = numeroLicencia;
        this.universidad = universidad;
        this.cedula = cedula;
    }
    
    //--------------------------------
    // GETS & SETS
    //--------------------------------

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    
    
    
}
