package Func;

// Conta Rendimento que extenderá a classe Conta e implementará a interface Rendimento

/*
 * A conta investimento so permite saque a cada tres
 * depositos.
 */

public class ContaInvestimento extends Conta implements Rendimento {
    
    private int qtdDepositos = 0;
    private double taxa = 0.02;

    public ContaInvestimento(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void depositar(double valor) {
        super.depositar(valor);
        this.qtdDepositos += 1;
    }

    @Override
    public void sacar(double valor) {
        if (valor <= saldo && (qtdDepositos % 3) == 0) {
            this.saldo =- valor;
        } else {
            throw new RuntimeException("Saque não permitido");
        }
    }

    public int getQtdDepositos() {
        return qtdDepositos;
    }

    @Override
    public double getTaxa() {
        return taxa;
    }

    @Override
    public void aplicar() {
        this.saldo += this.saldo * getTaxa();
    }
    
}
