/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.helper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class LocalDateHelper {
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    private LocalDateHelper() { }
    //</editor-fold>
    
    public static LocalDate sqlDateToLocalDate(java.sql.Date date) {
        LocalDate localDate = 
            new Date(date.getTime())
            .toInstant().atZone(ZoneId.systemDefault())
            .toLocalDate();
        return localDate;
    }
    
    public static java.sql.Date localDateToSqlDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }
}
