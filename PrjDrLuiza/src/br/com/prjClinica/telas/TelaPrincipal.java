/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prjClinica.telas;


import java.awt.Graphics;
import java.awt.Image;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author edson
 */
public class TelaPrincipal extends javax.swing.JFrame {

    
    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageIcon icon = new ImageIcon(getClass().getResource("/br/com/prjClinica/icones/CLINICA-INFANTIL.png"));
        Image image = icon.getImage();
        desktop = new javax.swing.JDesktopPane(){

            public void paintComponent(Graphics g){
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }
        };
        jLabel2 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        menCad = new javax.swing.JMenu();
        menCadPaci = new javax.swing.JMenuItem();
        menCadForn = new javax.swing.JMenuItem();
        menCadUsu = new javax.swing.JMenuItem();
        menCons = new javax.swing.JMenu();
        menConsPaci = new javax.swing.JMenuItem();
        menConsForn = new javax.swing.JMenuItem();
        menConsUsu = new javax.swing.JMenuItem();
        menRel = new javax.swing.JMenu();
        menRelPaci = new javax.swing.JMenuItem();
        menRelForn = new javax.swing.JMenuItem();
        menOpc = new javax.swing.JMenu();
        menOpcSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Clinica Infantil Mangolin");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        desktop.setPreferredSize(new java.awt.Dimension(1000, 350));

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1025, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/prjClinica/icones/clinica1.jpg"))); // NOI18N

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblUsuario.setText("Usu??rio");

        lblData.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblData.setText("Data");

        menCad.setText("Cadastro    ");
        menCad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        menCadPaci.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        menCadPaci.setText("Paciente");
        menCadPaci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadPaciActionPerformed(evt);
            }
        });
        menCad.add(menCadPaci);

        menCadForn.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        menCadForn.setText("Fornecedor");
        menCad.add(menCadForn);

        menCadUsu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        menCadUsu.setText("Usu??rio");
        menCadUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadUsuActionPerformed(evt);
            }
        });
        menCad.add(menCadUsu);

        Menu.add(menCad);

        menCons.setText("Consulta  ");
        menCons.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        menConsPaci.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        menConsPaci.setText("Paciente");
        menCons.add(menConsPaci);

        menConsForn.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        menConsForn.setText("Fornecedores");
        menCons.add(menConsForn);

        menConsUsu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        menConsUsu.setText("Usu??rio");
        menCons.add(menConsUsu);

        Menu.add(menCons);

        menRel.setText("Relat??rio   ");
        menRel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        menRelPaci.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        menRelPaci.setText("Pacientes");
        menRel.add(menRelPaci);

        menRelForn.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        menRelForn.setText("Fornecedores");
        menRel.add(menRelForn);

        Menu.add(menRel);

        menOpc.setText("Op????es");
        menOpc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        menOpcSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        menOpcSair.setText("Sair");
        menOpcSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menOpcSairActionPerformed(evt);
            }
        });
        menOpc.add(menOpcSair);

        Menu.add(menOpc);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(lblData)
                    .addComponent(lblUsuario))
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addGap(31, 31, 31)
                .addComponent(lblUsuario)
                .addGap(55, 55, 55)
                .addComponent(lblData)
                .addContainerGap(363, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1250, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // as linhas abaixosubstituem a label lbldata pela data atual do sistema
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        lblData.setText(formatador.format(data));
    }//GEN-LAST:event_formWindowActivated

    private void menOpcSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menOpcSairActionPerformed
        // exibe  uma caixa de di??logo
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Aten????o!!", JOptionPane.YES_NO_OPTION);
        if(sair == JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_menOpcSairActionPerformed

    private void menCadUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadUsuActionPerformed
        // chama tela de cadastro usu??rio dentro do desktopPanel
        TelaUsuario usuario = new TelaUsuario();
        usuario.setVisible(true);
        desktop.add(usuario);
        usuario.txtUsuId.requestFocus();
                
    }//GEN-LAST:event_menCadUsuActionPerformed

    private void menCadPaciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadPaciActionPerformed
        // chama tela de cadastro paciente dentro do desktoPanel
        TelaPaciente paciente = new TelaPaciente();
        paciente.setVisible(true);
        desktop.add(paciente);
       // paciente.txtPacCons.requestFocus();
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        paciente.txtPacDtCadas.setText(formatador.format(data));  
        
    }//GEN-LAST:event_menCadPaciActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar Menu;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblData;
    public static javax.swing.JLabel lblUsuario;
    public static javax.swing.JMenu menCad;
    private javax.swing.JMenuItem menCadForn;
    private javax.swing.JMenuItem menCadPaci;
    private javax.swing.JMenuItem menCadUsu;
    public static javax.swing.JMenu menCons;
    private javax.swing.JMenuItem menConsForn;
    private javax.swing.JMenuItem menConsPaci;
    private javax.swing.JMenuItem menConsUsu;
    private javax.swing.JMenu menOpc;
    private javax.swing.JMenuItem menOpcSair;
    public static javax.swing.JMenu menRel;
    private javax.swing.JMenuItem menRelForn;
    private javax.swing.JMenuItem menRelPaci;
    // End of variables declaration//GEN-END:variables
}
