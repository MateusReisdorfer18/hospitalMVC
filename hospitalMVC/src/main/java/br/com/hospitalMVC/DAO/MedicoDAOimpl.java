package br.com.hospitalMVC.DAO;

import br.com.hospitalMVC.model.Medico;
import br.com.hospitalMVC.model.Paciente;
import br.com.hospitalMVC.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAOimpl implements GenericDAO{
    private Connection conn;

    public MedicoDAOimpl() {
        try {
            this.conn = ConnectionFactory.getConnection();
        } catch (Exception e) {
            System.out.println("Problemas na DAO ao conectar com o banco");
            e.printStackTrace();
        }
    }


    @Override
    public List<Object> listarTodos() {
        List<Object> lista = new ArrayList<Object>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM medico";

        try {
            stmt = this.conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while(rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medico.setEspecialidade(rs.getString("especialidade"));
                medico.setCrm(rs.getString("crm"));
                medico.setPlantao(rs.getBoolean("isPlantao"));
                lista.add(medico);
            }
        } catch (SQLException e) {
            System.out.println("Problemas na DAO ao listar medicos");
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

    public List<Paciente> listarPacientes(Integer id) {
        List<Paciente> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM paciente p WHERE medico = ?";

        try {
            stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while(rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setIdade(rs.getInt("idade"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setInternado(rs.getBoolean("isInternado"));

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
        Medico medico = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM medico WHERE id = ?";

        try {
            stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medico.setCrm(rs.getString("crm"));
                medico.setEspecialidade(rs.getString("especialidade"));
                medico.setPlantao(rs.getBoolean("isPlantao"));
            }
        } catch(SQLException e) {
            System.out.println("Problemas na DAO ao buscar medico por id");
            e.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(this.conn, stmt, rs);
            } catch (Exception e) {
                System.out.println("Problemas na DAO ao fechar conexao com o banco");
                e.printStackTrace();
            }
        }

        return medico;
    }

    @Override
    public boolean cadastrar(Object objeto) {
        Medico medico = null;
        PreparedStatement stmt = null;
        String query = "INSERT INTO medico (nome, especialidade, crm, isPlantao) VALUES (?, ?, ?, ?)";

        try {
            stmt = this.conn.prepareStatement(query);
            medico = (Medico) objeto;
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getEspecialidade());
            stmt.setString(3, medico.getCrm());
            stmt.setBoolean(4, medico.getPlantao());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Problemas na DAO ao cadastrar medico");
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

    public boolean vincularPaciente(Paciente paciente, Integer idMedico) {
        PreparedStatement stmt = null;
        String query = "UPDATE paciente SET medico = ? WHERE id = ?";

        try {
            stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, idMedico);
            stmt.setInt(2, paciente.getId());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Problemas na DAO ao vincular paciente");
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
        Medico medico = null;
        PreparedStatement stmt = null;
        String query = "UPDATE medico SET nome=?, especialidade=?, crm=?, isPlantao=? WHERE id=?";

        try {
            stmt = this.conn.prepareStatement(query);
            medico = (Medico) objeto;
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getEspecialidade());
            stmt.setString(3, medico.getCrm());
            stmt.setBoolean(4, medico.getPlantao());
            stmt.setInt(5, medico.getId());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Problemas na DAO ao editar medico");
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
        String query = "DELETE FROM medico WHERE id=?";

        try {
            stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Problemas na DAO ao excluir medico");
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

    public boolean desvincularPaciente(Paciente paciente) {
        PreparedStatement stmt = null;
        String query = "UPDATE paciente SET medico = null WHERE id = ?";

        try {
            stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, paciente.getId());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Problemas na DAO ao desvincular paciente");
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
}
