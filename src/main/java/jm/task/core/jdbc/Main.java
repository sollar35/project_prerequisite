package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ivan","Ivanov", (byte) 25, "google");
        System.out.println("Пользователь с именем – Ivan добавлен в базу данных");

        userService.saveUser("Petr", "Petrov", (byte) 30, "amazon");
        System.out.println("Пользователь с именем – Petr добавлен в базу данных");

        userService.saveUser("Anna", "Smirnova", (byte) 28, "google");
        System.out.println("Пользователь с именем – Anna добавлен в базу данных");

        userService.saveUser("Olga", "Sidorova", (byte) 22, "oracle");
        System.out.println("Пользователь с именем – Olga добавлен в базу данных");

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
