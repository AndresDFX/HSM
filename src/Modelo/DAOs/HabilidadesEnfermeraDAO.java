package Modelo.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * [HabilidadesEnfermeraDAO] Clase que implementa el patron DAO de la clase Enfermera
 * @since 24/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class HabilidadesEnfermeraDAO 
{
    private AccesoBD fachada;
    
//=======================================================================================================
    /**
     * Constructor de la clase HabilidadesEnfermeraDAO
     * @since HabilidadesEnfermeraDAO.java
     */ 
    
    public HabilidadesEnfermeraDAO() {
        this.fachada = new AccesoBD();
    }
//=======================================================================================================    
    public boolean guardarHabilidades(String codigo, String nombre){
        String sql_insert, sql_llave;
        ResultSet resultado = null;
        
        sql_insert = "INSERT INTO habilidades VALUES ('"+codigo+"', '"+nombre+"');";
        sql_llave = "SELECT * FROM habilidades WHERE codigo = '"+codigo+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            Statement sentencia2 = conexion.createStatement();
            resultado = sentencia2.executeQuery(sql_llave);
            if(resultado.next()){
                System.out.println("Ya existen habilidades con ese código");
                return false;
                
            }else{
                int z = sentencia.executeUpdate(sql_insert);
                return z == 1;
            }
            
        }catch (SQLException ex) {
            System.out.println("Excepción en insertar habilidades");
            
        }
        return false;
    }
//=======================================================================================================    
    public boolean modificarHabiliades(String codigo, String nombre){
        String sql_insert;
        sql_insert = "UPDATE habilidades SET nombre = '"+nombre+"' WHERE codigo = '"+codigo+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            int z = sentencia.executeUpdate(sql_insert);
            return z == 1;
        }catch (SQLException ex) {
            System.out.println("Excepción en modificar habilidades");
        }
        return false;
    }
//=======================================================================================================    
    public ResultSet consultarHabilidad(String codigo){
        String sql;
        ResultSet resultado = null;
        sql = "SELECT * FROM habilidades WHERE codigo = '"+codigo+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en consultar habilidades");
        }
        return resultado;
    }
//=======================================================================================================    
    public ResultSet mostrarHabilidades(){
        String sql;
        ResultSet resultado = null;
        sql = "SELECT codigo, nombre FROM habilidades";
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
//=======================================================================================================    
    public boolean habilidadesEnfermeras(String cedula, String codigo){
        String sql_insert, sql_llave;
        ResultSet resultado = null;
        
        sql_insert = "INSERT INTO habilidades_enfermeras VALUES ('"+cedula+"', '"+codigo+"');";
        sql_llave = "SELECT * FROM habilidades_enfermeras WHERE cedula = '"+cedula+"' and codigo_habilidad = '"+codigo+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            Statement sentencia2 = conexion.createStatement();
            resultado = sentencia2.executeQuery(sql_llave);
            if(resultado.next()){
                System.out.println("Ya existe esta habilidad para esta enfermera");
                return false;
                
            }else{
                int z = sentencia.executeUpdate(sql_insert);
                return z == 1;
            }
            
        }catch (SQLException ex) {
            System.out.println("Excepción en insertar habilidades de enfermeras");            
        }
        return false;
        
    }
//=======================================================================================================

        public boolean existeHabilidad(String codigo){
        String sql = "SELECT * FROM habilidades WHERE codigo = '"+codigo+"';";
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
        
    public ArrayList <String> mostrarHabilidad(String codigoHabilidad)
    {
        ArrayList<String> paciente = new ArrayList<>();
        String habilidad;
        habilidad = "SELECT * FROM habilidades WHERE codigo = '"+codigoHabilidad+"';";
                    
        try {            
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();            
            if(existeHabilidad(codigoHabilidad)){
                ResultSet resultado = sentencia.executeQuery(habilidad);
                resultado.next();
                paciente.add(resultado.getString("codigo"));
                paciente.add(resultado.getString("nombre"));
            }else{
                System.out.println("No hay resulset en mostrar pacientes(Dao)");
                paciente = null;
            }
        }catch (SQLException ex) {
            System.out.println("Ocurrió un problema al traer los datos del empleado");            
        }      
        return paciente;
    }    

    public ResultSet consultarHabilidades() {
 
        String sql;
        ResultSet resultado = null;
        sql = "SELECT habilidades.codigo, habilidades.nombre FROM habilidades";
        
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
