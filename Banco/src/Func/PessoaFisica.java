package Func;

// A Classe PessoaFisica extenderá a classe Cliente e terá seus atributos + CPF

public class PessoaFisica extends Cliente {

    private final String cpf;

    public PessoaFisica(String cpf) {
        super();
        this.cpf = cpf;
    }

    public PessoaFisica(String cpf, String nome) {
        super(nome);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + cpf + ")";
    }

    
}
