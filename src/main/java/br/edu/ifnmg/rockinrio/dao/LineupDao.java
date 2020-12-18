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

    /**
     * Cria uma lista de instâncias do tipo especificado no ArrayList através
     * de uma Consulta no Banco de dados e a retorna como resultado
     * @sqlStatement String que representa a linha de comando no banco de dados
     * @return Lista de objetos prontos do tipo especificado no ArrayList
     */
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

    /**
     * Recebe o resultado da consulta do banco de dados e cria um comando de
     * repetição para separar esse resultado em linhas e, para cada linha,
     * gera um objeto do tipo especificado e adiciona na lista para ser retornada
     * @param resultSet Resultado inteiro da consulta no banco de dados
     * @return 
     */
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

    /**
     * Recebe o resultado da consulta no banco de dados linha por linha e cria
     * uma instância da classe de retorno através de seus atributos obtidos.
     * @param resultSet Parte da consulta do banco de dados (uma linha)
     * @return Objeto criado e preenchido.
     * @throws SQLException 
     */
    public static Lineup gerarLineup(ResultSet resultSet) throws SQLException {
        Lineup lineup = new Lineup(
                LocalDateHelper.sqlDateToLocalDate(resultSet.getDate("DATALINEUP"))
        );

        lineup.setBandas(gerarBandas(lineup.getDataLineup()));

        return lineup;
    }

    /**
     * Método que busca todas as informações de relacionamento entre banda e 
     * Lineup, que são armazenados em uma ArrayList do tipo String que armazena
     * em cada String, as informações de Banda & Lineup
     * @param data Atributo identificador de Lineup, usado para criar a consulta
     * @return 
     */
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
