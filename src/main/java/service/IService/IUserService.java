package service.IService;

import DAO.Factory;
import DAO.ImpDAO.IUserDAO;
import Data.Users;
import service.InterfaceService;
import service.InterfaceUserService;

import java.sql.SQLException;
import java.util.List;

public class IUserService implements InterfaceUserService, InterfaceService<Users> {
    private IUserDAO iUserDAO;

    public IUserService() {
        this.iUserDAO = Factory.getInstance().getUserDAO();
    }

    @Override
    public Users getUserById(String el) throws SQLException {
        return iUserDAO.getElById(el);
    }


    @Override
    public List<Users> GetListUser(String author, String title, String year) throws SQLException {
        return iUserDAO.GetSqlRequst(author, title, year);
    }

    @Override
    public boolean isUsernameExist(String username) throws  SQLException {
        return iUserDAO.isUsernameExist(username);
    }

    @Override
    public void insert(Users el) throws SQLException {
        iUserDAO.insert(el);
    }

    @Override
    public void update(Users el) throws SQLException {
        iUserDAO.update(el);
    }

    @Override
    public List<Users> getAll() throws SQLException {
        return iUserDAO.getAll(Users.class);
    }

    @Override
    public void delete(Users el) throws SQLException {
        iUserDAO.delete(el);
    }

    @Override
    public long getCount() throws SQLException {
        return iUserDAO.getCount(Users.class);
    }

    @Override
    public List<Users> getListByPosition(int position, int count) throws SQLException {
        return iUserDAO.getSubList(position, count, Users.class);
    }

    @Override
    public Users getElById(String id) throws SQLException {
        return iUserDAO.getElById(id);
    }
}
