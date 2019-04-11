package Modelo.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Modelo.Paciente;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * [PacienteDAO] Clase que implementa el patron DAO de la clase Paciente
 * @since 24/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class PacienteDAO 
{
    private AccesoBD fachada;

//=======================================================================================================
    
    /**
     * Constructor de clase PacienteDAO
     * @since PacienteDAO.java
     */
    public PacienteDAO()
    {
        fachada = new AccesoBD();
    }

//=======================================================================================================    
    public boolean guardarPaciente(Paciente paciente){
        String sql_insertPersona, sql_llave, sql_llavePa, sql_insertPaciente, sql_llaveNumero;
        ResultSet resultado = null;
        
        sql_insertPersona = "INSERT INTO personas VALUES ('"+paciente.getCedula()+"','"+paciente.getNombre()+"','"+paciente.getDireccion()+"','"+paciente.getTelefono()+"', '"+paciente.getContrasena()+"','"+"Paciente');";
        sql_insertPaciente = "INSERT INTO pacientes VALUES ('"+paciente.getNumeroSeguridad()+"','"+paciente.getFechaNacimiento()+"','"+paciente.getOcupacion()+"', '"+paciente.getCedula()+"', '0');";
        sql_llave = "SELECT * FROM personas WHERE cedula = '"+paciente.getCedula()+"';";
        sql_llavePa = "SELECT * FROM pacientes WHERE cedula = '"+paciente.getCedula()+"';";
        sql_llaveNumero = "SELECT * FROM pacientes WHERE seguridad_social = '"+paciente.getNumeroSeguridad()+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();           
            resultado = sentencia.executeQuery(sql_llave);
            if(resultado.next()){
                System.out.println("Ya existe una persona con la misma cedula");
                return false;              
            }
            else{
                resultado = sentencia.executeQuery(sql_llaveNumero);
                if(resultado.next()){
                    System.out.println("Ya existe una persona con la misma seguridad social");
                    return false;   
                }else{
                    sentencia.executeUpdate(sql_insertPaciente);
                    sentencia.executeUpdate(sql_insertPersona);
                    return true;
                }
            }
        }catch (SQLException ex) {
            System.out.println("Excepción en insertar pacientes");
            ex.printStackTrace();            
        }
        return false;
    }    
//=======================================================================================================    
    public boolean existePaciente(String cedula){
        String sql = "SELECT * FROM pacientes WHERE cedula = '"+cedula+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            if(resultado.next()){
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
//=======================================================================================================    
    public boolean modificarPaciente(String cedula, String nombre, String direccion, String telefono, String social, String fecha, String actividad){
        String sql_updatePa, sql_updatePer;
        sql_updatePer = "UPDATE personas SET nombre = '"+nombre+"', direccion = '"+direccion+"', telefono = '"
                +telefono+"' WHERE cedula = '"+cedula+"';";
        sql_updatePa = "UPDATE pacientes SET seguridad_social = '"+social+"', fecha_nacimiento = '"+fecha+"', ocupacion = '"
                +actividad+"' WHERE cedula = '"+cedula+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            int x = sentencia.executeUpdate(sql_updatePer);
            int y = sentencia.executeUpdate(sql_updatePer);
            return (x == 1 && y == 1);
        }catch (SQLException ex) {
            System.out.println("Excepción en modificar pacientes");
        }
        return false;
    }
//=======================================================================================================    
    public ResultSet consultarPaciente(String cedula){
        String sql;
        ResultSet resultado = null;
        ResultSet resultado2 = null;
        sql = "SELECT * FROM pacientes NATURAL JOIN personas WHERE cedula = '"+cedula+"';";;
        try {
            Connection conexion = fachada.getConnetion();
            PreparedStatement pstm = conexion.prepareStatement(sql);
            resultado = pstm.executeQuery();            
            
            conexion.getMetaData().getTables(null, null, "%",null);
            String tabla="";
            while (resultado2.next())
            {
                tabla = resultado2.getObject(1).toString();
            }
                       
            System.out.println(tabla);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en consultar pacientes");
        }
        
        catch (NullPointerException e)
        {
            System.out.println("No funciono");
        }
        return resultado;
    }
//=======================================================================================================   
    public ResultSet mostrarPacientes(){
        String sql;
        ResultSet resultado = null;
        sql = "SELECT pacientes.cedula, personas.nombre FROM personas NATURAL JOIN pacientes;";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar pacientes");
        }
        return resultado;
    }
//=======================================================================================================
    
    public ArrayList <String> mostrarPaciente(String cedula)
    {
        ArrayList<String> paciente = new ArrayList<>();
        String pacientes, persona;
        pacientes = "SELECT * FROM pacientes WHERE cedula = '"+cedula+"';";
        persona = "SELECT * FROM personas WHERE cedula = '"+cedula+"';";
             
        try {            
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();            
            if(existePaciente(cedula)){
                ResultSet resultado = sentencia.executeQuery(pacientes);
                resultado.next();
                paciente.add(resultado.getString("seguridad_social"));
                paciente.add(resultado.getString("fecha_nacimiento"));
                paciente.add(resultado.getString("ocupacion"));
                paciente.add(resultado.getString("cedula"));
                resultado = sentencia.executeQuery(persona);
                resultado.next();
                paciente.add(resultado.getString("nombre"));
                paciente.add(resultado.getString("direccion"));
                paciente.add(resultado.getString("telefono"));
            }else{
                System.out.println("No hay resulset en mostrar pacientes(Dao)");
                paciente = null;
            }
        }catch (SQLException ex) {
            System.out.println("Ocurrió un problema al traer los datos del paciente");            
        }      
        return paciente;
    }

//=======================================================================================================
   
    
    
    
    public ResultSet consultarPacientes(){
        String sql;
        ResultSet resultado = null;
        sql = "SELECT pacientes.cedula, personas.nombre, pacientes.ocupacion FROM personas NATURAL JOIN pacientes;";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar pacientes");
        }
        return resultado;
    }
}
    
    
        
   
            
    
    
    


