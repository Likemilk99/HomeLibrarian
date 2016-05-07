package sqltesting;

import DAO.BookDAO;
import DAO.Factory;
import DAO.InterfaceDao;
import DAO.UserDAO;
import Data.Books;
import Data.Users;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.Test;
import util.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        int year = 1980;
        for (int i = 0; i < 10; i++) {
            year++;
            B = new Books("Армагеддон",
                    "",
                    Integer.toString(year),
                    "Дмитрий Иванович",
                    "",
                    "./resource/default/Logo.png",
                    "");
            //B.Books();
            F = new Factory();
            InterfaceDao InBooks = F.getDAO(BookDAO.class);
            InBooks.addEl(B);
            //test
             }

    }

    @Test
    public void testGetByParametr() {
        String sql = "SELECT * FROM \"Book\"";//" WHERE Nickname = :employee_id";
        Session session = null;
        List<Books> books =  new LinkedList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Books.class);
            //query.setParameter("employee_id", "iround2@yandex.ru");
            books = query.list();

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                //   return users;
            }
            //   return users;
        }
        System.out.println("books.size() = " + books.size());

        for(Books el : books)
            System.out.println("el.getEmail() = " + el.getAuthor());
    }


    @Test
    public void testGetSqlRequst() {
        String sql = "SELECT * FROM Users WHERE \"Password\" ='7154255'";
        F= new Factory();
        InterfaceDao InBooks = F.getDAO(BookDAO.class );
        ArrayList<String> list = new ArrayList<>();
        list.add("7154255");
        list.add("iround0@yandex.ru");

        List<Books> users = null;
        try {
          //  users = InBooks.GetSqlRequst("Иван Иванович", "Пришлый", "2007");
            users = InBooks.GetSqlRequst("Иван Иванов", "", "2007");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        for(Books el : users)
            System.out.println(el.getAuthor() + " " + el.getTitle() + " " + el.getYear());
    }

    @Test
    public void testSub() {
        F= new Factory();
        InterfaceDao books = F.getDAO(BookDAO.class );
        try {
            books.GetByTitleAndName("1","1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("test ");
    }

    @Test
    public void getCount() {
        F= new Factory();
        BookDAO InBooks = new BookDAO();//F.getDAO(BookDAO.class );
        try {
            System.out.println("F = " + InBooks.getCount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
