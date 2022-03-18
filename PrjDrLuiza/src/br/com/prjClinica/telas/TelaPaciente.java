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
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.text.DateFormat;
import java.util.Date;

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
                       
        String sql = "insert into tbPaciente(cod_paciente, nome,endereco,bairro,cep,cidade,estado,fone1,fone2,fax,celular,email,cpf,rg,dt_cadastro,obs,nome_resp,dt_nasc) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        
        try {
            pst = conexao.prepareStatement(sql);                           
            
            if(txtPacCodigo.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
                txtPacCodigo.requestFocus();
            }else if(txtPacNome.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
                txtPacNome.requestFocus();            
            }else if(txtPacResp.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
                txtPacResp.requestFocus();
            }else if(txtPacDtNasc.getText().trim().length() < 10){                
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
                txtPacDtNasc.requestFocus();
            }else if(txtPacCidade.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
                txtPacCidade.requestFocus();
            }else if(txtPacFone1.getText().trim().length() < 17){
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
                txtPacFone1.requestFocus();
                
            } else {
                pst.setString(1, txtPacCodigo.getText());
                pst.setString(2, txtPacNome.getText());
                pst.setString(3, txtPacEndereco.getText());
                pst.setString(4, txtPacBairro.getText());
                pst.setString(5, txtPacCep.getText());
                pst.setString(6, txtPacCidade.getText());
                pst.setString(7, cboPacEstado.getSelectedItem().toString());
                pst.setString(8, txtPacFone1.getText());
                pst.setString(9, txtPacFone2.getText());
                pst.setString(10, txtPacFax.getText());
                pst.setString(11, txtPacCelular.getText());
                pst.setString(12, txtPacEmail.getText());
                pst.setString(13, txtPacCpf.getText());
                pst.setString(14, txtPacRg.getText());
                pst.setString(15, txtPacDtCadas.getText());
                pst.setString(16, txtPacObs.getText());
                pst.setString(17, txtPacResp.getText());
                pst.setString(18, txtPacDtNasc.getText());
                                
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!!");                   
                    limpar();
                    txtPacNome.requestFocus();
                    
                    btnPacUpdate.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
                
    }
     // consulta por nome
    private void pesquisar_paciente(){
                    
        String sql = "select nome as Nome from (select * from tbpaciente where nome like ?) tbpaciente order by nome";
               
        try {
            pst = conexao.prepareStatement(sql);
            //filtra a pesquisa no bando
            pst.setString(1, txtPacCons.getText() + "%");
            rs = pst.executeQuery();
             
            if(txtPacCons.getText().isEmpty()){
               ((DefaultTableModel) tblPac.getModel()).setRowCount(0); 
            }else{
            //usa bibliote rs2xml;jar
            tblPac.setModel(DbUtils.resultSetToTableModel(rs));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
   //quando clicar no nome, carrega setar e concatena na string sql para busca e preenchimento dos campos
    //metodo para setar os campos da tabela
    public void setar_campos() {
        
        //desabilita botão adicionar
        btnPacCreate.setEnabled(false);        
        btnPacUpdate.setEnabled(true);
        
        int setar = tblPac.getSelectedRow();         
        String escol = tblPac.getModel().getValueAt(setar, 0).toString();
                
        String sql = "select * from tbpaciente where nome =?";
        
        
        try {
            pst = conexao.prepareStatement(sql);
            //filtra a pesquisa no bando
            pst.setString(1, escol);            
            rs = pst.executeQuery();
                                            
            if (rs.next()) {                
                lblPacId.setText(rs.getString(1));
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
                
                limpar();
            }  
                                                       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
                         
    }
    
    
    private void alterar() {
        
        String sql = "update tbpaciente set cod_paciente=?, nome=?, endereco=?, bairro=?, cep=?, cidade=?, estado=?, fone1=?, fone2=?, fax=?, celular=?, email=?, cpf=?, rg=?, obs=?, nome_resp=?, dt_nasc=? where id_paciente=?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(18, lblPacId.getText());
            pst.setString(1, txtPacCodigo.getText());
            pst.setString(2, txtPacNome.getText());
            pst.setString(3, txtPacEndereco.getText());
            pst.setString(4, txtPacBairro.getText());
            pst.setString(5, txtPacCep.getText());
            pst.setString(6, txtPacCidade.getText());
            pst.setString(7, cboPacEstado.getSelectedItem().toString());
            pst.setString(8, txtPacFone1.getText());
            pst.setString(9, txtPacFone2.getText());
            pst.setString(10, txtPacFax.getText());
            pst.setString(11, txtPacCelular.getText());
            pst.setString(12, txtPacEmail.getText());
            pst.setString(13, txtPacCpf.getText());
            pst.setString(14, txtPacRg.getText());          
            pst.setString(15, txtPacObs.getText());
            pst.setString(16, txtPacResp.getText());
            pst.setString(17, txtPacDtNasc.getText());

            if(txtPacCodigo.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
                txtPacCodigo.requestFocus();               
            }else if(txtPacNome.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
                txtPacNome.requestFocus();
            }else if(txtPacResp.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
                txtPacResp.requestFocus();
            }else if(txtPacDtNasc.getText().trim().length() < 10){
                System.out.println(txtPacDtNasc);
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
                txtPacDtNasc.requestFocus();
            }else if(txtPacCidade.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
                txtPacCidade.requestFocus();
            }else if(txtPacFone1.getText().trim().length() < 17){
                JOptionPane.showMessageDialog(null, "Preencha todos o campos obrigatórios!!");
                txtPacFone1.requestFocus();
                
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do paciente alterados com sucesso!!");
                  
                    limpar();
                   
                    //ativa botao adicionar
                    btnPacCreate.setEnabled(true);
                   
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void remover(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este paciente??","Atenção", JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_NO_OPTION){
            String sql = "delete from tbpaciente where id_paciente=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, lblPacId.getText());
                int apagado = pst.executeUpdate();
                
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Paciente Removido com Sucesso!!");
                    
                    limpar();
                    
                    btnPacCreate.setVisible(true);
                   
                }
                                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
     
     // limpa os campos do formulario
    private void limpar() {
        lblPacId.setText(null);
        txtPacCodigo.setText(null);
        txtPacCons.setText(null);
        txtPacNome.setText(null);
        txtPacEndereco.setText(null);
        txtPacBairro.setText(null);
        txtPacCep.setText(null);
        txtPacCidade.setText(null);
        txtPacFone1.setText(null);
        txtPacFone2.setText(null);
        txtPacFax.setText(null);
        txtPacCelular.setText(null);
        txtPacEmail.setText(null);
        txtPacCpf.setText(null);
        txtPacRg.setText(null);
        txtPacDtCadas.setText(null);
        txtPacObs.setText(null);
        txtPacResp.setText(null);
        txtPacDtNasc.setText(null);
        ((DefaultTableModel) tblPac.getModel()).setRowCount(0);
    }
   
    private void codPaciente(){
        
        btnPacCreate.setEnabled(true);
        
        lblPacId.setText(null);
        txtPacCodigo.setText(null);
        txtPacCons.setText(null);
        txtPacNome.setText(null);
        txtPacEndereco.setText(null);
        txtPacBairro.setText(null);
        txtPacCep.setText(null);
        txtPacCidade.setText(null);
        txtPacFone1.setText(null);
        txtPacFone2.setText(null);
        txtPacFax.setText(null);
        txtPacCelular.setText(null);
        txtPacEmail.setText(null);
        txtPacCpf.setText(null);
        txtPacRg.setText(null);
        txtPacObs.setText(null);
        txtPacResp.setText(null);
        txtPacDtNasc.setText(null);
        ((DefaultTableModel) tblPac.getModel()).setRowCount(0);
       
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        txtPacDtCadas.setText(formatador.format(data)); 
        
        String sql = "select (max(cod_paciente)+1) from tbpaciente";
        
        try {

            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                txtPacCodigo.setText(rs.getString(1));
                txtPacNome.requestFocus();
                btnPacUpdate.setEnabled(false);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    private void ultimoCadastro(){
        
        String sql = "select * from tbpaciente where id_paciente = (select max(id_paciente)from tbpaciente)";
        
        try {
            pst = conexao.prepareStatement(sql);                                   
            rs = pst.executeQuery();
                                            
            if (rs.next()) {                
                lblPacId.setText(rs.getString(1));
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
                
                limpar();
            }  
                                                       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void minimoIdPac(){
        
        String sql = "select * from tbpaciente where id_paciente = (select min(id_paciente) from tbpaciente)";
        
        try {
            pst = conexao.prepareStatement(sql);                                   
            rs = pst.executeQuery();
                                            
            if (rs.next()) {                
                lblPacId.setText(rs.getString(1));
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
                
                limpar();
            }  
                                                       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void decremId(){
        
        String sql = "select * from tbpaciente where id_paciente = ?-1";
        
        try {
            pst = conexao.prepareStatement(sql); 
            pst.setString(1, lblPacId.getText());
            rs = pst.executeQuery();
                                            
            if (rs.next()) {                
                lblPacId.setText(rs.getString(1));
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
                
                limpar();
            }  
                                                       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void incremId(){
        
        String sql = "select * from tbpaciente where id_paciente = ?+1";
        
        try {
            pst = conexao.prepareStatement(sql); 
            pst.setString(1, lblPacId.getText());
            rs = pst.executeQuery();
                                            
            if (rs.next()) {                
                lblPacId.setText(rs.getString(1));
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
                
                limpar();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        btnPacCreate = new javax.swing.JButton();
        btnPacUpdate = new javax.swing.JButton();
        btnPacDelete = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtPacObs = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnPacNovo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblPacId = new javax.swing.JLabel();
        txtPacCodigo = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txtPacNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtPacResp = new javax.swing.JTextField();
        txtPacCpf = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        txtPacRg = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtPacDtCadas = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        txtPacDtNasc = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtPacFone1 = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPacFone2 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPacCelular = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPacFax = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtPacEmail = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        txtPacCons = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPac = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        txtPacCidade = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPacCep = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtPacBairro = new javax.swing.JTextField();
        txtPacEndereco = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cboPacEstado = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

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
        setTitle("Cadastro de Paciente");
        setFont(new java.awt.Font("Agency FB", 1, 10)); // NOI18N
        setPreferredSize(new java.awt.Dimension(1025, 648));

        btnPacCreate.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnPacCreate.setText("Adicionar");
        btnPacCreate.setToolTipText("Adicionar");
        btnPacCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPacCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacCreateActionPerformed(evt);
            }
        });

        btnPacUpdate.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnPacUpdate.setText("Alterar");
        btnPacUpdate.setToolTipText("");
        btnPacUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPacUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacUpdateActionPerformed(evt);
            }
        });

        btnPacDelete.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnPacDelete.setText("Remover");
        btnPacDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPacDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacDeleteActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Observação:");

        txtPacObs.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 0, 0));
        jLabel20.setText("* Campos obrigatórios");

        btnPacNovo.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnPacNovo.setText("Novo");
        btnPacNovo.setToolTipText("Novo");
        btnPacNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPacNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacNovoActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Códigos do Paciente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("*Código Paciente:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("ID Paciente:");

        lblPacId.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblPacId.setAlignmentY(0.0F);

        txtPacCodigo.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        txtPacCodigo.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(lblPacId, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtPacCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblPacId, txtPacCodigo});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPacId, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPacCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblPacId, txtPacCodigo});

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Dados do Paciente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txtPacNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("* Nome Paciente:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("* Nome Responsável:");

        txtPacResp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        try {
            txtPacCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.### - ##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtPacCpf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("CPF:");

        try {
            txtPacRg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.### - #")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtPacRg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("RG:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Data Cadastro:");

        try {
            txtPacDtCadas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtPacDtCadas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("* Dt. Nascimento:");

        try {
            txtPacDtNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtPacDtNasc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel15)
                    .addComponent(jLabel11)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPacNome)
                    .addComponent(txtPacResp)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtPacDtCadas, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtPacCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)))
                        .addGap(18, 18, 18)
                        .addComponent(txtPacRg, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(387, 387, 387)
                        .addComponent(txtPacDtNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPacNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPacResp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtPacCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPacRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtPacDtCadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtPacDtNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Contatos do Paciente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("*Telefone 01:");

        try {
            txtPacFone1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ##### - ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtPacFone1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText(" Telefone 02:");

        try {
            txtPacFone2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ##### - ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtPacFone2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Celular:");

        try {
            txtPacCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ##### - ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtPacCelular.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Fax:");

        txtPacFax.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("E-mail:");

        txtPacEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPacEmail))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(jLabel7))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addComponent(jLabel13)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPacCelular)
                                    .addComponent(txtPacFax)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPacFone1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                    .addComponent(txtPacFone2))))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPacFone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPacFone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPacCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPacFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPacEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Campo de Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txtPacCons.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPacCons.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPacConsKeyReleased(evt);
            }
        });

        tblPac.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblPac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblPac.setFocusable(false);
        tblPac.getTableHeader().setReorderingAllowed(false);
        tblPac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPacMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblPac);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                    .addComponent(txtPacCons))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(txtPacCons, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Endereço do Paciente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txtPacCidade.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("* Cidade:");

        try {
            txtPacCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##### - ###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtPacCep.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("CEP:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Bairro:");

        txtPacBairro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtPacEndereco.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Endereço:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Estado:");

        cboPacEstado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboPacEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SP", "AC", "AL", "AP", "AM", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SE", "TO", "DF" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel21)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtPacCep, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(139, 139, 139)
                                .addComponent(jLabel10))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtPacBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPacCidade)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(cboPacEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(txtPacEndereco))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPacEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPacBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel9)
                    .addComponent(txtPacCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPacCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cboPacEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(txtPacObs))
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(601, 601, 601)
                                .addComponent(jLabel18))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(141, 141, 141)
                                    .addComponent(jLabel20))
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(44, 44, 44)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108)
                        .addComponent(btnPacNovo)
                        .addGap(70, 70, 70)
                        .addComponent(btnPacCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(btnPacUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btnPacDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnPacCreate, btnPacDelete, btnPacNovo, btnPacUpdate});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel18)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtPacObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPacNovo)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnPacCreate, btnPacDelete, btnPacNovo, btnPacUpdate, jButton1});

        setBounds(0, 0, 1025, 648);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPacCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacCreateActionPerformed
        // TODO add your handling code here:
        adicionar();
        //btnPacUpdate.setEnabled(true);
    }//GEN-LAST:event_btnPacCreateActionPerformed

    private void txtPacConsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPacConsKeyReleased
        // chamar pesquisa em tempo real
        pesquisar_paciente();
    }//GEN-LAST:event_txtPacConsKeyReleased

    private void tblPacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPacMouseClicked
        // clique que seleciona o nome na tabela
        setar_campos();
        
    }//GEN-LAST:event_tblPacMouseClicked

    private void btnPacUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacUpdateActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btnPacUpdateActionPerformed

    private void btnPacDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacDeleteActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_btnPacDeleteActionPerformed

    private void btnPacNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacNovoActionPerformed
        // TODO add your handling code here:        
        codPaciente();
    }//GEN-LAST:event_btnPacNovoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ultimoCadastro();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        minimoIdPac();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        decremId();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        incremId();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPacCreate;
    private javax.swing.JButton btnPacDelete;
    private javax.swing.JButton btnPacNovo;
    private javax.swing.JButton btnPacUpdate;
    private javax.swing.JComboBox<String> cboPacEstado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblPacId;
    private javax.swing.JTable tblPac;
    private javax.swing.JTextField txtPacBairro;
    private javax.swing.JFormattedTextField txtPacCelular;
    private javax.swing.JFormattedTextField txtPacCep;
    private javax.swing.JTextField txtPacCidade;
    private javax.swing.JTextField txtPacCodigo;
    public static javax.swing.JTextField txtPacCons;
    private javax.swing.JFormattedTextField txtPacCpf;
    public static javax.swing.JFormattedTextField txtPacDtCadas;
    private javax.swing.JFormattedTextField txtPacDtNasc;
    private javax.swing.JTextField txtPacEmail;
    private javax.swing.JTextField txtPacEndereco;
    private javax.swing.JTextField txtPacFax;
    private javax.swing.JFormattedTextField txtPacFone1;
    private javax.swing.JFormattedTextField txtPacFone2;
    public static javax.swing.JTextField txtPacNome;
    private javax.swing.JTextField txtPacObs;
    private javax.swing.JTextField txtPacResp;
    private javax.swing.JFormattedTextField txtPacRg;
    // End of variables declaration//GEN-END:variables
}
