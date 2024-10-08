package Func;

// Essa será uma superclasse para as outras Contas do Banco

import java.util.UUID;

public abstract class Conta {

    final public String id = UUID.randomUUID().toString();

    private Cliente cliente;

    protected double saldo = 0;

    public Conta(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {

        if (valor <= 0)return;
        this.saldo += valor;

    }

    public abstract void sacar(double valor);

    @Override
    public String toString() {
        return "Conta [id = " + id + " | saldo = " + saldo + "]";
    }
    
}
