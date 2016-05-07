package DAO;



/**
 * Created by likemilk on 04.02.2016.
 */
public class Factory{

    private static UserDAO DAO = null;
    //private static BookDAO DAO = null;
    //  START WTF ???
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    //  END WTF
    public InterfaceDao getDAO(Object Obj){
       if(Obj== UserDAO.class ) return new UserDAO();
       if(Obj == BookDAO.class)return new BookDAO();
       if(Obj == RatingDAO.class)return new RatingDAO();
        return null;
    }
}