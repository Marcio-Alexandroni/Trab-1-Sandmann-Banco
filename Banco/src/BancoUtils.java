
import javax.swing.JOptionPane;

//Classe reservada para armazenamento das funções

public class BancoUtils {

    public static Banco banco = new Banco("Russin Bank");

    public static void showWelcomeMessage() {

        JOptionPane.showMessageDialog(null, "Bem vindo ao " + banco.getNome());
        JOptionPane.showMessageDialog(null, "Digite 'help' para ver os comandos disponíveis");

    }

    public static void showHelp() {

        JOptionPane.showMessageDialog(null, banco.getNome() + "\nComandos disponíveis:\n" +
                "1 - Listar Clientes\n" +
                "2 - Cadastrar Cliente\n" +
                "3 - Selecionar Cliente\n" +
                "4 - Listar Contas\n" +
                "5 - Criar Conta\n" +
                "6 - Selecionar Conta\n" +
                "7 - Depositar\n" +
                "8 - Sacar\n" +
                "exit - Sair\n" +
                "help - Ajuda\n");

    }

    public static void listCustomers() {

        StringBuilder sb = new StringBuilder("---- Lista de Clientes ----\n");
        banco.getClientes().forEach(cliente -> {

            if (cliente instanceof PessoaFisica) {

                sb.append("Pessoa Física: ").append(cliente.getNome())
                        .append(" | CPF: ").append(((PessoaFisica) cliente).getCpf()).append("\n");

            } else {

                sb.append("Pessoa Jurídica: ").append(cliente.getNome())
                        .append(" | CNPJ: ").append(((PessoaJuridica) cliente).getCnpj()).append("\n");

            }

        });
        JOptionPane.showMessageDialog(null, sb.toString());

    }

    public static Cliente insertCustomer() {

        JOptionPane.showMessageDialog(null, "Tipo de Cliente: \n" + "PF - Pessoa Física\n" + "PJ - Pessoa Jurídica\n");
        boolean isFisica = JOptionPane.showInputDialog("Tipo de cliente [PF|PJ]").equalsIgnoreCase("PF");
        final Cliente cliente;
        String nome = JOptionPane.showInputDialog("Nome do Cliente: ");
        if (isFisica) {

            String cpf = JOptionPane.showInputDialog("CPF do Cliente: ");
            cliente = new PessoaFisica(cpf, nome);

        } else {

            String cnpj = JOptionPane.showInputDialog("CNPJ do Cliente: ");
            cliente = new PessoaJuridica(cnpj, nome);

        }
        banco.add(cliente);
        return cliente;

    }

    public static void deposit(Conta conta) {

        if (conta == null) {
            throw new RuntimeException("Nenhuma conta selecionada");
        }
        double valor = Double.parseDouble(JOptionPane.showInputDialog("Valor do Depósito: "));
        conta.depositar(valor);

    }

    public static void withdraw(Conta conta) {

        if (conta == null) {
            throw new RuntimeException("Nenhuma conta selecionada");
        }
        double valor = Double.parseDouble(JOptionPane.showInputDialog("Valor do Saque: "));
        conta.sacar(valor);

    }

    public static void listContas(Cliente cliente) {

        if (cliente == null) {
            throw new RuntimeException("Nenhum cliente selecionado");
        }
        StringBuilder sb = new StringBuilder("---- Contas do Cliente ----\n");
        cliente.getContas().forEach(conta -> sb.append(conta).append("\n"));
        JOptionPane.showMessageDialog(null, sb.toString());

    }

    public static Cliente selectCliente() {

        listCustomers();
        String codigo = JOptionPane.showInputDialog("Código do Cliente: ");
        for (Cliente cliente : banco.getClientes()) {

            if (cliente.getId().equalsIgnoreCase(codigo)) {
                return cliente;
            }

        }
        throw new RuntimeException("Cliente não encontrado");

    }

    public static Conta selectConta(Cliente cliente) {

        if (cliente == null) {
            throw new RuntimeException("Nenhum cliente selecionado");
        }
        listContas(cliente);
        String codigo = JOptionPane.showInputDialog("Código da Conta: ");
        for (Conta conta : cliente.getContas()) {

            if (conta.getId().equalsIgnoreCase(codigo)) {
                return conta;
            }

        }
        throw new RuntimeException("Conta não encontrada");

    }

    public static Conta createConta(Cliente cliente) {

        if (cliente == null) {
            throw new RuntimeException("Nenhum cliente selecionado");
        }
        String tipo = JOptionPane.showInputDialog(
                "Tipo de Conta: \n" + "CC - Conta Corrente\n" + "CP - Conta Poupança\n" + "CI - Conta Investimento\n");
        Conta conta = tipo.equalsIgnoreCase("CC") ? new ContaCorrente(cliente)
                : tipo.equalsIgnoreCase("CP") ? new ContaPoupança(cliente)
                        : tipo.equalsIgnoreCase("CI") ? new ContaInvestimento(cliente) : null;
        if (conta == null) {
            throw new RuntimeException("Tipo de conta inválido");
        }
        cliente.getContas().add(conta);
        return conta;

    }

    public static Banco getBanco() {
        return banco;
    }
    
}
