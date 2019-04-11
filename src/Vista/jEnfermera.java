package Vista;

import Controladora.Control;
import Modelo.Validaciones;
import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultEditorKit;

/**
 * [jEnfermera] Clase de interfaz GUI de ventana modal de dialogos para registro de Enfermeras
 * @since 24/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class jEnfermera extends javax.swing.JDialog {

    //Declaracion de variables de clase
    public static int opcion=2; 
    private String nombre, cedula, direccion, telefono, cargo, email, area, contrasena,cedulaJefe;
    private double salario;
       
    //Declaracion de variables de objetos
    private Control controladora;
    private Validaciones validacionTotal;
 
//=======================================================================================================
    /**
     * Constructor de la clase jEnfemera
     * @param parent Frame que llama a este jDialog
     * @param modal Variable que determina si la ventana es modal o no
     * @param cedula Variable que almacena la cedula del empleado
     * @param nombre Variable que almacena el nombre del empleado
     * @param direccion Variable que almacena la direccion de residencia del empleado
     * @param salario Variable que almacena el salario del empleado
     * @param cargo Variable que almacena el cargo del empleado
     * @param email Variable que almacena el correo electronico del empleado
     * @param area Variable que almacena el area al que pertenece el empleado
     * @param telefono Variable que almacena el telefono de contacto del empleado
     * @param contrasena Variable que almacena la contrasena de ingreso al sistema del empleado (cedula)
     * @param cedulaJefe Variable que almacena la cedula del jefe del empleado
     * @since jEnfermera.java
     */
    public jEnfermera(java.awt.Frame parent, boolean modal,String cedula, String nombre, String direccion, String telefono, String cargo, String email, String area, double salario, String contrasena, String cedulaJefe) 
    {
        
        super(parent, modal);
        initComponents();
               
        //Inicializacion de Variables de Clase
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cargo = cargo;
        this.email = email;
        this.area = area;
        this.salario = salario;
        this.contrasena = contrasena;
        this.cedulaJefe = cedulaJefe;
        
        //Inicializacion de Variables de objetos
        controladora = new Control();
        validacionTotal = new Validaciones();
        
        //Ajustes graficos
        this.setLocationRelativeTo(null);

    }

    
//=======================================================================================================
    /**
     * Metodo para poner un color cuando el mouse este encima del panel
     * @param panel Panel que se pondra el color 
     */
    public void ponerColor(JPanel panel)
    {
        panel.setBackground(new Color(240,240,240));
    }

//=======================================================================================================
    /**
     * Metodo para poner un color cuando el mouse se quite del panel
     * @param panel Panel que se pondra el color 
     */
    public void repintarColor(JPanel panel)
    {
        panel.setBackground(new Color(204,204,204));
    }

//=======================================================================================================    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSPopuMenu1 = new rojerusan.RSPopuMenu();
        jPanelPpal = new javax.swing.JPanel();
        jPanelTitulo = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelimgTitulo = new javax.swing.JLabel();
        jPanelRegistrar = new javax.swing.JPanel();
        jLabelRegistrar = new javax.swing.JLabel();
        jPanelCancelar = new javax.swing.JPanel();
        jLabelCancelar = new javax.swing.JLabel();
        jLabelCedula = new javax.swing.JLabel();
        jTextFieldCedula = new javax.swing.JTextField();
        jLabelAnosExperiencia = new javax.swing.JLabel();
        jTextFieldAnosExperiencia = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanelPpal.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPpal.setMinimumSize(new java.awt.Dimension(860, 600));
        jPanelPpal.setPreferredSize(new java.awt.Dimension(854, 600));
        jPanelPpal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTitulo.setBackground(new java.awt.Color(51, 152, 219));
        jPanelTitulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitulo.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitulo.setText("Registrar Enfermera");
        jPanelTitulo.add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, 40));

        jLabelimgTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/JDialog/enfermera_38px.png"))); // NOI18N
        jPanelTitulo.add(jLabelimgTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 40, 60));

        jPanelPpal.add(jPanelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 60));

        jPanelRegistrar.setBackground(new java.awt.Color(204, 204, 204));
        jPanelRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelRegistrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelRegistrarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelRegistrarMousePressed(evt);
            }
        });
        jPanelRegistrar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelRegistrar.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelRegistrar.setForeground(new java.awt.Color(51, 152, 219));
        jLabelRegistrar.setText("Registrar");
        jPanelRegistrar.add(jLabelRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 6, -1, 20));

        jPanelPpal.add(jPanelRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 100, 30));

        jPanelCancelar.setBackground(new java.awt.Color(204, 204, 204));
        jPanelCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelCancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelCancelarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelCancelarMousePressed(evt);
            }
        });
        jPanelCancelar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelCancelar.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelCancelar.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCancelar.setText(" Cancelar");
        jPanelCancelar.add(jLabelCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 70, 20));

        jPanelPpal.add(jPanelCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, 90, 30));

        jLabelCedula.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelCedula.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCedula.setText("Identificación");
        jPanelPpal.add(jLabelCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jTextFieldCedula.setEditable(false);
        jTextFieldCedula.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldCedula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCedulaKeyTyped(evt);
            }
        });
        jPanelPpal.add(jTextFieldCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 180, 30));

        jLabelAnosExperiencia.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelAnosExperiencia.setForeground(new java.awt.Color(51, 152, 219));
        jLabelAnosExperiencia.setText("Años de experiencia");
        jPanelPpal.add(jLabelAnosExperiencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        jTextFieldAnosExperiencia.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldAnosExperiencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldAnosExperiencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldAnosExperienciaKeyTyped(evt);
            }
        });
        jPanelPpal.add(jTextFieldAnosExperiencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 180, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPpal, javax.swing.GroupLayout.PREFERRED_SIZE, 513, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPpal, javax.swing.GroupLayout.PREFERRED_SIZE, 357, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelRegistrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRegistrarMousePressed
                
        String anho = jTextFieldAnosExperiencia.getText();
        String arregloValidar1[] = {anho};
                   
        if (validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0).equals(""))
        {
            if(controladora.existeEmpleadoPersona(cedula)==false)
            {
                if(controladora.guardarEmpleado(email, cargo, salario, cedula, nombre, direccion, telefono, area,contrasena,cedulaJefe))
                {
                    controladora.guardarEnfermeras(anho, cedula);
                    opcion=1;
                    this.setVisible(false);
                }
                else
                {
                    this.setVisible(false);
                }
            }
            
            else if(controladora.existeEmpleadoPersona(cedula))
            {
                controladora.modificarEmpleado(email, cargo, salario, cedula, nombre, direccion, telefono, area, cedulaJefe);
                controladora.modificarEnfermera(anho, cedula);
                opcion=1;
                this.setVisible(false);
            }
        }   
    }//GEN-LAST:event_jPanelRegistrarMousePressed

    private void jPanelRegistrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRegistrarMouseExited
        repintarColor(jPanelRegistrar);
    }//GEN-LAST:event_jPanelRegistrarMouseExited

    private void jPanelRegistrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRegistrarMouseEntered
        ponerColor(jPanelRegistrar);
    }//GEN-LAST:event_jPanelRegistrarMouseEntered

    private void jPanelCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCancelarMouseEntered
        ponerColor(jPanelCancelar);
    }//GEN-LAST:event_jPanelCancelarMouseEntered

    private void jPanelCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCancelarMouseExited
        repintarColor(jPanelCancelar);
    }//GEN-LAST:event_jPanelCancelarMouseExited

    private void jPanelCancelarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCancelarMousePressed
        opcion=0;
        this.setVisible(false);
    }//GEN-LAST:event_jPanelCancelarMousePressed

    private void jTextFieldCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCedulaKeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldCedula, 10, 0);
    }//GEN-LAST:event_jTextFieldCedulaKeyTyped

    private void jTextFieldAnosExperienciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAnosExperienciaKeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldAnosExperiencia, 10, 0);
    }//GEN-LAST:event_jTextFieldAnosExperienciaKeyTyped

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        jTextFieldCedula.setText(cedula);
    }//GEN-LAST:event_formWindowOpened

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelAnosExperiencia;
    private javax.swing.JLabel jLabelCancelar;
    private javax.swing.JLabel jLabelCedula;
    private javax.swing.JLabel jLabelRegistrar;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelimgTitulo;
    private javax.swing.JPanel jPanelCancelar;
    private javax.swing.JPanel jPanelPpal;
    private javax.swing.JPanel jPanelRegistrar;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JTextField jTextFieldAnosExperiencia;
    private javax.swing.JTextField jTextFieldCedula;
    private rojerusan.RSPopuMenu rSPopuMenu1;
    // End of variables declaration//GEN-END:variables

}
