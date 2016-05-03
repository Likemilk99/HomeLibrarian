package DAO;

import Data.Books;
import Data.Guest;
import Data.Users;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.junit.Test;
import util.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
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
        Users user = new Users("LikeMilk", "Ivan", "7154255", "iround@yandex.ru");
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
        List<Users> users =  new LinkedList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            users = session.createCriteria(Users.class)
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

    @Override
    public List<Users> GetSqlRequst(String author, String title, String year) throws SQLException {

        String sql = "SELECT * FROM Users WHERE ";//" \"email\" ='iround0@yandex.ru'";

        Session session = null;
        List<Users> users =  new LinkedList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Users.class);
            // query.setParameter("employee_id", "iround0@yandex.ru");
            users = query.list();
            System.out.println("users.size() = " + users.size());
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

    public List<Users> GetByTitleAndName(String title, String author)  throws SQLException{
        return new ArrayList<>();
    }

}
