package br.mackenzie.ps2;

import java.util.List;

/**
 *
 * @author Joaquim Pessôa Filho
 * 
 */
public class Main {
    public static void main(String args[]){
        BancoDAO dao = new BancoDAO();
        
        List<Banco> bancos = dao.lerTodos();
        System.out.println(bancos.size());
        
        bancos.get(0).setNome("Itaú S.A.");
        bancos.get(0).setCnpj("987654321");
        bancos.get(0).setNumeroClientes(100);
        
        dao.atualizar(bancos.get(0));
    }
}
