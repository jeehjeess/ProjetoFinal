/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.assistencia.model.dao;

import br.edu.ifsul.assistencia.model.Marca;
import br.edu.ifsul.assistencia.model.Modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 20181210031
 */
public class DAOModelo {

    // INSERIR, ALTERAR , REMOVER, LISTAR; 
    public boolean inserir(Modelo obj) {
        String sql = " insert into modelo ( nome, marca) values"
                + "(?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getDescricao());
            pst.setInt(2, obj.getMarca().getCodigoMarca());

            //executa update para mostrar as linhas alteradas
            if (pst.executeUpdate() > 0) {
                System.out.println("Modelo inserido com sucesso!!!");
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

    public boolean alterar(Modelo obj) {
        String sql = "update modelo set nome =? , marca = ? where codigo = ?";

        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getDescricao());
            pst.setInt(2, obj.getMarca().getCodigoMarca());
            pst.setInt(3, obj.getCodigoModelo());

            //executa update para mostrar as linhas alteradas
            if (pst.executeUpdate() > 0) {
                System.out.println("Modelo alterado com sucesso!!!");
                return true;
            } else {
                //nada aconteceu feijoada, tem que retornar falso. Não alterou nenhuma linha
                return false;
            }

        } catch (Exception e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public boolean remover (Modelo obj){
        String sql = "delete * from modelo where codigo = ?";
        
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodigoModelo());
            
            //executa update para mostrar as linhas alteradas

            if (pst.executeUpdate() > 0) {
                System.out.println("Modelo  removido com sucesso!!!");
                return true;
            } else {
                //nada aconteceu feijoada, tem que retornar falso. Não alterou nenhuma linha
                return false;
            }
                  
        
        
        }catch(Exception  e ){
            System.out.println("Erro de SQL: " +e.getMessage());
            return false;
        }
    }
    
    public List<Modelo> listar(){
        String sql ="select m.modelo_cod, m.nome, ma.nome as marca from modelo as m, marca as ma " +
"where m.marca=ma.marca_cod";
        
        List<Modelo> lista = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery(); 
            
            while(rs.next()){
                Modelo m = new Modelo(); 
                m.setCodigoModelo(rs.getInt("modelo_cod"));
                m.setDescricao(rs.getString("nome"));
                Marca ma = new Marca();
                ma.setDescricao(rs.getString("marca"));
                m.setMarca(ma);
                lista.add(m);
                
            }
            
            
        }catch(Exception e){
            System.out.println("Erro SQL: " +e.getMessage());
        }
        return lista;
    }

}
