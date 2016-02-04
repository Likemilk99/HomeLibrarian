package DAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by likemilk on 04.02.2016.
 */
public interface InterfaceDao<T>{
    /**
     *
     * @param El Добавление обьекта  <b>EL<b>
     * @throws SQLException
     */
    void addUser(T El) throws SQLException;

    /**
     *
     * @param El Обновить обьект  <b>EL<b>
     * @throws SQLException
     */
    void updateUser(T El) throws SQLException;

    /**
     * Получить обьекта по id
     * @param id идентификатор
     * @return возвращает обьект заданного типа
     * @throws SQLException
     */
    // T getUserById(Integer id) throws SQLException;
    T getUserById(String id) throws SQLException;
    /**
     * Получить список обьектов
     * @return список
     * @throws SQLException
     */
    List getAllUsers() throws SQLException;

    /**
     * Удалить элемент из БД
     * @param El
     * @throws SQLException
     */
    void deleteUser(T El) throws SQLException;

}
