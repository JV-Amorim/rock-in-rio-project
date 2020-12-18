/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */

package br.edu.ifnmg.rockinrio.entity;

import java.time.LocalDate;

public class Espectador extends Pessoa{
    
    private int codigo;

    //<editor-fold defaultstate="collapsed" desc="CONSTRUTORES">

    public Espectador(){
        
    }
    public Espectador(int codigo) {
        this.codigo = codigo;
    }

    public Espectador(int codigo, String cpf, String nome, String tipoPessoa,
            LocalDate dataNascimento, Endereco endereco) {
        super(cpf, nome, tipoPessoa, dataNascimento, endereco);
        this.codigo = codigo;
    }
        
//</editor-fold>

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return super.getNome();
    }
    
    
}

