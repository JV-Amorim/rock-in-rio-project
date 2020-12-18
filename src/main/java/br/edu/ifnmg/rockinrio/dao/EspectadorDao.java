/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */

package br.edu.ifnmg.rockinrio.dao;


import br.edu.ifnmg.rockinrio.entity.Endereco;
import br.edu.ifnmg.rockinrio.entity.Espectador;
import br.edu.ifnmg.rockinrio.helper.LocalDateHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EspectadorDao {

    public static boolean inserir(Espectador espectador) {

        String sqlStatement = "INSERT INTO pessoa VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)) {
            pstmt.setString(1, espectador.getCpf());
            pstmt.setString(2, espectador.getTipoPessoa());
            pstmt.setDate(3, LocalDateHelper.localDateToSqlDate(espectador.getDataNascimento()));
            pstmt.setString(4, espectador.getNome());
            pstmt.setString(5, espectador.getEndereco().getCep());
            pstmt.setString(6, espectador.getEndereco().getBairro());
            pstmt.setInt(7, espectador.getEndereco().getNumero());
            pstmt.setString(8, espectador.getEndereco().getRua());
            pstmt.executeUpdate();
        } catch (Exception e) {
            return false;
        }

        String sqlStatement2 = "INSERT INTO espectador VALUES(?, ?)";

        try ( PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement2)) {
            pstmt.setString(1, espectador.getCpf());
            pstmt.setInt(2, espectador.getCodigo());
            pstmt.executeUpdate();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static Boolean atualizar(Espectador espectador) {
        String sqlStatement
                = "UPDATE PESSOA SET TIPOPESSOA=?, DATANASCIMENTO=?,"
                + " NOME=?, CEP=?, BAIRRO=?, NUMERO=?, RUA=? WHERE CPF=?;";

        try ( PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)) {
            pstmt.setString(1, espectador.getTipoPessoa());
            pstmt.setDate(2, LocalDateHelper.localDateToSqlDate(espectador.getDataNascimento()));
            pstmt.setString(3, espectador.getNome());
            pstmt.setString(4, espectador.getEndereco().getCep());
            pstmt.setString(5, espectador.getEndereco().getBairro());
            pstmt.setInt(6, espectador.getEndereco().getNumero());
            pstmt.setString(7, espectador.getEndereco().getRua());
            pstmt.setString(8, espectador.getCpf());
            pstmt.executeUpdate();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static Espectador obterUm(String cpfEspectador) {
        Espectador espectador = new Espectador();

        String sqlStatement = "SELECT codigo, cpf, nome, tipopessoa,"
                + " datanascimento, cep, bairro, rua, numero FROM pessoa,"
                + " espectador WHERE cpf = cpfpessoa AND cpf = ?";

        try ( PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)) {
            pstmt.setString(1, cpfEspectador);
            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();
            espectador = gerarEspectador(resultSet);

        } catch (Exception e) {

        }

        return espectador;
    }

    public static ArrayList<Espectador> obterTodos() {
        ArrayList<Espectador> espectadores = new ArrayList<>();

        String sqlStatement = "SELECT codigo, cpf, nome, tipopessoa,"
                + " datanascimento, cep, bairro, rua, numero FROM pessoa,"
                + " espectador WHERE cpf = cpfpessoa";

        try ( PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)) {
            ResultSet resultSet = pstmt.executeQuery();
            espectadores = gerarEspectadores(resultSet);
        } catch (Exception e) {
            return null;
        }

        return espectadores;
    }

    public static ArrayList<Espectador> gerarEspectadores(ResultSet resultSet) {

        ArrayList<Espectador> espectadores = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Espectador espectador;
                espectador = gerarEspectador(resultSet);
                espectadores.add(espectador);
            }
        } catch (Exception e) {

        }

        return espectadores;
    }

    public static Espectador gerarEspectador(ResultSet resultSet) throws SQLException {

        Espectador espectador;

        try {

            Endereco endereco = new Endereco(
                    resultSet.getString("CEP"),
                    resultSet.getString("BAIRRO"),
                    resultSet.getString("RUA"),
                    resultSet.getInt("NUMERO")
            );

            espectador = new Espectador(
                    resultSet.getInt("CODIGO"),
                    resultSet.getString("CPF"),
                    resultSet.getString("NOME"),
                    resultSet.getString("TIPOPESSOA"),
                    LocalDateHelper.sqlDateToLocalDate(resultSet.getDate("DATANASCIMENTO")),
                    endereco
            );

        } catch (SQLException e) {
            return null;
        }

        return espectador;
    }
    
    public static int obterProximoCodigo(){
        ArrayList<Espectador> espectadores = obterTodos();
        
        if (espectadores.isEmpty()) {
            return 1;
        }
        
        return espectadores.get(espectadores.size() - 1).getCodigo() + 1;
    }

}
