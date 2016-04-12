package DAO;

import Data.Guest;
import Data.Users;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация интерфейса для пользователей
 */
public class UserDAO implements InterfaceDao<Users> {

    public String WhoIsIt() {
        return "Users";
    }

    public void addEl(Users el) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(el);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public void updateEl(Users el) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(el);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public Users getElById(String el) throws SQLException {
        Session session = null;
        Users user = new Users();
        Guest guest = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            // test start
            user = (Users) session.get(Users.class, el);
            //guest =  session.load(Guest.class, nickname);

            //end test
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
          //  user = new Users();
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        // user.setNickname(guest.getNickname());
        // user.setPassword(guest.getPassword());
        return user;
    }


    public List<Users> getAllEls() throws SQLException {
        Session session = null;
        List<Users> users = new ArrayList<Users>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            users = session.createCriteria(Users.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return users;
    }


    public void deleteEl(Users el) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(el);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void getByName(String name) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.getNamedProcedureCall(name);
                    //createQuery("select fname from Users as Ivan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
