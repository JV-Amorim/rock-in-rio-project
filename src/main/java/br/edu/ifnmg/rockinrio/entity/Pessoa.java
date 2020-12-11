/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.entity;

import java.util.Date;

public class Pessoa {
    private final String cpf;
    private final String nome;
    private final Date dataNascimento;
    private final Endereco endereco;

    public Pessoa(String cpf, String nome, Date dataNascimento, Endereco endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }
    
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
}
