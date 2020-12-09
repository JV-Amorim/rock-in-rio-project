/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.entity;

public class Endereco {
    private final String cep;
    private final String bairro;
    private final String rua;
    private final Integer numero;

    public Endereco(String cep, String bairro, String rua, Integer numero) {
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters">
    
    public String getCep() {
        return cep;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public Integer getNumero() {
        return numero;
    }
    
    //</editor-fold>
}
