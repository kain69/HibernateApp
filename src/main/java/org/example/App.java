package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

//            List<Person> people = session.createQuery("from Person where age > 21 and name like 'T%'", Person.class).getResultList();
            session.createQuery("update Person set name='Test' where age > 21").executeUpdate();
            session.getTransaction().commit();

//            people.forEach(System.out::println);
        }
        finally {
            sessionFactory.close();
        }
    }
}
