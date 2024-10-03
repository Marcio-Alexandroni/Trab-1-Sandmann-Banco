
import static javax.swing.JOptionPane.*;
import java.util.Scanner;

// Classe reservada para visualização das funções do Banco

public class Console {

    private static Banco banco = new Banco("Russin Bank");
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Bem vindo ao " + banco.getNome());
        Cliente clienteAtual = null;
        Conta contaAtual = null;

        while (true) {

           try{

            // Monta o radical do prompt
            StringBuffer radical = new StringBuffer();
            radical.append(clienteAtual != null ? "" : clienteAtual.getNome());
            radical.append(radical.length() == 0 ? "" : " | ");
            radical.append(contaAtual == null ? "" : contaAtual);
            radical.append("> ");
            System.out.print(radical.toString());
            String linha = scanner.nextLine().trim(); // Recebe a linha de comando
            // Menu
            switch (linha) {
                case "exit":
                    break;
                case "help":
                    help();
                case "1":
                    listCustomers();
                case "2":
                    clienteAtual = insertCustomer();
                    contaAtual = null;
                case "3":
                    clienteAtual = selectCliente();
                    contaAtual = null;
                case "4":
                    listContas(clienteAtual);
                case "5":
                    contaAtual = createConta(clienteAtual);
                case "6":
                    contaAtual = selectConta(clienteAtual);
                case "7":
                    deposit(contaAtual);
                case "8":
                    withdraw(contaAtual);
                case "":
                    continue;
                default:
                    System.err.println("comando invalido");
            }

           } catch (RuntimeException e){

            e.printStackTrace();
            System.err.println("Erro: " + e.getMessage());

           }

        }

        System.out.println("--- Bye Bye! ---");
        scanner.close();
        
    }

    private static void help(){

        System.out.println(

            "---" + banco.getNome() + " ---\n" +
            "/n" +
            "help - Ajuda\n" +
            "1 - Listar clientes\n" +
            "2 - Inserir cliente\n" +
            "3 - Selecionar cliente\n" +
            "4 - Listar contas\n" +
            "5 - Criar conta\n" +
            "6 - Selecionar conta\n" +
            "7 - Depositar\n" +
            "8 - Sacar\n" +
            "exit - Sair\n" +

        );

    }

    private static void listCustomers(){

        System.out.println("--- Lista de clientes:");
        banco.getClientes().forEach(cliente -> System.out.println(cliente));

    }

    private static Cliente insertCustomer(){

        System.out.println("Tipo de cliente [PF|PJ]:");
        boolean isFisica = scanner.nextLine().trim().equalsIgnoreCase("PF");
        final Cliente cliente;
        System.out.println("Nome: ");
        String nome = scanner.nextLine().trim();

        if (isFisica) {

            System.out.println("CPF: ");
            String cpf = scanner.nextLine().trim();
            cliente = new PessoaFisica(nome, cpf);

        } else {

            System.out.println("CNPJ: ");
            String cnpj = scanner.nextLine().trim();
            cliente = new PessoaJuridica(nome, cnpj);

        }
        banco.addCliente(cliente);
        return cliente;

    }

    private static void deposit(Conta conta){

        if (conta == null) {
            throw new RuntimeException("Conta não selecionada");
        }
        System.out.println("Depositar: ");
        double valor = scanner.nextDouble();
        conta.depositar(valor);

    }

    private static void withdraw(Conta conta){

        if (conta == null) {
            throw new RuntimeException("Conta não selecionada");
        }
        System.out.println("Sacar: ");
        double valor = scanner.nextDouble();
        conta.sacar(valor);

    }

    priavte static void listContas(Cliente cliente){

        if (cliente == null) {
            throw new RuntimeException("Cliente não selecionado");
        }
        cliente.getContas().forEach(conta -> System.out.println(conta));

    }

    private static Cliente selectCliente(){

        System.out.println(-- Clientes --);
        listCustomers();
        System.out.println();
        System.out.println("Código: ");
        String codigo = scanner.nextLine().trim();

        for (Cliente cliente : banco.getClientes()) {

            if (cliente.getId().equalsIgnoreCase(codigo)) {
                return cliente;
            }
            throw new RuntimeException("Cliente não encontrado" + codigo);

        }

    }

    private static Conta selectConta(Cliente cliente){

        if (cliente == null) {
            throw new RuntimeException("Cliente não selecionado");
        }
        listContas(cliente);
        System.out.println();
        System.out.println("Código: ");
        String codigo = scanner.nextLine().trim();

        for (Conta conta : cliente.getContas()) {

            if (conta.getId().equalsIgnoreCase(codigo)) {
                return conta;
            }
            throw new RuntimeException("Conta não encontrada" + codigo);

        }

    }

    private static Conta createConta(Cliente cliente){

        if (cliente == null) {
            throw new RuntimeException("Cliente não selecionado");
        }
        System.out.println("Tipo de conta [CC | CP | CI]: ");
        String tipo = scanner.nextLine().trim();
        Conta conta = 
            tipo.equalsIgnoreCase("CC") ? new ContaCorrente(cliente) :
            tipo.equalsIgnoreCase("CP") ? new ContaPoupanca(cliente) :
            tipo.equalsIgnoreCase("CI") ? new ContaInvestimento(cliente) :
            null;
        if (conta == null) {
            throw new RuntimeException("Tipo de conta inválido");
        }
        cliente.getContas().add(conta);
        return conta;

    }

}
