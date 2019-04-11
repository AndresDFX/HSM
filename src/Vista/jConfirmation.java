package Vista;

import java.awt.*;
import javax.swing.JPanel;

/**
 * [jConfirmation] Clase de interfaz GUI de ventana modal de dialogos de confirmacion
 * @since 24/11/2017
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class jConfirmation extends javax.swing.JDialog {

    //Declaracion de variables de clase
    private String texto;
    public static int opcion=2;
    
    
//=======================================================================================================
    /**
     * Constructor de la clase jConfirmacion
     * @param parent Frame que llama a este jDialog
     * @param modal variable que determina si la ventana es modal o no
     * @param texto variable que almacena el mensaje a mostrar
     * @since jConfirmacion.java
     */
    public jConfirmation(java.awt.Frame parent, boolean modal,String texto) 
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
        panel.setBackground(new Color(250,250,250));
    }

//=======================================================================================================
    /**
     * Metodo para poner un color cuando el mouse se quite del panel
     * @param panel Panel que se pondra el color 
     */
    public void repintarColor(JPanel panel)
    {
        panel.setBackground(new Color(0,204,0));
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
        jPanelSi = new javax.swing.JPanel();
        jLabelSi = new javax.swing.JLabel();
        jPanelNo = new javax.swing.JPanel();
        jLabelNo = new javax.swing.JLabel();

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

        jLabelMensaje.setBackground(new java.awt.Color(153, 153, 153));
        jLabelMensaje.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelMensaje.setForeground(new java.awt.Color(153, 153, 153));
        jLabelMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMensaje.setToolTipText("");
        jPanelPpal.add(jLabelMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 360, 70));

        jPanelTitulo.setBackground(new java.awt.Color(0, 204, 0));
        jPanelTitulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitulo.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitulo.setText("Confirmación");
        jPanelTitulo.add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, 40));

        jLabelimgTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/JDialog/confirmacion.png"))); // NOI18N
        jPanelTitulo.add(jLabelimgTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 40, 60));

        jPanelPpal.add(jPanelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 60));

        jPanelSi.setBackground(new java.awt.Color(0, 204, 0));
        jPanelSi.setForeground(new java.awt.Color(255, 255, 255));
        jPanelSi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelSiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelSiMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelSiMousePressed(evt);
            }
        });
        jPanelSi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelSi.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelSi.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSi.setText("Aceptar");
        jPanelSi.add(jLabelSi, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 6, 70, 20));

        jPanelPpal.add(jPanelSi, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 80, 30));

        jPanelNo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelNo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanelNo.setForeground(new java.awt.Color(255, 255, 255));
        jPanelNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelNoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelNoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelNoMousePressed(evt);
            }
        });
        jPanelNo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNo.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelNo.setForeground(new java.awt.Color(0, 204, 0));
        jLabelNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNo.setText("Declinar");
        jPanelNo.add(jLabelNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 30));

        jPanelPpal.add(jPanelNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 80, 30));

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

    private void jPanelSiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSiMousePressed
        opcion=1;
        this.setVisible(false);
    }//GEN-LAST:event_jPanelSiMousePressed

    private void jPanelSiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSiMouseExited
        repintarColor(jPanelSi);
    }//GEN-LAST:event_jPanelSiMouseExited

    private void jPanelSiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSiMouseEntered
        ponerColor2(jPanelSi);
    }//GEN-LAST:event_jPanelSiMouseEntered

    private void jPanelNoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelNoMouseEntered
        ponerColor(jPanelNo);
    }//GEN-LAST:event_jPanelNoMouseEntered

    private void jPanelNoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelNoMouseExited
        repintarColor2(jPanelNo);
    }//GEN-LAST:event_jPanelNoMouseExited

    private void jPanelNoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelNoMousePressed
        opcion=0;
        this.setVisible(false);
    }//GEN-LAST:event_jPanelNoMousePressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        jLabelMensaje.setText(texto);
    }//GEN-LAST:event_formWindowOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLabelMensaje;
    private javax.swing.JLabel jLabelNo;
    private javax.swing.JLabel jLabelSi;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelimgTitulo;
    private javax.swing.JPanel jPanelNo;
    private javax.swing.JPanel jPanelPpal;
    private javax.swing.JPanel jPanelSi;
    private javax.swing.JPanel jPanelTitulo;
    // End of variables declaration//GEN-END:variables

    private void repintarColor2(JPanel panel) {
        panel.setBackground(new Color(255,255,255));
    }

    private void ponerColor2(JPanel panel) {
        panel.setBackground(new Color(0,230,0));
    }

}
