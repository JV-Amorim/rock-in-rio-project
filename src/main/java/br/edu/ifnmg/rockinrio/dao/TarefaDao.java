package br.edu.ifnmg.rockinrio.dao;

import br.edu.ifnmg.rockinrio.entity.Tarefa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Operações de persistência com a entidade Tarefa.
 */
public class TarefaDao extends AbstractDao<Tarefa, Integer> {

    /**
     * Recupera a sentença SQL específica para a inserção da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para inserção.
     */
    @Override
    public String getDeclaracaoInsert() {
        Random rand = new Random();
        int id = rand.nextInt(2147483647);
        return "INSERT INTO tarefa(id, descricao, concluida) VALUES (" + id + ", ?, ?)";
    }

    /**
     * Recupera a sentença SQL específica para a busca da entidade no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidade.
     */
    @Override
    public String getDeclaracaoSelectPorId() {
        return "SELECT * FROM tarefa WHERE id = ?";
    }

    /**
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    @Override
    public String getDeclaracaoSelectTodos() {
        return "SELECT * FROM tarefa";
    }

    /**
     * Recupera a sentença SQL específica para a atualização da entidade no
     * banco de dados.
     *
     * @return Sentença SQl para atualização.
     */
    @Override
    public String getDeclaracaoUpdate() {
        return "UPDATE tarefa SET descricao = ?, concluida = ? WHERE id = ?";
    }

    /**
     * Recupera a sentença SQL específica para a exclusão da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para exclusão.
     */
    @Override
    public String getDeclaracaoDelete() {
        return "DELETE FROM tarefa WHERE id = ?";
    }

    /**
     * Insere os valores do objeto na senteça SQL específica para inserção ou
     * atualização de registros no banco de dados.
     *
     * @param pstmt Declaração previamente preparada.
     * @param tarefa Objeto tarefa.
     */
    @Override
    public void montarDeclaracao(PreparedStatement pstmt, Tarefa tarefa) {
        try {
            if (tarefa.getId() == null || tarefa.getId() == 0) {
                pstmt.setString(1, tarefa.getDescricao());
                pstmt.setBoolean(2, tarefa.getConcluida());
            } else {
                pstmt.setString(1, tarefa.getDescricao());
                pstmt.setBoolean(2, tarefa.getConcluida());
                pstmt.setInt(3, tarefa.getId());
            }
        }
		catch (SQLException ex) {
            Logger.getLogger(TarefaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cria objeto a partir do registro fornecido pelo banco de dados.
     *
     * @param resultSet Resultado proveniente do banco de dados relacional.
     * @return Objeto constituído.
     */
    @Override
    public Tarefa extrairObjeto(ResultSet resultSet) {
        Tarefa tarefa = new Tarefa();

        try {
            tarefa.setId(resultSet.getInt("id"));
            tarefa.setDescricao(resultSet.getString("descricao"));
            tarefa.setConcluida(resultSet.getBoolean("concluida"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
		
        return tarefa;
    }

    /**
     * Cria objeto(s) a partir do(s) registro(s) fornecido(s) pelo banco de
     * dados.
     *
     * @param resultSet Resultado(s) proveniente(s) do banco de dados
     * relacional.
     * @return Lista de objeto(s) constituído(s).
     */
    @Override
    public List<Tarefa> extrairObjetos(ResultSet resultSet) {
        ArrayList<Tarefa> tarefas = new ArrayList<>();
		
        try {
            while (resultSet.next()) {
                Tarefa tarefa = new Tarefa();

                tarefa.setId(resultSet.getInt("id"));
                tarefa.setDescricao(resultSet.getString("descricao"));
                tarefa.setConcluida(resultSet.getBoolean("concluida"));
                
                tarefas.add(tarefa);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(TarefaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
		
        return tarefas;
    }
}
