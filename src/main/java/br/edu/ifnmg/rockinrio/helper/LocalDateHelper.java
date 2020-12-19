/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.helper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Classe para conversão de java.sql.Date para LocalDate e vice-versa.
 */
public final class LocalDateHelper {
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    private LocalDateHelper() { }
    //</editor-fold>
    
    /**
     * Converte java.sql.Date para LocalDate.
     * @param date Objeto java.sql.Date a ser convertido.
     * @return Objeto LocalDate gerado.
     */
    public static LocalDate sqlDateToLocalDate(java.sql.Date date) {
        LocalDate localDate = 
            new Date(date.getTime())
            .toInstant().atZone(ZoneId.systemDefault())
            .toLocalDate();
        return localDate;
    }
    
    /**
     * Converte LocalDate para java.sql.Date.
     * @param date Objeto LocalDate a ser convertido.
     * @return Objeto java.sql.Date gerado.
     */
    public static java.sql.Date localDateToSqlDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }
}
