package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoHibernateImpl();
    }

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        saveUser(name, lastName, age, null);
    }

    @Override
    public void saveUser(String name, String lastName, byte age, String workplace) {
        User user = new User(name, lastName, age, workplace);
        userDao.saveUser(name, lastName, age, workplace);
    }


    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }


    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }

    @Override
    public List<User> findByWorkplace(String workplace) {
        return userDao.findByWorkplace(workplace);
    }
}
