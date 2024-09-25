// Fazer a classe cliente que poderá ser extendida para PessoaFísica ou PessoaJurídica | Apenas atributos em comum deverão ser colocados (ex: nome, id - UUID)

import java.util.UUID;

public abstract class Cliente {
    
    String nome;
    String id;

    public Cliente() {
        this.id = UUID.randomUUID().toString();
    }

    public Cliente(String nome){
        this();
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cliente [nome = " + nome + ", id = " + id + "]";
    }

}