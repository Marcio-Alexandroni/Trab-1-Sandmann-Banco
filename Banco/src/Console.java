
import javax.swing.JOptionPane;

// Classe reservada para visualização das funções do Banco
public class Console {

    private static Banco banco = new Banco("Russin Bank");

    public static void main(String[] args) {

        BancoUtils.showWelcomeMessage(); 
        
        Cliente clienteAtual = null;
        Conta contaAtual = null;

         

        while (true) {
            try {
                // Ajuste no prompt para sempre mostrar o nome do cliente e, se tiver, a conta
                StringBuffer radical = new StringBuffer();
                radical.append(clienteAtual == null ? "" : clienteAtual.getNome());
                radical.append(radical.length() == 0 ? "" : "|");
                radical.append(contaAtual == null ? "" : contaAtual);
                radical.append("> ");
                String linha = JOptionPane.showInputDialog(radical.toString());

                if (linha.equals("exit")) break;
                else if (linha.equals("help")) BancoUtils.showHelp();
                else if (linha.equals("1")) BancoUtils.listCustomers();
                else if (linha.equals("2")) {
                    clienteAtual = BancoUtils.insertCustomer();
                    contaAtual = null;
                } else if (linha.equals("3")) {
                    clienteAtual = BancoUtils.selectCliente();
                    contaAtual = null;
                } else if (linha.equals("4")) BancoUtils.listContas(clienteAtual);
                else if (linha.equals("5")) contaAtual = BancoUtils.createConta(clienteAtual);
                else if (linha.equals("6")) contaAtual = BancoUtils.selectConta(clienteAtual);
                else if (linha.equals("7")) BancoUtils.deposit(contaAtual);
                else if (linha.equals("8")) BancoUtils.withdraw(contaAtual);
                else if (linha.length() == 0) continue;
                else JOptionPane.showMessageDialog(null, "Comando inválido");

            } catch (RuntimeException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        JOptionPane.showMessageDialog(null, "Obrigado por usar o " + banco.getNome());
    }

}
