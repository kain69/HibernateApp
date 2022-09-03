package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // Получили человека и его товары
//            Person person = session.get(Person.class, 3);
//            System.out.println(person);
//            List<Item> items = person.getItems();
//            System.out.println(items);

            // Получили товар и его владельца
//            Item item = session.get(Item.class, 5);
//            System.out.println(item);
//            Person person = item.getOwner();
//            System.out.println(person);

            // Добавить новый товар и прикрепить к владельцу
//            Person person = session.get(Person.class, 2);
//            Item newItem = new Item("Item from Hibernate", person);
//            person.getItems().add(newItem);
//
//            session.save(newItem);

            // Пример с ошибкой, когда один объект из двух не сохранён
//            Person person = new Person("Test person", 30);
//
//            Item newItem = new Item("Item from Hibernate 2", person);
//
//            person.setItems(new ArrayList<>(Collections.singleton(newItem)));
//
//            session.save(person);
//            session.save(newItem);

            // Хорошая практика при удалении, очищать и список
//            Person person = session.get(Person.class, 3);
//            List<Item> items = person.getItems();
//
//            // SQL
//            for(Item item : items)
//                session.remove(item);
//
//            // Не порождает SQL, но необходимо для того, чтобы в кэше всё было верно
//            person.getItems().clear();

            // Каскадное удаление в БД (замена на null)
//            Person person = session.get(Person.class, 2);
//            // SQL
//            session.remove(person);
//
//            // Чтобы было правильное состояние Hibernate кэша
//            person.getItems().forEach(i -> i.setOwner(null));

            // Поменять владельца у существуещего товара
            Person person = session.get(Person.class,4);
            Item item = session.get(Item.class, 1);

            // Кэш
            item.getOwner().getItems().remove(item);

            // SQL
            item.setOwner(person);

            // Кэш
            person.getItems().add(item);

            session.getTransaction().commit();
        }
        finally {
            sessionFactory.close();
        }
    }
}
