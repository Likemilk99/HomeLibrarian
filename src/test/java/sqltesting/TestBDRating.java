package sqltesting;

import DAO.Factory;
import DAO.InterfaceDao;
import DAO.RatingDAO;
import Data.Rating;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by likemilk on 07.05.2016.
 */
public class TestBDRating {

    @Test
    public void testAddOnetoBD() {
        Rating raiting = new Rating(10, "test@test.com","Title");
        Factory F = new Factory();
        InterfaceDao in = F.getDAO(RatingDAO.class);
        try {
            in.addEl(raiting);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddtoBD() {

        for (int i = 0; i < 30; i++){
            Rating raiting = new Rating(i % 5, "test@test.com", "Title");
            Factory F = new Factory();
            InterfaceDao in = F.getDAO(RatingDAO.class);
            try {
                in.addEl(raiting);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testGetRaiting() {
        Factory F = new Factory();
        RatingDAO in = (RatingDAO) F.getDAO(RatingDAO.class);

        try {
            System.out.println("Rating = " + in.getRaiting("Title"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpgate() {
        Factory F = new Factory();
        RatingDAO in = (RatingDAO) F.getDAO(RatingDAO.class);
        Rating raiting = new Rating(777, "test@test.com", "Title");
        raiting.setId(2);
        try {
                in. updateEl(raiting);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetUser() {
        Factory F = new Factory();
        RatingDAO in = (RatingDAO) F.getDAO(RatingDAO.class);
        try {
            Rating rating = in.getUser("test@test.com","Title");
            System.out.println("rating.getRaiting() = " + rating.getRaiting());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testChangeTitle() {
        Factory F = new Factory();
        RatingDAO in = (RatingDAO) F.getDAO(RatingDAO.class);

        try {

            in.updateTitle("new_Value", "ygufyf");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testChangeEmail() {
        Factory F = new Factory();
        RatingDAO in = (RatingDAO) F.getDAO(RatingDAO.class);

        try {
            in.updateEmail("new_Valu1e", "6666");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
