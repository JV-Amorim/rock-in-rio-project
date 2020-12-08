package br.edu.ifnmg.rockinrio.entity;

/**
 * Modelo geral para entidades.
 */
public abstract class Entidade {

    /**
     * Identidade da entidade (chave primária mapeada para o banco de dados)
     */
    private Integer id;

    //<editor-fold defaultstate="collapsed" desc="Construtores">
    /**
     * Construtor padrão.
     */
    public Entidade() {
    }

    /**
     * Construtor sobrecarregado.
     *
     * @param id Identidade da entidade
     */
    public Entidade(Integer id) {
        this.id = id;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    //</editor-fold>

}
