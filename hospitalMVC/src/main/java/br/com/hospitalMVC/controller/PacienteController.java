package br.com.hospitalMVC.controller;

import br.com.hospitalMVC.DAO.GenericDAO;
import br.com.hospitalMVC.DAO.PacienteDAOimpl;
import br.com.hospitalMVC.model.Medico;
import br.com.hospitalMVC.model.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PacienteController {
    public PacienteController() {}

    public List<Paciente> listarTodos() {
        try {
            GenericDAO dao = new PacienteDAOimpl();
            List<Paciente> pacientes = new ArrayList<>();

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

    public void chamarMenu(Scanner scan, MedicoController medicoController) {
        int opcaoPaciente;
        System.out.println("""
                            Selecione a ação desejada \s
                            [1] Listar todos os Pacientes \s
                            [2] Buscar Paciente por id \s
                            [3] Cadastrar Paciente \s
                            [4] Editar Paciente \s
                            [5] Excluir Paciente \s
                            [6] Exibir dados do paciente \s
                            [7] Validar acompanhante \s
                            [0] Sair
                        """);
        opcaoPaciente = scan.nextInt();

        switch(opcaoPaciente) {
            case 1:
                this.menuListarTodos();

                break;
            case 2:
                this.menuBuscarPorId(scan);

                break;
            case 3:
                boolean returnCadastro = this.menuCadastrar(scan, medicoController);

                if(!returnCadastro) {
                    System.out.println("Houve um problema ao cadastrar paciente");
                    break;
                }

                System.out.println("Paciente cadastrado com sucesso");

                break;
            case 4:
                boolean returnEditar = this.menuEditar(scan, medicoController);

                if(!returnEditar) {
                    System.out.println("Problemas ao editar paciente");
                    break;
                }

                System.out.println("Paciente editado com sucesso");

                break;
            case 5:
                this.menuExcluir(scan);

                break;
            case 6:
                this.menuExibirDados(scan);

                break;
            case 7:
                boolean returnValidarAcao = this.menuValidarAcompanhante(scan);

                if(!returnValidarAcao) {
                    System.out.println("O paciente não é válido para ter um acompanhante");
                    break;
                }

                System.out.println("O paciente esta válido para ter um acompanhante");
                break;
            default:
                System.out.println("Saindo...");
                break;
        }
    }

    public void menuListarTodos() {
        System.out.println("Lista de pacientes");
        System.out.println("Id, " + "nome, " + "cpf, " + "médico, " + "internado");

        for(Paciente paciente:this.listarTodos()) {
            System.out.println(paciente.getId() + ", " + paciente.getNome() + ", " + paciente.getCpf() + ", " + (paciente.getMedico() == null ? "null" : paciente.getMedico().getNome()) + ", " + paciente.getInternado());
        }
    }

    public void menuBuscarPorId(Scanner scan) {
        int idPaciente;
        Paciente paciente;

        do {
            System.out.println("Digite o id do paciente");
            idPaciente = scan.nextInt();

            paciente = this.buscarPorId(idPaciente);

            if(paciente == null)
                System.out.printf("Paciente não encontrado com o id %d, digite novamente \n", idPaciente);
        } while(paciente == null);

        System.out.println("Paciente encontrado");
        System.out.println("Id, " + "nome, " + "cpf, " + "médico, " + "internado");
        System.out.println(paciente.getId() + ", " + paciente.getNome() + ", " + paciente.getCpf() + ", " + paciente.getMedico().getNome() + ", " + paciente.getInternado());
    }

    public boolean menuCadastrar(Scanner scan, MedicoController medicoController) {
        Paciente novoPaciente = new Paciente();
        Medico medicoPaciente;
        String pacienteNome;
        String pacienteCpf;
        int pacienteIdade;
        Integer idMedicoPaciente;
        boolean pacienteInternado = false;
        int opcaoInternado;
        boolean returnCadastrarPaciente;

        System.out.println("Digite o nome do paciente");
        pacienteNome = scan.next();

        System.out.println("Digite a idade do paciente");
        pacienteIdade = scan.nextInt();

        System.out.println("Digite o cpf do paciente");
        pacienteCpf = scan.next();

        System.out.println("O paciente esta internado? [1] True [2] False");
        opcaoInternado = scan.nextInt();

        if(opcaoInternado == 1)
            pacienteInternado = true;

        do {
            System.out.println("Id, Nome");
            for(Medico medico:medicoController.listarTodos()) {
                System.out.println(medico.getId() + ", " + medico.getNome());
            }

            System.out.println("Digite o id do médico do paciente");
            idMedicoPaciente = scan.nextInt();

            medicoPaciente = medicoController.buscarPorId(idMedicoPaciente);

            if(medicoPaciente == null)
                System.out.printf("Médico não encontrado com o id %d, digite novamento \n", idMedicoPaciente);
        } while(medicoPaciente == null);

        System.out.printf("Médico encontrado com id %d \n", idMedicoPaciente);

        novoPaciente.setNome(pacienteNome);
        novoPaciente.setIdade(pacienteIdade);
        novoPaciente.setCpf(pacienteCpf);
        novoPaciente.setMedico(medicoPaciente);
        novoPaciente.setInternado(pacienteInternado);

        returnCadastrarPaciente = this.cadastrar(novoPaciente);

        return returnCadastrarPaciente;
    }

    public boolean menuEditar(Scanner scan, MedicoController medicoController) {
        int idPacienteAlterar;
        Paciente pacienteAlterar;
        int opcaoAlterar;
        boolean returnPacienteAlterar;

        do {
            System.out.println("Id, " + "nome, " + "cpf, " + "médico, " + "internado");

            for(Paciente pacienteExcluir:this.listarTodos()) {
                System.out.println(pacienteExcluir.getId() + ", " + pacienteExcluir.getNome() + ", " + pacienteExcluir.getCpf() + ", " + pacienteExcluir.getMedico().getNome() + ", " + pacienteExcluir.getInternado());
            }

            System.out.println("Digite o id do paciente que deseja alterar");
            idPacienteAlterar = scan.nextInt();

            pacienteAlterar = this.buscarPorId(idPacienteAlterar);

            if(pacienteAlterar == null)
                System.out.printf("Paciente não encontrado com o id %d, digite novamento \n", idPacienteAlterar);
        } while(pacienteAlterar == null);

        System.out.println("Paciente encontrado");
        System.out.println("Id, " + "nome, " + "cpf, " + "médico, " + "internado");
        System.out.println(pacienteAlterar.getId() + ", " + pacienteAlterar.getNome() + ", " + pacienteAlterar.getCpf() + ", " + pacienteAlterar.getMedico().getNome() + ", " + pacienteAlterar.getInternado());

        System.out.println("""
                                Digite a opção da propriedade que gostaria de alterar \s
                                [1] Nome \s
                                [2] Cpf \s
                                [3] Médico \s
                                [4] Internado \s
                                """);
        opcaoAlterar = scan.nextInt();

        switch (opcaoAlterar) {
            case 1:
                String nome;

                System.out.println("Digite o novo nome");
                nome = scan.next();

                pacienteAlterar.setNome(nome);
                break;
            case 2:
                String cpf;

                System.out.println("Digite o novo cpf");
                cpf = scan.next();

                pacienteAlterar.setCpf(cpf);
                break;
            case 3:
                int idMedico;
                Medico novoMedico;

                do {
                    System.out.println("Id, Nome");
                    for(Medico medico:medicoController.listarTodos()) {
                        System.out.println(medico.getId() + ", " + medico.getNome());
                    }

                    System.out.println("Digite o id do novo médico");
                    idMedico = scan.nextInt();

                    novoMedico = medicoController.buscarPorId(idMedico);

                    if(novoMedico == null)
                        System.out.printf("Médico não encontrado com o id %d, digite novamente \n", idMedico);
                } while(novoMedico == null);

                pacienteAlterar.setMedico(novoMedico);
                break;
            case 4:
                boolean internado = false;
                int internadoOpcao;

                System.out.println("O paciente está internado? [1] True [2] False");
                internadoOpcao = scan.nextInt();

                if(internadoOpcao == 1)
                    internado = true;

                pacienteAlterar.setInternado(internado);
                break;
            default:
                break;
        }

        returnPacienteAlterar = this.editar(pacienteAlterar);

        return returnPacienteAlterar;
    }

    public void menuExcluir(Scanner scan) {
        int idPacienteExcluir;
        boolean returnPacienteExcluir;

        do {
            System.out.println("Id, " + "nome, " + "cpf, " + "médico, " + "internado");

            for(Paciente pacienteExcluir:this.listarTodos()) {
                System.out.println(pacienteExcluir.getId() + ", " + pacienteExcluir.getNome() + ", " + pacienteExcluir.getCpf() + ", " + pacienteExcluir.getMedico().getNome() + ", " + pacienteExcluir.getInternado());
            }

            System.out.println("Digite o id do paciente que deseja excluir");
            idPacienteExcluir = scan.nextInt();

            returnPacienteExcluir = this.excluir(idPacienteExcluir);

            if(!returnPacienteExcluir)
                System.out.printf("Paciente não encontrado com o id %d, digite novamente \n", idPacienteExcluir);
        } while(!returnPacienteExcluir);

        System.out.println("Paciente excluido com sucesso");
    }

    public void menuExibirDados(Scanner scan) {
        int idPaciente;
        Paciente pacienteEncontrado;

        do {
            this.menuListarTodos();

            System.out.println("Digite o id do paciente");
            idPaciente = scan.nextInt();

            pacienteEncontrado = this.buscarPorId(idPaciente);

            if(pacienteEncontrado == null)
                System.out.printf("Paciente não encontrado com id %d, digite novamente \n", idPaciente);
        } while(pacienteEncontrado == null);

        System.out.printf("Paciente encontrado com o id %d! \n", idPaciente);

        this.exibirDados(pacienteEncontrado);
    }

    private boolean menuValidarAcompanhante(Scanner scan) {
        int idPaciente;
        Paciente paciente;

        do {
            this.menuListarTodos();

            System.out.println("Digite o id do paciente que deseja validar");
            idPaciente = scan.nextInt();

            paciente = this.buscarPorId(idPaciente);

            if(paciente == null)
                System.out.printf("Paciente não encontrado com o id %d, digite novamente \n", idPaciente);
        } while(paciente == null);

        return this.validarAcompanhante(paciente);
    }

    private void exibirDados(Paciente paciente) {
        System.out.printf("""
                    id: %d \s
                    idade: %d \s
                    nome: %s \s
                    cpf: %s \s
                    internado: %s \s
                    médico: %s
                """, paciente.getId(), paciente.getIdade(), paciente.getNome(), paciente.getCpf(), this.isInternado(paciente), paciente.getMedico().getNome());
    }

    private String isInternado(Paciente paciente) {
        if(paciente.getInternado())
            return "True";

        return "False";
    }

    private boolean validarAcompanhante(Paciente paciente) {
        return paciente.getIdade() > 60;
    }
}
