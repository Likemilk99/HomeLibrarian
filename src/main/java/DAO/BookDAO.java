package DAO;

import Data.Books;
import Data.Guest;
import Data.Users;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import util.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Реализация интерфейса для книг
 */
public class BookDAO implements InterfaceDao<Books> {


    public String WhoIsIt() {
        return "Books";
    }

    public void addEl(Books el) throws SQLException {
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


    public void updateEl(Books el) throws SQLException {
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


    public Books getElById(String el) throws SQLException {
        Session session = null;
        Books book = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            // test start
            book = (Books) session.get(Books.class, el);
            //guest =  session.load(Guest.class, nickname);

            //end test
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        // user.setNickname(guest.getNickname());
        // user.setPassword(guest.getPassword());
        return book;
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


    public void deleteEl(Books el) throws SQLException {
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

    @Override
    public long getCount() throws SQLException {
        Session session = null;
        long count = 0;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            count = (long) session.createCriteria(Users.class).setProjection(Projections.rowCount()).uniqueResult();
            session.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                return count;
            }
            return count;
        }
    }

    @Override
    public List getSubList(int position) throws SQLException {
        Session session = null;
        List<Books> users =  new LinkedList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            users = session.createCriteria(Books.class)
                    .setMaxResults(COUNT_ROWS)
                    .setFirstResult(position)
                    .list();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                return users;
            }
            return users;
        }
    }
}
