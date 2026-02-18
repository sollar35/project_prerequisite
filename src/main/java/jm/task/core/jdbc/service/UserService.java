package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserService {
    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void saveUser(String name, String lastName, byte age, String workplace);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();

    List<User> findByWorkplace(String workplace);
}
