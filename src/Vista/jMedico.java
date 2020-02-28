package Vista;

import Controlador.Control;
import Modelo.Validaciones;
import java.awt.*;
import javax.swing.JPanel;

/**
 * [jMedico] Clase de interfaz GUI de ventana modal de dialogos para registro de Medicos
 * @since 24/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class jMedico extends javax.swing.JDialog {

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
    public jMedico(java.awt.Frame parent, boolean modal,String cedula, String nombre, String direccion, String telefono, String cargo, String email, String area, double salario, String contrasena, String cedulaJefe) 
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
        jLabelUniversidad = new javax.swing.JLabel();
        jTextFieldUniversidad = new javax.swing.JTextField();
        jLabelEspecialidad = new javax.swing.JLabel();
        jTextFieldEspecialidad = new javax.swing.JTextField();
        jLabelNumeroLicencia = new javax.swing.JLabel();
        jTextFieldNumeroLicencia = new javax.swing.JTextField();

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
        jLabelTitulo.setText("Registrar Medico");
        jPanelTitulo.add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, 40));

        jLabelimgTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/JDialog/medico_38px.png"))); // NOI18N
        jPanelTitulo.add(jLabelimgTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 40, 60));

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
        jLabelRegistrar.setText("Registar");
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
        jPanelPpal.add(jLabelCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jTextFieldCedula.setEditable(false);
        jTextFieldCedula.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldCedula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCedulaKeyTyped(evt);
            }
        });
        jPanelPpal.add(jTextFieldCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 180, 30));

        jLabelUniversidad.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelUniversidad.setForeground(new java.awt.Color(51, 152, 219));
        jLabelUniversidad.setText("Universidad");
        jPanelPpal.add(jLabelUniversidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, -1, -1));

        jTextFieldUniversidad.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldUniversidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldUniversidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldUniversidadKeyTyped(evt);
            }
        });
        jPanelPpal.add(jTextFieldUniversidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 180, 30));

        jLabelEspecialidad.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelEspecialidad.setForeground(new java.awt.Color(51, 152, 219));
        jLabelEspecialidad.setText("Especialidad");
        jPanelPpal.add(jLabelEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        jTextFieldEspecialidad.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldEspecialidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldEspecialidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldEspecialidadKeyTyped(evt);
            }
        });
        jPanelPpal.add(jTextFieldEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 180, 30));

        jLabelNumeroLicencia.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelNumeroLicencia.setForeground(new java.awt.Color(51, 152, 219));
        jLabelNumeroLicencia.setText("Numero de licencia");
        jPanelPpal.add(jLabelNumeroLicencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, -1, -1));

        jTextFieldNumeroLicencia.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldNumeroLicencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldNumeroLicencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNumeroLicenciaKeyTyped(evt);
            }
        });
        jPanelPpal.add(jTextFieldNumeroLicencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 180, 30));

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
                
        String especialidad, universidad,licencia;                
        
        especialidad = jTextFieldEspecialidad.getText();
        universidad = jTextFieldUniversidad.getText();
        licencia = jTextFieldNumeroLicencia.getText();
        String arregloValidar1[] = {especialidad,universidad,licencia};
                   
        if (validacionTotal.validarExcepciones(arregloValidar1, 2, 1, 0).equals(""))
        {
            if(controladora.existeEmpleadoPersona(cedula)==false)
            {
                if(controladora.guardarEmpleado(email, cargo, salario, cedula, nombre, direccion, telefono, area,contrasena,cedulaJefe))
                {
                    controladora.guardarMedico(especialidad, licencia, universidad, cedula);
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
                controladora.modificarMedico(especialidad, licencia, universidad, cedula);
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

    private void jTextFieldEspecialidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEspecialidadKeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldEspecialidad, 10, 0);
    }//GEN-LAST:event_jTextFieldEspecialidadKeyTyped

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        jTextFieldCedula.setText(cedula);
    }//GEN-LAST:event_formWindowOpened

    private void jTextFieldUniversidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldUniversidadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUniversidadKeyTyped

    private void jTextFieldNumeroLicenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNumeroLicenciaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNumeroLicenciaKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelCancelar;
    private javax.swing.JLabel jLabelCedula;
    private javax.swing.JLabel jLabelEspecialidad;
    private javax.swing.JLabel jLabelNumeroLicencia;
    private javax.swing.JLabel jLabelRegistrar;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelUniversidad;
    private javax.swing.JLabel jLabelimgTitulo;
    private javax.swing.JPanel jPanelCancelar;
    private javax.swing.JPanel jPanelPpal;
    private javax.swing.JPanel jPanelRegistrar;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JTextField jTextFieldCedula;
    private javax.swing.JTextField jTextFieldEspecialidad;
    private javax.swing.JTextField jTextFieldNumeroLicencia;
    private javax.swing.JTextField jTextFieldUniversidad;
    private rojerusan.RSPopuMenu rSPopuMenu1;
    // End of variables declaration//GEN-END:variables

}
