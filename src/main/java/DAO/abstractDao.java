package DAO;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import util.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public abstract class AbstractDao<T extends Object> implements InterfaceDao<T>{

    @Override
    public String WhoIsIt() {
        return "abstractClass";
    }

    @Override
    public void insert(T el) throws SQLException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(el);
            session.getTransaction().commit();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        }
    }

    @Override
    public void update(T el) throws SQLException {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();
            session.update(el);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        }
    }

    @Override
    public List<T> getAll(Class <T> clazz) throws SQLException {
        List<T> books = new ArrayList<>();

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            books = session.createCriteria(clazz)
                            .list();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            return books;
        }
    }


    public void delete(T el) throws SQLException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(el);
            session.getTransaction().commit();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        }
    }

    @Override
    public long getCount( Class<T> clazz) throws SQLException {
        long count = 0;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            count = (long) session.createCriteria(clazz)
                                    .setProjection(Projections.rowCount())
                                    .uniqueResult();
            session.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            return count;
        }
    }

    @Override
    public List getSubList(int position, int count, Class<T> clazz) throws SQLException {
        List<T> users =  new LinkedList<>();

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            users = session.createCriteria(clazz)
                    .setMaxResults(count)
                    .setFirstResult(position)
                    .list();

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            return users;
        }
    }
}
