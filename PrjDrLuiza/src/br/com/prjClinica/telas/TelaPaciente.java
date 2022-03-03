/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prjClinica.telas;

/**
 *
 * @author edson
 */
import java.sql.*;
import br.com.prjClinica.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaPaciente extends javax.swing.JInternalFrame {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    /**
     * Creates new form TelaPaciente
     */
    public TelaPaciente() {
        initComponents();
        conexao = ModuloConexao.conector();
  
    }
       
     private void adicionar() {
                         
        String sql = "insert into tbPaciente(nome,endereco,bairro,cep,cidade,estado,fone1,fone2,fax,celular,email,cpf,rg,dt_cadastro,obs,nome_resp,dt_nasc) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        
        try {
            pst = conexao.prepareStatement(sql);                           
            //pst.setString(1, txtPacId.getText());
            //pst.setString(2, txtPacCodigo.getText());
            pst.setString(1, txtPacNome.getText());
            pst.setString(2, txtPacEndereco.getText());
            pst.setString(3, txtPacBairro.getText());
            pst.setString(4, txtPacCep.getText());
            pst.setString(5, txtPacCidade.getText());
            pst.setString(6, cboPacEstado.getSelectedItem().toString());
            pst.setString(7, txtPacFone1.getText());
            pst.setString(8, txtPacFone2.getText());
            pst.setString(9, txtPacFax.getText());
            pst.setString(10, txtPacCelular.getText());
            pst.setString(11, txtPacEmail.getText());
            pst.setString(12, txtPacCpf.getText());
            pst.setString(13, txtPacRg.getText());
            pst.setString(14, txtPacDtCadas.getText());
            pst.setString(15, txtPacObs.getText());
            pst.setString(16, txtPacResp.getText());
            pst.setString(17, txtPacDtNasc.getText());
                            
            if ((txtPacNome.getText().isEmpty()) || (txtPacFone1.getText().isEmpty()) || (txtPacResp.getText().isEmpty()) || (txtPacEndereco.getText().isEmpty()) || (txtPacCidade.getText().isEmpty()) ||  (txtPacDtNasc.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!!");
                    //txtPacId.setText(null);
                    //txtPacCodigo.setText(null);
                    txtPacNome.setText(null);
                    txtPacEndereco.setText(null);
                    txtPacBairro.setText(null);
                    txtPacCep.requestFocus();
                    txtPacCidade.setText(null);
                    txtPacFone1.setText(null);
                    txtPacFone2.setText(null);
                    txtPacFax.setText(null);
                    txtPacCelular.setText(null);
                    txtPacEmail.setText(null);
                    txtPacCpf.requestFocus();
                    txtPacRg.setText(null);
                    txtPacDtCadas.setText(null);
                    txtPacObs.setText(null);
                    txtPacResp.setText(null);
                    txtPacDtNasc.setText(null);
                    txtPacNome.requestFocus();
                    
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
                
    }
     // consulta por nome
    private void pesquisar_paciente(){
                    
        String sql = "select id_paciente, cod_paciente, nome from tbpaciente where nome like ?";
               
        try {
            pst = conexao.prepareStatement(sql);
            //filtra a pesquisa no bando
            pst.setString(1, txtPacCons.getText() + "%");
            rs = pst.executeQuery();
                    
            //usa bibliote rs2xml;jar
            tblPac.setModel(DbUtils.resultSetToTableModel(rs));
            
                                   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
   //quando clicar no nome, carrega setar e concatena na string sql para busca e preenchimento dos campos
    //metodo para setar os campos da tabela
    public void setar_campos() {
        int setar = tblPac.getSelectedRow(); 
        String escol = tblPac.getModel().getValueAt(setar, 0).toString();
        //System.out.println(escol);
        
        String sql = "select * from tbpaciente where id_paciente =?";
        
        try {
            pst = conexao.prepareStatement(sql);
            //filtra a pesquisa no bando
            pst.setString(1, escol);
            System.out.println(pst);
            rs = pst.executeQuery();
                                            
            if (rs.next()) {
                txtPacId.setText(rs.getString(1));
                txtPacCodigo.setText(rs.getString(2));
                txtPacNome.setText(rs.getString(3));
                txtPacEndereco.setText(rs.getString(4));
                txtPacBairro.setText(rs.getString(5));
                txtPacCep.setText(rs.getString(6));
                txtPacCidade.setText(rs.getString(7));
                cboPacEstado.setSelectedItem(rs.getString(8));
                txtPacFone1.setText(rs.getString(9));
                txtPacFone2.setText(rs.getString(10));
                txtPacFax.setText(rs.getString(11));
                txtPacCelular.setText(rs.getString(12));
                txtPacEmail.setText(rs.getString(13));
                txtPacCpf.setText(rs.getString(14));
                txtPacRg.setText(rs.getString(15));
                txtPacDtCadas.setText(rs.getString(16));
                txtPacObs.setText(rs.getString(17));
                txtPacResp.setText(rs.getString(18));
                txtPacDtNasc.setText(rs.getString(19));
            } else {
                txtPacId.setText("");
                txtPacCodigo.setText("");
                txtPacNome.setText("");
                txtPacEndereco.setText("");
                txtPacBairro.setText("");
                txtPacCep.setText("");
                txtPacCidade.setText("");             
                txtPacFone1.setText("");
                txtPacFone2.setText("");
                txtPacFax.setText("");
                txtPacCelular.setText("");
                txtPacEmail.setText("");
                txtPacCpf.setText("");
                txtPacRg.setText("");
                txtPacDtCadas.setText("");
                txtPacObs.setText("");
                txtPacResp.setText("");
                txtPacDtNasc.setText("");
            }  
                                                       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtPacCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPacNome = new javax.swing.JTextField();
        txtPacEndereco = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPacBairro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPacFone1 = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPacFone2 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPacCelular = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPacCep = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPacCidade = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cboPacEstado = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtPacCpf = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtPacRg = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPacFax = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtPacDtNasc = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        txtPacResp = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtPacDtCadas = new javax.swing.JFormattedTextField();
        btnPacCreate = new javax.swing.JButton();
        btnPacUpdate = new javax.swing.JButton();
        btnPacDelete = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtPacObs = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPac = new javax.swing.JTable();
        txtPacCons = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        txtPacId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtPacEmail = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro Paciente");
        setPreferredSize(new java.awt.Dimension(1025, 648));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Código Paciente:");

        txtPacCodigo.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtPacCodigo.setForeground(new java.awt.Color(204, 0, 0));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("* Nome Paciente:");

        txtPacNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtPacEndereco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("* Endereço:");

        txtPacBairro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("* Telefone 01:");

        try {
            txtPacFone1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText(" Telefone 02:");

        try {
            txtPacFone2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Celular:");

        try {
            txtPacCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("CEP:");

        try {
            txtPacCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##### - ###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("* Cidade:");

        txtPacCidade.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Estado:");

        cboPacEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", "DF" }));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("CPF / CNPJ:");

        txtPacCpf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("RG / Insc. Est.:");

        txtPacRg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Fax:");

        txtPacFax.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("* Dt. Nascimento:");

        try {
            txtPacDtNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("* Nome Responsável:");

        txtPacResp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Data Cadastro:");

        try {
            txtPacDtCadas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnPacCreate.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnPacCreate.setText("Adicionar");
        btnPacCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacCreateActionPerformed(evt);
            }
        });

        btnPacUpdate.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnPacUpdate.setText("Alterar");
        btnPacUpdate.setToolTipText("");

        btnPacDelete.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnPacDelete.setText("Remover");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Observação:");

        txtPacObs.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 0, 0));
        jLabel20.setText("* Campos obrigatórios");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Bairro:");

        tblPac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblPac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPacMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblPac);

        txtPacCons.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtPacCons.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPacConsKeyReleased(evt);
            }
        });

        btnConsultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultar.setText("Consultar");
        btnConsultar.setToolTipText("Consultar");
        btnConsultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtPacId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("ID Paciente:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("E-mail:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(txtPacObs))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPacCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPacDtCadas, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPacCep, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(120, 120, 120)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel9))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPacCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPacRg, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(104, 104, 104)
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPacDtNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(65, 65, 65)
                                        .addComponent(jLabel22)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPacEmail))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPacEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(123, 123, 123)
                                        .addComponent(jLabel7))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPacBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(258, 258, 258)
                                        .addComponent(jLabel13))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel15))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel1)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(txtPacResp, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtPacNome, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(80, 80, 80)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel4))))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPacFax)
                                    .addComponent(txtPacCelular)
                                    .addComponent(txtPacFone2, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                    .addComponent(txtPacFone1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPacId, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPacCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(700, 700, 700)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(cboPacEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPacCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(btnPacUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btnPacDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(txtPacCons, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(btnConsultar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel20))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPacCons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPacId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtPacCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPacNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtPacFone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtPacResp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtPacFone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtPacCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPacEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPacBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel13)
                    .addComponent(txtPacFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPacCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPacCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(cboPacEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel11)
                    .addComponent(txtPacCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtPacRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtPacDtCadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtPacDtNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(txtPacEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtPacObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPacCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        setBounds(0, 0, 1025, 648);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPacCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacCreateActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_btnPacCreateActionPerformed

    private void txtPacConsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPacConsKeyReleased
        // chamar pesquisa em tempo real
        pesquisar_paciente();
    }//GEN-LAST:event_txtPacConsKeyReleased

    private void tblPacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPacMouseClicked
        // clique que seleciona o nome na tabela
        setar_campos();
        System.out.println(tblPac);
    }//GEN-LAST:event_tblPacMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnPacCreate;
    private javax.swing.JButton btnPacDelete;
    private javax.swing.JButton btnPacUpdate;
    private javax.swing.JComboBox<String> cboPacEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblPac;
    private javax.swing.JTextField txtPacBairro;
    private javax.swing.JFormattedTextField txtPacCelular;
    private javax.swing.JFormattedTextField txtPacCep;
    private javax.swing.JTextField txtPacCidade;
    private javax.swing.JTextField txtPacCodigo;
    public static javax.swing.JTextField txtPacCons;
    private javax.swing.JTextField txtPacCpf;
    public static javax.swing.JFormattedTextField txtPacDtCadas;
    private javax.swing.JFormattedTextField txtPacDtNasc;
    private javax.swing.JTextField txtPacEmail;
    private javax.swing.JTextField txtPacEndereco;
    private javax.swing.JTextField txtPacFax;
    private javax.swing.JFormattedTextField txtPacFone1;
    private javax.swing.JFormattedTextField txtPacFone2;
    private javax.swing.JTextField txtPacId;
    public static javax.swing.JTextField txtPacNome;
    private javax.swing.JTextField txtPacObs;
    private javax.swing.JTextField txtPacResp;
    private javax.swing.JTextField txtPacRg;
    // End of variables declaration//GEN-END:variables
}
