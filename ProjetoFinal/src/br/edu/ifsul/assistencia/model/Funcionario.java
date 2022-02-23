/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.assistencia.model;

/**
 *
 * @author Jéssica
 */
public class Funcionario {
    private Integer funcionario_cod;
    private String nome; 
    private String telefone; 
    private String cpf; 
    private String tipo;

    public Integer getFuncionario_cod() {
        return funcionario_cod;
    }

    public void setFuncionario_cod(Integer funcionario_cod) {
        this.funcionario_cod = funcionario_cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
