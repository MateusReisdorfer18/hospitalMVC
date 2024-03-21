package br.com.hospitalMVC.DAO;

import br.com.hospitalMVC.controller.MedicoController;
import br.com.hospitalMVC.model.Medico;
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
    private MedicoController medicoController = new MedicoController();

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
                Medico medico = medicoController.buscarPorId(rs.getInt("medico"));
                paciente.setMedico(medico);
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
                Medico medico = medicoController.buscarPorId(rs.getInt("medico"));
                paciente.setMedico(medico);
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
        Paciente paciente;
        PreparedStatement stmt = null;
        String query = "INSERT INTO paciente (nome, cpf, isInternado, idade, medico) VALUES (?, ?, ?, ?, ?)";

        try {
            stmt = this.conn.prepareStatement(query);
            paciente = (Paciente) objeto;
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setBoolean(3, paciente.getInternado());
            stmt.setInt(4, paciente.getIdade());
            stmt.setInt(5, paciente.getMedico().getId());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Problemas na DAO ao cadastrar paciente");
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(this.conn, stmt);
            } catch (Exception e) {
                System.out.println("Problemas na DAO ao fechar conexao com o banco");
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public boolean editar(Object objeto) {
        Paciente paciente = null;
        PreparedStatement stmt = null;
        String query = "UPDATE paciente SET nome=?, cpf=?, isInternado=?, idade=?, medico=? WHERE id=?";

        try {
            stmt = this.conn.prepareStatement(query);
            paciente = (Paciente) objeto;
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setBoolean(3, paciente.getInternado());
            stmt.setInt(4, paciente.getIdade());
            stmt.setInt(5, paciente.getMedico().getId());
            stmt.setInt(6, paciente.getId());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Problemas na DAO ao editar paciente");
            e.printStackTrace();
            return false;
        } finally {
            try {
               ConnectionFactory.closeConnection(this.conn, stmt);
            } catch (Exception e) {
                System.out.println("Problemas na DAO ao fechar conexao com o banco");
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public void excluir(Integer id) {
        PreparedStatement stmt = null;
        String query = "DELETE FROM paciente WHERE id=?";

        try {
            stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Problemas na DAO ao excluir paciente");
            e.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(this.conn, stmt);
            } catch (Exception e) {
                System.out.println("Problemas na DAO ao fechar conexao com o banco");
                e.printStackTrace();
            }
        }
    }
}
