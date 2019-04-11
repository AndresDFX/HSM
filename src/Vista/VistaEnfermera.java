package Vista;

import Controladora.*;
import Modelo.Validaciones;
import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
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
import java.sql.Time;
import java.util.Calendar;

/**
 * [VistaAdministrador] Clase de interfaz GUI del usuario Administrador
 * @since 07/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class VistaEnfermera extends javax.swing.JFrame {
    
    //Declaracion de Variables Auxiliares
    private ActionMap acciones ;
    private Action accionCopiar;
    private Action accionPegar;
    private Action accionCortar;
    private Validaciones validacionTotal; 
    private Control controladora;
    private Image iconoVentana;
    private static VistaEnfermera INSTANCE = null;
   
//=======================================================================================================    
    /**
     * Constructor de Clase VistaAdministrador
     * @since VistaAdministrador.java
     */
    
    public VistaEnfermera() {

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
            INSTANCE = new VistaEnfermera();         
        }
    }
//=======================================================================================================
    /**
     * Metodo que permite obtener la instancia de la clase que ha sido creada
     * @return El objeto de la instancia de la clase
     */
    
    public static VistaEnfermera getInstancia(){
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
        rSTablaCitas.setDefaultEditor(Object.class, null);
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
        
        rSComboMetroMedico.getModel().setSelectedItem(cita.get(1)+", "+cita.get(4));
        rSComboMetroPaciente.getModel().setSelectedItem(cita.get(0)+", "+cita.get(3));
        rSComboMetroPaciente.setEnabled(false);
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
        roboto1 = new efectos.Roboto();
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
        jLabelCitas = new javax.swing.JLabel();
        jSeparatorCitas = new javax.swing.JSeparator();
        rSButtonPaneHome = new rojerusan.RSButtonPane();
        jLabelHome = new javax.swing.JLabel();
        rSButtonAgregarCita = new rojerusan.RSButtonPane();
        jLabelAgregarCita = new javax.swing.JLabel();
        rSButtonModificarCita = new rojerusan.RSButtonPane();
        jLabelModificarCita = new javax.swing.JLabel();
        jPanelSliders = new javax.swing.JPanel();
        jPanelAgregarCita = new javax.swing.JPanel();
        jLabelHoraCita = new javax.swing.JLabel();
        jLabelCedulaPaciente = new javax.swing.JLabel();
        jLabelCedulaMedico = new javax.swing.JLabel();
        jLabelFechaCita = new javax.swing.JLabel();
        rSButtonMetroGuardar1 = new rojerusan.RSButtonMetro();
        rSButtonMetroLimpiar1 = new rojerusan.RSButtonMetro();
        rSDateChooserFechaCita = new rojeru_san.componentes.RSDateChooser();
        rSComboMetroPaciente = new rojerusan.RSComboMetro();
        rSComboMetroMedico = new rojerusan.RSComboMetro();
        timePanelHoraCita = new com.lavantech.gui.comp.TimePanel();
        jPanelModificarCita = new javax.swing.JPanel();
        jLabelBuscar1 = new javax.swing.JLabel();
        jTextFieldBuscar1 = new javax.swing.JTextField();
        rSButtonMetroFiltrar1 = new rojerusan.RSButtonMetro();
        jScrollPaneTablaCitas = new javax.swing.JScrollPane();
        rSTablaCitas = new rojerusan.RSTableMetro();
        rSButtonMetroLimpiar12 = new rojerusan.RSButtonMetro();
        rSButtonMetroFiltrar10 = new rojerusan.RSButtonMetro();

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
        jLabelTitulo.setText("Portal Enfermera");
        jLabelTitulo.setToolTipText("");
        jPanelPerfil.add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 730, -1));

        jPanelTitulo.add(jPanelPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 740, 100));

        jLabelimgPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/nurse_256px.png"))); // NOI18N
        jPanelTitulo.add(jLabelimgPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 230, 210));

        jPanelMenu.add(jPanelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 210));

        jPanelRol.setBackground(new java.awt.Color(52, 152, 219));
        jPanelRol.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelRol1.setBackground(new java.awt.Color(52, 152, 219));
        jLabelRol1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabelRol1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRol1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRol1.setText("SGH");
        jPanelRol.add(jLabelRol1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, 80));

        jLabelRol2.setBackground(new java.awt.Color(52, 152, 219));
        jLabelRol2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
        jLabelRol2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRol2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRol2.setText("Bienvenido al");
        jPanelRol.add(jLabelRol2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 210, 80));

        jPanelMenu.add(jPanelRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 210, 390));

        jPanelCitas.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCitas.setToolTipText("Agendamiento de citas");
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
        jLabelCita.setText("Agendar Citas");
        jPanelCitas.add(jLabelCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 150, 30));

        jLabelimgCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/agendamensual_100px.png"))); // NOI18N
        jPanelCitas.add(jLabelimgCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 120));

        jPanelMenu.add(jPanelCitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, 150, 170));

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

        jPanelMenu.add(rSButtonCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 300, 150, 170));

        jPanelPpal.add(jPanelMenu, "card2");

        jPaneCitation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTituloCitacion.setBackground(new java.awt.Color(51, 152, 219));
        jPanelTituloCitacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTituloCitacion.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabelTituloCitacion.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloCitacion.setText("AGENDAMIENTO DE CITAS");
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

        jLabelCitas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelCitas.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/cita_50px.png"))); // NOI18N
        jLabelCitas.setText("CITAS");
        jPanelSlider.add(jLabelCitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 50));

        jSeparatorCitas.setForeground(new java.awt.Color(255, 255, 255));
        jSeparatorCitas.setPreferredSize(new java.awt.Dimension(240, 4));
        jPanelSlider.add(jSeparatorCitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));

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

        rSButtonAgregarCita.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonAgregarCita.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonAgregarCita.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonAgregarCita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonAgregarCitaMouseClicked(evt);
            }
        });
        rSButtonAgregarCita.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAgregarCita.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAgregarCita.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAgregarCita.setText(" Agregar Cita");
        rSButtonAgregarCita.add(jLabelAgregarCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider.add(rSButtonAgregarCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 240, -1));

        rSButtonModificarCita.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonModificarCita.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonModificarCita.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonModificarCita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonModificarCitaMouseClicked(evt);
            }
        });
        rSButtonModificarCita.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelModificarCita.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelModificarCita.setForeground(new java.awt.Color(255, 255, 255));
        jLabelModificarCita.setText(" Modificar Cita");
        rSButtonModificarCita.add(jLabelModificarCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider.add(rSButtonModificarCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 240, -1));

        jPaneCitation.add(jPanelSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 240, 510));

        jPanelSliders.setLayout(new java.awt.CardLayout());

        jPanelAgregarCita.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanelAgregarCitaComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelAgregarCitaComponentShown(evt);
            }
        });
        jPanelAgregarCita.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHoraCita.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelHoraCita.setForeground(new java.awt.Color(51, 152, 219));
        jLabelHoraCita.setText("Hora Cita");
        jPanelAgregarCita.add(jLabelHoraCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 200, -1, -1));

        jLabelCedulaPaciente.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelCedulaPaciente.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCedulaPaciente.setText("Identificación Paciente");
        jPanelAgregarCita.add(jLabelCedulaPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jLabelCedulaMedico.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelCedulaMedico.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCedulaMedico.setText("Identificación Medico");
        jPanelAgregarCita.add(jLabelCedulaMedico, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, -1, -1));

        jLabelFechaCita.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelFechaCita.setForeground(new java.awt.Color(51, 152, 219));
        jLabelFechaCita.setText("Fecha Cita");
        jPanelAgregarCita.add(jLabelFechaCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, -1, -1));

        rSButtonMetroGuardar1.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroGuardar1.setText("Guardar");
        rSButtonMetroGuardar1.setToolTipText("Guardar datos ingresados");
        rSButtonMetroGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroGuardar1ActionPerformed(evt);
            }
        });
        jPanelAgregarCita.add(rSButtonMetroGuardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 150, 30));

        rSButtonMetroLimpiar1.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar1.setText("Limpiar");
        rSButtonMetroLimpiar1.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar1ActionPerformed(evt);
            }
        });
        jPanelAgregarCita.add(rSButtonMetroLimpiar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 150, 30));

        rSDateChooserFechaCita.setColorBackground(new java.awt.Color(51, 152, 219));
        rSDateChooserFechaCita.setPlaceholder("Seleccione un Día");
        jPanelAgregarCita.add(rSDateChooserFechaCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 180, -1));

        rSComboMetroPaciente.setMaximumRowCount(6);
        rSComboMetroPaciente.setColorBorde(new java.awt.Color(51, 152, 219));
        rSComboMetroPaciente.setColorFondo(new java.awt.Color(51, 152, 219));
        jPanelAgregarCita.add(rSComboMetroPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 180, -1));

        rSComboMetroMedico.setMaximumRowCount(6);
        rSComboMetroMedico.setColorBorde(new java.awt.Color(51, 152, 219));
        rSComboMetroMedico.setColorFondo(new java.awt.Color(51, 152, 219));
        jPanelAgregarCita.add(rSComboMetroMedico, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 180, -1));
        jPanelAgregarCita.add(timePanelHoraCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, 150, 150));

        jPanelSliders.add(jPanelAgregarCita, "card2");

        jPanelModificarCita.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanelModificarCitaComponentHidden(evt);
            }
        });
        jPanelModificarCita.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBuscar1.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelBuscar1.setForeground(new java.awt.Color(51, 152, 219));
        jLabelBuscar1.setText("Codigo:");
        jPanelModificarCita.add(jLabelBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, 30));

        jTextFieldBuscar1.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldBuscar1.setText("Digite el codigo de la cita");
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
        jPanelModificarCita.add(jTextFieldBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 180, 30));

        rSButtonMetroFiltrar1.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroFiltrar1.setText("Buscar");
        rSButtonMetroFiltrar1.setToolTipText("Buscar el codigo de la cita");
        rSButtonMetroFiltrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar1ActionPerformed(evt);
            }
        });
        jPanelModificarCita.add(rSButtonMetroFiltrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 110, 30));

        rSTablaCitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo", "cedula", "cedula_medico"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        rSTablaCitas.setColorBackgoundHead(new java.awt.Color(52, 120, 219));
        rSTablaCitas.setColorFilasForeground1(new java.awt.Color(52, 152, 219));
        rSTablaCitas.setColorFilasForeground2(new java.awt.Color(52, 152, 219));
        rSTablaCitas.setColorSelBackgound(new java.awt.Color(52, 152, 219));
        rSTablaCitas.setRowHeight(25);
        rSTablaCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSTablaCitasMouseClicked(evt);
            }
        });
        jScrollPaneTablaCitas.setViewportView(rSTablaCitas);

        jPanelModificarCita.add(jScrollPaneTablaCitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 620, 380));

        rSButtonMetroLimpiar12.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar12.setText("Limpiar");
        rSButtonMetroLimpiar12.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar12ActionPerformed(evt);
            }
        });
        jPanelModificarCita.add(rSButtonMetroLimpiar12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 445, 110, 30));

        rSButtonMetroFiltrar10.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroFiltrar10.setText("Mostrar todo");
        rSButtonMetroFiltrar10.setToolTipText("Mostrar todas las citas del sistema");
        rSButtonMetroFiltrar10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar10ActionPerformed(evt);
            }
        });
        jPanelModificarCita.add(rSButtonMetroFiltrar10, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 110, 30));

        jPanelSliders.add(jPanelModificarCita, "card2");

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

    private void rSButtonAgregarCitaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonAgregarCitaMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelAgregarCita.setVisible(true);
    }//GEN-LAST:event_rSButtonAgregarCitaMouseClicked

    private void rSButtonModificarCitaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonModificarCitaMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelModificarCita.setVisible(true);
    }//GEN-LAST:event_rSButtonModificarCitaMouseClicked

    private void rSButtonMetroLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar1ActionPerformed
        limpiarComponentes(jPanelAgregarCita);
        rSDateChooserFechaCita.setDatoFecha(null);
    }//GEN-LAST:event_rSButtonMetroLimpiar1ActionPerformed

    private void rSButtonMetroLimpiar12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar12ActionPerformed
        limpiarComponentes(jPanelModificarCita);
        borrarTabla(rSTablaCitas);
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
            String horaCita, fechaCita;
            String cedulaPaciente, cedulaMedico;
            String[] codigoPacienteSolo;
            String[] codigoMedicoSolo;
            cedulaPaciente = (String)this.rSComboMetroPaciente.getSelectedItem();
            cedulaMedico = (String)this.rSComboMetroMedico.getSelectedItem();
            codigoPacienteSolo = cedulaPaciente.split(",");
            codigoMedicoSolo = cedulaMedico.split(",");
                    
            Calendar hora =  timePanelHoraCita.getCalendar();  
            long tiempo = rSDateChooserFechaCita.getDatoFecha().getTime();
            long tiempoReal = hora.getTimeInMillis();
            Timestamp fechaSQL = new Timestamp (tiempo);
            Time horaReal = new Time(tiempoReal);
            fechaCita = fechaSQL.toString();
            horaCita = horaReal+"";
            System.out.println(horaCita);
            System.out.println(fechaCita);
            String arregloValidar1[] = {fechaCita,horaCita,cedulaPaciente,cedulaMedico};

            
            if (!(validacionTotal.validarExcepciones(arregloValidar1, 4, 0, 0).equals("")))
            {
               new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 4, 0, 0)+"</center></html>").setVisible(true);                   
            }
            
            else
            {
                switch(controladora.guardarCitas(fechaCita, horaCita, codigoPacienteSolo[0], codigoMedicoSolo[0])){
                    case 0: 
                        new jInformation(this, true, "<html><center> La cita se guardo correctamente </center></html>").setVisible(true);
                        limpiarComponentes(jPanelAgregarCita);
                      break;
                    case 1: 
                        new jWarning(this, true, "<html><center> No existe el medico con la cedula: "+cedulaMedico+"</center></html>").setVisible(true);
                      break;
                    case 2: 
                        new jWarning(this, true, "<html><center> No existe el paciente con la cedula: "+cedulaPaciente+"</center></html>").setVisible(true);
                      break;
                    case -1: 
                        new jWarning(this, true, "<html><center> Ya existe la cita, intente en una fecha diferente</center></html>").setVisible(true);
                       break;
                }
            }
        }
        catch(Exception e)
        {
            new jWarning(this, true, "<html><center> No se ha seleccionado algun item</center></html>").setVisible(true);
        }      
    }//GEN-LAST:event_rSButtonMetroGuardar1ActionPerformed

    private void jPanelAgregarCitaComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAgregarCitaComponentHidden
        rSButtonMetroLimpiar1.doClick();
    }//GEN-LAST:event_jPanelAgregarCitaComponentHidden

    private void jPanelModificarCitaComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelModificarCitaComponentHidden
        limpiarComponentes(jPanelModificarCita);
        borrarTabla(rSTablaCitas);
    }//GEN-LAST:event_jPanelModificarCitaComponentHidden

    private void rSTablaCitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSTablaCitasMouseClicked
        String cadena="";
        DefaultTableModel modelo = (DefaultTableModel)rSTablaCitas.getModel();
        int row = rSTablaCitas.rowAtPoint(evt.getPoint());
        if (row >= 0 && rSTablaCitas.isEnabled())
        {
            for (int i=0; i < rSTablaCitas.getColumnCount();i++)
            {
               cadena=cadena + "," +  modelo.getValueAt(row,i).toString();
            }
        }
             
        String []cedula = cadena.split(",");
        new jConfirmation(this, true, "<html><center>¿Esta seguro que desea editar la cita ? </center></html>").setVisible(true);      
        if(jConfirmation.opcion ==1)
        {
            ocultarSliders(jPanelSliders);
            jPanelAgregarCita.setVisible(true);
            editarCita(cedula[1]);
            
        } 
    }//GEN-LAST:event_rSTablaCitasMouseClicked

    private void rSButtonMetroFiltrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar1ActionPerformed
        String filtro = jTextFieldBuscar1.getText();    
        String arregloValidar1[] = {filtro};
        borrarTabla(rSTablaCitas);
        if (!(validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0).equals("")))
        {
           new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0)+"</center></html>").setVisible(true);                   
        }
        
        else
        {
            ArrayList <String> paciente = (ArrayList)controladora.mostrarunaCita(filtro);
            if(paciente==null)
            {
                new jWarning(this, true,"<html><center>El cita no existe, intente denuevo</center></html>").setVisible(true);
            }

            else
            {                
                DefaultTableModel modelo = (DefaultTableModel) rSTablaCitas.getModel();
                String cargo = (paciente.get(1));
                String nombre = (paciente.get(0));
                String cedula = (paciente.get(2));
                modelo.addRow (new Object[]{cedula,nombre,cargo}); 
            }
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void jPanelAgregarCitaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAgregarCitaComponentShown
        controladora.mostrarMedicosCombo(rSComboMetroMedico);
        controladora.mostrarPacientesCombo(rSComboMetroPaciente);
        rSComboMetroPaciente.setEnabled(true);
    }//GEN-LAST:event_jPanelAgregarCitaComponentShown

    private void rSButtonMetroFiltrar10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar10ActionPerformed
        borrarTabla(rSTablaCitas);
        try{

            controladora.consultarCitas(rSTablaCitas);
            
        }
        catch(Exception e)
        {

        }
    }//GEN-LAST:event_rSButtonMetroFiltrar10ActionPerformed

    private void jTextFieldBuscar1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBuscar1FocusGained
        jTextFieldBuscar1.setText("");
    }//GEN-LAST:event_jTextFieldBuscar1FocusGained

//=======================================================================================================

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton jButtonSlider;
    private javax.swing.JLabel jLabelAgregarCita;
    private javax.swing.JLabel jLabelBuscar1;
    private javax.swing.JLabel jLabelCedulaMedico;
    private javax.swing.JLabel jLabelCedulaPaciente;
    private javax.swing.JLabel jLabelCita;
    private javax.swing.JLabel jLabelCitas;
    private javax.swing.JLabel jLabelFechaCita;
    private javax.swing.JLabel jLabelHome;
    private javax.swing.JLabel jLabelHoraCita;
    private javax.swing.JLabel jLabelModificarCita;
    private javax.swing.JLabel jLabelRol1;
    private javax.swing.JLabel jLabelRol2;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelTituloCitacion;
    private javax.swing.JLabel jLabelimgCerrarSesion;
    private javax.swing.JLabel jLabelimgCita;
    private javax.swing.JLabel jLabelimgCitacion;
    private javax.swing.JLabel jLabelimgPerfil;
    private javax.swing.JPanel jPaneCitation;
    private javax.swing.JPanel jPanelAgregarCita;
    private javax.swing.JPanel jPanelCitas;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelModificarCita;
    private javax.swing.JPanel jPanelPerfil;
    private javax.swing.JPanel jPanelPpal;
    private javax.swing.JPanel jPanelRol;
    private javax.swing.JPanel jPanelSlider;
    private javax.swing.JPanel jPanelSliders;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JPanel jPanelTituloCitacion;
    private javax.swing.JScrollPane jScrollPaneTablaCitas;
    private javax.swing.JSeparator jSeparatorCitas;
    private javax.swing.JTextField jTextFieldBuscar1;
    private rojerusan.RSButtonPane rSButtonAgregarCita;
    private rojerusan.RSButtonPane rSButtonCerrarSesion;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar1;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar10;
    private rojerusan.RSButtonMetro rSButtonMetroGuardar1;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar1;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar12;
    private rojerusan.RSButtonPane rSButtonModificarCita;
    private rojerusan.RSButtonPane rSButtonPaneHome;
    private rojerusan.RSComboMetro rSComboMetroMedico;
    private rojerusan.RSComboMetro rSComboMetroPaciente;
    private rojeru_san.componentes.RSDateChooser rSDateChooserFechaCita;
    private rojerusan.RSPopuMenu rSPopuMenu1;
    private rojerusan.RSTableMetro rSTablaCitas;
    private efectos.Roboto roboto1;
    private com.lavantech.gui.comp.TimePanel timePanelHoraCita;
    // End of variables declaration//GEN-END:variables

}    

