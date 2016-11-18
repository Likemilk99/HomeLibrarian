package service;

import Data.Rating;
import java.sql.SQLException;


public interface InterfaceRaitingService {
     double getRaiting(Integer book_id) throws SQLException;
     double getRaiting(String email, Integer book_id) throws  SQLException;
     Rating getUser(String email, Integer book_id)  throws SQLException;
     void updateEmail(String oldName, String newName) throws SQLException;
}
