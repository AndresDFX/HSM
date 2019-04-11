package Modelo.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * [CausasDAO] Clase que implementa el patron DAO de la clase Causas
 * @since 24/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class CausasDAO 
{
    private AccesoBD fachada;
    
//=======================================================================================================
    /**
     * Constructor de la clase CausasDAO
     * @since CausasDAO.java
     */     
    public CausasDAO()
    {
        fachada = new AccesoBD();
    }
//=======================================================================================================    
    public boolean guardarCausa(String codigo, String nombre, String descripcion)
    {
        String sql_insert, sql_llave;
        ResultSet resultado = null;
        
        sql_insert = "INSERT INTO causas VALUES ('"+codigo+"', '"+nombre+"', '"+descripcion+"');";
        sql_llave = "SELECT * FROM causas WHERE codigo = '"+codigo+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            Statement sentencia2 = conexion.createStatement();
            resultado = sentencia2.executeQuery(sql_llave);
            if(resultado.next()){
                System.out.println("Ya existen causas con ese código");
                return false;
                
            }else{
                int z = sentencia.executeUpdate(sql_insert);
                return z == 1;
            }
            
        }catch (SQLException ex) {
            System.out.println("Excepción en guardar causas");
            
        }
        return false;
    }
//=======================================================================================================    
    public boolean modificarCausas(String codigo, String nombre, String descripcion)
    {
        String sql_insert;
        sql_insert = "UPDATE causas SET nombre = '"+nombre+"', descripcion = '"+descripcion+"' WHERE codigo = '"+codigo+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            int z = sentencia.executeUpdate(sql_insert);
            return z == 1;
        }catch (SQLException ex) {
            System.out.println("Excepción en modificar causas");
        }
        return false;
    }
    
    public ResultSet mostrarCausas()
    {
        String sql;
        ResultSet resultado = null;
        sql = "SELECT codigo, nombre FROM causas";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar causas");
        }
        return resultado;
    }
//=======================================================================================================    
    public ResultSet consultarCausas(String codigo)
    {
        String sql;
        ResultSet resultado = null;
        sql = "SELECT * FROM causas WHERE codigo = '"+codigo+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en consultar causa");
        }
        return resultado;
    }
//=======================================================================================================    
    public boolean eliminarCausa(String codigo)
    {
        String sql_insert;
        sql_insert = "DELETE FROM causas WHERE codigo = '"+codigo+"' ;";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            int z = sentencia.executeUpdate(sql_insert);
            return z == 1;
        }catch (SQLException ex) {
            System.out.println("Excepción en eliminar causa");
        }
        return false;
    }
    
//=======================================================================================================    
    public boolean causasRegistros(String registro, String causa)
    {
        String sql_insert, sql_llave;
        ResultSet resultado = null;
        
        sql_insert = "INSERT INTO causas_registro VALUES ('"+registro+"', '"+causa+"');";
        sql_llave = "SELECT * FROM causas_registro WHERE codigo_registro = '"+registro+"' and codigo_causa = '"+causa+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            Statement sentencia2 = conexion.createStatement();
            resultado = sentencia2.executeQuery(sql_llave);
            if(resultado.next()){
                System.out.println("Ya existe esta causa para este registro");
                return false;
                
            }else{
                int z = sentencia.executeUpdate(sql_insert);
                return z == 1;
            }
            
        }catch (SQLException ex) {
            System.out.println("Excepción en insertar causas en registros");            
        }
        return false;
        
    }
//=======================================================================================================    

    public boolean existeCausa(String codigo)
    {
        String sql;
        ResultSet resultado = null;
        
        sql= "SELECT * FROM causas WHERE codigo = '"+codigo+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            if(resultado.next()){
                return true;
            }
            else{
                System.out.println("No existe la causa a modificar");
                return false;
            }
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar areas");
        }
        return false;
    }

    public ArrayList mostrarunaCausa(String cedula) {
        ArrayList<String> paciente = new ArrayList<>();
        String habilidad;
        habilidad = "SELECT * FROM causas WHERE codigo = '"+cedula+"';";
                    
        try {            
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();            
            if(existeCausa(cedula)){
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

    public ResultSet consultarCausas() {
        String sql;
        ResultSet resultado = null;
        sql = "SELECT causas.codigo, causas.nombre FROM causas;";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en consultar causa");
        }
        return resultado;
    }
}
