package org.example.util;



import org.hibernate.Session;
import jm.task.core.jdbc.model.User;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //User user = new User("Ivan", "Ivanov", (byte) 30);
        //session.persist(user);

        session.getTransaction().commit();
        session.close();

        System.out.println("USER SAVED");
    }
}



