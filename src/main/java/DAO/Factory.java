package DAO;

/**
 * Created by likemilk on 04.02.2016.
 */
public class Factory {

    private static UserDAO studentDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public UserDAO getStudentDAO(){
        if (studentDAO == null){
            studentDAO = new UserDAO();
        }
        return studentDAO;
    }
}