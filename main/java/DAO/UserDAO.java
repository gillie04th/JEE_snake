package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.User;

public class UserDAO implements UserDAOInterface {
	    
	private DAOFactory factory;

    UserDAO(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void add(User user) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = factory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO Joueur(pseudo, email, password) VALUES(?, ?, ?);");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<User>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = factory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM Joueur;");

            while (resultat.next()) {
            	int id = resultat.getInt("id_joueur");
            	String name = resultat.getString("pseudo");
                String email = resultat.getString("email");

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
	    
    public User get(int id) {
    	return new User();
    }
    public void delete(int id) {
    	
    }
    public User update(User user) {
    	return new User();
    }
    
    public List<String> getPseudos() {
		var listUser = getAll();
		var listPseudos = new ArrayList<String>();
		
		for(User user : listUser) {
			listPseudos.add(user.getName());
		}
		
		return listPseudos;
    }
    
    public List<String> getEmails() {
		var listUser = getAll();
		var listEmails = new ArrayList<String>();
		
		for(User user : listUser) {
			listEmails.add(user.getEmail());
		}
		return listEmails;
    }
    
    public User isUserRegistered(String email, String password) {
        Connection connexion = null;
    	Statement statement = null;
        ResultSet resultat = null;
        
        try {
        	connexion = factory.getConnection();
            statement = connexion.createStatement();

            // Exécution de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement("SELECT * FROM Joueur where email = ? and password = ?;");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultat = preparedStatement.executeQuery();

            // Récupération des données
            while (resultat.next()) {
                return new User(
                        resultat.getInt("id_joueur"),
                        resultat.getString("pseudo"),
                        resultat.getString("email"),
                        resultat.getString("password"));
            }
        } catch (SQLException e) {
        } finally {
            // Fermeture de la connexion
            try {
                if (resultat != null)
                    resultat.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignore) {
            }
        }
        
        return null;
    }


}
