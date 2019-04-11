/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 * Clase que representa una camapaña de prenvencion
 * @author invitado
 */
public class CampaniaPrevencion {
    
    //--------------------------------
    // Atributos
    //--------------------------------
    private String codigo;
    
    private String nombre;
    
    private String fechaRealizacion;
    
    private String objetivo;
    
    //--------------------------------
    // Constructor
    //--------------------------------

    public CampaniaPrevencion(String codigo, String nombre, String fechaRealizacion, String objetivo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaRealizacion = fechaRealizacion;
        this.objetivo = objetivo;
    }
    
    //--------------------------------
    // GETS & SETS
    //--------------------------------

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(String fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }    
    
}
