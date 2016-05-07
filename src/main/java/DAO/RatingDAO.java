package DAO;

import Data.Books;
import Data.Rating;
import Data.Users;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import util.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by likemilk on 07.05.2016.
 */
public class RatingDAO implements InterfaceDao <Rating> {


    @Override
    public String WhoIsIt() {
        return "Rating";
    }

    @Override
    public void addEl(Rating el) throws SQLException {
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

    @Override
    public void updateEl(Rating el) throws SQLException {
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

    @Override
    public Rating getElById(String id) throws SQLException {
        return null;
    }

    @Override
    public List getAllEls() throws SQLException {
        Session session = null;
        List<Rating> list = new ArrayList<Rating>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = session.createCriteria(Users.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    @Override
    public List<Rating> GetByTitleAndName(String title, String author) throws SQLException {
        return null;
    }

    @Override
    public void deleteEl(Rating el) throws SQLException {
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
    public List getSubList(int position, int count) throws SQLException {
        return null;
    }

    @Override
    public List<Rating> GetSqlRequst(String author, String title, String year) throws SQLException {
        return null;
    }


    public double getRaiting(String title) throws SQLException  {
        Session session = null;
        String sql = "SELECT * FROM \"Rating\" WHERE \"Title\" =:name_title";
        List<Rating> list = new LinkedList<>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Rating.class);
            query.setParameter("name_title", title);
            list = query.list();
            System.out.println("list.size() = " + list.size());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                double rating =0;
                for (Rating el : list)
                    rating = rating + el.getRaiting();
                rating/=list.size();
                return rating;
            }
            return 0.;
        }
    }

    public Rating getUser(String email, String title)  throws SQLException {
        Session session = null;
        String sql = "SELECT * FROM \"Rating\" WHERE \"Title\" =:name_title AND \"Email\" =:name_mail";
        Rating rating = new Rating();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Rating.class);
            query.setParameter("name_title", title);
            query.setParameter("name_mail", email);
            rating =(Rating) query.list().get(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                return rating;
            }
            return rating;
        }
    }


}
