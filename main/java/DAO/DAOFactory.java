package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
    private String url;
    private String username;
    private String password;

    DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        DAOFactory instance = new DAOFactory("jdbc:mysql://localhost:3306/db_snake", "user_snake", "the5uper5nake!");
        return instance;
    }

    public Connection getConnection() throws SQLException {
    	Connection connexion = DriverManager.getConnection(url, username, password);
        connexion.setAutoCommit(false);
        return connexion;
    }

    // Récupération du Dao UserDAO
    public UserDAOInterface getUserDAO() {
        return new UserDAO(this);
    }
    
    // Récupération du Dao GameDAO
    public GameDAOInterface getGameDAO() {
        return new GameDAO(this);
    }
}
