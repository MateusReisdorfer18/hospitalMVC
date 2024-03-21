package br.com.hospitalMVC;

import br.com.hospitalMVC.controller.MedicoController;
import br.com.hospitalMVC.model.Medico;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int opcao;

        MedicoController medicoController = new MedicoController();

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
                                System.out.printf("Médico não encontrado com o id %d, digite novamente", idMedico);
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
                                System.out.println("Opção inválida, digite novamente");
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
                                System.out.printf("Médico não encontrado com o id %d, digite novamente", idMedicoAlterar);
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
                                System.out.printf("Médico não encontrado com o id %d, digite novamente", idMedicoExluir);
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
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        break;
                }
            }
        } while(opcao != 0);

    }
}