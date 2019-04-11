package Vista;

import Controladora.*;
import Modelo.Validaciones;
import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultEditorKit;
import java.awt.Cursor;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.filechooser.FileNameExtensionFilter;
import Utilidades.Idioma;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JLabel;

/**
 * [VistaAdministrador] Clase de interfaz GUI del usuario Administrador
 * @since 07/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class VistaAdministrador extends javax.swing.JFrame {
    
    //Declaracion de Variables Auxiliares
    ActionMap acciones ;
    Action accionCopiar;
    Action accionPegar;
    Action accionCortar;
    Action accionDeshacer;
    Validaciones validacionTotal; 
    Control controladora;
    Idioma idioma;
    
    private Image iconoVentana;
    private String extension;
    private String rutaRespaldo;    
    private final byte RESPALDO;
    private final byte RESTAURACION;
    private final File carpetaAutomaticos;
    private final File carpetaManuales;
    private static VistaAdministrador INSTANCE = null;
      
//=======================================================================================================    
    /**
     * Constructor de Clase VistaAdministrador
     * @since VistaAdministrador.java
     */
    
    public VistaAdministrador() {
        
        initComponents();
        
        //Inicializacion de Variables
        acciones = jTextFieldBuscar.getActionMap();
        validacionTotal = new Validaciones();
        controladora = new Control();
        carpetaAutomaticos = new File("Backups\\Automaticos");
        carpetaManuales = new File("Backups\\Manuales");         
        iconoVentana = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Logos/iconoHospital.png"));
        RESPALDO = 1;
        RESTAURACION = 2;
        
        //Implementacion de Ajustes
        ajustesGraficos();
        ajustesFuncionales();                       
    }
    
 //=======================================================================================================
    /**
    * Metodo que permite crear una nueva instacia de la clase solo si ya no ha sido creada otra
    */
    
    private synchronized static void crearInstancia(){
        if(INSTANCE == null){
            INSTANCE = new VistaAdministrador();         
        }
    }
    
//=======================================================================================================   
    /**
    * Metodo que permite obtener la instancia de la clase que ha sido creada
    * @return El objeto de la instancia de la clase
    */
    
    public static VistaAdministrador getInstancia(){
        crearInstancia();
       return INSTANCE;
    }
//=======================================================================================================
    /**
     * Metodo que realiza ajustes visuales a la interfaz
     */
    
    public final void ajustesGraficos()
    {        
        this.setLocationRelativeTo(null);
        this.setIconImage(iconoVentana); 
        rSTablaPacientes.setDefaultEditor(Object.class, null);
        rSTablaEmpleados.setDefaultEditor(Object.class, null);
        rSTablaHabilidades.setDefaultEditor(Object.class, null);
        rSTablaArea.setDefaultEditor(Object.class, null);
        rSTablaCama.setDefaultEditor(Object.class, null);
        rSTablaCampana.setDefaultEditor(Object.class, null);
        rSTablaMedicamentos.setDefaultEditor(Object.class, null); 
        rSPopuMenu2.add(jPanelMetro);
    }
//=======================================================================================================    
    /**
     * Metodo que permite cambiar el Idioma del aplicativo de manera modular, previamente creando un fichero
     * de traduccion
     * @param nombreIdioma Variable que almacena el nombre del idioma al que se quiere traducir la aplicacion 
     */
    
    public void cambiarIdioma(String nombreIdioma){
        
        idioma = new Idioma(nombreIdioma);        
              
        //Modulo Menu
        jLabelTitulo.setText(idioma.getProperty("titulo"));
        jLabelRol1.setText(idioma.getProperty("titulo"));
        jLabelRol2.setText(idioma.getProperty("bienvenido"));

        jLabelCamas.setText(idioma.getProperty("cama"));
        jLabelCampanas.setText(idioma.getProperty("campana"));
        jLabelUsuarios.setText(idioma.getProperty("usuarios"));

        jLabelMedicinas.setText(idioma.getProperty("medicina"));
        
        //Modulo Reportes
        jLabelTituloReport.setText(idioma.getProperty("titulo_reportes"));
        jLabelListWorker.setText(idioma.getProperty("lista_empleados"));
        jLabeHistoryClinic.setText(idioma.getProperty("historia_clinica"));
        jLabelNumberQuote.setText(idioma.getProperty("num_citas"));
        jLabelMonthlyAgenda.setText(idioma.getProperty("agenda_mensual"));
        jLabelHome8.setText(idioma.getProperty("menu"));
        
        //Modulo Camas
        jLabelTituloCama.setText(idioma.getProperty("titulo_camas"));
        jLabelBed.setText(idioma.getProperty("camas"));
        jLabelAgregarCama.setText(idioma.getProperty("agregar_cama"));
        jLabelModificarCama.setText(idioma.getProperty("modificar_cama"));
        jLabelAsignarCama.setText(idioma.getProperty("asignar_cama"));
        jLabelBuscar5.setText(idioma.getProperty("seleccionar"));
        rSButtonMetroFiltrar5.setText(idioma.getProperty("buscar"));
        jLabelDescripcion2.setText(idioma.getProperty("descripcion"));
        jLabelCodigo4.setText(idioma.getProperty("codigo"));
        jCheckBoxDisponible.setText(idioma.getProperty("disponible"));
        jLabelFechaAsignacion.setText(idioma.getProperty("fecha_asignacion"));
        jLabelListaPacientes1.setText(idioma.getProperty("lista_pacientes"));
        jLabelListaCamas.setText(idioma.getProperty("lista_camas"));
        rSButtonAsignar2.setText(idioma.getProperty("asignar"));
        rSButtonMetroMostrar5.setText(idioma.getProperty("limpiar"));
        rSButtonMetroLimpiar7.setText(idioma.getProperty("limpiar"));
        rSButtonMetroLimpiar8.setText(idioma.getProperty("limpiar"));
        rSButtonMetroGuardar5.setText(idioma.getProperty("guardar"));
        jLabelHome3.setText(idioma.getProperty("menu"));
        
        //Modulo Campañas
        jLabelTituloCampaing.setText(idioma.getProperty("titulo_campanas"));
        jLabelCampaing.setText(idioma.getProperty("campanas"));
        jLabelAgregarCampana.setText(idioma.getProperty("agregar_campana"));
        jLabelModificarCampana.setText(idioma.getProperty("modificar_campana"));
        jLabelAsignarCampana.setText(idioma.getProperty("asignar_campana"));
        jLabelBuscar4.setText(idioma.getProperty("seleccionar"));
        rSButtonMetroFiltrar4.setText(idioma.getProperty("buscar"));
        jLabelNombre3.setText(idioma.getProperty("nombre"));
        jLabelCodigo3.setText(idioma.getProperty("codigo"));
        jLabelFecha.setText(idioma.getProperty("fecha"));
        jLabelDoctorEncargado.setText(idioma.getProperty("doctor_encargado"));
        jLabelObjetivo.setText(idioma.getProperty("objetivo"));
        rSComboMetroObjetivo.removeAllItems();
        rSComboMetroObjetivo.addItem(idioma.getProperty("promocion"));
        rSComboMetroObjetivo.addItem(idioma.getProperty("prevencion"));
        rSComboMetroObjetivo.addItem(idioma.getProperty("intervencion"));
        rSButtonMetroLimpiar5.setText(idioma.getProperty("limpiar"));
        rSButtonMetroLimpiar6.setText(idioma.getProperty("limpiar"));
        rSButtonMetroMostrar4.setText(idioma.getProperty("limpiar"));
        rSButtonMetroGuardar3.setText(idioma.getProperty("guardar"));
        rSButtonAsignar1.setText(idioma.getProperty("asignar"));
        jLabelListaCampanas.setText(idioma.getProperty("lista_campanas"));
        jLabelListaPacientes.setText(idioma.getProperty("lista_pacientes"));
        jLabelHome2.setText(idioma.getProperty("menu"));
        
        //Modulo Usuarios
        jLabelTituloUsuario.setText(idioma.getProperty("titulo_usuarios"));
        jLabelPaciente.setText(idioma.getProperty("pacientes"));
        jLabelEmpleado.setText(idioma.getProperty("empleados"));
        jLabelAgregarPaciente.setText(idioma.getProperty("agregar_paciente"));
        jLabelModificarPaciente.setText(idioma.getProperty("modificar_paciente"));
        jLabelAgregarEmpleado.setText(idioma.getProperty("agregar_empleado"));
        jLabelModificarEmpleado.setText(idioma.getProperty("modificar_empleado"));
        jLabelAgregarHabilidad.setText(idioma.getProperty("agregar_habilidad"));
        jLabelModificarHabilidad.setText(idioma.getProperty("modificar_habilidad"));
        jLabelAsignarHabilidad.setText(idioma.getProperty("asignar_habilidad"));
        jLabelNombre1.setText(idioma.getProperty("nombre"));
        jLabelCedula1.setText(idioma.getProperty("cedula"));
        jLabelDireccion1.setText(idioma.getProperty("direccion"));
        jLabelTelefono1.setText(idioma.getProperty("telefono"));
        jLabelActividad.setText(idioma.getProperty("actividad_economica"));
        jLabelSeguridadSocial.setText(idioma.getProperty("seguridad_social"));
        jLabelFechaNacimiento.setText(idioma.getProperty("fecha_nacimiento"));
        rSComboMetroCargo.removeAllItems();
        rSComboMetroCargo.addItem(idioma.getProperty("medico"));
        rSComboMetroCargo.addItem(idioma.getProperty("enfermera"));
        jLabelBuscar1.setText(idioma.getProperty("seleccionar"));
        rSButtonMetroFiltrar1.setText(idioma.getProperty("buscar"));
        rSButtonMetroLimpiar.setText(idioma.getProperty("limpiar"));
        rSButtonMetroLimpiar4.setText(idioma.getProperty("limpiar"));
        rSButtonMetroGuardar.setText(idioma.getProperty("guardar"));
        rSButtonMetroGuardar1.setText(idioma.getProperty("guardar"));
        rSButtonMetroGuardar4.setText(idioma.getProperty("guardar"));
        rSButtonMetroFiltrar.setText(idioma.getProperty("buscar"));
        jLabelBuscar.setText(idioma.getProperty("seleccionar"));
        jLabelNombre4.setText(idioma.getProperty("nombre"));
        jLabelCodigo2.setText(idioma.getProperty("codigo"));
        jLabelBuscar3.setText(idioma.getProperty("seleccionar"));
        rSButtonMetroFiltrar3.setText(idioma.getProperty("buscar"));
        rSButtonMetroLimpiar13.setText(idioma.getProperty("limpiar"));
        rSButtonAsignar.setText(idioma.getProperty("asignar"));
        jLabelListaEnfermeras.setText(idioma.getProperty("lista_enfermeras"));
        jLabelListaHabilidades.setText(idioma.getProperty("lista_habilidades"));
        rSButtonMetroLimpiar1.setText(idioma.getProperty("limpiar"));
        rSButtonMetroLimpiar3.setText(idioma.getProperty("limpiar"));
        rSButtonMetroLimpiar4.setText(idioma.getProperty("limpiar"));
        rSButtonMetroLimpiar11.setText(idioma.getProperty("limpiar"));
        rSButtonMetroLimpiar12.setText(idioma.getProperty("limpiar"));
        jLabelNombre.setText(idioma.getProperty("nombre"));
        jLabelCedula.setText(idioma.getProperty("cedula"));
        jLabelDireccion.setText(idioma.getProperty("direccion"));
        jLabelTelefono.setText(idioma.getProperty("telefono"));
        jLabelSalario.setText(idioma.getProperty("salario"));
        jLabelJefe.setText(idioma.getProperty("cedula_jefe"));
        jLabelCargo.setText(idioma.getProperty("cargo"));
        jLabelHome.setText(idioma.getProperty("menu"));
        
        //Modulo Herramientas
        jLabelTituloTools.setText(idioma.getProperty("titulo_herramientas"));
        jLabelBackupDB.setText(idioma.getProperty("respaldar_db"));
        jLabelSeleccionar.setText(idioma.getProperty("seleccionar_opcion"));
        jLabelCleanDB.setText(idioma.getProperty("limpiar_db"));
        jLabelRestoreDB.setText(idioma.getProperty("restaurar_db"));
        jLabelCambiarIdioma.setText(idioma.getProperty("cambio"));
        rSButtonMetroIdioma.setText(idioma.getProperty("cambio"));
        jLabelSeleccionIdioma.setText(idioma.getProperty("seleccion"));
        rSButtonMetroBackup.setText(idioma.getProperty("hacer_respaldo"));
        jLabelTipoBackup.setText(idioma.getProperty("tipo_respaldo"));
        jLabelTituloRuta.setText(idioma.getProperty("tipo_ruta"));
        jLabelFormatoSalida.setText(idioma.getProperty("formato_salida"));
        jLabelTituloSO.setText(idioma.getProperty("sistema_operativo"));
        jLabelTituloTablas.setText(idioma.getProperty("titulo_tablas"));      
        jLabelHome6.setText(idioma.getProperty("menu"));
        jLabelHome7.setText(idioma.getProperty("menu"));
        jLabelHome9.setText(idioma.getProperty("menu"));
        
        //Modulo Medicinas
        jLabelTituloMedicina.setText(idioma.getProperty("titulo_medicinas"));
        jLabelMedicina.setText(idioma.getProperty("medicinas"));
        jLabelAgregarMedicina.setText(idioma.getProperty("agregar_medicina"));
        jLabelModificarMedicina.setText(idioma.getProperty("modificar_medicina"));
        jLabelNombre2.setText(idioma.getProperty("nombre"));
        jLabelCodigo.setText(idioma.getProperty("codigo"));
        jLabelCosto.setText(idioma.getProperty("costo"));
        jLabelDescripcion.setText(idioma.getProperty("descripcion"));
        jLabelBuscar2.setText(idioma.getProperty("seleccionar"));
        rSButtonMetroLimpiar2.setText(idioma.getProperty("limpiar"));
        rSButtonMetroMostrar2.setText(idioma.getProperty("limpiar"));
        rSButtonMetroGuardar2.setText(idioma.getProperty("guardar"));        
        rSButtonMetroFiltrar2.setText(idioma.getProperty("buscar"));
        jLabelHome1.setText(idioma.getProperty("menu"));
        
        //Modulo Areas
        jLabelTituloArea.setText(idioma.getProperty("titulo_areas"));
        jLabelAgregarArea.setText(idioma.getProperty("agregar_area"));
        jLabelModificarArea.setText(idioma.getProperty("modificar_area"));
        jLabelNombre5.setText(idioma.getProperty("nombre"));
        jLabelCodigo5.setText(idioma.getProperty("codigo"));
        jLabelDescripcion3.setText(idioma.getProperty("descripcion"));
        jLabelBuscar6.setText(idioma.getProperty("seleccionar"));
        rSButtonMetroLimpiar9.setText(idioma.getProperty("limpiar"));
        rSButtonMetroMostrar6.setText(idioma.getProperty("limpiar"));
        rSButtonMetroGuardar6.setText(idioma.getProperty("guardar"));        
        rSButtonMetroFiltrar6.setText(idioma.getProperty("buscar"));
        jLabelHome4.setText(idioma.getProperty("menu"));
        
        rSComboMetroIdioma.removeAllItems();        
        String idiomas[]={
                          idioma.getProperty("espanol"),
                          idioma.getProperty("ingles"),
                          };
        
        for(int i=0;i<idiomas.length;i++){
            rSComboMetroIdioma.addItem(idiomas[i]);
        }
        
    }
 
//=======================================================================================================
    
    /**
     * Metodo que pone el color en un Jpanel
     * @param panel Variable que almacena el panel a modificar
     */
    public void ponerColor(JPanel panel)
    {
        panel.setBackground(new Color(197, 197, 197));
    }
    
//=======================================================================================================
    /**
     * Metodo que permite poner el color iniciar de un JPanel
     * @param panel Variable que almacena el panel a modificar
     */
    public void repintarColor(JPanel panel)
    {
        panel.setBackground(new Color(255,255,255));
    }
//=======================================================================================================
    /**
     * Metodo que permite ocultar todos los Panel que no crresponden al panel activo
     * @param panelPadre Variable que almacena el Root Panel de los Slider
     */
    public void ocultarSliders(JPanel panelPadre)
    {
        Component[] componentes = panelPadre.getComponents();
        for(int i=0 ; i<componentes.length;i++)
        {
            componentes[i].setVisible(false);
        }
                
    }
    
//======================================================================================================= 
    /**
     * Metodo para eliminar los datos de una tabla
     * @param tabla Variable que almacena la tabla que se desea borrar
     */
    public final void borrarTabla(JTable tabla)
    {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();        
        int cFilas = modelo.getRowCount();
        for (int i = cFilas-1; i >= 0; i--) {
            modelo.removeRow(i);            
        }       
    }
//=======================================================================================================    
    /**
     * Metodo para limpiar todos los componentes de un JPanel que contiene otros componentes
     * @param panelPadre Variable que almacena ela
     */
    private void limpiarComponentes(JPanel panelPadre) {
        
        Component[] componentes = panelPadre.getComponents();
        for(int i=0 ; i<componentes.length;i++)
        {
            if(componentes[i] instanceof JTextField)
            {
                JTextField jTextFieldAux = (JTextField) componentes[i];
                jTextFieldAux.setText("");
            }
            
            else if (componentes [i] instanceof JComboBox)
            {
                JComboBox jComboBoxAux = (JComboBox) componentes[i];
                jComboBoxAux.setSelectedIndex(-1);
            }
            
            else if (componentes [i] instanceof JTable)
            {
                JTable jTableAux = (JTable) componentes[i];
                borrarTabla(jTableAux);
            }   
           
        }
    }
    
    
//=======================================================================================================    
    /**
     * Metodo para limpiar todos los componentes de un JPanel que contiene otros componentes
     * @param panelPadre Variable que almacena ela
     */
    private void negrillaComponentes(JPanel panelPadre) {
        
        Component[] componentes = panelPadre.getComponents();
        for(int i=0 ; i<componentes.length;i++)
        {
            if(componentes[i] instanceof JLabel)
            {
                
                JLabel jTextFieldAux = (JLabel) componentes[i];
                jTextFieldAux.setText("<strong>"+jTextFieldAux.getText()+"</strong>");
            }

        }
    }

//=======================================================================================================
    /**
     * Metodo que permite mostrar el PopMenu ingresando un evento del mouse
     * @param e Variable que almacena el evento del mouse que va inicializar la funcionalidad
     */
    public void mostrarPopupMenu(MouseEvent e) 
    {      
       if (e.isPopupTrigger()) 
       { 
            rSPopuMenu1.show(e.getComponent(),e.getX(), e.getY()); 
       }
    }
    
    public void mostrarPopupMenu1(MouseEvent e) 
    {      

       rSPopuMenu2.show(e.getComponent(),e.getX(), e.getY());   
    }
//=======================================================================================================    
    
    /**
     * Metodo que implementa ajustes en la funcionalidad de la interfaz
     */
    public final void ajustesFuncionales()
    {
        asignarMenuContextual();
    }
    
//=======================================================================================================    
    /**
     * Metodo que permite configurar la funcionalidad del Menu Contextual en componentes como JTextfield
     * y jTextArea
     */
    public void asignarMenuContextual()
    {
        
        accionCopiar = acciones.get(DefaultEditorKit.copyAction);
        accionPegar = acciones.get(DefaultEditorKit.pasteAction);       
        accionCortar = acciones.get(DefaultEditorKit.cutAction);
          
        accionCopiar.putValue(Action.NAME, "Copiar");
        accionCopiar.putValue(Action.ACCELERATOR_KEY,KeyStroke.getAWTKeyStroke('C', Event.CTRL_MASK)); 
 
        accionCortar.putValue(Action.NAME, "Cortar");
        accionCortar.putValue(Action.ACCELERATOR_KEY,KeyStroke.getAWTKeyStroke('X', Event.CTRL_MASK)); 
       
        accionPegar.putValue(Action.NAME, "Pegar");
        accionPegar.putValue(Action.ACCELERATOR_KEY,KeyStroke.getAWTKeyStroke('V', Event.CTRL_MASK)); 
        
        rSPopuMenu1.add(accionCopiar);
        rSPopuMenu1.add(accionCortar);
        rSPopuMenu1.add(accionPegar);        
    }
    
//======================================================================================================= 
    /**
     * Metodo que permite mostrar en la interfaz GUI los datos de un empleado existente
     * @param cedula Variable que almacena el ID del empleado encontrado
     */
    
    public void editarEmpleado(String cedula){

        ArrayList <String> empleado = (ArrayList)controladora.mostrarEmpleado(cedula);
        
        String cargo = (empleado.get(0));
        jTextFieldSalario.setText(empleado.get(1));
        jTextFieldEmail.setText(empleado.get(2));
        jTextFieldCedula.setText(empleado.get(3));
        jTextFieldJefe.setText(empleado.get(5));
        jTextFieldNombre.setText(empleado.get(6));     
        rSComboMetroArea.getModel().setSelectedItem(empleado.get(4)+", "+empleado.get(9));
        jTextFieldDireccion.setText(empleado.get(7));
        jTextFieldTelefono.setText(empleado.get(8));
        
        if(cargo.equals("Medico"))
        {
            rSComboMetroCargo.setSelectedIndex(0);
            rSComboMetroCargo.setEnabled(false);
        }
        else
        {
            rSComboMetroCargo.setSelectedIndex(1);
            rSComboMetroCargo.setEnabled(false);   
        }
    }
//=======================================================================================================    
    /**
     * Metodo que permite mostrar en la interfaz GUI los datos de un paciente existente
     * @param cedula Variable que almacena el ID del empleado encontrado
     */
    
    public void editarPaciente(String cedula){

        ArrayList <String> paciente = (ArrayList)controladora.mostrarPaciente(cedula);
        
        
        jTextFieldSeguridad.setText(paciente.get(0));
        jTextFieldActividad.setText(paciente.get(2));
        jTextFieldCedula1.setText(paciente.get(3));
        jTextFieldNombre1.setText(paciente.get(4));     
        jTextFieldDireccion1.setText(paciente.get(5));
        jTextFieldTelefono1.setText(paciente.get(6));
    }
//=======================================================================================================
    /**
     * Metodo que permite mostrar en la interfaz GUI los datos de una habilidad existente
     * @param codigo Variable que almacena el ID de la habilidad encontrado
     */
    public void editarHabilidad(String codigo){

        ArrayList <String> habilidad = (ArrayList)controladora.mostrarHabilidad(codigo);
             
        jTextFieldNombre4.setText(habilidad.get(0));
        jTextFieldCodigo2.setText(habilidad.get(1));
 
    }
//======================================================================================================= 
    /**
     * Metodo que permite mostrar en la interfaz GUI los datos de una habilidad existente
     * @param codigo Variable que almacena el ID de la habilidad encontrada
     */
    public void editarMedicina(String codigo) {
        ArrayList <String> medicina = (ArrayList)controladora.mostrarMedicamento(codigo);
             
        jTextFieldNombre2.setText(medicina.get(1));
        jTextFieldCodigo.setText(medicina.get(0));
        jTextFieldCosto.setText(medicina.get(3));
        jTextAreaDescripcionMedicina.setText(medicina.get(2));
    }
//=======================================================================================================
    /**
     * Metodo que permite mostrar en la interfaz GUI los datos de una Campaña existente
     * @param codigo Variable que almacena el ID de la campaña encontrada
     */
    
    public void editarCampana(String codigo) {
        ArrayList <String> campana = (ArrayList)controladora.mostrarCampa(codigo);
            
        jTextFieldCodigo3.setText(campana.get(0));
        jTextFieldNombre3.setText(campana.get(1));
        rSComboMetroDoctorEncargado.getModel().setSelectedItem(campana.get(4)+", "+campana.get(5));
        rSComboMetroObjetivo.getModel().setSelectedItem(campana.get(2));

    }
//=======================================================================================================     
    /**
     * Metodo que permite mostrar en la interfaz GUI los datos de una Cama existente
     * @param codigo Variable que almacena el ID de la cama encontrada
     */
    public void editarCama(String codigo) {
        ArrayList <String> cama = (ArrayList)controladora.mostrarCama(codigo);
            
        jTextFieldCodigo4.setText(cama.get(0));
        jTextAreaDescripcionCama.setText(cama.get(1));
        rSComboArea2.getModel().setSelectedItem(cama.get(2)+", "+cama.get(3));

    }
//=======================================================================================================    
    /**
     * Metodo que permite mostrar en la interfaz GUI los datos de una Area existente
     * @param codigo Variable que almacena el ID del area encontrada
     */
    
    public void editarArea(String codigo) {
        ArrayList <String> area = (ArrayList)controladora.mostrarArea(codigo);
        
        jTextFieldNombre5.setText(area.get(1));
        jTextFieldCodigo5.setText(area.get(0));
        jTextAreaDescripcionArea.setText(area.get(2));

    }
    
//=======================================================================================================        
    /**
     * Metodo Encargado de verificar que todos los campos esten correctos para poder realizar el backup
     * @return Retorna true si se puede proceder a realizar el backup o false si no se puede
     */
    public boolean todoListo (){
        try{
          
            //Inicializa en 0 (invalido)
            byte[] valido = {0, 0, 0};

            //Si la ruta del respaldo y la extensión no estan vacias
            if( rutaRespaldo.isEmpty() == false && extension.isEmpty() == false){
                valido[0] = 1;
            }
            else{
                valido[0] = 0;
            }

            //Si la lista Verde almenos tiene 1 tabla para ejecutarBackup
            if( jTableTablasBackup.getModel().getSize() > 0){
               valido[1] = 1;
            }else{
                valido[1] = 0;
            }

            //Si almenos seleccionamos un formato
            if( jRadioSQL.isSelected() == true || jRadioBackup.isSelected() == true){
                valido[2] = 1;
            }
            else{
                valido[2] = 0;
            }

            //Se inicializa en 1 (valido)
            byte resultado = 1;

            for(byte i: valido){
            resultado = (byte) (resultado * i);
            }

            if( resultado == 1)
                return true;
            else
                return false;
            }
        
        catch (NullPointerException e)
        {
            jLabelRuta.setText("");
            return false;
        }
    }
//=======================================================================================================   
    /**
     * Metodo que permite crear un JFileChooser para seleccionar la ruta de restauracion o respaldo
     * @param tipoRuta Variable que almacena el tipo de ruta BACKUP o RESTAURACION
     * @return Retorna el valor la ruta donde se va a guardar o cargar el archivo
     */
    public String seleccionarRuta(byte tipoRuta)
    {
        //Crear objeto "seleccionar" de tipo "JFileChooser"
        JFileChooser seleccionar = new JFileChooser();
        seleccionar.setCurrentDirectory(carpetaManuales); 
        seleccionar.setMultiSelectionEnabled(false);
        //Mensaje a mostrar al seleccionar donde almacenaremos la base de datos
        //Y se indican los tipos de archivos que podran ser visualizados

        String[] sql = {" y sql", "sql"};

        if( tipoRuta == RESTAURACION )
            sql[0] = ""; sql[1] = ".backup";

        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo backup" + sql[0], "backup", sql[1]);

        //Titulo que mostrara la ventana de exploración
        seleccionar.setDialogTitle("Seleccionar Ruta");
        //Se asigna el filtro previamente declaro a neustra ventana de exploración
        seleccionar.setFileFilter(filtro);

        //"showSaveDialog(vista)" Se mostrara nuestra ventana de exploración
        //Se indica que el padre es la vista (VistaPrincipal)

        byte estadoVentana = 2;

        if( tipoRuta == RESPALDO){
            estadoVentana = (byte) seleccionar.showSaveDialog(this);

        }else

        if( tipoRuta == RESTAURACION ){
            estadoVentana = (byte) seleccionar.showOpenDialog(this);

        }

        //Guardar retorna 0 (APPROVE_OPTION)
        if( estadoVentana == seleccionar.APPROVE_OPTION ){
            return seleccionar.getSelectedFile().getAbsolutePath();
        }
        else          
            //cancelar retorna 1(CANCEL_OPTION)
            if( estadoVentana == seleccionar.CANCEL_OPTION ){
                new jInformation(this, true, "<html><center>Seleccion de ruta cancelada</center></html>").setVisible(true);
            }
            else
                if( estadoVentana == seleccionar.ERROR_OPTION){
                new jWarning(this, true, "<html><center>Error al seleccionar ruta </center></html>").setVisible(true);
                }

        return null;
    }
//=======================================================================================================
    /**
     * Metodo que permite reiniciar los componentes del panel de Backup
     */
    public void reiniciarValores(){

        jLabelRuta.setText("");
        rutaRespaldo = "";
        extension = "";

        jTableTablas.setModel( controladora.ConsultarTablas() );
        jTableTablasBackup.setModel( new DefaultListModel());
        buttonGroupFormato.clearSelection();
        jRadioParcial.setSelected(true);
        rSButtonMetroDerecha.setVisible(true);
        rSButtonMetroIzquierda.setVisible(true);
    }
//=======================================================================================================
    /**
     * Metodo que permite ejecutar el backup, llamando el metodo del DAO 
     */
    public void ejecutarBackup()
    {
        //Al iniciar el proceso el cursor cambia a modo "Cargando o Espera"
        this.setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );
        //Se obtiene la cantidad de tablas que estan en la "Lista Verde"
        byte cantTablas = (byte) jTableTablasBackup.getModel().getSize();
        String tablas = "";
        //Si se ha seleccionado el respaldo Parcial
        if( jRadioParcial.isSelected() ){
            for(byte i = 0; i < cantTablas; i++){
                //Se recorren todas las tablas y concatenan en el mismo atributo "tablas" agregando ", -t," antes de cada nombre de tabla
                tablas += ",-t," + jTableTablasBackup.getModel().getElementAt(i).toString();
            }
        }

        if ( controladora.generarBackup(jLabelRuta.getText(), tablas))  {
            
            new jInformation(this, true, "<html><center>Se realizo el backup correctamente en la ruta '"+ jLabelRuta.getText()+"'</center></html>").setVisible(true);
            reiniciarValores();
        }else{
            new jWarning(this, true, "<html><center>El backup no se pudo completar,intente mas tarde</center></html>").setVisible(true);
        }

        this.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
    }
    
//=======================================================================================================   
    /**
     * Metodo que ejecuta la restauracion y valida si se ejecuta correctamente
     * @param rutaArchivo Variable que almacena la ruta del archivo a restaurar
     */

    public void ejecutarRestauracion(String rutaArchivo){
        this.setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );
        if ( rutaArchivo != null && controladora.realizarRestore(rutaArchivo) == true ){
            new jInformation(this, true, "<html><center>Se realizo la restauracion de la BD correctamente, del archivo '"+ rutaArchivo+"'</center></html>").setVisible(true);

        }else

        if( rutaArchivo != null && controladora.realizarRestore(rutaArchivo) == false){
           new jWarning(this, true, "<html><center>No se pudo completar la restauracion, porfavor intente mas tarde</center></html>").setVisible(true);
        }        
        this.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
        reiniciarValores();
    }       
//=======================================================================================================
    /**
     * Metodo que permite mover los items de un JTable a otro
     * @param origen Variable que almacena la tabla de origen de los datos
     * @param destino Variable que almacena la tabla de destino de los datos
     */
    public void moverSeleccion(JList origen, JList destino) {

        DefaultListModel listaOrigen = (DefaultListModel) origen.getModel();
        DefaultListModel listaDestino = (DefaultListModel) destino.getModel();

        int[] indice = origen.getSelectedIndices();
        Object[] tabla = origen.getSelectedValues();
        byte i = 0;
            for(; i < indice.length; i++ ){
                listaDestino.addElement( tabla[i] );
            }

            if( indice.length > 0)
                listaOrigen.removeRange(indice[0], indice[i - 1]);
            else
                new jWarning(this, true,"<html><center>Porfavor seleccione un elemento de la lista para realizar esta accion</center></html>").setVisible(true);
    }

//=======================================================================================================    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSPopuMenu1 = new rojerusan.RSPopuMenu();
        buttonGroupTipo = new javax.swing.ButtonGroup();
        buttonGroupFormato = new javax.swing.ButtonGroup();
        rSPopuMenu2 = new rojerusan.RSPopuMenu();
        jPanelMetro = new javax.swing.JPanel();
        jPanelArriba2 = new javax.swing.JPanel();
        rSButtonMetro1 = new rojerusan.RSButtonMetro();
        rSButtonMetro2 = new rojerusan.RSButtonMetro();
        rSButtonMetro3 = new rojerusan.RSButtonMetro();
        rSButtonMetro4 = new rojerusan.RSButtonMetro();
        rSButtonMetro5 = new rojerusan.RSButtonMetro();
        rSButtonMetro6 = new rojerusan.RSButtonMetro();
        rSButtonMetro7 = new rojerusan.RSButtonMetro();
        rSButtonMetro8 = new rojerusan.RSButtonMetro();
        rSButtonMetro9 = new rojerusan.RSButtonMetro();
        rSButtonMetro10 = new rojerusan.RSButtonMetro();
        rSButtonMetro11 = new rojerusan.RSButtonMetro();
        rSButtonMetro12 = new rojerusan.RSButtonMetro();
        rSButtonMetro13 = new rojerusan.RSButtonMetro();
        rSButtonMetro14 = new rojerusan.RSButtonMetro();
        rSButtonMetro15 = new rojerusan.RSButtonMetro();
        rSButtonMetro16 = new rojerusan.RSButtonMetro();
        rSButtonMetro17 = new rojerusan.RSButtonMetro();
        rSButtonMetro18 = new rojerusan.RSButtonMetro();
        rSButtonMetro19 = new rojerusan.RSButtonMetro();
        rSButtonMetro20 = new rojerusan.RSButtonMetro();
        rSButtonMetro21 = new rojerusan.RSButtonMetro();
        rSButtonMetro22 = new rojerusan.RSButtonMetro();
        rSButtonMetro23 = new rojerusan.RSButtonMetro();
        rSButtonMetro24 = new rojerusan.RSButtonMetro();
        rSButtonMetro25 = new rojerusan.RSButtonMetro();
        rSButtonMetro26 = new rojerusan.RSButtonMetro();
        rSButtonMetro27 = new rojerusan.RSButtonMetro();
        rSButtonMetro28 = new rojerusan.RSButtonMetro();
        rSButtonMetro29 = new rojerusan.RSButtonMetro();
        rSButtonMetro30 = new rojerusan.RSButtonMetro();
        rSButtonMetro31 = new rojerusan.RSButtonMetro();
        rSButtonMetro32 = new rojerusan.RSButtonMetro();
        rSButtonMetro33 = new rojerusan.RSButtonMetro();
        rSButtonMetro34 = new rojerusan.RSButtonMetro();
        rSButtonMetro35 = new rojerusan.RSButtonMetro();
        rSButtonMetro36 = new rojerusan.RSButtonMetro();
        rSButtonMetro37 = new rojerusan.RSButtonMetro();
        rSButtonMetro38 = new rojerusan.RSButtonMetro();
        rSButtonMetro39 = new rojerusan.RSButtonMetro();
        rSButtonMetro40 = new rojerusan.RSButtonMetro();
        rSButtonMetro41 = new rojerusan.RSButtonMetro();
        rSButtonMetro42 = new rojerusan.RSButtonMetro();
        rSButtonMetro43 = new rojerusan.RSButtonMetro();
        rSButtonMetro44 = new rojerusan.RSButtonMetro();
        rSButtonMetro45 = new rojerusan.RSButtonMetro();
        rSButtonMetro46 = new rojerusan.RSButtonMetro();
        rSButtonMetro47 = new rojerusan.RSButtonMetro();
        rSButtonMetro48 = new rojerusan.RSButtonMetro();
        rSButtonMetro49 = new rojerusan.RSButtonMetro();
        rSButtonMetro50 = new rojerusan.RSButtonMetro();
        rSButtonMetro51 = new rojerusan.RSButtonMetro();
        rSButtonMetro52 = new rojerusan.RSButtonMetro();
        rSButtonMetro53 = new rojerusan.RSButtonMetro();
        rSButtonMetro54 = new rojerusan.RSButtonMetro();
        rSButtonMetro55 = new rojerusan.RSButtonMetro();
        rSButtonMetro56 = new rojerusan.RSButtonMetro();
        rSButtonMetro57 = new rojerusan.RSButtonMetro();
        rSButtonMetro58 = new rojerusan.RSButtonMetro();
        rSButtonMetro59 = new rojerusan.RSButtonMetro();
        rSButtonMetro60 = new rojerusan.RSButtonMetro();
        rSButtonMetro61 = new rojerusan.RSButtonMetro();
        rSButtonMetro62 = new rojerusan.RSButtonMetro();
        rSButtonMetro63 = new rojerusan.RSButtonMetro();
        rSButtonMetro64 = new rojerusan.RSButtonMetro();
        rSButtonMetro65 = new rojerusan.RSButtonMetro();
        rSButtonMetro66 = new rojerusan.RSButtonMetro();
        rSButtonMetro67 = new rojerusan.RSButtonMetro();
        rSButtonMetro68 = new rojerusan.RSButtonMetro();
        rSButtonMetro69 = new rojerusan.RSButtonMetro();
        rSButtonMetro70 = new rojerusan.RSButtonMetro();
        rSButtonMetro71 = new rojerusan.RSButtonMetro();
        rSButtonMetro72 = new rojerusan.RSButtonMetro();
        rSButtonMetro73 = new rojerusan.RSButtonMetro();
        rSButtonMetro74 = new rojerusan.RSButtonMetro();
        rSButtonMetro75 = new rojerusan.RSButtonMetro();
        rSButtonMetro76 = new rojerusan.RSButtonMetro();
        rSButtonMetro77 = new rojerusan.RSButtonMetro();
        rSButtonMetro78 = new rojerusan.RSButtonMetro();
        rSButtonMetro79 = new rojerusan.RSButtonMetro();
        rSButtonMetro80 = new rojerusan.RSButtonMetro();
        rSButtonMetro81 = new rojerusan.RSButtonMetro();
        rSButtonMetro82 = new rojerusan.RSButtonMetro();
        jPanelAbajo2 = new javax.swing.JPanel();
        jLabelNombre6 = new javax.swing.JLabel();
        jTextFieldNombre6 = new javax.swing.JTextField();
        jTextFieldIngreso = new javax.swing.JTextField();
        rSButtonMetroIngresar = new rojerusan.RSButtonMetro();
        rSButtonMetroBorrar = new rojerusan.RSButtonMetro();
        rSButtonMetroBorrarTodo = new rojerusan.RSButtonMetro();
        rSButtonMetroFinalizar = new rojerusan.RSButtonMetro();
        jPanelPpal = new javax.swing.JPanel();
        jPanelMenu = new javax.swing.JPanel();
        jPanelTitulo = new javax.swing.JPanel();
        jPanelPerfil = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelimgPerfil = new javax.swing.JLabel();
        jPanelRol = new javax.swing.JPanel();
        jLabelRol1 = new javax.swing.JLabel();
        jLabelRol2 = new javax.swing.JLabel();
        jPanelCamas = new javax.swing.JPanel();
        jLabelCamas = new javax.swing.JLabel();
        jLabelimgCamas = new javax.swing.JLabel();
        jPanelCampanas = new javax.swing.JPanel();
        jLabelCampanas = new javax.swing.JLabel();
        jLabelimgCampanas = new javax.swing.JLabel();
        jPanelUsuarios = new javax.swing.JPanel();
        jLabelUsuarios = new javax.swing.JLabel();
        jLabelimgUsuarios = new javax.swing.JLabel();
        jPanelMedicinas = new javax.swing.JPanel();
        jLabelMedicinas = new javax.swing.JLabel();
        jLabelimgMedicinas = new javax.swing.JLabel();
        jPanelAreas = new javax.swing.JPanel();
        jLabelAreas = new javax.swing.JLabel();
        jLabelimgAreas = new javax.swing.JLabel();
        rSButtonCerrarSesion = new rojerusan.RSButtonPane();
        jLabelimgCerrarSesion = new javax.swing.JLabel();
        jPanelStaff = new javax.swing.JPanel();
        jPanelTituloUsuario = new javax.swing.JPanel();
        jLabelTituloUsuario = new javax.swing.JLabel();
        jLabelimgUsuario = new javax.swing.JLabel();
        jButtonSlider = new javax.swing.JButton();
        jPanelSlider = new javax.swing.JPanel();
        jLabelPaciente = new javax.swing.JLabel();
        jLabelEmpleado = new javax.swing.JLabel();
        jSeparatorPaciente = new javax.swing.JSeparator();
        jSeparatorEmpleado = new javax.swing.JSeparator();
        rSButtonPaneHome = new rojerusan.RSButtonPane();
        jLabelHome = new javax.swing.JLabel();
        rSButtonAgregarPaciente = new rojerusan.RSButtonPane();
        jLabelAgregarPaciente = new javax.swing.JLabel();
        rSButtonModificarPaciente = new rojerusan.RSButtonPane();
        jLabelModificarPaciente = new javax.swing.JLabel();
        rSButtonAgregarEmpleado = new rojerusan.RSButtonPane();
        jLabelAgregarEmpleado = new javax.swing.JLabel();
        rSButtonModificarEmpleado = new rojerusan.RSButtonPane();
        jLabelModificarEmpleado = new javax.swing.JLabel();
        rSButtonAgregarHabilidad = new rojerusan.RSButtonPane();
        jLabelAgregarHabilidad = new javax.swing.JLabel();
        rSButtonModificarHabilidad = new rojerusan.RSButtonPane();
        jLabelModificarHabilidad = new javax.swing.JLabel();
        rSButtonAsignarHabilidad = new rojerusan.RSButtonPane();
        jLabelAsignarHabilidad = new javax.swing.JLabel();
        jPanelSliders = new javax.swing.JPanel();
        jPanelAgregarPaciente = new javax.swing.JPanel();
        jLabelNombre1 = new javax.swing.JLabel();
        jLabelCedula1 = new javax.swing.JLabel();
        jLabelDireccion1 = new javax.swing.JLabel();
        jLabelTelefono1 = new javax.swing.JLabel();
        jLabelActividad = new javax.swing.JLabel();
        jLabelSeguridadSocial = new javax.swing.JLabel();
        jLabelFechaNacimiento = new javax.swing.JLabel();
        jTextFieldNombre1 = new javax.swing.JTextField();
        jTextFieldCedula1 = new javax.swing.JTextField();
        jTextFieldDireccion1 = new javax.swing.JTextField();
        jTextFieldTelefono1 = new javax.swing.JTextField();
        jTextFieldActividad = new javax.swing.JTextField();
        jTextFieldSeguridad = new javax.swing.JTextField();
        rSButtonMetroGuardar1 = new rojerusan.RSButtonMetro();
        rSButtonMetroLimpiar1 = new rojerusan.RSButtonMetro();
        rSDateChooserFechaNacimiento = new rojeru_san.componentes.RSDateChooser();
        jPanelModificarPaciente = new javax.swing.JPanel();
        jLabelBuscar1 = new javax.swing.JLabel();
        jTextFieldBuscar1 = new javax.swing.JTextField();
        rSButtonMetroFiltrar1 = new rojerusan.RSButtonMetro();
        jScrollPaneTablaPacientes = new javax.swing.JScrollPane();
        rSTablaPacientes = new rojerusan.RSTableMetro();
        rSButtonMetroLimpiar12 = new rojerusan.RSButtonMetro();
        rSButtonMetroFiltrar7 = new rojerusan.RSButtonMetro();
        jPanelAgregarEmpleado = new javax.swing.JPanel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelCedula = new javax.swing.JLabel();
        jLabelDireccion = new javax.swing.JLabel();
        jLabelTelefono = new javax.swing.JLabel();
        jLabelSalario = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jLabelJefe = new javax.swing.JLabel();
        jLabelCargo = new javax.swing.JLabel();
        jLabelArea = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldCedula = new javax.swing.JTextField();
        jTextFieldDireccion = new javax.swing.JTextField();
        jTextFieldTelefono = new javax.swing.JTextField();
        jTextFieldSalario = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldJefe = new javax.swing.JTextField();
        rSComboMetroCargo = new rojerusan.RSComboMetro();
        rSComboMetroArea = new rojerusan.RSComboMetro();
        rSButtonMetroGuardar = new rojerusan.RSButtonMetro();
        rSButtonMetroLimpiar = new rojerusan.RSButtonMetro();
        jPanelModificarEmpleado = new javax.swing.JPanel();
        jLabelBuscar = new javax.swing.JLabel();
        jTextFieldBuscar = new javax.swing.JTextField();
        rSButtonMetroFiltrar = new rojerusan.RSButtonMetro();
        jScrollPaneTablaEmpleados = new javax.swing.JScrollPane();
        rSTablaEmpleados = new rojerusan.RSTableMetro();
        rSButtonMetroLimpiar11 = new rojerusan.RSButtonMetro();
        rSButtonMetroFiltrar8 = new rojerusan.RSButtonMetro();
        jPanelAgregarHabilidad = new javax.swing.JPanel();
        jLabelNombre4 = new javax.swing.JLabel();
        jLabelCodigo2 = new javax.swing.JLabel();
        jTextFieldNombre4 = new javax.swing.JTextField();
        jTextFieldCodigo2 = new javax.swing.JTextField();
        rSButtonMetroGuardar4 = new rojerusan.RSButtonMetro();
        rSButtonMetroLimpiar4 = new rojerusan.RSButtonMetro();
        jPanelModificarHabilidad = new javax.swing.JPanel();
        jLabelBuscar3 = new javax.swing.JLabel();
        jTextFieldBuscar3 = new javax.swing.JTextField();
        rSButtonMetroLimpiar13 = new rojerusan.RSButtonMetro();
        rSButtonMetroFiltrar3 = new rojerusan.RSButtonMetro();
        jScrollPaneTablaHabilidades = new javax.swing.JScrollPane();
        rSTablaHabilidades = new rojerusan.RSTableMetro();
        rSButtonMetroFiltrar9 = new rojerusan.RSButtonMetro();
        jPanelAsignarHabilidad = new javax.swing.JPanel();
        jLabelListaEnfermeras = new javax.swing.JLabel();
        jLabelListaHabilidades = new javax.swing.JLabel();
        rSComboMetroListaEnfermeras = new rojerusan.RSComboMetro();
        rSComboMetroListaHabilidades = new rojerusan.RSComboMetro();
        rSButtonMetroLimpiar3 = new rojerusan.RSButtonMetro();
        rSButtonAsignar = new rojerusan.RSButtonCircle();
        jPanelMedicians = new javax.swing.JPanel();
        jPanelTituloMedicina = new javax.swing.JPanel();
        jLabelTituloMedicina = new javax.swing.JLabel();
        jLabelimgMedicina = new javax.swing.JLabel();
        jButtonSlider1 = new javax.swing.JButton();
        jPanelSlider1 = new javax.swing.JPanel();
        jLabelMedicina = new javax.swing.JLabel();
        jSeparatorMedicina = new javax.swing.JSeparator();
        rSButtonPaneHome1 = new rojerusan.RSButtonPane();
        jLabelHome1 = new javax.swing.JLabel();
        rSButtonAgregarMedicina = new rojerusan.RSButtonPane();
        jLabelAgregarMedicina = new javax.swing.JLabel();
        rSButtonModificarMedicina = new rojerusan.RSButtonPane();
        jLabelModificarMedicina = new javax.swing.JLabel();
        jPanelSliders1 = new javax.swing.JPanel();
        jPanelAgregarMedicina = new javax.swing.JPanel();
        jLabelNombre2 = new javax.swing.JLabel();
        jLabelCodigo = new javax.swing.JLabel();
        jLabelCosto = new javax.swing.JLabel();
        jLabelDescripcion = new javax.swing.JLabel();
        jTextFieldNombre2 = new javax.swing.JTextField();
        jTextFieldCodigo = new javax.swing.JTextField();
        jTextFieldCosto = new javax.swing.JTextField();
        rSButtonMetroGuardar2 = new rojerusan.RSButtonMetro();
        rSButtonMetroLimpiar2 = new rojerusan.RSButtonMetro();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescripcionMedicina = new javax.swing.JTextArea();
        jPanelModificarMedicina = new javax.swing.JPanel();
        jLabelBuscar2 = new javax.swing.JLabel();
        jTextFieldBuscar2 = new javax.swing.JTextField();
        rSButtonMetroMostrar2 = new rojerusan.RSButtonMetro();
        rSButtonMetroFiltrar2 = new rojerusan.RSButtonMetro();
        jScrollPaneTablaMedicamentos = new javax.swing.JScrollPane();
        rSTablaMedicamentos = new rojerusan.RSTableMetro();
        rSButtonMetroFiltrar10 = new rojerusan.RSButtonMetro();
        jPanelCampaing = new javax.swing.JPanel();
        jPanelTituloCampaing = new javax.swing.JPanel();
        jLabelTituloCampaing = new javax.swing.JLabel();
        jLabelimgMedicinaCampaing = new javax.swing.JLabel();
        jButtonSlider2 = new javax.swing.JButton();
        jPanelSlider2 = new javax.swing.JPanel();
        jLabelCampaing = new javax.swing.JLabel();
        jSeparatorCampaing = new javax.swing.JSeparator();
        rSButtonPaneHome2 = new rojerusan.RSButtonPane();
        jLabelHome2 = new javax.swing.JLabel();
        rSButtonAgregarCampana = new rojerusan.RSButtonPane();
        jLabelAgregarCampana = new javax.swing.JLabel();
        rSButtonModificarCampana = new rojerusan.RSButtonPane();
        jLabelModificarCampana = new javax.swing.JLabel();
        rSButtonAsignarCampana = new rojerusan.RSButtonPane();
        jLabelAsignarCampana = new javax.swing.JLabel();
        jPanelSliders2 = new javax.swing.JPanel();
        jPanelAgregarCampana = new javax.swing.JPanel();
        jLabelNombre3 = new javax.swing.JLabel();
        jLabelCodigo3 = new javax.swing.JLabel();
        jLabelFecha = new javax.swing.JLabel();
        jLabelDoctorEncargado = new javax.swing.JLabel();
        jLabelObjetivo = new javax.swing.JLabel();
        jTextFieldNombre3 = new javax.swing.JTextField();
        jTextFieldCodigo3 = new javax.swing.JTextField();
        rSDateChooserFecha = new rojeru_san.componentes.RSDateChooser();
        rSComboMetroDoctorEncargado = new rojerusan.RSComboMetro();
        rSComboMetroObjetivo = new rojerusan.RSComboMetro();
        rSButtonMetroGuardar3 = new rojerusan.RSButtonMetro();
        rSButtonMetroLimpiar5 = new rojerusan.RSButtonMetro();
        jPanelModificarCampana = new javax.swing.JPanel();
        jLabelBuscar4 = new javax.swing.JLabel();
        jTextFieldBuscar4 = new javax.swing.JTextField();
        rSButtonMetroMostrar4 = new rojerusan.RSButtonMetro();
        rSButtonMetroFiltrar4 = new rojerusan.RSButtonMetro();
        jScrollPaneTablaCampana = new javax.swing.JScrollPane();
        rSTablaCampana = new rojerusan.RSTableMetro();
        rSButtonMetroFiltrar11 = new rojerusan.RSButtonMetro();
        jPanelAsignarCampana = new javax.swing.JPanel();
        jLabelListaCampanas = new javax.swing.JLabel();
        jLabelListaPacientes = new javax.swing.JLabel();
        rSComboMetroListaCampanas = new rojerusan.RSComboMetro();
        rSComboMetroListaPacientes = new rojerusan.RSComboMetro();
        rSButtonMetroLimpiar6 = new rojerusan.RSButtonMetro();
        rSButtonAsignar1 = new rojerusan.RSButtonCircle();
        jPanelBed = new javax.swing.JPanel();
        jPanelTituloBed = new javax.swing.JPanel();
        jLabelTituloCama = new javax.swing.JLabel();
        jLabelimgCama = new javax.swing.JLabel();
        jButtonSlider3 = new javax.swing.JButton();
        jPanelSlider3 = new javax.swing.JPanel();
        jLabelBed = new javax.swing.JLabel();
        jSeparatorBed = new javax.swing.JSeparator();
        rSButtonPaneHome3 = new rojerusan.RSButtonPane();
        jLabelHome3 = new javax.swing.JLabel();
        rSButtonAgregarCama = new rojerusan.RSButtonPane();
        jLabelAgregarCama = new javax.swing.JLabel();
        rSButtonModificarCama = new rojerusan.RSButtonPane();
        jLabelModificarCama = new javax.swing.JLabel();
        rSButtonAsignarCama = new rojerusan.RSButtonPane();
        jLabelAsignarCama = new javax.swing.JLabel();
        jPanelSliders3 = new javax.swing.JPanel();
        jPanelModificarCama = new javax.swing.JPanel();
        jLabelBuscar5 = new javax.swing.JLabel();
        jTextFieldBuscar5 = new javax.swing.JTextField();
        rSButtonMetroMostrar5 = new rojerusan.RSButtonMetro();
        rSButtonMetroFiltrar5 = new rojerusan.RSButtonMetro();
        jScrollPaneTablaCama = new javax.swing.JScrollPane();
        rSTablaCama = new rojerusan.RSTableMetro();
        rSButtonMetroFiltrar12 = new rojerusan.RSButtonMetro();
        jPanelAgregarCama = new javax.swing.JPanel();
        jLabelDescripcion2 = new javax.swing.JLabel();
        jLabelCodigo4 = new javax.swing.JLabel();
        jLabelArea2 = new javax.swing.JLabel();
        jTextFieldCodigo4 = new javax.swing.JTextField();
        rSComboArea2 = new rojerusan.RSComboMetro();
        rSButtonMetroGuardar5 = new rojerusan.RSButtonMetro();
        rSButtonMetroLimpiar7 = new rojerusan.RSButtonMetro();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaDescripcionCama = new javax.swing.JTextArea();
        jCheckBoxDisponible = new javax.swing.JCheckBox();
        jPanelAsignarCama = new javax.swing.JPanel();
        jLabelFechaAsignacion = new javax.swing.JLabel();
        jLabelListaPacientes1 = new javax.swing.JLabel();
        rSComboMetroListaPacientes1 = new rojerusan.RSComboMetro();
        rSButtonMetroLimpiar8 = new rojerusan.RSButtonMetro();
        rSButtonAsignar2 = new rojerusan.RSButtonCircle();
        rSDateChooserFechaAsignacion = new rojeru_san.componentes.RSDateChooser();
        rSComboMetroListaCamas = new rojerusan.RSComboMetro();
        jLabelListaCamas = new javax.swing.JLabel();
        jPanelArea = new javax.swing.JPanel();
        jPanelTituloArea = new javax.swing.JPanel();
        jLabelTituloArea = new javax.swing.JLabel();
        jLabelimgArea = new javax.swing.JLabel();
        jButtonSlider4 = new javax.swing.JButton();
        jPanelSlider4 = new javax.swing.JPanel();
        jLabelAreaS = new javax.swing.JLabel();
        jSeparatorAreaS = new javax.swing.JSeparator();
        rSButtonPaneHome4 = new rojerusan.RSButtonPane();
        jLabelHome4 = new javax.swing.JLabel();
        rSButtonAgregarArea = new rojerusan.RSButtonPane();
        jLabelAgregarArea = new javax.swing.JLabel();
        rSButtonModificarArea = new rojerusan.RSButtonPane();
        jLabelModificarArea = new javax.swing.JLabel();
        jPanelSliders4 = new javax.swing.JPanel();
        jPanelAgregarArea = new javax.swing.JPanel();
        jLabelNombre5 = new javax.swing.JLabel();
        jLabelDescripcion3 = new javax.swing.JLabel();
        jLabelCodigo5 = new javax.swing.JLabel();
        jTextFieldCodigo5 = new javax.swing.JTextField();
        rSButtonMetroGuardar6 = new rojerusan.RSButtonMetro();
        rSButtonMetroLimpiar9 = new rojerusan.RSButtonMetro();
        jTextFieldNombre5 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaDescripcionArea = new javax.swing.JTextArea();
        jPanelModificarArea = new javax.swing.JPanel();
        jLabelBuscar6 = new javax.swing.JLabel();
        jTextFieldBuscar6 = new javax.swing.JTextField();
        rSButtonMetroMostrar6 = new rojerusan.RSButtonMetro();
        rSButtonMetroFiltrar6 = new rojerusan.RSButtonMetro();
        jScrollPaneTablaArea = new javax.swing.JScrollPane();
        rSTablaArea = new rojerusan.RSTableMetro();
        rSButtonMetroFiltrar13 = new rojerusan.RSButtonMetro();
        jPanelReports = new javax.swing.JPanel();
        jPanelTituloReport = new javax.swing.JPanel();
        jLabelTituloReport = new javax.swing.JLabel();
        jLabelimgReport = new javax.swing.JLabel();
        rSButtonPaneHome8 = new rojerusan.RSButtonPane();
        jLabelHome8 = new javax.swing.JLabel();
        rSButtonMetroListWorker = new rojerusan.RSButtonMetro();
        rSButtonMetroHistoryClinic = new rojerusan.RSButtonMetro();
        rSButtonMetroNumberQuote = new rojerusan.RSButtonMetro();
        rSButtonMetroMonthlyAgenda = new rojerusan.RSButtonMetro();
        jLabelNumberQuote = new javax.swing.JLabel();
        jLabelSeleccionar1 = new javax.swing.JLabel();
        jLabelListWorker = new javax.swing.JLabel();
        jLabeHistoryClinic = new javax.swing.JLabel();
        jLabelMonthlyAgenda = new javax.swing.JLabel();
        jPanelTools = new javax.swing.JPanel();
        jPanelTituloTools = new javax.swing.JPanel();
        jLabelTituloTools = new javax.swing.JLabel();
        jLabelimgTools = new javax.swing.JLabel();
        jPanelSliders6 = new javax.swing.JPanel();
        jPanelPrincipal = new javax.swing.JPanel();
        rSButtonMetroCleanDB = new rojerusan.RSButtonMetro();
        rSButtonMetroRestoreBD = new rojerusan.RSButtonMetro();
        rSButtonMetroBackupBD = new rojerusan.RSButtonMetro();
        rSButtonMetroCambiarIdioma = new rojerusan.RSButtonMetro();
        jLabelBackupDB = new javax.swing.JLabel();
        jLabelSeleccionar = new javax.swing.JLabel();
        jLabelCleanDB = new javax.swing.JLabel();
        jLabelRestoreDB = new javax.swing.JLabel();
        jLabelCambiarIdioma = new javax.swing.JLabel();
        rSButtonPaneHome6 = new rojerusan.RSButtonPane();
        jLabelHome6 = new javax.swing.JLabel();
        jPanelCambiarIdioma = new javax.swing.JPanel();
        rSButtonPaneHome9 = new rojerusan.RSButtonPane();
        jLabelHome9 = new javax.swing.JLabel();
        rSComboMetroIdioma = new rojerusan.RSComboMetro();
        rSButtonMetroIdioma = new rojerusan.RSButtonMetro();
        jLabelSeleccionIdioma = new javax.swing.JLabel();
        jPanelBackupBD = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        rSButtonMetroBackup = new rojerusan.RSButtonMetro();
        jLabelFormatoSalida = new javax.swing.JLabel();
        jLabelTituloRuta = new javax.swing.JLabel();
        jLabelTipoBackup = new javax.swing.JLabel();
        jRadioParcial = new javax.swing.JRadioButton();
        jRadioTotal = new javax.swing.JRadioButton();
        jLabelRuta = new javax.swing.JLabel();
        jRadioBackup = new javax.swing.JRadioButton();
        jRadioSQL = new javax.swing.JRadioButton();
        jLabelSO = new javax.swing.JLabel();
        jLabelTituloSO = new javax.swing.JLabel();
        jLabelTituloTablas = new javax.swing.JLabel();
        jLabelTituloBackup = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableTablasBackup = new javax.swing.JList();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableTablas = new javax.swing.JList();
        rSButtonMetroIzquierda = new rojerusan.RSButtonMetro();
        rSButtonMetroDerecha = new rojerusan.RSButtonMetro();
        rSButtonChooserRuta = new rojerusan.RSButtonMetro();
        rSButtonPaneHome7 = new rojerusan.RSButtonPane();
        jLabelHome7 = new javax.swing.JLabel();

        jPanelMetro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelArriba2.setPreferredSize(new java.awt.Dimension(700, 300));
        jPanelArriba2.setLayout(new java.awt.GridLayout(12, 8, 1, 1));

        rSButtonMetro1.setText("Agencia");
        rSButtonMetro1.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro1.setInheritsPopupMenu(true);
        rSButtonMetro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro1ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro1);

        rSButtonMetro2.setText("Almacen");
        rSButtonMetro2.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro2.setInheritsPopupMenu(true);
        rSButtonMetro2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro2ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro2);

        rSButtonMetro3.setText("Altillo");
        rSButtonMetro3.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro3.setInheritsPopupMenu(true);
        rSButtonMetro3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro3ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro3);

        rSButtonMetro4.setText("Apartado");
        rSButtonMetro4.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro4.setInheritsPopupMenu(true);
        rSButtonMetro4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro4ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro4);

        rSButtonMetro5.setText("Autopista");
        rSButtonMetro5.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro5.setInheritsPopupMenu(true);
        rSButtonMetro5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro5ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro5);

        rSButtonMetro6.setText("Avenida");
        rSButtonMetro6.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro6.setInheritsPopupMenu(true);
        rSButtonMetro6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro6ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro6);

        rSButtonMetro7.setText("Barrio");
        rSButtonMetro7.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro7.setInheritsPopupMenu(true);
        rSButtonMetro7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro7ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro7);

        rSButtonMetro8.setText("Bloque");
        rSButtonMetro8.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro8.setInheritsPopupMenu(true);
        rSButtonMetro8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro8ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro8);

        rSButtonMetro9.setText("Boulevar");
        rSButtonMetro9.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro9.setInheritsPopupMenu(true);
        rSButtonMetro9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro9ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro9);

        rSButtonMetro10.setText("Calle");
        rSButtonMetro10.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro10.setInheritsPopupMenu(true);
        rSButtonMetro10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro10ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro10);

        rSButtonMetro11.setText("Carretera");
        rSButtonMetro11.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro11.setInheritsPopupMenu(true);
        rSButtonMetro11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro11ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro11);

        rSButtonMetro12.setText("Carrera");
        rSButtonMetro12.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro12.setInheritsPopupMenu(true);
        rSButtonMetro12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro12ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro12);

        rSButtonMetro13.setText("Casa");
        rSButtonMetro13.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro13.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro13.setInheritsPopupMenu(true);
        rSButtonMetro13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro13ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro13);

        rSButtonMetro14.setText("Circular");
        rSButtonMetro14.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro14.setInheritsPopupMenu(true);
        rSButtonMetro14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro14ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro14);

        rSButtonMetro15.setText("Circunvalar");
        rSButtonMetro15.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro15.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro15.setInheritsPopupMenu(true);
        rSButtonMetro15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro15ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro15);

        rSButtonMetro16.setText("Ciudadela");
        rSButtonMetro16.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro16.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro16.setInheritsPopupMenu(true);
        rSButtonMetro16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro16ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro16);

        rSButtonMetro17.setText("Conjunto");
        rSButtonMetro17.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro17.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro17.setInheritsPopupMenu(true);
        rSButtonMetro17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro17ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro17);

        rSButtonMetro18.setText("Agencia");
        rSButtonMetro18.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro18.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro18.setInheritsPopupMenu(true);
        rSButtonMetro18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro18ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro18);

        rSButtonMetro19.setText("Corregimiento");
        rSButtonMetro19.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro19.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro19.setInheritsPopupMenu(true);
        rSButtonMetro19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro19ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro19);

        rSButtonMetro20.setText("Centro");
        rSButtonMetro20.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro20.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro20.setInheritsPopupMenu(true);
        rSButtonMetro20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro20ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro20);

        rSButtonMetro21.setText("Diagonal");
        rSButtonMetro21.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro21.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro21.setInheritsPopupMenu(true);
        rSButtonMetro21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro21ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro21);

        rSButtonMetro22.setText("Edificio");
        rSButtonMetro22.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro22.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro22.setInheritsPopupMenu(true);
        rSButtonMetro22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro22ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro22);

        rSButtonMetro23.setText("Entrada");
        rSButtonMetro23.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro23.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro23.setInheritsPopupMenu(true);
        rSButtonMetro23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro23ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro23);

        rSButtonMetro24.setText("Esquina");
        rSButtonMetro24.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro24.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro24.setInheritsPopupMenu(true);
        rSButtonMetro24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro24ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro24);

        rSButtonMetro25.setText("Etapa");
        rSButtonMetro25.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro25.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro25.setInheritsPopupMenu(true);
        rSButtonMetro25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro25ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro25);

        rSButtonMetro26.setText("Finca");
        rSButtonMetro26.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro26.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro26.setInheritsPopupMenu(true);
        rSButtonMetro26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro26ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro26);

        rSButtonMetro27.setText("Hacienda");
        rSButtonMetro27.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro27.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro27.setInheritsPopupMenu(true);
        rSButtonMetro27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro27ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro27);

        rSButtonMetro28.setText("Kilometro");
        rSButtonMetro28.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro28.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro28.setInheritsPopupMenu(true);
        rSButtonMetro28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro28ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro28);

        rSButtonMetro29.setText("Local");
        rSButtonMetro29.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro29.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro29.setInheritsPopupMenu(true);
        rSButtonMetro29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro29ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro29);

        rSButtonMetro30.setText("Lote");
        rSButtonMetro30.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro30.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro30.setInheritsPopupMenu(true);
        rSButtonMetro30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro30ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro30);

        rSButtonMetro31.setText("Manzana");
        rSButtonMetro31.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro31.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro31.setInheritsPopupMenu(true);
        rSButtonMetro31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro31ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro31);

        rSButtonMetro32.setText("Oficina");
        rSButtonMetro32.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro32.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro32.setInheritsPopupMenu(true);
        rSButtonMetro32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro32ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro32);

        rSButtonMetro33.setText("Parque");
        rSButtonMetro33.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro33.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro33.setInheritsPopupMenu(true);
        rSButtonMetro33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro33ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro33);

        rSButtonMetro34.setText("Pasaje");
        rSButtonMetro34.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro34.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro34.setInheritsPopupMenu(true);
        rSButtonMetro34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro34ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro34);

        rSButtonMetro35.setText("Piso");
        rSButtonMetro35.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro35.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro35.setInheritsPopupMenu(true);
        rSButtonMetro35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro35ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro35);

        rSButtonMetro36.setText("Planta");
        rSButtonMetro36.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro36.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro36.setInheritsPopupMenu(true);
        rSButtonMetro36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro36ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro36);

        rSButtonMetro37.setText("Salon");
        rSButtonMetro37.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro37.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro37.setInheritsPopupMenu(true);
        rSButtonMetro37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro37ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro37);

        rSButtonMetro38.setText("Sector");
        rSButtonMetro38.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro38.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro38.setInheritsPopupMenu(true);
        rSButtonMetro38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro38ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro38);

        rSButtonMetro39.setText("Torre");
        rSButtonMetro39.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro39.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro39.setInheritsPopupMenu(true);
        rSButtonMetro39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro39ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro39);

        rSButtonMetro40.setText("Transversal");
        rSButtonMetro40.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro40.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro40.setInheritsPopupMenu(true);
        rSButtonMetro40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro40ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro40);

        rSButtonMetro41.setText("Unidad");
        rSButtonMetro41.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro41.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro41.setInheritsPopupMenu(true);
        rSButtonMetro41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro41ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro41);

        rSButtonMetro42.setText("Vereda");
        rSButtonMetro42.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro42.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro42.setInheritsPopupMenu(true);
        rSButtonMetro42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro42ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro42);

        rSButtonMetro43.setText("Zona");
        rSButtonMetro43.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro43.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro43.setInheritsPopupMenu(true);
        rSButtonMetro43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro43ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro43);

        rSButtonMetro44.setText("Zona Franca");
        rSButtonMetro44.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro44.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro44.setInheritsPopupMenu(true);
        rSButtonMetro44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro44ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro44);

        rSButtonMetro45.setText("#");
        rSButtonMetro45.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro45.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro45.setInheritsPopupMenu(true);
        rSButtonMetro45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro45ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro45);

        rSButtonMetro46.setText("-");
        rSButtonMetro46.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro46.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro46.setInheritsPopupMenu(true);
        rSButtonMetro46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro46ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro46);

        rSButtonMetro47.setText("0");
        rSButtonMetro47.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro47.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro47.setInheritsPopupMenu(true);
        rSButtonMetro47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro47ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro47);

        rSButtonMetro48.setText("1");
        rSButtonMetro48.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro48.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro48.setInheritsPopupMenu(true);
        rSButtonMetro48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro48ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro48);

        rSButtonMetro49.setText("2");
        rSButtonMetro49.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro49.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro49.setInheritsPopupMenu(true);
        rSButtonMetro49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro49ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro49);

        rSButtonMetro50.setText("3");
        rSButtonMetro50.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro50.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro50.setInheritsPopupMenu(true);
        rSButtonMetro50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro50ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro50);

        rSButtonMetro51.setText("4");
        rSButtonMetro51.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro51.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro51.setInheritsPopupMenu(true);
        rSButtonMetro51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro51ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro51);

        rSButtonMetro52.setText("5");
        rSButtonMetro52.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro52.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro52.setInheritsPopupMenu(true);
        rSButtonMetro52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro52ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro52);

        rSButtonMetro53.setText("6");
        rSButtonMetro53.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro53.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro53.setInheritsPopupMenu(true);
        rSButtonMetro53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro53ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro53);

        rSButtonMetro54.setText("7");
        rSButtonMetro54.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro54.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro54.setInheritsPopupMenu(true);
        rSButtonMetro54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro54ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro54);

        rSButtonMetro55.setText("8");
        rSButtonMetro55.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro55.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro55.setInheritsPopupMenu(true);
        rSButtonMetro55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro55ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro55);

        rSButtonMetro56.setText("9");
        rSButtonMetro56.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro56.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro56.setInheritsPopupMenu(true);
        rSButtonMetro56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro56ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro56);

        rSButtonMetro57.setText("A");
        rSButtonMetro57.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro57.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro57.setInheritsPopupMenu(true);
        rSButtonMetro57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro57ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro57);

        rSButtonMetro58.setText("B");
        rSButtonMetro58.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro58.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro58.setInheritsPopupMenu(true);
        rSButtonMetro58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro58ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro58);

        rSButtonMetro59.setText("C");
        rSButtonMetro59.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro59.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro59.setInheritsPopupMenu(true);
        rSButtonMetro59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro59ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro59);

        rSButtonMetro60.setText("D");
        rSButtonMetro60.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro60.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro60.setInheritsPopupMenu(true);
        rSButtonMetro60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro60ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro60);

        rSButtonMetro61.setText("E");
        rSButtonMetro61.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro61.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro61.setInheritsPopupMenu(true);
        rSButtonMetro61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro61ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro61);

        rSButtonMetro62.setText("F");
        rSButtonMetro62.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro62.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro62.setInheritsPopupMenu(true);
        rSButtonMetro62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro62ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro62);

        rSButtonMetro63.setText("G");
        rSButtonMetro63.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro63.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro63.setInheritsPopupMenu(true);
        rSButtonMetro63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro63ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro63);

        rSButtonMetro64.setText("H");
        rSButtonMetro64.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro64.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro64.setInheritsPopupMenu(true);
        rSButtonMetro64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro64ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro64);

        rSButtonMetro65.setText("I");
        rSButtonMetro65.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro65.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro65.setInheritsPopupMenu(true);
        rSButtonMetro65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro65ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro65);

        rSButtonMetro66.setText("J");
        rSButtonMetro66.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro66.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro66.setInheritsPopupMenu(true);
        rSButtonMetro66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro66ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro66);

        rSButtonMetro67.setText("K");
        rSButtonMetro67.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro67.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro67.setInheritsPopupMenu(true);
        rSButtonMetro67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro67ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro67);

        rSButtonMetro68.setText("L");
        rSButtonMetro68.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro68.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro68.setInheritsPopupMenu(true);
        rSButtonMetro68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro68ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro68);

        rSButtonMetro69.setText("M");
        rSButtonMetro69.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro69.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro69.setInheritsPopupMenu(true);
        rSButtonMetro69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro69ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro69);

        rSButtonMetro70.setText("N");
        rSButtonMetro70.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro70.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro70.setInheritsPopupMenu(true);
        rSButtonMetro70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro70ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro70);

        rSButtonMetro71.setText("O");
        rSButtonMetro71.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro71.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro71.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro71.setInheritsPopupMenu(true);
        rSButtonMetro71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro71ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro71);

        rSButtonMetro72.setText("P");
        rSButtonMetro72.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro72.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro72.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro72.setInheritsPopupMenu(true);
        rSButtonMetro72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro72ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro72);

        rSButtonMetro73.setText("Q");
        rSButtonMetro73.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro73.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro73.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro73.setInheritsPopupMenu(true);
        rSButtonMetro73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro73ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro73);

        rSButtonMetro74.setText("R");
        rSButtonMetro74.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro74.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro74.setInheritsPopupMenu(true);
        rSButtonMetro74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro74ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro74);

        rSButtonMetro75.setText("S");
        rSButtonMetro75.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro75.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro75.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro75.setInheritsPopupMenu(true);
        rSButtonMetro75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro75ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro75);

        rSButtonMetro76.setText("T");
        rSButtonMetro76.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro76.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro76.setInheritsPopupMenu(true);
        rSButtonMetro76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro76ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro76);

        rSButtonMetro77.setText("U");
        rSButtonMetro77.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro77.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro77.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro77.setInheritsPopupMenu(true);
        rSButtonMetro77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro77ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro77);

        rSButtonMetro78.setText("V");
        rSButtonMetro78.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro78.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro78.setInheritsPopupMenu(true);
        rSButtonMetro78.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro78ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro78);

        rSButtonMetro79.setText("X");
        rSButtonMetro79.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro79.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro79.setInheritsPopupMenu(true);
        rSButtonMetro79.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro79ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro79);

        rSButtonMetro80.setText("Y");
        rSButtonMetro80.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro80.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro80.setInheritsPopupMenu(true);
        rSButtonMetro80.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro80ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro80);

        rSButtonMetro81.setText("Z");
        rSButtonMetro81.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro81.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro81.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro81.setInheritsPopupMenu(true);
        rSButtonMetro81.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro81ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro81);

        rSButtonMetro82.setText("BIS");
        rSButtonMetro82.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro82.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro82.setInheritsPopupMenu(true);
        rSButtonMetro82.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro82ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro82);

        jPanelMetro.add(jPanelArriba2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 330));

        jLabelNombre6.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelNombre6.setForeground(new java.awt.Color(0, 112, 192));
        jLabelNombre6.setText("Nombre:");

        jTextFieldNombre6.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldNombre6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 112, 192), 2));
        jTextFieldNombre6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombre6ActionPerformed(evt);
            }
        });

        jTextFieldIngreso.setEditable(false);
        jTextFieldIngreso.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldIngreso.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldIngreso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 112, 192), 2));

        rSButtonMetroIngresar.setText("Ingresar Nombre");
        rSButtonMetroIngresar.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        rSButtonMetroIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroIngresarActionPerformed(evt);
            }
        });

        rSButtonMetroBorrar.setText("Borrar Ultimo");
        rSButtonMetroBorrar.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        rSButtonMetroBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroBorrarActionPerformed(evt);
            }
        });

        rSButtonMetroBorrarTodo.setText("Borrar Todo");
        rSButtonMetroBorrarTodo.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        rSButtonMetroBorrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroBorrarTodoActionPerformed(evt);
            }
        });

        rSButtonMetroFinalizar.setText("Finalizar");
        rSButtonMetroFinalizar.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        rSButtonMetroFinalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rSButtonMetroFinalizarMousePressed(evt);
            }
        });
        rSButtonMetroFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFinalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAbajo2Layout = new javax.swing.GroupLayout(jPanelAbajo2);
        jPanelAbajo2.setLayout(jPanelAbajo2Layout);
        jPanelAbajo2Layout.setHorizontalGroup(
            jPanelAbajo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAbajo2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAbajo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldIngreso)
                    .addGroup(jPanelAbajo2Layout.createSequentialGroup()
                        .addComponent(jLabelNombre6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNombre6, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rSButtonMetroIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonMetroBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonMetroBorrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonMetroFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanelAbajo2Layout.setVerticalGroup(
            jPanelAbajo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAbajo2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAbajo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSButtonMetroIngresar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelAbajo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelNombre6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldNombre6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rSButtonMetroBorrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rSButtonMetroBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rSButtonMetroFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanelMetro.add(jPanelAbajo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 800, 80));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanelPpal.setLayout(new java.awt.CardLayout());

        jPanelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTitulo.setBackground(new java.awt.Color(51, 152, 219));
        jPanelTitulo.setOpaque(false);
        jPanelTitulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelPerfil.setBackground(new java.awt.Color(51, 152, 219));
        jPanelPerfil.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Admin Portal");
        jLabelTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanelPerfil.add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 680, -1));

        jPanelTitulo.add(jPanelPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 660, 100));

        jLabelimgPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/admin_256px.png"))); // NOI18N
        jPanelTitulo.add(jLabelimgPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 0, 240, 210));

        jPanelMenu.add(jPanelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 210));

        jPanelRol.setBackground(new java.awt.Color(52, 152, 219));
        jPanelRol.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelRol1.setBackground(new java.awt.Color(52, 152, 219));
        jLabelRol1.setFont(new java.awt.Font("Segoe UI Light", 0, 34)); // NOI18N
        jLabelRol1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRol1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRol1.setText("SGH");
        jPanelRol.add(jLabelRol1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, 80));

        jLabelRol2.setBackground(new java.awt.Color(52, 152, 219));
        jLabelRol2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 34)); // NOI18N
        jLabelRol2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRol2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRol2.setText("Bienvenido al");
        jPanelRol.add(jLabelRol2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 210, 80));

        jPanelMenu.add(jPanelRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 210, 390));

        jPanelCamas.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCamas.setToolTipText("Gestión de camas");
        jPanelCamas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelCamasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelCamasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelCamasMouseExited(evt);
            }
        });
        jPanelCamas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelCamas.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCamas.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabelCamas.setForeground(new java.awt.Color(52, 152, 219));
        jLabelCamas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCamas.setText("Camas");
        jPanelCamas.add(jLabelCamas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 150, 30));

        jLabelimgCamas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/cama_100px.png"))); // NOI18N
        jPanelCamas.add(jLabelimgCamas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 120));

        jPanelMenu.add(jPanelCamas, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, 150, 170));

        jPanelCampanas.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCampanas.setToolTipText("Gestión de campañas");
        jPanelCampanas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelCampanasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelCampanasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelCampanasMouseExited(evt);
            }
        });
        jPanelCampanas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelCampanas.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCampanas.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabelCampanas.setForeground(new java.awt.Color(52, 152, 219));
        jLabelCampanas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCampanas.setText("Campañas");
        jPanelCampanas.add(jLabelCampanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 150, 30));

        jLabelimgCampanas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/campanas_100px.png"))); // NOI18N
        jPanelCampanas.add(jLabelimgCampanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 120));

        jPanelMenu.add(jPanelCampanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 150, 170));

        jPanelUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        jPanelUsuarios.setToolTipText("Gestión de usuarios");
        jPanelUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelUsuariosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelUsuariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelUsuariosMouseExited(evt);
            }
        });
        jPanelUsuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        jLabelUsuarios.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabelUsuarios.setForeground(new java.awt.Color(52, 152, 219));
        jLabelUsuarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUsuarios.setText("Usuarios");
        jPanelUsuarios.add(jLabelUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 150, 30));

        jLabelimgUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/usuarios_100px.png"))); // NOI18N
        jPanelUsuarios.add(jLabelimgUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 120));

        jPanelMenu.add(jPanelUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 220, 150, 170));

        jPanelMedicinas.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMedicinas.setToolTipText("Gestión de medicamentos");
        jPanelMedicinas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelMedicinasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelMedicinasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelMedicinasMouseExited(evt);
            }
        });
        jPanelMedicinas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelMedicinas.setBackground(new java.awt.Color(255, 255, 255));
        jLabelMedicinas.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabelMedicinas.setForeground(new java.awt.Color(52, 152, 219));
        jLabelMedicinas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMedicinas.setText("Medicamentos");
        jPanelMedicinas.add(jLabelMedicinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 150, 30));

        jLabelimgMedicinas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/pastillas_100px.png"))); // NOI18N
        jPanelMedicinas.add(jLabelimgMedicinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 120));

        jPanelMenu.add(jPanelMedicinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 410, 150, 170));

        jPanelAreas.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAreas.setToolTipText("Gestión de areas");
        jPanelAreas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelAreasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelAreasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelAreasMouseExited(evt);
            }
        });
        jPanelAreas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAreas.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAreas.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabelAreas.setForeground(new java.awt.Color(52, 152, 219));
        jLabelAreas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAreas.setText("Areas");
        jPanelAreas.add(jLabelAreas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 150, 30));

        jLabelimgAreas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/areas_100px.png"))); // NOI18N
        jPanelAreas.add(jLabelimgAreas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 120));

        jPanelMenu.add(jPanelAreas, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, -1, 170));

        rSButtonCerrarSesion.setBackground(new java.awt.Color(52, 152, 219));
        rSButtonCerrarSesion.setToolTipText("Cerrar sesión");
        rSButtonCerrarSesion.setColorHover(new java.awt.Color(56, 166, 239));
        rSButtonCerrarSesion.setColorNormal(new java.awt.Color(52, 152, 219));
        rSButtonCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonCerrarSesionMouseClicked(evt);
            }
        });
        rSButtonCerrarSesion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelimgCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/salir_100px.png"))); // NOI18N
        rSButtonCerrarSesion.add(jLabelimgCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 120));

        jPanelMenu.add(rSButtonCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 410, 150, 170));

        jPanelPpal.add(jPanelMenu, "card2");

        jPanelStaff.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTituloUsuario.setBackground(new java.awt.Color(51, 152, 219));
        jPanelTituloUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTituloUsuario.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabelTituloUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloUsuario.setText("GESTIÓN DE USUARIOS");
        jPanelTituloUsuario.add(jLabelTituloUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 520, -1));

        jLabelimgUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/administracionUser_100px.png"))); // NOI18N
        jPanelTituloUsuario.add(jLabelimgUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 110, 90));

        jButtonSlider.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/menu_48px.png"))); // NOI18N
        jButtonSlider.setContentAreaFilled(false);
        jButtonSlider.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSlider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSliderActionPerformed(evt);
            }
        });
        jPanelTituloUsuario.add(jButtonSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, 70));

        jPanelStaff.add(jPanelTituloUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 90));

        jPanelSlider.setBackground(new java.awt.Color(153, 153, 153));
        jPanelSlider.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPaciente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelPaciente.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/paciente_50px.png"))); // NOI18N
        jLabelPaciente.setText("PACIENTES");
        jPanelSlider.add(jLabelPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 50));

        jLabelEmpleado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/tarjetaempleado_50px.png"))); // NOI18N
        jLabelEmpleado.setText("EMPLEADOS");
        jPanelSlider.add(jLabelEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 240, 50));

        jSeparatorPaciente.setForeground(new java.awt.Color(255, 255, 255));
        jSeparatorPaciente.setPreferredSize(new java.awt.Dimension(240, 4));
        jPanelSlider.add(jSeparatorPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));

        jSeparatorEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        jSeparatorEmpleado.setPreferredSize(new java.awt.Dimension(240, 4));
        jPanelSlider.add(jSeparatorEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, -1, -1));

        rSButtonPaneHome.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonPaneHome.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonPaneHome.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonPaneHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonPaneHomeMouseClicked(evt);
            }
        });
        rSButtonPaneHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHome.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelHome.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/home_50px.png"))); // NOI18N
        jLabelHome.setText("INICIO");
        jLabelHome.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, new java.awt.Color(255, 255, 255), null, null));
        jLabelHome.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rSButtonPaneHome.add(jLabelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider.add(rSButtonPaneHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 240, -1));

        rSButtonAgregarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonAgregarPaciente.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonAgregarPaciente.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonAgregarPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonAgregarPacienteMouseClicked(evt);
            }
        });
        rSButtonAgregarPaciente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAgregarPaciente.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAgregarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAgregarPaciente.setText(" Agregar Paciente");
        rSButtonAgregarPaciente.add(jLabelAgregarPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider.add(rSButtonAgregarPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 240, -1));

        rSButtonModificarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonModificarPaciente.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonModificarPaciente.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonModificarPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonModificarPacienteMouseClicked(evt);
            }
        });
        rSButtonModificarPaciente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelModificarPaciente.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelModificarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        jLabelModificarPaciente.setText(" Modificar Paciente");
        rSButtonModificarPaciente.add(jLabelModificarPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider.add(rSButtonModificarPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 240, -1));

        rSButtonAgregarEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonAgregarEmpleado.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonAgregarEmpleado.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonAgregarEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonAgregarEmpleadoMouseClicked(evt);
            }
        });
        rSButtonAgregarEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAgregarEmpleado.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAgregarEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAgregarEmpleado.setText(" Agregar Empleado");
        rSButtonAgregarEmpleado.add(jLabelAgregarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider.add(rSButtonAgregarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 240, -1));

        rSButtonModificarEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonModificarEmpleado.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonModificarEmpleado.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonModificarEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonModificarEmpleadoMouseClicked(evt);
            }
        });
        rSButtonModificarEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelModificarEmpleado.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelModificarEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        jLabelModificarEmpleado.setText(" Modificar Empleado");
        rSButtonModificarEmpleado.add(jLabelModificarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider.add(rSButtonModificarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 240, -1));

        rSButtonAgregarHabilidad.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonAgregarHabilidad.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonAgregarHabilidad.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonAgregarHabilidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonAgregarHabilidadMouseClicked(evt);
            }
        });
        rSButtonAgregarHabilidad.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAgregarHabilidad.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAgregarHabilidad.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAgregarHabilidad.setText(" Agregar Función de Enfermera");
        rSButtonAgregarHabilidad.add(jLabelAgregarHabilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider.add(rSButtonAgregarHabilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 240, -1));

        rSButtonModificarHabilidad.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonModificarHabilidad.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonModificarHabilidad.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonModificarHabilidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonModificarHabilidadMouseClicked(evt);
            }
        });
        rSButtonModificarHabilidad.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelModificarHabilidad.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelModificarHabilidad.setForeground(new java.awt.Color(255, 255, 255));
        jLabelModificarHabilidad.setText(" Modificar Función de Enfermera");
        rSButtonModificarHabilidad.add(jLabelModificarHabilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider.add(rSButtonModificarHabilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 240, -1));

        rSButtonAsignarHabilidad.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonAsignarHabilidad.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonAsignarHabilidad.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonAsignarHabilidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonAsignarHabilidadMouseClicked(evt);
            }
        });
        rSButtonAsignarHabilidad.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAsignarHabilidad.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAsignarHabilidad.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAsignarHabilidad.setText(" Asignar Función de Enfermera");
        rSButtonAsignarHabilidad.add(jLabelAsignarHabilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider.add(rSButtonAsignarHabilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 240, -1));

        jPanelStaff.add(jPanelSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 240, 510));

        jPanelSliders.setLayout(new java.awt.CardLayout());

        jPanelAgregarPaciente.setToolTipText("");
        jPanelAgregarPaciente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNombre1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelNombre1.setForeground(new java.awt.Color(51, 152, 219));
        jLabelNombre1.setText("Nombre");
        jPanelAgregarPaciente.add(jLabelNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabelCedula1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelCedula1.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCedula1.setText("Identificación");
        jPanelAgregarPaciente.add(jLabelCedula1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        jLabelDireccion1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelDireccion1.setForeground(new java.awt.Color(51, 152, 219));
        jLabelDireccion1.setText("Dirección");
        jPanelAgregarPaciente.add(jLabelDireccion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, -1, -1));

        jLabelTelefono1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelTelefono1.setForeground(new java.awt.Color(51, 152, 219));
        jLabelTelefono1.setText("Telefono");
        jPanelAgregarPaciente.add(jLabelTelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jLabelActividad.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelActividad.setForeground(new java.awt.Color(51, 152, 219));
        jLabelActividad.setText("Ocupación");
        jPanelAgregarPaciente.add(jLabelActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, -1, -1));

        jLabelSeguridadSocial.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelSeguridadSocial.setForeground(new java.awt.Color(51, 152, 219));
        jLabelSeguridadSocial.setText("Numero de Seguridad Social");
        jPanelAgregarPaciente.add(jLabelSeguridadSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        jLabelFechaNacimiento.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelFechaNacimiento.setForeground(new java.awt.Color(51, 152, 219));
        jLabelFechaNacimiento.setText("Fecha de Nacimiento");
        jPanelAgregarPaciente.add(jLabelFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 270, -1, -1));

        jTextFieldNombre1.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldNombre1.setText("Digite el nombre del paciente");
        jTextFieldNombre1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldNombre1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNombre1FocusGained(evt);
            }
        });
        jTextFieldNombre1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldNombre1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldNombre1MouseReleased(evt);
            }
        });
        jTextFieldNombre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombre1ActionPerformed(evt);
            }
        });
        jTextFieldNombre1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNombre1KeyTyped(evt);
            }
        });
        jPanelAgregarPaciente.add(jTextFieldNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 180, 30));

        jTextFieldCedula1.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldCedula1.setText("Digite la identificación del paciente");
        jTextFieldCedula1.setToolTipText("");
        jTextFieldCedula1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldCedula1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCedula1FocusGained(evt);
            }
        });
        jTextFieldCedula1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldCedula1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldCedula1MouseReleased(evt);
            }
        });
        jTextFieldCedula1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCedula1ActionPerformed(evt);
            }
        });
        jTextFieldCedula1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCedula1KeyTyped(evt);
            }
        });
        jPanelAgregarPaciente.add(jTextFieldCedula1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 180, 30));

        jTextFieldDireccion1.setEditable(false);
        jTextFieldDireccion1.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldDireccion1.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldDireccion1.setText("Seleccione la dirección");
        jTextFieldDireccion1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldDireccion1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldDireccion1FocusGained(evt);
            }
        });
        jTextFieldDireccion1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldDireccion1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldDireccion1MouseReleased(evt);
            }
        });
        jTextFieldDireccion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDireccion1ActionPerformed(evt);
            }
        });
        jTextFieldDireccion1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldDireccion1KeyTyped(evt);
            }
        });
        jPanelAgregarPaciente.add(jTextFieldDireccion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 180, 30));

        jTextFieldTelefono1.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldTelefono1.setText("Digite el teléfono del paciente");
        jTextFieldTelefono1.setToolTipText("");
        jTextFieldTelefono1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldTelefono1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldTelefono1FocusGained(evt);
            }
        });
        jTextFieldTelefono1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldTelefono1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldTelefono1MouseReleased(evt);
            }
        });
        jTextFieldTelefono1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTelefono1KeyTyped(evt);
            }
        });
        jPanelAgregarPaciente.add(jTextFieldTelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 180, 30));

        jTextFieldActividad.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldActividad.setText("Digite la ocupación del paciente");
        jTextFieldActividad.setToolTipText("");
        jTextFieldActividad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldActividad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldActividadFocusGained(evt);
            }
        });
        jTextFieldActividad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldActividadMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldActividadMouseReleased(evt);
            }
        });
        jTextFieldActividad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldActividadKeyTyped(evt);
            }
        });
        jPanelAgregarPaciente.add(jTextFieldActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 180, 30));

        jTextFieldSeguridad.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldSeguridad.setText("Digite el n° de seguridad social");
        jTextFieldSeguridad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldSeguridad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldSeguridadFocusGained(evt);
            }
        });
        jTextFieldSeguridad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldSeguridadMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldSeguridadMouseReleased(evt);
            }
        });
        jTextFieldSeguridad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldSeguridadKeyTyped(evt);
            }
        });
        jPanelAgregarPaciente.add(jTextFieldSeguridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 180, 30));

        rSButtonMetroGuardar1.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroGuardar1.setText("Guardar");
        rSButtonMetroGuardar1.setToolTipText("Guardar datos ingresados");
        rSButtonMetroGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroGuardar1ActionPerformed(evt);
            }
        });
        jPanelAgregarPaciente.add(rSButtonMetroGuardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 150, 30));

        rSButtonMetroLimpiar1.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar1.setText("Limpiar");
        rSButtonMetroLimpiar1.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar1ActionPerformed(evt);
            }
        });
        jPanelAgregarPaciente.add(rSButtonMetroLimpiar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 150, 30));

        rSDateChooserFechaNacimiento.setColorBackground(new java.awt.Color(51, 152, 219));
        rSDateChooserFechaNacimiento.setPlaceholder("Seleccionar Día");
        jPanelAgregarPaciente.add(rSDateChooserFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 180, -1));

        jPanelSliders.add(jPanelAgregarPaciente, "card2");

        jPanelModificarPaciente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBuscar1.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelBuscar1.setForeground(new java.awt.Color(51, 152, 219));
        jLabelBuscar1.setText("Identificación:");
        jPanelModificarPaciente.add(jLabelBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 30));

        jTextFieldBuscar1.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldBuscar1.setText("Digite la identificación del paciente");
        jTextFieldBuscar1.setToolTipText("");
        jTextFieldBuscar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldBuscar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldBuscar1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldBuscar1MouseReleased(evt);
            }
        });
        jTextFieldBuscar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscar1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBuscar1KeyTyped(evt);
            }
        });
        jPanelModificarPaciente.add(jTextFieldBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 180, 30));

        rSButtonMetroFiltrar1.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroFiltrar1.setText("Buscar");
        rSButtonMetroFiltrar1.setToolTipText("Buscar paciente en el sistema");
        rSButtonMetroFiltrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar1ActionPerformed(evt);
            }
        });
        jPanelModificarPaciente.add(rSButtonMetroFiltrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 110, 30));

        rSTablaPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cedula", "nombre", "ocupacion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        rSTablaPacientes.setCellSelectionEnabled(true);
        rSTablaPacientes.setColorBackgoundHead(new java.awt.Color(52, 120, 219));
        rSTablaPacientes.setColorFilasForeground1(new java.awt.Color(52, 152, 219));
        rSTablaPacientes.setColorFilasForeground2(new java.awt.Color(52, 152, 219));
        rSTablaPacientes.setColorSelBackgound(new java.awt.Color(52, 152, 219));
        rSTablaPacientes.setRowHeight(25);
        rSTablaPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSTablaPacientesMouseClicked(evt);
            }
        });
        jScrollPaneTablaPacientes.setViewportView(rSTablaPacientes);

        jPanelModificarPaciente.add(jScrollPaneTablaPacientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 620, 380));

        rSButtonMetroLimpiar12.setBackground(new java.awt.Color(255, 200, 23));
        rSButtonMetroLimpiar12.setText("Limpiar");
        rSButtonMetroLimpiar12.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar12ActionPerformed(evt);
            }
        });
        jPanelModificarPaciente.add(rSButtonMetroLimpiar12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 445, 110, 30));

        rSButtonMetroFiltrar7.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroFiltrar7.setText("Mostrar todo");
        rSButtonMetroFiltrar7.setToolTipText("Mostrar todos los pacientes del sistema");
        rSButtonMetroFiltrar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar7ActionPerformed(evt);
            }
        });
        jPanelModificarPaciente.add(rSButtonMetroFiltrar7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 110, 30));

        jPanelSliders.add(jPanelModificarPaciente, "card2");

        jPanelAgregarEmpleado.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelAgregarEmpleadoComponentShown(evt);
            }
        });
        jPanelAgregarEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNombre.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelNombre.setForeground(new java.awt.Color(51, 152, 219));
        jLabelNombre.setText("Nombre");
        jPanelAgregarEmpleado.add(jLabelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabelCedula.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelCedula.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCedula.setText("Identificación");
        jPanelAgregarEmpleado.add(jLabelCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        jLabelDireccion.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelDireccion.setForeground(new java.awt.Color(51, 152, 219));
        jLabelDireccion.setText("Dirección");
        jPanelAgregarEmpleado.add(jLabelDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, -1, -1));

        jLabelTelefono.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelTelefono.setForeground(new java.awt.Color(51, 152, 219));
        jLabelTelefono.setText("Telefono");
        jPanelAgregarEmpleado.add(jLabelTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jLabelSalario.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelSalario.setForeground(new java.awt.Color(51, 152, 219));
        jLabelSalario.setText("Salario");
        jPanelAgregarEmpleado.add(jLabelSalario, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, -1, -1));

        jLabelEmail.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelEmail.setForeground(new java.awt.Color(51, 152, 219));
        jLabelEmail.setText("Email");
        jPanelAgregarEmpleado.add(jLabelEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, -1, -1));

        jLabelJefe.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelJefe.setForeground(new java.awt.Color(51, 152, 219));
        jLabelJefe.setText("Identificación del Jefe");
        jPanelAgregarEmpleado.add(jLabelJefe, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        jLabelCargo.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelCargo.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCargo.setText("Ocupación");
        jPanelAgregarEmpleado.add(jLabelCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, -1, -1));

        jLabelArea.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelArea.setForeground(new java.awt.Color(51, 152, 219));
        jLabelArea.setText("Area");
        jPanelAgregarEmpleado.add(jLabelArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 270, -1, -1));

        jTextFieldNombre.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldNombre.setText("Digite el nombre del empleado");
        jTextFieldNombre.setToolTipText("");
        jTextFieldNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNombreFocusGained(evt);
            }
        });
        jTextFieldNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldNombreMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldNombreMouseReleased(evt);
            }
        });
        jTextFieldNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNombreKeyTyped(evt);
            }
        });
        jPanelAgregarEmpleado.add(jTextFieldNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 180, 30));

        jTextFieldCedula.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldCedula.setText("Digite la identificación del empleado");
        jTextFieldCedula.setToolTipText("");
        jTextFieldCedula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldCedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCedulaFocusGained(evt);
            }
        });
        jTextFieldCedula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldCedulaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldCedulaMouseReleased(evt);
            }
        });
        jTextFieldCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCedulaKeyTyped(evt);
            }
        });
        jPanelAgregarEmpleado.add(jTextFieldCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 180, 30));

        jTextFieldDireccion.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldDireccion.setText("Seleccione la dirección");
        jTextFieldDireccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldDireccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldDireccionMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldDireccionMouseReleased(evt);
            }
        });
        jTextFieldDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldDireccionKeyTyped(evt);
            }
        });
        jPanelAgregarEmpleado.add(jTextFieldDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 180, 30));

        jTextFieldTelefono.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldTelefono.setText("Digite el teléfono del paciente");
        jTextFieldTelefono.setToolTipText("");
        jTextFieldTelefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldTelefonoFocusGained(evt);
            }
        });
        jTextFieldTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldTelefonoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldTelefonoMouseReleased(evt);
            }
        });
        jTextFieldTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTelefonoKeyTyped(evt);
            }
        });
        jPanelAgregarEmpleado.add(jTextFieldTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 180, 30));

        jTextFieldSalario.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldSalario.setText("Digite el salario del empleado");
        jTextFieldSalario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldSalario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldSalarioFocusGained(evt);
            }
        });
        jTextFieldSalario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldSalarioMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldSalarioMouseReleased(evt);
            }
        });
        jTextFieldSalario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldSalarioKeyTyped(evt);
            }
        });
        jPanelAgregarEmpleado.add(jTextFieldSalario, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 180, 30));

        jTextFieldEmail.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldEmail.setText("Digite el email del empleado");
        jTextFieldEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldEmailFocusGained(evt);
            }
        });
        jTextFieldEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldEmailMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldEmailMouseReleased(evt);
            }
        });
        jTextFieldEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldEmailKeyTyped(evt);
            }
        });
        jPanelAgregarEmpleado.add(jTextFieldEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 180, 30));

        jTextFieldJefe.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldJefe.setText("Digite la identificación del jefe ");
        jTextFieldJefe.setToolTipText("");
        jTextFieldJefe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldJefe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldJefeFocusGained(evt);
            }
        });
        jTextFieldJefe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldJefeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldJefeMouseReleased(evt);
            }
        });
        jTextFieldJefe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldJefeKeyTyped(evt);
            }
        });
        jPanelAgregarEmpleado.add(jTextFieldJefe, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 180, 30));

        rSComboMetroCargo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Medico", "Enfermera" }));
        rSComboMetroCargo.setColorBorde(new java.awt.Color(51, 152, 219));
        rSComboMetroCargo.setColorFondo(new java.awt.Color(51, 152, 219));
        jPanelAgregarEmpleado.add(rSComboMetroCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 180, -1));

        rSComboMetroArea.setColorBorde(new java.awt.Color(51, 152, 219));
        rSComboMetroArea.setColorFondo(new java.awt.Color(51, 152, 219));
        jPanelAgregarEmpleado.add(rSComboMetroArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 180, -1));

        rSButtonMetroGuardar.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroGuardar.setText("Guardar");
        rSButtonMetroGuardar.setToolTipText("Guardar datos ingresados");
        rSButtonMetroGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroGuardarActionPerformed(evt);
            }
        });
        jPanelAgregarEmpleado.add(rSButtonMetroGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 150, 30));

        rSButtonMetroLimpiar.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar.setText("Limpiar");
        rSButtonMetroLimpiar.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiarActionPerformed(evt);
            }
        });
        jPanelAgregarEmpleado.add(rSButtonMetroLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 150, 30));

        jPanelSliders.add(jPanelAgregarEmpleado, "card2");

        jPanelModificarEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBuscar.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelBuscar.setForeground(new java.awt.Color(51, 152, 219));
        jLabelBuscar.setText("Identificación:");
        jPanelModificarEmpleado.add(jLabelBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 30));

        jTextFieldBuscar.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldBuscar.setText("Digite la identificación del empleado");
        jTextFieldBuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldBuscarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldBuscarMouseReleased(evt);
            }
        });
        jTextFieldBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarKeyTyped(evt);
            }
        });
        jPanelModificarEmpleado.add(jTextFieldBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 180, 30));

        rSButtonMetroFiltrar.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroFiltrar.setText("Buscar");
        rSButtonMetroFiltrar.setToolTipText("Buscar empleado en el sistema");
        rSButtonMetroFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrarActionPerformed(evt);
            }
        });
        jPanelModificarEmpleado.add(rSButtonMetroFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 110, 30));

        rSTablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cedula", "nombre", "cargo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        rSTablaEmpleados.setColorBackgoundHead(new java.awt.Color(52, 120, 219));
        rSTablaEmpleados.setColorFilasForeground1(new java.awt.Color(52, 152, 219));
        rSTablaEmpleados.setColorFilasForeground2(new java.awt.Color(52, 152, 219));
        rSTablaEmpleados.setColorSelBackgound(new java.awt.Color(52, 152, 219));
        rSTablaEmpleados.setRowHeight(25);
        rSTablaEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSTablaEmpleadosMouseClicked(evt);
            }
        });
        jScrollPaneTablaEmpleados.setViewportView(rSTablaEmpleados);

        jPanelModificarEmpleado.add(jScrollPaneTablaEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 620, 380));

        rSButtonMetroLimpiar11.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar11.setText("Limpiar");
        rSButtonMetroLimpiar11.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar11ActionPerformed(evt);
            }
        });
        jPanelModificarEmpleado.add(rSButtonMetroLimpiar11, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 445, 110, 30));

        rSButtonMetroFiltrar8.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroFiltrar8.setText("Mostrar todo");
        rSButtonMetroFiltrar8.setToolTipText("Mostrar todos los empleados del sistema");
        rSButtonMetroFiltrar8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar8ActionPerformed(evt);
            }
        });
        jPanelModificarEmpleado.add(rSButtonMetroFiltrar8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 110, 30));

        jPanelSliders.add(jPanelModificarEmpleado, "card2");

        jPanelAgregarHabilidad.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNombre4.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelNombre4.setForeground(new java.awt.Color(51, 152, 219));
        jLabelNombre4.setText("Nombre");
        jPanelAgregarHabilidad.add(jLabelNombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, -1, -1));

        jLabelCodigo2.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelCodigo2.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCodigo2.setText("Codigo");
        jPanelAgregarHabilidad.add(jLabelCodigo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, -1, -1));

        jTextFieldNombre4.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldNombre4.setText("Ingrese el nombre de la función");
        jTextFieldNombre4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldNombre4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNombre4FocusGained(evt);
            }
        });
        jTextFieldNombre4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldNombre4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldNombre4MouseReleased(evt);
            }
        });
        jTextFieldNombre4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombre4ActionPerformed(evt);
            }
        });
        jTextFieldNombre4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNombre4KeyTyped(evt);
            }
        });
        jPanelAgregarHabilidad.add(jTextFieldNombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 180, 30));

        jTextFieldCodigo2.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldCodigo2.setText("Ingrese el codigo de la función");
        jTextFieldCodigo2.setToolTipText("");
        jTextFieldCodigo2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldCodigo2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCodigo2FocusGained(evt);
            }
        });
        jTextFieldCodigo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldCodigo2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldCodigo2MouseReleased(evt);
            }
        });
        jTextFieldCodigo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCodigo2KeyTyped(evt);
            }
        });
        jPanelAgregarHabilidad.add(jTextFieldCodigo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 180, 30));

        rSButtonMetroGuardar4.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroGuardar4.setText("Guardar");
        rSButtonMetroGuardar4.setToolTipText("Guardar datos ingresados");
        rSButtonMetroGuardar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroGuardar4ActionPerformed(evt);
            }
        });
        jPanelAgregarHabilidad.add(rSButtonMetroGuardar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 150, 30));

        rSButtonMetroLimpiar4.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar4.setText("Limpiar");
        rSButtonMetroLimpiar4.setToolTipText("Limpiar datos ingresados ");
        rSButtonMetroLimpiar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar4ActionPerformed(evt);
            }
        });
        jPanelAgregarHabilidad.add(rSButtonMetroLimpiar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 150, 30));

        jPanelSliders.add(jPanelAgregarHabilidad, "card2");

        jPanelModificarHabilidad.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBuscar3.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelBuscar3.setForeground(new java.awt.Color(51, 152, 219));
        jLabelBuscar3.setText("Codigo:");
        jPanelModificarHabilidad.add(jLabelBuscar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, 30));

        jTextFieldBuscar3.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldBuscar3.setText("Ingrese el codigo de la función");
        jTextFieldBuscar3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldBuscar3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldBuscar3FocusGained(evt);
            }
        });
        jTextFieldBuscar3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldBuscar3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldBuscar3MouseReleased(evt);
            }
        });
        jTextFieldBuscar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscar3ActionPerformed(evt);
            }
        });
        jTextFieldBuscar3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBuscar3KeyTyped(evt);
            }
        });
        jPanelModificarHabilidad.add(jTextFieldBuscar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 180, 30));

        rSButtonMetroLimpiar13.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar13.setText("Limpiar");
        rSButtonMetroLimpiar13.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar13ActionPerformed(evt);
            }
        });
        jPanelModificarHabilidad.add(rSButtonMetroLimpiar13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 445, 110, 30));

        rSButtonMetroFiltrar3.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroFiltrar3.setText("Buscar");
        rSButtonMetroFiltrar3.setToolTipText("Buscar función en el sistema");
        rSButtonMetroFiltrar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar3ActionPerformed(evt);
            }
        });
        jPanelModificarHabilidad.add(rSButtonMetroFiltrar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 110, 30));

        rSTablaHabilidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo", "nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        rSTablaHabilidades.setColorBackgoundHead(new java.awt.Color(52, 120, 219));
        rSTablaHabilidades.setColorFilasForeground1(new java.awt.Color(52, 152, 219));
        rSTablaHabilidades.setColorFilasForeground2(new java.awt.Color(52, 152, 219));
        rSTablaHabilidades.setColorSelBackgound(new java.awt.Color(52, 152, 219));
        rSTablaHabilidades.setRowHeight(25);
        rSTablaHabilidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSTablaHabilidadesMouseClicked(evt);
            }
        });
        jScrollPaneTablaHabilidades.setViewportView(rSTablaHabilidades);

        jPanelModificarHabilidad.add(jScrollPaneTablaHabilidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 620, 380));

        rSButtonMetroFiltrar9.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroFiltrar9.setText("Mostrar todo");
        rSButtonMetroFiltrar9.setToolTipText("Mostrar todos las funciones del sistema");
        rSButtonMetroFiltrar9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar9ActionPerformed(evt);
            }
        });
        jPanelModificarHabilidad.add(rSButtonMetroFiltrar9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 110, 30));

        jPanelSliders.add(jPanelModificarHabilidad, "card2");

        jPanelAsignarHabilidad.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelAsignarHabilidadComponentShown(evt);
            }
        });
        jPanelAsignarHabilidad.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelListaEnfermeras.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelListaEnfermeras.setForeground(new java.awt.Color(51, 152, 219));
        jLabelListaEnfermeras.setText("Lista de Enfermeras");
        jPanelAsignarHabilidad.add(jLabelListaEnfermeras, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));

        jLabelListaHabilidades.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelListaHabilidades.setForeground(new java.awt.Color(51, 152, 219));
        jLabelListaHabilidades.setText("Lista de Funciones");
        jPanelAsignarHabilidad.add(jLabelListaHabilidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, -1));

        rSComboMetroListaEnfermeras.setColorBorde(new java.awt.Color(51, 152, 219));
        rSComboMetroListaEnfermeras.setColorFondo(new java.awt.Color(51, 152, 219));
        rSComboMetroListaEnfermeras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSComboMetroListaEnfermerasActionPerformed(evt);
            }
        });
        jPanelAsignarHabilidad.add(rSComboMetroListaEnfermeras, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 180, -1));

        rSComboMetroListaHabilidades.setColorBorde(new java.awt.Color(51, 152, 219));
        rSComboMetroListaHabilidades.setColorFondo(new java.awt.Color(51, 152, 219));
        jPanelAsignarHabilidad.add(rSComboMetroListaHabilidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 180, -1));

        rSButtonMetroLimpiar3.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar3.setText("Limpiar");
        rSButtonMetroLimpiar3.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar3ActionPerformed(evt);
            }
        });
        jPanelAsignarHabilidad.add(rSButtonMetroLimpiar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 410, 150, 30));

        rSButtonAsignar.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonAsignar.setText("Enlazar");
        rSButtonAsignar.setToolTipText("Asignar una función a una enfermera");
        rSButtonAsignar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        rSButtonAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonAsignarActionPerformed(evt);
            }
        });
        jPanelAsignarHabilidad.add(rSButtonAsignar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 70, 70));

        jPanelSliders.add(jPanelAsignarHabilidad, "card2");

        jPanelStaff.add(jPanelSliders, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 660, 510));

        jPanelPpal.add(jPanelStaff, "card4");

        jPanelMedicians.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTituloMedicina.setBackground(new java.awt.Color(51, 152, 219));
        jPanelTituloMedicina.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTituloMedicina.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabelTituloMedicina.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloMedicina.setText("GESTIÓN DE MEDICAMENTOS");
        jPanelTituloMedicina.add(jLabelTituloMedicina, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 520, -1));

        jLabelimgMedicina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/maletinMedico_100px.png"))); // NOI18N
        jPanelTituloMedicina.add(jLabelimgMedicina, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 110, 90));

        jButtonSlider1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/menu_48px.png"))); // NOI18N
        jButtonSlider1.setContentAreaFilled(false);
        jButtonSlider1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSlider1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSlider1ActionPerformed(evt);
            }
        });
        jPanelTituloMedicina.add(jButtonSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, 70));

        jPanelMedicians.add(jPanelTituloMedicina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 90));

        jPanelSlider1.setBackground(new java.awt.Color(153, 153, 153));
        jPanelSlider1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelMedicina.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelMedicina.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMedicina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/jeringa_50px.png"))); // NOI18N
        jLabelMedicina.setText("MEDICAMENTO");
        jPanelSlider1.add(jLabelMedicina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 50));

        jSeparatorMedicina.setForeground(new java.awt.Color(255, 255, 255));
        jSeparatorMedicina.setPreferredSize(new java.awt.Dimension(240, 4));
        jPanelSlider1.add(jSeparatorMedicina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));

        rSButtonPaneHome1.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonPaneHome1.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonPaneHome1.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonPaneHome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonPaneHome1MouseClicked(evt);
            }
        });
        rSButtonPaneHome1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHome1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelHome1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHome1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/home_50px.png"))); // NOI18N
        jLabelHome1.setText("INICIO");
        jLabelHome1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, new java.awt.Color(255, 255, 255), null, null));
        jLabelHome1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rSButtonPaneHome1.add(jLabelHome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider1.add(rSButtonPaneHome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 240, -1));

        rSButtonAgregarMedicina.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonAgregarMedicina.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonAgregarMedicina.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonAgregarMedicina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonAgregarMedicinaMouseClicked(evt);
            }
        });
        rSButtonAgregarMedicina.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAgregarMedicina.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAgregarMedicina.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAgregarMedicina.setText(" Agregar Medicamento");
        rSButtonAgregarMedicina.add(jLabelAgregarMedicina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider1.add(rSButtonAgregarMedicina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 240, -1));

        rSButtonModificarMedicina.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonModificarMedicina.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonModificarMedicina.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonModificarMedicina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonModificarMedicinaMouseClicked(evt);
            }
        });
        rSButtonModificarMedicina.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelModificarMedicina.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelModificarMedicina.setForeground(new java.awt.Color(255, 255, 255));
        jLabelModificarMedicina.setText(" Modificar Medicamento");
        rSButtonModificarMedicina.add(jLabelModificarMedicina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider1.add(rSButtonModificarMedicina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 240, -1));

        jPanelMedicians.add(jPanelSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 240, 510));

        jPanelSliders1.setLayout(new java.awt.CardLayout());

        jPanelAgregarMedicina.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNombre2.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelNombre2.setForeground(new java.awt.Color(51, 152, 219));
        jLabelNombre2.setText("Nombre");
        jPanelAgregarMedicina.add(jLabelNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabelCodigo.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelCodigo.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCodigo.setText("Codigo");
        jPanelAgregarMedicina.add(jLabelCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        jLabelCosto.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelCosto.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCosto.setText("Costo");
        jPanelAgregarMedicina.add(jLabelCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, -1, -1));

        jLabelDescripcion.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelDescripcion.setForeground(new java.awt.Color(51, 152, 219));
        jLabelDescripcion.setText("Descripción");
        jPanelAgregarMedicina.add(jLabelDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jTextFieldNombre2.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldNombre2.setText("Digite el nombre del medicamento");
        jTextFieldNombre2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldNombre2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNombre2FocusGained(evt);
            }
        });
        jTextFieldNombre2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldNombre2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldNombre2MouseReleased(evt);
            }
        });
        jTextFieldNombre2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNombre2KeyTyped(evt);
            }
        });
        jPanelAgregarMedicina.add(jTextFieldNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 180, 30));

        jTextFieldCodigo.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldCodigo.setText("Digite el codigo del medicamento");
        jTextFieldCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCodigoFocusGained(evt);
            }
        });
        jTextFieldCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldCodigoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldCodigoMouseReleased(evt);
            }
        });
        jTextFieldCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCodigoKeyTyped(evt);
            }
        });
        jPanelAgregarMedicina.add(jTextFieldCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 180, 30));

        jTextFieldCosto.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldCosto.setText("Digite el costo del medicamento");
        jTextFieldCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldCosto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCostoFocusGained(evt);
            }
        });
        jPanelAgregarMedicina.add(jTextFieldCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 180, 30));

        rSButtonMetroGuardar2.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroGuardar2.setText("Guardar");
        rSButtonMetroGuardar2.setToolTipText("Guardar datos ingresados");
        rSButtonMetroGuardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroGuardar2ActionPerformed(evt);
            }
        });
        jPanelAgregarMedicina.add(rSButtonMetroGuardar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, 150, 30));

        rSButtonMetroLimpiar2.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar2.setText("Limpiar");
        rSButtonMetroLimpiar2.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar2ActionPerformed(evt);
            }
        });
        jPanelAgregarMedicina.add(rSButtonMetroLimpiar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 420, 150, 30));

        jTextAreaDescripcionMedicina.setColumns(20);
        jTextAreaDescripcionMedicina.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextAreaDescripcionMedicina.setForeground(new java.awt.Color(51, 152, 219));
        jTextAreaDescripcionMedicina.setLineWrap(true);
        jTextAreaDescripcionMedicina.setRows(5);
        jTextAreaDescripcionMedicina.setText("Ingrese una breve descripción del medicamento");
        jTextAreaDescripcionMedicina.setToolTipText("");
        jTextAreaDescripcionMedicina.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jScrollPane1.setViewportView(jTextAreaDescripcionMedicina);

        jPanelAgregarMedicina.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 600, 170));

        jPanelSliders1.add(jPanelAgregarMedicina, "card2");

        jPanelModificarMedicina.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBuscar2.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelBuscar2.setForeground(new java.awt.Color(51, 152, 219));
        jLabelBuscar2.setText("Codigo:");
        jPanelModificarMedicina.add(jLabelBuscar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, 30));

        jTextFieldBuscar2.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldBuscar2.setText("Digite el codigo del medicamento");
        jTextFieldBuscar2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldBuscar2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldBuscar2FocusGained(evt);
            }
        });
        jTextFieldBuscar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldBuscar2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldBuscar2MouseReleased(evt);
            }
        });
        jTextFieldBuscar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBuscar2KeyTyped(evt);
            }
        });
        jPanelModificarMedicina.add(jTextFieldBuscar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 180, 30));

        rSButtonMetroMostrar2.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroMostrar2.setText("Limpiar");
        rSButtonMetroMostrar2.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroMostrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroMostrar2ActionPerformed(evt);
            }
        });
        jPanelModificarMedicina.add(rSButtonMetroMostrar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 460, 110, 30));

        rSButtonMetroFiltrar2.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroFiltrar2.setText("Buscar");
        rSButtonMetroFiltrar2.setToolTipText("Buscar medicamento en el sistema");
        rSButtonMetroFiltrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar2ActionPerformed(evt);
            }
        });
        jPanelModificarMedicina.add(rSButtonMetroFiltrar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 110, 30));

        rSTablaMedicamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo", "nombre", "costo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        rSTablaMedicamentos.setColorBackgoundHead(new java.awt.Color(52, 120, 219));
        rSTablaMedicamentos.setColorFilasForeground1(new java.awt.Color(52, 152, 219));
        rSTablaMedicamentos.setColorFilasForeground2(new java.awt.Color(52, 152, 219));
        rSTablaMedicamentos.setColorSelBackgound(new java.awt.Color(52, 152, 219));
        rSTablaMedicamentos.setRowHeight(25);
        rSTablaMedicamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSTablaMedicamentosMouseClicked(evt);
            }
        });
        jScrollPaneTablaMedicamentos.setViewportView(rSTablaMedicamentos);

        jPanelModificarMedicina.add(jScrollPaneTablaMedicamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 620, 380));

        rSButtonMetroFiltrar10.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroFiltrar10.setText("Mostrar todo");
        rSButtonMetroFiltrar10.setToolTipText("Mostrar todos los medicamentos del sistema");
        rSButtonMetroFiltrar10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar10ActionPerformed(evt);
            }
        });
        jPanelModificarMedicina.add(rSButtonMetroFiltrar10, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 110, 30));

        jPanelSliders1.add(jPanelModificarMedicina, "card2");

        jPanelMedicians.add(jPanelSliders1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 660, 510));

        jPanelPpal.add(jPanelMedicians, "card4");

        jPanelCampaing.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTituloCampaing.setBackground(new java.awt.Color(51, 152, 219));
        jPanelTituloCampaing.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTituloCampaing.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabelTituloCampaing.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloCampaing.setText("GESTIÓN DE CAMPAÑAS");
        jPanelTituloCampaing.add(jLabelTituloCampaing, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 520, -1));

        jLabelimgMedicinaCampaing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/campana_100px.png"))); // NOI18N
        jPanelTituloCampaing.add(jLabelimgMedicinaCampaing, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 110, 90));

        jButtonSlider2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/menu_48px.png"))); // NOI18N
        jButtonSlider2.setContentAreaFilled(false);
        jButtonSlider2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSlider2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSlider2ActionPerformed(evt);
            }
        });
        jPanelTituloCampaing.add(jButtonSlider2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, 70));

        jPanelCampaing.add(jPanelTituloCampaing, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 90));

        jPanelSlider2.setBackground(new java.awt.Color(153, 153, 153));
        jPanelSlider2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelCampaing.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelCampaing.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCampaing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/planTratamiento_50px.png"))); // NOI18N
        jLabelCampaing.setText("CAMPAÑA");
        jPanelSlider2.add(jLabelCampaing, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 50));

        jSeparatorCampaing.setForeground(new java.awt.Color(255, 255, 255));
        jSeparatorCampaing.setPreferredSize(new java.awt.Dimension(240, 4));
        jPanelSlider2.add(jSeparatorCampaing, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));

        rSButtonPaneHome2.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonPaneHome2.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonPaneHome2.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonPaneHome2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonPaneHome2MouseClicked(evt);
            }
        });
        rSButtonPaneHome2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHome2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelHome2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHome2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/home_50px.png"))); // NOI18N
        jLabelHome2.setText("INICIO");
        jLabelHome2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, new java.awt.Color(255, 255, 255), null, null));
        jLabelHome2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rSButtonPaneHome2.add(jLabelHome2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider2.add(rSButtonPaneHome2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 240, -1));

        rSButtonAgregarCampana.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonAgregarCampana.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonAgregarCampana.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonAgregarCampana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonAgregarCampanaMouseClicked(evt);
            }
        });
        rSButtonAgregarCampana.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAgregarCampana.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAgregarCampana.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAgregarCampana.setText(" Agregar Campaña");
        rSButtonAgregarCampana.add(jLabelAgregarCampana, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider2.add(rSButtonAgregarCampana, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 240, -1));

        rSButtonModificarCampana.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonModificarCampana.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonModificarCampana.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonModificarCampana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonModificarCampanaMouseClicked(evt);
            }
        });
        rSButtonModificarCampana.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelModificarCampana.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelModificarCampana.setForeground(new java.awt.Color(255, 255, 255));
        jLabelModificarCampana.setText(" Modificar Campaña");
        rSButtonModificarCampana.add(jLabelModificarCampana, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider2.add(rSButtonModificarCampana, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 240, -1));

        rSButtonAsignarCampana.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonAsignarCampana.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonAsignarCampana.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonAsignarCampana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonAsignarCampanaMouseClicked(evt);
            }
        });
        rSButtonAsignarCampana.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAsignarCampana.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAsignarCampana.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAsignarCampana.setText(" Agregar Pacientes a Campaña");
        rSButtonAsignarCampana.add(jLabelAsignarCampana, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider2.add(rSButtonAsignarCampana, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 240, -1));

        jPanelCampaing.add(jPanelSlider2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 240, 510));

        jPanelSliders2.setLayout(new java.awt.CardLayout());

        jPanelAgregarCampana.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelAgregarCampanaComponentShown(evt);
            }
        });
        jPanelAgregarCampana.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNombre3.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelNombre3.setForeground(new java.awt.Color(51, 152, 219));
        jLabelNombre3.setText("Nombre");
        jPanelAgregarCampana.add(jLabelNombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabelCodigo3.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelCodigo3.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCodigo3.setText("Codigo");
        jPanelAgregarCampana.add(jLabelCodigo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, -1, -1));

        jLabelFecha.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelFecha.setForeground(new java.awt.Color(51, 152, 219));
        jLabelFecha.setText("Fecha");
        jPanelAgregarCampana.add(jLabelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, -1, -1));

        jLabelDoctorEncargado.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelDoctorEncargado.setForeground(new java.awt.Color(51, 152, 219));
        jLabelDoctorEncargado.setText("Medico encargado");
        jPanelAgregarCampana.add(jLabelDoctorEncargado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, -1));

        jLabelObjetivo.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelObjetivo.setForeground(new java.awt.Color(51, 152, 219));
        jLabelObjetivo.setText("Objetivo");
        jPanelAgregarCampana.add(jLabelObjetivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, -1, -1));

        jTextFieldNombre3.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldNombre3.setText("Digite el nombre de la campaña");
        jTextFieldNombre3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldNombre3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNombre3FocusGained(evt);
            }
        });
        jTextFieldNombre3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldNombre3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldNombre3MouseReleased(evt);
            }
        });
        jTextFieldNombre3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNombre3KeyTyped(evt);
            }
        });
        jPanelAgregarCampana.add(jTextFieldNombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 180, 30));

        jTextFieldCodigo3.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldCodigo3.setText("Digite el codigo de la campaña");
        jTextFieldCodigo3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldCodigo3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCodigo3FocusGained(evt);
            }
        });
        jTextFieldCodigo3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldCodigo3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldCodigo3MouseReleased(evt);
            }
        });
        jTextFieldCodigo3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCodigo3KeyTyped(evt);
            }
        });
        jPanelAgregarCampana.add(jTextFieldCodigo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 180, 30));

        rSDateChooserFecha.setColorBackground(new java.awt.Color(51, 152, 219));
        rSDateChooserFecha.setPlaceholder("Seleccionar Día");
        jPanelAgregarCampana.add(rSDateChooserFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 180, 30));

        rSComboMetroDoctorEncargado.setColorFondo(new java.awt.Color(32, 131, 255));
        jPanelAgregarCampana.add(rSComboMetroDoctorEncargado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 180, -1));

        rSComboMetroObjetivo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Promocion", "Prevencion", "Intervencion" }));
        rSComboMetroObjetivo.setColorFondo(new java.awt.Color(32, 131, 255));
        jPanelAgregarCampana.add(rSComboMetroObjetivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, 180, -1));

        rSButtonMetroGuardar3.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroGuardar3.setText("Guardar");
        rSButtonMetroGuardar3.setToolTipText("Guardar datos ingresados");
        rSButtonMetroGuardar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroGuardar3ActionPerformed(evt);
            }
        });
        jPanelAgregarCampana.add(rSButtonMetroGuardar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 150, 30));

        rSButtonMetroLimpiar5.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar5.setText("Limpiar");
        rSButtonMetroLimpiar5.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar5ActionPerformed(evt);
            }
        });
        jPanelAgregarCampana.add(rSButtonMetroLimpiar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 150, 30));

        jPanelSliders2.add(jPanelAgregarCampana, "card2");

        jPanelModificarCampana.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBuscar4.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelBuscar4.setForeground(new java.awt.Color(51, 152, 219));
        jLabelBuscar4.setText("Codigo:");
        jPanelModificarCampana.add(jLabelBuscar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, 30));

        jTextFieldBuscar4.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldBuscar4.setText("Digite el codigo de la campaña");
        jTextFieldBuscar4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldBuscar4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldBuscar4FocusGained(evt);
            }
        });
        jTextFieldBuscar4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldBuscar4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldBuscar4MouseReleased(evt);
            }
        });
        jTextFieldBuscar4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBuscar4KeyTyped(evt);
            }
        });
        jPanelModificarCampana.add(jTextFieldBuscar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 180, 30));

        rSButtonMetroMostrar4.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroMostrar4.setText("Limpiar");
        rSButtonMetroMostrar4.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroMostrar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroMostrar4ActionPerformed(evt);
            }
        });
        jPanelModificarCampana.add(rSButtonMetroMostrar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 445, 110, 30));

        rSButtonMetroFiltrar4.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroFiltrar4.setText("Buscar");
        rSButtonMetroFiltrar4.setToolTipText("Buscar campaña en el sistema");
        rSButtonMetroFiltrar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar4ActionPerformed(evt);
            }
        });
        jPanelModificarCampana.add(rSButtonMetroFiltrar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 110, 30));

        rSTablaCampana.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo", "nombre", "objetivo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        rSTablaCampana.setColorBackgoundHead(new java.awt.Color(52, 120, 219));
        rSTablaCampana.setColorFilasForeground1(new java.awt.Color(52, 152, 219));
        rSTablaCampana.setColorFilasForeground2(new java.awt.Color(52, 152, 219));
        rSTablaCampana.setColorSelBackgound(new java.awt.Color(52, 152, 219));
        rSTablaCampana.setRowHeight(25);
        rSTablaCampana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSTablaCampanaMouseClicked(evt);
            }
        });
        jScrollPaneTablaCampana.setViewportView(rSTablaCampana);

        jPanelModificarCampana.add(jScrollPaneTablaCampana, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 620, 380));

        rSButtonMetroFiltrar11.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroFiltrar11.setText("Mostrar todo");
        rSButtonMetroFiltrar11.setToolTipText("Mostrar todas las campañas del sistema");
        rSButtonMetroFiltrar11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar11ActionPerformed(evt);
            }
        });
        jPanelModificarCampana.add(rSButtonMetroFiltrar11, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 110, 30));

        jPanelSliders2.add(jPanelModificarCampana, "card2");

        jPanelAsignarCampana.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelAsignarCampanaComponentShown(evt);
            }
        });
        jPanelAsignarCampana.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelListaCampanas.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelListaCampanas.setForeground(new java.awt.Color(51, 152, 219));
        jLabelListaCampanas.setText("Lista de Campañas");
        jPanelAsignarCampana.add(jLabelListaCampanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));

        jLabelListaPacientes.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelListaPacientes.setForeground(new java.awt.Color(51, 152, 219));
        jLabelListaPacientes.setText("Lista de Pacientes");
        jPanelAsignarCampana.add(jLabelListaPacientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, -1));

        rSComboMetroListaCampanas.setColorBorde(new java.awt.Color(51, 152, 219));
        rSComboMetroListaCampanas.setColorFondo(new java.awt.Color(51, 152, 219));
        jPanelAsignarCampana.add(rSComboMetroListaCampanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 180, -1));

        rSComboMetroListaPacientes.setColorBorde(new java.awt.Color(51, 152, 219));
        rSComboMetroListaPacientes.setColorFondo(new java.awt.Color(51, 152, 219));
        jPanelAsignarCampana.add(rSComboMetroListaPacientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 180, -1));

        rSButtonMetroLimpiar6.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar6.setText("Limpiar");
        rSButtonMetroLimpiar6.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar6ActionPerformed(evt);
            }
        });
        jPanelAsignarCampana.add(rSButtonMetroLimpiar6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 410, 150, 30));

        rSButtonAsignar1.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonAsignar1.setText("Enlazar");
        rSButtonAsignar1.setToolTipText("Asignar una campaña a un paciente");
        rSButtonAsignar1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        rSButtonAsignar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonAsignar1ActionPerformed(evt);
            }
        });
        jPanelAsignarCampana.add(rSButtonAsignar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 70, 70));

        jPanelSliders2.add(jPanelAsignarCampana, "card2");

        jPanelCampaing.add(jPanelSliders2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 660, 510));

        jPanelPpal.add(jPanelCampaing, "card4");

        jPanelBed.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTituloBed.setBackground(new java.awt.Color(51, 152, 219));
        jPanelTituloBed.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTituloCama.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabelTituloCama.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloCama.setText("GESTIÓN DE CAMAS");
        jPanelTituloBed.add(jLabelTituloCama, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 520, -1));

        jLabelimgCama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/camas_100px.png"))); // NOI18N
        jPanelTituloBed.add(jLabelimgCama, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 110, 90));

        jButtonSlider3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/menu_48px.png"))); // NOI18N
        jButtonSlider3.setContentAreaFilled(false);
        jButtonSlider3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSlider3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSlider3ActionPerformed(evt);
            }
        });
        jPanelTituloBed.add(jButtonSlider3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, 70));

        jPanelBed.add(jPanelTituloBed, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 90));

        jPanelSlider3.setBackground(new java.awt.Color(153, 153, 153));
        jPanelSlider3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBed.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelBed.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/cama_50px.png"))); // NOI18N
        jLabelBed.setText("CAMAS");
        jPanelSlider3.add(jLabelBed, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 50));

        jSeparatorBed.setForeground(new java.awt.Color(255, 255, 255));
        jSeparatorBed.setPreferredSize(new java.awt.Dimension(240, 4));
        jPanelSlider3.add(jSeparatorBed, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));

        rSButtonPaneHome3.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonPaneHome3.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonPaneHome3.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonPaneHome3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonPaneHome3MouseClicked(evt);
            }
        });
        rSButtonPaneHome3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHome3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelHome3.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHome3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/home_50px.png"))); // NOI18N
        jLabelHome3.setText("INICIO");
        jLabelHome3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, new java.awt.Color(255, 255, 255), null, null));
        jLabelHome3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rSButtonPaneHome3.add(jLabelHome3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider3.add(rSButtonPaneHome3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 240, -1));

        rSButtonAgregarCama.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonAgregarCama.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonAgregarCama.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonAgregarCama.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonAgregarCamaMouseClicked(evt);
            }
        });
        rSButtonAgregarCama.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAgregarCama.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAgregarCama.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAgregarCama.setText(" Agregar Cama");
        rSButtonAgregarCama.add(jLabelAgregarCama, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider3.add(rSButtonAgregarCama, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 240, -1));

        rSButtonModificarCama.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonModificarCama.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonModificarCama.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonModificarCama.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonModificarCamaMouseClicked(evt);
            }
        });
        rSButtonModificarCama.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelModificarCama.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelModificarCama.setForeground(new java.awt.Color(255, 255, 255));
        jLabelModificarCama.setText(" Modificar Cama");
        rSButtonModificarCama.add(jLabelModificarCama, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider3.add(rSButtonModificarCama, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 240, -1));

        rSButtonAsignarCama.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonAsignarCama.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonAsignarCama.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonAsignarCama.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonAsignarCamaMouseClicked(evt);
            }
        });
        rSButtonAsignarCama.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAsignarCama.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAsignarCama.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAsignarCama.setText(" Asignar Cama a Paciente");
        rSButtonAsignarCama.add(jLabelAsignarCama, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider3.add(rSButtonAsignarCama, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 240, -1));

        jPanelBed.add(jPanelSlider3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 240, 510));

        jPanelSliders3.setLayout(new java.awt.CardLayout());

        jPanelModificarCama.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBuscar5.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelBuscar5.setForeground(new java.awt.Color(51, 152, 219));
        jLabelBuscar5.setText("Codigo:");
        jPanelModificarCama.add(jLabelBuscar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, 30));

        jTextFieldBuscar5.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldBuscar5.setText("Digite el codigo de la cama");
        jTextFieldBuscar5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldBuscar5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldBuscar5FocusGained(evt);
            }
        });
        jTextFieldBuscar5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldBuscar5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldBuscar5MouseReleased(evt);
            }
        });
        jTextFieldBuscar5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBuscar5KeyTyped(evt);
            }
        });
        jPanelModificarCama.add(jTextFieldBuscar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 180, 30));

        rSButtonMetroMostrar5.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroMostrar5.setText("Limpiar");
        rSButtonMetroMostrar5.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroMostrar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroMostrar5ActionPerformed(evt);
            }
        });
        jPanelModificarCama.add(rSButtonMetroMostrar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 445, 110, 30));

        rSButtonMetroFiltrar5.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroFiltrar5.setText("Buscar");
        rSButtonMetroFiltrar5.setToolTipText("Buscar cama en el sistema");
        rSButtonMetroFiltrar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar5ActionPerformed(evt);
            }
        });
        jPanelModificarCama.add(rSButtonMetroFiltrar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 110, 30));

        rSTablaCama.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo", "nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        rSTablaCama.setColorBackgoundHead(new java.awt.Color(52, 120, 219));
        rSTablaCama.setColorFilasForeground1(new java.awt.Color(52, 152, 219));
        rSTablaCama.setColorFilasForeground2(new java.awt.Color(52, 152, 219));
        rSTablaCama.setColorSelBackgound(new java.awt.Color(52, 152, 219));
        rSTablaCama.setRowHeight(25);
        rSTablaCama.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSTablaCamaMouseClicked(evt);
            }
        });
        jScrollPaneTablaCama.setViewportView(rSTablaCama);

        jPanelModificarCama.add(jScrollPaneTablaCama, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 620, 380));

        rSButtonMetroFiltrar12.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroFiltrar12.setText("Mostrar todo");
        rSButtonMetroFiltrar12.setToolTipText("Mostrar todas las camas del sistema");
        rSButtonMetroFiltrar12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar12ActionPerformed(evt);
            }
        });
        jPanelModificarCama.add(rSButtonMetroFiltrar12, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 110, 30));

        jPanelSliders3.add(jPanelModificarCama, "card2");

        jPanelAgregarCama.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelAgregarCamaComponentShown(evt);
            }
        });
        jPanelAgregarCama.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelDescripcion2.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelDescripcion2.setForeground(new java.awt.Color(51, 152, 219));
        jLabelDescripcion2.setText("Descripción");
        jPanelAgregarCama.add(jLabelDescripcion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jLabelCodigo4.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelCodigo4.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCodigo4.setText("Codigo");
        jPanelAgregarCama.add(jLabelCodigo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabelArea2.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelArea2.setForeground(new java.awt.Color(51, 152, 219));
        jLabelArea2.setText("Area");
        jPanelAgregarCama.add(jLabelArea2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, -1, -1));

        jTextFieldCodigo4.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldCodigo4.setText("Digite el codigo de la cama");
        jTextFieldCodigo4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldCodigo4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCodigo4FocusGained(evt);
            }
        });
        jTextFieldCodigo4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldCodigo4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldCodigo4MouseReleased(evt);
            }
        });
        jTextFieldCodigo4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCodigo4KeyTyped(evt);
            }
        });
        jPanelAgregarCama.add(jTextFieldCodigo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 180, 30));

        rSComboArea2.setColorFondo(new java.awt.Color(32, 131, 255));
        jPanelAgregarCama.add(rSComboArea2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, 180, -1));

        rSButtonMetroGuardar5.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroGuardar5.setText("Guardar");
        rSButtonMetroGuardar5.setToolTipText("Guardar datos ingresados");
        rSButtonMetroGuardar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroGuardar5ActionPerformed(evt);
            }
        });
        jPanelAgregarCama.add(rSButtonMetroGuardar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 150, 30));

        rSButtonMetroLimpiar7.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar7.setText("Limpiar");
        rSButtonMetroLimpiar7.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar7ActionPerformed(evt);
            }
        });
        jPanelAgregarCama.add(rSButtonMetroLimpiar7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 150, 30));

        jTextAreaDescripcionCama.setColumns(20);
        jTextAreaDescripcionCama.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextAreaDescripcionCama.setForeground(new java.awt.Color(51, 152, 219));
        jTextAreaDescripcionCama.setLineWrap(true);
        jTextAreaDescripcionCama.setRows(5);
        jTextAreaDescripcionCama.setText("Ingrese una breve descripción de la cama");
        jTextAreaDescripcionCama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextAreaDescripcionCama.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextAreaDescripcionCamaFocusGained(evt);
            }
        });
        jTextAreaDescripcionCama.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaDescripcionCamaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextAreaDescripcionCamaMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTextAreaDescripcionCama);

        jPanelAgregarCama.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 600, 170));

        jCheckBoxDisponible.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBoxDisponible.setForeground(new java.awt.Color(51, 152, 219));
        jCheckBoxDisponible.setText("Disponible?");
        jCheckBoxDisponible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxDisponibleActionPerformed(evt);
            }
        });
        jPanelAgregarCama.add(jCheckBoxDisponible, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 90, -1));

        jPanelSliders3.add(jPanelAgregarCama, "card2");

        jPanelAsignarCama.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelAsignarCamaComponentShown(evt);
            }
        });
        jPanelAsignarCama.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelFechaAsignacion.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelFechaAsignacion.setForeground(new java.awt.Color(51, 152, 219));
        jLabelFechaAsignacion.setText("Fecha de Asignación");
        jPanelAsignarCama.add(jLabelFechaAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabelListaPacientes1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelListaPacientes1.setForeground(new java.awt.Color(51, 152, 219));
        jLabelListaPacientes1.setText("Lista de Pacientes");
        jPanelAsignarCama.add(jLabelListaPacientes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, -1, -1));

        rSComboMetroListaPacientes1.setColorBorde(new java.awt.Color(51, 152, 219));
        rSComboMetroListaPacientes1.setColorFondo(new java.awt.Color(51, 152, 219));
        jPanelAsignarCama.add(rSComboMetroListaPacientes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 180, -1));

        rSButtonMetroLimpiar8.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar8.setText("Limpiar");
        rSButtonMetroLimpiar8.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar8ActionPerformed(evt);
            }
        });
        jPanelAsignarCama.add(rSButtonMetroLimpiar8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 410, 150, 30));

        rSButtonAsignar2.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonAsignar2.setText("Enlazar");
        rSButtonAsignar2.setToolTipText("Asignar una cama a un paciente");
        rSButtonAsignar2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        rSButtonAsignar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonAsignar2ActionPerformed(evt);
            }
        });
        jPanelAsignarCama.add(rSButtonAsignar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 190, 70, 70));

        rSDateChooserFechaAsignacion.setColorBackground(new java.awt.Color(51, 152, 219));
        rSDateChooserFechaAsignacion.setPlaceholder("Seleccionar Día");
        jPanelAsignarCama.add(rSDateChooserFechaAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 180, -1));

        rSComboMetroListaCamas.setColorBorde(new java.awt.Color(51, 152, 219));
        rSComboMetroListaCamas.setColorFondo(new java.awt.Color(51, 152, 219));
        rSComboMetroListaCamas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSComboMetroListaCamasActionPerformed(evt);
            }
        });
        jPanelAsignarCama.add(rSComboMetroListaCamas, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 180, -1));

        jLabelListaCamas.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelListaCamas.setForeground(new java.awt.Color(51, 152, 219));
        jLabelListaCamas.setText("Lista de Camas");
        jPanelAsignarCama.add(jLabelListaCamas, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jPanelSliders3.add(jPanelAsignarCama, "card2");

        jPanelBed.add(jPanelSliders3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 660, 510));

        jPanelPpal.add(jPanelBed, "card4");

        jPanelArea.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTituloArea.setBackground(new java.awt.Color(51, 152, 219));
        jPanelTituloArea.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTituloArea.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabelTituloArea.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloArea.setText("GESTIÓN DE AREAS");
        jPanelTituloArea.add(jLabelTituloArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 520, -1));

        jLabelimgArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/areas_100px_1.png"))); // NOI18N
        jPanelTituloArea.add(jLabelimgArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 110, 90));

        jButtonSlider4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/menu_48px.png"))); // NOI18N
        jButtonSlider4.setContentAreaFilled(false);
        jButtonSlider4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSlider4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSlider4ActionPerformed(evt);
            }
        });
        jPanelTituloArea.add(jButtonSlider4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, 70));

        jPanelArea.add(jPanelTituloArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 90));

        jPanelSlider4.setBackground(new java.awt.Color(153, 153, 153));
        jPanelSlider4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAreaS.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelAreaS.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAreaS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/tareas_50px.png"))); // NOI18N
        jLabelAreaS.setText("AREA");
        jPanelSlider4.add(jLabelAreaS, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 50));

        jSeparatorAreaS.setForeground(new java.awt.Color(255, 255, 255));
        jSeparatorAreaS.setPreferredSize(new java.awt.Dimension(240, 4));
        jPanelSlider4.add(jSeparatorAreaS, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));

        rSButtonPaneHome4.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonPaneHome4.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonPaneHome4.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonPaneHome4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonPaneHome4MouseClicked(evt);
            }
        });
        rSButtonPaneHome4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHome4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelHome4.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHome4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/home_50px.png"))); // NOI18N
        jLabelHome4.setText("INICIO");
        jLabelHome4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, new java.awt.Color(255, 255, 255), null, null));
        jLabelHome4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rSButtonPaneHome4.add(jLabelHome4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider4.add(rSButtonPaneHome4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 240, -1));

        rSButtonAgregarArea.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonAgregarArea.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonAgregarArea.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonAgregarArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonAgregarAreaMouseClicked(evt);
            }
        });
        rSButtonAgregarArea.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAgregarArea.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAgregarArea.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAgregarArea.setText(" Agregar Area");
        rSButtonAgregarArea.add(jLabelAgregarArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider4.add(rSButtonAgregarArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 240, -1));

        rSButtonModificarArea.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonModificarArea.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonModificarArea.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonModificarArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonModificarAreaMouseClicked(evt);
            }
        });
        rSButtonModificarArea.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelModificarArea.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelModificarArea.setForeground(new java.awt.Color(255, 255, 255));
        jLabelModificarArea.setText(" Modificar Area");
        rSButtonModificarArea.add(jLabelModificarArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider4.add(rSButtonModificarArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 240, -1));

        jPanelArea.add(jPanelSlider4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 240, 510));

        jPanelSliders4.setLayout(new java.awt.CardLayout());

        jPanelAgregarArea.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNombre5.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelNombre5.setForeground(new java.awt.Color(51, 152, 219));
        jLabelNombre5.setText("Nombre");
        jPanelAgregarArea.add(jLabelNombre5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabelDescripcion3.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelDescripcion3.setForeground(new java.awt.Color(51, 152, 219));
        jLabelDescripcion3.setText("Descripción");
        jPanelAgregarArea.add(jLabelDescripcion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jLabelCodigo5.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelCodigo5.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCodigo5.setText("Codigo");
        jPanelAgregarArea.add(jLabelCodigo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, -1, -1));

        jTextFieldCodigo5.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldCodigo5.setText("Digite el codigo del area");
        jTextFieldCodigo5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldCodigo5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCodigo5FocusGained(evt);
            }
        });
        jPanelAgregarArea.add(jTextFieldCodigo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, 180, 30));

        rSButtonMetroGuardar6.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroGuardar6.setText("Guardar");
        rSButtonMetroGuardar6.setToolTipText("Guardar datos ingresados");
        rSButtonMetroGuardar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroGuardar6ActionPerformed(evt);
            }
        });
        jPanelAgregarArea.add(rSButtonMetroGuardar6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 150, 30));

        rSButtonMetroLimpiar9.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar9.setText("Limpiar");
        rSButtonMetroLimpiar9.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar9ActionPerformed(evt);
            }
        });
        jPanelAgregarArea.add(rSButtonMetroLimpiar9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 150, 30));

        jTextFieldNombre5.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldNombre5.setText("Digite el nombre del area");
        jTextFieldNombre5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldNombre5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNombre5FocusGained(evt);
            }
        });
        jPanelAgregarArea.add(jTextFieldNombre5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 180, 30));

        jTextAreaDescripcionArea.setColumns(20);
        jTextAreaDescripcionArea.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextAreaDescripcionArea.setForeground(new java.awt.Color(51, 152, 219));
        jTextAreaDescripcionArea.setLineWrap(true);
        jTextAreaDescripcionArea.setRows(5);
        jTextAreaDescripcionArea.setText("Ingrese una breve descripción del medicamento");
        jTextAreaDescripcionArea.setToolTipText("");
        jTextAreaDescripcionArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextAreaDescripcionArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextAreaDescripcionAreaFocusGained(evt);
            }
        });
        jTextAreaDescripcionArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaDescripcionAreaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextAreaDescripcionAreaMouseReleased(evt);
            }
        });
        jTextAreaDescripcionArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextAreaDescripcionAreaKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(jTextAreaDescripcionArea);

        jPanelAgregarArea.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 600, 170));

        jPanelSliders4.add(jPanelAgregarArea, "card2");

        jPanelModificarArea.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBuscar6.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelBuscar6.setForeground(new java.awt.Color(51, 152, 219));
        jLabelBuscar6.setText("Codigo:");
        jPanelModificarArea.add(jLabelBuscar6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, 30));

        jTextFieldBuscar6.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldBuscar6.setText("Digite el codigo del area");
        jTextFieldBuscar6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldBuscar6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldBuscar6FocusGained(evt);
            }
        });
        jTextFieldBuscar6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldBuscar6MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldBuscar6MouseReleased(evt);
            }
        });
        jTextFieldBuscar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscar6ActionPerformed(evt);
            }
        });
        jTextFieldBuscar6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBuscar6KeyTyped(evt);
            }
        });
        jPanelModificarArea.add(jTextFieldBuscar6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 180, 30));

        rSButtonMetroMostrar6.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroMostrar6.setText("Limpiar");
        rSButtonMetroMostrar6.setToolTipText("Limpiar datos ingresados ");
        rSButtonMetroMostrar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroMostrar6ActionPerformed(evt);
            }
        });
        jPanelModificarArea.add(rSButtonMetroMostrar6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 445, 110, 30));

        rSButtonMetroFiltrar6.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroFiltrar6.setText("Buscar");
        rSButtonMetroFiltrar6.setToolTipText("Buscar area en el sistema");
        rSButtonMetroFiltrar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar6ActionPerformed(evt);
            }
        });
        jPanelModificarArea.add(rSButtonMetroFiltrar6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 110, 30));

        rSTablaArea.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo", "nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        rSTablaArea.setColorBackgoundHead(new java.awt.Color(52, 120, 219));
        rSTablaArea.setColorFilasForeground1(new java.awt.Color(52, 152, 219));
        rSTablaArea.setColorFilasForeground2(new java.awt.Color(52, 152, 219));
        rSTablaArea.setColorSelBackgound(new java.awt.Color(52, 152, 219));
        rSTablaArea.setRowHeight(25);
        rSTablaArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSTablaAreaMouseClicked(evt);
            }
        });
        jScrollPaneTablaArea.setViewportView(rSTablaArea);

        jPanelModificarArea.add(jScrollPaneTablaArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 620, 380));

        rSButtonMetroFiltrar13.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroFiltrar13.setText("Mostrar todo");
        rSButtonMetroFiltrar13.setToolTipText("Mostrar todas las areas del sistema");
        rSButtonMetroFiltrar13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar13ActionPerformed(evt);
            }
        });
        jPanelModificarArea.add(rSButtonMetroFiltrar13, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 110, 30));

        jPanelSliders4.add(jPanelModificarArea, "card2");

        jPanelArea.add(jPanelSliders4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 660, 510));

        jPanelPpal.add(jPanelArea, "card4");

        jPanelReports.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTituloReport.setBackground(new java.awt.Color(51, 152, 219));
        jPanelTituloReport.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTituloReport.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabelTituloReport.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloReport.setText("REPORT MANAGER");
        jPanelTituloReport.add(jLabelTituloReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 520, -1));

        jLabelimgReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/reportes_100px.png"))); // NOI18N
        jPanelTituloReport.add(jLabelimgReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 110, 90));

        jPanelReports.add(jPanelTituloReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 90));

        rSButtonPaneHome8.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonPaneHome8.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonPaneHome8.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonPaneHome8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonPaneHome8MouseClicked(evt);
            }
        });
        rSButtonPaneHome8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHome8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelHome8.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHome8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/home_50px.png"))); // NOI18N
        jLabelHome8.setText("HOME");
        jLabelHome8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, new java.awt.Color(255, 255, 255), null, null));
        jLabelHome8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rSButtonPaneHome8.add(jLabelHome8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelReports.add(rSButtonPaneHome8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 240, -1));

        rSButtonMetroListWorker.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroListWorker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/empleados_100px.png"))); // NOI18N
        rSButtonMetroListWorker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroListWorkerActionPerformed(evt);
            }
        });
        jPanelReports.add(rSButtonMetroListWorker, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 120, 140));

        rSButtonMetroHistoryClinic.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroHistoryClinic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/historia_100px.png"))); // NOI18N
        rSButtonMetroHistoryClinic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroHistoryClinicActionPerformed(evt);
            }
        });
        jPanelReports.add(rSButtonMetroHistoryClinic, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, 120, 140));

        rSButtonMetroNumberQuote.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroNumberQuote.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/numerocitas_100px.png"))); // NOI18N
        rSButtonMetroNumberQuote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroNumberQuoteActionPerformed(evt);
            }
        });
        jPanelReports.add(rSButtonMetroNumberQuote, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, 120, 140));

        rSButtonMetroMonthlyAgenda.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroMonthlyAgenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/agendamensual_100px.png"))); // NOI18N
        rSButtonMetroMonthlyAgenda.setToolTipText("");
        rSButtonMetroMonthlyAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroMonthlyAgendaActionPerformed(evt);
            }
        });
        jPanelReports.add(rSButtonMetroMonthlyAgenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, 120, 140));

        jLabelNumberQuote.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelNumberQuote.setForeground(new java.awt.Color(51, 152, 219));
        jLabelNumberQuote.setText("Number of quote");
        jPanelReports.add(jLabelNumberQuote, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 300, 130, -1));

        jLabelSeleccionar1.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelSeleccionar1.setForeground(new java.awt.Color(51, 152, 219));
        jLabelSeleccionar1.setText("Choose a Option:");
        jPanelReports.add(jLabelSeleccionar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabelListWorker.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelListWorker.setForeground(new java.awt.Color(51, 152, 219));
        jLabelListWorker.setText("  List of Worker");
        jPanelReports.add(jLabelListWorker, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 120, -1));

        jLabeHistoryClinic.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabeHistoryClinic.setForeground(new java.awt.Color(51, 152, 219));
        jLabeHistoryClinic.setText("  History Clinic");
        jPanelReports.add(jLabeHistoryClinic, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 300, 120, -1));

        jLabelMonthlyAgenda.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelMonthlyAgenda.setForeground(new java.awt.Color(51, 152, 219));
        jLabelMonthlyAgenda.setText("Monthly Agenda");
        jPanelReports.add(jLabelMonthlyAgenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 490, 120, -1));

        jPanelPpal.add(jPanelReports, "card4");

        jPanelTools.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTituloTools.setBackground(new java.awt.Color(51, 152, 219));
        jPanelTituloTools.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTituloTools.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabelTituloTools.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloTools.setText("TOOLS MANAGER");
        jPanelTituloTools.add(jLabelTituloTools, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 520, -1));

        jLabelimgTools.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/tools_100px.png"))); // NOI18N
        jPanelTituloTools.add(jLabelimgTools, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 110, 90));

        jPanelTools.add(jPanelTituloTools, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 90));

        jPanelSliders6.setLayout(new java.awt.CardLayout());

        jPanelPrincipal.setMinimumSize(new java.awt.Dimension(900, 610));
        jPanelPrincipal.setPreferredSize(new java.awt.Dimension(900, 610));
        jPanelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSButtonMetroCleanDB.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroCleanDB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/cleanDB_100px.png"))); // NOI18N
        rSButtonMetroCleanDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroCleanDBActionPerformed(evt);
            }
        });
        jPanelPrincipal.add(rSButtonMetroCleanDB, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 120, 140));

        rSButtonMetroRestoreBD.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroRestoreBD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/restoreBD_100px.png"))); // NOI18N
        rSButtonMetroRestoreBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroRestoreBDActionPerformed(evt);
            }
        });
        jPanelPrincipal.add(rSButtonMetroRestoreBD, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 120, 140));

        rSButtonMetroBackupBD.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroBackupBD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/crearBD_100px.png"))); // NOI18N
        rSButtonMetroBackupBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroBackupBDActionPerformed(evt);
            }
        });
        jPanelPrincipal.add(rSButtonMetroBackupBD, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 120, 140));

        rSButtonMetroCambiarIdioma.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroCambiarIdioma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/cambiarlenguaje_100px.png"))); // NOI18N
        rSButtonMetroCambiarIdioma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroCambiarIdiomaActionPerformed(evt);
            }
        });
        jPanelPrincipal.add(rSButtonMetroCambiarIdioma, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 290, 120, 140));

        jLabelBackupDB.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelBackupDB.setForeground(new java.awt.Color(51, 152, 219));
        jLabelBackupDB.setText(" Backup Database");
        jPanelPrincipal.add(jLabelBackupDB, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, 130, -1));

        jLabelSeleccionar.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelSeleccionar.setForeground(new java.awt.Color(51, 152, 219));
        jLabelSeleccionar.setText("Choose a Option:");
        jPanelPrincipal.add(jLabelSeleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabelCleanDB.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelCleanDB.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCleanDB.setText(" Clean Database");
        jPanelPrincipal.add(jLabelCleanDB, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 120, -1));

        jLabelRestoreDB.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelRestoreDB.setForeground(new java.awt.Color(51, 152, 219));
        jLabelRestoreDB.setText("Restore Database");
        jPanelPrincipal.add(jLabelRestoreDB, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, -1, -1));

        jLabelCambiarIdioma.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelCambiarIdioma.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCambiarIdioma.setText("Change Language");
        jPanelPrincipal.add(jLabelCambiarIdioma, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 440, 130, -1));

        rSButtonPaneHome6.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonPaneHome6.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonPaneHome6.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonPaneHome6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonPaneHome6MouseClicked(evt);
            }
        });
        rSButtonPaneHome6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHome6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelHome6.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHome6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/home_50px.png"))); // NOI18N
        jLabelHome6.setText("HOME");
        jLabelHome6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, new java.awt.Color(255, 255, 255), null, null));
        jLabelHome6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rSButtonPaneHome6.add(jLabelHome6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelPrincipal.add(rSButtonPaneHome6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 240, -1));

        jPanelSliders6.add(jPanelPrincipal, "card2");

        jPanelCambiarIdioma.setMinimumSize(new java.awt.Dimension(900, 600));
        jPanelCambiarIdioma.setPreferredSize(new java.awt.Dimension(900, 600));
        jPanelCambiarIdioma.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelCambiarIdiomaComponentShown(evt);
            }
        });
        jPanelCambiarIdioma.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSButtonPaneHome9.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonPaneHome9.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonPaneHome9.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonPaneHome9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonPaneHome9MouseClicked(evt);
            }
        });
        rSButtonPaneHome9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHome9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelHome9.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHome9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/home_50px.png"))); // NOI18N
        jLabelHome9.setText("HOME");
        jLabelHome9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, new java.awt.Color(255, 255, 255), null, null));
        jLabelHome9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabelHome9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelHome9MouseClicked(evt);
            }
        });
        rSButtonPaneHome9.add(jLabelHome9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelCambiarIdioma.add(rSButtonPaneHome9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 240, -1));

        rSComboMetroIdioma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spanish", "English" }));
        rSComboMetroIdioma.setToolTipText("");
        jPanelCambiarIdioma.add(rSComboMetroIdioma, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, -1, -1));

        rSButtonMetroIdioma.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroIdioma.setText("Change Language");
        rSButtonMetroIdioma.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        rSButtonMetroIdioma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroIdiomaActionPerformed(evt);
            }
        });
        jPanelCambiarIdioma.add(rSButtonMetroIdioma, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, 140, 20));

        jLabelSeleccionIdioma.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelSeleccionIdioma.setForeground(new java.awt.Color(51, 152, 219));
        jLabelSeleccionIdioma.setText("Select Language:");
        jPanelCambiarIdioma.add(jLabelSeleccionIdioma, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 130, -1));

        jPanelSliders6.add(jPanelCambiarIdioma, "card5");

        jPanelBackupBD.setMinimumSize(new java.awt.Dimension(900, 600));
        jPanelBackupBD.setPreferredSize(new java.awt.Dimension(900, 600));
        jPanelBackupBD.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelBackupBDComponentShown(evt);
            }
        });
        jPanelBackupBD.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelBackupBD.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 650, 10));

        rSButtonMetroBackup.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroBackup.setText("Go Backup");
        rSButtonMetroBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroBackupActionPerformed(evt);
            }
        });
        jPanelBackupBD.add(rSButtonMetroBackup, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 110, 20));

        jLabelFormatoSalida.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelFormatoSalida.setForeground(new java.awt.Color(51, 152, 219));
        jLabelFormatoSalida.setText("Output Format");
        jPanelBackupBD.add(jLabelFormatoSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 26));

        jLabelTituloRuta.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelTituloRuta.setForeground(new java.awt.Color(51, 152, 219));
        jLabelTituloRuta.setText("Save in");
        jPanelBackupBD.add(jLabelTituloRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 26));

        jLabelTipoBackup.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelTipoBackup.setForeground(new java.awt.Color(51, 152, 219));
        jLabelTipoBackup.setText("Backup Type");
        jLabelTipoBackup.setToolTipText("");
        jPanelBackupBD.add(jLabelTipoBackup, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        buttonGroupTipo.add(jRadioParcial);
        jRadioParcial.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jRadioParcial.setForeground(new java.awt.Color(51, 152, 219));
        jRadioParcial.setText("Partial");
        jRadioParcial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioParcial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioParcialActionPerformed(evt);
            }
        });
        jPanelBackupBD.add(jRadioParcial, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, -1));

        buttonGroupTipo.add(jRadioTotal);
        jRadioTotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jRadioTotal.setForeground(new java.awt.Color(51, 152, 219));
        jRadioTotal.setText("Total");
        jRadioTotal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioTotalActionPerformed(evt);
            }
        });
        jPanelBackupBD.add(jRadioTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, -1, -1));

        jLabelRuta.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 152, 219), null));
        jPanelBackupBD.add(jLabelRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 410, 20));

        buttonGroupFormato.add(jRadioBackup);
        jRadioBackup.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jRadioBackup.setForeground(new java.awt.Color(51, 152, 219));
        jRadioBackup.setText(".backup");
        jRadioBackup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioBackupActionPerformed(evt);
            }
        });
        jPanelBackupBD.add(jRadioBackup, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, -1));

        buttonGroupFormato.add(jRadioSQL);
        jRadioSQL.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jRadioSQL.setForeground(new java.awt.Color(51, 152, 219));
        jRadioSQL.setText(".sql");
        jRadioSQL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioSQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioSQLActionPerformed(evt);
            }
        });
        jPanelBackupBD.add(jRadioSQL, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));

        jLabelSO.setForeground(new java.awt.Color(51, 152, 219));
        jLabelSO.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 152, 219), null));
        jPanelBackupBD.add(jLabelSO, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 136, 410, 20));

        jLabelTituloSO.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelTituloSO.setForeground(new java.awt.Color(51, 152, 219));
        jLabelTituloSO.setText("Operating System");
        jPanelBackupBD.add(jLabelTituloSO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, 26));

        jLabelTituloTablas.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelTituloTablas.setForeground(new java.awt.Color(51, 152, 219));
        jLabelTituloTablas.setText("Select the tables to.. ");
        jPanelBackupBD.add(jLabelTituloTablas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 159, -1));

        jLabelTituloBackup.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelTituloBackup.setForeground(new java.awt.Color(51, 152, 219));
        jLabelTituloBackup.setText("Backup");
        jPanelBackupBD.add(jLabelTituloBackup, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, -1, -1));

        jTableTablasBackup.setBackground(new java.awt.Color(202, 228, 201));
        jTableTablasBackup.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane4.setViewportView(jTableTablasBackup);

        jPanelBackupBD.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 270, 230));

        jTableTablas.setBackground(new java.awt.Color(222, 185, 186));
        jTableTablas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane5.setViewportView(jTableTablas);

        jPanelBackupBD.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 270, 230));

        rSButtonMetroIzquierda.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroIzquierda.setText("<");
        rSButtonMetroIzquierda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroIzquierdaActionPerformed(evt);
            }
        });
        jPanelBackupBD.add(rSButtonMetroIzquierda, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 360, 30, 30));

        rSButtonMetroDerecha.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroDerecha.setText(">");
        rSButtonMetroDerecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroDerechaActionPerformed(evt);
            }
        });
        jPanelBackupBD.add(rSButtonMetroDerecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 30, 30));

        rSButtonChooserRuta.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonChooserRuta.setText("...");
        rSButtonChooserRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonChooserRutaActionPerformed(evt);
            }
        });
        jPanelBackupBD.add(rSButtonChooserRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 40, 20));

        rSButtonPaneHome7.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonPaneHome7.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonPaneHome7.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonPaneHome7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonPaneHome7MouseClicked(evt);
            }
        });
        rSButtonPaneHome7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHome7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelHome7.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHome7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/home_50px.png"))); // NOI18N
        jLabelHome7.setText("HOME");
        jLabelHome7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, new java.awt.Color(255, 255, 255), null, null));
        jLabelHome7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabelHome7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelHome7MouseClicked(evt);
            }
        });
        rSButtonPaneHome7.add(jLabelHome7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelBackupBD.add(rSButtonPaneHome7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 240, -1));

        jPanelSliders6.add(jPanelBackupBD, "card5");

        jPanelTools.add(jPanelSliders6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 660, 510));

        jPanelPpal.add(jPanelTools, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanelPpal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanelPpal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setBounds(0, 0, 910, 600);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelCamasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCamasMouseClicked
        jPanelMenu.setVisible(false);
        jPanelBed.setVisible(true);
    }//GEN-LAST:event_jPanelCamasMouseClicked

    private void jPanelCamasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCamasMouseEntered
        ponerColor(jPanelCamas);
    }//GEN-LAST:event_jPanelCamasMouseEntered

    private void jPanelCamasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCamasMouseExited
        repintarColor(jPanelCamas);
    }//GEN-LAST:event_jPanelCamasMouseExited

    private void jPanelCampanasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCampanasMouseClicked
        jPanelMenu.setVisible(false);
        jPanelCampaing.setVisible(true);
    }//GEN-LAST:event_jPanelCampanasMouseClicked

    private void jPanelCampanasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCampanasMouseEntered
        ponerColor(jPanelCampanas);
    }//GEN-LAST:event_jPanelCampanasMouseEntered

    private void jPanelCampanasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCampanasMouseExited
        repintarColor(jPanelCampanas);
    }//GEN-LAST:event_jPanelCampanasMouseExited

    private void jPanelUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelUsuariosMouseClicked
        jPanelStaff.setVisible(true);
        jPanelMenu.setVisible(false);
    }//GEN-LAST:event_jPanelUsuariosMouseClicked

    private void jPanelUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelUsuariosMouseEntered
        ponerColor(jPanelUsuarios);
    }//GEN-LAST:event_jPanelUsuariosMouseEntered

    private void jPanelUsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelUsuariosMouseExited
        repintarColor(jPanelUsuarios);
    }//GEN-LAST:event_jPanelUsuariosMouseExited

    private void jPanelMedicinasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelMedicinasMouseClicked
        jPanelMenu.setVisible(false);
        jPanelMedicians.setVisible(true);
    }//GEN-LAST:event_jPanelMedicinasMouseClicked

    private void jPanelMedicinasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelMedicinasMouseEntered
        ponerColor(jPanelMedicinas);
    }//GEN-LAST:event_jPanelMedicinasMouseEntered

    private void jPanelMedicinasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelMedicinasMouseExited
        repintarColor(jPanelMedicinas);
    }//GEN-LAST:event_jPanelMedicinasMouseExited

    private void jPanelAreasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelAreasMouseClicked
        jPanelMenu.setVisible(false);
        jPanelArea.setVisible(true);
    }//GEN-LAST:event_jPanelAreasMouseClicked

    private void jPanelAreasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelAreasMouseEntered
        ponerColor(jPanelAreas);
    }//GEN-LAST:event_jPanelAreasMouseEntered

    private void jPanelAreasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelAreasMouseExited
        repintarColor(jPanelAreas);
    }//GEN-LAST:event_jPanelAreasMouseExited

    private void rSButtonCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonCerrarSesionMouseClicked
        new jConfirmation(this, true, "<html><center>¿Esta seguro que desea salir ? </center></html>").setVisible(true);
        
        if(jConfirmation.opcion ==1)
        {
            this.setVisible(false);
            new VentanaLogin().setVisible(true);
        } 
    }//GEN-LAST:event_rSButtonCerrarSesionMouseClicked

    private void jButtonSliderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSliderActionPerformed
        int posicion = jPanelSlider.getX();
        if(posicion > -1){
            Animacion.Animacion.mover_izquierda(0, -264, 1, 1, jPanelSlider);
        }else{
            Animacion.Animacion.mover_derecha(-264, 0, 1, 1, jPanelSlider);
        }
    }//GEN-LAST:event_jButtonSliderActionPerformed

    private void rSButtonPaneHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonPaneHomeMouseClicked
       jPanelStaff.setVisible(false);
       jPanelMenu.setVisible(true);
    }//GEN-LAST:event_rSButtonPaneHomeMouseClicked

    private void rSButtonAgregarPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonAgregarPacienteMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelAgregarPaciente.setVisible(true);
    }//GEN-LAST:event_rSButtonAgregarPacienteMouseClicked

    private void rSButtonModificarPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonModificarPacienteMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelModificarPaciente.setVisible(true);
    }//GEN-LAST:event_rSButtonModificarPacienteMouseClicked

    private void rSButtonAgregarEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonAgregarEmpleadoMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelAgregarEmpleado.setVisible(true);
    }//GEN-LAST:event_rSButtonAgregarEmpleadoMouseClicked

    private void rSButtonModificarEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonModificarEmpleadoMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelModificarEmpleado.setVisible(true);
    }//GEN-LAST:event_rSButtonModificarEmpleadoMouseClicked

    private void rSButtonAgregarHabilidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonAgregarHabilidadMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelAgregarHabilidad.setVisible(true);
    }//GEN-LAST:event_rSButtonAgregarHabilidadMouseClicked

    private void rSButtonModificarHabilidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonModificarHabilidadMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelModificarHabilidad.setVisible(true);
    }//GEN-LAST:event_rSButtonModificarHabilidadMouseClicked

    private void rSButtonAsignarHabilidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonAsignarHabilidadMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelAsignarHabilidad.setVisible(true);
    }//GEN-LAST:event_rSButtonAsignarHabilidadMouseClicked

    private void jButtonSlider1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSlider1ActionPerformed
        int posicion = jPanelSlider1.getX();
        if(posicion > -1){
            Animacion.Animacion.mover_izquierda(0, -264, 1, 1, jPanelSlider1);
        }else{
            Animacion.Animacion.mover_derecha(-264, 0, 1, 1, jPanelSlider1);
        }
    }//GEN-LAST:event_jButtonSlider1ActionPerformed

    private void rSButtonPaneHome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonPaneHome1MouseClicked
        jPanelMedicians.setVisible(false);
        jPanelMenu.setVisible(true);
    }//GEN-LAST:event_rSButtonPaneHome1MouseClicked

    private void rSButtonAgregarMedicinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonAgregarMedicinaMouseClicked
        ocultarSliders(jPanelSliders1);
        jPanelAgregarMedicina.setVisible(true);
    }//GEN-LAST:event_rSButtonAgregarMedicinaMouseClicked

    private void rSButtonModificarMedicinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonModificarMedicinaMouseClicked
        ocultarSliders(jPanelSliders1);
        jPanelModificarMedicina.setVisible(true);
    }//GEN-LAST:event_rSButtonModificarMedicinaMouseClicked

    private void jButtonSlider2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSlider2ActionPerformed
        int posicion = jPanelSlider2.getX();
        if(posicion > -1){
            Animacion.Animacion.mover_izquierda(0, -264, 1, 1, jPanelSlider2);
        }else{
            Animacion.Animacion.mover_derecha(-264, 0, 1, 1, jPanelSlider2);
        }
    }//GEN-LAST:event_jButtonSlider2ActionPerformed

    private void rSButtonPaneHome2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonPaneHome2MouseClicked
       jPanelCampaing.setVisible(false);
       jPanelMenu.setVisible(true);
    }//GEN-LAST:event_rSButtonPaneHome2MouseClicked

    private void rSButtonAgregarCampanaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonAgregarCampanaMouseClicked
        ocultarSliders(jPanelSliders2);
        jPanelAgregarCampana.setVisible(true);
    }//GEN-LAST:event_rSButtonAgregarCampanaMouseClicked

    private void rSButtonModificarCampanaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonModificarCampanaMouseClicked
        ocultarSliders(jPanelSliders2);
        jPanelModificarCampana.setVisible(true);
    }//GEN-LAST:event_rSButtonModificarCampanaMouseClicked

    private void rSButtonAsignarCampanaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonAsignarCampanaMouseClicked
        ocultarSliders(jPanelSliders2);
        jPanelAsignarCampana.setVisible(true);
    }//GEN-LAST:event_rSButtonAsignarCampanaMouseClicked

    private void jButtonSlider3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSlider3ActionPerformed
        int posicion = jPanelSlider3.getX();
        if(posicion > -1){
            Animacion.Animacion.mover_izquierda(0, -264, 1, 1, jPanelSlider3);
        }else{
            Animacion.Animacion.mover_derecha(-264, 0, 1, 1, jPanelSlider3);
        }
    }//GEN-LAST:event_jButtonSlider3ActionPerformed

    private void rSButtonPaneHome3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonPaneHome3MouseClicked
       jPanelBed.setVisible(false);
       jPanelMenu.setVisible(true);
    }//GEN-LAST:event_rSButtonPaneHome3MouseClicked

    private void rSButtonAgregarCamaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonAgregarCamaMouseClicked
        ocultarSliders(jPanelSliders3);
        jPanelAgregarCama.setVisible(true);
    }//GEN-LAST:event_rSButtonAgregarCamaMouseClicked

    private void rSButtonModificarCamaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonModificarCamaMouseClicked
        ocultarSliders(jPanelSliders3);
        jPanelModificarCama.setVisible(true);
    }//GEN-LAST:event_rSButtonModificarCamaMouseClicked

    private void rSButtonAsignarCamaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonAsignarCamaMouseClicked
        ocultarSliders(jPanelSliders3);
        jPanelAsignarCama.setVisible(true);
    }//GEN-LAST:event_rSButtonAsignarCamaMouseClicked

    private void jButtonSlider4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSlider4ActionPerformed
        int posicion = jPanelSlider4.getX();
        if(posicion > -1){
            Animacion.Animacion.mover_izquierda(0, -264, 1, 1, jPanelSlider4);
        }else{
            Animacion.Animacion.mover_derecha(-264, 0, 1, 1, jPanelSlider4);
        }
    }//GEN-LAST:event_jButtonSlider4ActionPerformed

    private void rSButtonPaneHome4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonPaneHome4MouseClicked
        jPanelArea.setVisible(false);
        jPanelMenu.setVisible(true);
    }//GEN-LAST:event_rSButtonPaneHome4MouseClicked

    private void rSButtonAgregarAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonAgregarAreaMouseClicked
        ocultarSliders(jPanelSliders4);
        jPanelAgregarArea.setVisible(true);
    }//GEN-LAST:event_rSButtonAgregarAreaMouseClicked

    private void rSButtonModificarAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonModificarAreaMouseClicked
        ocultarSliders(jPanelSliders4);
        jPanelModificarArea.setVisible(true);
    }//GEN-LAST:event_rSButtonModificarAreaMouseClicked

    private void rSButtonMetroLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiarActionPerformed
        limpiarComponentes(jPanelAgregarEmpleado);
    }//GEN-LAST:event_rSButtonMetroLimpiarActionPerformed

    private void rSButtonMetroLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar1ActionPerformed
        limpiarComponentes(jPanelAgregarPaciente);
        rSDateChooserFechaNacimiento.setDatoFecha(null);
    }//GEN-LAST:event_rSButtonMetroLimpiar1ActionPerformed

    private void rSButtonMetroLimpiar12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar12ActionPerformed
        limpiarComponentes(jPanelModificarPaciente);
        borrarTabla(rSTablaPacientes);
    }//GEN-LAST:event_rSButtonMetroLimpiar12ActionPerformed

    private void rSButtonMetroLimpiar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar4ActionPerformed
        limpiarComponentes(jPanelAgregarHabilidad);
        rSDateChooserFechaNacimiento.setDatoFecha(null);
    }//GEN-LAST:event_rSButtonMetroLimpiar4ActionPerformed

    private void rSButtonMetroLimpiar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar3ActionPerformed
        limpiarComponentes(jPanelAsignarHabilidad);
    }//GEN-LAST:event_rSButtonMetroLimpiar3ActionPerformed

    private void jTextFieldNombre1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNombre1KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldNombre1, 20, 0);
    }//GEN-LAST:event_jTextFieldNombre1KeyTyped

    private void jTextFieldNombre1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNombre1MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldNombre1MousePressed

    private void jTextFieldNombre1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNombre1MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldNombre1MouseReleased

    private void jTextFieldCedula1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCedula1KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldCedula1, 10, 0);
    }//GEN-LAST:event_jTextFieldCedula1KeyTyped

    private void jTextFieldCedula1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCedula1MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldCedula1MousePressed

    private void jTextFieldCedula1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCedula1MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldCedula1MouseReleased

    private void jTextFieldDireccion1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDireccion1KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldDireccion1, 20, 0);
    }//GEN-LAST:event_jTextFieldDireccion1KeyTyped

    private void jTextFieldDireccion1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldDireccion1MousePressed
        mostrarPopupMenu1(evt);      
    }//GEN-LAST:event_jTextFieldDireccion1MousePressed

    private void jTextFieldDireccion1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldDireccion1MouseReleased
        mostrarPopupMenu1(evt);   
    }//GEN-LAST:event_jTextFieldDireccion1MouseReleased

    private void jTextFieldTelefono1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTelefono1MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldTelefono1MousePressed

    private void jTextFieldTelefono1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTelefono1MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldTelefono1MouseReleased

    private void jTextFieldTelefono1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTelefono1KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldTelefono1, 10, 0);
    }//GEN-LAST:event_jTextFieldTelefono1KeyTyped

    private void jTextFieldActividadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldActividadKeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldActividad , 20, 0);
    }//GEN-LAST:event_jTextFieldActividadKeyTyped

    private void jTextFieldActividadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldActividadMousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldActividadMousePressed

    private void jTextFieldActividadMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldActividadMouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldActividadMouseReleased

    private void jTextFieldBuscar1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscar1KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldBuscar1, 20, 0);
    }//GEN-LAST:event_jTextFieldBuscar1KeyTyped

    private void jTextFieldBuscar1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscar1MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscar1MousePressed

    private void jTextFieldBuscar1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscar1MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscar1MouseReleased

    private void jTextFieldNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNombreKeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldNombre, 20, 0);
    }//GEN-LAST:event_jTextFieldNombreKeyTyped

    private void jTextFieldNombreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNombreMousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldNombreMousePressed

    private void jTextFieldNombreMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNombreMouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldNombreMouseReleased

    private void jTextFieldCedulaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCedulaMousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldCedulaMousePressed

    private void jTextFieldCedulaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCedulaMouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldCedulaMouseReleased

    private void jTextFieldCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCedulaKeyTyped
       validacionTotal.limitarCantidadChar(evt, jTextFieldCedula, 10, 0);
    }//GEN-LAST:event_jTextFieldCedulaKeyTyped

    private void jTextFieldDireccionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldDireccionMousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldDireccionMousePressed

    private void jTextFieldDireccionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldDireccionMouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldDireccionMouseReleased

    private void jTextFieldDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDireccionKeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldDireccion, 20, 0);
    }//GEN-LAST:event_jTextFieldDireccionKeyTyped

    private void jTextFieldTelefonoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoMousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldTelefonoMousePressed

    private void jTextFieldTelefonoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoMouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldTelefonoMouseReleased

    private void jTextFieldTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoKeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldTelefono, 10, 0);
    }//GEN-LAST:event_jTextFieldTelefonoKeyTyped

    private void jTextFieldSalarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSalarioMousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldSalarioMousePressed

    private void jTextFieldSalarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSalarioMouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldSalarioMouseReleased

    private void jTextFieldSalarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSalarioKeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldSalario, 10, 0);
    }//GEN-LAST:event_jTextFieldSalarioKeyTyped

    private void jTextFieldEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEmailKeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldEmail, 20, 0);
    }//GEN-LAST:event_jTextFieldEmailKeyTyped

    private void jTextFieldEmailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldEmailMousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldEmailMousePressed

    private void jTextFieldEmailMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldEmailMouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldEmailMouseReleased

    private void jTextFieldJefeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldJefeMousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldJefeMousePressed

    private void jTextFieldJefeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldJefeMouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldJefeMouseReleased

    private void jTextFieldJefeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldJefeKeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldJefe, 20, 0);
    }//GEN-LAST:event_jTextFieldJefeKeyTyped

    private void jTextFieldBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarKeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldBuscar, 20, 0);
    }//GEN-LAST:event_jTextFieldBuscarKeyTyped

    private void jTextFieldBuscarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscarMousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscarMousePressed

    private void jTextFieldBuscarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscarMouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscarMouseReleased

    private void jTextFieldNombre4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNombre4KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldNombre4, 20, 0);
    }//GEN-LAST:event_jTextFieldNombre4KeyTyped

    private void jTextFieldNombre4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNombre4MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldNombre4MousePressed

    private void jTextFieldNombre4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNombre4MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldNombre4MouseReleased

    private void jTextFieldCodigo2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigo2KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldCodigo2, 10, 0);
    }//GEN-LAST:event_jTextFieldCodigo2KeyTyped

    private void jTextFieldCodigo2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCodigo2MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldCodigo2MousePressed

    private void jTextFieldCodigo2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCodigo2MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldCodigo2MouseReleased

    private void jTextFieldBuscar3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscar3MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscar3MousePressed

    private void jTextFieldBuscar3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscar3MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscar3MouseReleased

    private void jTextFieldBuscar3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscar3KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldBuscar3, 20, 0);
    }//GEN-LAST:event_jTextFieldBuscar3KeyTyped

    private void rSButtonMetroLimpiar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar2ActionPerformed
        limpiarComponentes(jPanelAgregarMedicina);
        jTextAreaDescripcionMedicina.setText("");
    }//GEN-LAST:event_rSButtonMetroLimpiar2ActionPerformed

    private void jTextFieldNombre2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNombre2MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldNombre2MousePressed

    private void jTextFieldNombre2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNombre2MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldNombre2MouseReleased

    private void jTextFieldNombre2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNombre2KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldNombre2, 20, 0);
    }//GEN-LAST:event_jTextFieldNombre2KeyTyped

    private void jTextFieldCodigoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCodigoMousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldCodigoMousePressed

    private void jTextFieldCodigoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCodigoMouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldCodigoMouseReleased

    private void jTextFieldCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigoKeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldCodigo, 20, 0);
    }//GEN-LAST:event_jTextFieldCodigoKeyTyped

    private void jTextFieldBuscar2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscar2MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscar2MousePressed

    private void jTextFieldBuscar2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscar2MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscar2MouseReleased

    private void jTextFieldBuscar2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscar2KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldBuscar2, 20, 0);
    }//GEN-LAST:event_jTextFieldBuscar2KeyTyped

    private void rSButtonMetroLimpiar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar5ActionPerformed
        limpiarComponentes(jPanelAgregarCampana);
    }//GEN-LAST:event_rSButtonMetroLimpiar5ActionPerformed

    private void rSButtonMetroLimpiar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar6ActionPerformed
        limpiarComponentes(jPanelAsignarCampana);
    }//GEN-LAST:event_rSButtonMetroLimpiar6ActionPerformed

    private void jTextFieldNombre3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNombre3MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldNombre3MousePressed

    private void jTextFieldNombre3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNombre3MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldNombre3MouseReleased

    private void jTextFieldNombre3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNombre3KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldNombre3, 20, 0);
    }//GEN-LAST:event_jTextFieldNombre3KeyTyped

    private void jTextFieldCodigo3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigo3KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldCodigo3, 10, 0);
    }//GEN-LAST:event_jTextFieldCodigo3KeyTyped

    private void jTextFieldCodigo3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCodigo3MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldCodigo3MousePressed

    private void jTextFieldCodigo3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCodigo3MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldCodigo3MouseReleased

    private void jTextFieldBuscar4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscar4MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscar4MousePressed

    private void jTextFieldBuscar4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscar4MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscar4MouseReleased

    private void jTextFieldBuscar4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscar4KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldBuscar4, 20, 0);
    }//GEN-LAST:event_jTextFieldBuscar4KeyTyped

    private void rSButtonMetroLimpiar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar7ActionPerformed
        limpiarComponentes(jPanelAgregarCama);
    }//GEN-LAST:event_rSButtonMetroLimpiar7ActionPerformed

    private void rSButtonMetroLimpiar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar8ActionPerformed
        limpiarComponentes(jPanelAsignarCama);
    }//GEN-LAST:event_rSButtonMetroLimpiar8ActionPerformed

    private void jTextFieldCodigo4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigo4KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldCodigo4, 20, 0);
    }//GEN-LAST:event_jTextFieldCodigo4KeyTyped

    private void jTextFieldCodigo4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCodigo4MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldCodigo4MousePressed

    private void jTextFieldCodigo4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCodigo4MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldCodigo4MouseReleased

    private void jTextAreaDescripcionCamaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaDescripcionCamaMousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextAreaDescripcionCamaMousePressed

    private void jTextAreaDescripcionCamaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaDescripcionCamaMouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextAreaDescripcionCamaMouseReleased

    private void jTextFieldBuscar5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscar5KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldBuscar5, 20, 0);
    }//GEN-LAST:event_jTextFieldBuscar5KeyTyped

    private void jTextFieldBuscar5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscar5MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscar5MousePressed

    private void jTextFieldBuscar5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscar5MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscar5MouseReleased

    private void rSButtonMetroLimpiar9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar9ActionPerformed
        limpiarComponentes(jPanelAgregarArea);
    }//GEN-LAST:event_rSButtonMetroLimpiar9ActionPerformed

    private void jTextAreaDescripcionAreaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaDescripcionAreaMousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextAreaDescripcionAreaMousePressed

    private void jTextAreaDescripcionAreaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaDescripcionAreaMouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextAreaDescripcionAreaMouseReleased

    private void jTextAreaDescripcionAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaDescripcionAreaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextAreaDescripcionAreaKeyTyped

    private void rSButtonMetroFiltrar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar6ActionPerformed
        borrarTabla(rSTablaArea);
        String filtro = jTextFieldBuscar6.getText();    
        String arregloValidar1[] = {filtro};
        
        
        if (!(validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0).equals("")))
        {
           new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0)+"</center></html>").setVisible(true);                   
        }
        
        else
        {
            ArrayList <String> empleado = (ArrayList)controladora.mostrarArea(filtro);
            if(empleado==null)
            {
                new jWarning(this, true,"<html><center>El area no existe, intente denuevo</center></html>").setVisible(true);
            }

            else
            {                
                DefaultTableModel modelo = (DefaultTableModel) rSTablaArea.getModel();
                String cedula = (empleado.get(0));
                String nombre = (empleado.get(1));
                modelo.addRow (new Object[]{cedula,nombre}); 
            }
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar6ActionPerformed

    private void jTextFieldBuscar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscar6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscar6ActionPerformed

    private void jTextFieldBuscar6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscar6KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldBuscar6, 20, 0);
    }//GEN-LAST:event_jTextFieldBuscar6KeyTyped

    private void jTextFieldBuscar6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscar6MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscar6MousePressed

    private void jTextFieldBuscar6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscar6MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscar6MouseReleased

    private void rSButtonMetroGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroGuardarActionPerformed
        String nombre, cedula, direccion, telefono, cargo, email, codigoArea,salario,jefe;
        String[] codigoAreasolo;
        
        nombre = jTextFieldNombre.getText();
        cedula = jTextFieldCedula.getText();
        direccion = jTextFieldDireccion.getText();
        telefono = jTextFieldTelefono.getText();        
        cargo = (String)rSComboMetroCargo.getSelectedItem();
        salario = jTextFieldSalario.getText();
        email = jTextFieldEmail.getText();
        jefe = jTextFieldJefe.getText();
        codigoArea = (String)this.rSComboMetroArea.getSelectedItem();
        codigoAreasolo = codigoArea.split(",");
   
        String arregloValidar1[] = {nombre,direccion,email,jefe,cargo,cedula,telefono,codigoAreasolo[0],salario};
        String arregloValidar2[] = {nombre};
                    
        if (!(validacionTotal.validarExcepciones(arregloValidar1, 5, 3, 1).equals("")))
        {
           new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 5, 3, 1)+"</center></html>").setVisible(true);                   
        }
        
        else if(!(validacionTotal.soloLetras(arregloValidar2).equals("")))
        {
            new jWarning(this, true,"<html><center>"+validacionTotal.soloLetras(arregloValidar2)+"</center></html>").setVisible(true);                   
        }
         
        else 
        {                    
            if ("Nurse".equals(cargo) || "Enfermera".equals(cargo))
            {      
                new jEnfermera(this,true,cedula, nombre, direccion, telefono, "Enfermera", email, codigoAreasolo[0], Double.parseDouble(salario),cedula,jefe).setVisible(true);
                if(jEnfermera.opcion ==1)
                {
                    new jInformation(this, true, "<html><center> La enfermera se registro correctamente </center></html>").setVisible(true);
                    rSButtonMetroLimpiar.doClick();
                    rSComboMetroCargo.setEnabled(true);
                }
                
                else
                {
                    new jWarning(this, true, "<html><center>La enfermera no se guardo </center></html>").setVisible(true);
                    rSComboMetroCargo.setEnabled(true);
                }
            }
            
            else if ("Doctor".equals(cargo)|| "Medico".equals(cargo))
            {      
                new jMedico(this,true,cedula, nombre, direccion, telefono, "Medico", email, codigoAreasolo[0], Double.parseDouble(salario), cedula,jefe).setVisible(true);
                if(jMedico.opcion ==1)
                {
                    new jInformation(this, true, "<html><center> El medico se registro correctamente </center></html>").setVisible(true);
                    rSButtonMetroLimpiar.doClick();
                    rSComboMetroCargo.setEnabled(true);
                }
                
                else
                {
                    new jWarning(this, true, "<html><center>El medico no se guardo </center></html>").setVisible(true);
                    rSComboMetroCargo.setEnabled(true);
                }
            }            
        }
       
    }//GEN-LAST:event_rSButtonMetroGuardarActionPerformed

    private void rSButtonMetroFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrarActionPerformed
        String filtro = jTextFieldBuscar.getText();    
        String arregloValidar1[] = {filtro};
        borrarTabla(rSTablaEmpleados);
        
        if (!(validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0).equals("")))
        {
           new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 1, 0, 0)+"</center></html>").setVisible(true);                   
        }
        
        else
        {
            ArrayList <String> empleado = (ArrayList)controladora.mostrarEmpleado(filtro);
            if(empleado==null)
            {
                new jWarning(this, true,"<html><center>El empleado no existe, intente denuevo</center></html>").setVisible(true);
            }

            else
            {                
                DefaultTableModel modelo = (DefaultTableModel) rSTablaEmpleados.getModel();
                String cargo = (empleado.get(0));
                String nombre = (empleado.get(6));
                String cedula = (empleado.get(3));
                modelo.addRow (new Object[]{cedula,nombre,cargo}); 
            }
        }
    }//GEN-LAST:event_rSButtonMetroFiltrarActionPerformed

    private void rSTablaEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSTablaEmpleadosMouseClicked
        String cadena="";
        DefaultTableModel modelo = (DefaultTableModel)rSTablaEmpleados.getModel();
        int row = rSTablaEmpleados.rowAtPoint(evt.getPoint());
        if (row >= 0 && rSTablaEmpleados.isEnabled())
        {
            for (int i=0; i < rSTablaEmpleados.getColumnCount();i++)
            {
               cadena=cadena + "," +  modelo.getValueAt(row,i).toString();
            }
        }
             
        String []cedula = cadena.split(",");
        new jConfirmation(this, true, "<html><center>¿Esta seguro que desea editar el empleado ? </center></html>").setVisible(true);      
        if(jConfirmation.opcion ==1)
        {
            ocultarSliders(jPanelSliders);
            jPanelAgregarEmpleado.setVisible(true);
            editarEmpleado(cedula[1]);
            
        } 
    }//GEN-LAST:event_rSTablaEmpleadosMouseClicked

    private void jPanelAgregarEmpleadoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAgregarEmpleadoComponentShown
        controladora.mostrarAreasCombo(rSComboMetroArea);
    }//GEN-LAST:event_jPanelAgregarEmpleadoComponentShown

    private void rSButtonMetroLimpiar11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar11ActionPerformed
        limpiarComponentes(jPanelModificarEmpleado);
        borrarTabla(rSTablaEmpleados);
    }//GEN-LAST:event_rSButtonMetroLimpiar11ActionPerformed

    private void rSButtonMetroGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroGuardar1ActionPerformed
        try{
            String nombre, cedula, direccion, telefono, seguridadSocial,actividad, fechaNacimiento;
            Date fecha = rSDateChooserFechaNacimiento.getDatoFecha();
            long tiempo = fecha.getTime();
            Timestamp fechaSQL = new Timestamp(tiempo);
            
            nombre = jTextFieldNombre1.getText();
            cedula = jTextFieldCedula1.getText();
            direccion = jTextFieldDireccion1.getText();
            telefono = jTextFieldTelefono1.getText();        
   
            actividad = jTextFieldActividad.getText();
            seguridadSocial = jTextFieldSeguridad.getText();
            fechaNacimiento = fechaSQL.toString();

            String arregloValidar1[] = {nombre,direccion,actividad,seguridadSocial,cedula,telefono};
            String arregloValidar2[] = {nombre,actividad};
            
            if (!(validacionTotal.validarExcepciones(arregloValidar1, 3, 3, 0).equals("")))
            {
               new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 3, 3, 0)+"</center></html>").setVisible(true);                   
            }

            else if(!(validacionTotal.soloLetras(arregloValidar2).equals("")))
            {
                new jWarning(this, true,"<html><center>"+validacionTotal.soloLetras(arregloValidar2)+"</center></html>").setVisible(true);                   
            }
            
            else
            {
                if(controladora.existePaciente(cedula)==false)
                {
                    if(controladora.guardarPaciente(nombre, cedula, direccion, telefono, seguridadSocial, fechaNacimiento, actividad,cedula))
                    {                      
                            if(controladora.guardarHistoriaClinica(cedula))
                            {
                                new jInformation(this, true, "<html><center> El paciente se guardo correctamente </center></html>").setVisible(true);
                                limpiarComponentes(jPanelAgregarPaciente);
                            }

                            else
                            {
                                new jWarning(this, true, "<html><center> El paciente no se guardo</center></html>").setVisible(true);
                            }    
                    }
                    
                    else
                    {
                        new jWarning(this, true, "<html><center> El paciente no se guardo, ya existe un paciente con la misma seguridad social</center></html>").setVisible(true);
                    }
                }
                
                else if(controladora.existePaciente(cedula))
                {
                    controladora.modificarPaciente(nombre, cedula, direccion, telefono, seguridadSocial, fechaNacimiento, actividad);
                    new jInformation(this, true, "<html><center> El paciente se actualizo correctamente </center></html>").setVisible(true);
                }
       
            }
        }
        
        catch(Exception e)
        {
            new jWarning(this, true, "<html><center> No se ha seleccionado ninguna fecha</center></html>").setVisible(true);
        }      
    }//GEN-LAST:event_rSButtonMetroGuardar1ActionPerformed

    private void rSComboMetroListaEnfermerasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSComboMetroListaEnfermerasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSComboMetroListaEnfermerasActionPerformed

    private void rSTablaPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSTablaPacientesMouseClicked
        String cadena="";
        DefaultTableModel modelo = (DefaultTableModel)rSTablaPacientes.getModel();
        int row = rSTablaPacientes.rowAtPoint(evt.getPoint());
        if (row >= 0 && rSTablaPacientes.isEnabled())
        {
            for (int i=0; i < rSTablaPacientes.getColumnCount();i++)
            {
               cadena=cadena + "," +  modelo.getValueAt(row,i).toString();
            }
        }
             
        String []cedula = cadena.split(",");
        new jConfirmation(this, true, "<html><center>¿Esta seguro que desea editar el paciente ? </center></html>").setVisible(true);      
        if(jConfirmation.opcion ==1)
        {
            ocultarSliders(jPanelSliders);
            jPanelAgregarPaciente.setVisible(true);
            editarPaciente(cedula[1]);
            
        } 
    }//GEN-LAST:event_rSTablaPacientesMouseClicked

    private void jTextFieldSeguridadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSeguridadMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSeguridadMousePressed

    private void jTextFieldSeguridadMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSeguridadMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSeguridadMouseReleased

    private void jTextFieldSeguridadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSeguridadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSeguridadKeyTyped

    private void rSButtonMetroFiltrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar1ActionPerformed
        String filtro = jTextFieldBuscar1.getText();    
        String arregloValidar1[] = {filtro};
        borrarTabla(rSTablaPacientes);
        if (!(validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0).equals("")))
        {
           new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0)+"</center></html>").setVisible(true);                   
        }
        
        else
        {
            ArrayList <String> paciente = (ArrayList)controladora.mostrarPaciente(filtro);
            if(paciente==null)
            {
                new jWarning(this, true,"<html><center>El paciente no existe, intente denuevo</center></html>").setVisible(true);
            }

            else
            {                
                DefaultTableModel modelo = (DefaultTableModel) rSTablaPacientes.getModel();
                String cargo = (paciente.get(2));
                String nombre = (paciente.get(4));
                String cedula = (paciente.get(3));
                modelo.addRow (new Object[]{cedula,nombre,cargo}); 
            }
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar1ActionPerformed

    private void rSButtonMetroGuardar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroGuardar4ActionPerformed
            String nombre = jTextFieldNombre4.getText();
            String codigo = jTextFieldCodigo2.getText();
            
            String arregloValidar1[] = {nombre,codigo};
            String arregloValidar2[] = {nombre};
            
            if (!(validacionTotal.validarExcepciones(arregloValidar1, 1, 1, 0).equals("")))
            {
               new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 1, 1, 0)+"</center></html>").setVisible(true);                   
            }

            else if(!(validacionTotal.soloLetras(arregloValidar2).equals("")))
            {
                new jWarning(this, true,"<html><center>"+validacionTotal.soloLetras(arregloValidar2)+"</center></html>").setVisible(true);                   
            }
            
            else
            {
                if(controladora.existeHabilidad(codigo)==false)
                {
                    if(controladora.guardarHabilidades(codigo,nombre))
                    {
                        new jInformation(this, true, "<html><center> La habilidad se guardo correctamente </center></html>").setVisible(true);
                        limpiarComponentes(jPanelAgregarHabilidad);
                    }                


                    else
                    {
                        new jWarning(this, true, "<html><center>La habilidad no se guardo</center></html>").setVisible(true);
                    }  
                }
                
                else if(controladora.existeHabilidad(codigo))
                {
                    controladora.modificarHabilidades(codigo, nombre);
                    new jInformation(this, true, "<html><center> La habilidad se actualizo correctamente </center></html>").setVisible(true);
                }
            }
    }//GEN-LAST:event_rSButtonMetroGuardar4ActionPerformed

    private void jTextFieldBuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscar3ActionPerformed

    private void rSButtonMetroFiltrar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar3ActionPerformed
        borrarTabla(rSTablaHabilidades);
        String filtro = jTextFieldBuscar3.getText();    
        String arregloValidar1[] = {filtro};
        
        
        if (!(validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0).equals("")))
        {
           new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0)+"</center></html>").setVisible(true);                   
        }
        
        else
        {
            ArrayList <String> empleado = (ArrayList)controladora.mostrarHabilidad(filtro);
            if(empleado==null)
            {
                new jWarning(this, true,"<html><center>La habilidad no existe, intente denuevo</center></html>").setVisible(true);
            }

            else
            {                
                DefaultTableModel modelo = (DefaultTableModel) rSTablaHabilidades.getModel();
                String nombre = (empleado.get(1));
                String cedula = (empleado.get(0));
                modelo.addRow (new Object[]{cedula,nombre}); 
            }
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar3ActionPerformed

    private void rSTablaHabilidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSTablaHabilidadesMouseClicked
        String cadena="";
        DefaultTableModel modelo = (DefaultTableModel)rSTablaHabilidades.getModel();
        int row = rSTablaHabilidades.rowAtPoint(evt.getPoint());
        if (row >= 0 && rSTablaHabilidades.isEnabled())
        {
            for (int i=0; i < rSTablaHabilidades.getColumnCount();i++)
            {
               cadena=cadena + "," +  modelo.getValueAt(row,i).toString();
            }
        }
             
        String []cedula = cadena.split(",");
        new jConfirmation(this, true, "<html><center>¿Esta seguro que desea editar la habilidad? </center></html>").setVisible(true);      
        if(jConfirmation.opcion ==1)
        {
            ocultarSliders(jPanelSliders);
            jPanelAgregarHabilidad.setVisible(true);
            editarHabilidad(cedula[1]);       
        } 
    }//GEN-LAST:event_rSTablaHabilidadesMouseClicked

    private void jTextFieldNombre4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombre4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombre4ActionPerformed

    private void rSButtonMetroLimpiar13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar13ActionPerformed
        limpiarComponentes(jPanelModificarHabilidad);
    }//GEN-LAST:event_rSButtonMetroLimpiar13ActionPerformed

    private void jPanelAsignarHabilidadComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAsignarHabilidadComponentShown
        
        controladora.mostrarHabilidadesCombo(rSComboMetroListaHabilidades);
        controladora.mostrarEnfermerasCombo(rSComboMetroListaEnfermeras);
    }//GEN-LAST:event_jPanelAsignarHabilidadComponentShown

    private void rSButtonAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonAsignarActionPerformed
        String codigo,cedula;
        String[] codigoHabilidad;
        codigo = (String)this.rSComboMetroListaHabilidades.getSelectedItem();
        cedula = (String)this.rSComboMetroListaEnfermeras.getSelectedItem();
        codigoHabilidad = codigo.split(",");
        
        if(controladora.habilidadesEnfermeras(cedula, codigoHabilidad[0]))
        {
            new jInformation(this, true, "<html><center>La habilidad se inserto correctamente </center></html>").setVisible(true);
        }
        
        else
        {
            new jInformation(this, true, "<html><center>No fue posible insertar la habilidad: Ya existe esta habilidad para esta enfermera </center></html>").setVisible(true);
        }
    }//GEN-LAST:event_rSButtonAsignarActionPerformed

    private void rSButtonMetroMostrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroMostrar2ActionPerformed
        limpiarComponentes(jPanelModificarMedicina);
        borrarTabla(rSTablaMedicamentos);
    }//GEN-LAST:event_rSButtonMetroMostrar2ActionPerformed

    private void rSButtonMetroGuardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroGuardar2ActionPerformed
        String nombre = jTextFieldNombre2.getText();
        String codigo = jTextFieldCodigo.getText();
        String costo = jTextFieldCosto.getText();
        String descripcion = jTextAreaDescripcionMedicina.getText();

        String arregloValidar1[] = {nombre,codigo,costo};
        String arregloValidar2[] = {nombre};

        if (!(validacionTotal.validarExcepciones(arregloValidar1, 1, 1, 1).equals("")))
        {
           new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 1, 1, 1)+"</center></html>").setVisible(true);                   
        }

        else if(!(validacionTotal.soloLetras(arregloValidar2).equals("")))
        {
            new jWarning(this, true,"<html><center>"+validacionTotal.soloLetras(arregloValidar2)+"</center></html>").setVisible(true);                   
        }

        else
        {
            if(controladora.existeMedicina(codigo)==false)
            {
                if(controladora.guardarMedicamentos(codigo,nombre,descripcion,costo))
                {
                    new jInformation(this, true, "<html><center> La medicina se guardo correctamente </center></html>").setVisible(true);
                    limpiarComponentes(jPanelAgregarMedicina);
                }                


                else
                {
                    new jWarning(this, true, "<html><center>La medicina no se guardo</center></html>").setVisible(true);
                }  
            }

            else if(controladora.existeMedicina(codigo))
            {
                controladora.modificarMedicamentos(codigo, nombre,descripcion,costo);
                new jInformation(this, true, "<html><center> La habilidad se actualizo correctamente </center></html>").setVisible(true);
            }
        }
    }//GEN-LAST:event_rSButtonMetroGuardar2ActionPerformed

    private void rSButtonMetroFiltrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar2ActionPerformed
        borrarTabla(rSTablaMedicamentos);
        String filtro = jTextFieldBuscar2.getText();    
        String arregloValidar1[] = {filtro};
        
        
        if (!(validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0).equals("")))
        {
           new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0)+"</center></html>").setVisible(true);                   
        }
        
        else
        {
            ArrayList <String> empleado = (ArrayList)controladora.mostrarMedicamento(filtro);
            if(empleado==null)
            {
                new jWarning(this, true,"<html><center>La habilidad no existe, intente denuevo</center></html>").setVisible(true);
            }

            else
            {                
                DefaultTableModel modelo = (DefaultTableModel) rSTablaMedicamentos.getModel();
                String cedula = (empleado.get(0));
                String nombre = (empleado.get(1));
                String costo = (empleado.get(3));
                modelo.addRow (new Object[]{cedula,nombre,costo}); 
            }
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar2ActionPerformed

    private void rSTablaMedicamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSTablaMedicamentosMouseClicked
        String cadena="";
        DefaultTableModel modelo = (DefaultTableModel)rSTablaMedicamentos.getModel();
        int row = rSTablaMedicamentos.rowAtPoint(evt.getPoint());
        if (row >= 0 && rSTablaMedicamentos.isEnabled())
        {
            for (int i=0; i < rSTablaMedicamentos.getColumnCount();i++)
            {
               cadena=cadena + "," +  modelo.getValueAt(row,i).toString();
            }
        }
             
        String []cedula = cadena.split(",");
        new jConfirmation(this, true, "<html><center>¿Esta seguro que desea editar la medicina? </center></html>").setVisible(true);      
        if(jConfirmation.opcion ==1)
        {
            ocultarSliders(jPanelSliders1);
            jPanelAgregarMedicina.setVisible(true);
            editarMedicina(cedula[1]);       
        } 
    }//GEN-LAST:event_rSTablaMedicamentosMouseClicked

    private void rSButtonMetroMostrar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroMostrar4ActionPerformed
        limpiarComponentes(jPanelModificarCampana);
        borrarTabla(rSTablaCampana);
    }//GEN-LAST:event_rSButtonMetroMostrar4ActionPerformed

    private void jPanelAgregarCampanaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAgregarCampanaComponentShown
         controladora.mostrarMedicosCombo(rSComboMetroDoctorEncargado);
    }//GEN-LAST:event_jPanelAgregarCampanaComponentShown

    private void rSButtonMetroGuardar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroGuardar3ActionPerformed
            
        try{    
            String nombre = jTextFieldNombre3.getText();
            String codigo = jTextFieldCodigo3.getText();
            Date fecha = rSDateChooserFecha.getDatoFecha();
            long tiempo = fecha.getTime();
            Timestamp fechaSQL1 = new Timestamp(tiempo);

            String doctor = (String)this.rSComboMetroDoctorEncargado.getSelectedItem();
            String []datosDoctor = doctor.split(",");
            String cedulaDoctor = datosDoctor[0];
            String objetivo = (String)this.rSComboMetroObjetivo.getSelectedItem();
            String fecha1 = fechaSQL1.toString();

            String arregloValidar1[] = {nombre,objetivo,codigo,cedulaDoctor};
            String arregloValidar2[] = {nombre};

            if (!(validacionTotal.validarExcepciones(arregloValidar1,2 , 2, 0).equals("")))
            {
               new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 2, 2, 0)+"</center></html>").setVisible(true);                   
            }

            else if(!(validacionTotal.soloLetras(arregloValidar2).equals("")))
            {
                new jWarning(this, true,"<html><center>"+validacionTotal.soloLetras(arregloValidar2)+"</center></html>").setVisible(true);                   
            }

            else
            {
                if(controladora.existeCampa(codigo)==false)
                {
                    if(controladora.guardarCampania(codigo,nombre,fecha1,objetivo,cedulaDoctor))
                    {
                        new jInformation(this, true, "<html><center> La campaña se guardo correctamente </center></html>").setVisible(true);
                        limpiarComponentes(jPanelAgregarCampana);
                    }                


                    else
                    {
                        new jWarning(this, true, "<html><center>La campaña no se guardo</center></html>").setVisible(true);
                    }  
                }

                else if(controladora.existeCampa(codigo))
                {
                    
                    controladora.modificarCampania(codigo, nombre,fecha1,objetivo,cedulaDoctor);
                    new jInformation(this, true, "<html><center> La campaña se actualizo correctamente </center></html>").setVisible(true);
                }
            }
        }
        catch ( Exception e)
        {
            new jWarning(this, true, "<html><center> No se ha seleccionado algun item</center></html>").setVisible(true);
        }
    }//GEN-LAST:event_rSButtonMetroGuardar3ActionPerformed

    private void rSButtonMetroFiltrar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar4ActionPerformed
        borrarTabla(rSTablaCampana);
        String filtro = jTextFieldBuscar4.getText();    
        String arregloValidar1[] = {filtro};
        
        
        if (!(validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0).equals("")))
        {
           new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0)+"</center></html>").setVisible(true);                   
        }
        
        else
        {
            ArrayList <String> empleado = (ArrayList)controladora.mostrarCampa(filtro);
            if(empleado==null)
            {
                new jWarning(this, true,"<html><center>La campañana no existe, intente denuevo</center></html>").setVisible(true);
            }

            else
            {                
                DefaultTableModel modelo = (DefaultTableModel) rSTablaCampana.getModel();
                String cedula = (empleado.get(0));
                String nombre = (empleado.get(1));
                String objetivo = (empleado.get(2));
                modelo.addRow (new Object[]{cedula,nombre,objetivo}); 
            }
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar4ActionPerformed

    private void rSTablaCampanaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSTablaCampanaMouseClicked
       String cadena="";
        DefaultTableModel modelo = (DefaultTableModel)rSTablaCampana.getModel();
        int row = rSTablaCampana.rowAtPoint(evt.getPoint());
        if (row >= 0 && rSTablaCampana.isEnabled())
        {
            for (int i=0; i < rSTablaCampana.getColumnCount();i++)
            {
               cadena=cadena + "," +  modelo.getValueAt(row,i).toString();
            }
        }
             
        String []cedula = cadena.split(",");
        new jConfirmation(this, true, "<html><center>¿Esta seguro que desea editar la campaña? </center></html>").setVisible(true);      
        if(jConfirmation.opcion ==1)
        {
            ocultarSliders(jPanelSliders2);
            jPanelAgregarCampana.setVisible(true);
            editarCampana(cedula[1]);       
        } 
    }//GEN-LAST:event_rSTablaCampanaMouseClicked

    private void jPanelAsignarCampanaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAsignarCampanaComponentShown
        controladora.mostrarPacientesCombo(rSComboMetroListaPacientes);
        controladora.mostrarCampaCombo(rSComboMetroListaCampanas);       
    }//GEN-LAST:event_jPanelAsignarCampanaComponentShown

    private void rSButtonAsignar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonAsignar1ActionPerformed
        String cedula, codigoCampa;
        String[] codigoCedulaSolo;
        String[] codigoCampaSolo;
        cedula = (String)this.rSComboMetroListaPacientes.getSelectedItem();
        codigoCampa = (String)this.rSComboMetroListaCampanas.getSelectedItem();
        codigoCedulaSolo = cedula.split(",");
        codigoCampaSolo = codigoCampa.split(",");
        
        if(controladora.registrarPacienteCampania(codigoCampaSolo[0], codigoCedulaSolo[0]))
        {
            new jInformation(this, true, "<html><center>El paciente se ingreso correctamente a la campana </center></html>").setVisible(true);
        }
        
        else
        {
            new jInformation(this, true, "<html><center>No fue posible insertar el paciente, ya se encuentra dentro de la campaña</center></html>").setVisible(true);
        }
     
    }//GEN-LAST:event_rSButtonAsignar1ActionPerformed

    private void rSButtonMetroMostrar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroMostrar5ActionPerformed
        limpiarComponentes(jPanelModificarCama);
        borrarTabla(rSTablaCama);
    }//GEN-LAST:event_rSButtonMetroMostrar5ActionPerformed

    private void rSButtonMetroMostrar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroMostrar6ActionPerformed
        limpiarComponentes(jPanelModificarArea);
        borrarTabla(rSTablaArea);
    }//GEN-LAST:event_rSButtonMetroMostrar6ActionPerformed

    private void jPanelAgregarCamaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAgregarCamaComponentShown
        controladora.mostrarAreasCombo(rSComboArea2);
        jCheckBoxDisponible.setVisible(false);
    }//GEN-LAST:event_jPanelAgregarCamaComponentShown

    private void rSButtonMetroGuardar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroGuardar5ActionPerformed

        String codigo = jTextFieldCodigo4.getText();       
        String area = (String)this.rSComboArea2.getSelectedItem();
        String descripcion = jTextAreaDescripcionCama.getText();
        String []datosArea = area.split(",");
        String codigoArea = datosArea[0];
 
        String arregloValidar1[] = {codigo};

        if (!(validacionTotal.validarExcepciones(arregloValidar1,0 , 1, 0).equals("")))
        {
           new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0)+"</center></html>").setVisible(true);                   
        }
       
        else
        {
            if(controladora.existeCama(codigo)==false)
            {
                if(controladora.guardarCama(codigo,codigoArea,descripcion))
                {
                    new jInformation(this, true, "<html><center> La cama se guardo correctamente </center></html>").setVisible(true);
                    limpiarComponentes(jPanelAgregarCama);
                    }                

                else
                {
                    new jWarning(this, true, "<html><center>La cama no se guardo</center></html>").setVisible(true);
                }  
            }

            else if(controladora.existeCama(codigo))
            {   
                jCheckBoxDisponible.setVisible(true);
                controladora.modificarCama(codigo, codigoArea,descripcion,jCheckBoxDisponible.isSelected());
                System.out.println(jCheckBoxDisponible.isSelected());
                new jInformation(this, true, "<html><center> La cama se actualizo correctamente </center></html>").setVisible(true);
            }
        }
    }//GEN-LAST:event_rSButtonMetroGuardar5ActionPerformed

    private void rSButtonMetroFiltrar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar5ActionPerformed
        borrarTabla(rSTablaCama);
        String filtro = jTextFieldBuscar5.getText();    
        String arregloValidar1[] = {filtro};
        
        
        if (!(validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0).equals("")))
        {
           new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0)+"</center></html>").setVisible(true);                   
        }
        
        else
        {
            ArrayList <String> empleado = (ArrayList)controladora.mostrarCama(filtro);
            if(empleado==null)
            {
                new jWarning(this, true,"<html><center>La cama no existe, intente denuevo</center></html>").setVisible(true);
            }

            else
            {                
                DefaultTableModel modelo = (DefaultTableModel) rSTablaCama.getModel();
                String cedula = (empleado.get(0));
                String nombre = (empleado.get(3));
                modelo.addRow (new Object[]{cedula,nombre}); 
            }
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar5ActionPerformed

    private void rSTablaCamaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSTablaCamaMouseClicked
        String cadena="";
        DefaultTableModel modelo = (DefaultTableModel)rSTablaCama.getModel();
        int row = rSTablaCama.rowAtPoint(evt.getPoint());
        if (row >= 0 && rSTablaCama.isEnabled())
        {
            for (int i=0; i < rSTablaCama.getColumnCount();i++)
            {
               cadena=cadena + "," +  modelo.getValueAt(row,i).toString();
            }
        }
             
        String []cedula = cadena.split(",");
        new jConfirmation(this, true, "<html><center>¿Esta seguro que desea editar la cama? </center></html>").setVisible(true);      
        if(jConfirmation.opcion ==1)
        {
            ocultarSliders(jPanelSliders3);
            jPanelAgregarCama.setVisible(true);
            editarCama(cedula[1]);       
        } 
    }//GEN-LAST:event_rSTablaCamaMouseClicked

    private void jPanelAsignarCamaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAsignarCamaComponentShown
        controladora.mostrarCamasCombo(rSComboMetroListaCamas);
        controladora.mostrarPacientesCombo(rSComboMetroListaPacientes1);
    }//GEN-LAST:event_jPanelAsignarCamaComponentShown

    private void rSComboMetroListaCamasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSComboMetroListaCamasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSComboMetroListaCamasActionPerformed

    private void rSButtonAsignar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonAsignar2ActionPerformed
        try{
            String cedula, codigoCampa;
            String[] codigoCedulaSolo;
            String[] codigoCampaSolo;
            Date fecha = rSDateChooserFechaAsignacion.getDatoFecha();
            long tiempo = fecha.getTime();
            Timestamp fechaSQL = new Timestamp(tiempo);
            String fecha2 = fechaSQL.toString();
            cedula = (String)this.rSComboMetroListaPacientes1.getSelectedItem();
            codigoCampa = (String)this.rSComboMetroListaCamas.getSelectedItem();
            codigoCedulaSolo = cedula.split(",");
            codigoCampaSolo = codigoCampa.split(",");

            if(controladora.camasPacientes(codigoCedulaSolo[0],codigoCampaSolo[0],fecha2 ))
            {
                new jInformation(this, true, "<html><center>La cama se asigno correctamente </center></html>").setVisible(true);
            }

            else
            {
                new jWarning(this, true, "<html><center>No fue posible asignar la cama ya fue asignada </center></html>").setVisible(true);
            }
        }
        catch(Exception e)
        {
            new jWarning(this, true, "<html><center> No se ha seleccionado algun item</center></html>").setVisible(true);
        }
    }//GEN-LAST:event_rSButtonAsignar2ActionPerformed

    private void rSButtonMetroGuardar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroGuardar6ActionPerformed
        
        String nombre = jTextFieldNombre5.getText();
        String codigo = jTextFieldCodigo5.getText();       
        String descripcion = jTextAreaDescripcionArea.getText();
  
        String arregloValidar1[] = {nombre,codigo,descripcion};

        if (!(validacionTotal.validarExcepciones(arregloValidar1,3 , 0, 0).equals("")))
        {
           new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 3, 0, 0)+"</center></html>").setVisible(true);                   
        }
       
        else
        {
            if(controladora.existeArea(codigo)==false)
            {
                if(controladora.guardarArea(codigo,nombre,descripcion))
                {
                    controladora.guardarArea(codigo,nombre,descripcion);
                    new jInformation(this, true, "<html><center> El area se guardo correctamente </center></html>").setVisible(true);
                    limpiarComponentes(jPanelAgregarArea);
                }                


                else
                {
                    new jWarning(this, true, "<html><center>El area no se guardo</center></html>").setVisible(true);
                }  
            }

            else if(controladora.existeArea(codigo))
            {   
               
                controladora.modificarArea(codigo,nombre,descripcion);
                new jInformation(this, true, "<html><center> El area se actualizo correctamente </center></html>").setVisible(true);
            }
        }
    }//GEN-LAST:event_rSButtonMetroGuardar6ActionPerformed

    private void rSTablaAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSTablaAreaMouseClicked
        String cadena="";
        DefaultTableModel modelo = (DefaultTableModel)rSTablaArea.getModel();
        int row = rSTablaArea.rowAtPoint(evt.getPoint());
        if (row >= 0 && rSTablaArea.isEnabled())
        {
            for (int i=0; i < rSTablaArea.getColumnCount();i++)
            {
               cadena=cadena + "," +  modelo.getValueAt(row,i).toString();
            }
        }
             
        String []cedula = cadena.split(",");
        new jConfirmation(this, true, "<html><center>¿Esta seguro que desea editar el area? </center></html>").setVisible(true);      
        if(jConfirmation.opcion ==1)
        {
            ocultarSliders(jPanelSliders4);
            jPanelAgregarArea.setVisible(true);
            editarArea(cedula[1]);       
        } 
    }//GEN-LAST:event_rSTablaAreaMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        todoListo();
    }//GEN-LAST:event_formWindowActivated

    private void jRadioSQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioSQLActionPerformed
        extension = ".sql";
        jLabelRuta.setText( rutaRespaldo + extension);
    }//GEN-LAST:event_jRadioSQLActionPerformed

    private void jRadioBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioBackupActionPerformed
        extension = ".backup";
        jLabelRuta.setText( rutaRespaldo + extension);
    }//GEN-LAST:event_jRadioBackupActionPerformed

    private void jRadioParcialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioParcialActionPerformed


            //Cargar tablas base de datos en 'ListadoEmpleados Verde'
            jTableTablas.setModel(controladora.ConsultarTablas() );
            //Inicializar 'ListadoEmpleados Rojo' (Limpiar)
            jTableTablasBackup.setModel( new DefaultListModel());

            //Ocultar botones (<, >) ya que no se utilizaran al realizar un respaldo de todas las tablas de la base de datos
            rSButtonMetroDerecha.setVisible(true);
            rSButtonMetroIzquierda.setVisible(true);
        
    }//GEN-LAST:event_jRadioParcialActionPerformed

    private void jRadioTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioTotalActionPerformed
        //Cargar tablas base de datos en 'ListadoEmpleados Verde'
        jTableTablasBackup.setModel(controladora.ConsultarTablas() );
        //Inicializar 'ListadoEmpleados Rojo' (Limpiar)
        jTableTablas.setModel( new DefaultListModel() );

        //Ocultar botones (<, >) ya que no se utilizaran al realizar un respaldo de todas las tablas de la base de datos
        rSButtonMetroDerecha.setVisible(false);
        rSButtonMetroIzquierda.setVisible(false);
    }//GEN-LAST:event_jRadioTotalActionPerformed

    private void rSButtonMetroBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroBackupActionPerformed
        if(todoListo())
        {
            ejecutarBackup(); 
        }
        
        else
        {
            new jWarning(this, true, "<html><center>Verifique toda la informacion del formulario este diligenciada </center></html>").setVisible(true);
        }
        
    }//GEN-LAST:event_rSButtonMetroBackupActionPerformed

    private void rSButtonMetroIzquierdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroIzquierdaActionPerformed
        moverSeleccion(jTableTablasBackup, jTableTablas);
        
    }//GEN-LAST:event_rSButtonMetroIzquierdaActionPerformed

    private void rSButtonMetroDerechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroDerechaActionPerformed
        moverSeleccion(jTableTablas, jTableTablasBackup);
    }//GEN-LAST:event_rSButtonMetroDerechaActionPerformed

    private void rSButtonChooserRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonChooserRutaActionPerformed
        rutaRespaldo = seleccionarRuta(RESPALDO);
        jLabelRuta.setText(rutaRespaldo + extension);
    }//GEN-LAST:event_rSButtonChooserRutaActionPerformed

    private void jPanelBackupBDComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelBackupBDComponentShown
        reiniciarValores();
        jRadioParcial.setSelected(true);
        jTableTablas.setModel(controladora.ConsultarTablas());
        jTableTablasBackup.setModel( new DefaultListModel() );
        jLabelSO.setText( System.getProperty("os.name"));
    }//GEN-LAST:event_jPanelBackupBDComponentShown

    private void jLabelHome7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelHome7MouseClicked
        jPanelBackupBD.setVisible(false);
        jPanelPrincipal.setVisible(true);
    }//GEN-LAST:event_jLabelHome7MouseClicked

    private void rSButtonPaneHome7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonPaneHome7MouseClicked

    }//GEN-LAST:event_rSButtonPaneHome7MouseClicked

    private void rSButtonPaneHome6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonPaneHome6MouseClicked
        jPanelTools.setVisible(false);
        jPanelMenu.setVisible(true);
    }//GEN-LAST:event_rSButtonPaneHome6MouseClicked

    private void rSButtonMetroBackupBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroBackupBDActionPerformed
        jPanelPrincipal.setVisible(false);
        jPanelBackupBD.setVisible(true);
    }//GEN-LAST:event_rSButtonMetroBackupBDActionPerformed

    private void rSButtonMetroRestoreBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroRestoreBDActionPerformed
        ejecutarRestauracion( seleccionarRuta(RESTAURACION) );
    }//GEN-LAST:event_rSButtonMetroRestoreBDActionPerformed

    private void rSButtonMetroCleanDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroCleanDBActionPerformed
        new jConfirmation(this, true, "<html><center>¿Esta seguro limpiar la BD (esta accion no puede revertirse) ? </center></html>").setVisible(true);
        if(jConfirmation.opcion ==1)
        {
            if(controladora.limpiarTablas())
            {
                new jInformation(this, true, "<html><center>Se limpio la BD correctamente </center></html>").setVisible(true);
            }

            else
            {
                new jWarning(this, true, "<html><center>la BD no se pudo limpiar, por favor intente mas tarde</center></html>").setVisible(true);
            }
        }
    }//GEN-LAST:event_rSButtonMetroCleanDBActionPerformed

    private void jTextFieldBuscar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscar1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscar1KeyReleased

    private void rSButtonMetroListWorkerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroListWorkerActionPerformed
        this.setVisible(false);
        new ListadoEmpleados().setVisible(true);
    }//GEN-LAST:event_rSButtonMetroListWorkerActionPerformed

    private void rSButtonMetroHistoryClinicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroHistoryClinicActionPerformed
        this.setVisible(false);
        new HistoriaClinica().setVisible(true);
    }//GEN-LAST:event_rSButtonMetroHistoryClinicActionPerformed

    private void rSButtonMetroNumberQuoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroNumberQuoteActionPerformed
        this.setVisible(false);
        new CitasMes().setVisible(true);
    }//GEN-LAST:event_rSButtonMetroNumberQuoteActionPerformed

    private void rSButtonPaneHome8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonPaneHome8MouseClicked
        jPanelReports.setVisible(false);
        jPanelMenu.setVisible(true);
    }//GEN-LAST:event_rSButtonPaneHome8MouseClicked

    private void rSButtonMetroMonthlyAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroMonthlyAgendaActionPerformed
        this.setVisible(false);
        new AgendaMensual().setVisible(true);
    }//GEN-LAST:event_rSButtonMetroMonthlyAgendaActionPerformed

    private void jTextFieldDireccion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDireccion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDireccion1ActionPerformed

    private void rSButtonMetro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro1ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro1.getText());
    }//GEN-LAST:event_rSButtonMetro1ActionPerformed

    private void rSButtonMetro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro2ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro2.getText());
    }//GEN-LAST:event_rSButtonMetro2ActionPerformed

    private void rSButtonMetro3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro3ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro3.getText());
    }//GEN-LAST:event_rSButtonMetro3ActionPerformed

    private void rSButtonMetro4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro4ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro4.getText());
    }//GEN-LAST:event_rSButtonMetro4ActionPerformed

    private void rSButtonMetro5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro5ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro5.getText());
    }//GEN-LAST:event_rSButtonMetro5ActionPerformed

    private void rSButtonMetro6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro6ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro6.getText());
    }//GEN-LAST:event_rSButtonMetro6ActionPerformed

    private void rSButtonMetro7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro7ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro7.getText());
    }//GEN-LAST:event_rSButtonMetro7ActionPerformed

    private void rSButtonMetro8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro8ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro8.getText());
    }//GEN-LAST:event_rSButtonMetro8ActionPerformed

    private void rSButtonMetro9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro9ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro9.getText());
    }//GEN-LAST:event_rSButtonMetro9ActionPerformed

    private void rSButtonMetro10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro10ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro10.getText());
    }//GEN-LAST:event_rSButtonMetro10ActionPerformed

    private void rSButtonMetro11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro11ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro11.getText());
    }//GEN-LAST:event_rSButtonMetro11ActionPerformed

    private void rSButtonMetro12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro12ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro12.getText());
    }//GEN-LAST:event_rSButtonMetro12ActionPerformed

    private void rSButtonMetro13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro13ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro13.getText());
    }//GEN-LAST:event_rSButtonMetro13ActionPerformed

    private void rSButtonMetro14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro14ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro14.getText());
    }//GEN-LAST:event_rSButtonMetro14ActionPerformed

    private void rSButtonMetro15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro15ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro15.getText());
    }//GEN-LAST:event_rSButtonMetro15ActionPerformed

    private void rSButtonMetro16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro16ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro16.getText());
    }//GEN-LAST:event_rSButtonMetro16ActionPerformed

    private void rSButtonMetro17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro17ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro17.getText());
    }//GEN-LAST:event_rSButtonMetro17ActionPerformed

    private void rSButtonMetro18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro18ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro18.getText());
    }//GEN-LAST:event_rSButtonMetro18ActionPerformed

    private void rSButtonMetro19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro19ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro19.getText());
    }//GEN-LAST:event_rSButtonMetro19ActionPerformed

    private void rSButtonMetro20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro20ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro20.getText());
    }//GEN-LAST:event_rSButtonMetro20ActionPerformed

    private void rSButtonMetro21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro21ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro21.getText());
    }//GEN-LAST:event_rSButtonMetro21ActionPerformed

    private void rSButtonMetro22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro22ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro22.getText());
    }//GEN-LAST:event_rSButtonMetro22ActionPerformed

    private void rSButtonMetro23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro23ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro23.getText());
    }//GEN-LAST:event_rSButtonMetro23ActionPerformed

    private void rSButtonMetro24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro24ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro24.getText());
    }//GEN-LAST:event_rSButtonMetro24ActionPerformed

    private void rSButtonMetro25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro25ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro25.getText());
    }//GEN-LAST:event_rSButtonMetro25ActionPerformed

    private void rSButtonMetro26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro26ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro26.getText());
    }//GEN-LAST:event_rSButtonMetro26ActionPerformed

    private void rSButtonMetro27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro27ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro27.getText());
    }//GEN-LAST:event_rSButtonMetro27ActionPerformed

    private void rSButtonMetro28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro28ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro28.getText());
    }//GEN-LAST:event_rSButtonMetro28ActionPerformed

    private void rSButtonMetro29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro29ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro29.getText());
    }//GEN-LAST:event_rSButtonMetro29ActionPerformed

    private void rSButtonMetro30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro30ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro30.getText());
    }//GEN-LAST:event_rSButtonMetro30ActionPerformed

    private void rSButtonMetro31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro31ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro31.getText());
    }//GEN-LAST:event_rSButtonMetro31ActionPerformed

    private void rSButtonMetro32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro32ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro32.getText());
    }//GEN-LAST:event_rSButtonMetro32ActionPerformed

    private void rSButtonMetro33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro33ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro33.getText());
    }//GEN-LAST:event_rSButtonMetro33ActionPerformed

    private void rSButtonMetro34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro34ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro34.getText());
    }//GEN-LAST:event_rSButtonMetro34ActionPerformed

    private void rSButtonMetro35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro35ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro35.getText());
    }//GEN-LAST:event_rSButtonMetro35ActionPerformed

    private void rSButtonMetro36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro36ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro36.getText());
    }//GEN-LAST:event_rSButtonMetro36ActionPerformed

    private void rSButtonMetro37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro37ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro37.getText());
    }//GEN-LAST:event_rSButtonMetro37ActionPerformed

    private void rSButtonMetro38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro38ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro38.getText());
    }//GEN-LAST:event_rSButtonMetro38ActionPerformed

    private void rSButtonMetro39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro39ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro39.getText());
    }//GEN-LAST:event_rSButtonMetro39ActionPerformed

    private void rSButtonMetro40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro40ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro40.getText());
    }//GEN-LAST:event_rSButtonMetro40ActionPerformed

    private void rSButtonMetro41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro41ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro41.getText());
    }//GEN-LAST:event_rSButtonMetro41ActionPerformed

    private void rSButtonMetro42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro42ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro42.getText());
    }//GEN-LAST:event_rSButtonMetro42ActionPerformed

    private void rSButtonMetro43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro43ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro43.getText());
    }//GEN-LAST:event_rSButtonMetro43ActionPerformed

    private void rSButtonMetro44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro44ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro44.getText());
    }//GEN-LAST:event_rSButtonMetro44ActionPerformed

    private void rSButtonMetro45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro45ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro45.getText());
    }//GEN-LAST:event_rSButtonMetro45ActionPerformed

    private void rSButtonMetro46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro46ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro46.getText());
    }//GEN-LAST:event_rSButtonMetro46ActionPerformed

    private void rSButtonMetro47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro47ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro47.getText());
    }//GEN-LAST:event_rSButtonMetro47ActionPerformed

    private void rSButtonMetro48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro48ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro48.getText());
    }//GEN-LAST:event_rSButtonMetro48ActionPerformed

    private void rSButtonMetro49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro49ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro49.getText());
    }//GEN-LAST:event_rSButtonMetro49ActionPerformed

    private void rSButtonMetro50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro50ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro50.getText());
    }//GEN-LAST:event_rSButtonMetro50ActionPerformed

    private void rSButtonMetro51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro51ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro51.getText());
    }//GEN-LAST:event_rSButtonMetro51ActionPerformed

    private void rSButtonMetro52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro52ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro52.getText());
    }//GEN-LAST:event_rSButtonMetro52ActionPerformed

    private void rSButtonMetro53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro53ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro53.getText());
    }//GEN-LAST:event_rSButtonMetro53ActionPerformed

    private void rSButtonMetro54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro54ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro54.getText());
    }//GEN-LAST:event_rSButtonMetro54ActionPerformed

    private void rSButtonMetro55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro55ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro55.getText());
    }//GEN-LAST:event_rSButtonMetro55ActionPerformed

    private void rSButtonMetro56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro56ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro56.getText());
    }//GEN-LAST:event_rSButtonMetro56ActionPerformed

    private void rSButtonMetro57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro57ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro57.getText());
    }//GEN-LAST:event_rSButtonMetro57ActionPerformed

    private void rSButtonMetro58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro58ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro58.getText());
    }//GEN-LAST:event_rSButtonMetro58ActionPerformed

    private void rSButtonMetro59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro59ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro59.getText());
    }//GEN-LAST:event_rSButtonMetro59ActionPerformed

    private void rSButtonMetro60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro60ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro60.getText());
    }//GEN-LAST:event_rSButtonMetro60ActionPerformed

    private void rSButtonMetro61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro61ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro61.getText());
    }//GEN-LAST:event_rSButtonMetro61ActionPerformed

    private void rSButtonMetro62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro62ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro62.getText());
    }//GEN-LAST:event_rSButtonMetro62ActionPerformed

    private void rSButtonMetro63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro63ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro63.getText());
    }//GEN-LAST:event_rSButtonMetro63ActionPerformed

    private void rSButtonMetro64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro64ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro64.getText());
    }//GEN-LAST:event_rSButtonMetro64ActionPerformed

    private void rSButtonMetro65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro65ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro65.getText());
    }//GEN-LAST:event_rSButtonMetro65ActionPerformed

    private void rSButtonMetro66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro66ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro66.getText());
    }//GEN-LAST:event_rSButtonMetro66ActionPerformed

    private void rSButtonMetro67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro67ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro67.getText());
    }//GEN-LAST:event_rSButtonMetro67ActionPerformed

    private void rSButtonMetro68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro68ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro68.getText());
    }//GEN-LAST:event_rSButtonMetro68ActionPerformed

    private void rSButtonMetro69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro69ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro69.getText());
    }//GEN-LAST:event_rSButtonMetro69ActionPerformed

    private void rSButtonMetro70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro70ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro70.getText());
    }//GEN-LAST:event_rSButtonMetro70ActionPerformed

    private void rSButtonMetro71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro71ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro71.getText());
    }//GEN-LAST:event_rSButtonMetro71ActionPerformed

    private void rSButtonMetro72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro72ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro72.getText());
    }//GEN-LAST:event_rSButtonMetro72ActionPerformed

    private void rSButtonMetro73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro73ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro73.getText());
    }//GEN-LAST:event_rSButtonMetro73ActionPerformed

    private void rSButtonMetro74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro74ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro74.getText());
    }//GEN-LAST:event_rSButtonMetro74ActionPerformed

    private void rSButtonMetro75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro75ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro75.getText());
    }//GEN-LAST:event_rSButtonMetro75ActionPerformed

    private void rSButtonMetro76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro76ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro76.getText());
    }//GEN-LAST:event_rSButtonMetro76ActionPerformed

    private void rSButtonMetro77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro77ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro77.getText());
    }//GEN-LAST:event_rSButtonMetro77ActionPerformed

    private void rSButtonMetro78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro78ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro78.getText());
    }//GEN-LAST:event_rSButtonMetro78ActionPerformed

    private void rSButtonMetro79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro79ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro79.getText());
    }//GEN-LAST:event_rSButtonMetro79ActionPerformed

    private void rSButtonMetro80ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro80ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro80.getText());
    }//GEN-LAST:event_rSButtonMetro80ActionPerformed

    private void rSButtonMetro81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro81ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro81.getText());
    }//GEN-LAST:event_rSButtonMetro81ActionPerformed

    private void rSButtonMetro82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro82ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro82.getText());
    }//GEN-LAST:event_rSButtonMetro82ActionPerformed

    private void jTextFieldNombre6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombre6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombre6ActionPerformed

    private void rSButtonMetroIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroIngresarActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+jTextFieldNombre6.getText());
    }//GEN-LAST:event_rSButtonMetroIngresarActionPerformed

    private void rSButtonMetroBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroBorrarActionPerformed
        int pos;
        String s = jTextFieldIngreso.getText().trim();
        pos = s.lastIndexOf(" ");
        if (pos != -1) {
            s = s.substring(0, pos);
        } else {
            s = "";
        }
        jTextFieldIngreso.setText(" "+s);
    }//GEN-LAST:event_rSButtonMetroBorrarActionPerformed

    private void rSButtonMetroBorrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroBorrarTodoActionPerformed
        jTextFieldNombre.setText("");
        jTextFieldIngreso.setText("");
    }//GEN-LAST:event_rSButtonMetroBorrarTodoActionPerformed

    private void jTextFieldCedula1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCedula1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCedula1ActionPerformed

    private void rSButtonMetroFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFinalizarActionPerformed
      
    }//GEN-LAST:event_rSButtonMetroFinalizarActionPerformed

    private void rSButtonMetroFinalizarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonMetroFinalizarMousePressed
         jTextFieldDireccion1.setText(jTextFieldIngreso.getText().trim());
         rSPopuMenu2.setVisible(false);
    }//GEN-LAST:event_rSButtonMetroFinalizarMousePressed

    private void rSButtonMetroCambiarIdiomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroCambiarIdiomaActionPerformed
        jPanelCambiarIdioma.setVisible(true);
        jPanelPrincipal.setVisible(false);
    }//GEN-LAST:event_rSButtonMetroCambiarIdiomaActionPerformed

    private void jLabelHome9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelHome9MouseClicked
        jPanelCambiarIdioma.setVisible(false);
        jPanelPrincipal.setVisible(true);
    }//GEN-LAST:event_jLabelHome9MouseClicked

    private void rSButtonPaneHome9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonPaneHome9MouseClicked

    }//GEN-LAST:event_rSButtonPaneHome9MouseClicked

    private void jPanelCambiarIdiomaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelCambiarIdiomaComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanelCambiarIdiomaComponentShown

    private void rSButtonMetroIdiomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroIdiomaActionPerformed
            switch(rSComboMetroIdioma.getSelectedIndex()){
            case 0:
            cambiarIdioma("Español");
            break;
            case 1:
            cambiarIdioma("Ingles");
            break;
        }
    }//GEN-LAST:event_rSButtonMetroIdiomaActionPerformed

    private void jTextFieldNombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombre1ActionPerformed

    private void jCheckBoxDisponibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxDisponibleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxDisponibleActionPerformed

    private void rSButtonMetroFiltrar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar7ActionPerformed
        borrarTabla(rSTablaPacientes);
        try{
            
            controladora.consultarPacientes(rSTablaPacientes); 
        }
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar7ActionPerformed

    private void rSButtonMetroFiltrar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar8ActionPerformed
        borrarTabla(rSTablaEmpleados);
        try{
            
            controladora.consultarEmpleados(rSTablaEmpleados); 
        }
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar8ActionPerformed

    private void rSButtonMetroFiltrar9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar9ActionPerformed
        borrarTabla(rSTablaHabilidades);
        try{
            
            controladora.consultarHabilidades(rSTablaHabilidades); 
        }
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar9ActionPerformed

    private void rSButtonMetroFiltrar10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar10ActionPerformed
        borrarTabla(rSTablaMedicamentos);
        try{
            
            controladora.consultarMedicamentos(rSTablaMedicamentos); 
        }
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar10ActionPerformed

    private void rSButtonMetroFiltrar11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar11ActionPerformed
        borrarTabla(rSTablaCampana);
        try{
            
            controladora.consultarCampanas(rSTablaCampana); 
        }
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar11ActionPerformed

    private void rSButtonMetroFiltrar12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar12ActionPerformed
        borrarTabla(rSTablaCama);
        try{
            
            controladora.consultarCamas(rSTablaCama); 
        }
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar12ActionPerformed

    private void rSButtonMetroFiltrar13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar13ActionPerformed
        borrarTabla(rSTablaArea);
        try{
            
            controladora.consultarAreas(rSTablaArea); 
        }
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar13ActionPerformed

    private void jTextFieldNombre1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombre1FocusGained
        jTextFieldNombre1.setText("");
    }//GEN-LAST:event_jTextFieldNombre1FocusGained

    private void jTextFieldCedula1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCedula1FocusGained
        jTextFieldCedula1.setText("");
    }//GEN-LAST:event_jTextFieldCedula1FocusGained

    private void jTextFieldDireccion1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDireccion1FocusGained
        
    }//GEN-LAST:event_jTextFieldDireccion1FocusGained

    private void jTextFieldTelefono1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTelefono1FocusGained
       jTextFieldTelefono1.setText("");
    }//GEN-LAST:event_jTextFieldTelefono1FocusGained

    private void jTextFieldActividadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldActividadFocusGained
        jTextFieldActividad.setText("");
    }//GEN-LAST:event_jTextFieldActividadFocusGained

    private void jTextFieldSeguridadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSeguridadFocusGained
        jTextFieldSeguridad.setText("");
    }//GEN-LAST:event_jTextFieldSeguridadFocusGained

    private void jTextFieldNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombreFocusGained
        jTextFieldNombre.setText("");
    }//GEN-LAST:event_jTextFieldNombreFocusGained

    private void jTextFieldCedulaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCedulaFocusGained
        jTextFieldCedula.setText("");
    }//GEN-LAST:event_jTextFieldCedulaFocusGained

    private void jTextFieldTelefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoFocusGained
        jTextFieldTelefono.setText("");
    }//GEN-LAST:event_jTextFieldTelefonoFocusGained

    private void jTextFieldSalarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSalarioFocusGained
        jTextFieldSalario.setText("");
    }//GEN-LAST:event_jTextFieldSalarioFocusGained

    private void jTextFieldEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldEmailFocusGained
        jTextFieldEmail.setText("");
    }//GEN-LAST:event_jTextFieldEmailFocusGained

    private void jTextFieldJefeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldJefeFocusGained
       jTextFieldJefe.setText("");
    }//GEN-LAST:event_jTextFieldJefeFocusGained

    private void jTextFieldNombre4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombre4FocusGained
        jTextFieldNombre4.setText("");
    }//GEN-LAST:event_jTextFieldNombre4FocusGained

    private void jTextFieldCodigo2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigo2FocusGained
        jTextFieldCodigo2.setText("");
    }//GEN-LAST:event_jTextFieldCodigo2FocusGained

    private void jTextFieldBuscar3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBuscar3FocusGained
        jTextFieldBuscar3.setText("");
    }//GEN-LAST:event_jTextFieldBuscar3FocusGained

    private void jTextFieldNombre2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombre2FocusGained
        jTextFieldNombre2.setText("");
    }//GEN-LAST:event_jTextFieldNombre2FocusGained

    private void jTextFieldCodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigoFocusGained
        jTextFieldCodigo.setText("");
    }//GEN-LAST:event_jTextFieldCodigoFocusGained

    private void jTextFieldCostoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCostoFocusGained
        jTextFieldCosto.setText("");
    }//GEN-LAST:event_jTextFieldCostoFocusGained

    private void jTextFieldBuscar2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBuscar2FocusGained
        jTextFieldBuscar2.setText("");
    }//GEN-LAST:event_jTextFieldBuscar2FocusGained

    private void jTextFieldNombre3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombre3FocusGained
        jTextFieldNombre3.setText("");
    }//GEN-LAST:event_jTextFieldNombre3FocusGained

    private void jTextFieldBuscar4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBuscar4FocusGained
        jTextFieldBuscar4.setText("");
    }//GEN-LAST:event_jTextFieldBuscar4FocusGained

    private void jTextFieldCodigo4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigo4FocusGained
        jTextFieldCodigo4.setText("");
    }//GEN-LAST:event_jTextFieldCodigo4FocusGained

    private void jTextAreaDescripcionCamaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextAreaDescripcionCamaFocusGained
        jTextAreaDescripcionCama.setText("");
    }//GEN-LAST:event_jTextAreaDescripcionCamaFocusGained

    private void jTextFieldNombre5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombre5FocusGained
        jTextFieldNombre5.setText("");
    }//GEN-LAST:event_jTextFieldNombre5FocusGained

    private void jTextFieldCodigo5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigo5FocusGained
        jTextFieldCodigo5.setText("");
    }//GEN-LAST:event_jTextFieldCodigo5FocusGained

    private void jTextAreaDescripcionAreaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextAreaDescripcionAreaFocusGained
        jTextAreaDescripcionArea.setText("");
    }//GEN-LAST:event_jTextAreaDescripcionAreaFocusGained

    private void jTextFieldBuscar6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBuscar6FocusGained
          jTextFieldBuscar6.setText("");
    }//GEN-LAST:event_jTextFieldBuscar6FocusGained

    private void jTextFieldCodigo3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigo3FocusGained
        jTextFieldCodigo3.setText("");
    }//GEN-LAST:event_jTextFieldCodigo3FocusGained

    private void jTextFieldBuscar5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBuscar5FocusGained
        jTextFieldBuscar5.setText("");
    }//GEN-LAST:event_jTextFieldBuscar5FocusGained

//=======================================================================================================

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupFormato;
    private javax.swing.ButtonGroup buttonGroupTipo;
    public static javax.swing.JButton jButtonSlider;
    public static javax.swing.JButton jButtonSlider1;
    public static javax.swing.JButton jButtonSlider2;
    public static javax.swing.JButton jButtonSlider3;
    public static javax.swing.JButton jButtonSlider4;
    private javax.swing.JCheckBox jCheckBoxDisponible;
    private javax.swing.JLabel jLabeHistoryClinic;
    private javax.swing.JLabel jLabelActividad;
    private javax.swing.JLabel jLabelAgregarArea;
    private javax.swing.JLabel jLabelAgregarCama;
    private javax.swing.JLabel jLabelAgregarCampana;
    private javax.swing.JLabel jLabelAgregarEmpleado;
    private javax.swing.JLabel jLabelAgregarHabilidad;
    private javax.swing.JLabel jLabelAgregarMedicina;
    private javax.swing.JLabel jLabelAgregarPaciente;
    private javax.swing.JLabel jLabelArea;
    private javax.swing.JLabel jLabelArea2;
    private javax.swing.JLabel jLabelAreaS;
    private javax.swing.JLabel jLabelAreas;
    private javax.swing.JLabel jLabelAsignarCama;
    private javax.swing.JLabel jLabelAsignarCampana;
    private javax.swing.JLabel jLabelAsignarHabilidad;
    private javax.swing.JLabel jLabelBackupDB;
    private javax.swing.JLabel jLabelBed;
    private javax.swing.JLabel jLabelBuscar;
    private javax.swing.JLabel jLabelBuscar1;
    private javax.swing.JLabel jLabelBuscar2;
    private javax.swing.JLabel jLabelBuscar3;
    private javax.swing.JLabel jLabelBuscar4;
    private javax.swing.JLabel jLabelBuscar5;
    private javax.swing.JLabel jLabelBuscar6;
    private javax.swing.JLabel jLabelCamas;
    private javax.swing.JLabel jLabelCambiarIdioma;
    private javax.swing.JLabel jLabelCampaing;
    private javax.swing.JLabel jLabelCampanas;
    private javax.swing.JLabel jLabelCargo;
    private javax.swing.JLabel jLabelCedula;
    private javax.swing.JLabel jLabelCedula1;
    private javax.swing.JLabel jLabelCleanDB;
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelCodigo2;
    private javax.swing.JLabel jLabelCodigo3;
    private javax.swing.JLabel jLabelCodigo4;
    private javax.swing.JLabel jLabelCodigo5;
    private javax.swing.JLabel jLabelCosto;
    private javax.swing.JLabel jLabelDescripcion;
    private javax.swing.JLabel jLabelDescripcion2;
    private javax.swing.JLabel jLabelDescripcion3;
    private javax.swing.JLabel jLabelDireccion;
    private javax.swing.JLabel jLabelDireccion1;
    private javax.swing.JLabel jLabelDoctorEncargado;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelEmpleado;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelFechaAsignacion;
    private javax.swing.JLabel jLabelFechaNacimiento;
    private javax.swing.JLabel jLabelFormatoSalida;
    private javax.swing.JLabel jLabelHome;
    private javax.swing.JLabel jLabelHome1;
    private javax.swing.JLabel jLabelHome2;
    private javax.swing.JLabel jLabelHome3;
    private javax.swing.JLabel jLabelHome4;
    private javax.swing.JLabel jLabelHome6;
    private javax.swing.JLabel jLabelHome7;
    private javax.swing.JLabel jLabelHome8;
    private javax.swing.JLabel jLabelHome9;
    private javax.swing.JLabel jLabelJefe;
    private javax.swing.JLabel jLabelListWorker;
    private javax.swing.JLabel jLabelListaCamas;
    private javax.swing.JLabel jLabelListaCampanas;
    private javax.swing.JLabel jLabelListaEnfermeras;
    private javax.swing.JLabel jLabelListaHabilidades;
    private javax.swing.JLabel jLabelListaPacientes;
    private javax.swing.JLabel jLabelListaPacientes1;
    private javax.swing.JLabel jLabelMedicina;
    private javax.swing.JLabel jLabelMedicinas;
    private javax.swing.JLabel jLabelModificarArea;
    private javax.swing.JLabel jLabelModificarCama;
    private javax.swing.JLabel jLabelModificarCampana;
    private javax.swing.JLabel jLabelModificarEmpleado;
    private javax.swing.JLabel jLabelModificarHabilidad;
    private javax.swing.JLabel jLabelModificarMedicina;
    private javax.swing.JLabel jLabelModificarPaciente;
    private javax.swing.JLabel jLabelMonthlyAgenda;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelNombre1;
    private javax.swing.JLabel jLabelNombre2;
    private javax.swing.JLabel jLabelNombre3;
    private javax.swing.JLabel jLabelNombre4;
    private javax.swing.JLabel jLabelNombre5;
    private javax.swing.JLabel jLabelNombre6;
    private javax.swing.JLabel jLabelNumberQuote;
    private javax.swing.JLabel jLabelObjetivo;
    private javax.swing.JLabel jLabelPaciente;
    private javax.swing.JLabel jLabelRestoreDB;
    private javax.swing.JLabel jLabelRol1;
    private javax.swing.JLabel jLabelRol2;
    private javax.swing.JLabel jLabelRuta;
    private javax.swing.JLabel jLabelSO;
    private javax.swing.JLabel jLabelSalario;
    private javax.swing.JLabel jLabelSeguridadSocial;
    private javax.swing.JLabel jLabelSeleccionIdioma;
    private javax.swing.JLabel jLabelSeleccionar;
    private javax.swing.JLabel jLabelSeleccionar1;
    private javax.swing.JLabel jLabelTelefono;
    private javax.swing.JLabel jLabelTelefono1;
    private javax.swing.JLabel jLabelTipoBackup;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelTituloArea;
    private javax.swing.JLabel jLabelTituloBackup;
    private javax.swing.JLabel jLabelTituloCama;
    private javax.swing.JLabel jLabelTituloCampaing;
    private javax.swing.JLabel jLabelTituloMedicina;
    private javax.swing.JLabel jLabelTituloReport;
    private javax.swing.JLabel jLabelTituloRuta;
    private javax.swing.JLabel jLabelTituloSO;
    private javax.swing.JLabel jLabelTituloTablas;
    private javax.swing.JLabel jLabelTituloTools;
    private javax.swing.JLabel jLabelTituloUsuario;
    private javax.swing.JLabel jLabelUsuarios;
    private javax.swing.JLabel jLabelimgArea;
    private javax.swing.JLabel jLabelimgAreas;
    private javax.swing.JLabel jLabelimgCama;
    private javax.swing.JLabel jLabelimgCamas;
    private javax.swing.JLabel jLabelimgCampanas;
    private javax.swing.JLabel jLabelimgCerrarSesion;
    private javax.swing.JLabel jLabelimgMedicina;
    private javax.swing.JLabel jLabelimgMedicinaCampaing;
    private javax.swing.JLabel jLabelimgMedicinas;
    private javax.swing.JLabel jLabelimgPerfil;
    private javax.swing.JLabel jLabelimgReport;
    private javax.swing.JLabel jLabelimgTools;
    private javax.swing.JLabel jLabelimgUsuario;
    private javax.swing.JLabel jLabelimgUsuarios;
    private javax.swing.JPanel jPanelAbajo2;
    private javax.swing.JPanel jPanelAgregarArea;
    private javax.swing.JPanel jPanelAgregarCama;
    private javax.swing.JPanel jPanelAgregarCampana;
    private javax.swing.JPanel jPanelAgregarEmpleado;
    private javax.swing.JPanel jPanelAgregarHabilidad;
    private javax.swing.JPanel jPanelAgregarMedicina;
    private javax.swing.JPanel jPanelAgregarPaciente;
    private javax.swing.JPanel jPanelArea;
    private javax.swing.JPanel jPanelAreas;
    private javax.swing.JPanel jPanelArriba2;
    private javax.swing.JPanel jPanelAsignarCama;
    private javax.swing.JPanel jPanelAsignarCampana;
    private javax.swing.JPanel jPanelAsignarHabilidad;
    private javax.swing.JPanel jPanelBackupBD;
    private javax.swing.JPanel jPanelBed;
    private javax.swing.JPanel jPanelCamas;
    private javax.swing.JPanel jPanelCambiarIdioma;
    private javax.swing.JPanel jPanelCampaing;
    private javax.swing.JPanel jPanelCampanas;
    private javax.swing.JPanel jPanelMedicians;
    private javax.swing.JPanel jPanelMedicinas;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelMetro;
    private javax.swing.JPanel jPanelModificarArea;
    private javax.swing.JPanel jPanelModificarCama;
    private javax.swing.JPanel jPanelModificarCampana;
    private javax.swing.JPanel jPanelModificarEmpleado;
    private javax.swing.JPanel jPanelModificarHabilidad;
    private javax.swing.JPanel jPanelModificarMedicina;
    private javax.swing.JPanel jPanelModificarPaciente;
    private javax.swing.JPanel jPanelPerfil;
    private javax.swing.JPanel jPanelPpal;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelReports;
    private javax.swing.JPanel jPanelRol;
    private javax.swing.JPanel jPanelSlider;
    private javax.swing.JPanel jPanelSlider1;
    private javax.swing.JPanel jPanelSlider2;
    private javax.swing.JPanel jPanelSlider3;
    private javax.swing.JPanel jPanelSlider4;
    private javax.swing.JPanel jPanelSliders;
    private javax.swing.JPanel jPanelSliders1;
    private javax.swing.JPanel jPanelSliders2;
    private javax.swing.JPanel jPanelSliders3;
    private javax.swing.JPanel jPanelSliders4;
    private javax.swing.JPanel jPanelSliders6;
    private javax.swing.JPanel jPanelStaff;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JPanel jPanelTituloArea;
    private javax.swing.JPanel jPanelTituloBed;
    private javax.swing.JPanel jPanelTituloCampaing;
    private javax.swing.JPanel jPanelTituloMedicina;
    private javax.swing.JPanel jPanelTituloReport;
    private javax.swing.JPanel jPanelTituloTools;
    private javax.swing.JPanel jPanelTituloUsuario;
    private javax.swing.JPanel jPanelTools;
    private javax.swing.JPanel jPanelUsuarios;
    private javax.swing.JRadioButton jRadioBackup;
    private javax.swing.JRadioButton jRadioParcial;
    private javax.swing.JRadioButton jRadioSQL;
    private javax.swing.JRadioButton jRadioTotal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPaneTablaArea;
    private javax.swing.JScrollPane jScrollPaneTablaCama;
    private javax.swing.JScrollPane jScrollPaneTablaCampana;
    private javax.swing.JScrollPane jScrollPaneTablaEmpleados;
    private javax.swing.JScrollPane jScrollPaneTablaHabilidades;
    private javax.swing.JScrollPane jScrollPaneTablaMedicamentos;
    private javax.swing.JScrollPane jScrollPaneTablaPacientes;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparatorAreaS;
    private javax.swing.JSeparator jSeparatorBed;
    private javax.swing.JSeparator jSeparatorCampaing;
    private javax.swing.JSeparator jSeparatorEmpleado;
    private javax.swing.JSeparator jSeparatorMedicina;
    private javax.swing.JSeparator jSeparatorPaciente;
    private javax.swing.JList jTableTablas;
    private javax.swing.JList jTableTablasBackup;
    private javax.swing.JTextArea jTextAreaDescripcionArea;
    private javax.swing.JTextArea jTextAreaDescripcionCama;
    private javax.swing.JTextArea jTextAreaDescripcionMedicina;
    private javax.swing.JTextField jTextFieldActividad;
    private javax.swing.JTextField jTextFieldBuscar;
    private javax.swing.JTextField jTextFieldBuscar1;
    private javax.swing.JTextField jTextFieldBuscar2;
    private javax.swing.JTextField jTextFieldBuscar3;
    private javax.swing.JTextField jTextFieldBuscar4;
    private javax.swing.JTextField jTextFieldBuscar5;
    private javax.swing.JTextField jTextFieldBuscar6;
    private javax.swing.JTextField jTextFieldCedula;
    private javax.swing.JTextField jTextFieldCedula1;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldCodigo2;
    private javax.swing.JTextField jTextFieldCodigo3;
    private javax.swing.JTextField jTextFieldCodigo4;
    private javax.swing.JTextField jTextFieldCodigo5;
    private javax.swing.JTextField jTextFieldCosto;
    private javax.swing.JTextField jTextFieldDireccion;
    private javax.swing.JTextField jTextFieldDireccion1;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldIngreso;
    private javax.swing.JTextField jTextFieldJefe;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldNombre1;
    private javax.swing.JTextField jTextFieldNombre2;
    private javax.swing.JTextField jTextFieldNombre3;
    private javax.swing.JTextField jTextFieldNombre4;
    private javax.swing.JTextField jTextFieldNombre5;
    private javax.swing.JTextField jTextFieldNombre6;
    private javax.swing.JTextField jTextFieldSalario;
    private javax.swing.JTextField jTextFieldSeguridad;
    private javax.swing.JTextField jTextFieldTelefono;
    private javax.swing.JTextField jTextFieldTelefono1;
    private rojerusan.RSButtonPane rSButtonAgregarArea;
    private rojerusan.RSButtonPane rSButtonAgregarCama;
    private rojerusan.RSButtonPane rSButtonAgregarCampana;
    private rojerusan.RSButtonPane rSButtonAgregarEmpleado;
    private rojerusan.RSButtonPane rSButtonAgregarHabilidad;
    private rojerusan.RSButtonPane rSButtonAgregarMedicina;
    private rojerusan.RSButtonPane rSButtonAgregarPaciente;
    private rojerusan.RSButtonCircle rSButtonAsignar;
    private rojerusan.RSButtonCircle rSButtonAsignar1;
    private rojerusan.RSButtonCircle rSButtonAsignar2;
    private rojerusan.RSButtonPane rSButtonAsignarCama;
    private rojerusan.RSButtonPane rSButtonAsignarCampana;
    private rojerusan.RSButtonPane rSButtonAsignarHabilidad;
    private rojerusan.RSButtonPane rSButtonCerrarSesion;
    private rojerusan.RSButtonMetro rSButtonChooserRuta;
    private rojerusan.RSButtonMetro rSButtonMetro1;
    private rojerusan.RSButtonMetro rSButtonMetro10;
    private rojerusan.RSButtonMetro rSButtonMetro11;
    private rojerusan.RSButtonMetro rSButtonMetro12;
    private rojerusan.RSButtonMetro rSButtonMetro13;
    private rojerusan.RSButtonMetro rSButtonMetro14;
    private rojerusan.RSButtonMetro rSButtonMetro15;
    private rojerusan.RSButtonMetro rSButtonMetro16;
    private rojerusan.RSButtonMetro rSButtonMetro17;
    private rojerusan.RSButtonMetro rSButtonMetro18;
    private rojerusan.RSButtonMetro rSButtonMetro19;
    private rojerusan.RSButtonMetro rSButtonMetro2;
    private rojerusan.RSButtonMetro rSButtonMetro20;
    private rojerusan.RSButtonMetro rSButtonMetro21;
    private rojerusan.RSButtonMetro rSButtonMetro22;
    private rojerusan.RSButtonMetro rSButtonMetro23;
    private rojerusan.RSButtonMetro rSButtonMetro24;
    private rojerusan.RSButtonMetro rSButtonMetro25;
    private rojerusan.RSButtonMetro rSButtonMetro26;
    private rojerusan.RSButtonMetro rSButtonMetro27;
    private rojerusan.RSButtonMetro rSButtonMetro28;
    private rojerusan.RSButtonMetro rSButtonMetro29;
    private rojerusan.RSButtonMetro rSButtonMetro3;
    private rojerusan.RSButtonMetro rSButtonMetro30;
    private rojerusan.RSButtonMetro rSButtonMetro31;
    private rojerusan.RSButtonMetro rSButtonMetro32;
    private rojerusan.RSButtonMetro rSButtonMetro33;
    private rojerusan.RSButtonMetro rSButtonMetro34;
    private rojerusan.RSButtonMetro rSButtonMetro35;
    private rojerusan.RSButtonMetro rSButtonMetro36;
    private rojerusan.RSButtonMetro rSButtonMetro37;
    private rojerusan.RSButtonMetro rSButtonMetro38;
    private rojerusan.RSButtonMetro rSButtonMetro39;
    private rojerusan.RSButtonMetro rSButtonMetro4;
    private rojerusan.RSButtonMetro rSButtonMetro40;
    private rojerusan.RSButtonMetro rSButtonMetro41;
    private rojerusan.RSButtonMetro rSButtonMetro42;
    private rojerusan.RSButtonMetro rSButtonMetro43;
    private rojerusan.RSButtonMetro rSButtonMetro44;
    private rojerusan.RSButtonMetro rSButtonMetro45;
    private rojerusan.RSButtonMetro rSButtonMetro46;
    private rojerusan.RSButtonMetro rSButtonMetro47;
    private rojerusan.RSButtonMetro rSButtonMetro48;
    private rojerusan.RSButtonMetro rSButtonMetro49;
    private rojerusan.RSButtonMetro rSButtonMetro5;
    private rojerusan.RSButtonMetro rSButtonMetro50;
    private rojerusan.RSButtonMetro rSButtonMetro51;
    private rojerusan.RSButtonMetro rSButtonMetro52;
    private rojerusan.RSButtonMetro rSButtonMetro53;
    private rojerusan.RSButtonMetro rSButtonMetro54;
    private rojerusan.RSButtonMetro rSButtonMetro55;
    private rojerusan.RSButtonMetro rSButtonMetro56;
    private rojerusan.RSButtonMetro rSButtonMetro57;
    private rojerusan.RSButtonMetro rSButtonMetro58;
    private rojerusan.RSButtonMetro rSButtonMetro59;
    private rojerusan.RSButtonMetro rSButtonMetro6;
    private rojerusan.RSButtonMetro rSButtonMetro60;
    private rojerusan.RSButtonMetro rSButtonMetro61;
    private rojerusan.RSButtonMetro rSButtonMetro62;
    private rojerusan.RSButtonMetro rSButtonMetro63;
    private rojerusan.RSButtonMetro rSButtonMetro64;
    private rojerusan.RSButtonMetro rSButtonMetro65;
    private rojerusan.RSButtonMetro rSButtonMetro66;
    private rojerusan.RSButtonMetro rSButtonMetro67;
    private rojerusan.RSButtonMetro rSButtonMetro68;
    private rojerusan.RSButtonMetro rSButtonMetro69;
    private rojerusan.RSButtonMetro rSButtonMetro7;
    private rojerusan.RSButtonMetro rSButtonMetro70;
    private rojerusan.RSButtonMetro rSButtonMetro71;
    private rojerusan.RSButtonMetro rSButtonMetro72;
    private rojerusan.RSButtonMetro rSButtonMetro73;
    private rojerusan.RSButtonMetro rSButtonMetro74;
    private rojerusan.RSButtonMetro rSButtonMetro75;
    private rojerusan.RSButtonMetro rSButtonMetro76;
    private rojerusan.RSButtonMetro rSButtonMetro77;
    private rojerusan.RSButtonMetro rSButtonMetro78;
    private rojerusan.RSButtonMetro rSButtonMetro79;
    private rojerusan.RSButtonMetro rSButtonMetro8;
    private rojerusan.RSButtonMetro rSButtonMetro80;
    private rojerusan.RSButtonMetro rSButtonMetro81;
    private rojerusan.RSButtonMetro rSButtonMetro82;
    private rojerusan.RSButtonMetro rSButtonMetro9;
    private rojerusan.RSButtonMetro rSButtonMetroBackup;
    private rojerusan.RSButtonMetro rSButtonMetroBackupBD;
    private rojerusan.RSButtonMetro rSButtonMetroBorrar;
    private rojerusan.RSButtonMetro rSButtonMetroBorrarTodo;
    private rojerusan.RSButtonMetro rSButtonMetroCambiarIdioma;
    private rojerusan.RSButtonMetro rSButtonMetroCleanDB;
    private rojerusan.RSButtonMetro rSButtonMetroDerecha;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar1;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar10;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar11;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar12;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar13;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar2;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar3;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar4;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar5;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar6;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar7;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar8;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar9;
    private rojerusan.RSButtonMetro rSButtonMetroFinalizar;
    private rojerusan.RSButtonMetro rSButtonMetroGuardar;
    private rojerusan.RSButtonMetro rSButtonMetroGuardar1;
    private rojerusan.RSButtonMetro rSButtonMetroGuardar2;
    private rojerusan.RSButtonMetro rSButtonMetroGuardar3;
    private rojerusan.RSButtonMetro rSButtonMetroGuardar4;
    private rojerusan.RSButtonMetro rSButtonMetroGuardar5;
    private rojerusan.RSButtonMetro rSButtonMetroGuardar6;
    private rojerusan.RSButtonMetro rSButtonMetroHistoryClinic;
    private rojerusan.RSButtonMetro rSButtonMetroIdioma;
    private rojerusan.RSButtonMetro rSButtonMetroIngresar;
    private rojerusan.RSButtonMetro rSButtonMetroIzquierda;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar1;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar11;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar12;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar13;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar2;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar3;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar4;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar5;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar6;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar7;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar8;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar9;
    private rojerusan.RSButtonMetro rSButtonMetroListWorker;
    private rojerusan.RSButtonMetro rSButtonMetroMonthlyAgenda;
    private rojerusan.RSButtonMetro rSButtonMetroMostrar2;
    private rojerusan.RSButtonMetro rSButtonMetroMostrar4;
    private rojerusan.RSButtonMetro rSButtonMetroMostrar5;
    private rojerusan.RSButtonMetro rSButtonMetroMostrar6;
    private rojerusan.RSButtonMetro rSButtonMetroNumberQuote;
    private rojerusan.RSButtonMetro rSButtonMetroRestoreBD;
    private rojerusan.RSButtonPane rSButtonModificarArea;
    private rojerusan.RSButtonPane rSButtonModificarCama;
    private rojerusan.RSButtonPane rSButtonModificarCampana;
    private rojerusan.RSButtonPane rSButtonModificarEmpleado;
    private rojerusan.RSButtonPane rSButtonModificarHabilidad;
    private rojerusan.RSButtonPane rSButtonModificarMedicina;
    private rojerusan.RSButtonPane rSButtonModificarPaciente;
    private rojerusan.RSButtonPane rSButtonPaneHome;
    private rojerusan.RSButtonPane rSButtonPaneHome1;
    private rojerusan.RSButtonPane rSButtonPaneHome2;
    private rojerusan.RSButtonPane rSButtonPaneHome3;
    private rojerusan.RSButtonPane rSButtonPaneHome4;
    private rojerusan.RSButtonPane rSButtonPaneHome6;
    private rojerusan.RSButtonPane rSButtonPaneHome7;
    private rojerusan.RSButtonPane rSButtonPaneHome8;
    private rojerusan.RSButtonPane rSButtonPaneHome9;
    private rojerusan.RSComboMetro rSComboArea2;
    private rojerusan.RSComboMetro rSComboMetroArea;
    private rojerusan.RSComboMetro rSComboMetroCargo;
    private rojerusan.RSComboMetro rSComboMetroDoctorEncargado;
    private rojerusan.RSComboMetro rSComboMetroIdioma;
    private rojerusan.RSComboMetro rSComboMetroListaCamas;
    private rojerusan.RSComboMetro rSComboMetroListaCampanas;
    private rojerusan.RSComboMetro rSComboMetroListaEnfermeras;
    private rojerusan.RSComboMetro rSComboMetroListaHabilidades;
    private rojerusan.RSComboMetro rSComboMetroListaPacientes;
    private rojerusan.RSComboMetro rSComboMetroListaPacientes1;
    private rojerusan.RSComboMetro rSComboMetroObjetivo;
    private rojeru_san.componentes.RSDateChooser rSDateChooserFecha;
    private rojeru_san.componentes.RSDateChooser rSDateChooserFechaAsignacion;
    private rojeru_san.componentes.RSDateChooser rSDateChooserFechaNacimiento;
    private rojerusan.RSPopuMenu rSPopuMenu1;
    private rojerusan.RSPopuMenu rSPopuMenu2;
    private rojerusan.RSTableMetro rSTablaArea;
    private rojerusan.RSTableMetro rSTablaCama;
    private rojerusan.RSTableMetro rSTablaCampana;
    private rojerusan.RSTableMetro rSTablaEmpleados;
    private rojerusan.RSTableMetro rSTablaHabilidades;
    private rojerusan.RSTableMetro rSTablaMedicamentos;
    private rojerusan.RSTableMetro rSTablaPacientes;
    // End of variables declaration//GEN-END:variables

}    

