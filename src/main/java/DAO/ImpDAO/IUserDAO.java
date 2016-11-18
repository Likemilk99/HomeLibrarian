package DAO.ImpDAO;

import DAO.InterfaceDao;
import DAO.InterfaceUser;
import Data.Users;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Реализация интерфейса для пользователей
 */
public class IUserDAO extends AbstractDao<Users> implements InterfaceDao<Users>, InterfaceUser {

    @Override
    public String WhoIsIt() {
        return "Users";
    }

    @Override
    public Users getElById(String el) throws SQLException {
       // Users user = new Users("LikeMilk", "Ivan", "7154255", "iround@yandex.ru");
        Users user = new Users();

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            user = session.get(Users.class, el);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            return user;
        }

    }

    @Override
    public void getByName(String name) throws SQLException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.getNamedProcedureCall(name);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        }
    }


    @Override
    public List<Users> GetSqlRequst(String author, String title, String year) throws SQLException {
        String sql = "SELECT * FROM Users WHERE ";//" \"email\" ='iround0@yandex.ru'";
        List<Users> users =  new LinkedList<>();

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Users.class);
            users = query.list();

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            return users;
        }
    }

    @Override
    public boolean isUsernameExist(String username) throws  SQLException {
        String sql = "SELECT * FROM Users WHERE \"Nickname\" =:nickname";
        List<Users> users =  new LinkedList<>();

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Users.class);
            query.setParameter("nickname", username);
            users = query.list();

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            return users.size() > 0;
        }
    }

}
