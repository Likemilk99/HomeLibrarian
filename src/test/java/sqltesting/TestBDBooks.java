package sqltesting;

import DAO.BookDAO;
import DAO.Factory;
import DAO.InterfaceDao;
import DAO.UserDAO;
import Data.Books;
import Data.Users;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by likemilk on 07.02.2016.
 */
public class TestBDBooks {
    private Factory F ;
    private Books B;

    /**
     * Тестирование класса Books работы с БД с использование фабрики
     */
    @Test
    public void TestAddtoBDUsingFactory() throws SQLException {
      for (int i=0; i<1000 ; i++) {
          B = new Books();
          B.Books("Пришлый", "2006", "Иван Иванович", "C://test//pic//1.png");
          F = new Factory();
          InterfaceDao InUser = F.getDAO(BookDAO.class);
          InUser.addEl(B);
          //test
      }
    }
}
