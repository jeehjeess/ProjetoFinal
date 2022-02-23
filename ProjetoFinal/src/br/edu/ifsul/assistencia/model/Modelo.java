/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.assistencia.model;

/**
 *
 * @author 20181210031
 */
public class Modelo {
    
    private Integer codigoModelo;
    private String descricao;
    private Marca marca; 
    
    public Modelo(){
    
    }

    public Integer getCodigoModelo() {
        return codigoModelo;
    }

    public void setCodigoModelo(Integer codigoModelo) {
        this.codigoModelo = codigoModelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}
