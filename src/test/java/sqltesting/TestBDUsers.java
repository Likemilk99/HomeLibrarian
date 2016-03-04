package sqltesting;

import DAO.Factory;
import DAO.InterfaceDao;
import DAO.UserDAO;
import Data.Users;
import org.hibernate.Session;
import org.junit.Test;
import util.HibernateUtil;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;

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
    public void TestAddtoBD() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Users contactEntity = new Users("Ivan", "7154255", "iround@yandex.ru");
        contactEntity.setFname("1");
       // contactEntity.SetId(15);
        session.save(contactEntity);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Тестирование класса Users работы с БД с использование фабрики
     */
    @Test
    public void TestAddtoBDUsingFactory() throws SQLException {
        Us = new Users("Ivan", "7154255", "iround@yandex.ru");
      //  Us.Users("Ivan", "7154255", "iround@yandex.ru");
        F= new Factory();
        InterfaceDao InUser = F.getDAO(UserDAO.class );
        InUser.addEl(Us);
    }
}
