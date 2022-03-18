/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prjClinica.telas;

import java.sql.*;
import br.com.prjClinica.dal.ModuloConexao;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author edson
 */
public class TelaListaFone extends javax.swing.JInternalFrame {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    /**
     * Creates new form TelaConsUsu
     */
    public TelaListaFone() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
     // consulta por nome
    private void pesquisarListaPac(){
                    
        String sql = "select cod_paciente as Código, nome as Nome, fone1 as Fone_01, fone2 as Fone_02, celular as Celular, fax as Fax from (select * from tbpaciente where nome like ?)tbpaciente order by nome";
               
        try {
            pst = conexao.prepareStatement(sql);
            //filtra a pesquisa no bando
            pst.setString(1, txtListaConsPac.getText() + "%");
            rs = pst.executeQuery();
             
            if(txtListaConsPac.getText().isEmpty()){
               ((DefaultTableModel) tblListaPac.getModel()).setRowCount(0); 
            }else{
            //usa bibliote rs2xml;jar
            tblListaPac.setModel(DbUtils.resultSetToTableModel(rs));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
       
    public void setar_camposLista() {
                        
        int setar = tblListaPac.getSelectedRow(); 
        String escol = tblListaPac.getModel().getValueAt(setar, 0).toString();
        //System.out.println(escol);
        
        String sql = "select * from tbpaciente where cod_paciente=?";
        
        try {
            pst = conexao.prepareStatement(sql);
            //filtra a pesquisa no bando
            pst.setString(1, escol);            
            rs = pst.executeQuery();
                                          
            if (rs.next()){
                
                txtListaCod.setText(rs.getString(2));
                txtListaNome.setText(rs.getString(3));
                                      
            }    
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void limpar(){
        
            txtListaConsPac.setText(null);
            ((DefaultTableModel) tblListaPac.getModel()).setRowCount(0); 
            txtListaCod.setText(null);
            txtListaNome.setText(null);
                        
    }
    
    private void imprimirListaSelec(){
        //classe para criar filtro
        
        HashMap filtro = new HashMap();
        filtro.put("listaFone", Integer.parseInt(txtListaCod.getText()));
                
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão deste relatório?", "Atenção!!", JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
            //imprimi o relatorio
            try {
                JasperPrint print = JasperFillManager.fillReport("\\ProjetoDrLuiza\\PrjDrLuiza\\src\\reports\\clinicaListaFone.jasper", filtro, conexao);
                
            //exibe realtorio pela classe jasperViewer
            JasperViewer.viewReport(print, false);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
    }
      
    private void imprimirLista(){
        //classe para criar filtro
        
        HashMap filtro = new HashMap();
        filtro.put("listaFoneLetra", txtListaConsPac.getText());
                
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão deste relatório?", "Atenção!!", JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
            //imprimi o relatorio
            try {
                JasperPrint print = JasperFillManager.fillReport("\\ProjetoDrLuiza\\PrjDrLuiza\\src\\reports\\listaFoneLetra.jasper", filtro, conexao);
                
            //exibe realtorio pela classe jasperViewer
            JasperViewer.viewReport(print, false);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtListaNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtListaCod = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnListaPac = new javax.swing.JButton();
        btnListaPacLimpar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtListaConsPac = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaPac = new javax.swing.JTable();
        btnListaSelec = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Lista de Telefone dos Pacientes");
        setFont(new java.awt.Font("Agency FB", 1, 12)); // NOI18N
        setPreferredSize(new java.awt.Dimension(1025, 648));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Paciente Selecionado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txtListaNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nome:");

        txtListaCod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Código:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtListaCod, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtListaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtListaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtListaCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btnListaPac.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnListaPac.setText("Imprimir Seleção");
        btnListaPac.setToolTipText("Imprimir");
        btnListaPac.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnListaPac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaPacActionPerformed(evt);
            }
        });

        btnListaPacLimpar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnListaPacLimpar.setText("Limpar");
        btnListaPacLimpar.setToolTipText("Limpar");
        btnListaPacLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnListaPacLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaPacLimparActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Campo de pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txtListaConsPac.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtListaConsPac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtListaConsPacKeyReleased(evt);
            }
        });

        tblListaPac = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblListaPac.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblListaPac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblListaPac.setFocusable(false);
        tblListaPac.getTableHeader().setReorderingAllowed(false);
        tblListaPac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListaPacMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListaPac);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtListaConsPac)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtListaConsPac, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
        );

        btnListaSelec.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnListaSelec.setText("Imprimir Lista");
        btnListaSelec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaSelecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 183, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnListaPacLimpar)
                        .addGap(85, 85, 85)
                        .addComponent(btnListaSelec)
                        .addGap(74, 74, 74)
                        .addComponent(btnListaPac)
                        .addGap(50, 50, 50))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(164, 164, 164))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnListaPacLimpar, btnListaSelec});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnListaPacLimpar)
                    .addComponent(btnListaPac, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListaSelec))
                .addGap(22, 22, 22))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnListaPac, btnListaPacLimpar, btnListaSelec});

        setBounds(0, 0, 1025, 590);
    }// </editor-fold>//GEN-END:initComponents

    private void txtListaConsPacKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtListaConsPacKeyReleased
        // TODO add your handling code here:
        pesquisarListaPac();
    }//GEN-LAST:event_txtListaConsPacKeyReleased

    private void tblListaPacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaPacMouseClicked
        // TODO add your handling code here:
        setar_camposLista();
    }//GEN-LAST:event_tblListaPacMouseClicked

    private void btnListaPacLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaPacLimparActionPerformed
        // TODO add your handling code here:
        limpar();
    }//GEN-LAST:event_btnListaPacLimparActionPerformed

    private void btnListaPacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaPacActionPerformed
        // TODO add your handling code here:
        imprimirListaSelec();
    }//GEN-LAST:event_btnListaPacActionPerformed

    private void btnListaSelecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaSelecActionPerformed
        // TODO add your handling code here:
        imprimirLista();
    }//GEN-LAST:event_btnListaSelecActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnListaPac;
    private javax.swing.JButton btnListaPacLimpar;
    private javax.swing.JButton btnListaSelec;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListaPac;
    private javax.swing.JTextField txtListaCod;
    private javax.swing.JTextField txtListaConsPac;
    private javax.swing.JTextField txtListaNome;
    // End of variables declaration//GEN-END:variables

   
    
}
