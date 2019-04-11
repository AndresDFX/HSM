package Modelo.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import Modelo.Cita;
import java.util.ArrayList;

/**
 * [CitaDAO] Clase que implementa el patron DAO de la clase Cita
 * @since 24/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class CitaDAO {
    
    private AccesoBD fachada;
    
//=======================================================================================================
    /**
     * Constructor de la clase CitaDAO
     * @since CitaDAO.java
     */  
    
    public CitaDAO()
    {
        fachada = new AccesoBD();
    }
//=======================================================================================================    
    public int guardarCita(Cita cita)
    {
        int guardado = -1;
        String existePaciente, existeMedico, guardarCita, existeCita;
        ResultSet resultado = null;
        
        guardarCita = "INSERT INTO cita(codigo, fecha, hora, cedula, cedula_medico, costo) VALUES (NEXTVAL('numero_cita'), '"+cita.getFecha()+"', '"+cita.getHora()+"', '"+cita.getCedulaPaciente()+"', '"+cita.getCedulaMedico()+"', '25000');";
        existePaciente = "SELECT * FROM pacientes WHERE cedula = '"+cita.getCedulaPaciente()+"';";
        existeMedico = "SELECT * FROM medicos WHERE cedula = '"+cita.getCedulaMedico()+"';";
        existeCita = "SELECT * FROM cita WHERE cedula_medico = '"+cita.getCedulaMedico()+"' AND fecha ='"+cita.getFecha()+"' AND hora = '"+cita.getHora()+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia2 = conexion.createStatement();
            resultado = sentencia2.executeQuery(existeCita);
            if(resultado.next()){                
                System.out.println("La cita ya existe");                                   
            }
            else{
                    resultado = sentencia2.executeQuery(existeMedico);
                    if(resultado.next()){
                        resultado = sentencia2.executeQuery(existePaciente);
                        if(resultado.next()){
                            sentencia2.executeUpdate(guardarCita);
                            guardado = 0;
                            
                        }else{
                            guardado = 2;
                            System.out.println("El paciente no existe");
                        }                    
                    }else{
                        guardado = 1;
                        System.out.println("El medico no existe");
                    }
            }            
        }catch (SQLException ex) {            
            
            System.out.println("Excepción en guardar cita"); 
            System.out.println(ex.getMessage());  
        }
        return guardado;
    }
//=======================================================================================================    
    public boolean modificarCita(String cedulaMedico, String cedula, String fecha, String costo, String hora)
    {
        boolean modificada = false;
        String sql_update;        
        sql_update = "UPDATE cita SET fecha = '"+fecha+"', hora = '"+hora+"', cedula = '"+cedula+"', cedula_medico = '"+cedulaMedico+"', costo = '"+costo+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            if(existeCita(cedulaMedico, fecha, hora)){
                sentencia.executeUpdate(sql_update);
                modificada = true;
            }
            else{                
                System.out.println("No existe la cita");
            }
        }catch (SQLException ex) {
            System.out.println("Excepción en modificar cita");
        }
        return false;
    }
//=======================================================================================================    
    public boolean eliminarCita(String codigo)
    {
        String sql_insert;
        sql_insert = "DELETE FROM cita WHERE codigo = '"+codigo+"' ;";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            int z = sentencia.executeUpdate(sql_insert);
            return z == 1;
        }catch (SQLException ex) {
            System.out.println("Excepción en eliminar cita");
        }
        return false;
    }
//=======================================================================================================    
    public boolean existeCitaCodigo(String codigo)
    {        
        String existeCita = "SELECT * FROM cita WHERE codigo = '"+codigo+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia2 = conexion.createStatement();
            ResultSet resultado = sentencia2.executeQuery(existeCita);
            if(resultado.next()){
                return true;
            }
        } catch (Exception e) {
        } 
         return false;   
    }
//=======================================================================================================    
    public boolean existeCita(String cedulaMedico, String fecha, String hora)
    {
        boolean existe = false;
        String existeCita = "SELECT * FROM cita WHERE cedula_medico = '"+cedulaMedico+"' AND fecha ='"+fecha+"' AND hora = '"+hora+"';";
        ResultSet resultado = null;
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia2 = conexion.createStatement();
            resultado = sentencia2.executeQuery(existeCita);
            if(resultado.next()){                
                existe = true;                            
            }
        }
        catch(SQLException e){
            System.out.println("Excepción en existe cita");
        }
        return existe;
    }
//=======================================================================================================    
    public ResultSet consultarCita(String codigo)
    {
        String sql;
        ResultSet resultado = null;
        sql = "SELECT * FROM cita WHERE codigo = '"+codigo+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en consultar cita");
        }
        return resultado;
    }
//=======================================================================================================    
    
    /**
     * Muesta la agenda programada para un médico en un mes
     * @return 
     */
    public ResultSet mostrarAgendaProgramada(String cedulaMedico){
        String sql, between;
        ResultSet resultado = null;
        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH)+1;
        between = "";
        switch (mes) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
              between  = anio+"/"+mes+"/01' AND '"+anio+"/"+mes+"/31'";
              break;
            case 4: case 6: case 9: case 11:
              between  = anio+"/"+mes+"/01' AND '"+anio+"/"+mes+"/30'";
              break;
            case 2:			
              between  = anio+"/"+mes+"/01' AND '"+anio+"/"+mes+"/28'";
              break;			
        }
        sql = "SELECT * FROM cita WHERE cedula_medico = '"+cedulaMedico+"' AND fecha BETWEEN '"+between+";";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql); 
        }catch (SQLException ex) {
            System.out.println("Excepción en agenda programada");
        }
        return resultado;
    }
//=======================================================================================================    
    public int contarCitas(String cedulaMedico, String mese){
        String sql, between;
        ResultSet resultado = null;
        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        int mes = Integer.parseInt(mese);
        between = "";
        switch (mes) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
              between  = anio+"/"+mes+"/01' AND '"+anio+"/"+mes+"/31'";
              break;
            case 4: case 6: case 9: case 11:
              between  = anio+"/"+mes+"/01' AND '"+anio+"/"+mes+"/30'";
              break;
            case 2:			
              between  = anio+"/"+mes+"/01' AND '"+anio+"/"+mes+"/28'";
              break;			
        }
        sql = "SELECT COUNT(codigo) AS cuenta FROM cita WHERE cedula_medico = '"+cedulaMedico+"' AND fecha BETWEEN '"+between+";";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado.getInt("cuenta");
        }catch (SQLException ex) {
            System.out.println("Excepción en agenda programada");
        }
        return 0;
    }
//=======================================================================================================    
    public ResultSet mostrarCita()
    {
        String sql;
        ResultSet resultado = null;
        sql = "SELECT * FROM cita ;";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar citas");
        }
        return resultado;
    }
//=======================================================================================================    
    public ResultSet mostrarCitas(String cedula)
    {
        String sql;
        ResultSet resultado = null;
        sql = "SELECT codigo,fecha FROM cita WHERE cedula = '"+cedula+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar citas");
        }
        return resultado;
    }
//=======================================================================================================    
    public int descuento(String cedula){
        
        int descuento = 0;
        String capanias;
        ResultSet resultado = null;
        capanias = "SELECT campanias FROM pacientes WHERE cedula = '"+cedula+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(capanias);
            if(resultado.next()){
                int numero = resultado.getInt("campanias");
                    if(numero>2){
                        descuento = 25;
                    }
                    if(numero>5){
                        descuento = 50;
                    }
                    if(numero>10){
                        descuento = 75;
                    }
            }else{
                System.out.println("No hay resultset en descuento");
            }            
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar descuento");
            ex.printStackTrace();
        }
        return descuento;
    }
//=======================================================================================================    
    public boolean asignarDescuento(String codigo, String cedula, String descuento)
    {
        String sql;
        sql = "UPDATE cita SET descuento = '"+descuento+"' WHERE codigo = '"+codigo+"' and cedula = '"+cedula+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            int z = sentencia.executeUpdate(sql);
            return z == 1;
        }catch (SQLException ex) {
            System.out.println("Excepción en asignar descuento");
            ex.printStackTrace();
        }
        return false;
    }
//=======================================================================================================  
    
    public ArrayList <String> mostrarunaCita(String cedula)
    {
        ArrayList<String> paciente = new ArrayList<>();
        String sql,persona;
        sql = "SELECT * FROM cita WHERE codigo = '"+cedula+"';";
             
        try {            
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();            
            if(existeCitaCodigo(cedula)){
                ResultSet resultado = sentencia.executeQuery(sql);
                resultado.next();
                
                paciente.add(resultado.getString("cedula"));
                paciente.add(resultado.getString("cedula_medico"));
                paciente.add(resultado.getString("codigo"));
                
                String cedula1 = resultado.getString("cedula");
                String cedulaMedico = resultado.getString("cedula_medico");

               
                persona = "SELECT * FROM personas WHERE cedula = '"+cedula1+"';";
                resultado = sentencia.executeQuery(persona);
                resultado.next();
                paciente.add(resultado.getString("nombre"));
                
                
                persona = "SELECT * FROM personas WHERE cedula = '"+cedulaMedico+"';";
                resultado = sentencia.executeQuery(persona);
                resultado.next();
                paciente.add(resultado.getString("nombre"));
                
            }else{
                System.out.println("No hay resulset en mostrar citas(Dao)");
                paciente = null;
            }
        }catch (SQLException ex) {
            System.out.println("Ocurrió un problema al traer los datos de la cita");            
        }      
        return paciente;
    }

    public ResultSet consultarCitas() {
        String sql;
        ResultSet resultado = null;
        
        sql ="SELECT cita.codigo , cita.cedula, sub1.cedula_medico FROM cita NATURAL JOIN (SELECT cita.cedula, cita.cedula_medico FROM cita INNER JOIN medicos ON cita.cedula_medico = medicos.cedula) AS sub1;";
          
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
