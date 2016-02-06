package sqltesting;

import DAO.BookDAO;
import DAO.UserDAO;
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
        InterfaceDao InBook = F.getDAO(BookDAO.class );
        InterfaceDao InUser = F.getDAO(UserDAO.class );

        assertEquals(InBook.WhoIsIt().toString(),"Books");
        assertEquals(InUser.WhoIsIt().toString(), "Users");
    }
}
