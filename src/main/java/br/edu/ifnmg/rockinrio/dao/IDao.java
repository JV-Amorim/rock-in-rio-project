/*
 * Projeto de conclusão das disciplinas de BD e POO (Ciência da Computação/IFNMG).
 */
package br.edu.ifnmg.rockinrio.dao;

import java.sql.ResultSet;
import java.util.List;

/**
 * Interface genérica com métodos mínimos requeridos para classes concretas.
 * @param <T> Tipo da entidade a ser processada.
 * @param <K> Tipo da chave primária.
 */
public interface IDao<T, K> {
    /**
     * Executa o procedimento de salvamento (inserção ou atualização) do objeto
     * mapeado no banco de dados.
     * 
     * @param obj Objeto a ser salvo no banco de dados.
     * @return Valor da chave primária gerada pela inclusão de um novo registro
     * ou mesmo valor da chave primária do objeto original presistido anteriormente.
     */
    public K insert(T obj);

    /**
     * Recupera todos os objetos mapeados para o banco de dados do tipo específico.
     * 
     * @return Lista (geralmente um <code>ArrayList<T></code>) de objetos persistidos.
     */
    public List<T> selectAll();

    /**
     * Recupera um dado objeto mapeado para o banco de dados por meio de sua
     * chave de identidade.
     * 
     * @param id Atributo identificador do objeto.
     * @return Objeto segundo registro persistido.
     */
    public T selectById(K id);

    /**
     * Exclui o registro do objeto no banco de dados.
     * 
     * @param obj Objeto a ser excluído.<br>
     * @return Condição de sucesso ou falha na exclusão.
     */
    public Boolean delete(T obj);

    /**
     * Cria um objeto a partir do registro fornecido pelo banco de dados.
     *
     * @param resultSet Resultado proveniente do banco de dados relacional.
     * @return Objeto constituído.
     */
    public T generateObject(ResultSet resultSet);

    /**
     * Cria objeto(s) a partir do(s) registro(s) fornecido(s) pelo banco de
     * dados.
     *
     * @param resultSet Resultado(s) proveniente(s) do banco de dados
     * relacional.
     * @return Lista de objeto(s) constituído(s).
     */
    public List<T> generateObjects(ResultSet resultSet);
}
