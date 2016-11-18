package DAO.ImpDAO;

import DAO.AbstractDao;
import DAO.InterfaceBook;
import DAO.InterfaceDao;
import Data.Books;
import Data.Guest;
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
 * Реализация интерфейса для книг
 */
public class IBookDAO extends AbstractDao<Books> implements InterfaceDao<Books>, InterfaceBook{

    @Override
    public String WhoIsIt() {
        return "Books";
    }


    @Override
    public List<Books> GetByTitleAndName(String title, String author)  throws SQLException {
        String sql = "SELECT * FROM \"Book\" WHERE \"Title\" =:name_title AND \"Author\" =:name_author";
        List<Books> books =  new LinkedList<>();

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Books.class);
            query.setParameter("name_title", title);
            query.setParameter("name_author", author);
            books = query.list();

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            return books;
        }
    }

    @Override
    public List<Books> GetSqlRequst(String author, String title, String year) throws SQLException {

        String sql = "SELECT * FROM \"Book\" WHERE ";//" \"email\" ='iround0@yandex.ru'";
        if(!author.isEmpty() ||!title.isEmpty() || !year.isEmpty() ) {
            if (!author.isEmpty())
                sql = sql + "\"Author\"" + " LIKE '%" + author + "%'";
            if (!title.isEmpty() || !year.isEmpty()) {
                if (!author.isEmpty()) {
                    sql = sql + " AND ";
                }
                if (!title.isEmpty()) {
                    sql = sql + "\"Title\"" + " LIKE '%" + title + "%'";
                    if (!year.isEmpty()) {
                        sql = sql + " AND ";
                        sql = sql + "\"Year\"" + " LIKE '%" + year + "%'";
                    }
                } else if (!year.isEmpty()) {
                    sql = sql + "\"Year\"" + " LIKE '%" + year + "%'";
                }
            }

            List<Books> users = new LinkedList<>();
            try(Session session = HibernateUtil.getSessionFactory().openSession()) {
                SQLQuery query = session.createSQLQuery(sql);
                query.addEntity(Books.class);
                users = query.list();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
            } finally {
                return users;
            }
        }
        return new ArrayList<>();
    }

}
