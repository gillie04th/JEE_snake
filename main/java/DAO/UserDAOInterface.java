package DAO;

import java.util.List;

import models.User;

public interface UserDAOInterface {
    void add( User user) throws DAOException;
    List<User> getAll() throws DAOException;
    User get(int id);
    void delete(int id);
    User update(User user);
}