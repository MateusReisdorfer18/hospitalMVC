package br.com.hospitalMVC.DAO;

import br.com.hospitalMVC.model.Paciente;
import br.com.hospitalMVC.util.ConnectionFactory;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOimpl implements GenericDAO{
    private Connection conn;

    public PacienteDAOimpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Object> listarTodos() {
        List<Object> lista = new ArrayList<Object>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM paciente";

        try {
            stmt = this.conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while(rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setIdade(rs.getInt("idade"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setInternado(rs.getBoolean("isInternado"));
                //paciente.setMedico
                lista.add(paciente);
            }
        } catch(SQLException e) {
            System.out.println("Problemas na DAO ao listar pacientes");
            e.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(this.conn, stmt, rs);
            } catch (Exception e) {
                System.out.println("Problemas na DAO ao fechar conexao com o banco");
                e.printStackTrace();
            }
        }


        return lista;
    }

    @Override
    public Object buscarPorId(Integer id) {
        Paciente paciente = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM paciente WHERE id=?";

        try {
            stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while(rs.next()) {
                paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setIdade(rs.getInt("idade"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setInternado(rs.getBoolean("isInternado"));
                //paciente.setMedico
            }
        } catch (SQLException e) {
            System.out.println("Problemas na DAO ao buscar paciente pelo id");
            e.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(this.conn, stmt, rs);
            } catch (Exception e) {
                System.out.println("Problemas na DAO ao fechar conexao com o banco");
                e.printStackTrace();
            }
        }

        return paciente;
    }

    @Override
    public boolean cadastrar(Object objeto) {
        return false;
    }

    @Override
    public boolean editar(Object objeto) {
        return false;
    }

    @Override
    public void excluir(Integer id) {

    }
}
