package Controlador;

import Modelo.DAOs.RegistroDAO;
import Modelo.DAOs.EmpleadoDAO;
import Modelo.DAOs.MedicoDAO;
import Modelo.DAOs.HabilidadesEnfermeraDAO;
import Modelo.DAOs.HistoriaClinicaDAO;
import Modelo.DAOs.MedicamentoDAO;
import Modelo.DAOs.EnfermeraDAO;
import Modelo.DAOs.AreaDAO;
import Modelo.DAOs.CitaDAO;
import Modelo.DAOs.PacienteDAO;
import Modelo.DAOs.CamaDAO;
import Modelo.DAOs.CausasDAO;
import Modelo.DAOs.CampaniaDAO;
import Modelo.Medico;
import Modelo.Cama;
import Modelo.CampaniaPrevencion;
import Modelo.Enfermera;
import Modelo.Empleado;
import Modelo.Area;
import Modelo.Cita;
import Modelo.DAOs.HerramientasDAO;
import Modelo.DAOs.PersonasDAO;
import Modelo.Paciente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import rojerusan.RSTableMetro;
import net.proteanit.sql.DbUtils;

/**
 * [Control] Clase que realiza el control de los patrones DAO
 * @since 24/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class Control {
        
    private final AreaDAO daoArea;
    private final CamaDAO daoCama;
    private final EmpleadoDAO daoEmpleado;
    private final MedicoDAO daoMedico;
    private final PacienteDAO daoPaciente;
    private final HistoriaClinicaDAO daoHistoria;
    private final HabilidadesEnfermeraDAO daoHabilidades;
    private final EnfermeraDAO daoEnfermera;
    private final CitaDAO daoCita;
    private final CampaniaDAO daoCampania;
    private final RegistroDAO daoRegistro;
    private final CausasDAO daoCausas;
    private final MedicamentoDAO daoMedicamentos;
    private final PersonasDAO daoUsuarios;
    private final HerramientasDAO daoHerramientas;
    
//=======================================================================================================
    /**
     * Constructor de la clase Control
     * @since Control.java
     */
   
    public Control() 
    {       
        daoArea = new AreaDAO();
        daoCama = new CamaDAO();
        daoEmpleado = new EmpleadoDAO();
        daoMedico = new MedicoDAO();
        daoPaciente = new PacienteDAO();
        daoHistoria = new HistoriaClinicaDAO();
        daoCita = new CitaDAO();
        daoCampania = new CampaniaDAO();
        daoHabilidades = new HabilidadesEnfermeraDAO();
        daoEnfermera = new EnfermeraDAO();
        daoRegistro = new RegistroDAO();
        daoCausas = new CausasDAO();
        daoMedicamentos = new MedicamentoDAO();
        daoUsuarios = new PersonasDAO();
        daoHerramientas = new HerramientasDAO();
    }
//=======================================================================================================
    /**
     * PERSONAS
     */
    
    public String loginUsuario(String usuario, String contrasena) 
    {
        return daoUsuarios.loginUsuarioDAO(usuario, contrasena);
    }
//=======================================================================================================    
    /**
     * ÁREAS
     */
    public boolean guardarArea(String codigo, String nombre, String descripcion){
        Area area = new Area(codigo, nombre, descripcion);
        if(daoArea.guardarArea(area)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean modificarArea(String codigo, String nombre, String descripcion){
        if(daoArea.modificarArea(codigo, nombre, descripcion)){
            return true;
        }else{
            return false;
        }
    }
    
    public ResultSet consultarArea(String codigo){
        ResultSet info = daoArea.consultarAreaa(codigo);
        return info;
    }
    
    public boolean eliminarArea(String codigo){
        if(daoArea.eliminarArea(codigo)){
            return true;
        }else{
            return false;
        }
    }
    
    public void mostrarAreasCombo(JComboBox <String> combo){
        combo.removeAllItems();
        try {
            ResultSet info = daoArea.mostrarAreas();
            while(info.next()){
                combo.addItem(info.getString("codigo") + ", "
                        + info.getString("nombre"));
            }
        }catch(SQLException ex){
            System.out.println("Excepción en mostrar areas(control). No hay áreas para mostrar.");
        }
    }
    
    public boolean existeArea(String codigo){
        if(daoArea.existeArea(codigo)){
            return true;
        }else{
            return false;
        }
    }
           
    public ArrayList<String> datosArea(String codigo){
        ArrayList <String>resultado = new ArrayList<>();
        try {
            ResultSet result = daoArea.datosArea(codigo);
            if(result.next()){
                resultado.add(result.getString("codigo")); //0
                resultado.add(result.getString("nombre")); //1
                resultado.add(result.getString("descripcion")); //2
            }else{
                System.out.println("No hay resulset de áreas(control)");
            }
        }catch (SQLException ex) {
            System.out.println("Ocurrió un problema al traer los datos del área(control)");
            
        }
        return resultado;
    }
    
    public ArrayList mostrarArea(String cedula){
        return daoArea.mostrarArea(cedula); 
    }
    
    
    public void consultarAreas(RSTableMetro tabla) {
        ResultSet rs = null;
        rs = daoArea.consultarAreas();       
        tabla.setModel(DbUtils.resultSetToTableModel(rs));
    }
//=======================================================================================================    
    /**
     * CAMAS
     */
    public boolean guardarCama(String codigo, String codigoArea, String descripcion){
        Cama cama = new Cama(codigo, codigoArea,descripcion,true);
        if(daoCama.guardarCama(cama)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean existeCama(String codigo){
        return daoCama.existeCama(codigo);
    }
    
    public boolean modificarCama(String codigo, String codigoArea, String descripcion, boolean estado){
        if(daoCama.modificarCama(codigo, codigoArea, descripcion, estado)){
            return true;
        }else{
            return false;
        }
    }
    
    public ResultSet consultarCama(String codigo){
        ResultSet info = daoCama.consultarCama(codigo);
        return info;
    }
    
    public boolean eliminarCama(String codigo){
        if(daoCama.eliminarCama(codigo)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean camasPacientes(String cedula, String codigo, String fecha){
        if(daoCama.camasPacientes(cedula, codigo, fecha)){
            return true;
        }else{
            return false;
        }        
    }
    
    public void mostrarCamasCombo(JComboBox <String>combo){
        combo.removeAllItems();
        try {
            ResultSet info = daoCama.mostrarCamas();
            while(info.next()){
                combo.addItem(info.getString("codigo") + ", "
                        + info.getString("descripcion"));
            }
        }catch(SQLException ex){
            System.out.println("Excepción en mostrar camas(control). No hay camas para mostrar.");
        }
    }
    
    public void mostrarCamaCombo(JComboBox <String> combo){
        combo.removeAllItems();
        try {
            ResultSet info = daoCama.mostrarCama();
            while(info.next()){
                combo.addItem(info.getString("codigo") + ", "
                        + info.getString("nombre"));
            }
        }catch(SQLException ex){
            System.out.println("Excepción en mostrar camas(control). No hay camas para mostrar.");
        }
    }
    
    public ArrayList mostrarCama(String cedula){
        return daoCama.mostrarCama(cedula); 
    }
    
    public void consultarCamas(RSTableMetro tabla) {
        ResultSet rs = null;
        rs = daoCama.consultarCamas();       
        tabla.setModel(DbUtils.resultSetToTableModel(rs));
    }
//=======================================================================================================    
    /**
     * EMPLEADOS
     */
    
    public boolean guardarEmpleado(String correo, String cargo, double salario, String cedula, String nombre, String direccion, String telefono, String codigoArea,String contrasena,String cedulaJefe){
        boolean guardado = false;
        Empleado empleado = new Empleado(correo, cargo, salario, cedula, nombre,direccion,telefono,codigoArea,contrasena,cedulaJefe);                   
        if(daoEmpleado.guardarEmpleado(empleado)){
            guardado = true;
        }        
        return guardado;        
    }
    
    public boolean modificarEmpleado(String correo, String cargo, double salario, String cedula, String nombre, String direccion, String telefono, String codigoArea, String cedulaJefe){
        boolean modificado = false;
        if(daoEmpleado.modificarEmpleado(correo, cargo, salario, cedula, nombre, direccion, telefono, codigoArea, cedulaJefe)){
            modificado = true;
        }
        return modificado;
    }
    
    public boolean existeEmpleadoPersona(String cedula){
        return daoEmpleado.existeEmpleadoPersona(cedula);
    }
    
    public ResultSet consultarEmpleado(String cedula){
        ResultSet info = daoEmpleado.consultarEmpleado(cedula);
        return info;
    }
    
    public ResultSet listarEmpleados(String codigo){
        ResultSet info = daoEmpleado.listarEmpleadosArea(codigo);
        return info;
    }
    
    public ArrayList mostrarEmpleado(String cedula){
        return daoEmpleado.mostrarEmpleado(cedula); 
    }
    
    
    public void consultarEmpleados(RSTableMetro tabla) {
        ResultSet rs = null;
        rs = daoEmpleado.consultarEmpleados();       
        tabla.setModel(DbUtils.resultSetToTableModel(rs));
    }
//=======================================================================================================    
    /**
     * MEDICOS 
     */
    
    public boolean guardarMedico(String especialidad, String numeroLicencia, String universidad, String cedula){
        boolean guardado = false;        
        Medico medico = new Medico(especialidad, numeroLicencia, universidad, cedula);        
        if(daoMedico.guardarMedico(medico)){
            guardado = true;
        }        
        return guardado;        
    }
    
    public boolean modificarMedico(String especialidad, String numeroLicencia, String universidad, String cedula){
        boolean modificado = false;
        if(daoMedico.modificarMedico(especialidad, numeroLicencia, universidad, cedula)){
            modificado = true;
        }
        return modificado;
    }
    
    public ResultSet consultarMedicos(String cedula){
        ResultSet info = daoMedico.consultarMedico(cedula);
        return info;
    }
    
    public void mostrarMedicosCombo(JComboBox <String> combo){
        combo.removeAllItems();
        try {
            ResultSet info = daoMedico.mostrarMedicos();
            while(info.next()){
                combo.addItem(info.getString("cedula") + ", "
                        + info.getString("nombre"));
            }
        }catch(SQLException ex){
            System.out.println("Excepción en mostrar pacientes(control). No hay pacientes para mostrar.");
        }
    }
//=======================================================================================================    
    /**
     * ENFERMERAS 
     */
    
    public boolean guardarEnfermeras(String exp, String cedula){
        Enfermera enfermera = new Enfermera(exp, cedula);
        if(daoEnfermera.guardarEnfermera(enfermera)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean modificarEnfermera(String anosExperiencia, String cedula){
        
        if(daoEnfermera.modificarEnfermera(cedula, anosExperiencia)){
            return true;
        }else{
            return false;
        }
    }
    
    public ResultSet consultarEnfermeras(String cedula){
        ResultSet info = daoEnfermera.consultarEnfermera(cedula);
        return info;
    }
    
    public void mostrarEnfermerasCombo(JComboBox <String>combo){
        combo.removeAllItems();
        try {
            ResultSet info = daoEnfermera.mostrarEnfermeras();
            while(info.next()){
                combo.addItem(info.getString("cedula"));
            }
        }catch(SQLException ex){
            System.out.println("Excepción en mostrar enfermeras(control). No hay áreas para mostrar.");
        }
        catch(NullPointerException e)
        {
            System.out.println("No hay enfermeras en la bd");
        }
    }
//=======================================================================================================    
    
    /**
     * PACIENTES 
     */
    
    public boolean guardarPaciente(String nombre, String cedula, String direccion, String telefono, String social, String fecha, String actividad, String contrasena){      
        Paciente paciente = new Paciente(social,fecha,actividad, cedula, nombre, direccion, telefono, contrasena);
        if(daoPaciente.guardarPaciente(paciente)){
            return true;
        }else{
            return false;
        }                  
    }
    
    public boolean existePaciente(String cedula){
        return daoPaciente.existePaciente(cedula);
    }
    
    public boolean modificarPaciente(String nombre, String cedula, String direccion, String telefono, String social, String fecha, String actividad){
        if(daoPaciente.modificarPaciente(cedula, nombre, direccion, telefono, social, fecha, actividad)){
            return true;
        }else{
            return false;
        }
    }
    
    public ResultSet consultarPacientes(String cedula){
        ResultSet info = daoPaciente.consultarPaciente(cedula);
        return info;
    }
    
    public void mostrarPacientesCombo(JComboBox <String> combo){
        combo.removeAllItems();
        try {
            ResultSet info = daoPaciente.mostrarPacientes();
            while(info.next()){
                combo.addItem(info.getString("cedula") + ", "
                        + info.getString("nombre"));
            }
        }catch(SQLException ex){
            System.out.println("Excepción en mostrar pacientes(control). No hay pacientes para mostrar.");
        }
    }
    
    public ArrayList mostrarPaciente(String cedula){
        return daoPaciente.mostrarPaciente(cedula); 
    }
    
    public void consultarPacientes ( RSTableMetro tabla) throws SQLException{
        ResultSet rs = null;
        rs = daoPaciente.consultarPacientes();       
        tabla.setModel(DbUtils.resultSetToTableModel(rs));

   }


//=======================================================================================================    
    /**
     * CITAS
     */
    public int guardarCitas(String fecha, String hora, String cedulaPaciente, String cedulaMedico){
        
        Cita cita = new Cita(fecha, hora, cedulaPaciente, cedulaMedico);
        return daoCita.guardarCita(cita);
    }
    
    public ResultSet consultarCitas(String codigo){
        ResultSet info = daoCita.consultarCita(codigo);
        return info;
    }
    
    public boolean eliminarCita(String codigo){
        if(daoCita.eliminarCita(codigo)){
            return true;
        }else{
            return false;
        }
    }
    
    public int contarCitas(String cedula, String mes){
        return daoCita.contarCitas(cedula, mes);
    }
    
    public boolean existeCitaCodigo(String codigo){
        return daoCita.existeCitaCodigo(codigo);
    }
    
    public void mostrarCitaCombo(JComboBox <String>combo){
        combo.removeAllItems();
        try {
            ResultSet info = daoCita.mostrarCita();
            while(info.next()){
                combo.addItem(info.getString("codigo") + ", "
                        + info.getString("cedula"));
            }
        }catch(SQLException ex){
            System.out.println("Excepción en mostrar citas(control). No hay citas para mostrar.");
        }
    }
    
    public void mostrarCitasCombo(JComboBox <String>combo, String cedula){
        combo.removeAllItems();
        try {
            ResultSet info = daoCita.mostrarCitas(cedula);
            while(info.next()){
                combo.addItem(info.getString("codigo") + ", "
                        + info.getString("fecha"));
            }
        }catch(SQLException ex){
            System.out.println("Excepción en mostrar citas(control). No hay citas para mostrar.");
        }
    }
    
    public boolean asignarDescuento(String codigo, String cedula, String descuento){
        if(daoCita.asignarDescuento(codigo, cedula, descuento)){
            return true;
        }else{
            return false;
        }
    }
    
    public int descuentoCitas(String cedula){
        return daoCita.descuento(cedula);
    }
    
    public String mostrarAgendaMensual(String cedulaMedico){
        String agenda = "";
        try{
            ResultSet info = daoCita.mostrarAgendaProgramada(cedulaMedico);            
            while(info.next()){
                agenda += "Codigo Cita: "+info.getString("codigo")+" "+ info.getString("fecha")+" "+ info.getString("hora")+" Paciente: "+ info.getString("cedula") + "\n";
            }
            if(agenda.equals("")){
                agenda = "No hay citas programas este mes";
            }
        }
        catch(SQLException ex){
            System.out.println("No hay resultset(control)");
        }
        return agenda;
    }
    
    public ArrayList mostrarunaCita(String cedula){
        return daoCita.mostrarunaCita(cedula); 
    }
    
        
    public void consultarCitas(RSTableMetro tabla) {
        ResultSet rs = null;
        rs = daoCita.consultarCitas();       
        tabla.setModel(DbUtils.resultSetToTableModel(rs));
    }


//=======================================================================================================    
    /**
     * HABILIDADES
     */
    public boolean guardarHabilidades(String codigo, String nombre){
        if(daoHabilidades.guardarHabilidades(codigo, nombre)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean modificarHabilidades(String codigo, String nombre){
        if(daoHabilidades.modificarHabiliades(codigo, nombre)){
            return true;
        }else{
            return false;
        }
    }
    
    public ResultSet consultarHabilidades(String codigo){
        ResultSet info = daoHabilidades.consultarHabilidad(codigo);
        return info;
    }
    
    
    public void mostrarHabilidadesCombo(JComboBox <String>combo){
        combo.removeAllItems();
        try {
            ResultSet info = daoHabilidades.mostrarHabilidades();
            while(info.next()){
                combo.addItem(info.getString("codigo") + ", "
                        + info.getString("nombre"));
            }
        }catch(SQLException ex){
            System.out.println("Excepción en mostrar areas(control). No hay áreas para mostrar.");
        }
        catch(NullPointerException e)
        {
            System.out.println("No hay areas en la bd");
        }
             
    }
    
    public ArrayList mostrarHabilidad(String cedula){
        return daoHabilidades.mostrarHabilidad(cedula); 
    }
    
    public boolean habilidadesEnfermeras(String cedula, String codigo){
        if(daoHabilidades.habilidadesEnfermeras(cedula, codigo)){
            return true;
        }else{
            return false;
        }        
    }
    
    public boolean existeHabilidad(String codigo){
        return daoHabilidades.existeHabilidad(codigo);
    }
    
    public void consultarHabilidades(RSTableMetro tabla) {
        ResultSet rs = null;
        rs = daoHabilidades.consultarHabilidades();       
        tabla.setModel(DbUtils.resultSetToTableModel(rs));
    }
//=======================================================================================================    
    
    /**
     * CAMPAÑAS
     */
    
    public boolean guardarCampania(String codigo, String nombre, String fechaRealizacion, String objetivo, String cedula){
        boolean guardado = false;
        CampaniaPrevencion campania = new CampaniaPrevencion(codigo, nombre, fechaRealizacion, objetivo);
        if(daoCampania.guardarCampania(campania, cedula)){
            guardado = true;
        }
        return guardado;
    }
    
    public boolean modificarCampania(String codigo, String nombre, String fechaRealizacion, String objetivo, String cedula){
        boolean guardado = false;
        if(daoCampania.modificarCampania(codigo, nombre, fechaRealizacion,objetivo,cedula )){
            guardado = true;
        }
        return guardado;
    }
    
    public ResultSet consultarCampa(String codigo){
        ResultSet info = daoCampania.consultarCampa(codigo);
        return info;
    }
    
    public boolean eliminarCampa(String codigo){
        if(daoCampania.eliminarCampa(codigo)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean existeCampa(String codigo){
        return daoCampania.existeCampa(codigo);
    }
    
    public void mostrarCampaCombo(JComboBox <String> combo){
        combo.removeAllItems();
        try {
            ResultSet info = daoCampania.mostrarCampa();
            while(info.next()){
                combo.addItem(info.getString("codigo") + ", "
                        + info.getString("nombre"));
            }
        }catch(SQLException ex){
            System.out.println("Excepción en mostrar camas(control). No hay camas para mostrar.");
        }
    }
    
    public boolean registrarPacienteCampania(String codigo, String cedula){
        boolean registrado = false;
        if(daoCampania.registrarPacienteCampania(codigo, cedula)){
            registrado = true;
        }
        return registrado;
    }
    
    public ArrayList mostrarCampa(String cedula){
        return daoCampania.mostrarCampa(cedula); 
    }
    
    
    public void consultarCampanas(RSTableMetro tabla) {
        ResultSet rs = null;
        rs = daoCampania.consultarCampanas();       
        tabla.setModel(DbUtils.resultSetToTableModel(rs));
    }
//=======================================================================================================    
    
    /**
     * REGISTROS
     */
    
    public boolean guardarRegistro(String cita, String cedula){      
        if(daoRegistro.guardarRegistro(cita, cedula)){
            return true;
        }else{
            return false;  
        }
    }
    
    public void mostrarRegistrosCombo(JComboBox <String> combo){
        combo.removeAllItems();
        try {
            ResultSet info = daoRegistro.mostrarRegistros();
            while(info.next()){
                combo.addItem(info.getString("codigo") + ", "
                        + info.getString("codigo_historia"));
            }
        }catch(SQLException ex){
            System.out.println("Excepción en mostrar registros(control). No hay registros para mostrar.");
        }
    }
    
//=======================================================================================================
    
    /**
     * CAUSAS
     */
    
    public boolean guardarCausas(String codigo, String nombre, String descripcion){
        if(daoCausas.guardarCausa(codigo, nombre, descripcion)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean modificarCausas(String codigo, String nombre, String descripcion){
        if(daoCausas.modificarCausas(codigo, nombre, descripcion)){
            return true;
        }else{
            return false;
        }
    }
    
    public ResultSet consultarCausas(String codigo){
        ResultSet info = daoCausas.consultarCausas(codigo);
        return info;
    }
    
    public boolean eliminarCusas(String codigo){
        if(daoCausas.eliminarCausa(codigo)){
            return true;
        }else{
            return false;
        }
    }
    
    public void mostrarCausasCombo(JComboBox <String> combo){
        combo.removeAllItems();
        try {
            ResultSet info = daoCausas.mostrarCausas();
            while(info.next()){
                combo.addItem(info.getString("codigo") + ", "
                        + info.getString("nombre"));
            }
        }catch(SQLException ex){
            System.out.println("Excepción en mostrar causas(control). No hay causas para mostrar.");
        }
    }
    
    public boolean causasRegistro(String registro, String causa){
        if(daoCausas.causasRegistros(registro, causa)){
            return true;
        }else{
            return false;
        }        
    }
    
    public boolean existeCausa(String codigo) {
        return daoCausas.existeCausa(codigo);
    }
    
    public ArrayList mostrarunaCausa(String cedula){
        return daoCausas.mostrarunaCausa(cedula); 
    }
    
    public void consultarCausas(RSTableMetro tabla) {
        ResultSet rs = null;
        rs = daoCausas.consultarCausas();       
        tabla.setModel(DbUtils.resultSetToTableModel(rs));
    }

//=======================================================================================================    
    /**
     * MEDICAMENTOS
     */
    
    public boolean guardarMedicamentos(String codigo, String nombre, String descripcion, String costo){
        if(daoMedicamentos.guardarMedicamento(codigo, nombre, descripcion, costo)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean modificarMedicamentos(String codigo, String nombre, String descripcion, String costo){
        if(daoMedicamentos.modificarmedicamentos(codigo, nombre, descripcion, costo)){
            return true;
        }else{
            return false;
        }
    }
    
    public ResultSet consultarMedicamentos(String codigo){
        ResultSet info = daoMedicamentos.consultarMedicamento(codigo);
        return info;
    }
    
    public boolean eliminarMedicamentos(String codigo){
        if(daoMedicamentos.eliminarMedicamento(codigo)){
            return true;
        }else{
            return false;
        }
    }
    
    public void mostrarMedicamentosCombo(JComboBox <String>combo){
        combo.removeAllItems();
        try {
            ResultSet info = daoMedicamentos.mostrarmedicamentos();
            while(info.next()){
                combo.addItem(info.getString("codigo") + ", "
                        + info.getString("nombre") + ", "
                        + info.getString("costo"));
            }
        }catch(SQLException ex){
            System.out.println("Excepción en mostrar causas(control). No hay causas para mostrar.");
        }
    }
    
    public boolean medicamentosRegistro(String registro, String medicamento){
        if(daoMedicamentos.medicamentosRegistro(registro, medicamento)){
            return true;
        }else{
            return false;
        }        
    }
    
    public boolean existeMedicina(String codigo) {
        return daoMedicamentos.existeMedicina(codigo);
    }
    
    public ArrayList mostrarMedicamento(String cedula){
        return daoMedicamentos.mostrarMedicina(cedula); 
    }
    
    public void consultarMedicamentos(RSTableMetro tabla) {
        ResultSet rs = null;
        rs = daoMedicamentos.consultarMedicamentos();       
        tabla.setModel(DbUtils.resultSetToTableModel(rs));
    }
//=======================================================================================================

    /**
    * HERRAMIENTAS
    */
   
    public boolean limpiarTablas(){
        return daoHerramientas.limpiarTablas();
    }
    
   public DefaultListModel ConsultarTablas() {
       return daoHerramientas.ConsultarTablas();
   }
   
   public boolean generarBackup(String source, String parcial) {
       return daoHerramientas.generarBackup(source, parcial);
   }
   
   public boolean realizarRestore(String source)
   {
       return daoHerramientas.realizarRestore(source);
   }
    
}
