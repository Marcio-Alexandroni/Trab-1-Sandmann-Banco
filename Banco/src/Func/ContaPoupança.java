package Func;

// Conta Poupança que extenderá a classe Conta e implementará a interface Rendimento

public class ContaPoupança extends Conta implements Rendimento {

    public ContaPoupança(Cliente cliente){
        super(cliente);
    }

    @Override
    public double getTaxa() {
        return 0.05;
    }
    
    @Override
    public void aplicar() {
        saldo += saldo * getTaxa();
    }

    @Override
    public void sacar(double valor) {

        if (valor <= saldo) {
            this.saldo -= valor;
        } else {
            throw new RuntimeException("Saldo insuficiente");
        }

    }
    
}
