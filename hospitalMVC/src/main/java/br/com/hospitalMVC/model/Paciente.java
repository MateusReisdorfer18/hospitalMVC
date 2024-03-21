package br.com.hospitalMVC.model;

public class Paciente {
    private Integer id;
    private String nome;
    private String cpf;
    private Boolean isInternado;
    private Integer idade;
    private Medico medico;

    public Paciente() {
    }

    public Paciente (Integer id, String nome, String cpf, Boolean isInternado, Integer idade, Medico medico) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.isInternado = isInternado;
        this.idade = idade;
        this.medico = medico;
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

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getInternado() {
        return this.isInternado;
    }

    public void setInternado(Boolean internado) {
        isInternado = internado;
    }

    public Integer getIdade() {
        return this.idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Medico getMedico() {
        return this.medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
