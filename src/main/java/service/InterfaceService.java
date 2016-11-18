package service;

import Data.Books;

import java.sql.SQLException;
import java.util.List;


public interface InterfaceService<T> {
    void insert(T el) throws SQLException ;
    void update(T el) throws SQLException;
    List<T> getAll() throws SQLException ;
    void delete(T el) throws SQLException ;
    long getCount() throws SQLException ;
    List<T> getListByPosition(int position, int count) throws SQLException ;

}
