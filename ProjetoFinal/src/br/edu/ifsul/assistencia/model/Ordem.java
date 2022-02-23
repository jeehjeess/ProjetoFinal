/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.assistencia.model;

import java.util.Date;

/**
 *
 * @author 20181210031
 */
public class Ordem {
    
    private Integer ordem_cod;
    private String motivo;
    private Peca peca;
    private Date data_inicial; 
    private Date data_final; 
    private Float valor; 
    private Boolean pago; 
    private Funcionario funcionario; 
    

    public Integer getOrdem_cod() {
        return ordem_cod;
    }

    public void setOrdem_cod(Integer ordem_cod) {
        this.ordem_cod = ordem_cod;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public Date getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(Date data_inicial) {
        this.data_inicial = data_inicial;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    
    
    
    
    
}
