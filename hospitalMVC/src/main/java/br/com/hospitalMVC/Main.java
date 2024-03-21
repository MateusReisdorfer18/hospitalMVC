package br.com.hospitalMVC;

import br.com.hospitalMVC.controller.MedicoController;
import br.com.hospitalMVC.controller.PacienteController;
import br.com.hospitalMVC.model.Medico;
import br.com.hospitalMVC.model.Paciente;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int opcao;

        MedicoController medicoController = new MedicoController();
        PacienteController pacienteController = new PacienteController();

        do {
            System.out.println("Selecione uma opção \n [1] Médico \n [2] Paciente \n [0] Sair do sistema");
            opcao = scan.nextInt();

            if(opcao == 1) {
                int opcaoMedico;

                System.out.println("""
                            Selecione a ação desejada \s
                            [1] Listar todos os Médicos \s
                            [2] Buscar Médico por id \s
                            [3] Cadastrar Médico \s
                            [4] Editar Médico \s
                            [5] Excluir Médico \s
                            [0] Sair
                        """);
                opcaoMedico = scan.nextInt();

                switch(opcaoMedico) {
                    case 1:
                        System.out.println("Lista de Médicos");
                        System.out.println("Id, " + "Nome, " + "Especialidade, " + "CRM, " + "Plantão");

                        for (Medico medico:medicoController.listarTodos()) {
                            System.out.println(medico.getId() + ", " + medico.getNome() + ", " + medico.getEspecialidade() + ", " + medico.getCrm() + ", " + medico.getPlantao());
                        }

                        break;
                    case 2:
                        int idMedico;
                        Medico medicoEncontrado;

                        do {
                            System.out.println("Digite o id do médico que deseja encontrar");
                            idMedico = scan.nextInt();

                            medicoEncontrado = medicoController.buscarPorId(idMedico);

                            if(medicoEncontrado == null)
                                System.out.printf("Médico não encontrado com o id %d, digite novamente \n", idMedico);
                        } while(medicoEncontrado == null);

                        System.out.println("Médico encontrado!");
                        System.out.println("Id, " + "Nome, " + "Especialidade, " + "CRM, " + "Plantão");
                        System.out.println(medicoEncontrado.getId() + ", " + medicoEncontrado.getNome() + ", " + medicoEncontrado.getEspecialidade() + ", " + medicoEncontrado.getCrm() + ", " + medicoEncontrado.getPlantao());

                        break;
                    case 3:
                        Medico novoMedico = new Medico();
                        String medicoNome;
                        String medicoEspecialidade;
                        String medicoCrm;
                        int opcaoPlantao;
                        boolean medicoPlantao = false;
                        boolean returnCadastrarMedico = false;

                        System.out.println("Digite o nome do médico");
                        medicoNome = scan.next();

                        System.out.println("Digite a especialidade do médico");
                        medicoEspecialidade = scan.next();

                        System.out.println("Digite o CRM do médico");
                        medicoCrm = scan.next();

                        do {
                            System.out.println("O médico está de plantão? [1] True [2] False");
                            opcaoPlantao = scan.nextInt();

                            if(opcaoPlantao > 2 || opcaoPlantao == 0)
                                System.out.println("Opção inválida, digite novamente \n");
                        } while (opcaoPlantao > 2 || opcaoPlantao == 0);

                        if(opcaoPlantao == 1)
                            medicoPlantao = true;

                        if(opcaoPlantao == 2)
                            medicoPlantao = false;

                        novoMedico.setNome(medicoNome);
                        novoMedico.setEspecialidade(medicoEspecialidade);
                        novoMedico.setCrm(medicoCrm);
                        novoMedico.setPlantao(medicoPlantao);

                        returnCadastrarMedico = medicoController.cadastrar(novoMedico);

                        if(!returnCadastrarMedico) {
                            System.out.println("Houve um problema ao cadastrar médico");
                            break;
                        };

                        System.out.println("Médico cadastrado");
                        break;
                    case 4:
                        int idMedicoAlterar;
                        Medico medicoAlterar;
                        int opcaoAlterar;
                        boolean returnMedicoAlterar;

                        do {
                            System.out.println("Id, " + "Nome, " + "Especialidade, " + "CRM, " + "Plantão");
                            for (Medico medico:medicoController.listarTodos()) {
                                System.out.println(medico.getId() + ", " + medico.getNome() + ", " + medico.getEspecialidade() + ", " + medico.getCrm() + ", " + medico.getPlantao());
                            }

                            System.out.println("Digite o id do médico que deseja editar");
                            idMedicoAlterar = scan.nextInt();

                            medicoAlterar = medicoController.buscarPorId(idMedicoAlterar);

                            if(medicoAlterar == null)
                                System.out.printf("Médico não encontrado com o id %d, digite novamente \n", idMedicoAlterar);
                        } while(medicoAlterar == null);

                        System.out.println("Médico encontrado");
                        System.out.println("Id, " + "Nome, " + "Especialidade, " + "CRM, " + "Plantão");
                        System.out.println(medicoAlterar.getId() + ", " + medicoAlterar.getNome() + ", " + medicoAlterar.getEspecialidade() + ", " + medicoAlterar.getCrm() + ", " + medicoAlterar.getPlantao());

                        System.out.println("""
                                Digite a opção da propriedade que gostaria de alterar \s
                                [1] Nome \s
                                [2] Especialidade \s
                                [3] CRM \s
                                [4] Plantao \s
                                """);
                        opcaoAlterar = scan.nextInt();

                        switch(opcaoAlterar) {
                            case 1:
                                String nome;

                                System.out.println("Digite o nome do médico");
                                nome = scan.next();

                                medicoAlterar.setNome(nome);
                                break;
                            case 2:
                                String especialidade;

                                System.out.println("Digite a especialidade");
                                especialidade = scan.next();

                                medicoAlterar.setEspecialidade(especialidade);
                                break;
                            case 3:
                                String crm;

                                System.out.println("Digite o crm");
                                crm = scan.next();

                                medicoAlterar.setCrm(crm);
                                break;
                            case 4:
                                int opcaoPlantaoAlterar;
                                boolean plantaoAlterar = false;

                                System.out.println("O médico está de plantão? [1] True [2] False");
                                opcaoPlantaoAlterar = scan.nextInt();

                                if(opcaoPlantaoAlterar == 1)
                                    plantaoAlterar = true;

                                if(opcaoPlantaoAlterar == 2)
                                    plantaoAlterar = false;

                                medicoAlterar.setPlantao(plantaoAlterar);
                                break;
                            default:
                                System.out.println("Opção inválida");
                                break;
                        }

                        returnMedicoAlterar = medicoController.editar(medicoAlterar);

                        if(!returnMedicoAlterar) {
                            System.out.println("Houve um problema ao editar médico");
                            break;
                        }

                        System.out.println("Médico editado");
                        break;
                    case 5:
                        int idMedicoExluir;
                        boolean returnMedicoExcluir;

                        do {
                            System.out.println("Id, " + "Nome, " + "Especialidade, " + "CRM, " + "Plantão");
                            for (Medico medico:medicoController.listarTodos()) {
                                System.out.println(medico.getId() + ", " + medico.getNome() + ", " + medico.getEspecialidade() + ", " + medico.getCrm() + ", " + medico.getPlantao());
                            }

                            System.out.println("Digite o id do médico que deseja excluir");
                            idMedicoExluir = scan.nextInt();

                            returnMedicoExcluir = medicoController.excluir(idMedicoExluir);

                            if(!returnMedicoExcluir)
                                System.out.printf("Médico não encontrado com o id %d, digite novamente \n", idMedicoExluir);
                        } while(!returnMedicoExcluir);

                        System.out.println("Médico excluído");
                        break;
                    default:
                        break;
                }
            } else if(opcao == 2) {
                int opcaoPaciente;
                System.out.println("""
                            Selecione a ação desejada \s
                            [1] Listar todos os Pacientes \s
                            [2] Buscar Paciente por id \s
                            [3] Cadastrar Paciente \s
                            [4] Editar Paciente \s
                            [5] Excluir Paciente \s
                            [0] Sair
                        """);
                opcaoPaciente = scan.nextInt();

                switch(opcaoPaciente) {
                    case 1:
                        System.out.println("Lista de pacientes");
                        System.out.println("Id, " + "nome, " + "cpf, " + "médico, " + "internado");

                        for(Paciente paciente:pacienteController.listarTodos()) {
                            System.out.println(paciente.getId() + ", " + paciente.getNome() + ", " + paciente.getCpf() + ", " + paciente.getMedico().getNome() + ", " + paciente.getInternado());
                        }

                        break;
                    case 2:
                        int idPaciente;
                        Paciente paciente;

                        do {
                            System.out.println("Digite o id do paciente");
                            idPaciente = scan.nextInt();

                            paciente = pacienteController.buscarPorId(idPaciente);

                            if(paciente == null)
                                System.out.printf("Paciente não encontrado com o id %d, digite novamente \n", idPaciente);
                        } while(paciente == null);

                        System.out.println("Paciente encontrado");
                        System.out.println("Id, " + "nome, " + "cpf, " + "médico, " + "internado");
                        System.out.println(paciente.getId() + ", " + paciente.getNome() + ", " + paciente.getCpf() + ", " + paciente.getMedico().getNome() + ", " + paciente.getInternado());
                        break;
                    case 3:
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

                        if(opcaoInternado == 2)
                            pacienteInternado = false;

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

                        returnCadastrarPaciente = pacienteController.cadastrar(novoPaciente);

                        if(!returnCadastrarPaciente) {
                            System.out.println("Houve um problema ao cadastrar paciente");
                            break;
                        }

                        System.out.println("Paciente cadastrado com sucesso");
                        break;
                    case 4:
                        int idPacienteAlterar;
                        Paciente pacienteAlterar;
                        int opcaoAlterar;
                        boolean returnPacienteAlterar;

                        do {
                            System.out.println("Id, " + "nome, " + "cpf, " + "médico, " + "internado");

                            for(Paciente pacienteExcluir:pacienteController.listarTodos()) {
                                System.out.println(pacienteExcluir.getId() + ", " + pacienteExcluir.getNome() + ", " + pacienteExcluir.getCpf() + ", " + pacienteExcluir.getMedico().getNome() + ", " + pacienteExcluir.getInternado());
                            }

                            System.out.println("Digite o id do paciente que deseja alterar");
                            idPacienteAlterar = scan.nextInt();

                            pacienteAlterar = pacienteController.buscarPorId(idPacienteAlterar);

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
                                opcaoInternado = scan.nextInt();

                                if(opcaoInternado == 1)
                                    internado = true;

                                if(opcaoInternado == 2)
                                    internado = false;

                                pacienteAlterar.setInternado(internado);
                                break;
                            default:
                                break;
                        }

                        returnPacienteAlterar = pacienteController.editar(pacienteAlterar);

                        if(!returnPacienteAlterar) {
                            System.out.println("Problemas ao editar paciente");
                            break;
                        }

                        System.out.println("Paciente editado com sucesso");
                        break;
                    case 5:
                        int idPacienteExcluir;
                        boolean returnPacienteExcluir;

                        do {
                            System.out.println("Id, " + "nome, " + "cpf, " + "médico, " + "internado");

                            for(Paciente pacienteExcluir:pacienteController.listarTodos()) {
                                System.out.println(pacienteExcluir.getId() + ", " + pacienteExcluir.getNome() + ", " + pacienteExcluir.getCpf() + ", " + pacienteExcluir.getMedico().getNome() + ", " + pacienteExcluir.getInternado());
                            }

                            System.out.println("Digite o id do paciente que deseja excluir");
                            idPacienteExcluir = scan.nextInt();

                            returnPacienteExcluir = pacienteController.excluir(idPacienteExcluir);

                            if(!returnPacienteExcluir)
                                System.out.printf("Paciente não encontrado com o id %d, digite novamente \n", idPacienteExcluir);
                        } while(!returnPacienteExcluir);

                        System.out.println("Paciente excluido com sucesso");
                        break;
                    default:
                        System.out.println("Saindo...");
                        break;
                }
            }
        } while(opcao != 0);

    }
}