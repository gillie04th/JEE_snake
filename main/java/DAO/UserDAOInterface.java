package DAO;

import java.util.List;

import models.User;

public interface UserDAOInterface {
    void add( User user) throws DAOException;
    List<User> getAll() throws DAOException;
    User get(int id) throws DAOException;
    void delete(int id) throws DAOException;
    void update(User user) throws DAOException;
}