package fb2testing;

import XMlWorker.Fb2OpenXPATH;
import org.junit.Test;

/**
 * Testing working with Fb2 (getters and Setters)
 */
public class TestFB2XPATH {
    private final String path = new String("F:\\PROJECTS\\JAVA\\newtest\\src\\main\\resources\\doc_xml\\Drakonoboriets_impierii_-_Andriei_Burievoi.fb2");

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
}
