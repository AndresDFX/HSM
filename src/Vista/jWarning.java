package Vista;

import java.awt.Color;
import javax.swing.JPanel;

/**
 * [jWarning] Clase de interfaz GUI de ventana modal de dialogos de advertencia
 * @since 24/11/2017
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class jWarning extends javax.swing.JDialog {

    //Declaracion de variables de clase
    private String texto;
    
//=======================================================================================================
    /**
     * Constructor de la clase jWarning
     * @param parent Frame que llama a este jDialog
     * @param modal variable que determina si la ventana es modal o no
     * @param texto variable que almacena el mensaje a mostrar
     * @since jWarning.java
     */
    public jWarning(java.awt.Frame parent, boolean modal,String texto) 
    {
        super(parent, modal);
        initComponents();
        this.texto=texto;
        this.setLocationRelativeTo(null);
    }
    
//=======================================================================================================
    /**
     * Metodo para poner un color cuando el mouse este encima del panel
     * @param panel Panel que se pondra el color 
     */
    public void ponerColor(JPanel panel)
    {
        panel.setBackground(new Color(255,230,0));
    }

//=======================================================================================================
    /**
     * Metodo para poner un color cuando el mouse se quite del panel
     * @param panel Panel que se pondra el color 
     */
    public void repintarColor(JPanel panel)
    {
        panel.setBackground(new Color(255,200,0));
    }
    
//=======================================================================================================   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPpal = new javax.swing.JPanel();
        jLabelMensaje = new javax.swing.JLabel();
        jPanelTitulo = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelimgTitulo = new javax.swing.JLabel();
        jPanelAceptar = new javax.swing.JPanel();
        jLabelAceptar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanelPpal.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPpal.setMinimumSize(new java.awt.Dimension(860, 600));
        jPanelPpal.setPreferredSize(new java.awt.Dimension(854, 600));
        jPanelPpal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelMensaje.setBackground(new java.awt.Color(153, 153, 153));
        jLabelMensaje.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelMensaje.setForeground(new java.awt.Color(153, 153, 153));
        jLabelMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMensaje.setText("AA");
        jLabelMensaje.setToolTipText("");
        jPanelPpal.add(jLabelMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 360, 80));

        jPanelTitulo.setBackground(new java.awt.Color(255, 200, 0));
        jPanelTitulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitulo.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitulo.setText("Advertencia");
        jPanelTitulo.add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, 40));

        jLabelimgTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/JDialog/warning.png"))); // NOI18N
        jPanelTitulo.add(jLabelimgTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 40, 60));

        jPanelPpal.add(jPanelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 60));

        jPanelAceptar.setBackground(new java.awt.Color(255, 200, 0));
        jPanelAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelAceptarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelAceptarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelAceptarMousePressed(evt);
            }
        });
        jPanelAceptar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAceptar.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAceptar.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAceptar.setText("Aceptar");
        jPanelAceptar.add(jLabelAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 6, -1, 20));

        jPanelPpal.add(jPanelAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 130, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPpal, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPpal, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelAceptarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelAceptarMousePressed
        this.setVisible(false);
    }//GEN-LAST:event_jPanelAceptarMousePressed

    private void jPanelAceptarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelAceptarMouseExited
       repintarColor(jPanelAceptar);
    }//GEN-LAST:event_jPanelAceptarMouseExited

    private void jPanelAceptarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelAceptarMouseEntered
       ponerColor(jPanelAceptar);
    }//GEN-LAST:event_jPanelAceptarMouseEntered

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        jLabelMensaje.setText(texto);
    }//GEN-LAST:event_formWindowActivated

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelAceptar;
    public javax.swing.JLabel jLabelMensaje;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelimgTitulo;
    private javax.swing.JPanel jPanelAceptar;
    private javax.swing.JPanel jPanelPpal;
    private javax.swing.JPanel jPanelTitulo;
    // End of variables declaration//GEN-END:variables

}
