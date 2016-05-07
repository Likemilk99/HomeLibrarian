package fb2testing;

import XMlWorker.Fb2OpenXPATH;
import org.junit.Test;
import org.postgresql.util.Base64;

import java.awt.image.BufferedImageOp;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Testing working with Fb2 (getters and Setters)
 */
public class TestFB2XPATH {
    private final String path =  //"tmp/uploads/Злотников Роман - Время вызова. Нужны князья, а не тати.fb2";
     new String("F:\\PROJECTS\\JAVA\\HomeLibrarian\\src\\main\\resources\\doc_xml\\Drakonoboriets_impierii_-_Andriei_Burievoi.fb2");

    /**
     * testing getfname for XPath
     *
     */
    @Test
    public void TestgetFName() {
        Fb2OpenXPATH Fbxpath = new Fb2OpenXPATH(path);
        System.out.print("[" + Fbxpath.GetFName().toString() + "]");
    }

    /**
     * testing book series for XPath
     *
     */
    @Test
    public void TestGetBookSeries() {
        Fb2OpenXPATH Fbxpath = new Fb2OpenXPATH(path);
        System.out.print("[" + Fbxpath.GetBookSeries().toString() + "]");
    }

    /**
     * testing setting fname with XPath
     *
     */
    @Test
    public void TestSetFName() {
        Fb2OpenXPATH Fbxpath = new Fb2OpenXPATH(path);
        Fbxpath.SetFName("Andrei");
       // System.out.print("[" + Fbxpath.GetBookSeries().toString() + "]");
    }

    /**
     * testing setting number of book
     */
    @Test
    public void TestChangeAttr() {
        Fb2OpenXPATH Fbxpath = new Fb2OpenXPATH(path);
        Fbxpath.SetNameSeries("TESTSTSTSTSTST");

    }

    @Test
    public void TestGetImage() {
        Fb2OpenXPATH Fbxpath = new Fb2OpenXPATH(path);
        String str ="jjj";// Fbxpath.GetImage();
        try(FileWriter writer = new FileWriter("notes3.jpeg", false))
        {
            // запись всей строки
            writer.write(str);
            // запись по символам
          //  writer.append('\n');
         //   writer.append('E');

            writer.flush();
            writer.close();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        // byte[] restoredBytes = Base64.decode(String.valueOf(Fbxpath.GetImage().getBytes()));

    }

    @Test
    public void testGetAnnotation() {
        Fb2OpenXPATH Fbxpath = new Fb2OpenXPATH(path);
        System.out.print("[" + Fbxpath.GetAnnotatiome().toString() + "]");
    }
}
