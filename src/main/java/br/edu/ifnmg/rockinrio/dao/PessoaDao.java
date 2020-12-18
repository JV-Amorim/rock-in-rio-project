/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.dao;

import br.edu.ifnmg.rockinrio.entity.Endereco;
import br.edu.ifnmg.rockinrio.entity.Pessoa;
import br.edu.ifnmg.rockinrio.helper.LocalDateHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class PessoaDao {

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    private PessoaDao() {
    }
    //</editor-fold>

    /**
     * Método que executa uma atualização no banco de dados para persistir uma
     * Instância do tipo Pessoa
     *
     * @param pessoa Objeto já preenchido com as informações a serem persistidas
     * @return
     */
    public static Boolean inserir(Pessoa pessoa) {
        String sqlStatement = "INSERT INTO PESSOA VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)) {
            pstmt.setString(1, pessoa.getCpf());
            pstmt.setString(2, pessoa.getTipoPessoa());
            pstmt.setDate(3, LocalDateHelper.localDateToSqlDate(pessoa.getDataNascimento()));
            pstmt.setString(4, pessoa.getNome());
            pstmt.setString(5, pessoa.getEndereco().getCep());
            pstmt.setString(6, pessoa.getEndereco().getBairro());
            pstmt.setInt(7, pessoa.getEndereco().getNumero());
            pstmt.setString(8, pessoa.getEndereco().getRua());
            pstmt.executeUpdate();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Método que executa uma atualização no banco de dados de uma instância já
     * existente
     *
     * @param pessoa Objeto com as novas informações a serem persistidas
     * @return
     */
    public static Boolean atualizar(Pessoa pessoa) {
        String sqlStatement
                = "UPDATE PESSOA SET "
                + "TIPOPESSOA=?, DATANASCIMENTO=?, NOME=?, CEP=?, BAIRRO=?, NUMERO=?, RUA=?"
                + "WHERE CPF=?";

        try ( PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)) {
            pstmt.setString(1, pessoa.getTipoPessoa());
            pstmt.setDate(2, LocalDateHelper.localDateToSqlDate(pessoa.getDataNascimento()));
            pstmt.setString(3, pessoa.getNome());
            pstmt.setString(4, pessoa.getEndereco().getCep());
            pstmt.setString(5, pessoa.getEndereco().getBairro());
            pstmt.setInt(6, pessoa.getEndereco().getNumero());
            pstmt.setString(7, pessoa.getEndereco().getRua());
            pstmt.setString(8, pessoa.getCpf());
            pstmt.executeUpdate();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Método que Executa uma consulta SQL para obter uma determinada pessoa no
     * Banco de dados usando o campo CPF como identificador
     *
     * @param cpfPessoa atributo único que identifica Pessoa
     * @return
     */
    public static Pessoa obter(String cpfPessoa) {
        Pessoa pessoa;

        String sqlStatement = "SELECT * FROM PESSOA WHERE CPF=?";

        try ( PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)) {
            pstmt.setString(1, cpfPessoa);
            ResultSet resultSet = pstmt.executeQuery();
            pessoa = gerarObjeto(resultSet);
        } catch (Exception e) {
            return null;
        }

        return pessoa;
    }

    /**
     * Método que realiza uma consulta SQL para obter todas as pessoas
     * persistidas no banco de dados (CPF e Nome)
     *
     * @return Lista de pessoas persistidas
     */
    public static ArrayList<Pessoa> obterTodos() {
        ArrayList<Pessoa> pessoas;

        String sqlStatement = "SELECT CPF, NOME FROM PESSOA";

        try ( PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)) {
            ResultSet resultSet = pstmt.executeQuery();
            pessoas = gerarObjetos(resultSet);
        } catch (Exception e) {
            return null;
        }

        return pessoas;
    }

    /**
     * Cria um objeto do tipo pessoa através do resultado de uma consulta SQL
     *
     * @param resultSet Consulta Sql (uma linha) para geração de um objeto
     * @return pessoa com seus devidos atributos
     */
    private static Pessoa gerarObjeto(ResultSet resultSet) {
        Pessoa pessoa;

        try {
            resultSet.next();

            pessoa = new Pessoa(
                    resultSet.getString("CPF"),
                    resultSet.getString("NOME"),
                    resultSet.getString("TIPOPESSOA"),
                    LocalDateHelper.sqlDateToLocalDate(resultSet.getDate("DATANASCIMENTO")),
                    new Endereco(
                            resultSet.getString("CEP"),
                            resultSet.getString("BAIRRO"),
                            resultSet.getString("RUA"),
                            resultSet.getInt("NUMERO")
                    )
            );
        } catch (SQLException e) {
            return null;
        }

        return pessoa;
    }

    /**
     * método que gera uma lista de Pessoas, através do resultado de uma consulta
     * SQL (1 ou várias linhas)
     * @param resultSet
     * @return
     */
    private static ArrayList<Pessoa> gerarObjetos(ResultSet resultSet) {
        ArrayList<Pessoa> pessoas = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setCpf(resultSet.getString("CPF"));
                pessoa.setNome(resultSet.getString("NOME"));
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            return null;
        }

        return pessoas;
    }
}
