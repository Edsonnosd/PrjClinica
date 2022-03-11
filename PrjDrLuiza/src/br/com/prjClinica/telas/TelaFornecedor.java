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
                    
        String sql = "select id as ID, nome as Nome , cnpj as CNPJ from tbfornecedor where nome like ?";
               
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtFornComp = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtFornNum = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblForn = new javax.swing.JTable();
        cboFornEstado = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFornEmail = new javax.swing.JTextField();
        lblFornId = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFornCidade = new javax.swing.JTextField();
        txtFornNome = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtFornEnd = new javax.swing.JTextField();
        txtFornCep = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtFornCnpj = new javax.swing.JFormattedTextField();
        txtFornFone1 = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtFornFone2 = new javax.swing.JFormattedTextField();
        txtFornCelular = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        txtFornBairro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtFornCons = new javax.swing.JTextField();
        btnFornCreate = new javax.swing.JButton();
        btnFornUpdate = new javax.swing.JButton();
        btnFornDelete = new javax.swing.JButton();
        btnFornNovo = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Fornecedor");
        setPreferredSize(new java.awt.Dimension(1025, 648));

        txtFornComp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Número:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("* Campos obrigatórios!!");

        txtFornNum.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

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

        cboFornEstado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cboFornEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", "DF" }));
        cboFornEstado.setToolTipText("Estado");
        cboFornEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("E-mail:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("ID Fornecedor:");

        txtFornEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblFornId.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("*Cidade:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("*Nome:");

        txtFornCidade.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtFornNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("CEP:");

        txtFornEnd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        try {
            txtFornCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##### - ###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFornCep.setText("");
        txtFornCep.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("*Endereço:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("CNPJ:");

        try {
            txtFornCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/#### -##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFornCnpj.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        try {
            txtFornFone1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ##### - ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFornFone1.setText("");
        txtFornFone1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Telefone 01:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Telefone 02:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Celular:");

        try {
            txtFornFone2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ##### - ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFornFone2.setText("   ");
        txtFornFone2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        try {
            txtFornCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ##### - ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFornCelular.setText("");
        txtFornCelular.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Bairro:");

        txtFornBairro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Complemento:");

        txtFornCons.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFornCons.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFornConsKeyReleased(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 614, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblFornId, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel14))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtFornCnpj, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                            .addComponent(txtFornFone1)
                                            .addComponent(txtFornFone2)
                                            .addComponent(txtFornCelular)
                                            .addComponent(txtFornCep)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtFornEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtFornBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(txtFornCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cboFornEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(txtFornComp, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel11)))
                                            .addGap(18, 18, 18)
                                            .addComponent(txtFornNum, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtFornEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 321, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtFornNome, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(321, 321, 321)))
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
                    .addComponent(txtFornCons))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(43, 43, 43))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFornNovo)
                .addGap(81, 81, 81)
                .addComponent(btnFornCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(btnFornUpdate)
                .addGap(89, 89, 89)
                .addComponent(btnFornDelete)
                .addGap(102, 102, 102))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnFornCreate, btnFornDelete, btnFornNovo, btnFornUpdate});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtFornCons, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFornId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFornNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtFornCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFornEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtFornFone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtFornBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtFornFone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtFornComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFornNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel8)
                    .addComponent(txtFornCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtFornCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboFornEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtFornCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtFornEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFornCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFornUpdate)
                    .addComponent(btnFornDelete)
                    .addComponent(btnFornNovo))
                .addGap(266, 266, 266))
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
