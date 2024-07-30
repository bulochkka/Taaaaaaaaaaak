package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.CopyUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction;
        String sql = "CREATE TABLE IF NOT EXISTS User "
                + "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(50) NOT NULL, "
                + "lastName VARCHAR(50) NOT NULL, "
                + "age TINYINT NOT NULL)";
        try (Session session = new CopyUtil().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Problem with create table");
        }

    }

    @Override
    public void dropUsersTable() {
        Transaction transaction;
        String sql = "DROP TABLE IF EXISTS User";
        try (Session session = new CopyUtil().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            System.out.println("Problem with drop table");
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction;
        try (Session session = new CopyUtil().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Problem with save user");
        }

    }

    @Override
    public void removeUserById(long id) {
        User user;
        Transaction transaction;
        try (Session session = new CopyUtil().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.find(User.class, id);
            if (user != null) {
                session.remove(user);
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Problem with remove user");
        }

    }

    @Override
    public List<User> getAllUsers() {
        List users = new ArrayList<>();
        Transaction transaction;
        try (Session session = new CopyUtil().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            users = session.createQuery("from User").list();
            transaction.commit();

        } catch (Exception e) {
            System.out.println("Problem with get users");
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction;
        try (Session session = new CopyUtil().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            System.out.println("Problem with clean user table");
        }
    }

}

