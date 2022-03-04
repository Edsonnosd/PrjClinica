/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prjClinica.dal;

import java.awt.event.ActionEvent;
import java.sql.*;

/**
 *
 * @author edson
 */
public class ModuloConexao {
    //estabele conexao
    public static Connection conector(){
        Connection conexao = null;
        //chama driver
        String driver = "com.mysql.cj.jdbc.Driver";
        //armazenando informações do banco
        //String url = "Mysql@localhost:3306/dbClinica";
        String url = "jdbc:mysql://localhost:3306/dbClinica";
        String user = "root";
        String password = "edsonmysql";
        //estabelecendo a conexão com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            //linha que apoia a esclarecer o erro
            
            //System.out.println(e);
            return null;
        }
    }

    public void btnUsuCreate2ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    public void btnUsuCreate1ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }
}
