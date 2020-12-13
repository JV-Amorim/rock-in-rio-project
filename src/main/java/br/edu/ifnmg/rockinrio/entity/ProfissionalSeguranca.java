/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.entity;

import java.time.LocalDate;

public class ProfissionalSeguranca extends Pessoa {
    private final Long numeroCredencial;

    public ProfissionalSeguranca(Long numeroCredencial, String cpf, String nome, LocalDate dataNascimento, Endereco endereco) {
        super(cpf, nome, "Funcionario", dataNascimento, endereco);
        this.numeroCredencial = numeroCredencial;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters">
    
    public Long getNumeroCredencial() {
        return numeroCredencial;
    }
    
    //</editor-fold>
}
