package Modelo.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Modelo.Empleado;

/**
 * [EmpleadoDAO] Clase que implementa el patron DAO de la clase Empleado
 * @since 24/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class EmpleadoDAO {
    
    private AccesoBD fachada;
    
//=======================================================================================================
    /**
     * Constructor de la clase EmpleadoDAO
     * @since CitaDAO.java
     */  
    
    public EmpleadoDAO()
    {
        fachada = new AccesoBD();
    }
//=======================================================================================================    
    public boolean guardarEmpleado(Empleado empleado)
    {       
        boolean guardado = false;
        String insertarPersona, existe, existe2, insertarEmpleado;
        ResultSet resultado = null;
        
        insertarPersona = "INSERT INTO personas VALUES ('"+empleado.getCedula()+"','"+empleado.getNombre()+"','"+empleado.getDireccion()+"','"+empleado.getTelefono()+"','"+empleado.getContrasena()+"','"+empleado.getCargo()+"');";
        insertarEmpleado = "INSERT INTO empleados VALUES ('"+empleado.getCargo()+"','"+empleado.getSalario()+"','"+empleado.getCorreo()+"','"+empleado.getCedula()+"','"+empleado.getCodigoArea()+"','"+empleado.getJefe()+"');";
        existe = "SELECT * FROM empleados WHERE cedula = '"+empleado.getCedula()+"';";
        existe2 = "SELECT * FROM personas WHERE cedula = '"+empleado.getCedula()+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();           
            resultado = sentencia.executeQuery(existe2);
            if(!resultado.next()){                
                resultado = sentencia.executeQuery(existe);
                if(!resultado.next()){                    
                    sentencia.executeUpdate(insertarEmpleado);
                    sentencia.executeUpdate(insertarPersona);
                    guardado = true;                  
                }
                else{
                    System.out.println("Ya existe un empleado con la misma cedula");
                    guardado = false;  
                }
            }
            else{
                System.out.println("Ya existe una persona con la misma cedula");
                guardado = false;  
            }
        }            
        catch (SQLException ex) {
            System.out.println("Excepción en insertar empleado");
            ex.printStackTrace();            
        }
        return guardado;
    }
//=======================================================================================================    
    public boolean existeEmpleadoPersona(String cedula)
    {
        boolean exist = false;
        String existe = "SELECT * FROM empleados WHERE cedula = '"+cedula+"';";
        String existe2 = "SELECT * FROM personas WHERE cedula = '"+cedula+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();           
            ResultSet resultado = sentencia.executeQuery(existe2);
            if(resultado.next()){
                resultado = sentencia.executeQuery(existe);
                if(resultado.next()){                    
                    exist = true;                 
                }
            }
        }catch(SQLException e){
            System.out.println("Error en existe empleado(dao)");
        }
        return exist;
    }
//=======================================================================================================    
    /**
     * cargo, salario, email, cedula, codigo_area, codigo_jefe
     * nombre, direccion, telefono
     * @return 
     */
    public ArrayList <String> mostrarEmpleado(String cedula)
    {
        ArrayList<String> empleado = new ArrayList<>();
        String empleados, persona,area;
        empleados = "SELECT * FROM empleados WHERE cedula = '"+cedula+"';";
        persona = "SELECT * FROM personas WHERE cedula = '"+cedula+"';";
        
        
        try {            
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();            
            if(existeEmpleadoPersona(cedula)){
                ResultSet resultado = sentencia.executeQuery(empleados);
                resultado.next();
                empleado.add(resultado.getString("cargo"));
                empleado.add(resultado.getString("salario"));
                empleado.add(resultado.getString("email"));
                empleado.add(resultado.getString("cedula"));
                empleado.add(resultado.getString("codigo_area"));
                empleado.add(resultado.getString("cedula_jefe"));
                String codigo = resultado.getString("codigo_area");
                area = "SELECT * FROM areas WHERE codigo = '"+codigo+"';";
                resultado = sentencia.executeQuery(persona);
                resultado.next();
                empleado.add(resultado.getString("nombre"));
                empleado.add(resultado.getString("direccion"));
                empleado.add(resultado.getString("telefono"));
                resultado = sentencia.executeQuery(area);
                resultado.next();
                empleado.add(resultado.getString("nombre"));
            }else{
                System.out.println("No hay resulset en mostrar empleados(Dao)");
                empleado = null;
            }
        }catch (SQLException ex) {
            System.out.println("Ocurrió un problema al traer los datos del empleado");            
        }      
        return empleado;
    }
//=======================================================================================================    
    public boolean modificarEmpleado(String correo, String cargo, double salario, String cedula, String nombre, String direccion, String telefono, String codigoArea, String cedulaJefe)
    {
        String updatePersona, updateEmpleado, existe;
        boolean modificado = false;
        existe = "SELECT * FROM empleados WHERE cedula = '"+cedula+"';";
        updatePersona = "UPDATE personas SET nombre = '"+nombre+"', direccion = '"+direccion+"', telefono = '"+telefono+"' WHERE cedula = '"+cedula+"'";
        updateEmpleado = "UPDATE empleados SET email = '"+correo+"', cargo = '"+cargo+"', cedula_jefe = '"+cedulaJefe+"', codigo_area = '"+codigoArea+"', salario = '"+salario+"' WHERE cedula = '"+cedula+"'";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(existe);
            if(resultado.next()){
                sentencia.executeUpdate(updatePersona);
                sentencia.executeUpdate(updateEmpleado);                
                modificado = true;
            }else{
                modificado = false;
                
            }             
        }catch (SQLException ex) {
            System.out.println("Excepción en modificar empleado");
        }
        return modificado;
    }
//=======================================================================================================    
    public ResultSet consultarEmpleado(String cedula)
    {
        String sql;
        
        ResultSet resultado = null;
        sql = "SELECT * FROM empleados NATURAL JOIN personas WHERE cedula = '"+cedula+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en consultar empleado");
        }
        return resultado;
    }
//=======================================================================================================    
    public ResultSet listarEmpleadosArea(String codigo)
    {
        ResultSet resultado = null;
        String sql;
        
        sql = "SELECT * FROM personas NATURAL JOIN empleados WHERE codigo_area = '"+codigo+"' ;";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
        }catch (SQLException ex) {
            System.out.println("Excepción en listar empleados");
        }
        
        return resultado;
    }

    public ResultSet consultarEmpleados() {
         
        String sql;
        ResultSet resultado = null;
        sql = "SELECT empleados.cedula, personas.nombre, empleados.cargo FROM personas NATURAL JOIN empleados;";
        
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
