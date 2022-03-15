/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prjClinica.telas;

import java.sql.*;
import br.com.prjClinica.dal.ModuloConexao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author edson
 */
public class TelaFornecedor extends javax.swing.JInternalFrame {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form TelaFornecedor1
     */
    public TelaFornecedor() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    private void adicionar_forn() {
                       
        String sql = "insert into tbFornecedor( nome, cnpj, email, fone1, fone2, celular, cep, endereco, num, compl, bairro, cidade, estado) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        
        try {
            pst = conexao.prepareStatement(sql);                           
            
            
            pst.setString(1, txtFornNome.getText());
            pst.setString(2, txtFornCnpj.getText());
            pst.setString(3, txtFornEmail.getText());
            pst.setString(4, txtFornFone1.getText());
            pst.setString(5, txtFornFone2.getText());
            pst.setString(6, txtFornCelular.getText());
            pst.setString(7, txtFornCep.getText());
            pst.setString(8, txtFornEnd.getText());
            pst.setString(9, txtFornNum.getText());
            pst.setString(10, txtFornComp.getText());
            pst.setString(11, txtFornBairro.getText());            
            pst.setString(12, txtFornCidade.getText());                                                                 
            pst.setString(13, cboFornEstado.getSelectedItem().toString());                                            
                            
            if (txtFornNome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
                txtFornNome.requestFocus();
            }else if(txtFornEnd.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
                txtFornEnd.requestFocus();
            }else if(txtFornCidade.getText().isEmpty()){                
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
                txtFornCidade.requestFocus();
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!!");
                   
                    limpar_forn();
                    
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
                
    }
    
     // consulta por nome
    private void pesquisar_forn(){
                    
        String sql = "select id as ID, nome as Nome , cnpj as CNPJ from (select * from tbfornecedor where nome like ?) tbfornecedor order by nome";
               
        try {
            pst = conexao.prepareStatement(sql);
            //filtra a pesquisa no bando
            pst.setString(1, txtFornCons.getText() + "%");
            rs = pst.executeQuery();
             
            if(txtFornCons.getText().isEmpty()){
               ((DefaultTableModel) tblForn.getModel()).setRowCount(0); 
            }else{
            //usa bibliote rs2xml;jar
            tblForn.setModel(DbUtils.resultSetToTableModel(rs));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void setar_campos() {
        
        //desabilita botão adicionar
        btnFornCreate.setEnabled(false);
        
        int setar = tblForn.getSelectedRow(); 
        String escol = tblForn.getModel().getValueAt(setar, 0).toString();
        //System.out.println(escol);
        
        String sql = "select * from tbfornecedor where id =?";
        
        try {
            pst = conexao.prepareStatement(sql);
            //filtra a pesquisa no bando
            pst.setString(1, escol);            
            rs = pst.executeQuery();
                                            
            if (rs.next()) {
                
                lblFornId.setText(rs.getString(1));              
                txtFornNome.setText(rs.getString(2));
                txtFornCnpj.setText(rs.getString(3));
                txtFornEmail.setText(rs.getString(4));
                txtFornFone1.setText(rs.getString(5));
                txtFornFone2.setText(rs.getString(6));
                txtFornCelular.setText(rs.getString(7));
                txtFornCep.setText(rs.getString(8));
                txtFornEnd.setText(rs.getString(9));
                txtFornNum.setText(rs.getString(10));
                txtFornComp.setText(rs.getString(11));
                txtFornBairro.setText(rs.getString(12));                
                txtFornCidade.setText(rs.getString(13));
                cboFornEstado.setSelectedItem(rs.getString(14));    
                
                btnFornUpdate.setEnabled(true);
                
            } else {
                
                limpar_forn();
            }  
                                                       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
                         
    }
    
    private void alterar_forn() {
        
        String sql = "update tbfornecedor set nome=?, cnpj=?, email=?, fone1=?, fone2=?, celular=?, cep=?, endereco=?, num=?, compl=?, bairro=?, cidade=?, estado=? where id=?";
        
        try {
            pst = conexao.prepareStatement(sql);
            
           
            pst.setString(1, txtFornNome.getText());
            pst.setString(2, txtFornCnpj.getText());
            pst.setString(3, txtFornEmail.getText());
            pst.setString(4, txtFornFone1.getText());
            pst.setString(5, txtFornFone2.getText());
            pst.setString(6, txtFornCelular.getText());
            pst.setString(7, txtFornCep.getText());
            pst.setString(8, txtFornEnd.getText());
            pst.setString(9, txtFornNum.getText());
            pst.setString(10, txtFornComp.getText());
            pst.setString(11, txtFornBairro.getText());            
            pst.setString(12, txtFornCidade.getText());
            pst.setString(13, cboFornEstado.getSelectedItem().toString());
            pst.setString(14, lblFornId.getText());

                            
            if(txtFornNome.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
                txtFornNome.requestFocus();
            }else if(txtFornEnd.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
                txtFornEnd.requestFocus();
            }else if(txtFornCidade.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
                txtFornCidade.requestFocus();
             
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do fornecedor alterados com sucesso!!");
                  
                    limpar_forn();
                   
                    //ativa botao adicionar
                    btnFornCreate.setEnabled(true);
                   
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void remover_forn(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este fornecedor??","Atenção", JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_NO_OPTION){
            String sql = "delete from tbfornecedor where id=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, lblFornId.getText());
                int apagado = pst.executeUpdate();
                
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Fornecedor Removido com Sucesso!!");
                    
                    limpar_forn();
                    
                    btnFornCreate.setVisible(true);
                   
                }
                                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
     // limpa os campos do formulario
    private void limpar_forn() {
        lblFornId.setText(null);        
        txtFornCons.setText(null);
        txtFornNome.setText(null);
        txtFornEnd.setText(null);
        txtFornBairro.setText(null);
        txtFornCep.setText(null);
        txtFornCidade.setText(null);
        txtFornFone1.setText(null);
        txtFornFone2.setText(null);
        txtFornNum.setText(null);
        txtFornCelular.setText(null);
        txtFornEmail.setText(null);
        txtFornCnpj.setText(null);
        txtFornComp.setText(null);       
        ((DefaultTableModel) tblForn.getModel()).setRowCount(0);
        
        txtFornNome.requestFocus();
    }
    
    private void novo_Forn(){
        
        btnFornCreate.setEnabled(true);        
        btnFornUpdate.setEnabled(false);
        txtFornNome.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnFornCreate = new javax.swing.JButton();
        btnFornUpdate = new javax.swing.JButton();
        btnFornDelete = new javax.swing.JButton();
        btnFornNovo = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblFornId = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtFornCons = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblForn = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtFornNome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtFornCnpj = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtFornEnd = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtFornBairro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtFornComp = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtFornCidade = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtFornNum = new javax.swing.JTextField();
        cboFornEstado = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtFornCep = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtFornFone1 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtFornFone2 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        txtFornCelular = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        txtFornEmail = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Fornecedor");
        setFont(new java.awt.Font("Agency FB", 1, 10)); // NOI18N
        setPreferredSize(new java.awt.Dimension(1025, 648));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("* Campos obrigatórios!!");

        btnFornCreate.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnFornCreate.setText("Adicionar");
        btnFornCreate.setToolTipText("Adicionar");
        btnFornCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFornCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFornCreateActionPerformed(evt);
            }
        });

        btnFornUpdate.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnFornUpdate.setText("Alterar");
        btnFornUpdate.setToolTipText("Alterar");
        btnFornUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFornUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFornUpdateActionPerformed(evt);
            }
        });

        btnFornDelete.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnFornDelete.setText("Remover");
        btnFornDelete.setToolTipText("Remover");
        btnFornDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFornDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFornDeleteActionPerformed(evt);
            }
        });

        btnFornNovo.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnFornNovo.setText("Novo");
        btnFornNovo.setToolTipText("Novo");
        btnFornNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFornNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFornNovoActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Código do Fornecedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("ID Fornecedor:");

        lblFornId.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFornId, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFornId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(0, 28, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Campo de Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txtFornCons.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFornCons.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFornConsKeyReleased(evt);
            }
        });

        tblForn = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblForn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblForn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblForn.setFocusable(false);
        tblForn.getTableHeader().setReorderingAllowed(false);
        tblForn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFornMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblForn);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(txtFornCons))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFornCons, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Dados do Fornecedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("*Nome:");

        txtFornNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("CNPJ:");

        try {
            txtFornCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/#### -##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFornCnpj.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtFornNome))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtFornCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFornNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtFornCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Endereço do Fornecedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("*Endereço:");

        txtFornEnd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Bairro:");

        txtFornBairro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Complemento:");

        txtFornComp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("*Cidade:");

        txtFornCidade.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Número:");

        txtFornNum.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cboFornEstado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cboFornEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", "DF" }));
        cboFornEstado.setToolTipText("Estado");
        cboFornEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("CEP:");

        try {
            txtFornCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##### - ###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFornCep.setText("");
        txtFornCep.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(txtFornCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                                .addComponent(cboFornEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(txtFornCep, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFornEnd, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                                    .addComponent(txtFornBairro))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtFornNum, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtFornComp, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFornEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11)
                    .addComponent(txtFornNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFornBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel14)
                    .addComponent(txtFornCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFornComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboFornEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtFornCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Contatos do Fornecedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Telefone 01:");

        try {
            txtFornFone1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ##### - ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFornFone1.setText("");
        txtFornFone1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Telefone 02:");

        try {
            txtFornFone2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ##### - ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFornFone2.setText("   ");
        txtFornFone2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Celular:");

        try {
            txtFornCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ##### - ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFornCelular.setText("");
        txtFornCelular.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("E-mail:");

        txtFornEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFornFone2)
                            .addComponent(txtFornFone1)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtFornCelular, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                .addGap(2, 2, 2)))
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtFornEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtFornFone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtFornFone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtFornCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtFornEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFornNovo)
                .addGap(81, 81, 81)
                .addComponent(btnFornCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(btnFornUpdate)
                .addGap(89, 89, 89)
                .addComponent(btnFornDelete)
                .addGap(173, 173, 173))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnFornCreate, btnFornDelete, btnFornNovo, btnFornUpdate});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFornCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFornUpdate)
                    .addComponent(btnFornDelete)
                    .addComponent(btnFornNovo))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnFornCreate, btnFornDelete, btnFornNovo, btnFornUpdate});

        setBounds(0, 0, 1025, 648);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFornCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFornCreateActionPerformed
        // TODO add your handling code here:
        adicionar_forn();
    }//GEN-LAST:event_btnFornCreateActionPerformed

    private void btnFornUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFornUpdateActionPerformed
        // TODO add your handling code here:
        alterar_forn();
    }//GEN-LAST:event_btnFornUpdateActionPerformed

    private void btnFornDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFornDeleteActionPerformed
        // TODO add your handling code here:
        remover_forn();
    }//GEN-LAST:event_btnFornDeleteActionPerformed

    private void tblFornMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFornMouseClicked
        // TODO add your handling code here:
        setar_campos();
    }//GEN-LAST:event_tblFornMouseClicked

    private void txtFornConsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFornConsKeyReleased
        // TODO add your handling code here:
        pesquisar_forn();
    }//GEN-LAST:event_txtFornConsKeyReleased

    private void btnFornNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFornNovoActionPerformed
        // TODO add your handling code here:
        limpar_forn(); 
        novo_Forn();
    }//GEN-LAST:event_btnFornNovoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFornCreate;
    private javax.swing.JButton btnFornDelete;
    private javax.swing.JButton btnFornNovo;
    private javax.swing.JButton btnFornUpdate;
    private javax.swing.JComboBox<String> cboFornEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFornId;
    private javax.swing.JTable tblForn;
    private javax.swing.JTextField txtFornBairro;
    private javax.swing.JFormattedTextField txtFornCelular;
    private javax.swing.JFormattedTextField txtFornCep;
    private javax.swing.JTextField txtFornCidade;
    private javax.swing.JFormattedTextField txtFornCnpj;
    private javax.swing.JTextField txtFornComp;
    private javax.swing.JTextField txtFornCons;
    private javax.swing.JTextField txtFornEmail;
    private javax.swing.JTextField txtFornEnd;
    private javax.swing.JFormattedTextField txtFornFone1;
    private javax.swing.JFormattedTextField txtFornFone2;
    private javax.swing.JTextField txtFornNome;
    private javax.swing.JTextField txtFornNum;
    // End of variables declaration//GEN-END:variables
}
