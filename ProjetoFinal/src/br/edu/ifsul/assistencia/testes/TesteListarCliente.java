/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.assistencia.testes;

import br.edu.ifsul.assistencia.model.Cliente;
import br.edu.ifsul.assistencia.model.dao.DAOCliente;
import java.util.List;

/**
 *
 * @author ramon
 */
public class TesteListarCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DAOCliente  dao = new DAOCliente();
                List<Cliente> lista = dao.listar();
                for(Cliente c : lista){
                    System.out.println("Codigo: "+c.getCodigoCliente()+ "\nNome: "+ c.getNome() + "\nEndereco: "+ c.getEndereco() );
                }
    }
    
}
