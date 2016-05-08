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
        Rating raiting = new Rating(5, "test@test.com",1);
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
            Rating raiting = new Rating(i % 5 + 1, "test@test.com", i);
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
            System.out.println("Rating = " + in.getRaiting(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        Factory F = new Factory();
        RatingDAO in = (RatingDAO) F.getDAO(RatingDAO.class);

        try {
            Rating rat = in.getUser("test@test.com",1);
            rat.setRaiting(1);
            in.updateEl(rat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetUser() {
        Factory F = new Factory();
        RatingDAO in = (RatingDAO) F.getDAO(RatingDAO.class);
        try {
            Rating rating = in.getUser("test@test.com",1);
            System.out.println("rating.getRaiting() = " + rating.getRaiting());
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
