/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */

package br.edu.ifnmg.rockinrio.entity;

import java.time.LocalDate;

public class Ingresso {

    private int numero;
    private int valor;
    private LocalDate dataLineupEntrada;
    private String horaEntrada;
    private String cpfEspectador;
    private LocalDate pertenceDataLineup;
    private String nomeEspectador;

    //<editor-fold defaultstate="collapsed" desc="CONSTRUTORES">
    

    public Ingresso(){
        
    }
    
    public Ingresso(int numero, int valor, LocalDate dataLineupEntrada,
            String horaEntrada, String cpfEspectador, LocalDate pertenceDataLineup) {
        this.numero = numero;
        this.valor = valor;
        this.dataLineupEntrada = dataLineupEntrada;
        this.horaEntrada = horaEntrada;
        this.cpfEspectador = cpfEspectador;
        this.pertenceDataLineup = pertenceDataLineup;
    }
    
    //</editor-fold>   
    
    //<editor-fold defaultstate="collapsed" desc="GETTERS/SETTERS">

    public String getNomeEspectador() {
        return nomeEspectador;
    }

    public void setNomeEspectador(String nomeEspectador) {
        this.nomeEspectador = nomeEspectador;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public LocalDate getDataLineupEntrada() {
        return dataLineupEntrada;
    }

    public void setDataLineupEntrada(LocalDate datalineupentrada) {
        this.dataLineupEntrada = datalineupentrada;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaentrada) {
        this.horaEntrada = horaentrada;
    }

    public String getCpfEspectador() {
        return cpfEspectador;
    }

    public void setCpfespEctador(String cpfespectador) {
        this.cpfEspectador = cpfespectador;
    }

    public LocalDate getPertenceDataLineup() {
        return pertenceDataLineup;
    }

    public void setPertenceDataLineup(LocalDate pertencedatalineup) {
        this.pertenceDataLineup = pertencedatalineup;
    }
    
    //</editor-fold>

    @Override
    public String toString() {
        return numero +"          |          "+ nomeEspectador +"          |          "+ pertenceDataLineup;
    }
}
