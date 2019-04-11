

package Modelo.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author sebas
 */
public class RegistroDAO {
    private AccesoBD fachada;

    public RegistroDAO() {
        fachada = new AccesoBD();
    }
    
    public boolean guardarRegistro(String cita, String cedula){
        String sql_insert, sql_llave;
        ResultSet resultado = null;
        
        sql_insert = "INSERT INTO registro VALUES(NEXTVAL('numero_registro'), '"+cita+"', (SELECT historias.codigo FROM historias NATURAL JOIN "
                + "pacientes WHERE historias.cedula = '"+cedula+"'));";
        
        sql_llave = "SELECT * FROM registro WHERE codigo_cita = '"+cita+"' and codigo_historia = ("
                + "SELECT historias.codigo FROM historias INNER JOIN pacientes ON historias.cedula = pacientes.cedula WHERE historias.cedula = '"
                +cedula+"');";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            Statement sentencia2 = conexion.createStatement();
            resultado = sentencia2.executeQuery(sql_llave);
            if(resultado.next()){
                System.out.println("Ya existe este registro");
                return false;
            }else{
                int z = sentencia.executeUpdate(sql_insert);
                return z == 1;
            }
        }catch (SQLException ex) {
            System.out.println("Excepción en guardar registro");
        }
        return false;
    }
    
    public ResultSet mostrarRegistros(){
        String sql;
        ResultSet resultado = null;
        sql = "SELECT codigo, codigo_historia FROM registro";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar registro");
        }
        return resultado;
    }
}
