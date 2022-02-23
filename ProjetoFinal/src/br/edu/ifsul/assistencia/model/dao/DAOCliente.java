/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.assistencia.model.dao;

import br.edu.ifsul.assistencia.model.Cliente;
import br.edu.ifsul.assistencia.model.Marca;
import br.edu.ifsul.assistencia.model.Modelo;
import br.edu.ifsul.assistencia.model.Ordem;
import br.edu.ifsul.assistencia.model.Peca;
import br.edu.ifsul.assistencia.model.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 20181210031
 */
public class DAOCliente {

    // INSERIR, ALTERAR , REMOVER, LOCALIZAR, LISTAR
    public boolean inserir(Cliente obj) {
        String sql = " insert into cliente (nome, endereco, telefone, cpf, produto) values"
                + "(?,?,?,?,?) ";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getEndereco());
            pst.setString(3, obj.getTelefone());
            pst.setString(4, obj.getCpf());
            pst.setInt(5, obj.getProduto().getCodigoProduto());
            //executa update para mostrar as linhas alteradas

            if (pst.executeUpdate() > 0) {
                System.out.println("Cliente inserido com sucesso!!!");
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

    public boolean alterar(Cliente obj) {
        String sql = " update cliente set nome = ? , endereco =? , telefone =? , cpf = ?, produto =? "
                + "where cliente_cod = ? ";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getEndereco());
            pst.setString(3, obj.getTelefone());
            pst.setString(4, obj.getCpf());
            pst.setInt(5, obj.getProduto().getCodigoProduto());
            pst.setInt(6, obj.getCodigoCliente());
            //executa update para mostrar as linhas alteradas

            if (pst.executeUpdate() > 0) {
                System.out.println("Cliente alterado com sucesso!!!");
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

    public boolean remover(int id) {
        String sql = " delete  from cliente where cliente_cod =?  ";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            //executa update para mostrar as linhas alteradas

            if (pst.executeUpdate() > 0) {
                System.out.println("Cliente removido com sucesso!!!");
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

    public List<Cliente> listar() {

        String sql = "select distinct c.cliente_cod, c.nome, c.cpf, c.telefone, \n" +
"                o.ordem_cod,o.pago, o. valor ,p.n_serie,m.nome as modelo,   ma.nome  as marca \n" +
"                from cliente as c, produto as p,\n" +
"                modelo as m, marca as ma, ordem as o where\n" +
"                p.produto_cod = c.produto and m.modelo_cod = p.modelo and      \n" +
"				ma.marca_cod = m.marca and o.ordem_cod = p.ordem  \n" +
"                 order by c.cliente_cod asc";
        List<Cliente> lista = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setCodigoCliente(rs.getInt("cliente_cod"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));
                
                
                Ordem o = new Ordem();
                o.setOrdem_cod(rs.getInt("ordem_cod"));
                o.setPago(rs.getBoolean("pago"));
                o.setValor(rs.getFloat("valor"));
                
                Produto p = new Produto();
                p.setNumeroSerie(rs.getString("n_serie"));
                p.setOrdem(o);
                
                Modelo m = new Modelo();
                m.setDescricao(rs.getString("modelo"));
                
                Marca ma = new Marca();
                ma.setDescricao(rs.getString("marca"));
                m.setMarca(ma);
                p.setModelo(m);
                c.setProduto(p);
                

                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return lista;

    }

    public Cliente localizar(Integer id) {

        String sql = "select distinct c.cliente_cod, c.nome, c.cpf, c.telefone, " +
"                 o.ordem_cod,o.pago ,p.n_serie,m.nome as modelo,   ma.nome  as marca " +
"                  from cliente as c, produto as p," +
"                 modelo as m, marca as ma, ordem as o where" +
"                 p.produto_cod = c.produto and m.modelo_cod = p.modelo and" +
"                ma.marca_cod = m.marca and o.ordem_cod = p.ordem  " +
"                  where cliente_cod = "+ id;

        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setCodigoCliente(rs.getInt("cliente_cod"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));
                
                
                Ordem o = new Ordem();
                o.setOrdem_cod(rs.getInt("ordem_cod"));
                o.setPago(rs.getBoolean("pago"));
                
                Produto p = new Produto();
                p.setNumeroSerie(rs.getString("n_serie"));
                p.setOrdem(o);
                
                Modelo m = new Modelo();
                m.setDescricao(rs.getString("modelo"));
                
                Marca ma = new Marca();
                ma.setDescricao(rs.getString("marca"));
                m.setMarca(ma);
                p.setModelo(m);
                c.setProduto(p);
            }
        } catch (Exception e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return null;

    }

}
