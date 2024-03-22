package br.com.hospitalMVC;

import br.com.hospitalMVC.controller.MedicoController;
import br.com.hospitalMVC.controller.PacienteController;

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
                medicoController.chamarMenu(scan);
            } else if(opcao == 2) {
                pacienteController.chamarMenu(scan, medicoController);
            }
        } while(opcao != 0);

    }
}