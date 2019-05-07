package br.mackenzie.ps2;

/**
 *
 * @author Joaquim Pessôa Filho
 * 
 */
public class Banco {
    private int id;
    private String nome;
    private String cnpj;
    private int numeroClientes;
    
    public Banco(){
        this.nome = "";
        this.cnpj = "";
    }
    
    public Banco(int id, String nome, String cnpj, int numeroClientes) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.numeroClientes = numeroClientes;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @return the numeroClientes
     */
    public int getNumeroClientes() {
        return numeroClientes;
    }

    /**
     * @param numeroClientes the numeroClientes to set
     */
    public void setNumeroClientes(int numeroClientes) {
        this.numeroClientes = numeroClientes;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
}
