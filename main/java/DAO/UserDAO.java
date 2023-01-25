package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.User;

public class UserDAO extends DAO {
	    
		private static UserDAO instance;
	
		private UserDAO() {
			
		}
		
		public static UserDAO getInstance() {
			if(instance == null) {
				instance = new UserDAO();
			}
			return instance;
		}
		
	
	    public List<User> getUsers() {
	        List<User> utilisateurs = new ArrayList<User>();
	        Statement statement = null;
	        ResultSet resultat = null;

	        loadDatabase();
	        
	        try {
	            statement = connexion.createStatement();

	            // Exécution de la requête
	            resultat = statement.executeQuery("SELECT * FROM Joueur;");

	            // Récupération des données
	            while (resultat.next()) {
	                String nom = resultat.getString("pseudo");
	                String email = resultat.getString("email");
	                String password = resultat.getString("password");
	                
	                User utilisateur = new User(nom, email, password);
	                
	                utilisateurs.add(utilisateur);
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
	        
	        return utilisateurs;
	    }
	    
	    public List<String> getPseudos() {
			var listUser = getUsers();
			var listPseudos = new ArrayList<String>();
			
			for(User user : listUser) {
				listPseudos.add(user.getName());
			}
			
			return listPseudos;
	    }
	    
	    
	    public List<String> getEmails() {
			var listUser = getUsers();
			var listEmails = new ArrayList<String>();
			
			for(User user : listUser) {
				listEmails.add(user.getEmail());
			}
			
			return listEmails;
	    }
	    
	    
	    public User isUserRegistered(String login, String password) {
	    	User user = new User();
	        Statement statement = null;
	        ResultSet resultat = null;

	        loadDatabase();
	        
	        try {
	            statement = connexion.createStatement();

	            // Exécution de la requête
	            PreparedStatement preparedStatement = connexion.prepareStatement("SELECT * FROM Joueur where login = ? and password = ?;");
	            preparedStatement.setString(1, login);
	            preparedStatement.setString(2, password);
	            resultat = preparedStatement.executeQuery();

	            // Récupération des données
	            while (resultat.next()) {
	                user.setId(resultat.getInt("id_joueur"));
	                user.setName(resultat.getString("pseudo"));
	                user.setEmail(resultat.getString("login"));
	                user.setPassword(resultat.getString("password"));
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
	        
	        return user;
	    }
	    
	    public void addUser(User utilisateur) {
	        loadDatabase();
	        
	        try {
	            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO Joueur(email, password, pseudo) VALUES(?, ?, ?);");
	            preparedStatement.setString(1, utilisateur.getEmail());
	            preparedStatement.setString(2, utilisateur.getPassword());
	            preparedStatement.setString(3, utilisateur.getName());
	            
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


}
