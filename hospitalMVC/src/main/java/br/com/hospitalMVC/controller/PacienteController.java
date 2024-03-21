package br.com.hospitalMVC.controller;

import br.com.hospitalMVC.DAO.GenericDAO;
import br.com.hospitalMVC.DAO.PacienteDAOimpl;
import br.com.hospitalMVC.model.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteController {
    public PacienteController() {}

    public List<Paciente> listarTodos() {
        try {
            GenericDAO dao = new PacienteDAOimpl();
            List<Paciente> pacientes = new ArrayList<Paciente>();

            for(Object objeto:dao.listarTodos()) {
                pacientes.add((Paciente) objeto);
            }

            return pacientes;
        } catch (Exception e) {
            System.out.println("Problemas no controller ao listar todos os pacientes");
            e.printStackTrace();
            return null;
        }
    }

    public Paciente buscarPorId(Integer id) {
        try {
            GenericDAO dao = new PacienteDAOimpl();
            return (Paciente) dao.buscarPorId(id);
        } catch (Exception e) {
            System.out.println("Problemas no controller ao buscar paciente por id");
            e.printStackTrace();
            return null;
        }
    }

    public boolean cadastrar(Paciente paciente) {
        try {
            GenericDAO dao = new PacienteDAOimpl();
            dao.cadastrar(paciente);
            return true;
        } catch (Exception e) {
            System.out.println("Problemas no controller ao cadastrar paciente");
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Paciente paciente) {
        try {
            GenericDAO dao = new PacienteDAOimpl();
            dao.editar(paciente);
            return true;
        } catch (Exception e) {
            System.out.println("Problemas no controller ao editar paciente");
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(Integer id) {
        try {
            GenericDAO dao = new PacienteDAOimpl();
            dao.excluir(id);
            return true;
        } catch (Exception e) {
            System.out.println("Problemas no controller ao excluir paciente");
            e.printStackTrace();
            return false;
        }
    }
}
