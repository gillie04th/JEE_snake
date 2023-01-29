package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.ModelException;
import models.User;

public class UserDAO implements UserDAOInterface {
	    
	private DAOFactory factory;

    UserDAO(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void add(User user) throws DAOException {
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
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DAOException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible de communiquer avec la base de données");
            }
        }
    }

    @Override
    public List<User> getAll() throws DAOException {
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
            throw new DAOException("Impossible de communiquer avec la base de données");
        } catch (ModelException e) {
            throw new DAOException("Les données de la base sont invalides");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible de communiquer avec la base de données");
            }
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
    	List<User> users;
    	ArrayList<String> pseudos = new ArrayList<String>();
    	try {
	    	users = getAll();
			
			for(User user : users) {
				pseudos.add(user.getName());
			}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return pseudos;
    }
    
    public List<String> getEmails() {
		List<User> users;
		ArrayList<String> emails = new ArrayList<String>();
		try {
			users = getAll();
			
			for(User user : users) {
				emails.add(user.getEmail());
			}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
		return emails;
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
