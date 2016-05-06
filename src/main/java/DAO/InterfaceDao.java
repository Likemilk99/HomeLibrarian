package DAO;

import Data.Books;
import Data.Users;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by likemilk on 04.02.2016.
 */
public interface InterfaceDao<T>{
    //int COUNT_ROWS = 8;
    /**
     *
     * Чья реализауия.
     * @return
     */
    String WhoIsIt();

    /**
     *
     * @param El Добавление обьекта  <b>EL<b>
     * @throws SQLException
     */
    void addEl(T El) throws SQLException;

    /**
     *
     * @param El Обновить обьект  <b>EL<b>
     * @throws SQLException
     */
    void updateEl(T El) throws SQLException;

    /**
     * Получить обьекта по id
     * @param id идентификатор
     * @return возвращает обьект заданного типа
     * @throws SQLException
     */
    // T getUserById(Integer id) throws SQLException;
    T getElById(String id) throws SQLException;
    /**
     * Получить список обьектов
     * @return список
     * @throws SQLException
     */
    List getAllEls() throws SQLException;

    List<T> GetByTitleAndName(String title, String author)  throws SQLException;

    /**
     * Удалить элемент из БД
     * @param El
     * @throws SQLException
     */
    void deleteEl(T El) throws SQLException;

    long getCount() throws  SQLException;

    List getSubList(int position, int count) throws SQLException;

    public List<T> GetSqlRequst(String author, String title, String year) throws SQLException;
}
