package br.edu.ifnmg.rockinrio.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Classe que realiza a conexão com o banco de dados.
 */
public class DatabaseManager {
    private static Connection connection;
    private static String dbDriver;
    private static String url;
    private static String user;
    private static String password;

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    private DatabaseManager() { }
    //</editor-fold>

    /**
     * Estabelece e gera a retenção da conexão com o banco de dados.
     * @return Conexão com o banco de dados.
     */
    public static Connection getConnection() {
        if (connection == null) {
            if (!getConnectionProperties()) return null;
            
            try {
                Class.forName(dbDriver);
            }
            catch (ClassNotFoundException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                connection = DriverManager.getConnection(url, user, password);
            }
            catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
    
    private static boolean getConnectionProperties() {
        var classLoader = DatabaseManager.class.getClassLoader();
        
        try (InputStream input = classLoader.getResourceAsStream("db.properties")) {
            Properties properties = new Properties();

            if (input == null) {
                System.err.println("Não foi possível encontrar o arquivo de propriedades.");
                return false;
            }

            properties.load(input);

            dbDriver = properties.getProperty("dbDriver");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            
            return true;
        }
        catch (IOException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
