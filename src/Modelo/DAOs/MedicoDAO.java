package Modelo.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Modelo.Medico;

/**
 * [MedicoDAO] Clase que implementa el patron DAO de la clase Medico
 * @since 24/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class MedicoDAO {

    private final AccesoBD fachada;
    
//=======================================================================================================
    /**
     * Constructor de clase MedicoDAO
     * @since MedicoDAO.java
     */
    public MedicoDAO()
    {
        fachada = new AccesoBD();
    }
    
//=======================================================================================================    
    public boolean guardarMedico(Medico medico){
               
        boolean guardado = false;
        String insertarMedico, existe;
        ResultSet resultado = null;
        
        insertarMedico = "INSERT INTO medicos VALUES ('"+medico.getNumeroLicencia()+"','"+medico.getEspecialidad()+"','"+medico.getUniversidad()+"','"+medico.getCedula()+"');";
        existe = "SELECT * FROM medicos WHERE cedula = '"+medico.getCedula()+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();           
            resultado = sentencia.executeQuery(existe);
            if(resultado.next()){
                System.out.println("Ya existe un médico con la misma cedula");
                guardado = false;                
            }
            else{
                sentencia.executeUpdate(insertarMedico);
                guardado = true;
            }
        }            
        catch (SQLException ex) {
            System.out.println("Excepción en guardar medico");
            ex.printStackTrace();            
        }
        return guardado;
    }
//=======================================================================================================    
    public boolean modificarMedico(String especialidad, String numeroLicencia, String universidad, String cedula){
        boolean modificado = false;
        String updateMedico, existe;
        existe = "SELECT * FROM medicos WHERE cedula = '"+cedula+"';";
        updateMedico = "UPDATE medicos SET especialidad = '"+especialidad+"', numero_licencia = '"+numeroLicencia+"', universidad = '"+universidad+"' WHERE cedula = '"+cedula+"'";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(existe);
            if(resultado.next()){
                sentencia.executeUpdate(updateMedico);                
                modificado = true;
            }else{
                modificado = false;
                System.out.println("No existe el médico con la cédula indicada");
            }             
        }catch (SQLException ex) {
            System.out.println("Excepción en modificar medico");
        }
        return modificado;
    }
//=======================================================================================================    
    public ResultSet consultarMedico(String cedula){
        String sql;
        ResultSet resultado = null;
        sql = "SELECT * FROM medicos WHERE cedula = '"+cedula+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en consultar medico");
        }
        return resultado;
    }
//=======================================================================================================    
    public ResultSet mostrarMedicos(){
        String sql;
        ResultSet resultado = null;
        sql = "SELECT medicos.cedula, personas.nombre FROM personas NATURAL JOIN medicos;";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar medicos");
        }
        return resultado;
    }
//=======================================================================================================    
}
