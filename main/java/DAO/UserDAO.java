package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.User;

public class UserDAO extends DAO {
	    
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
	            	int id = resultat.getInt("id_joueur");
	                String nom = resultat.getString("pseudo");
	                String email = resultat.getString("login");
	                String password = resultat.getString("password");
	                
	                User utilisateur = new User(id, nom, email, password);
	                
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
	    
	    public void addUser(User utilisateur) {
	        loadDatabase();
	        
	        try {
	            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO joueurs(login, password, pseudo) VALUES(?, ?, ?);");
	            preparedStatement.setString(1, utilisateur.getEmail());
	            preparedStatement.setString(2, utilisateur.getPassword());
	            preparedStatement.setString(2, utilisateur.getName());
	            
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


}
