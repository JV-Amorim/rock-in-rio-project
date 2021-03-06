/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.dao;

import br.edu.ifnmg.rockinrio.entity.Ocorrencia;
import br.edu.ifnmg.rockinrio.helper.LocalDateHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class OcorrenciaDao {

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    private OcorrenciaDao() { }
    //</editor-fold>
    
    /**
     * Método para inserir uma ocorrência no banco de dados através de uma consulta
     * sql "sqlStatement"
     * @param ocorrencia Ocorrencia preenchida com os dados a serem persistidos
     * @return 
     */
    public static Boolean inserir(Ocorrencia ocorrencia) {
        var connection = DatabaseManager.getConnection();
        
        String sqlStatement = "INSERT INTO OCORRENCIA VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sqlStatement)) {
            pstmt.setDate(1, LocalDateHelper.localDateToSqlDate(ocorrencia.getDataOcorrencia()));
            pstmt.setInt(2, ocorrencia.getNumero());
            pstmt.setDate(3, java.sql.Date.valueOf(ocorrencia.getDataOcorrencia()));
            pstmt.setString(4, ocorrencia.getCpfProfissionalSeguranca());
            pstmt.setString(5, ocorrencia.getDescricao());
            pstmt.setDouble(6, ocorrencia.getLongitude());
            pstmt.setDouble(7, ocorrencia.getLatitude());
            pstmt.executeUpdate();
        }
	catch (Exception e) {
            return false;
        }
        
        String sqlStatement2 = "INSERT INTO OCORRENCIAPESSOA VALUES (?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sqlStatement2)) {
            pstmt.setString(1, ocorrencia.getCpfPessoa());
            pstmt.setInt(2, ocorrencia.getNumero());
            pstmt.executeUpdate();
        }
	catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Método que atualiza uma instância do tipo Ocorrencia persistida no banco de dados
     * @param ocorrencia Objeto preenchido com os novos dados/valores
     * @return 
     */
    public static Boolean atualizar(Ocorrencia ocorrencia) {
        var connection = DatabaseManager.getConnection();
        
        String sqlStatement =
            "UPDATE OCORRENCIA SET "
            + "DTCONTRATACAO=?, DATAOCORRENCIA=?, DESCRIÇÃO=?, LONGITUDE=?, LATITUDE=? "
            + "WHERE NUMERO=?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sqlStatement)) {
            pstmt.setDate(1, LocalDateHelper.localDateToSqlDate(ocorrencia.getDataOcorrencia()));
            pstmt.setDate(2, java.sql.Date.valueOf(ocorrencia.getDataOcorrencia()));
            pstmt.setString(3, ocorrencia.getDescricao());
            pstmt.setDouble(4, ocorrencia.getLongitude());
            pstmt.setDouble(5, ocorrencia.getLatitude());
            pstmt.setInt(6, ocorrencia.getNumero());
            pstmt.executeUpdate();
        }
	catch (Exception e) {
            return false;
        }
        
        String sqlStatement2 =
            "UPDATE OCORRENCIAPESSOA SET CPFPESSOA=? WHERE NUMEROOCORRENCIA=?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sqlStatement2)) {
            pstmt.setString(1, ocorrencia.getCpfPessoa());
            pstmt.setInt(2, ocorrencia.getNumero());
            pstmt.executeUpdate();
        }
	catch (Exception e) {
            return false;
        }
        
        return true;
    }

   /**
     * Cria uma lista de instâncias do tipo especificado no ArrayList através
     * de uma Consulta no Banco de dados e a retorna como resultado
     * @sqlStatement String que representa a linha de comando no banco de dados
     * @return Lista de objetos prontos do tipo especificado no ArrayList
     */
    public static ArrayList<Ocorrencia> obterTodos() {
        ArrayList<Ocorrencia> ocorrencias;
        
        String sqlStatement =
            "SELECT * FROM "
            + "(OCORRENCIA JOIN OCORRENCIAPESSOA "
            + "ON NUMERO=NUMEROOCORRENCIA) "
            + "JOIN PESSOA ON CPFPESSOA=CPF "
            + "ORDER BY NUMEROOCORRENCIA";
        
        try (PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)) {
            ResultSet resultSet = pstmt.executeQuery();
            ocorrencias = gerarObjetos(resultSet);
        }
	catch (Exception e) {
            return null;
        }
        
        return ocorrencias;
    }

    /**
     * Executa um comando de atualização no banco de dados para excluir uma
     * determinada Ocorrencia através de seu numero (ID)
     * @param ocorrencia ocorrencia a ser excluida
     * @return 
     */
    public static Boolean excluir(Ocorrencia ocorrencia) {
        var connection = DatabaseManager.getConnection();
        
        String sqlStatement = "DELETE FROM OCORRENCIAPESSOA WHERE NUMEROOCORRENCIA=?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sqlStatement)) {
            pstmt.setInt(1, ocorrencia.getNumero());
            pstmt.executeUpdate();
        }
	catch (Exception e) {
            return false;
        }
        
        String sqlStatement2 = "DELETE FROM OCORRENCIA WHERE NUMERO=?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sqlStatement2)) {
            pstmt.setInt(1, ocorrencia.getNumero());
            pstmt.executeUpdate();
        }
	catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Algorítmo para geração de um novo id para uma nova instância do tipo
     * Ocorrencia
     * @return 
     */
    public static int obterProximoNumeroOcorrencia() {
        ArrayList<Ocorrencia> ocorrencias = obterTodos();
        
        if (ocorrencias.isEmpty()) {
            return 1;
        }
        
        return ocorrencias.get(ocorrencias.size() - 1).getNumero() + 1;
    }

    /**
     * Gera uma lista de Ocorrências através de um resultado de uma consulta SQL
     * @param resultSet
     * @return 
     */
    private static ArrayList<Ocorrencia> gerarObjetos(ResultSet resultSet) {
        ArrayList<Ocorrencia> ocorrencias = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Ocorrencia o = 
                    new Ocorrencia(
                        resultSet.getInt("NUMERO"),
                        resultSet.getString("CPFPROFISSIONALSEG"),
                        resultSet.getString("CPFPESSOA"),
                        LocalDateHelper.sqlDateToLocalDate(resultSet.getDate("DATAOCORRENCIA")),
                        resultSet.getString("DESCRIÇÃO"),
                        resultSet.getDouble("LATITUDE"),
                        resultSet.getDouble("LONGITUDE")
                    );
                o.setNomePessoa(resultSet.getString("NOME"));
                ocorrencias.add(o);
            }
        }
        catch (SQLException e) {
            return null;
        }

        return ocorrencias;
    }
}
