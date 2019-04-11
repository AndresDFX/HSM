package Modelo.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * [MedicamentoDAO] Clase que implementa el patron DAO de la clase Medicamento
 * @since 24/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class MedicamentoDAO 
{
    private AccesoBD fachada;
    
//=======================================================================================================    
    /**
     * Constructor de la clase MedicamentoDAO
     * @since MedicamentoDAO.java
     */
    public MedicamentoDAO(){
        fachada = new AccesoBD();
    }
    
    public boolean guardarMedicamento(String codigo, String nombre, String descripcion, String costo)
    {
        String sql_insert, sql_llave;
        ResultSet resultado = null;
        
        sql_insert = "INSERT INTO medicamentos VALUES ('"+codigo+"', '"+nombre+"', '"+descripcion+"', '"+costo+"');";
        sql_llave = "SELECT * FROM medicamentos WHERE codigo = '"+codigo+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            Statement sentencia2 = conexion.createStatement();
            resultado = sentencia2.executeQuery(sql_llave);
            if(resultado.next()){
                System.out.println("Ya existen medicamentos con ese código");
                return false;
                
            }else{
                int z = sentencia.executeUpdate(sql_insert);
                return z == 1;
            }
            
        }catch (SQLException ex) {
            System.out.println("Excepción en guardar medicamentos");
            
        }
        return false;
    }
//=======================================================================================================    
    public boolean modificarmedicamentos(String codigo, String nombre, String descripcion, String costo){
        String sql_insert;
        sql_insert = "UPDATE medicamentos SET nombre = '"+nombre+"', descripcion = '"+descripcion+"', costo = '"+costo+"' WHERE codigo = '"+codigo+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            int z = sentencia.executeUpdate(sql_insert);
            return z == 1;
        }catch (SQLException ex) {
            System.out.println("Excepción en modificar medicamentos");
        }
        return false;
    }
//=======================================================================================================    
    public ResultSet consultarMedicamento(String codigo){
        String sql;
        ResultSet resultado = null;
        sql = "SELECT * FROM medicamentos WHERE codigo = '"+codigo+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en consultar medicamentos");
        }
        return resultado;
    }
//=======================================================================================================    
    public boolean eliminarMedicamento(String codigo){
        String sql_insert;
        sql_insert = "DELETE FROM medicamentos WHERE codigo = '"+codigo+"' ;";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            int z = sentencia.executeUpdate(sql_insert);
            return z == 1;
        }catch (SQLException ex) {
            System.out.println("Excepción en eliminar medicamento");
        }
        return false;
    }
//=======================================================================================================    
    public ResultSet mostrarmedicamentos(){
        String sql;
        ResultSet resultado = null;
        sql = "SELECT codigo, nombre, costo FROM medicamentos";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar medicamentos");
        }
        return resultado;
    }
//=======================================================================================================    
    public boolean medicamentosRegistro(String registro, String medicamento){
        String sql_insert, sql_llave;
        ResultSet resultado = null;
        
        sql_insert = "INSERT INTO registros_medicamentos VALUES ('"+registro+"', '"+medicamento+"');";
        sql_llave = "SELECT * FROM registros_medicamentos WHERE codigo_registro = '"+registro+"' and codigo_medicamento = '"+medicamento+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            Statement sentencia2 = conexion.createStatement();
            resultado = sentencia2.executeQuery(sql_llave);
            if(resultado.next()){
                System.out.println("Ya existe este medicamento para este registro");
                return false;
                
            }else{
                int z = sentencia.executeUpdate(sql_insert);
                return z == 1;
            }
            
        }catch (SQLException ex) {
            System.out.println("Excepción en insertar medicamentos en registros");            
        }
        return false;
        
    }
//=======================================================================================================   

    public boolean existeMedicina(String codigo) {
        String sql = "SELECT * FROM medicamentos WHERE codigo = '"+codigo+"';";
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
    
    
    
     public ArrayList <String> mostrarMedicina(String codigo)
    {
        ArrayList<String> paciente = new ArrayList<>();
        String habilidad;
        habilidad = "SELECT * FROM medicamentos WHERE codigo = '"+codigo+"';";
                    
        try {            
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();            
            if(existeMedicina(codigo)){
                ResultSet resultado = sentencia.executeQuery(habilidad);
                resultado.next();
                paciente.add(resultado.getString("codigo"));
                paciente.add(resultado.getString("nombre"));
                paciente.add(resultado.getString("descripcion"));
                paciente.add(resultado.getString("costo"));
            }else{
                System.out.println("No hay resulset en mostrar pacientes(Dao)");
                paciente = null;
            }
        }catch (SQLException ex) {
            System.out.println("Ocurrió un problema al traer los datos del empleado");            
        }      
        return paciente;
    
    }

    public ResultSet consultarMedicamentos() {
       String sql;
        ResultSet resultado = null;
        sql = "SELECT medicamentos.codigo, medicamentos.nombre, medicamentos.costo FROM medicamentos";
        
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
