package DAO;

import Data.Books;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by home on 17.11.2016.
 */
public interface InterfaceBook {
    List<Books> GetSqlRequst(String author, String title, String year) throws SQLException;
    List<Books> GetByTitleAndName(String title, String author)  throws SQLException;

}
