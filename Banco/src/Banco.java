
// Classe reservada para o Banco e suas funcionalidades

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Banco {

    private String nome;

    private Map<String, Cliente> clientes = new HashMap<>();
    private Mao<String, PessoaFisica> clientebyCPF = new HashMap<>();
    private Map<String, PessoaJuridica> clientebyCNPJ = new HashMap<>();

    public Banco(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void add(Cliente cliente) {

        clientes.put(cliente.getId(), cliente);
        if (cliente instanceof PessoaFisica pf) clientebyCPF.put(pf.getCPF(), pf);
        if (cliente instanceof PessoaJuridica pj) clientebyCNPJ.put(pj.getCNPJ(), pj);
    
    }

    public Set<Cliente> getClientes() {

        Collection<Cliente> colecao = clientes.values();
        colecao = Collections.unmodifiableCollection(colecao);
        return Collections.unmodifiableSet(new HashSet<>(colecao));

    }

}
