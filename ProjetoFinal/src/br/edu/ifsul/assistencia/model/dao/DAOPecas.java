/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.assistencia.model.dao;

import br.edu.ifsul.assistencia.model.Marca;
import br.edu.ifsul.assistencia.model.Modelo;
import br.edu.ifsul.assistencia.model.Peca;
import br.edu.ifsul.assistencia.model.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 20181210031
 */
public class DAOPecas {
    // INSERIR, ALTERAR , REMOVER, LISTAR, VERIFICAR ESTOQUE
    public boolean inserir(Peca obj){
        String sql = "insert into pecas (nome, modelo, estoque, valor) values (?,?,?,?)";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getDescricaoPeca());
            pst.setInt(2, obj.getModelo().getCodigoModelo());
            pst.setInt(3, obj.getEstoque());
            pst.setDouble(4, obj.getValor());
            
            if(pst.executeUpdate()>0){
                System.out.println("Peça incluída com sucesso");
                return true;
            }else{
                return false;
            }
            
                    
        }catch (SQLException e){
            System.out.println("Erro de SQL: "+ e.getMessage());
            return false;
        }
    }
    
    public boolean remover(Peca obj){
        String sql = "delete from pecas where peca_cod=?";
    try{
    PreparedStatement pst = Conexao.getPreparedStatement(sql);
    pst.setInt(1, obj.getCodigoPeca());
    if (pst.executeUpdate()> 0){
        System.out.println("Peca removida com sucesso");
        return true;
    }else{
        return false;
    }
}catch (SQLException e){
            System.out.println("Erro de SQL: "+ e.getMessage());
            return false;
}
    }
    
    public List<Peca> listar(){
        
        String sql = "select mo.modelo_cod, mo.nome, mo.marca, ma.nome from modelo as mo, marca as ma\n" +
"where mo.marca = ma.marca_cod order by mo.modelo_cod asc;";
        List<Peca> lista = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                Peca obj = new Peca();
                obj.setCodigoPeca(rs.getInt("peca_cod"));
                obj.setDescricaoPeca(rs.getString("nome"));
                ///??
                Produto p = new Produto();
                p.setNumeroSerie(rs.getString("n_serie"));
                
                Modelo m = new Modelo();
                m.setDescricao(rs.getString("nome"));
                Marca ma = new Marca();
                ma.setDescricao(rs.getString("nome"));
                m.setMarca(ma);
                obj.setModelo(m);
                obj.setEstoque(rs.getInt("estoque"));
                obj.setValor(rs.getDouble("valor"));
                
                lista.add(obj);
            }
        }catch (Exception e){
            System.out.println("Erro de SQL: "+ e.getMessage());
    
}
    return lista;
}
    
    public boolean alterar(Peca obj){
        String sql = "update pecas set nome = ?, modelo=?, estoque=?, valor=? where peca_cod=?";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getDescricaoPeca());
            pst.setInt(2, obj.getModelo().getCodigoModelo());
                        
            pst.setInt(3, obj.getEstoque());
            pst.setDouble(4, obj.getValor());
            pst.setInt(5, obj.getCodigoPeca());
            
            if(pst.executeUpdate() > 0 ){
                System.out.println("Peça atualizada com sucesso!!");
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            System.out.println("Erro de SQL: "+ e.getMessage());
            return false;
        }
    }
    
     public Peca localizar(Integer id){
        
        String sql = "select mo.modelo_cod, mo.nome, mo.marca, ma.nome from modelo as mo, marca as ma\n" +
"where mo.marca = ma.marca_cod where mo.modelo_cod ="+id;
     
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                Peca obj = new Peca();
                obj.setCodigoPeca(rs.getInt("peca_cod"));
                obj.setDescricaoPeca(rs.getString("nome"));
               
                Produto p = new Produto();
                p.setNumeroSerie(rs.getString("n_serie"));
                
                Modelo m = new Modelo();
                m.setDescricao(rs.getString("nome"));
                Marca ma = new Marca();
                ma.setDescricao(rs.getString("nome"));
                m.setMarca(ma);
                obj.setModelo(m);
                obj.setEstoque(rs.getInt("estoque"));
                obj.setValor(rs.getDouble("valor"));
                
                
            }
        }catch (Exception e){
            System.out.println("Erro de SQL: "+ e.getMessage());
    
}
    return null;
}
    
}
