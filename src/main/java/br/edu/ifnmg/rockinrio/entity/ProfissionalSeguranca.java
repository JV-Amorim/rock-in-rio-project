/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.entity;

import java.util.Date;

public class ProfissionalSeguranca extends Pessoa {
    private final Long numeroCredencial;

    public ProfissionalSeguranca(Long numeroCredencial, String cpf, String nome, Date dataNascimento, Endereco endereco) {
        super(cpf, nome, dataNascimento, endereco);
        this.numeroCredencial = numeroCredencial;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters">
    
    public Long getNumeroCredencial() {
        return numeroCredencial;
    }
    
    //</editor-fold>
}
