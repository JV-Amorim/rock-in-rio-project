/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */

package br.edu.ifnmg.rockinrio.dao;


import br.edu.ifnmg.rockinrio.entity.Lineup;
import br.edu.ifnmg.rockinrio.helper.LocalDateHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class LineupDao {

    public static ArrayList<Lineup> obterTodos() {

        ArrayList<Lineup> lineups;

        String sqlStatement = "SELECT datalineup FROM lineup";

        try ( PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)) {
            ResultSet resultSet = pstmt.executeQuery();
            lineups = gerarLineups(resultSet);
        } catch (Exception e) {
            return null;
        }

        return lineups;
    }

    public static ArrayList<Lineup> gerarLineups(ResultSet resultSet) {
        ArrayList<Lineup> lineups = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Lineup lineup;
                lineup = gerarLineup(resultSet);
                lineups.add(lineup);
            }
        } catch (SQLException e) {
            return null;
        }

        return lineups;
    }

    public static Lineup gerarLineup(ResultSet resultSet) throws SQLException {
        Lineup lineup = new Lineup(
                LocalDateHelper.sqlDateToLocalDate(resultSet.getDate("DATALINEUP"))
        );

        lineup.setBandas(gerarBandas(lineup.getDataLineup()));

        return lineup;
    }

    public static ArrayList<String> gerarBandas(LocalDate data) {

        ArrayList<String> bandas = new ArrayList<>();

        String sqlStatement = "SELECT * FROM banda WHERE datalineupshow = ? ORDER BY horainicioshow";

        try ( PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)) {
            pstmt.setDate(1, java.sql.Date.valueOf(data));
            ResultSet resultSet = pstmt.executeQuery();

            try {
                while (resultSet.next()) {
                    String banda;
                    banda = resultSet.getString("NOME")
                            + "  |  " + resultSet.getString("NOMELOCALSHOW")
                            + "  |  " + resultSet.getString("HORAINICIOSHOW");
                    bandas.add(banda);
                }
            } catch (SQLException e) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

        return bandas;
    }
}
