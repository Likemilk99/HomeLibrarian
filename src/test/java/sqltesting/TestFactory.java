package sqltesting;

import DAO.ImpDAO.IBookDAO;
import DAO.ImpDAO.IUserDAO;
import DAO.Factory;
import DAO.InterfaceDao;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Тестированеи работы фабрики
 */
public class TestFactory {
    private Factory F= new Factory();

    @Test
    public void TestFactory(){
        IUserDAO iUserDAO = F.getUserDAO();
        IBookDAO iBookDAO = F.getBookDAO();

        assertEquals(iUserDAO.WhoIsIt().toString(),"Books");
        assertEquals(iBookDAO.WhoIsIt().toString(), "Users");
    }
}
