/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */

package br.edu.ifnmg.rockinrio.entity;

import java.time.LocalDate;
import java.util.ArrayList;


public class Lineup {

    private final LocalDate dataLineup;
    private int capacidade;
    ArrayList<String> bandas = new ArrayList<>();

    //<editor-fold defaultstate="collapsed" desc="CONSTRUTORES">
    

    public Lineup(LocalDate dataLineup){
        this.dataLineup = dataLineup;
    }
    
    public Lineup(LocalDate dataLineup, ArrayList<String> bandas){
        this.dataLineup = dataLineup;
        this.bandas = bandas;
    }

    public Lineup(LocalDate datalineup, int capacidade) {
        this.dataLineup = datalineup;
        this.capacidade = capacidade;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GETTERS/SETTERS">
    
    public LocalDate getDataLineup() {
        return dataLineup;
    }
    
    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public ArrayList<String> getBandas() {
        return bandas;
    }

    public void setBandas(ArrayList<String> bandas) {
        this.bandas = bandas;
    }   
    
    //</editor-fold>
    
}
