package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createNativeQuery("""
                CREATE TABLE IF NOT EXISTS users (
                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(100),
                    lastName VARCHAR(100),
                    age TINYINT,
                    workplace VARCHAR(100)
                    
                )
            """).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create table", e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to drop table", e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age, String workplace) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(new User(name, lastName, age, workplace));
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to save user", e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to remove user", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            return session
                    .createQuery("FROM User", User.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get users", e);
        }
    }

    @Override
    public void cleanUsersTable() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createMutationQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to clean table", e);
        }
    }

    @Override
    public List<User> findByWorkplace(String workplace) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "From User u Where u.workplace = :workplace",
                    User.class
            )
                    .setParameter("workplace", workplace)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to find user by workplace", e);
        }
    }
}
