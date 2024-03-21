package br.com.hospitalMVC.DAO;

import java.util.List;

public interface GenericDAO {
    public List<Object> listarTodos();
    public Object buscarPorId(Integer id);
    public boolean cadastrar(Object objeto);
    public boolean editar(Object objeto);
    public void excluir(Integer id);
}
