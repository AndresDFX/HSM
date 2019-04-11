/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author invitado
 */
public class Cama {
    
    /**
     * Identificador de la cama
     */
    private String codigo;
    
    /**
     * Despcricion 
     */
    private String descripcion;
    
    /**
     * Boolean con la disponibilidad
     */
    private boolean estado;
    /**
     * Identificador del area a la que pertenece
     */
    private String codigoArea;
    

    public Cama(String codigo, String codigoArea, String descripcion, boolean estado){
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.codigoArea = codigoArea;
    }
    
    //--------------------------------
    // GETS & SETS
    //--------------------------------
    
    public String getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean disponible) {
        this.estado = disponible;
    }
    
}
