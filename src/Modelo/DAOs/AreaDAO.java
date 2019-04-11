package Modelo.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Modelo.Area;
import java.util.ArrayList;

/**
 * [AreaDAO] Clase que implementa el patron DAO de la clase Area
 * @since 24/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class AreaDAO {
 
    private AccesoBD fachada;

//=======================================================================================================
    /**
     * Constructor de la clase AreaDAO
     * @since AreaDAO.java
     */
    public AreaDAO()
    {
        fachada = new AccesoBD();
    }
    
//=======================================================================================================    
    public boolean guardarArea(Area area)
    {
        String sql_insert, sql_llave;
        ResultSet resultado = null;
        
        sql_insert = "INSERT INTO areas VALUES ('"+area.getCodigoArea()+"', '"+area.getNombre()+"', '"+area.getDescripcion()+"');";
        sql_llave = "SELECT * FROM areas WHERE codigo = '"+area.getCodigoArea()+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            Statement sentencia2 = conexion.createStatement();
            resultado = sentencia2.executeQuery(sql_llave);
            if(resultado.next()){
                System.out.println("Ya existen areas con ese código");
                return false;
                
            }else{
                int z = sentencia.executeUpdate(sql_insert);
                return z == 1;
            }
            
        }catch (SQLException ex) {
            System.out.println("Excepción en guardar area");
            
        }
        return false;
    }
//=======================================================================================================    
    public boolean modificarArea(String codigo, String nombre, String descripcion){
        String sql_insert;
        sql_insert = "UPDATE areas SET nombre = '"+nombre+"', descripcion = '"+descripcion+"' WHERE codigo = '"+codigo+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            int z = sentencia.executeUpdate(sql_insert);
            return z == 1;
        }catch (SQLException ex) {
            System.out.println("Excepción en modificar area");
        }
        return false;
    }
//=======================================================================================================    
    public ResultSet consultarAreaa(String codigo){
        String sql;
        ResultSet resultado = null;
        sql = "SELECT * FROM areas WHERE codigo = '"+codigo+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en consultar area");
        }
        return resultado;
    }
    
//=======================================================================================================    
    public boolean eliminarArea(String codigo)
    {
        String sql_insert;
        sql_insert = "DELETE FROM areas WHERE codigo = '"+codigo+"' ;";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            int z = sentencia.executeUpdate(sql_insert);
            return z == 1;
        }catch (SQLException ex) {
            System.out.println("Excepción en eliminar area");
        }
        return false;
    }
//=======================================================================================================    
    public ResultSet mostrarAreas()
    {
        String sql;
        ResultSet resultado = null;
        sql = "SELECT codigo, nombre FROM areas";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar areas");
        }
        return resultado;
    }
//=======================================================================================================    
    public boolean existeArea(String codigo)
    {
        String sql;
        ResultSet resultado = null;
        
        sql= "SELECT * FROM areas WHERE codigo = '"+codigo+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            if(resultado.next()){
                return true;
            }
            else{
                System.out.println("No existe el área a modificar");
                return false;
            }
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar areas");
        }
        return false;
    }
//=======================================================================================================
    
    public ResultSet datosArea(String codigo)
    {
        String sql;
        ResultSet resultado = null;
        
        sql= "SELECT * FROM areas WHERE codigo = '"+codigo+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en traer areas");
        }
        return resultado;
    }
//=======================================================================================================

    public ArrayList mostrarArea(String cedula) {
       ArrayList <String> paciente = new ArrayList<>();
        String habilidad;
        habilidad = "SELECT * FROM areas WHERE codigo = '"+cedula+"';";
                    
        try {            
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();            
            if(existeArea(cedula)){
                ResultSet resultado = sentencia.executeQuery(habilidad);
                resultado.next();
                paciente.add(resultado.getString("codigo"));
                paciente.add(resultado.getString("nombre"));
                paciente.add(resultado.getString("descripcion"));
            }else{
                System.out.println("No hay resulset en mostrar pacientes(Dao)");
                paciente = null;
            }
        }catch (SQLException ex) {
            System.out.println("Ocurrió un problema al traer los datos del empleado");            
        }      
        return paciente;
    }

    public ResultSet consultarAreas() {
        String sql;
        ResultSet resultado = null;
        sql = "SELECT areas.codigo, areas.nombre FROM areas;";
          
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar camas");
        }
        return resultado;
    
    }
}
