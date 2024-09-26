
// Conta Poupança extenderá a classe Conta 

public class ContaCorrente extends Conta {

    public ContaCorrenteq(Cliente cliente) {
        super(cliente);
    }

    private double limite = 1000;

    @Override
    public void sacar(double valor) {

        if (valor > 0 && valor <= saldo + limite) {
            saldo -= valor;
        }
        
    }
    
}
