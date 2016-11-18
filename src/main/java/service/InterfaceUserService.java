package service;

import Data.Users;

import java.sql.SQLException;
import java.util.List;


public interface InterfaceUserService {
    Users getUserById(String el) throws SQLException;
    List<Users> GetListUser(String author, String title, String year) throws SQLException;
    boolean isUsernameExist(String username) throws SQLException;
    public Users getElById(String id) throws SQLException;
}
