/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 * Clase Empleado
 * clase que representa un empleado dentro de la clinica
 * Extiende de la clase persona
 * @author invitado
 */
public class Empleado extends Persona{
    
    //--------------------------------
    // Atributos
    //--------------------------------
    
    private String correo;
    
    private String cargo;
    
    private double salario;
    
    private String codigoArea;
    
    private String cedulaJefe;
    
    //--------------------------------
    // Constructor
    //--------------------------------

    public Empleado(String correo, String cargo, double salario, String cedula, String nombre, String direccion, String telefono, String codigoArea, String contrasena,String cedulaJefe) {
        super(cedula, nombre, direccion, telefono, contrasena);
        this.correo = correo;
        this.cargo = cargo;
        this.salario = salario;
        this.codigoArea = codigoArea;
        this.cedulaJefe = cedulaJefe;
    }
    
    //--------------------------------
    // GETS & SETS
    //--------------------------------

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }    public Empleado(String cedula, String nombre, String direccion, String telefono,String contrasena) {
        super(cedula, nombre, direccion, telefono, contrasena);
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }   

    public String getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }
    
    public String getJefe() {
        return cedulaJefe;
    }
    
}
