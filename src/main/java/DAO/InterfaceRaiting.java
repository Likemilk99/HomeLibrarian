package DAO;

import Data.Rating;

import java.sql.SQLException;

/**
 * Created by home on 17.11.2016.
 */
public interface InterfaceRaiting {
    double getRaiting(Integer book_id) throws SQLException;
    double getRaiting(String email, Integer book_id) throws  SQLException;
    Rating getUser(String email, Integer book_id)  throws SQLException;
    void updateEmail(String oldName, String newName) throws SQLException;
}
