package Modelo.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class PersonasDAO 
{   
    //Declaracion de Variable de Conexion
    private final AccesoBD fachada;
    
    
    public PersonasDAO()
    {
        fachada = new AccesoBD();
    }
    
    public String loginUsuarioDAO (String idCedula, String contrasenaCedula) 
    {       
        String cargo = "";     
        String sentenciaSQL = "SELECT * FROM personas WHERE cedula = '" + idCedula + "' and contrasena = '"
                + contrasenaCedula + "'";
        
        try 
        {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sentenciaSQL);
            
            if(resultado.next())
            {                
                cargo = resultado.getString("tipo_usuario");                
                if(cargo.equals("Administrador")){
                    return "Administrador";
                }
                if(cargo.equals("Medico")){
                    return "Medico";
                }
                if(cargo.equals("Enfermera")){
                    return "Enfermera";
                }
            }
            else
            {
                return "";
            }

        } 
        
        catch (Exception ex) {
            System.out.println("Error a la hora de loguearse en el sistema: "+ex.getMessage());
        }
        return "";
    }
    
}
