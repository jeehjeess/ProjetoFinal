/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.assistencia.model.dao;

import br.edu.ifsul.assistencia.model.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 20181210031
 */
public class DAOFuncionario {
    
    public boolean inserir(Funcionario obj){
        
        String sql = " insert into funcionario (nome, telefone, cpf, tipo) values" +
        "(?,?,?,?) ";
        try{
            PreparedStatement pst= Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getTelefone());
            pst.setString(3, obj.getCpf());
            pst.setString(4, obj.getTipo());
            
            if(pst.executeUpdate()> 0){
                System.out.println("Funcionário inserido com sucesso!!");
                return true; 
            
            }else{
                //nada acontece feijoada. Funcionário não foi inserido!
                return false;
            }
        
        }catch(Exception e){
            System.out.println("Erro de SQL: "+ e.getMessage());
            return false;
        }
        
    
    
    }
    public boolean alterar( Funcionario obj){
    
        String sql = "update funcionario set nome = ?, telefone =?, cpf =?, tipo =? "
                + "where funcionario_cod =? ";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getTelefone());
            pst.setString(3, obj.getCpf());
            pst.setString(4, obj.getTipo());            
            pst.setInt(5, obj.getFuncionario_cod());
            
             if (pst.executeUpdate() > 0) {
                System.out.println("Funcionário alterado com sucesso!!!");
                return true;
            } else {
                //nada aconteceu feijoada, tem que retornar falso. Não alterou nenhuma linha
                return false;
            }
        
        }catch(Exception e){
            System.out.println("Erro de SQL: "+e.getMessage());
            return false;
        }
    }
    
    public boolean remover(Funcionario obj) {
        String sql = " delete  from funcionario where funcionario_cod =?  ";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getFuncionario_cod());
            //executa update para mostrar as linhas alteradas

            if (pst.executeUpdate() > 0) {
                System.out.println("Funcionário removido com sucesso!!!");
                return true;
            } else {
                //nada aconteceu feijoada, tem que retornar falso. Não alterou nenhuma linha
                return false;
            }

        } catch (Exception e) {
            System.out.println("Erro SQL: " + e.getMessage());
            return false;
        }
    }
    
    public List<Funcionario> listar(){
    
        String sql = "select * from funcionario order by nome asc";
        List<Funcionario> lista = new ArrayList<>(); 
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs= pst.executeQuery();
            while(rs.next()){
                Funcionario f = new Funcionario();
                f.setFuncionario_cod(rs.getInt("funcionario_cod"));
                f.setNome(rs.getString("nome"));
                f.setTelefone(rs.getString("telefone"));
                f.setCpf(rs.getString("cpf"));
                f.setTipo(rs.getString("tipo"));
                
                lista.add(f);
            
            }
        
        
        }catch(Exception e){
            System.out.println("Erro de SQL: " +e.getMessage());
            
        }
        return lista;        
                
    }
    public Funcionario localizar(Integer id){
    
        String sql = "select * from funcionario where funcionario_cod="+ id;
        List<Funcionario> lista = new ArrayList<>(); 
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs= pst.executeQuery();
            while(rs.next()){
                Funcionario f = new Funcionario();
                f.setFuncionario_cod(rs.getInt("funcionario_cod"));
                f.setNome(rs.getString("nome"));
                f.setTelefone(rs.getString("telefone"));
                f.setCpf(rs.getString("cpf"));
                f.setTipo(rs.getString("tipo"));
                
                lista.add(f);
               
            
            }
        
        
        }catch(Exception e){
            System.out.println("Erro de SQL: " +e.getMessage());
            
        }
       return null;        
                
    }
    
}
