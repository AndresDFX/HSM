/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author invitado
 */
public class Area {
    
    //--------------------------------
    // Atributos
    //--------------------------------
    
    /**
     * String con el codigo del area
     */
    private String codigoArea;
    
    /**
     * String con el nombre del area
     */
    private String nombre;
    
    /**
     * String con la despcripción del area
     */
    private String descripcion;
    
    /**
     * Lista de camas del area
     */
    private ArrayList camas;

    //--------------------------------
    // Constructor
    //--------------------------------
    
    /**
     * Constructor de la clase Area
     * @param codigoArea - String
     * @param nombre -String
     * @param descripcion - String
     */
    public Area(String codigoArea, String nombre, String descripcion){
        this.codigoArea = codigoArea;
        this.nombre = nombre;
        this.descripcion = descripcion;
        camas = new ArrayList();
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList getCamas() {
        return camas;
    }

    public void setCamas(ArrayList camas) {
        this.camas = camas;
    }    
}
