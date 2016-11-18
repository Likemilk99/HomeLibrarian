package DAO;

import Data.Users;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by home on 17.11.2016.
 */
public interface InterfaceUser {
    Users getElById(String el) throws SQLException;
    void getByName(String name) throws SQLException;
    List<Users> GetSqlRequst(String author, String title, String year) throws SQLException;
    boolean isUsernameExist(String username) throws SQLException;
}
