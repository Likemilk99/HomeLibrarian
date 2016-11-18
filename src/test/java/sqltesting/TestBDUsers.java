package sqltesting;

import DAO.Factory;
import DAO.InterfaceDao;
import DAO.ImpDAO.IUserDAO;
import Data.ConstParam;
import Data.Users;
import org.hibernate.Session;
import org.junit.Test;
import org.osgi.service.useradmin.User;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
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
        Session session = HibernateUtil.getSessionFactory().openSession();

        Users adminEntity = new Users("admin","admin", "7154255", "iround0@yandex.ru");
        adminEntity.setRole("admin");

        session.beginTransaction();
        //    contactEntity.SetId(15);
        session.save(adminEntity);
        session.getTransaction().commit();

        for (int i=1; i <100;i++) {
            Users contactEntity = new Users("test@test.com","Ivan", "7154255", "test@test.com");
            contactEntity.setEmail("iround"+i+"@yandex.ru");

            session.beginTransaction();
         //    contactEntity.SetId(15);
            session.save(contactEntity);
            session.getTransaction().commit();
        }
        session.close();
    }

    /**
     * Тестирование класса Users работы с БД с использование фабрики
     */
    @Test
    public void testAddtoBDUsingFactory() throws SQLException {
        Us = new Users("Likemilk", "Ivan", "7154255", "iround@yandex.ru");
        F= new Factory();
        IUserDAO iUserDAO = F.getUserDAO();
        iUserDAO.insert(Us);

    }

    @Test
    public void testGetQuery() throws SQLException{
        F= new Factory();
        IUserDAO iUserDAO = F.getUserDAO();
        List<Users> users =iUserDAO.getSubList(30,  ConstParam.TABLE_PAGE_VALUE, Users.class);

        for (Users el : users)
            System.out.println("el.getNickName() = " + el.getFname());

    }

    @Test
    public void testGetCountTable() throws SQLException{
        F= new Factory();
        IUserDAO iUserDAO = F.getUserDAO();
        System.out.println("Count = " + iUserDAO.getCount(Users.class));
    }

    @Test
    public void testGetSqlRequst() {
        String sql = "SELECT * FROM Users WHERE \"Password\" ='7154255'";
        F= new Factory();
        IUserDAO iUserDAO = F.getUserDAO();
        ArrayList<String> list = new ArrayList<>();
        list.add("7154255");
        list.add("iround0@yandex.ru");

        List<Users> users = null;


        for(Users el : users)
            System.out.println(el.getEmail() + " " + el.getNickName() + " " +  el.getPassword() + " " +  el.getRole() + " " +  el.getFname());
    }

    /*@Test
    public void testSub() {
        F= new Factory();
        IUserDAO iUserDAO = F.getUserDAO();
        try {
            iUserDAO.GetByTitleAndName("1","1", Users.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("test ");
    }*/


}
