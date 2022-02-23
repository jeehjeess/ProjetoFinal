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
public class Marca {
    
    private Integer codigoMarca;
    private String descricao; 
    
    public Marca(){
    
    }

    public Integer getCodigoMarca() {
        return codigoMarca;
    }

    public void setCodigo_marca(Integer codigoMarca) {
        this.codigoMarca = codigoMarca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
