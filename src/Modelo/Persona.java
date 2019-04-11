package Modelo;

/**
 * Clase: Persona
 * Clase que representa los atributos básicos de una persona
 * @author invitado
 */
public class Persona {


    
    //--------------------------------
    // Atributos
    //--------------------------------
    
    private String cedula;
    
    private String nombre;
    
    private String direccion;
    
    private String telefono;
    
    private String contrasena;
    
    //private String tipo_usuario;
    
    
    //--------------------------------
    // Constructor
    //--------------------------------

    public Persona(String cedula, String nombre, String direccion, String telefono, String contrasena) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.contrasena= contrasena;
        //this.tipo_usuario = tipo_usuario;
    }
    
    //--------------------------------
    // GETS & SETS
    //--------------------------------

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
}