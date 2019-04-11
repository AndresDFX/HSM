package Modelo.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Modelo.CampaniaPrevencion;
import java.util.ArrayList;

/**
 * [CampaniaDAO] Clase que implementa el patron DAO de la clase Campania
 * @since 24/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class CampaniaDAO {
    
    private AccesoBD fachada;
    
//=======================================================================================================
    /**
     * Constructor de la clase CampaniaDAO
     * @since CamapaniaDAO.java
     */    
    public CampaniaDAO()
    {
        fachada = new AccesoBD();
    }
//=======================================================================================================    
    public boolean guardarCampania(CampaniaPrevencion campania, String cedula)
    {       
        boolean guardado = false;
        String insertarCampania, existe, existe2;
       
        insertarCampania = "INSERT INTO campania VALUES ('"+campania.getCodigo()+"','"+campania.getNombre()+"','"+campania.getObjetivo()+"','"+campania.getFechaRealizacion()+"', '"+cedula+"');";
        existe = "SELECT* FROM campania WHERE codigo = '"+campania.getCodigo()+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();           
            ResultSet resultado = sentencia.executeQuery(existe);
                if(resultado.next()){
                    System.out.println("Ya existe una campaña con este código.");
                }else{
                    sentencia.executeUpdate(insertarCampania);
                    guardado = true;
                }
        }          
        catch (SQLException ex) {
            System.out.println("Excepción en insertar campaña");
            ex.printStackTrace();            
        }
        return guardado;
    }
//=======================================================================================================    
    public boolean modificarCampania(String codigo, String nombre, String fecha, String objetivo, String cedula){
        String updateCampania, existe;
        boolean modificado = false;
        existe = "SELECT * FROM campania WHERE codigo = '"+codigo+"';";
        updateCampania = "UPDATE campania SET nombre = '"+nombre+"', objetivo = '"+objetivo+"', fecha = '"+fecha+"', cedula = '"+cedula+"' WHERE codigo = '"+codigo+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();           
            ResultSet resultado = sentencia.executeQuery(existe);
            
            if(resultado.next()){
                sentencia.executeUpdate(updateCampania);
                modificado = true;
            }else{
                System.out.println("No existe la campañaa");
            }
        }            
        catch (SQLException ex) {
            System.out.println("Excepción en modificar campaña");
            ex.printStackTrace();            
        }        
        return modificado;
    }
//=======================================================================================================    
    public boolean existeCampa(String cod){
        ResultSet resultado = null;
        String sql = "SELECT * FROM campania WHERE codigo = '"+cod+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            if(resultado.next()){
                return true;
            }
        }catch(SQLException e){
            
        }
        return false;
    }
//=======================================================================================================    
    public ResultSet consultarCampa(String codigo){
        String sql;
        ResultSet resultado = null;
        sql = "SELECT * FROM campania WHERE codigo = '"+codigo+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en consultar campaña");
        }
        return resultado;
    }
//=======================================================================================================    
    public boolean eliminarCampa(String codigo)
    {
        String sql_insert;
        sql_insert = "DELETE FROM campania WHERE codigo = '"+codigo+"' ;";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            int z = sentencia.executeUpdate(sql_insert);
            return z == 1;
        }catch (SQLException ex) {
            System.out.println("Excepción en eliminar campaña");
        }
        return false;
    }
//=======================================================================================================    
    public ResultSet mostrarCampa()
    {
        String sql;
        ResultSet resultado = null;
        sql = "SELECT codigo, nombre FROM campania";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar campaña");
        }
        return resultado;
    }
//=======================================================================================================    
    public boolean registrarPacienteCampania(String codigo, String cedula)
    {
        String existeCampania, existePaciente, insertarPaciente, existeTabla, campanias;
        
        existeCampania = "SELECT * FROM campania WHERE codigo = '"+codigo+"';";
        existeTabla = "SELECT * FROM campanias_pacientes WHERE codigo_campania = '"+codigo+"' and cedula_paciente = '"+cedula+"';";
        existePaciente = "SELECT * FROM pacientes WHERE cedula = '"+cedula+"';";
        insertarPaciente = "INSERT INTO campanias_pacientes VALUES('"+codigo+"','"+cedula+"')";
        campanias = "SELECT campanias FROM pacientes WHERE cedula = '"+cedula+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();           
            ResultSet resultado = sentencia.executeQuery(existeCampania);
            if(resultado.next()){
                resultado = sentencia.executeQuery(existePaciente);
                if(resultado.next()){
                    resultado = sentencia.executeQuery(existeTabla);
                    if(resultado.next()){
                        System.out.println("Ya existe el paciente en la campaña");
                        return false;
                    }else{
                        resultado = sentencia.executeQuery(campanias);
                        if(resultado.next()){
                            int x = resultado.getInt("campanias");
                            x++;
                            int y = sentencia.executeUpdate("UPDATE pacientes SET campanias ='"+x+"' WHERE cedula = '"+cedula+"';");
                            int z = sentencia.executeUpdate(insertarPaciente);
                            return z == 1;
                        }else{
                            System.out.println("No hay campanas(null)");
                            return false;
                        }
                    }
                }else{
                    System.out.println("No existe el paciente");
                    return false;
                }
            }else{
                System.out.println("No existe la campaña");
                return false;
            }
        }
        catch (SQLException ex) {
            System.out.println("Excepción en asignar paciente en la campaña");
            ex.printStackTrace();
        }
        return false;
    }
//=======================================================================================================    

    public ArrayList mostrarCampa(String codigo) {
        ArrayList<String> paciente = new ArrayList<>();
        String habilidad,persona;
        habilidad = "SELECT * FROM campania WHERE codigo = '"+codigo+"';";
                   
        try {            
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();            
            if(existeCampa(codigo)){
                ResultSet resultado = sentencia.executeQuery(habilidad);
                resultado.next();
                paciente.add(resultado.getString("codigo"));
                paciente.add(resultado.getString("nombre"));
                paciente.add(resultado.getString("objetivo"));
                paciente.add(resultado.getString("fecha"));
                paciente.add(resultado.getString("cedula"));
                String cedula = resultado.getString("cedula");
                persona = "SELECT * FROM personas WHERE cedula = '"+cedula+"';";      
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

    public ResultSet consultarCampanas() {
        String sql;
        ResultSet resultado = null;
        sql = "SELECT campania.codigo, campania.nombre, campania.objetivo FROM campania";
        
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
