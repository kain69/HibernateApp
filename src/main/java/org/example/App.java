package org.example;

import org.example.model.Actor;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try(sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
//
//            Movie movie = new Movie("Pulp fiction", 1994);
//            Actor actor1 = new Actor("Harvey Keitel", 81);
//            Actor actor2 = new Actor("Samuel L. Jackson", 72);
//
//            // Arrays.asList()
//            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
//
//            actor1.setMovies(new ArrayList<>(List.of(movie)));
//            actor2.setMovies(new ArrayList<>(List.of(movie)));
//
//            session.save(movie);
//            session.save(actor1);
//            session.save(actor2);

            Movie movie = session.get(Movie.class, 1);
            System.out.println(movie.getActors());

            Actor actor = session.get(Actor.class, 1);
            System.out.println(actor.getMovies());



            session.getTransaction().commit();
        }
    }
}
