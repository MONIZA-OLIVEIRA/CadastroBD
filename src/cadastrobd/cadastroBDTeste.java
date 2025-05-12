package cadastrobd;

import cadastrobd.model.*;

import java.util.List;
import java.util.Scanner;

public class CadastroBDTeste {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
    private static final PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO();

    public static void main(String[] args) {

        int opcao;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Obter por ID");
            System.out.println("5 - Obter todos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> incluir();
                case 2 -> alterar();
                case 3 -> excluir();
                case 4 -> obterPorId();
                case 5 -> listarTodos();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void incluir() {
        System.out.print("Pessoa Física (F) ou Jurídica (J)? ");
        String tipo = scanner.nextLine().toUpperCase();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        if (tipo.equals("F")) {
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            PessoaFisica pf = new PessoaFisica(0, nome, logradouro, cidade, estado, telefone, email, cpf);
            pfDAO.incluir(pf);
            System.out.println("Pessoa física incluída.");
        } else if (tipo.equals("J")) {
            System.out.print("CNPJ: ");
            String cnpj = scanner.nextLine();
            PessoaJuridica pj = new PessoaJuridica(0, nome, logradouro, cidade, estado, telefone, email, cnpj);
            pjDAO.incluir(pj);
            System.out.println("Pessoa jurídica incluída.");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void alterar() {
        System.out.print("Pessoa Física (F) ou Jurídica (J)? ");
        String tipo = scanner.nextLine().toUpperCase();

        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (tipo.equals("F")) {
            PessoaFisica pf = pfDAO.getPessoa(id);
            if (pf == null) {
                System.out.println("Pessoa não encontrada.");
                return;
            }
            System.out.println("Dados atuais:");
            pf.exibir();

            System.out.print("Novo nome: ");
            pf.nome = scanner.nextLine();
            System.out.print("Novo logradouro: ");
            pf.logradouro = scanner.nextLine();
            System.out.print("Nova cidade: ");
            pf.cidade = scanner.nextLine();
            System.out.print("Novo estado: ");
            pf.estado = scanner.nextLine();
            System.out.print("Novo telefone: ");
            pf.telefone = scanner.nextLine();
            System.out.print("Novo email: ");
            pf.email = scanner.nextLine();
            System.out.print("Novo CPF: ");
            pf = new PessoaFisica(pf.id, pf.nome, pf.logradouro, pf.cidade, pf.estado, pf.telefone, pf.email, scanner.nextLine());

            pfDAO.alterar(pf);
            System.out.println("Pessoa física atualizada.");

        } else if (tipo.equals("J")) {
            PessoaJuridica pj = pjDAO.getPessoa(id);
            if (pj == null) {
                System.out.println("Pessoa não encontrada.");
                return;
            }
            System.out.println("Dados atuais:");
            pj.exibir();

            System.out.print("Novo nome: ");
            pj.nome = scanner.nextLine();
            System.out.print("Novo logradouro: ");
            pj.logradouro = scanner.nextLine();
            System.out.print("Nova cidade: ");
            pj.cidade = scanner.nextLine();
            System.out.print("Novo estado: ");
            pj.estado = scanner.nextLine();
            System.out.print("Novo telefone: ");
            pj.telefone = scanner.nextLine();
            System.out.print("Novo email: ");
            pj.email = scanner.nextLine();
            System.out.print("Novo CNPJ: ");
            pj = new PessoaJuridica(pj.id, pj.nome, pj.logradouro, pj.cidade, pj.estado, pj.telefone, pj.email, scanner.nextLine());

            pjDAO.alterar(pj);
            System.out.println("Pessoa jurídica atualizada.");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void excluir() {
        System.out.print("Pessoa Física (F) ou Jurídica (J)? ");
        String tipo = scanner.nextLine().toUpperCase();
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (tipo.equals("F")) {
            pfDAO.excluir(id);
            System.out.println("Pessoa física excluída.");
        } else if (tipo.equals("J")) {
            pjDAO.excluir(id);
            System.out.println("Pessoa jurídica excluída.");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void obterPorId() {
        System.out.print("Pessoa Física (F) ou Jurídica (J)? ");
        String tipo = scanner.nextLine().toUpperCase();
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (tipo.equals("F")) {
            PessoaFisica pf = pfDAO.getPessoa(id);
            if (pf != null) pf.exibir();
            else System.out.println("Pessoa não encontrada.");
        } else if (tipo.equals("J")) {
            PessoaJuridica pj = pjDAO.getPessoa(id);
            if (pj != null) pj.exibir();
            else System.out.println("Pessoa não encontrada.");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void listarTodos() {
        System.out.print("Pessoa Física (F) ou Jurídica (J)? ");
        String tipo = scanner.nextLine().toUpperCase();

        if (tipo.equals("F")) {
            List<PessoaFisica> lista = pfDAO.getPessoas();
            lista.forEach(p -> {
                p.exibir();
                System.out.println("----------------------");
            });
        } else if (tipo.equals("J")) {
            List<PessoaJuridica> lista = pjDAO.getPessoas();
            lista.forEach(p -> {
                p.exibir();
                System.out.println("----------------------");
            });
        } else {
            System.out.println("Tipo inválido.");
        }
    }
}