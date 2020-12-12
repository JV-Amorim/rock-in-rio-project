/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.entity;

import java.util.Date;

public class Pessoa {
    private String cpf;
    private String nome;
    private Date dataNascimento;
    private Endereco endereco;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public Pessoa() { }
    
    public Pessoa(String cpf, String nome, Date dataNascimento, Endereco endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters">

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setters">

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    //</editor-fold>
}
