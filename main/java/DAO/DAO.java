package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	
    protected Connection connexion;

	protected void loadDatabase() {
        // Chargement du driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }

        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_snake", "user_snake", "the5uper5nake!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}