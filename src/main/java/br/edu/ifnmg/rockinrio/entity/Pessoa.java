/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.entity;

import java.time.LocalDate;

public class Pessoa {
    private String cpf;
    private String nome;
    private String tipoPessoa;
    private LocalDate dataNascimento;
    private Endereco endereco;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public Pessoa() { }
    
    public Pessoa(String cpf, String nome, String tipoPessoa, LocalDate dataNascimento, Endereco endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.tipoPessoa = tipoPessoa;
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
    
    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getDataNascimentoEmString(boolean formatoBR) {
        if (formatoBR) {
            String brDate = dataNascimento.toString();
            brDate =
                brDate.substring(8, 10) + "/" + 
                brDate.substring(5, 7) + "/" +
                brDate.substring(0, 4);
            return brDate;
        }
        else {
            return getDataNascimento().toString();
        }
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
    
    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }
    
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    //</editor-fold>
    
}
