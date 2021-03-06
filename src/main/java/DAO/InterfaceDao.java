package DAO;

import Data.Books;
import Data.Users;
import org.omg.CORBA.IRObject;

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
    void insert(T El) throws SQLException;

    /**
     *
     * @param El Обновить обьект  <b>EL<b>
     * @throws SQLException
     */
    void update(T El) throws SQLException;

    /**
     * Получить обьекта по id
     * @param id идентификатор
     * @return возвращает обьект заданного типа
     * @throws SQLException
     */

    /**
     * Получить список обьектов
     * @return список
     * @throws SQLException
     */
    List getAll(Class <T> clazz) throws SQLException;

    /**
     * Удалить элемент из БД
     * @param El
     * @throws SQLException
     */
    void delete(T El) throws SQLException;

    long getCount(Class<T> clazz) throws  SQLException;

    List getSubList(int position, int count, Class<T> clazz) throws SQLException;
}
