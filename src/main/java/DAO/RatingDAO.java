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
    public boolean isUsernameExist(String username) throws  SQLException {

        return true;
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


    public double getRaiting(Integer book_id) throws SQLException  {
        Session session = null;
        String sql = "SELECT * FROM \"Rating\" WHERE book =:book_id";
        List<Rating> list = new LinkedList<>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Rating.class);
            query.setParameter("book_id", book_id);
            list = query.list();
            //System.out.println("list.size() = " + list.size());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            double rating =0.;
            if (session != null && session.isOpen()) {
                session.close();
                for (Rating el : list)
                    rating = rating + el.getRaiting();
                if(list.size() > 0)
                    rating/=list.size();
            }
            return rating;
        }
    }

    public double getRaiting(String email, Integer book_id) throws  SQLException {
        return getUser(email, book_id).getRaiting();
    }

    public Rating getUser(String email, Integer book_id)  throws SQLException {
        Session session = null;
        String sql = "SELECT * FROM \"Rating\" WHERE book =:book_id AND \"Email\" =:name_mail";
        Rating rating = new Rating();
        rating.setEmail(email);
        rating.setBook(book_id);
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Rating.class);
            query.setParameter("book_id", book_id);
            query.setParameter("name_mail", email);

            if(query.list().size() > 0)
                rating =(Rating) query.list().get(0);
            else {
                if (session != null && session.isOpen())
                    session.close();
                addEl(rating);
                rating = getUser(email, book_id);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen())
                session.close();
            return rating;
        }
    }

    public void updateEmail(String oldName, String newName) throws SQLException {
        Session session = null;
        String sql = "SELECT * FROM \"Rating\" WHERE \"Email\" =:name_mail";
        List<Rating> rating = new ArrayList<>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Rating.class);
            query.setParameter("name_mail", oldName);
            rating =query.list();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        for (Rating el : rating){
            el.setEmail(newName);
            try {
                updateEl(el);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
