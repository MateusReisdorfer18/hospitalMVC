package br.com.hospitalMVC.model;

import java.util.List;

public class Medico {
    private Integer id;
    private String nome;
    private String especialidade;
    private String crm;
    private Boolean isPlantao;
    private List<Paciente> pacientes;

    public Medico() {
    }

    public Medico (String nome, String especialidade, String crm, Boolean isPlantao) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.crm = crm;
        this.isPlantao = isPlantao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return this.especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCrm() {
        return this.crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Boolean getPlantao() {
        return this.isPlantao;
    }

    public void setPlantao(Boolean plantao) {
        isPlantao = plantao;
    }

    public List<Paciente> getPacientes() {
        return this.pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
}
