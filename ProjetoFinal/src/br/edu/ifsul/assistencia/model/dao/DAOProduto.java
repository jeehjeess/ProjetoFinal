/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.assistencia.model.dao;

import br.edu.ifsul.assistencia.model.Marca;
import br.edu.ifsul.assistencia.model.Modelo;
import br.edu.ifsul.assistencia.model.Ordem;
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
public class DAOProduto {

    // TERMINAR
    // INSERIR, ALTERAR , REMOVER, LOCALIZAR
    public boolean incluir(Produto obj) {
        String sql = "insert into produtos (motivo, n_serie, modelo, ordem) values (?,?,?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getMotivo());
            pst.setString(2, obj.getNumeroSerie());

            pst.setInt(3, obj.getModelo().getCodigoModelo());

            pst.setInt(4, obj.getOrdem().getOrdem_cod());

            if (pst.executeUpdate() > 0) {
                System.out.println("Produto incluido com sucesso");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }

    public boolean remover(Produto obj) {
        String sql = "delete from produtos where produto_cod = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodigoProduto());
            if (pst.executeUpdate() > 0) {
                System.out.println("Produto removido com sucesso");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }

    public Produto localizar(Integer id) {
        //revisar2008
        String sql = "select p.produto_cod,c.nome, p.motivo, p.n_serie, "
                + " ma.nome, m.nome,  pe.nome, pe.valor, o.pago"
                + " from produto as p, modelo as m, ordem as o, pecas as pe,marca as ma,"
                + " cliente as c where p.modelo= m.modelo_cod "
                + "and m.marca = ma.marca_cod"
                + " and p.ordem = o.ordem_cod and o.peca = pe.peca_cod and"
                + " c.produto=p.produto_cod and p.produto_codigo= " + id;
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setCodigoProduto(rs.getInt("produto_cod"));
                p.setNumeroSerie(rs.getString("n_serie"));
                p.setMotivo(rs.getString("motivo"));
                
                Ordem o = new Ordem(); 
                o.setPago(rs.getBoolean("pago"));
                Peca pe = new Peca();
                pe.setDescricaoPeca(rs.getString("nome"));
                pe.setValor(rs.getDouble("valor"));
                o.setPeca(pe);
                p.setOrdem(o);
                
                
                Modelo m = new Modelo();
                m.setDescricao(rs.getString("nome"));
                Marca ma = new Marca();
                ma.setDescricao(rs.getString("nome"));
                m.setMarca(ma);
                p.setModelo(m);
                
              
                  
                               
                               
            }
        } catch (Exception e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return null;

    }

    public List<Produto> listar() {
        String sql = "select p.produto_cod,c.nome, p.motivo, p.n_serie, "
                + " ma.nome, m.nome,  pe.nome, pe.valor, o.pago"
                + " from produto as p, modelo as m, ordem as o, pecas as pe,marca as ma,"
                + " cliente as c where p.modelo= m.modelo_cod "
                + "and m.marca = ma.marca_cod"
                + " and p.ordem = o.ordem_cod and o.peca = pe.peca_cod and"
                + " c.produto=p.produto_cod order by p.produto_cod asc"; 
        List<Produto> lista = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setCodigoProduto(rs.getInt("produto_cod"));
                p.setMotivo(rs.getString("motivo"));
                p.setNumeroSerie(rs.getString("n_serie"));
                
                Ordem o = new Ordem(); 
                o.setPago(rs.getBoolean("pago"));
                Peca pe = new Peca();
                pe.setDescricaoPeca(rs.getString("nome"));
                pe.setValor(rs.getDouble("valor"));
                o.setPeca(pe);
                p.setOrdem(o);
                
                
                Modelo m = new Modelo();
                m.setDescricao(rs.getString("nome"));
                Marca ma = new Marca();
                ma.setDescricao(rs.getString("nome"));
                m.setMarca(ma);
                p.setModelo(m);
                
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return lista;
    }

    public boolean atualizar(Produto obj) {
        String sql = "update produto set motivo = ?, n_serie= ?,"
                + " modelo=?, ordem=? where produto_cod = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getMotivo());
            pst.setString(2, obj.getNumeroSerie());
            pst.setInt(2, obj.getModelo().getCodigoModelo());
            pst.setInt(4, obj.getOrdem().getOrdem_cod());
            pst.setInt(5, obj.getCodigoProduto());

            if (pst.executeUpdate() > 0) {
                System.out.println("Produto atualizado com sucesso");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
}
