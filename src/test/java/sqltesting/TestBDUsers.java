package sqltesting;

import Data.Users;
import org.hibernate.Session;
import org.junit.Test;
import util.HibernateUtil;

/**
 * Testing working with BD
 */
public class TestBDUsers {

    /**
     * Testing working with BD for class "Users"
     */
    @Test
    public void testAddtoBD() {
        System.out.println("Hibernate tutorial");

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Users contactEntity = new Users();
        contactEntity.setFname("1");
       // contactEntity.SetId(15);
        session.save(contactEntity);
        session.getTransaction().commit();
        session.close();
    }
}
