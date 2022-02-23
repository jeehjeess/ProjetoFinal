/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.assistencia.model.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;


/**
 *
 * @author 20181210031
 */

//No exemplo do Telmo ele joga todos os metodos nessa classe.
public class Conexao {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String USER = "postgres";
    //Atencao na senha
    private static final String SENHA = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/projeto";
    private static Connection con = null;
    
    public Conexao(){    
}

    
    //abre c
    public static Connection getConexao(){
        if(con == null){
            try{
                Class.forName(DRIVER); 
                con = DriverManager.getConnection(URL, USER, SENHA );
                
                
            }catch(ClassNotFoundException ex){
                System.out.println("Não encontrou o driver: " +ex.getMessage());
            }catch (SQLException ex){
                System.out.println("Erro na conexão: "+ ex.getMessage());
            }
        }
        return con;
    }
    
    public String abreConexao(){
    
        String erro = null;        
        try{
            if(this.con != null &&   !this.con.isClosed()    ){
                fechaConexao();
            }
            Class.forName(DRIVER); 
            //tentar conectar no banco de dados
            this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA);                                               
        }catch(ClassNotFoundException | SQLException e){
            //se nao conseguir conectar no banco de dados, imprimi na saida o erro
            System.err.print(e);
            erro = e.getLocalizedMessage();            
        }
        return erro;//retorna a variavel, se estiver null a conexao foi estabelecida com sucesso
    }
    
    public String fechaConexao(){
    
        String erro = null;    
        try{
            //tenta fechar a conexao                   
            this.con.close();
            
        }catch(SQLException e){
            
            System.err.print(e);
            erro = e.getLocalizedMessage();            
        }
        return erro;
    }
    
    public static PreparedStatement getPreparedStatement(String sql ){
        if( con == null ){
            con = getConexao(); 
            
        }
        try{
            return con.prepareStatement(sql); 
            
        }catch (SQLException ex){
            System.out.println("Erro de SQL:" +ex.getMessage());
        }
        return null;
    }
    
    
    

    
}
