/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.dao;

import br.edu.ifnmg.rockinrio.entity.Ocorrencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class OcorrenciaDao {

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    private OcorrenciaDao() { }
    //</editor-fold>
    
    public static Object inserir(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static ArrayList<Ocorrencia> obterTodos() {
        ArrayList<Ocorrencia> ocorrencias = new ArrayList<>();
        
        String sqlStatement = "SELECT * FROM OCORRENCIA JOIN OCORRENCIAPESSOA ON NUMERO=NUMEROOCORRENCIA";
        
        try (PreparedStatement pstmt =  DatabaseManager.getConnection().prepareStatement(sqlStatement)) {
            ResultSet resultSet = pstmt.executeQuery();
            ocorrencias = gerarObjetos(resultSet);
        }
	catch (Exception e) {
            e.printStackTrace();
        }
        
        return ocorrencias;
    }

    public static Boolean excluir(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static Object gerarObjeto(ResultSet resultSet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static ArrayList<Ocorrencia> gerarObjetos(ResultSet resultSet) {
        ArrayList<Ocorrencia> ocorrencias = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Ocorrencia o = 
                    new Ocorrencia(
                        resultSet.getInt("NUMERO"),
                        resultSet.getString("CPFPROFISSIONALSEG"),
                        resultSet.getString("CPFPESSOA"),
                        resultSet.getDate("DATAOCORRENCIA"),
                        resultSet.getString("DESCRIÇÃO"),
                        resultSet.getDouble("LATITUDE"),
                        resultSet.getDouble("LONGITUDE")
                    );
                ocorrencias.add(o);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return ocorrencias;
    }
}
