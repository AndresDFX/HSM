package Vista;

import Controladora.*;
import Modelo.Validaciones;
import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultEditorKit;

/**
 * [VistaAdministrador] Clase de interfaz GUI del usuario Administrador
 * @since 07/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class VistaMedico extends javax.swing.JFrame {
    
    //Declaracion de Variables Auxiliares
    private ActionMap acciones ;
    private Action accionCopiar;
    private Action accionPegar;
    private Action accionCortar;
    private Validaciones validacionTotal; 
    private Control controladora;
    private Image iconoVentana; 
    private static VistaMedico INSTANCE = null;
      
//=======================================================================================================    
    /**
     * Constructor de Clase VistaAdministrador
     * @since VistaAdministrador.java
     */
    
    public VistaMedico() {
        
        initComponents();
        
        //Inicializacion de Variables
        acciones = jTextFieldBuscar1.getActionMap();
        validacionTotal = new Validaciones();
        controladora = new Control();
        iconoVentana = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Logos/iconoHospital.png"));
        
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
            INSTANCE = new VistaMedico();         
        }
    }
    
 //======================================================================================================= 
    /**
     * Metodo que permite obtener la instancia de la clase que ha sido creada
     * @return El objeto de la instancia de la clase
     */
    public static VistaMedico getInstancia(){
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
        rSTablaCausas.setDefaultEditor(Object.class, null);
        this.setIconImage(iconoVentana); 
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
     * Metodo que permite mostrar en la interfaz GUI los datos de una Cita existente
     * @param codigo Variable que almacena el ID de la cita encontrado
     */
    public void editarCita(String codigo){

        ArrayList <String> cita = (ArrayList)controladora.mostrarunaCita(codigo);
        
        rSComboMetroCitas.getModel().setSelectedItem(cita.get(1)+", "+cita.get(4));
        rSComboMetroPaciente.getModel().setSelectedItem(cita.get(0)+", "+cita.get(3));
        rSComboMetroPaciente.setEditable(false);
    }
       
//======================================================================================================= 
    /**
     * Metodo que permite mostrar en la interfaz GUI los datos de una Causa existente
     * @param codigo Variable que almacena el ID de la causa encontrado
     */    
    public void editarCausa(String codigo){

        ArrayList <String> causa = (ArrayList)controladora.mostrarunaCausa(codigo);
        
        jTextFieldCodigo5.setText(causa.get(0));
        jTextFieldNombre5.setText(causa.get(1));
        jTextAreaDescripcionArea.setText(causa.get(2));
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
        jPanelPpal = new javax.swing.JPanel();
        jPanelMenu = new javax.swing.JPanel();
        jPanelTitulo = new javax.swing.JPanel();
        jPanelPerfil = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelimgPerfil = new javax.swing.JLabel();
        jPanelRol = new javax.swing.JPanel();
        jLabelRol1 = new javax.swing.JLabel();
        jLabelRol2 = new javax.swing.JLabel();
        jPanelCitas = new javax.swing.JPanel();
        jLabelCita = new javax.swing.JLabel();
        jLabelimgCita = new javax.swing.JLabel();
        rSButtonCerrarSesion = new rojerusan.RSButtonPane();
        jLabelimgCerrarSesion = new javax.swing.JLabel();
        jPaneCitation = new javax.swing.JPanel();
        jPanelTituloCitacion = new javax.swing.JPanel();
        jLabelTituloCitacion = new javax.swing.JLabel();
        jLabelimgCitacion = new javax.swing.JLabel();
        jButtonSlider = new javax.swing.JButton();
        jPanelSlider = new javax.swing.JPanel();
        jLabelAsistencia = new javax.swing.JLabel();
        jSeparatorRegistros = new javax.swing.JSeparator();
        jSeparatorCausas = new javax.swing.JSeparator();
        jLabelMedicinas = new javax.swing.JLabel();
        jLabelCausas = new javax.swing.JLabel();
        jSeparatorMedicinas = new javax.swing.JSeparator();
        rSButtonAsignarMedicamentos = new rojerusan.RSButtonPane();
        jLabelAsignarCausas1 = new javax.swing.JLabel();
        rSButtonPaneHome = new rojerusan.RSButtonPane();
        jLabelHome = new javax.swing.JLabel();
        rSButtonAgregarRegistro = new rojerusan.RSButtonPane();
        jLabelAgregarRegistro = new javax.swing.JLabel();
        rSButtonAgregarCausas = new rojerusan.RSButtonPane();
        jLabelAsignarCausas2 = new javax.swing.JLabel();
        rSButtonAsignarCausas = new rojerusan.RSButtonPane();
        jLabelAsignarCausas = new javax.swing.JLabel();
        rSButtonModificarCausas = new rojerusan.RSButtonPane();
        jLabelAsignarCausas3 = new javax.swing.JLabel();
        jPanelSliders = new javax.swing.JPanel();
        jPanelAgregarRegistro = new javax.swing.JPanel();
        jLabelCedulaPaciente = new javax.swing.JLabel();
        jLabelCedulaMedico = new javax.swing.JLabel();
        rSButtonMetroGuardar1 = new rojerusan.RSButtonMetro();
        rSButtonMetroLimpiar1 = new rojerusan.RSButtonMetro();
        rSComboMetroPaciente = new rojerusan.RSComboMetro();
        rSComboMetroCitas = new rojerusan.RSComboMetro();
        jPanelAgregarCausa = new javax.swing.JPanel();
        rSButtonMetroGuardar2 = new rojerusan.RSButtonMetro();
        rSButtonMetroLimpiar2 = new rojerusan.RSButtonMetro();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaDescripcionArea = new javax.swing.JTextArea();
        jTextFieldNombre5 = new javax.swing.JTextField();
        jLabelNombre5 = new javax.swing.JLabel();
        jLabelDescripcion3 = new javax.swing.JLabel();
        jLabelCodigo5 = new javax.swing.JLabel();
        jTextFieldCodigo5 = new javax.swing.JTextField();
        jPanelModificarCausa = new javax.swing.JPanel();
        jLabelBuscar1 = new javax.swing.JLabel();
        jTextFieldBuscar1 = new javax.swing.JTextField();
        rSButtonMetroFiltrar1 = new rojerusan.RSButtonMetro();
        jScrollPaneTablaCausas = new javax.swing.JScrollPane();
        rSTablaCausas = new rojerusan.RSTableMetro();
        rSButtonMetroLimpiar12 = new rojerusan.RSButtonMetro();
        rSButtonMetroFiltrar10 = new rojerusan.RSButtonMetro();
        jPanelAsignarCausas = new javax.swing.JPanel();
        jLabelListaCausas = new javax.swing.JLabel();
        rSComboMetroListaCausas = new rojerusan.RSComboMetro();
        rSButtonMetroLimpiar8 = new rojerusan.RSButtonMetro();
        rSButtonAsignar2 = new rojerusan.RSButtonCircle();
        rSComboMetroListaRegistros = new rojerusan.RSComboMetro();
        jLabelListaRegistros = new javax.swing.JLabel();
        jPanelAsignarMedicinas = new javax.swing.JPanel();
        jLabelListaMedicamentos = new javax.swing.JLabel();
        rSComboMetroListaMedicamentos = new rojerusan.RSComboMetro();
        rSButtonMetroLimpiar9 = new rojerusan.RSButtonMetro();
        rSButtonAsignar3 = new rojerusan.RSButtonCircle();
        rSComboMetroListaRegistros1 = new rojerusan.RSComboMetro();
        jLabelListaRegistros1 = new javax.swing.JLabel();

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
        jLabelTitulo.setText("               Portal Medico");
        jLabelTitulo.setToolTipText("");
        jPanelPerfil.add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 680, -1));

        jPanelTitulo.add(jPanelPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 740, 100));

        jLabelimgPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/doctor_256px.png"))); // NOI18N
        jPanelTitulo.add(jLabelimgPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 230, 210));

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
        jLabelRol2.setText("Bienvenido al ");
        jPanelRol.add(jLabelRol2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 110, 250, 80));

        jPanelMenu.add(jPanelRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 210, 390));

        jPanelCitas.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCitas.setToolTipText("Atención de citas");
        jPanelCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelCitasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelCitasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelCitasMouseExited(evt);
            }
        });
        jPanelCitas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelCita.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCita.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabelCita.setForeground(new java.awt.Color(52, 152, 219));
        jLabelCita.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCita.setText("Gestionar Citas");
        jPanelCitas.add(jLabelCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 150, 30));

        jLabelimgCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/historia_100px.png"))); // NOI18N
        jPanelCitas.add(jLabelimgCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 120));

        jPanelMenu.add(jPanelCitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 150, 170));

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

        jPanelMenu.add(rSButtonCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 280, 150, 170));

        jPanelPpal.add(jPanelMenu, "card2");

        jPaneCitation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTituloCitacion.setBackground(new java.awt.Color(51, 152, 219));
        jPanelTituloCitacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTituloCitacion.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabelTituloCitacion.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloCitacion.setText("GESTIÓN DE CITAS");
        jPanelTituloCitacion.add(jLabelTituloCitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 520, -1));

        jLabelimgCitacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/cita_100px.png"))); // NOI18N
        jPanelTituloCitacion.add(jLabelimgCitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 110, 90));

        jButtonSlider.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/menu_48px.png"))); // NOI18N
        jButtonSlider.setBorder(null);
        jButtonSlider.setContentAreaFilled(false);
        jButtonSlider.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSlider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSliderActionPerformed(evt);
            }
        });
        jPanelTituloCitacion.add(jButtonSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, 70));

        jPaneCitation.add(jPanelTituloCitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 90));

        jPanelSlider.setBackground(new java.awt.Color(153, 153, 153));
        jPanelSlider.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAsistencia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelAsistencia.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAsistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/cita_50px.png"))); // NOI18N
        jLabelAsistencia.setText("ASISTENCIA");
        jPanelSlider.add(jLabelAsistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 50));

        jSeparatorRegistros.setForeground(new java.awt.Color(255, 255, 255));
        jSeparatorRegistros.setPreferredSize(new java.awt.Dimension(240, 4));
        jPanelSlider.add(jSeparatorRegistros, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));

        jSeparatorCausas.setForeground(new java.awt.Color(255, 255, 255));
        jSeparatorCausas.setPreferredSize(new java.awt.Dimension(240, 4));
        jPanelSlider.add(jSeparatorCausas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, -1, -1));

        jLabelMedicinas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelMedicinas.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMedicinas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/jeringa_50px.png"))); // NOI18N
        jLabelMedicinas.setText("MEDICAMENTOS");
        jPanelSlider.add(jLabelMedicinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 240, 50));

        jLabelCausas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelCausas.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCausas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/pacienteEnfermo_50px.png"))); // NOI18N
        jLabelCausas.setText("CAUSAS");
        jPanelSlider.add(jLabelCausas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 240, 50));

        jSeparatorMedicinas.setForeground(new java.awt.Color(255, 255, 255));
        jSeparatorMedicinas.setPreferredSize(new java.awt.Dimension(240, 4));
        jPanelSlider.add(jSeparatorMedicinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, -1, -1));

        rSButtonAsignarMedicamentos.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonAsignarMedicamentos.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonAsignarMedicamentos.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonAsignarMedicamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonAsignarMedicamentosMouseClicked(evt);
            }
        });
        rSButtonAsignarMedicamentos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAsignarCausas1.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAsignarCausas1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAsignarCausas1.setText(" Asignar Medicamento a Cita");
        rSButtonAsignarMedicamentos.add(jLabelAsignarCausas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider.add(rSButtonAsignarMedicamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 240, -1));

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

        rSButtonAgregarRegistro.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonAgregarRegistro.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonAgregarRegistro.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonAgregarRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonAgregarRegistroMouseClicked(evt);
            }
        });
        rSButtonAgregarRegistro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAgregarRegistro.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAgregarRegistro.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAgregarRegistro.setText(" Agregar Registro de Cita");
        rSButtonAgregarRegistro.add(jLabelAgregarRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider.add(rSButtonAgregarRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 240, -1));

        rSButtonAgregarCausas.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonAgregarCausas.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonAgregarCausas.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonAgregarCausas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonAgregarCausasMouseClicked(evt);
            }
        });
        rSButtonAgregarCausas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAsignarCausas2.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAsignarCausas2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAsignarCausas2.setText(" Agregar Causas al Sistema");
        rSButtonAgregarCausas.add(jLabelAsignarCausas2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 240, 30));

        jPanelSlider.add(rSButtonAgregarCausas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 240, -1));

        rSButtonAsignarCausas.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonAsignarCausas.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonAsignarCausas.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonAsignarCausas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonAsignarCausasMouseClicked(evt);
            }
        });
        rSButtonAsignarCausas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAsignarCausas.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAsignarCausas.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAsignarCausas.setText(" Asignar Causas a Cita");
        rSButtonAsignarCausas.add(jLabelAsignarCausas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 240, 30));

        jPanelSlider.add(rSButtonAsignarCausas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 335, 240, -1));

        rSButtonModificarCausas.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonModificarCausas.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonModificarCausas.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonModificarCausas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonModificarCausasMouseClicked(evt);
            }
        });
        rSButtonModificarCausas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAsignarCausas3.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAsignarCausas3.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAsignarCausas3.setText(" Modificar Causas del Sistema");
        rSButtonModificarCausas.add(jLabelAsignarCausas3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 240, 30));

        jPanelSlider.add(rSButtonModificarCausas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 240, -1));

        jPaneCitation.add(jPanelSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 240, 510));

        jPanelSliders.setLayout(new java.awt.CardLayout());

        jPanelAgregarRegistro.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanelAgregarRegistroComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelAgregarRegistroComponentShown(evt);
            }
        });
        jPanelAgregarRegistro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelCedulaPaciente.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelCedulaPaciente.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCedulaPaciente.setText("Identificacion del Paciente");
        jPanelAgregarRegistro.add(jLabelCedulaPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabelCedulaMedico.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelCedulaMedico.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCedulaMedico.setText("Citas Programadas");
        jPanelAgregarRegistro.add(jLabelCedulaMedico, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, -1, -1));

        rSButtonMetroGuardar1.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroGuardar1.setText("Guardar");
        rSButtonMetroGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroGuardar1ActionPerformed(evt);
            }
        });
        jPanelAgregarRegistro.add(rSButtonMetroGuardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 150, 30));

        rSButtonMetroLimpiar1.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar1.setText("Limpiar");
        rSButtonMetroLimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar1ActionPerformed(evt);
            }
        });
        jPanelAgregarRegistro.add(rSButtonMetroLimpiar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 150, 30));

        rSComboMetroPaciente.setMaximumRowCount(6);
        rSComboMetroPaciente.setColorBorde(new java.awt.Color(51, 152, 219));
        rSComboMetroPaciente.setColorFondo(new java.awt.Color(51, 152, 219));
        jPanelAgregarRegistro.add(rSComboMetroPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 180, -1));

        rSComboMetroCitas.setMaximumRowCount(6);
        rSComboMetroCitas.setColorBorde(new java.awt.Color(51, 152, 219));
        rSComboMetroCitas.setColorFondo(new java.awt.Color(51, 152, 219));
        rSComboMetroCitas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                rSComboMetroCitasFocusGained(evt);
            }
        });
        rSComboMetroCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSComboMetroCitasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rSComboMetroCitasMouseEntered(evt);
            }
        });
        jPanelAgregarRegistro.add(rSComboMetroCitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, 180, -1));

        jPanelSliders.add(jPanelAgregarRegistro, "card2");

        jPanelAgregarCausa.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanelAgregarCausaComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelAgregarCausaComponentShown(evt);
            }
        });
        jPanelAgregarCausa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSButtonMetroGuardar2.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroGuardar2.setText("Guardar");
        rSButtonMetroGuardar2.setToolTipText("Guardar datos ingresados");
        rSButtonMetroGuardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroGuardar2ActionPerformed(evt);
            }
        });
        jPanelAgregarCausa.add(rSButtonMetroGuardar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 150, 30));

        rSButtonMetroLimpiar2.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar2.setText("Limpiar");
        rSButtonMetroLimpiar2.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar2ActionPerformed(evt);
            }
        });
        jPanelAgregarCausa.add(rSButtonMetroLimpiar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 150, 30));

        jTextAreaDescripcionArea.setColumns(20);
        jTextAreaDescripcionArea.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextAreaDescripcionArea.setForeground(new java.awt.Color(51, 152, 219));
        jTextAreaDescripcionArea.setLineWrap(true);
        jTextAreaDescripcionArea.setRows(5);
        jTextAreaDescripcionArea.setText("Ingrese una breve descripción de la causa");
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

        jPanelAgregarCausa.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 600, 170));

        jTextFieldNombre5.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldNombre5.setText("Digite el nombre de la causa");
        jTextFieldNombre5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldNombre5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNombre5FocusGained(evt);
            }
        });
        jPanelAgregarCausa.add(jTextFieldNombre5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 180, 30));

        jLabelNombre5.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelNombre5.setForeground(new java.awt.Color(51, 152, 219));
        jLabelNombre5.setText("Nombre");
        jPanelAgregarCausa.add(jLabelNombre5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabelDescripcion3.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelDescripcion3.setForeground(new java.awt.Color(51, 152, 219));
        jLabelDescripcion3.setText("Descripción");
        jPanelAgregarCausa.add(jLabelDescripcion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jLabelCodigo5.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelCodigo5.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCodigo5.setText("Codigo");
        jPanelAgregarCausa.add(jLabelCodigo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, -1, -1));

        jTextFieldCodigo5.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldCodigo5.setText("Digite el codigo de la causa");
        jTextFieldCodigo5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldCodigo5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCodigo5FocusGained(evt);
            }
        });
        jPanelAgregarCausa.add(jTextFieldCodigo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, 180, 30));

        jPanelSliders.add(jPanelAgregarCausa, "card2");

        jPanelModificarCausa.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanelModificarCausaComponentHidden(evt);
            }
        });
        jPanelModificarCausa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBuscar1.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelBuscar1.setForeground(new java.awt.Color(51, 152, 219));
        jLabelBuscar1.setText("Codigo:");
        jPanelModificarCausa.add(jLabelBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, 30));

        jTextFieldBuscar1.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldBuscar1.setText("Digite el codigo de la causa");
        jTextFieldBuscar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldBuscar1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldBuscar1FocusGained(evt);
            }
        });
        jTextFieldBuscar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldBuscar1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldBuscar1MouseReleased(evt);
            }
        });
        jTextFieldBuscar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBuscar1KeyTyped(evt);
            }
        });
        jPanelModificarCausa.add(jTextFieldBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 180, 30));

        rSButtonMetroFiltrar1.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroFiltrar1.setText("Buscar");
        rSButtonMetroFiltrar1.setToolTipText("Buscar causa en el sistema");
        rSButtonMetroFiltrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar1ActionPerformed(evt);
            }
        });
        jPanelModificarCausa.add(rSButtonMetroFiltrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 110, 30));

        rSTablaCausas.setModel(new javax.swing.table.DefaultTableModel(
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
        rSTablaCausas.setColorBackgoundHead(new java.awt.Color(52, 120, 219));
        rSTablaCausas.setColorFilasForeground1(new java.awt.Color(52, 152, 219));
        rSTablaCausas.setColorFilasForeground2(new java.awt.Color(52, 152, 219));
        rSTablaCausas.setColorSelBackgound(new java.awt.Color(52, 152, 219));
        rSTablaCausas.setRowHeight(25);
        rSTablaCausas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSTablaCausasMouseClicked(evt);
            }
        });
        jScrollPaneTablaCausas.setViewportView(rSTablaCausas);

        jPanelModificarCausa.add(jScrollPaneTablaCausas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 620, 380));

        rSButtonMetroLimpiar12.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar12.setText("Limpiar");
        rSButtonMetroLimpiar12.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar12ActionPerformed(evt);
            }
        });
        jPanelModificarCausa.add(rSButtonMetroLimpiar12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 445, 110, 30));

        rSButtonMetroFiltrar10.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroFiltrar10.setText("Mostrar todo");
        rSButtonMetroFiltrar10.setToolTipText("Mostrar todas las causas del sistema");
        rSButtonMetroFiltrar10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar10ActionPerformed(evt);
            }
        });
        jPanelModificarCausa.add(rSButtonMetroFiltrar10, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 110, 30));

        jPanelSliders.add(jPanelModificarCausa, "card2");

        jPanelAsignarCausas.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanelAsignarCausasComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelAsignarCausasComponentShown(evt);
            }
        });
        jPanelAsignarCausas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelListaCausas.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelListaCausas.setForeground(new java.awt.Color(51, 152, 219));
        jLabelListaCausas.setText("Lista de Causas");
        jPanelAsignarCausas.add(jLabelListaCausas, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, -1, -1));

        rSComboMetroListaCausas.setColorBorde(new java.awt.Color(51, 152, 219));
        rSComboMetroListaCausas.setColorFondo(new java.awt.Color(51, 152, 219));
        jPanelAsignarCausas.add(rSComboMetroListaCausas, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 180, -1));

        rSButtonMetroLimpiar8.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar8.setText("Limpiar");
        rSButtonMetroLimpiar8.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar8ActionPerformed(evt);
            }
        });
        jPanelAsignarCausas.add(rSButtonMetroLimpiar8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 410, 150, 30));

        rSButtonAsignar2.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonAsignar2.setText("Enlazar");
        rSButtonAsignar2.setToolTipText("Asignar una causa a una cita");
        rSButtonAsignar2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        rSButtonAsignar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonAsignar2ActionPerformed(evt);
            }
        });
        jPanelAsignarCausas.add(rSButtonAsignar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 190, 70, 70));

        rSComboMetroListaRegistros.setColorBorde(new java.awt.Color(51, 152, 219));
        rSComboMetroListaRegistros.setColorFondo(new java.awt.Color(51, 152, 219));
        rSComboMetroListaRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSComboMetroListaRegistrosActionPerformed(evt);
            }
        });
        jPanelAsignarCausas.add(rSComboMetroListaRegistros, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 180, -1));

        jLabelListaRegistros.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelListaRegistros.setForeground(new java.awt.Color(51, 152, 219));
        jLabelListaRegistros.setText("Lista de Registros");
        jPanelAsignarCausas.add(jLabelListaRegistros, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jPanelSliders.add(jPanelAsignarCausas, "card2");

        jPanelAsignarMedicinas.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanelAsignarMedicinasComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelAsignarMedicinasComponentShown(evt);
            }
        });
        jPanelAsignarMedicinas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelListaMedicamentos.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelListaMedicamentos.setForeground(new java.awt.Color(51, 152, 219));
        jLabelListaMedicamentos.setText("Lista de Medicamentos");
        jLabelListaMedicamentos.setToolTipText("");
        jPanelAsignarMedicinas.add(jLabelListaMedicamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, -1, -1));

        rSComboMetroListaMedicamentos.setColorBorde(new java.awt.Color(51, 152, 219));
        rSComboMetroListaMedicamentos.setColorFondo(new java.awt.Color(51, 152, 219));
        jPanelAsignarMedicinas.add(rSComboMetroListaMedicamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 180, -1));

        rSButtonMetroLimpiar9.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar9.setText("Limpiar");
        rSButtonMetroLimpiar9.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar9ActionPerformed(evt);
            }
        });
        jPanelAsignarMedicinas.add(rSButtonMetroLimpiar9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 410, 150, 30));

        rSButtonAsignar3.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonAsignar3.setText("Enlazar");
        rSButtonAsignar3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        rSButtonAsignar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonAsignar3ActionPerformed(evt);
            }
        });
        jPanelAsignarMedicinas.add(rSButtonAsignar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 190, 70, 70));

        rSComboMetroListaRegistros1.setColorBorde(new java.awt.Color(51, 152, 219));
        rSComboMetroListaRegistros1.setColorFondo(new java.awt.Color(51, 152, 219));
        rSComboMetroListaRegistros1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSComboMetroListaRegistros1ActionPerformed(evt);
            }
        });
        jPanelAsignarMedicinas.add(rSComboMetroListaRegistros1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 180, -1));

        jLabelListaRegistros1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelListaRegistros1.setForeground(new java.awt.Color(51, 152, 219));
        jLabelListaRegistros1.setText("Lista de Registros");
        jPanelAsignarMedicinas.add(jLabelListaRegistros1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jPanelSliders.add(jPanelAsignarMedicinas, "card2");

        jPaneCitation.add(jPanelSliders, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 660, 510));

        jPanelPpal.add(jPaneCitation, "card4");

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

    private void jPanelCitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCitasMouseClicked
        jPaneCitation.setVisible(true);
        jPanelMenu.setVisible(false);
    }//GEN-LAST:event_jPanelCitasMouseClicked

    private void jPanelCitasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCitasMouseEntered
        ponerColor(jPanelCitas);
    }//GEN-LAST:event_jPanelCitasMouseEntered

    private void jPanelCitasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCitasMouseExited
        repintarColor(jPanelCitas);
    }//GEN-LAST:event_jPanelCitasMouseExited

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
       jPaneCitation.setVisible(false);
       jPanelMenu.setVisible(true);
    }//GEN-LAST:event_rSButtonPaneHomeMouseClicked

    private void rSButtonAgregarRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonAgregarRegistroMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelAgregarRegistro.setVisible(true);
    }//GEN-LAST:event_rSButtonAgregarRegistroMouseClicked

    private void rSButtonModificarCausasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonModificarCausasMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelModificarCausa.setVisible(true);
    }//GEN-LAST:event_rSButtonModificarCausasMouseClicked

    private void rSButtonMetroLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar1ActionPerformed
        limpiarComponentes(jPanelAgregarRegistro);
    }//GEN-LAST:event_rSButtonMetroLimpiar1ActionPerformed

    private void rSButtonMetroLimpiar12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar12ActionPerformed
        limpiarComponentes(jPanelModificarCausa);
        borrarTabla(rSTablaCausas);
    }//GEN-LAST:event_rSButtonMetroLimpiar12ActionPerformed

    private void jTextFieldBuscar1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscar1KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldBuscar1, 20, 0);
    }//GEN-LAST:event_jTextFieldBuscar1KeyTyped

    private void jTextFieldBuscar1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscar1MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscar1MousePressed

    private void jTextFieldBuscar1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscar1MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscar1MouseReleased

    private void rSButtonMetroGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroGuardar1ActionPerformed
            
    try{
        String cedula, codigoCita;
        String[] codigoCedulaSolo;
        String[] codigoCitaSolo;
        cedula = (String)this.rSComboMetroPaciente.getSelectedItem();
        codigoCita = (String)this.rSComboMetroCitas.getSelectedItem();
        codigoCedulaSolo = cedula.split(",");
        codigoCitaSolo = codigoCita.split(",");
        int descuento = controladora.descuentoCitas(codigoCedulaSolo[0]);
        String descuentoString = Integer.toString(descuento);
        String arregloValidar1[] = {cedula,codigoCita,descuentoString};
        
        if (!(validacionTotal.validarExcepciones(arregloValidar1, 2, 0, 1).equals("")))
        {
            new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 2, 0, 1)+"</center></html>").setVisible(true);                   
        }
        
        else
        {

            if(controladora.guardarRegistro(codigoCitaSolo[0], codigoCedulaSolo[0])){
                if(controladora.asignarDescuento(codigoCitaSolo[0], codigoCedulaSolo[0], descuentoString)){
                    new jInformation(this, true, "<html><center> Se asigno la cita correctamente, su descuento fue:"+descuentoString+"%</center></html>").setVisible(true);
                }else{
                    new jWarning(this, true, "<html><center> El paciente no tiene descuento</center></html>").setVisible(true);
                }
            }else{
                new jWarning(this, true, "<html><center> El paciente ya asistio a la cita, intente denuevo con otra cita y/o otro paciente</center></html>").setVisible(true);
            }
        }
    }
    catch (Exception e){        
    }
        
    }//GEN-LAST:event_rSButtonMetroGuardar1ActionPerformed

    private void jPanelAgregarRegistroComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAgregarRegistroComponentHidden
        rSButtonMetroLimpiar1.doClick();
    }//GEN-LAST:event_jPanelAgregarRegistroComponentHidden

    private void jPanelModificarCausaComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelModificarCausaComponentHidden
        limpiarComponentes(jPanelModificarCausa);
        borrarTabla(rSTablaCausas);
    }//GEN-LAST:event_jPanelModificarCausaComponentHidden

    private void rSTablaCausasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSTablaCausasMouseClicked
        String cadena="";
        DefaultTableModel modelo = (DefaultTableModel)rSTablaCausas.getModel();
        int row = rSTablaCausas.rowAtPoint(evt.getPoint());
        if (row >= 0 && rSTablaCausas.isEnabled())
        {
            for (int i=0; i < rSTablaCausas.getColumnCount();i++)
            {
               cadena=cadena + "," +  modelo.getValueAt(row,i).toString();
            }
        }
             
        String []cedula = cadena.split(",");
        new jConfirmation(this, true, "<html><center>¿Esta seguro que desea editar la causa? </center></html>").setVisible(true);      
        if(jConfirmation.opcion ==1)
        {
            ocultarSliders(jPanelSliders);
            jPanelAgregarCausa.setVisible(true);
            editarCausa(cedula[1]);
            
        } 
    }//GEN-LAST:event_rSTablaCausasMouseClicked

    private void rSButtonMetroFiltrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar1ActionPerformed
        String filtro = jTextFieldBuscar1.getText();    
        String arregloValidar1[] = {filtro};
        borrarTabla(rSTablaCausas);
        if (!(validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0).equals("")))
        {
           new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0)+"</center></html>").setVisible(true);                   
        }
        
        else
        {
            ArrayList <String> paciente = (ArrayList)controladora.mostrarunaCausa(filtro);
            if(paciente==null)
            {
                new jWarning(this, true,"<html><center>La causa no existe, intente denuevo</center></html>").setVisible(true);
            }

            else
            {                
                DefaultTableModel modelo = (DefaultTableModel) rSTablaCausas.getModel();
                String nombre = (paciente.get(1));
                String cedula = (paciente.get(0));
                modelo.addRow (new Object[]{cedula,nombre}); 
            }
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void jPanelAgregarRegistroComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAgregarRegistroComponentShown
        controladora.mostrarPacientesCombo(rSComboMetroPaciente);
    }//GEN-LAST:event_jPanelAgregarRegistroComponentShown

    private void rSButtonAsignarCausasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonAsignarCausasMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelAsignarCausas.setVisible(true);
    }//GEN-LAST:event_rSButtonAsignarCausasMouseClicked

    private void rSButtonAgregarCausasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonAgregarCausasMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelAgregarCausa.setVisible(true);
    }//GEN-LAST:event_rSButtonAgregarCausasMouseClicked

    private void rSButtonAsignarMedicamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonAsignarMedicamentosMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelAsignarMedicinas.setVisible(true);
    }//GEN-LAST:event_rSButtonAsignarMedicamentosMouseClicked

    private void rSButtonMetroGuardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroGuardar2ActionPerformed
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
            if(controladora.existeCausa(codigo)==false)
            {
                if(controladora.guardarCausas(codigo,nombre,descripcion))
                {
                    new jInformation(this, true, "<html><center> La causa se guardo correctamente </center></html>").setVisible(true);
                    limpiarComponentes(jPanelAgregarCausa);
                }                


                else
                {
                    new jWarning(this, true, "<html><center>La causa no se guardo</center></html>").setVisible(true);
                }  
            }

            else if(controladora.existeCausa(codigo))
            {   
               
                controladora.modificarCausas(codigo,nombre,descripcion);
                new jInformation(this, true, "<html><center> El area se actualizo correctamente </center></html>").setVisible(true);
            }
        }
    }//GEN-LAST:event_rSButtonMetroGuardar2ActionPerformed

    private void rSButtonMetroLimpiar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonMetroLimpiar2ActionPerformed

    private void jPanelAgregarCausaComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAgregarCausaComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanelAgregarCausaComponentHidden

    private void jPanelAgregarCausaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAgregarCausaComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanelAgregarCausaComponentShown

    private void rSButtonMetroLimpiar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar8ActionPerformed
        limpiarComponentes(jPanelAsignarCausas);
    }//GEN-LAST:event_rSButtonMetroLimpiar8ActionPerformed

    private void rSButtonAsignar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonAsignar2ActionPerformed
        try{
        String registro, causa;
        String[] codigoRegistro;
        String[] codigoCausa;
        registro = (String)this.rSComboMetroListaRegistros.getSelectedItem();
        causa = (String)this.rSComboMetroListaCausas.getSelectedItem();
        codigoRegistro = registro.split(",");
        codigoCausa = causa.split(",");

            if(controladora.causasRegistro(codigoRegistro[0], codigoCausa[0]))
            {
                new jInformation(this, true, "<html><center>La causa se asigno correctamente </center></html>").setVisible(true);
            }

            else
            {
                new jWarning(this, true, "<html><center>No fue posible asignar la causa ya fue asignada </center></html>").setVisible(true);
            }
        }
        catch(Exception e)
        {
            new jInformation(this, true, "<html><center>No ha seleccionado un elemento de la lista </center></html>").setVisible(true);
        }
    }//GEN-LAST:event_rSButtonAsignar2ActionPerformed

    private void rSComboMetroListaRegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSComboMetroListaRegistrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSComboMetroListaRegistrosActionPerformed

    private void jPanelAsignarCausasComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAsignarCausasComponentHidden
     
    }//GEN-LAST:event_jPanelAsignarCausasComponentHidden

    private void jPanelAsignarCausasComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAsignarCausasComponentShown
        controladora.mostrarRegistrosCombo(rSComboMetroListaRegistros);
        controladora.mostrarCausasCombo(rSComboMetroListaCausas);
    }//GEN-LAST:event_jPanelAsignarCausasComponentShown

    private void rSButtonMetroLimpiar9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar9ActionPerformed
        limpiarComponentes(jPanelAsignarMedicinas);
    }//GEN-LAST:event_rSButtonMetroLimpiar9ActionPerformed

    private void rSButtonAsignar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonAsignar3ActionPerformed
        try{
        String registro, medicamento;
        String[] codigoRegistro;
        String[] codigoMedicamento;
        registro = (String)this.rSComboMetroListaRegistros1.getSelectedItem();
        medicamento = (String)this.rSComboMetroListaMedicamentos.getSelectedItem();
        codigoRegistro = registro.split(",");
        codigoMedicamento = medicamento.split(",");
        
        if(controladora.medicamentosRegistro(codigoRegistro[0], codigoMedicamento[0]))
        {
            new jInformation(this, true, "<html><center>El medicamento se asigno correctamente </center></html>").setVisible(true);
        }

        else
        {
            new jInformation(this, true, "<html><center>No fue posible asignar la medicamento ya fue asignada </center></html>").setVisible(true);
        }
        }
        catch(Exception e)
        {
            new jInformation(this, true, "<html><center>No ha seleccionado ningun item de la lista </center></html>").setVisible(true);
        }
    }//GEN-LAST:event_rSButtonAsignar3ActionPerformed

    private void rSComboMetroListaRegistros1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSComboMetroListaRegistros1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSComboMetroListaRegistros1ActionPerformed

    private void jPanelAsignarMedicinasComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAsignarMedicinasComponentHidden
       
    }//GEN-LAST:event_jPanelAsignarMedicinasComponentHidden

    private void jPanelAsignarMedicinasComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAsignarMedicinasComponentShown
        controladora.mostrarRegistrosCombo(rSComboMetroListaRegistros1);
        controladora.mostrarMedicamentosCombo(rSComboMetroListaMedicamentos);
    }//GEN-LAST:event_jPanelAsignarMedicinasComponentShown

    private void rSComboMetroCitasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rSComboMetroCitasFocusGained
        try
        {
        String cedula;
        String[] codigoCedulaSolo;
        cedula = (String)this.rSComboMetroPaciente.getSelectedItem();
        codigoCedulaSolo = cedula.split(",");
        controladora.mostrarCitasCombo(rSComboMetroCitas, codigoCedulaSolo[0]);
        }
        catch (Exception e )
        {
            
        }
    }//GEN-LAST:event_rSComboMetroCitasFocusGained

    private void rSComboMetroCitasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSComboMetroCitasMouseEntered
        try
        {
        String cedula;
        String[] codigoCedulaSolo;
        cedula = (String)this.rSComboMetroPaciente.getSelectedItem();
        codigoCedulaSolo = cedula.split(",");
        controladora.mostrarCitasCombo(rSComboMetroCitas, codigoCedulaSolo[0]);
        }catch (Exception e)
        {
            
        }
    }//GEN-LAST:event_rSComboMetroCitasMouseEntered

    private void rSComboMetroCitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSComboMetroCitasMouseClicked
        
        try
        {String cedula;
        String[] codigoCedulaSolo;
        cedula = (String)this.rSComboMetroPaciente.getSelectedItem();
        codigoCedulaSolo = cedula.split(",");
        controladora.mostrarCitasCombo(rSComboMetroCitas, codigoCedulaSolo[0]);
        }catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_rSComboMetroCitasMouseClicked

    private void jTextAreaDescripcionAreaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaDescripcionAreaMousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextAreaDescripcionAreaMousePressed

    private void jTextAreaDescripcionAreaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaDescripcionAreaMouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextAreaDescripcionAreaMouseReleased

    private void jTextAreaDescripcionAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaDescripcionAreaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextAreaDescripcionAreaKeyTyped

    private void rSButtonMetroFiltrar10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar10ActionPerformed
        borrarTabla(rSTablaCausas);
        try{

            controladora.consultarCausas(rSTablaCausas);
        }
        catch(Exception e)
        {

        }
    }//GEN-LAST:event_rSButtonMetroFiltrar10ActionPerformed

    private void jTextFieldNombre5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombre5FocusGained
        jTextFieldNombre5.setText("");
    }//GEN-LAST:event_jTextFieldNombre5FocusGained

    private void jTextFieldCodigo5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigo5FocusGained
        jTextFieldCodigo5.setText("");
    }//GEN-LAST:event_jTextFieldCodigo5FocusGained

    private void jTextAreaDescripcionAreaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextAreaDescripcionAreaFocusGained
         jTextAreaDescripcionArea.setText("");
    }//GEN-LAST:event_jTextAreaDescripcionAreaFocusGained

    private void jTextFieldBuscar1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBuscar1FocusGained
        jTextFieldBuscar1.setText("");
    }//GEN-LAST:event_jTextFieldBuscar1FocusGained

//=======================================================================================================
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton jButtonSlider;
    private javax.swing.JLabel jLabelAgregarRegistro;
    private javax.swing.JLabel jLabelAsignarCausas;
    private javax.swing.JLabel jLabelAsignarCausas1;
    private javax.swing.JLabel jLabelAsignarCausas2;
    private javax.swing.JLabel jLabelAsignarCausas3;
    private javax.swing.JLabel jLabelAsistencia;
    private javax.swing.JLabel jLabelBuscar1;
    private javax.swing.JLabel jLabelCausas;
    private javax.swing.JLabel jLabelCedulaMedico;
    private javax.swing.JLabel jLabelCedulaPaciente;
    private javax.swing.JLabel jLabelCita;
    private javax.swing.JLabel jLabelCodigo5;
    private javax.swing.JLabel jLabelDescripcion3;
    private javax.swing.JLabel jLabelHome;
    private javax.swing.JLabel jLabelListaCausas;
    private javax.swing.JLabel jLabelListaMedicamentos;
    private javax.swing.JLabel jLabelListaRegistros;
    private javax.swing.JLabel jLabelListaRegistros1;
    private javax.swing.JLabel jLabelMedicinas;
    private javax.swing.JLabel jLabelNombre5;
    private javax.swing.JLabel jLabelRol1;
    private javax.swing.JLabel jLabelRol2;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelTituloCitacion;
    private javax.swing.JLabel jLabelimgCerrarSesion;
    private javax.swing.JLabel jLabelimgCita;
    private javax.swing.JLabel jLabelimgCitacion;
    private javax.swing.JLabel jLabelimgPerfil;
    private javax.swing.JPanel jPaneCitation;
    private javax.swing.JPanel jPanelAgregarCausa;
    private javax.swing.JPanel jPanelAgregarRegistro;
    private javax.swing.JPanel jPanelAsignarCausas;
    private javax.swing.JPanel jPanelAsignarMedicinas;
    private javax.swing.JPanel jPanelCitas;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelModificarCausa;
    private javax.swing.JPanel jPanelPerfil;
    private javax.swing.JPanel jPanelPpal;
    private javax.swing.JPanel jPanelRol;
    private javax.swing.JPanel jPanelSlider;
    private javax.swing.JPanel jPanelSliders;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JPanel jPanelTituloCitacion;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPaneTablaCausas;
    private javax.swing.JSeparator jSeparatorCausas;
    private javax.swing.JSeparator jSeparatorMedicinas;
    private javax.swing.JSeparator jSeparatorRegistros;
    private javax.swing.JTextArea jTextAreaDescripcionArea;
    private javax.swing.JTextField jTextFieldBuscar1;
    private javax.swing.JTextField jTextFieldCodigo5;
    private javax.swing.JTextField jTextFieldNombre5;
    private rojerusan.RSButtonPane rSButtonAgregarCausas;
    private rojerusan.RSButtonPane rSButtonAgregarRegistro;
    private rojerusan.RSButtonCircle rSButtonAsignar2;
    private rojerusan.RSButtonCircle rSButtonAsignar3;
    private rojerusan.RSButtonPane rSButtonAsignarCausas;
    private rojerusan.RSButtonPane rSButtonAsignarMedicamentos;
    private rojerusan.RSButtonPane rSButtonCerrarSesion;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar1;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar10;
    private rojerusan.RSButtonMetro rSButtonMetroGuardar1;
    private rojerusan.RSButtonMetro rSButtonMetroGuardar2;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar1;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar12;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar2;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar8;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar9;
    private rojerusan.RSButtonPane rSButtonModificarCausas;
    private rojerusan.RSButtonPane rSButtonPaneHome;
    private rojerusan.RSComboMetro rSComboMetroCitas;
    private rojerusan.RSComboMetro rSComboMetroListaCausas;
    private rojerusan.RSComboMetro rSComboMetroListaMedicamentos;
    private rojerusan.RSComboMetro rSComboMetroListaRegistros;
    private rojerusan.RSComboMetro rSComboMetroListaRegistros1;
    private rojerusan.RSComboMetro rSComboMetroPaciente;
    private rojerusan.RSPopuMenu rSPopuMenu1;
    private rojerusan.RSTableMetro rSTablaCausas;
    // End of variables declaration//GEN-END:variables

}    

