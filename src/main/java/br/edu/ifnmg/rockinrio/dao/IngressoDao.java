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
    
    /**
     * Recebe uma instância do tipo Ingresso para ser persistida no banco de dados
     * @param ingresso objeto completo, com as informações a serem persistidas
     * @return confirmação do processo de inserção
     */
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
    
    /**
     * Faz uma consulta no banco de dados para construir um objeto do tipo Ingresso
     * @param numero inteiro que representa a chave primária de ingresso
     * que será utilizada para consulta
     * @return 
     */
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
    
    /**
     * Monta uma instância do tipo Ingresso através de uma tupla, resultante
     * de uma consulta no banco de dados
     * @param resultSet resultado de consulta (uma linha)
     * @return Ingresso Construido
     * @throws SQLException 
     */
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
    
    /**
     * Faz uma consulta para obter TODOS os ingressos persistidos no banco de dados
     * 
     * @return uma lista de ingressos montados 
     */
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

    
    /**
     * Prepara a construção de uma lista de ingressos, recebe o resultado
     * inteiro de uma consulta, e envia uma tupla para geração de cada Ingresso
     * @param resultSet Resultado inteiro de uma consulta SQL
     * @return Lista de ingressos pronta
     */
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
    
    /**
     * Atualza uma instância persistida no banco de dados através de um objeto
     * do tipo ingresso passado como parâmetro
     * @param ingresso ingresso com os novos atributos a serem persistidos
     * @return 
     */
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
    
    /**
     * Deleta do banco de dados uma instância através do número do ingresso passado
     * como parâmetro
     * @param ingresso ingresso completo, que servirá para identificar a instância
     * a ser excluida
     * @return 
     */
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
    /**
     * Gera um novo identificado para uma nova instância de Ingresso.
     * Pega o ultimo ingresso de uma consulta, adiciona um ao seu ID e retorna
     * @return ID para uma nova instância
     */
    public static int obterProximoNumero(){
        ArrayList<Ingresso> ingressos = obterTodos();
        
        if (ingressos.isEmpty()) {
            return 1;
        }
        
        return ingressos.get(ingressos.size() - 1).getNumero() + 1;
    }
}
