/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.dao;

import br.edu.ifnmg.rockinrio.entity.ProfissionalSeguranca;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfissionalSegurancaDao {
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    private ProfissionalSegurancaDao() { }
    //</editor-fold>
    
    /**
     * Realiza uma consulta SQL para obter uma instância persistida do tipo
     * Profissional de segurança
     * @param cpf usado para identificar o Profissional de Segurança
     * @return profissional com seus dados preenchidos (objeto criado)
     */
    public static ProfissionalSeguranca obter(String cpf) {
        ProfissionalSeguranca profissionalSeg;
        
        String sqlStatement =
            "SELECT P.CPF AS CPF, P.NOME AS NOME FROM "
          + "PROFISSIONALSEG PS "
          + "JOIN "
          + "FUNCIONARIO F "
          + "ON PS.CPFFUNCIONARIO=F.CPFPESSOA "
          + "JOIN "
          + "PESSOA P "
          + "ON F.CPFPESSOA=P.CPF "
          + "WHERE P.CPF=?";
        
        try (PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)) {
            pstmt.setString(1, cpf);
            ResultSet resultSet = pstmt.executeQuery();
            profissionalSeg = gerarObjeto(resultSet);
        }
	catch (Exception e) {
            return null;
        }
        
        return profissionalSeg;
    }
    
    /**
     * gera um Objeto do tipo Profissional  de segurança através
     * de uma linha de resultadoSQL (consulta) e o retorna.
     * @param resultSet
     * @return 
     */
    private static ProfissionalSeguranca gerarObjeto(ResultSet resultSet) {
        ProfissionalSeguranca profissionalSeg;

        try {
            resultSet.next();
            
            profissionalSeg = new ProfissionalSeguranca(
                null,
                resultSet.getString("CPF"),
                resultSet.getString("NOME"),
                null,
                null
            );
        }
        catch (SQLException e) {
            return null;
        }

        return profissionalSeg;
    }
}
