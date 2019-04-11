package Modelo.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * [AccesoBD] Clase principal que administra la conexion a la Base de Datos
 * @since 24/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class AccesoBD {
    
    //Declaracion de variables de conexion
    private final String driver = "org.postgresql.Driver";
    protected final String usuario = "postgres";
    protected final String clave = "admin";
    protected final String ip = "localhost";
    protected final String puerto = "5432";
    protected final String database = "HSM DS1";
    protected final String url = "jdbc:postgresql://"; 
    private Connection conexion = null;
    
//=======================================================================================================    
    /**
     * Metodo que permite realizar la conexion a la BD Postgress
     * @return Cambiar el valor del objeto de la conexion si se realiza correctamente
     */   
    public Connection conectarBD()
    {
            try {
            Class.forName(driver);
                System.out.println( "Driver Cargado" );
            } 
            catch( ClassNotFoundException e ) {
                System.out.println( "No se pudo cargar el driver: "+e.getMessage());
            }

            try{               
                conexion = DriverManager.getConnection(url+ ip  +":" + puerto + "/" + database , usuario, clave);
                System.out.println( "Conexion Abierta" );
                return conexion;
             } 
            
            catch( SQLException e ) 
            {
                System.out.println( "No se pudo abrir la bd: "+e.getMessage() );
                return null;
            }

    }
//=======================================================================================================    
    /**
     * Metodo que permite obtener la conexion de la BD en caso de no estar conectada se llama recursivamente
     * el metodo para conectarla
     * @return Devuelve el objeto de conexion a a base de datos
     */

    public Connection getConnetion()
    {
        if (conexion == null) 
        {
            return this.conectarBD();
        }
        
        else
        {
            return conexion;      
        }            
    }
//=======================================================================================================    
}
