package DAO.ImpDAO;

import DAO.InterfaceDao;
import DAO.InterfaceRaiting;
import Data.Rating;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by likemilk on 07.05.2016.
 */
public class IRatingDAO extends AbstractDao<Rating> implements InterfaceDao<Rating>, InterfaceRaiting {
    @Override
    public String WhoIsIt() {
        return "Rating";
    }

    @Override
    public double getRaiting(Integer book_id) throws SQLException  {

        String sql = "SELECT * FROM \"Rating\" WHERE book =:book_id";
        List<Rating> list = new LinkedList<>();

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Rating.class);
            query.setParameter("book_id", book_id);
            list = query.list();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            double rating = 0.;
            for (Rating el : list)
                rating = rating + el.getRaiting();
            if (list.size() > 0)
                rating /= list.size();
            return rating;
        }
    }

    @Override
    public double getRaiting(String email, Integer book_id) throws  SQLException {
        return getUser(email, book_id).getRaiting();
    }

    @Override
    public Rating getUser(String email, Integer book_id)  throws SQLException {
        String sql = "SELECT * FROM \"Rating\" WHERE book =:book_id AND \"Email\" =:name_mail";
        Rating rating = new Rating();
        rating.setEmail(email);
        rating.setBook(book_id);

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Rating.class);
            query.setParameter("book_id", book_id);
            query.setParameter("name_mail", email);

            if(query.list().size() > 0)
                rating =(Rating) query.list().get(0);
            else {
                insert(rating);
                rating = getUser(email, book_id);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            return rating;
        }
    }

    @Override
    public void updateEmail(String oldName, String newName) throws SQLException {

        String sql = "SELECT * FROM \"Rating\" WHERE \"Email\" =:name_mail";
        List<Rating> rating = new ArrayList<>();

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Rating.class);
            query.setParameter("name_mail", oldName);
            rating =query.list();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        }
        for (Rating el : rating){
            el.setEmail(newName);
            try {
                update(el);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
