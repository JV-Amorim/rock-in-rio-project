/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.entity;

import java.time.LocalDate;
import java.util.Date;

public class Ocorrencia {
    private int numero; // Auto Generated PK
    private String cpfProfissionalSeguranca;
    private String cpfPessoa;
    private LocalDate dataOcorrencia;
    private String descricao;
    private Double latitude;
    private Double longitude;
    private String nomePessoa;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public Ocorrencia() { }
    
    public Ocorrencia(
        int numero,
        String cpfProfissionalSeguranca,
        String cpfPessoa,
        LocalDate dataOcorrencia,
        String descricao,
        Double latitude,
        Double longitude
    ) {
        this.numero = numero;
        this.cpfProfissionalSeguranca = cpfProfissionalSeguranca;
        this.cpfPessoa = cpfPessoa;
        this.dataOcorrencia = dataOcorrencia;
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

    public LocalDate getDataOcorrencia() {
        return dataOcorrencia;
    }

    public String getDataOcorrenciaEmString(boolean formatoBR) {
        if (formatoBR) {
            String brDate = dataOcorrencia.toString();
            brDate =
                brDate.substring(8, 10) + "/" + 
                brDate.substring(5, 7) + "/" +
                brDate.substring(0, 4);
            return brDate;
        }
        else {
            return getDataOcorrencia().toString();
        }
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
    
    public void setCpfProfissionalSeguranca(String cpfProfissionalSeguranca) {
        this.cpfProfissionalSeguranca = cpfProfissionalSeguranca;
    }

    public void setCpfPessoa(String cpfPessoa) {
        this.cpfPessoa = cpfPessoa;
    }

    public void setDataOcorrencia(LocalDate dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public void setLongitude(Double longitude) {    
        this.longitude = longitude;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }
    
    //</editor-fold>
    
    @Override
    public String toString() {
        return "Nº " + numero + " | " + nomePessoa + " | " + getDataOcorrenciaEmString(true);
    }
}
