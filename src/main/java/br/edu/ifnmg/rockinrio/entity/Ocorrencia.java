/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.entity;

import java.util.Date;

public class Ocorrencia {
    private int numero; // Auto Generated PK
    private final String cpfProfissionalSeguranca;
    private final String cpfPessoa;
    private final Date dataHora;
    private final String descricao;
    private final Double latitude;
    private final Double longitude;
    private String nomePessoa;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public Ocorrencia(
        int numero,
        String cpfProfissionalSeguranca,
        String cpfPessoa,
        Date dataHora,
        String descricao,
        Double latitude,
        Double longitude
    ) {
        this.numero = numero;
        this.cpfProfissionalSeguranca = cpfProfissionalSeguranca;
        this.cpfPessoa = cpfPessoa;
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Ocorrencia(
        String cpfProfissionalSeguranca,
        String cpfPessoa,
        Date dataHora,
        String descricao,
        Double latitude,
        Double longitude
    ) {
        this.cpfProfissionalSeguranca = cpfProfissionalSeguranca;
        this.cpfPessoa = cpfPessoa;
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters">
    
    public int getNumero() {
        return numero;
    }

    public String getCpfProfissionalSeguranca() {
        return cpfProfissionalSeguranca;
    }

    public String getCpfPessoa() {
        return cpfPessoa;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters">
    
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }
    
    //</editor-fold>

    @Override
    public String toString() {
        String brDate = dataHora.toString();
        brDate =
            brDate.substring(8, 10) + "/" + 
            brDate.substring(5, 7) + "/" +
            brDate.substring(0, 4);
                
        return "Nº " + numero + " | " + nomePessoa + " | " + brDate;
    }
}
