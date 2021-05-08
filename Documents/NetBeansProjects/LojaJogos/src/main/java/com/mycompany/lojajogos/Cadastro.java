package com.mycompany.lojajogos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Cadastro {

    private int codigo;
    private String login;
    private String senha;

    public Cadastro() {
    }

    public Cadastro(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public void salvar() {

        String sql = "INSERT INTO tb_cadastro (login, senha) VALUES (?, ?)";

        ConnectionFactory factory = new ConnectionFactory();

        try (Connection con = factory.obtemConexao()) {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, this.login);
            pst.setString(2, this.senha);

            pst.execute();

            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean verificaLogin() {

        String sql = "SELECT * FROM tb_cadastro where login = ? and senha = ?";

        ConnectionFactory factory = new ConnectionFactory();

        try (Connection con = factory.obtemConexao()) {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, this.login);
            pst.setString(2, this.senha);
            boolean resp = false;
            //Executar o comando e receber o resultado, armazenando num ResultSet
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Login realizado com sucesso",
                        "Loja de jogos", JOptionPane.INFORMATION_MESSAGE);
                resp = true;
                return resp;
            } else {
                JOptionPane.showMessageDialog(null, "Login ou senha incorretos",
                        "Loja de jogos", JOptionPane.WARNING_MESSAGE);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Conexao nao realizada");
        }
        return false;
    }
}
