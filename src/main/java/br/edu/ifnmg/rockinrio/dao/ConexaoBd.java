package br.edu.ifnmg.rockinrio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tratamento da conexão com o banco de dados.
 */
public class ConexaoBd {
    /**
     * Driver de conexão com o banco de dados.
     */
    public static final String DB_DRIVER;

    /**
     * URL de conexão com o banco de dados.
     */
    public static final String URL;
    
    /**
     * Usuário para acesso ao banco de dados.
     */
    private static final String USUARIO;
    
    /**
     * Senha para acesso ao banco de dados.
     */
    private static final String SENHA;
    
    /**
     * Retém a conexão estabelecida com o banco de dados durante a operação do sistema.
     */
    private static Connection conexao;

    /**
     * Inicialização de valores estáticos.
     */
    static {
        DB_DRIVER = "oracle.jdbc.OracleDriver";
        URL = "jdbc:oracle:thin:@grad.icmc.usp.br:15215:orcl";
        USUARIO = "G2j24078";
        SENHA = "08042001";
    }

    //<editor-fold defaultstate="collapsed" desc="Construtor privado">
    /**
     * Construtor privado para forçar acesso à conexão pelo membro estático
     * <code>getConexao()</code> sem que sejam gerados novos objetos "ConexaoBd".
     */
    private ConexaoBd() {
    }
    //</editor-fold>

    /**
     * Estabelece e gera a retenção da conexão com o banco de dados.
     * 
     * @return Conexão com o banco de dados.
     */
    public static Connection getConexao() {
        if (conexao == null) {
            try {
                Class.forName(DB_DRIVER);
            }
            catch (ClassNotFoundException ex) {
                Logger.getLogger(ConexaoBd.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            }
            catch (SQLException ex) {
                Logger.getLogger(ConexaoBd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conexao;
    }
}
