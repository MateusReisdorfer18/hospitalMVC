package br.com.hospitalMVC.controller;

import br.com.hospitalMVC.DAO.GenericDAO;
import br.com.hospitalMVC.DAO.MedicoDAOimpl;
import br.com.hospitalMVC.model.Medico;

import java.util.ArrayList;
import java.util.List;

public class MedicoController {
    public MedicoController() {}

    public List<Medico> listarTodos() {
        try {
            GenericDAO dao = new MedicoDAOimpl();
            List<Medico> medicos = new ArrayList<Medico>();

            for(Object objeto:dao.listarTodos()) {
                medicos.add((Medico) objeto);
            }

            return medicos;
        } catch(Exception e) {
            System.out.println("Problemas no controller ao listar todos os medicos");
            e.printStackTrace();
            return null;
        }
    }

    public Medico buscarPorId(Integer id) {
        try {
            GenericDAO dao = new MedicoDAOimpl();

            return (Medico) dao.buscarPorId(id);
        } catch(Exception e) {
            System.out.println("Problemas no controller ao buscar medico por id");
            e.printStackTrace();
            return null;
        }
    }

    public boolean cadastrar(Medico medico) {
        try {
            GenericDAO dao = new MedicoDAOimpl();
            dao.cadastrar(medico);
            return true;
        } catch (Exception e) {
            System.out.println("Problemas no controller ao cadastrar medico");
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Medico medico) {
        try {
            GenericDAO dao = new MedicoDAOimpl();
            dao.editar(medico);
            return true;
        } catch (Exception e) {
            System.out.println("Problemas no controller ao cadastrar medico");
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(Integer id) {
        try {
            GenericDAO dao = new MedicoDAOimpl();
            dao.excluir(id);
            return true;
        } catch (Exception e) {
            System.out.println("Problemas no controller ao excluir medico");
            e.printStackTrace();
            return false;
        }
    }
}
