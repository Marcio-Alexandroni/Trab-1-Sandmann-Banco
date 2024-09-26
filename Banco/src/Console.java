
import static javax.swing.JOptionPane.*;
import java.util.Scanner;

// Classe reservada para visualização das funções do Banco
public class Console {

    private static Banco banco = new Banco("Russin Bank");
    private static final Scnaner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Bem vindo ao " + banco.getNome());
        while (true) {
            Sysout.println("> ");
            String linha = scanner.nextLine().trim();
            if (linha.equalsIgnoreCase("exit")) {
                break;
            }
            else if (linha.equalsIgnoreCase("help")) help();
            else if (linha.equals("1")) listCustomers();
            else if (linha.equals("2")) insertCustomer();
            else if (linha.length() == 0) continue;
            else System.err.println("Comando inválido");
        }
        System.out.println("Até mais!");
        scanner.close();
        
    }

    private static void listCustomers(){

        System.out.println("--- Lista de clientes:");
        banco,getClientes().forEach(System.out::println);

    }

    private static void help(){

        System.out.println("Comandos disponíveis:");
        System.out.println("1 - Listar clientes");
        System.out.println("2 - Inserir cliente");
        System.out.println("exit - Sair");
        System.out.println("help - Ajuda");

    }

    private static void insertCustomer(){

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

    }

}
