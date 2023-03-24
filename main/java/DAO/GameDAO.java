package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        PreparedStatement preparedStatement2 = null;

        try {
            connexion = factory.getConnection();
            
            preparedStatement = connexion.prepareStatement("INSERT INTO Partie(map_name, temps_depart, id_joueur_gagnant, nb_tour, nb_tour_max, speed, status) VALUES(?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, game.getMap());
            preparedStatement.setString(2, game.getDepart().toString());
            if(game.getGagnant() != null) {
            	preparedStatement.setInt(3, game.getGagnant().getId());
            } else {        	
            	preparedStatement.setNull(3, 0);
            }
            preparedStatement.setInt(4, game.getTours());
            preparedStatement.setInt(5, game.getToursMax());
            preparedStatement.setInt(6, game.getSpeed());
            preparedStatement.setString(7, game.getStatus());

            preparedStatement.executeUpdate();
                    	
	        preparedStatement2 = connexion.prepareStatement("INSERT INTO Joueur_Partie(id_joueur, id_partie) VALUES(?, ?, ?);");
	        preparedStatement2.setInt(1, user.getId());
	        preparedStatement2.setInt(2, game.getId());
	        preparedStatement2.setInt(3, game.getScore());
            preparedStatement2.executeUpdate();
            
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e2) {
            	e2.printStackTrace();
            }
            e.printStackTrace();
            throw new DAOException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                	connexion.commit();
                    connexion.close();  
                }
            } catch (SQLException e) {
            	e.printStackTrace();
                throw new DAOException("Impossible de communiquer avec la base de données");
            }
        }
    }
	
    @Override
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
    
    @Override
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
    
    @Override
    public List<Game> getAll() throws DAOException{
    	List<Game> games = new ArrayList<Game>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = factory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM Joueur_Partie;");

            PreparedStatement preparedStatement = null;
            ResultSet resultat_partie = null;
            ResultSet resultat_joueur = null;
            
            while (resultat.next()) {
            	int id_partie = resultat.getInt("id_partie");
            	int id_joueur = resultat.getInt("id_joueur");
            	
            	preparedStatement = connexion.prepareStatement("SELECT * FROM Partie WHERE id_partie = ?;");
        		preparedStatement.setInt(1, id_partie);
        		resultat_partie = preparedStatement.executeQuery();
        		
        		preparedStatement = connexion.prepareStatement("SELECT * FROM Joueur WHERE id_joueur = ?;");
        		preparedStatement.setInt(1, id_joueur);
        		resultat_joueur = preparedStatement.executeQuery();
            	
        		
        		resultat_joueur.next();
        		
        		var userDAO = factory.getUserDAO();     
        		User joueur = userDAO.get(id_joueur);
        		
        		
        		resultat_partie.next();
        		
        		int id = resultat.getInt("id_partie");
        		int score = resultat.getInt("score");
            	String map = resultat_partie.getString("map_name");
                String temps_depart = resultat_partie.getString("temps_depart");
                int nb_tour = resultat_partie.getInt("nb_tour");
                int nb_tour_max = resultat_partie.getInt("nb_tour_max");
                int speed = resultat_partie.getInt("speed");
                String status = resultat_partie.getString("status");

                int id_gagnant = resultat_partie.getInt("id_joueur_gagnant");
                User joueur_gagnant = userDAO.get(id_gagnant);
                
        		
                Game game = new Game();
                game.setId(id);
                game.setJoueur(joueur);
                game.setMap(map);
                game.setDepart(temps_depart);
                game.setTours(nb_tour);
                game.setToursMax(nb_tour_max);
                game.setSpeed(speed);
                game.setScore(score);
                game.setStatus(status);   
                game.setGagnant(joueur_gagnant);

                games.add(game);
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
        return games;
    }
    
    public Game getBestGame(int id_joueur) throws DAOException {
    	Connection connexion = null;
    	PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        
        try {
    		connexion = factory.getConnection();
    		preparedStatement = connexion.prepareStatement("SELECT * FROM Joueur_Partie WHERE id_joueur = ? ORDER BY score DESC LIMIT 1;");
            
    		preparedStatement.setInt(1, id_joueur);

    		resultat = preparedStatement.executeQuery();
    		
    		
    		while(resultat.next()) {
            	int id_partie = resultat.getInt("id_partie");
            	int score = resultat.getInt("score");

            	PreparedStatement preparedStatementPartie = connexion.prepareStatement("SELECT * FROM Partie WHERE id_partie = ?;");	
            	preparedStatementPartie.setInt(1, id_partie);
            	
                ResultSet resultatPartie = preparedStatementPartie.executeQuery();
                
                while(resultatPartie.next()) {
                	
                	String map = resultatPartie.getString("map_name");
                	String temps_depart = resultatPartie.getString("temps_depart");
                    int nb_tour = resultatPartie.getInt("nb_tour");
                	
                	Game game = new Game();
                    game.setId(id_partie);			
    				game.setMap(map);
                    game.setTours(nb_tour);
                    game.setDepart(temps_depart);
                    game.setScore(score);
                    
                    return game;
                }
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
    
    public List<String> getAllMapName() throws DAOException{
    	List<String> maps = new ArrayList<String>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = factory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT map_name FROM Partie;");
            
            while (resultat.next()) {
            	String map_name = resultat.getString("map_name");
            	
            	if(!maps.contains(map_name)) {
            		maps.add(map_name);
            	}
            }
        } catch (SQLException e) {
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
        return maps;
    }

}
