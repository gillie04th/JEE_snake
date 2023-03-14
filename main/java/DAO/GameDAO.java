package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Game;
import models.ModelException;
import models.User;

public class GameDAO implements GameDAOInterface {
	    
	private DAOFactory factory;

    GameDAO(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void add(Game game, User user) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        //PreparedStatement preparedStatement2 = null;

        try {
            connexion = factory.getConnection();
            
            preparedStatement = connexion.prepareStatement("INSERT INTO Partie(map_name, temps_depart, id_joueur_gagnant, nb_tour, nb_tour_max, speed, status) VALUES(?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, game.getMap());
            preparedStatement.setString(2, game.getDepart().toString());
            //preparedStatement.setInt(3, game.getGagnant().getId());
            preparedStatement.setNull(3, 0);
            preparedStatement.setInt(4, game.getTours());
            preparedStatement.setInt(5, game.getToursMax());
            preparedStatement.setInt(6, game.getSpeed());
            preparedStatement.setString(7, game.getStatus());

            preparedStatement.executeUpdate();
            
            //System.out.println(preparedStatement.getGeneratedKeys());
                    	
	        //preparedStatement2 = connexion.prepareStatement("INSERT INTO Joueur_Partie(id_joueur, id_partie) VALUES(?, ?);");
	        //preparedStatement2.setInt(1, user.getId());
	        //preparedStatement2.setInt(2, game.getId());
            //preparedStatement2.executeUpdate();
            
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
                	connexion.commit();
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible de communiquer avec la base de données");
            }
        }
    }
	    
    public Game get(int id) throws DAOException{
    	Connection connexion = null;
    	PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        
    	try {
    		connexion = factory.getConnection();
    		preparedStatement = connexion.prepareStatement("SELECT * FROM Joueur WHERE id_joueur = ?;");
            
    		preparedStatement.setInt(1, id);

    		resultat = preparedStatement.executeQuery();
    		
    		
    		while(resultat.next()) {
            	String name = resultat.getString("pseudo");
                String email = resultat.getString("email");
                String password = resultat.getString("password");

                Game game = new Game();
                game.setId(id);			
                game.setGagnant(null);
				game.setMap(name);
                game.setTours(0);
                game.setToursMax(0);
                game.setDepart(null);
                
                return game;
    		}
		}
    	catch (SQLException e) {
    		throw new DAOException("Impossible de communiquer avec la base de données");
		}
    	catch (ModelException e) {
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

    	return null;
    }
    
    public void delete(int id) throws DAOException{
    	Connection connexion = null;
    	PreparedStatement preparedStatement = null;
        
    	try {
    		connexion = factory.getConnection();
    		preparedStatement = connexion.prepareStatement("DELETE FROM Joueur WHERE id_joueur = ?;");
            
    		preparedStatement.setInt(1, id);

    		preparedStatement.executeUpdate();
    		   		
		}
    	catch (SQLException e) {
    		throw new DAOException("Impossible de communiquer avec la base de données");
		}
    	finally {
            try {
                if (connexion != null) {
                	connexion.commit();
                    connexion.close();  
                }
            } catch (SQLException e) {
            	throw new DAOException("Impossible de communiquer avec la base de données");
            }
        }

    }

}
