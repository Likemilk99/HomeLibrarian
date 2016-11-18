package DAO;


import DAO.ImpDAO.IBookDAO;
import DAO.ImpDAO.IRatingDAO;
import DAO.ImpDAO.IUserDAO;

/**
 * Created by likemilk on 04.02.2016.
 */
public class Factory{
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public IUserDAO getUserDAO() {
        return new IUserDAO();
    }

    public IBookDAO getBookDAO() {
        return new IBookDAO();
    }

    public IRatingDAO getRaitingDAO() {
        return new IRatingDAO();
    }

}