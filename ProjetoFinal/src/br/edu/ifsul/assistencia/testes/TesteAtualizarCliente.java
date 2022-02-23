/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.assistencia.testes;

import br.edu.ifsul.assistencia.model.Cliente;
import br.edu.ifsul.assistencia.model.Produto;
import br.edu.ifsul.assistencia.model.dao.DAOCliente;

/**
 *
 * @author ramon
 */
public class TesteAtualizarCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Cliente c = new Cliente();
        c.setCodigoCliente(1);
        c.setCpf("12345678912");
        c.setEndereco("RUA TESTE");
        c.setNome("TELMO TESTE");
        c.setTelefone("1111111111");
        //?
        Produto p = new Produto();
        p.setCodigoProduto(1);
        c.setProduto(p);
        //?
        
        DAOCliente dao = new DAOCliente();
        if(dao.alterar(c)){
            System.out.println("Atualizou com sucesso");
        }else{
            System.out.println("Nao atualizou com sucesso");
        }
        
    }
    
}
