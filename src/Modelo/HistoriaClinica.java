/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 * Clase que representa la historia clinica de un paciente
 * @author invitado
 */
public class HistoriaClinica {
    
    //--------------------------------
    // Atributos
    //--------------------------------
    /**
     * Representa un numero consecutivo de historia clinica
     */
    private String numero;
    
    /**
     * La fecha de apertura de la historia clinica
     */
    private String fechaApertura;
    
    private String cedula;
    //--------------------------------
    // Constructor
    //--------------------------------

    public HistoriaClinica(String numero, String cedula) {
        this.numero = numero;
        this.cedula = cedula;
    }
    
    //--------------------------------
    // GETS & SETS
    //--------------------------------

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
}
