package DAO;

import java.util.List;

import models.Game;
import models.User;

public interface GameDAOInterface {
    void add( Game game, User user) throws DAOException;
    List<Game> getAll() throws DAOException;
    Game get(int id) throws DAOException;
    void delete(int id) throws DAOException;
    //void update(User user) throws DAOException;
}