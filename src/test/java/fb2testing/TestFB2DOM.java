package fb2testing;

import Data.Books;
import XMlWorker.Fb2OpenDOM;
import org.junit.Test;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Created by likemilk on 06.01.2016.
 */

public class TestFB2DOM {

    private Fb2OpenDOM Fxml;
    private final String path = new String("F:\\PROJECTS\\JAVA\\HomeLibrarian\\src\\main\\resources\\doc_xml\\Drakonoboriets_impierii_-_Andriei_Burievoi.fb2");

    /**
     * Тест открытия xml файла
     * @throws Exception
     */
    @Test
    public void TestOpenFb2() throws Exception {
        Fxml = new Fb2OpenDOM(path);
        //Document Doc = Fxml.getDoc();
    }

    /**
     * Testing working with Fb2 (DOM method)
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * NOT COMLETED, USE Fb2OpenXPATH
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * @throws Exception
     */
    @Test
    public void TestGetAllChildNodes() throws Exception {
        TestOpenFb2();
        Fxml.getElements(path);
    }

    @Test
    public void TestGetNods() throws Exception {
        {
            Fxml = new Fb2OpenDOM(path);
        }
    }

    @Test
    public void TestGetFullName() throws Exception {
        Fxml = new Fb2OpenDOM(path);
        String str = Fxml.getFullName();
        System.out.print(str.toString());
    }

    @Test
    public void TestGetTitleBook() throws  Exception {
        Fxml = new Fb2OpenDOM(path);
        String str = Fxml.getTitle();
        System.out.print(str.toString());
    }

    @Test
    public void TestGetYear() throws  Exception {
        Fxml = new Fb2OpenDOM(path);
        String str = Fxml.getYear();
        System.out.print(str.toString());
    }

    @Test
    public void TestGetBook() throws  Exception {
        Fxml = new Fb2OpenDOM(path);
        Books Book = Fxml.getBook();
        //System.out.print(Books.GetAuthor());
    }

    @Test
    public void TestGetAnotation() throws  Exception {
        Fxml = new Fb2OpenDOM(path);
        String str = Fxml.getAnnotation();
        System.out.print(str.toString());
    }

    @Test
    public void TestGetImage() throws  Exception {
        Fxml = new Fb2OpenDOM(path);
        BufferedImage image = Fxml.getImage();
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);

    }
}
