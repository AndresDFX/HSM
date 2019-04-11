package Modelo.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * [HistoriaClinicaDAO] Clase que implementa el patron DAO de la clase HistoriaClinica
 * @since 24/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class HistoriaClinicaDAO {
    
    private AccesoBD fachada;
//=======================================================================================================    
    /**
     * Constructor de la clase HistoriaClinicaDAO
     * @since HistoriaClinicaDAO.java
     */

    public HistoriaClinicaDAO() 
    {
        fachada = new AccesoBD();
    }    
//=======================================================================================================   
    //0- nombrePaciente 1- numero 2- fecha
    public String[] consultarDatosHistoria(String cedula){
        String[] historia = new String[3];
        String sqlPaciente = "SELECT * FROM (personas NATURAL JOIN pacientes) JOIN historias ON pacientes.cedula = historias.cedula WHERE pacientes.cedula = '"+cedula+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();           
            ResultSet resultado = sentencia.executeQuery(sqlPaciente);
            resultado.next();
            historia[0] = resultado.getString("nombre");
            historia[1] = resultado.getString("codigo");
            historia[2] = resultado.getString("fecha_apertura");            
            
        } catch (Exception e) {
        }
        return historia;
    }
//=======================================================================================================    
    public ResultSet consultarRegistrosHistoria(String cedula){
        ResultSet resultado = null;
        String sql = "SELECT * FROM (((historias JOIN registro ON historias.codigo = registro.codigo_historia) JOIN causas_registro ON causas_registro.codigo_registro = registro.codigo) JOIN causas ON causas.codigo = causas_registro.codigo_causa) JOIN personas ON personas.cedula = historias.cedula WHERE historias.cedula = '"+cedula+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();           
            resultado = sentencia.executeQuery(sql);           
            
        } catch (Exception e) {
        }
        return resultado;
    }
//=======================================================================================================    
    public boolean guardarHistoria(String cedula){
               
        boolean guardado = false;
        String insertarHistoria, existe, existePaciente;
        ResultSet resultado = null;
        
        insertarHistoria = "INSERT INTO historias VALUES (NEXTVAL('numero_historia'), CURRENT_DATE, '"+cedula+"');";
        existe = "SELECT * FROM historias WHERE cedula = '"+cedula+"';";
        existePaciente = "SELECT * FROM pacientes WHERE cedula = '"+cedula+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();           
            resultado = sentencia.executeQuery(existePaciente);
            //Pregunta si el paciente existe
            if(resultado.next()){
                //Pregunta si la historia existe
                resultado = sentencia.executeQuery(existe);
                if(resultado.next()){
                    System.out.println("Ya existe una historia clinica con esta cedula");
                    guardado = false;
                }            
                else{
                    sentencia.executeUpdate(insertarHistoria);
                    System.out.println("Historia Clínica Generada");
                    guardado = true;
                }
            }
            else{
                System.out.println("No existe el paciente con esa cedula");
            }
        }    
        catch (SQLException ex) {
            System.out.println("Excepción en guardar historia clínica");          
        }
        return guardado;
    }
//=======================================================================================================    
}
