package Modelo.DAOs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 * [HerramientasDAO] Clase que implementa el patron DAO de la clase Herramientas
 * @since 24/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class HerramientasDAO extends AccesoBD{

    private Process p;
    private ProcessBuilder pb; 
    private Runtime runTime;
    private AccesoBD fachada;
    
//=======================================================================================================
    /**
     * Constructor de la clase HerramientasDAO
     * @since HerramientasDAO.java
     */
    
    public HerramientasDAO()
    {
        fachada = new AccesoBD();
    }
//=======================================================================================================
    public boolean generarBackup(String source, String parcial) {
        try {            
            String versionPostgres = versionPostgreSQL()[1].substring(0, 2);
            String SQL, ruta;
            String so = System.getProperty("os.name");

            if( so.equals("Linux") ){
                ruta = "/opt/PostgreSQL/"+versionPostgres+"/bin/pg_dump";
                System.err.println("Linux :( -> " + ruta);

            }else

            if( so.contains("Windows")){
                ruta = "C:\\Program Files\\PostgreSQL\\"+versionPostgres+"\\bin\\pg_dump.exe";
                System.err.println("Windows :( -> " + ruta);
            }

            SQL = "pg_dump,-h,"+ip+",-p,"+puerto+",-U,"+ usuario+parcial+ ",-F,c,-b,-v,-f,"+ source+","+database;   

            String[] arregloProceso = SQL.split(",");
            runTime = Runtime.getRuntime();
            
            pb = new ProcessBuilder(arregloProceso);   
            pb.environment().put("PGHOST", ip);
            pb.environment().put("PGPORT", puerto);
            pb.environment().put("PGUSER", usuario);
            pb.environment().put("PGPASSWORD", clave);   
            p = pb.start();
            
            //Escritura del archivo de archivo de backup
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line = r.readLine();
            while (line != null) 
            {
                System.err.println(line);
                line = r.readLine();
            }
            r.close();
            p.waitFor();
            System.out.println(p.exitValue());
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
//=======================================================================================================
    /**
     * Metodo que permite consultar todas las tablas de la BD y guardarlas en un modelo
     * @return Retorna el modelo de la tabla para aplicar en la interfaz GUI
     */
    public DefaultListModel ConsultarTablas() {

        DefaultListModel tabla = new DefaultListModel();
        String sql = "SELECT  tablename FROM pg_tables WHERE schemaname ='public' ORDER BY tablename ASC";
        try {
                Connection conexion = fachada.getConnetion();
                Statement sentencia = conexion.createStatement();           
                ResultSet resultado = sentencia.executeQuery(sql);
            
            while (resultado.next()) {
                tabla.addElement(resultado.getString(1));          
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return tabla;
    }
//=======================================================================================================
    /**
    * Metodo Encargado obtener la versión de nuestro servidor PostgreSQL
    */
    private String[] versionPostgreSQL() {
        
        String sql = "SELECT  version();";
        try {
            
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();           
            ResultSet resultado = sentencia.executeQuery(sql);
            

            while (resultado.next()) {
                return resultado.getString(1).split(" ", 3);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
//=======================================================================================================   
    public boolean realizarRestore(String source)
    {
        try {

            String versionPostgres = versionPostgreSQL()[1].substring(0, 2);
            String ruta = "";
            String so = System.getProperty("os.name");
            String rutaServidor = "";
            
            if( so.contains("Linux") ){
                rutaServidor = "/opt/PostgreSQL/"+versionPostgres+"/bin/pg_restore";

            }
            else
                if( so.contains("Windows")){
                rutaServidor = "C:\\Program Files\\PostgreSQL\\"+versionPostgres+"\\bin\\pg_restore.exe";
                System.err.println("Windows :( -> " + ruta);
                }

            String[] arregloProceso = {rutaServidor, "-d", database, "-v", source};
            pb = new ProcessBuilder(arregloProceso);
            runTime = Runtime.getRuntime();
            borrarTablas();

            //Se asignan valores a las variables de PostgreSQL
            pb.environment().put("PGHOST", ip);
            pb.environment().put("PGPORT", puerto);
            pb.environment().put("PGUSER", usuario);
            pb.environment().put("PGPASSWORD", clave);
            pb.redirectErrorStream(true);    
            p = pb.start();
            return true;
            
        } catch (IOException e) {
            System.err.println(e);
            return false;
        }    
    }
//=======================================================================================================
    public ArrayList<String> retornarTablas()
    {
        ArrayList<String> tablas = new ArrayList<>();
        String SQL = "SELECT tablename FROM pg_tables WHERE schemaname = 'public'";       
            try {            
                Connection conexion = fachada.getConnetion();
                Statement sentencia = conexion.createStatement();            
                ResultSet resultado = sentencia.executeQuery(SQL);
                while(resultado.next())
                {
                    tablas.add(resultado.getString(1));
                }
            }
            catch (SQLException ex) {
                System.out.println(ex.getMessage());            
            }
        return tablas;        
    }
//=======================================================================================================    
    public boolean limpiarTablas()
    {
        try {            
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();            

            ArrayList<String> tablas = retornarTablas();
            for(int i=0; i<tablas.size();i++)
            {
                sentencia.executeUpdate("TRUNCATE "+ tablas.get(i)+" ;");
            }
            return true;
        }
        catch (SQLException ex) {
            System.out.println("Error a la hora de limpiar las tablas antes de RESTAURAR: "+ex.getMessage());
            return false;
        }
      
   }
//=======================================================================================================    
    public boolean borrarTablas()
    {        
        try {                           
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();            
            sentencia.executeUpdate("drop schema public cascade;");
            sentencia.executeUpdate("create schema public;");
            return true;          
            }
        
        catch (SQLException ex) {
            System.out.println("Error al momento de borrar las tablas: "+ex.getMessage());
            return false;            
        }        
    }
//=======================================================================================================    
}    
