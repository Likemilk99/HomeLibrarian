package sqltesting;

import DAO.Factory;
import DAO.InterfaceDao;
import DAO.UserDAO;
import Data.Users;
import org.hibernate.Session;
import org.junit.Test;
import util.HibernateUtil;

import java.sql.SQLException;
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
        for (int i=0; i <1000;i++) {
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


}
