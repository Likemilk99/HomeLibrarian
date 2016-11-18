package service.IService;

import DAO.Factory;
import DAO.ImpDAO.IRatingDAO;
import Data.Rating;
import service.InterfaceRaitingService;
import service.InterfaceService;

import java.sql.SQLException;
import java.util.List;

public class IRaitingService implements InterfaceRaitingService, InterfaceService <Rating> {
    private IRatingDAO iRatingDAO;

    public IRaitingService() { iRatingDAO = Factory.getInstance().getRaitingDAO();
    }

    @Override
    public double getRaiting(Integer book_id) throws SQLException  {
        return iRatingDAO.getRaiting(book_id);
    }

    @Override
    public double getRaiting(String email, Integer book_id) throws  SQLException {
        return iRatingDAO.getRaiting(email, book_id);
    }

    @Override
    public Rating getUser(String email, Integer book_id)  throws SQLException {
        return iRatingDAO.getUser(email, book_id);
    }

    @Override
    public void updateEmail(String oldName, String newName) throws SQLException {
        iRatingDAO.updateEmail(oldName, newName);
    }

    @Override
    public void insert(Rating el) throws SQLException {
        iRatingDAO.insert(el);
    }

    @Override
    public void update(Rating el) throws SQLException {
        iRatingDAO.update(el);
    }

    @Override
    public List<Rating> getAll() throws SQLException {
        return iRatingDAO.getAll(Rating.class);
    }

    @Override
    public void delete(Rating el) throws SQLException {
        iRatingDAO.delete(el);
    }

    @Override
    public long getCount() throws SQLException {
        return iRatingDAO.getCount(Rating.class);
    }

    @Override
    public List<Rating> getListByPosition(int position, int count) throws SQLException {
        return iRatingDAO.getSubList(position, count, Rating.class);
    }
}
