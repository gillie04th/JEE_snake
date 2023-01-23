package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.User;

public class UserDAO {

	    private Connection connexion;
	    
	    public List<User> recupererUtilisateurs() {
	        List<User> utilisateurs = new ArrayList<User>();
	        Statement statement = null;
	        ResultSet resultat = null;

	        loadDatabase();
	        
	        try {
	            statement = connexion.createStatement();

	            // Exécution de la requête
	            resultat = statement.executeQuery("SELECT nom, prenom FROM noms;");

	            // Récupération des données
	            while (resultat.next()) {
	            	int id = resultat.getInt("id");
	                String nom = resultat.getString("nom");
	                String email = resultat.getString("email");
	                
	                User utilisateur = new User(id, nom, email);
	                
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
	    
	    private void loadDatabase() {
	        // Chargement du driver
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	        }

	        try {
	            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee", "root", "");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void ajouterUtilisateur(User utilisateur) {
	        loadDatabase();
	        
	        try {
	            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO noms(nom, prenom) VALUES(?, ?);");
	            preparedStatement.setString(1, utilisateur.getNom());
	            preparedStatement.setString(2, utilisateur.getEmail());
	            
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


}
