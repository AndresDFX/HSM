package Modelo.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Modelo.Cama;
import java.util.ArrayList;

/**
 * [CamaDAO] Clase que implementa el patron DAO de la clase Cama
 * @since 24/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class CamaDAO {
    
    private AccesoBD fachada;
    
//=======================================================================================================
    /**
     * Constructor de la clase CamaDAO
     * @since CamaDAO.java
     */
    public CamaDAO() 
    {
        fachada = new AccesoBD();
    }
//=======================================================================================================    
    public boolean guardarCama(Cama cama)
    {
        String sql_insert, sql_llave;
        ResultSet resultado = null;
        
        sql_insert = "INSERT INTO camas VALUES ('"+cama.getCodigo()+"', '"+cama.getDescripcion()+"', '"
                +cama.isEstado()+"', '"+cama.getCodigoArea()+"');";
        sql_llave = "SELECT * FROM camas WHERE codigo = '"+cama.getCodigo()+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            Statement sentencia2 = conexion.createStatement();
            resultado = sentencia2.executeQuery(sql_llave);
            if(resultado.next()){
                System.out.println("Ya existen camas con ese código");
                return false;
            }else{
                int z = sentencia.executeUpdate(sql_insert);
                return z == 1;
            }
        }catch (SQLException ex) {
            System.out.println("Excepción en guardar cama");
        }
        return false;
    }
//=======================================================================================================    
    public boolean modificarCama(String codigo, String codigoArea, String descripcion, boolean estado)
    {
        String sql_update, sql_area;
        ResultSet resultado = null;        
        
        sql_update = "UPDATE camas SET codigo_area = '"+codigoArea+"', descripcion = '"
                +descripcion+"', estado = '"+estado+"' WHERE codigo = '"+codigo+"';";
        sql_area = "DELETE FROM camas_paciente WHERE codigo = '"+codigo+"'; ";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            Statement sentencia2 = conexion.createStatement();
            
            
            if(existeCama(codigo)){
               sentencia2.executeQuery(sql_update);
               int z = sentencia.executeUpdate(sql_area);
               return z == 1;
            }else{
                System.out.println("No existen camas con ese código");
                return false;
            }
        }catch (SQLException ex) {
           
            System.out.println("Excepción en modificar cama:" + ex);
        }
        return false;
    }
    
//=======================================================================================================    
    public boolean existeCama(String codigo){
        try {
            ResultSet cama = consultarCama(codigo);
            if(cama.next()){
                return true;
            }
            
        } catch (Exception e) {
        }
        return false;
    }
//=======================================================================================================    
    public ResultSet consultarCama(String codigo){
        String sql;
        ResultSet resultado = null;
        sql = "SELECT * FROM camas WHERE codigo = '"+codigo+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en consultar cama");
        }
        return resultado;
    }
//=======================================================================================================    
    public boolean eliminarCama(String codigo){
        String sql_insert;
        sql_insert = "DELETE FROM camas WHERE codigo = '"+codigo+"' ;";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            int z = sentencia.executeUpdate(sql_insert);
            return z == 1;
        }catch (SQLException ex) {
            System.out.println("Excepción en eliminar cama");
        }
        return false;
    }
//=======================================================================================================    
    public boolean camasPacientes(String cedula, String codigo, String fecha)
    {
        String sql_insert, sql_llave;
        ResultSet resultado = null;
        
        sql_insert = "INSERT INTO camas_paciente VALUES ('"+codigo+"', '"+cedula+"', '"+fecha+"');";
        sql_llave = "SELECT * FROM camas_paciente WHERE cedula_paciente = '"+cedula+"' and codigo_cama = '"+codigo+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            Statement sentencia2 = conexion.createStatement();
            resultado = sentencia2.executeQuery(sql_llave);
            if(resultado.next()){
                System.out.println("Ya existe esta cama para este paciente");
                return false;
                
            }else{
                int z = sentencia.executeUpdate(sql_insert);
                return z == 1;
            }
            
        }catch (SQLException ex) {
            System.out.println("Excepción en insertar cama al paciente");            
        }
        return false;
    }
//=======================================================================================================    
    public ResultSet mostrarCama()
    {
        String sql;
        ResultSet resultado = null;
        sql = "SELECT camas.codigo, areas.nombre FROM camas INNER JOIN areas ON camas.codigo_area = areas.codigo;";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar cama");
        }
        return resultado;
    }
//=======================================================================================================    
    public ResultSet mostrarCamas()
    {
        String sql;
        ResultSet resultado = null;
        sql = "SELECT codigo, descripcion FROM camas WHERE estado = 'false';";
        
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
//=======================================================================================================

    public ArrayList mostrarCama(String codigo) {
        ArrayList<String> paciente = new ArrayList<>();
        String habilidad,persona;
        habilidad = "SELECT * FROM camas WHERE codigo = '"+codigo+"';";

                    
        try {            
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();            
            if(existeCama(codigo)){
                ResultSet resultado = sentencia.executeQuery(habilidad);
                resultado.next();
                paciente.add(resultado.getString("codigo"));
                paciente.add(resultado.getString("descripcion"));
                paciente.add(resultado.getString("codigo_area"));
                String cedula = resultado.getString("codigo_area");
                persona = "SELECT * FROM areas WHERE codigo = '"+cedula+"';";      
                resultado = sentencia.executeQuery(persona);
                resultado.next();
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

    public ResultSet consultarCamas() {
        String sql;
        ResultSet resultado = null;
        sql = "SELECT camas.codigo, areas.nombre FROM camas INNER JOIN areas ON camas.codigo_area = areas.codigo;";
          
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
