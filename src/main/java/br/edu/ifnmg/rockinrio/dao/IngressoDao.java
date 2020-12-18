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

public class IngressoDao {
    
    public static Boolean inserir(Ingresso ingresso){
        
        String sqlStatement = "INSERT INTO ingresso VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)){
            pstmt.setInt(1, ingresso.getNumero());
            pstmt.setInt(2, ingresso.getValor());
            pstmt.setDate(3, java.sql.Date.valueOf(ingresso.getDataLineupEntrada()));
            pstmt.setString(4, ingresso.getHoraEntrada());
            pstmt.setString(5, ingresso.getCpfEspectador());
            pstmt.setDate(6, java.sql.Date.valueOf(ingresso.getPertenceDataLineup()));
            pstmt.executeUpdate();
        } catch (Exception e){
            return false;
        }
        
        return true;
    }
    
    public static Ingresso obterUm(int numero){
        Ingresso ingresso = new Ingresso();
        
        String sqlStatement = "SELECT nome, i.numero, valor, datalineupentrada,"
                + " horaentrada, cpfespectador, pertencedatalineup FROM "
                + "ingresso i , pessoa  WHERE cpfespectador = cpf AND i.numero = ?";
        
        try(PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)){
            pstmt.setInt(1, numero);
            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();
            ingresso = gerarIngresso(resultSet);
            
        } catch (Exception e){
            return null;
        }
        
        return ingresso;
    }
    
    public static Ingresso gerarIngresso(ResultSet resultSet) throws SQLException{
        
        Ingresso ingresso = new Ingresso(
                resultSet.getInt("NUMERO"),
                resultSet.getInt("VALOR"),
                LocalDateHelper.sqlDateToLocalDate(resultSet.getDate("DATALINEUPENTRADA")),
                resultSet.getString("HORAENTRADA"),
                resultSet.getString("CPFESPECTADOR"),
                LocalDateHelper.sqlDateToLocalDate(resultSet.getDate("PERTENCEDATALINEUP"))
        );
        
        ingresso.setNomeEspectador(resultSet.getString("NOME")); 
        
        return ingresso;
    }
    
    public static ArrayList<Ingresso> obterTodos(){
        
        ArrayList<Ingresso> ingressos;
        
        String sqlStatement = "SELECT nome, i.numero, valor, datalineupentrada,"
                + " horaentrada, cpfespectador, pertencedatalineup FROM "
                + "ingresso i , pessoa  WHERE cpfespectador = cpf order by numero";
        
        try(PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)){
            ResultSet resultSet = pstmt.executeQuery();
            ingressos = gerarIngressos(resultSet);
            
        } catch (Exception e){
            return null;
        }
        
        return ingressos;
    }

    public static ArrayList<Ingresso> gerarIngressos(ResultSet resultSet){
        
        ArrayList<Ingresso> ingressos = new ArrayList<>();
        
        try{
            while(resultSet.next()){
                Ingresso ingresso;
                ingresso = gerarIngresso(resultSet);
                ingressos.add(ingresso);
            }
        } catch(SQLException e){
            return null;
        }
        
        return ingressos;
    }
    
    public static Boolean atualizar(Ingresso ingresso){
        
        String sqlStatement = "update ingresso set numero=?, valor = ?,"
                + " datalineupentrada = ?, horaentrada = ?, cpfespectador = ?,"
                + " pertencedatalineup = ? where numero = ?";
        
        try (PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)){
            pstmt.setInt(1, ingresso.getNumero());
            pstmt.setInt(2, ingresso.getValor());
            pstmt.setDate(3, java.sql.Date.valueOf(ingresso.getDataLineupEntrada()));
            pstmt.setString(4, ingresso.getHoraEntrada());
            pstmt.setString(5, ingresso.getCpfEspectador());
            pstmt.setDate(6, java.sql.Date.valueOf(ingresso.getPertenceDataLineup()));
            pstmt.setInt(7, ingresso.getNumero());
            pstmt.executeUpdate();
        } catch (Exception e){
            return false;
        }
        
        return true;
    }
    
    public static Boolean excluir(Ingresso ingresso){
        
        String sqlStatement = "delete from ingresso where numero = ?";
        
        try (PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlStatement)) {
            pstmt.setInt(1, ingresso.getNumero());
            pstmt.executeUpdate();
        }
	catch (Exception e) {
            return false;
        }
                
        return true;
    }
    
    public static int obterProximoNumero(){
        ArrayList<Ingresso> ingressos = obterTodos();
        
        if (ingressos.isEmpty()) {
            return 1;
        }
        
        return ingressos.get(ingressos.size() - 1).getNumero() + 1;
    }
}
