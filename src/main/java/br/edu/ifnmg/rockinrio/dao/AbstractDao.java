package br.edu.ifnmg.rockinrio.dao;

import br.edu.ifnmg.rockinrio.entity.Entidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe abstrata para generalização de operações com banco de dados.
 */
public abstract class AbstractDao<T, K> implements IDao<T, K> {

    /**
     * Executa o procedimento de salvamento (inserção ou atualização) do objeto
     * mapeado no banco de dados.
     *
     * @param o Objeto a ser salvo no banco de dados.
     * @return Valor da chave primária gerada pela inclusão de um novo registro
     * ou mesmo valor da chave primária do objeto original presistido
     * anteriormente.
     */
    @Override
    public K salvar(T o) {

        Integer id = 0;

        if (((Entidade) o).getId() == null || ((Entidade) o).getId() == 0) {
            try {
                
                String generatedColumns[] = { "ID" };
                
                PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(getDeclaracaoInsert(), generatedColumns);
                
                montarDeclaracao(pstmt, o);

                pstmt.executeUpdate();
                
                ResultSet rs = pstmt.getGeneratedKeys();

                if (rs.next()) {
                    id = rs.getInt(1);
                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
	else {
            try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            getDeclaracaoUpdate())) {

                montarDeclaracao(pstmt, o);

                pstmt.executeUpdate();

                id = ((Entidade) o).getId();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return (K) id;
    }

    /**
     * Exclui o registro do objeto no banco de dados.
     *
     * @param o Objeto a ser excluído.<br>
     * <i>OBS.: o único valor útil é a identidade do objeto mapeado.</i>
     * @return Condição de sucesso ou falha na exclusão.
     */
    @Override
    public Boolean excluir(T o) {
        Integer id = ((Entidade) o).getId();

        if (id != null && id != 0) {
            try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            getDeclaracaoDelete())) {
                ajustarIdDeclaracao(pstmt, (K) id);
                pstmt.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	else {
            return false;
        }

        return true;
    }

    /**
     * Recupera um dado objeto mapeado para o banco de dados por meio de sua
     * chave de identidade.
     *
     * @param id Identidade do objeto.
     * @return Objeto segundo registro persistido.
     */
    @Override
    public T localizarPorId(K id) {
        T objeto = null;

        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        getDeclaracaoSelectPorId())) {
            ajustarIdDeclaracao(pstmt, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                objeto = extrairObjeto(resultSet);
            }
        }
	catch (Exception e) {
            e.printStackTrace();
        }

        return objeto;
    }

    /**
     * Recupera todos os objetos mapeados para o banco de dados do tipo
     * específico.
     *
     * @return Lista (geralmente um <code>ArrayList<T></code>) de objetos
     * persistidos.
     */
    @Override
    public List<T> localizarTodos() {
        List<T> objetos = new ArrayList<>();

        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        getDeclaracaoSelectTodos())) {

            ResultSet resultSet = pstmt.executeQuery();

            objetos = extrairObjetos(resultSet);

        }
	catch (Exception e) {
            e.printStackTrace();
        }

        return objetos;
    }

    /**
     * Recupera a sentença SQL específica para a inserção da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para inserção.
     */
    public abstract String getDeclaracaoInsert();

    /**
     * Recupera a sentença SQL específica para a busca da entidade no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidade.
     */
    public abstract String getDeclaracaoSelectPorId();

    /**
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    public abstract String getDeclaracaoSelectTodos();

    /**
     * Recupera a sentença SQL específica para a atualização da entidade no
     * banco de dados.
     *
     * @return Sentença SQl para atualização.
     */
    public abstract String getDeclaracaoUpdate();

    /**
     * Recupera a sentença SQL específica para a exclusão da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para exclusão.
     */
    public abstract String getDeclaracaoDelete();

    /**
     * Insere o valor da chave primária na senteça SQL específica para seu uso.
     *
     * @param pstmt Declaração previamente preparada.
     * @param id Chave primária a ser inserida na sentença SQL.
     */
    public void ajustarIdDeclaracao(PreparedStatement pstmt, K id) {
        try {
            if(id instanceof Long) {
                pstmt.setLong(1, (Long) id);
            } else {
                pstmt.setInt(1, (Integer) id);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Insere os valores do objeto na senteça SQL específica para inserção ou
     * atualização de registros no banco de dados.
     *
     * @param pstmt Declaração previamente preparada.
     * @param id Chave primária a ser inserida na sentença SQL.
     */
    public abstract void montarDeclaracao(PreparedStatement pstmt, T o);

    /**
     * Cria objeto a partir do registro fornecido pelo banco de dados.
     *
     * @param resultSet Resultado proveniente do banco de dados relacional.
     * @return Objeto constituído.
     */
    public abstract T extrairObjeto(ResultSet resultSet);

    /**
     * Cria objeto(s) a partir do(s) registro(s) fornecido(s) pelo banco de
     * dados.
     *
     * @param resultSet Resultado(s) proveniente(s) do banco de dados
     * relacional.
     * @return Lista de objeto(s) constituído(s).
     */
    public abstract List<T> extrairObjetos(ResultSet resultSet);
}
