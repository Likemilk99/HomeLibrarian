package service.IService;

import DAO.Factory;
import DAO.ImpDAO.IBookDAO;
import Data.Books;

import service.InterfaceBookSrvice;
import service.InterfaceService;
import java.sql.SQLException;
import java.util.List;


public class IBookService implements InterfaceBookSrvice, InterfaceService <Books> {
    private IBookDAO iBookDAO;

    public IBookService() {
        iBookDAO = Factory.getInstance().getBookDAO();
    }

    @Override
    public List<Books> GetListBooks(String author, String title, String year) throws SQLException{
        return iBookDAO.GetSqlRequst(author, title, year) ;
    }

    @Override
    public List<Books> GetListBooks(String title, String author) throws SQLException{
        return iBookDAO.GetByTitleAndName(title, author);
    }

    @Override
    public void insert(Books el) throws SQLException {
        iBookDAO.insert(el);
    }

    @Override
    public void update(Books el) throws SQLException {
        iBookDAO.update(el);
    }

    @Override
    public List<Books> getAll() throws SQLException {
        return iBookDAO.getAll(Books.class);
    }

    @Override
    public void delete(Books el) throws SQLException {
        iBookDAO.delete(el);
    }

    @Override
    public long getCount() throws SQLException {
      return iBookDAO.getCount(Books.class);
    }

    @Override
    public List<Books> getListByPosition(int position, int count) throws SQLException {
        return iBookDAO.getSubList(position, count, Books.class);
    }
}
