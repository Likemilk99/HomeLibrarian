package service;

import Data.Books;
import java.sql.SQLException;
import java.util.List;


public interface InterfaceBookSrvice {
     List<Books> GetListBooks(String author, String title, String year) throws SQLException;
     List<Books> GetListBooks(String title, String author) throws SQLException;
}
