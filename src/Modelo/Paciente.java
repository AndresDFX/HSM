/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 * Clase Paciente
 * Clase que representa un paciente en la clinica
 * Extiende de Persona
 * @author invitado
 */
public class Paciente extends Persona{
    
    //--------------------------------
    // Atributos
    //--------------------------------
    
    /**
     * String que representa el numero de seguridad social del paciente
     */
    private String numeroSeguridad;
    
    private String fechaNacimiento;
    
    private String ocupacion;
    
    private HistoriaClinica historiaClinica;
    
    //--------------------------------
    // Constructor
    //--------------------------------

    public Paciente(String numeroSeguridad, String fechaNacimiento, String ocupacion, String cedula, String nombre, String direccion, String telefono, String contrasena) {
        super(cedula, nombre, direccion, telefono,contrasena);
        this.numeroSeguridad = numeroSeguridad;
        this.fechaNacimiento = fechaNacimiento;
        this.ocupacion = ocupacion;
        
    }
    
    //--------------------------------
    // GETS & SETS
    //--------------------------------

    public String getNumeroSeguridad() {
        return numeroSeguridad;
    }

    public void setNumeroSeguridad(String numeroSeguridad) {
        this.numeroSeguridad = numeroSeguridad;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }
    
    
}
