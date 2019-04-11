package Modelo.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Modelo.Enfermera;

/**
 * [EnfermeraDAO] Clase que implementa el patron DAO de la clase Enfermera
 * @since 24/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class EnfermeraDAO {

    private AccesoBD fachada;
    
//=======================================================================================================
    /**
     * Constructor de la clase EnfermeraDAO
     * @since EnfermeraDAO.java
     */  
    
    public EnfermeraDAO(){
        fachada = new AccesoBD();
    }
//=======================================================================================================    
    public boolean guardarEnfermera(Enfermera enfermera){
               
        boolean guardado = false;
        String insertarEnfermera, existe;
        ResultSet resultado = null;
        
        insertarEnfermera = "INSERT INTO enfermeras VALUES ('"+enfermera.getAniosExperiencia()+"','"+enfermera.getCedula()+"');";
        existe = "SELECT * FROM enfermeras WHERE cedula = '"+enfermera.getCedula()+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();           
            resultado = sentencia.executeQuery(existe);
            if(resultado.next()){
                System.out.println("Ya existe una enfermera con la misma cedula");
                guardado = false;                
            }
            else{
                sentencia.executeUpdate(insertarEnfermera);
                guardado = true;
            }
        }            
        catch (SQLException ex) {
            System.out.println("Excepción en guardar enfermera");
            ex.printStackTrace();            
        }
        return guardado;
    }
//=======================================================================================================    
    public boolean modificarEnfermera(String cedula, String anosExperiencia){
       boolean modificado = false;
        String updateEnfermera, existe;
        existe = "SELECT * FROM enfermeras WHERE cedula = '"+cedula+"';";
        updateEnfermera = "UPDATE enfermeras SET anos_experiencia = '"+anosExperiencia+"' WHERE cedula = '"+cedula+"'";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(existe);
            if(resultado.next()){
                sentencia.executeUpdate(updateEnfermera);                
                modificado = true;
            }else{
                modificado = false;
                System.out.println("No existe la enfermera con la cédula indicada");
            }             
        }catch (SQLException ex) {
            System.out.println("Excepción en modificar enfermera");
        }
        return modificado;
    }
//=======================================================================================================    
    public ResultSet consultarEnfermera(String cedula){
        String sql;
        ResultSet resultado = null;
        sql = "SELECT * FROM enfermeras WHERE cedula = '"+cedula+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en consultar enfermeras");
        }
        return resultado;
    }
//=======================================================================================================  
        public ResultSet mostrarEnfermeras(){
        String sql;
        ResultSet resultado = null;
        sql = "SELECT cedula FROM enfermeras";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar habilidades");
        }
        return resultado;
    }
}
