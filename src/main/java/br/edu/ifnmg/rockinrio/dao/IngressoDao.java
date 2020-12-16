/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.dao;

import br.edu.ifnmg.rockinrio.entity.Ingresso;
import br.edu.ifnmg.rockinrio.helper.LocalDateHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class IngressoDao {

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    private IngressoDao() {
    }
    //</editor-fold>

    public static Boolean inserir(Ingresso ingresso) {
        var connection = DatabaseManager.getConnection();

        String sqlStatement = "INSERT INTO ingresso(numero, valor, "
                + "datalineupentrada, horaentrada, cpfespectador, pertencedatalineup) "
                + "VALUES(?, ?, ?, ?, ?, ?);";

        try ( PreparedStatement pstmt = connection.prepareStatement(sqlStatement)) {
            pstmt.setInt(1, ingresso.getNumero());
            pstmt.setInt(2, ingresso.getValor());
            pstmt.setDate(3, java.sql.Date.valueOf(ingresso.getDataLineupEntrada()));
            pstmt.setString(4, ingresso.getHoraEntrada());
            pstmt.setString(5, ingresso.getCpfEspectador());
            pstmt.setDate(6, java.sql.Date.valueOf(ingresso.getPertenceDataLineup()));
            pstmt.executeUpdate();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static Boolean atualizar(Ingresso ingresso) {
        var connection = DatabaseManager.getConnection();

        String sqlStatement
                = "UPDATE ingresso set numero=?, valor=?, datalineupentrada=?, "
                + "horaentrada=?, cpfespectador=?, pertencedatalineup=? WHERE numero = ?;";

        try ( PreparedStatement pstmt = connection.prepareStatement(sqlStatement)) {
            pstmt.setInt(1, ingresso.getNumero());
            pstmt.setInt(2, ingresso.getValor());
            pstmt.setDate(3, java.sql.Date.valueOf(ingresso.getDataLineupEntrada()));
            pstmt.setString(4, ingresso.getHoraEntrada());
            pstmt.setString(5, ingresso.getCpfEspectador());
            pstmt.setDate(6, java.sql.Date.valueOf(ingresso.getPertenceDataLineup()));
            pstmt.setInt(7, ingresso.getNumero());
            pstmt.executeUpdate();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static ArrayList<Ingresso> obterTodos() {
        ArrayList<Ingresso> ingressos = new ArrayList<>();

        String sqlStatement = "SELECT nome, ingresso.numero as id, valor,"
                + " datalineupentrada, horaentrada, cpfespectador,"
                + " pertencedatalineup FROM ingresso , pessoa p"
                + " WHERE cpfespectador = cpf order by id";

        try ( PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)) {
            ResultSet resultSet = pstmt.executeQuery();
            ingressos = gerarObjetos(resultSet);
        } catch (Exception e) {
            // TODO - Exibir mensagem de erro.
            e.printStackTrace();
        }

        return ingressos;
    }

    public static Boolean excluir(Ingresso ingresso) {
        var connection = DatabaseManager.getConnection();

        String sqlStatement = "DELETE FROM ingresso WHERE numero=?";

        try ( PreparedStatement pstmt = connection.prepareStatement(sqlStatement)) {
            pstmt.setInt(1, ingresso.getNumero());
            pstmt.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static int obterProximoNumeroIngresso() {
        String sqlStatement = "SELECT MAX(numero) FROM ingresso";

        int numero = 0;

        try ( PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)) {
            ResultSet resultSet = pstmt.executeQuery();
            numero = resultSet.getInt("numero");
        } catch (Exception e) {
            // TODO - Exibir mensagem de erro.
            e.printStackTrace();
        }
        return numero + 1;
    }

    private static ArrayList<Ingresso> gerarObjetos(ResultSet resultSet) {
        ArrayList<Ingresso> ingressos = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Ingresso o = new Ingresso(
                        resultSet.getInt("ID"),
                        resultSet.getInt("VALOR"),
                        LocalDateHelper.sqlDateToLocalDate(resultSet.getDate("DATALINEUPENTRADA")),
                        resultSet.getString("HORAENTRADA"),
                        resultSet.getString("CPFESPECTADOR"),
                        LocalDateHelper.sqlDateToLocalDate(resultSet.getDate("PERTENCEDATALINEUP"))
                );
                o.setNomeEspectador(resultSet.getString("NOME"));
                ingressos.add(o);
            }
        } catch (SQLException e) {
            // TODO - Exibir mensagem de erro.
            e.printStackTrace();
        }

        return ingressos;
    }
}
