package sqltesting;

import DAO.Factory;
import DAO.InterfaceDao;
import DAO.UserDAO;
import Data.Address;
import Data.Users;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl;
import org.junit.Test;
import util.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Testing working with BD
 */
public class TestBDUsers {
    private Factory F ;
    private Users Us;

    /**
     * Testing working with BD for class "Users"
     */
    @Test
    public void testAddtoBD() {
        for (int i=0; i <100;i++) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Users contactEntity = new Users("test@test.com","Ivan", "7154255", "test@test.com");

            contactEntity.setEmail("iround"+i+"@yandex.ru");
         //    contactEntity.SetId(15);
            session.save(contactEntity);
            session.getTransaction().commit();

            session.close();
        }
    }

    /**
     * Тестирование класса Users работы с БД с использование фабрики
     */
    @Test
    public void testAddtoBDUsingFactory() throws SQLException {
        Us = new Users("Likemilk", "Ivan", "7154255", "iround@yandex.ru");
        F= new Factory();
        InterfaceDao InUser = F.getDAO(UserDAO.class );
        InUser.addEl(Us);

    }

    @Test
    public void testGetQuery() throws SQLException{
        F= new Factory();
        InterfaceDao InUser = F.getDAO(UserDAO.class );
        List<Users> users =InUser.getSubList(30);

        for (Users el : users)
            System.out.println("el.getNickName() = " + el.getFname());

    }

    @Test
    public void testGetCountTable() throws SQLException{
        F= new Factory();
        InterfaceDao InUser = F.getDAO(UserDAO.class );
        System.out.println("Count = " + InUser.getCount());
    }

    @Test
    public void testGetSqlRequst() {
        String sql = "SELECT * FROM Users WHERE \"Password\" ='7154255'";
        F= new Factory();
        InterfaceDao InUser = F.getDAO(UserDAO.class );
        ArrayList<String> list = new ArrayList<>();
        list.add("7154255");
        list.add("iround0@yandex.ru");

        List<Users> users = null;


        for(Users el : users)
            System.out.println(el.getEmail() + " " + el.getNickName() + " " +  el.getPassword() + " " +  el.getRole() + " " +  el.getFname());
    }

    @Test
    public void testSub() {
        F= new Factory();
        InterfaceDao InUser = F.getDAO(UserDAO.class );
        try {
            InUser.GetByTitleAndName("1","1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("test ");
    }


}
