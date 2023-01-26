package DAO;

import java.util.List;

import models.User;

public interface UserDAOInterface {
    void add( User user);
    List<User> getAll();
    User get(int id);
    void delete(int id);
    User update(User user);
}