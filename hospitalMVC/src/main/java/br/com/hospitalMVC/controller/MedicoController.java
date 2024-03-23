package br.com.hospitalMVC.controller;

import br.com.hospitalMVC.DAO.GenericDAO;
import br.com.hospitalMVC.DAO.MedicoDAOimpl;
import br.com.hospitalMVC.model.Medico;
import br.com.hospitalMVC.model.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MedicoController {
    public MedicoController() {}

    public List<Medico> listarTodos() {
        try {
            GenericDAO dao = new MedicoDAOimpl();
            List<Medico> medicos = new ArrayList<>();

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

    public List<Paciente> listarPacientes(Integer id) {
        try {
            MedicoDAOimpl dao = new MedicoDAOimpl();

            return new ArrayList<>(dao.listarPacientes(id));
        } catch (Exception e) {
            System.out.println("Problemas no controller ao listar pacientes");
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

    public boolean vincularPaciente(Paciente paciente, Integer idMedico) {
        try {
            MedicoDAOimpl dao = new MedicoDAOimpl();

            dao.vincularPaciente(paciente, idMedico);

            return true;
        } catch (Exception e) {
            System.out.println("Problemas no controller ao vincular paciente");
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

    public boolean desvincularPaciente(Paciente paciente) {
        try {
            MedicoDAOimpl dao = new MedicoDAOimpl();

            dao.desvincularPaciente(paciente);

            return true;
        } catch (Exception e) {
            System.out.println("Problemas no controller ao desvincular paciente");
            e.printStackTrace();
            return false;
        }
    }
    public void chamarMenu(Scanner scan, PacienteController pacienteController) {
        int opcaoMedico;

        System.out.println("""
                            Selecione a ação desejada \s
                            [1] Listar todos os Médicos \s
                            [2] Buscar Médico por id \s
                            [3] Cadastrar Médico \s
                            [4] Editar Médico \s
                            [5] Excluir Médico \s
                            [6] Vincular Paciente \s
                            [7] Desvincular Paciente \s
                            [8] Exibir pacientes \s
                            [0] Sair
                        """);
        opcaoMedico = scan.nextInt();

        switch(opcaoMedico) {
            case 1:
                this.menuListarTodos();

                break;
            case 2:
                this.menuBuscarPorId(scan);

                break;
            case 3:
                boolean returnCadastro = this.menuCadastrar(scan);

                if(!returnCadastro) {
                    System.out.println("Houve um problema ao cadastrar médico");
                    break;
                }

                System.out.println("Médico cadastrado");

                break;
            case 4:
                boolean returnEditar = this.menuEditar(scan);

                if(!returnEditar) {
                    System.out.println("Houve um problema ao editar médico");
                    break;
                }

                System.out.println("Médico editado");

                break;
            case 5:
                this.menuExcluir(scan);

                break;
            case 6:
                boolean returnVincularPaciente = this.menuVincularPaciente(scan, pacienteController);

                if(!returnVincularPaciente) {
                    System.out.println("Houve um problema ao vincular paciente");
                    break;
                }

                System.out.println("Paciente vinculado com sucesso");

                break;
            case 7:
                boolean returnDesvincularPaciente = this.menuDesvincularPaciente(scan, pacienteController);

                if(!returnDesvincularPaciente) {
                    System.out.println("Houve um problema ao desvincular paciente");
                    break;
                }

                System.out.println("Paciente desvinculado com sucesso");

                break;
            case 8:
                this.menuExibirPacientes(scan);

                break;
            default:
                break;
        }
    }

    private void menuListarTodos() {
        System.out.println("Lista de Médicos");
        System.out.println("Id, " + "Nome, " + "Especialidade, " + "CRM, " + "Plantão");

        for (Medico medico:this.listarTodos()) {
            System.out.println(medico.getId() + ", " + medico.getNome() + ", " + medico.getEspecialidade() + ", " + medico.getCrm() + ", " + medico.getPlantao());
        }
    }

    private void menuBuscarPorId(Scanner scan) {
        int idMedico;
        Medico medicoEncontrado;

        do {
            System.out.println("Digite o id do médico que deseja encontrar");
            idMedico = scan.nextInt();

            medicoEncontrado = this.buscarPorId(idMedico);

            if(medicoEncontrado == null)
                System.out.printf("Médico não encontrado com o id %d, digite novamente \n", idMedico);
        } while(medicoEncontrado == null);

        System.out.println("Médico encontrado!");
        System.out.println("Id, " + "Nome, " + "Especialidade, " + "CRM, " + "Plantão");
        System.out.println(medicoEncontrado.getId() + ", " + medicoEncontrado.getNome() + ", " + medicoEncontrado.getEspecialidade() + ", " + medicoEncontrado.getCrm() + ", " + medicoEncontrado.getPlantao());

    }

    private boolean menuCadastrar(Scanner scan) {
        Medico novoMedico = new Medico();
        String medicoNome;
        String medicoEspecialidade;
        String medicoCrm;
        int opcaoPlantao;
        boolean medicoPlantao = false;
        boolean returnCadastrarMedico;

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

        novoMedico.setNome(medicoNome);
        novoMedico.setEspecialidade(medicoEspecialidade);
        novoMedico.setCrm(medicoCrm);
        novoMedico.setPlantao(medicoPlantao);

        returnCadastrarMedico = this.cadastrar(novoMedico);

        return returnCadastrarMedico;
    }

    private boolean menuEditar(Scanner scan) {
        int idMedicoAlterar;
        Medico medicoAlterar;
        int opcaoAlterar;
        boolean returnMedicoAlterar;

        do {
            System.out.println("Id, " + "Nome, " + "Especialidade, " + "CRM, " + "Plantão");
            for (Medico medico:this.listarTodos()) {
                System.out.println(medico.getId() + ", " + medico.getNome() + ", " + medico.getEspecialidade() + ", " + medico.getCrm() + ", " + medico.getPlantao());
            }

            System.out.println("Digite o id do médico que deseja editar");
            idMedicoAlterar = scan.nextInt();

            medicoAlterar = this.buscarPorId(idMedicoAlterar);

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

                medicoAlterar.setPlantao(plantaoAlterar);
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }

        returnMedicoAlterar = this.editar(medicoAlterar);

        return returnMedicoAlterar;
    }

    private void menuExcluir(Scanner scan) {
        int idMedicoExluir;
        boolean returnMedicoExcluir;

        do {
            System.out.println("Id, " + "Nome, " + "Especialidade, " + "CRM, " + "Plantão");
            for (Medico medico:this.listarTodos()) {
                System.out.println(medico.getId() + ", " + medico.getNome() + ", " + medico.getEspecialidade() + ", " + medico.getCrm() + ", " + medico.getPlantao());
            }

            System.out.println("Digite o id do médico que deseja excluir");
            idMedicoExluir = scan.nextInt();

            returnMedicoExcluir = this.excluir(idMedicoExluir);

            if(!returnMedicoExcluir)
                System.out.printf("Médico não encontrado com o id %d, digite novamente \n", idMedicoExluir);
        } while(!returnMedicoExcluir);

        System.out.println("Médico excluído");
    }

    private boolean menuVincularPaciente(Scanner scan, PacienteController pacienteController) {
        int idPaciente;
        int idMedico;
        Medico medico;
        Paciente paciente;
        boolean returnVincularPaciente;

        do {
            this.menuListarTodos();

            System.out.println("Digite o id do médico em que deseja vincular um paciente");
            idMedico = scan.nextInt();

            medico = this.buscarPorId(idMedico);

            if(medico == null)
                System.out.printf("Médico não encontrado com o id %d, digite novamente \n", idMedico);
        } while(medico == null);

        do {
            pacienteController.menuListarTodos();

            System.out.println("Digite o id do paciente que deseja vincular ao médico");
            idPaciente = scan.nextInt();

            paciente = pacienteController.buscarPorId(idPaciente);

            if(paciente == null)
                System.out.printf("Paciente não encontrado com o id %d, digite novamente \n", idPaciente);
        } while(paciente == null);

        returnVincularPaciente = this.vincularPaciente(paciente, idMedico);

        return returnVincularPaciente;
    }

    private boolean menuDesvincularPaciente(Scanner scan, PacienteController pacienteController) {
        int idMedico;
        int idPaciente;
        Medico medico;
        Paciente paciente;
        boolean returnDesvincularPaciente;

        do {
            this.menuListarTodos();

            System.out.println("Digite o id do médico que deseja desvincular um paciente");
            idMedico = scan.nextInt();

            medico = this.buscarPorId(idMedico);

            if(medico == null)
                System.out.printf("Médico não encontrado com o id %d, digite novamente \n", idMedico);
        } while(medico == null);

        do {
            System.out.println("Id, Nome");
            for(Paciente pacienteReturn:this.listarPacientes(idMedico)) {
                System.out.println(pacienteReturn.getId() + ", " + pacienteReturn.getNome());
            }

            System.out.println("Digite o id do paciente que deseja remover");
            idPaciente = scan.nextInt();

            paciente = pacienteController.buscarPorId(idPaciente);

            if (paciente == null)
                System.out.printf("Paciente não encontrado com o id %d, digite novamente \n", idPaciente);
        } while(paciente == null);

        returnDesvincularPaciente = this.desvincularPaciente(paciente);

        return returnDesvincularPaciente;
    }

    private void menuExibirPacientes(Scanner scan) {
        int idMedico;
        Medico medico;
        int tipoRelatorio;

        do {
            this.menuListarTodos();

            System.out.println("Digite o id do médico que deseja visualizar os pacientes");
            idMedico = scan.nextInt();

            medico = this.buscarPorId(idMedico);

            if(medico == null)
                System.out.printf("Médico não encontrado com o id %d, digite novamente \n", idMedico);

        } while(medico == null);

        do {
            System.out.println("""
                    Selecione o tipo do relatorio \s
                    [1] Todos os pacientes \s
                    [2] Pacientes internados \s
                    [3] Pacientes não internados
                    """);
            tipoRelatorio = scan.nextInt();

            if(tipoRelatorio > 3 || tipoRelatorio == 0)
                System.out.println("Opção inválida, digite novamente");
        } while(tipoRelatorio > 3);

        this.relatorioPacientesVinculados(tipoRelatorio, idMedico);
    }

    private void relatorioPacientesVinculados(Integer tipoRelatorio, Integer idMedico) {
        List<Paciente> pacientes = new ArrayList<>();

        switch (tipoRelatorio) {
            case 1:
                pacientes.addAll(this.listarPacientes(idMedico));
                this.exibirRelatorioPacientes(pacientes);
                break;
            case 2:
                for(Paciente paciente:this.listarPacientes(idMedico)) {
                    if(paciente.getInternado())
                        pacientes.add(paciente);
                }
                this.exibirRelatorioPacientes(pacientes);
                break;
            case 3:
                for(Paciente paciente:this.listarPacientes(idMedico)) {
                    if(!paciente.getInternado())
                        pacientes.add(paciente);
                }
                this.exibirRelatorioPacientes(pacientes);
                break;
            default:
                break;
        }
    }

    private void exibirRelatorioPacientes(List<Paciente> pacientes) {
        System.out.println("Id, Nome");
        for (Paciente paciente:pacientes) {
            System.out.println(paciente.getId() + ", " + paciente.getNome());
        }
    }
}
