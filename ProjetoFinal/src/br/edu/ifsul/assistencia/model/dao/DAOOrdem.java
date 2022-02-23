/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.assistencia.model.dao;

import br.edu.ifsul.assistencia.model.Funcionario;
import br.edu.ifsul.assistencia.model.Marca;
import br.edu.ifsul.assistencia.model.Modelo;
import br.edu.ifsul.assistencia.model.Ordem;
import br.edu.ifsul.assistencia.model.Peca;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 20181210031
 */
public class DAOOrdem {
    // sem localizar ?
    // INSERIR, ALTERAR , REMOVER, LISTAR, LOCALIZAR
    public boolean incluir(Ordem obj){
        //Arrumar
        String sql = " insert into ordem (motivo, peca) values (?,?)";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getMotivo());
            pst.setInt(2, obj.getPeca().getCodigoPeca());
            
            if(pst.executeUpdate()>0){
                System.out.println("Ordem incluida com sucesso");
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            System.out.println("Erro de SQL: "+ e.getMessage());
            return false;
        }   
}
    
    public boolean alterar(Ordem obj){
        String sql = "update ordem set motivo=?, peca=? where ordem_cod =?)";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getMotivo());
            //??
            pst.setInt(2, obj.getPeca().getCodigoPeca());
            pst.setInt(3, obj.getOrdem_cod());
            
            if(pst.executeUpdate()>0){
                System.out.println("Ordem atualizada com sucesso");
                return true;
            }else{
                return false;
            }
            }catch (Exception e){
                    System.out.println("Erro SQL: "+e.getMessage());
                    return false;
                    }
        }
    
    public boolean remover(Ordem obj){
        String sql = "delete * from ordem where ordem_cod=?";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getOrdem_cod());
            
            if(pst.executeUpdate()> 0){
                System.out.println("Ordem removida com sucesso");
                return true;
            }else{
                return false;
            }
                    
        }catch(Exception e){
            System.out.println("Erro SQL: "+ e.getMessage());
            return false;
        }
        
        
    }
    
    public List<Ordem> listar(){
        
        String sql = "select o.ordem_cod, o.motivo, pe.nome as peca, mo.nome as modelo, \n" +
"  ma.nome as marca, o.data_inicial, o.data_final, o.valor, o.pago, f.nome as funcionario from\n" +
"  ordem as o,pecas as pe, modelo as mo, marca as ma, funcionario as f\n" +
"  where o.peca = pe.peca_cod and pe.modelo = mo.modelo_cod and o.funcionario = f.funcionario_cod\n" +
"and mo.marca = ma.marca_cod order by o.ordem_cod asc" ;
        List<Ordem> lista = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Ordem o = new Ordem();
                o.setOrdem_cod(rs.getInt("ordem_cod"));
                o.setMotivo(rs.getString("motivo"));
                Peca p = new Peca();
                p.setDescricaoPeca(rs.getString("peca"));
                
                Modelo mo = new Modelo();
                mo.setDescricao(rs.getString("modelo"));
                
                Marca m = new Marca();
                m.setDescricao(rs.getString("marca"));
                mo.setMarca(m);
                
                p.setModelo(mo);
                
                o.setPeca(p);
                
                o.setData_inicial(rs.getDate("data_inicial"));
                o.setData_final(rs.getDate("data_final"));
                o.setValor(rs.getFloat("valor"));
                o.setPago(rs.getBoolean("pago"));
                
                Funcionario f = new Funcionario();
                f.setNome(rs.getString("funcionario"));
                
                o.setFuncionario(f);
                
                lista.add(o);
            }
        }catch (Exception e){
            System.out.println("Erro de SQL: "+ e.getMessage());
        }
        return lista;
    }
      public Ordem localizar(Integer id){
        String sql = "select * from ordem where ordem_cod = " +id;
        List<Ordem> lista = new ArrayList<>();
        try{
            PreparedStatement pst= Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs= pst.executeQuery();
            while(rs.next()){
                Ordem o = new Ordem();
                o.setOrdem_cod(rs.getInt("ordem_cod"));
                o.setMotivo(rs.getString("motivo"));
                o.setPago(rs.getBoolean("pago"));
                o.setValor(rs.getFloat("valor"));
                
                Peca p = new Peca();
                p.setCodigoPeca(rs.getInt("peca_cod"));
                o.setPeca(p);
                
                Funcionario f = new Funcionario();
                f.setFuncionario_cod(rs.getInt("funcionario_cod"));
                o.setFuncionario(f);
                
                o.setData_final(rs.getDate("data_final"));
                o.setData_inicial(rs.getDate("data_inicial"));
                
            }
        
            
        }catch(Exception e ){
            System.out.println("Erro de SQL: " +e.getMessage());
        }
        return null;
    }
    
    
            
            
            
        
    
}
