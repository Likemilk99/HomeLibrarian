package XMlWorker;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPath;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


/**
 * Working wirh fb2 formate using XPath
 * Documentation - http://gribuser.ru/xml/fictionbook/shema_comments.html
 * Tutorial XPath - http://viralpatel.net/blogs/java-xml-xpath-tutorial-parse-xml/
 */
public class Fb2OpenXPATH {
    private Document xmlDoc = null;
    private XPath xpath = null;
    private String pathtoxml;

    private final String Err = "None";

    /**
     * Open fb2 and get xpath
     *
     * @param path
     */
    public Fb2OpenXPATH(String path) {
        pathtoxml = path;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            File xmlFile = new File(path);
            xmlDoc = builder.parse(new FileInputStream(xmlFile));//.getBytes()));
            xpath = XPathFactory.newInstance().newXPath();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // GETTEERS
    /**
     * Get Name from xml
     *
     * @return
     */
    public String GetFName() {
        try {
            return xpath.compile("/FictionBook/description/title-info/author/first-name/text()").evaluate(xmlDoc);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return Err;
    }

    /**
     * Get Annotation
     *
     * @return
     */
    public String GetAnnotatiome() {
        try {
            return xpath.compile("/FictionBook/description/title-info/annotation").evaluate(xmlDoc);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return Err;
    }

    /**
     * Get Middlename from xml
     *
     * @return
     */
    public String GetMName() {
        try {
            return xpath.compile("/FictionBook/description/title-info/author/middle-name/text()").evaluate(xmlDoc);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return Err;
    }

    /**
     * Get Last from xml
     *
     * @return
     */
    public String GetLName() {
        try {
            return xpath.compile("/FictionBook/description/title-info/author/last-name/text()").evaluate(xmlDoc);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return Err;
    }

    /**
     * Get any tag using path like "/FictionBook/description/title-info/author/last-name/text()"....  from xml
     *
     * @return
     */
    public String GetTagByPath() {
        try {
            return xpath.compile("/FictionBook/description/title-info/author/last-name/text()").evaluate(xmlDoc);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return Err;
    }

    /**
     * Get book title
     *
     * @return
     */
    public String GetTitle() {
        try {
            return xpath.compile("/FictionBook/description/title-info/book-title/text()").evaluate(xmlDoc);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return Err;
    }

    /**
     * Get Date
     *
     * @return
     */
    public String GetDate() {
        try {
            return xpath.compile("/FictionBook/description/title-info/date/text()").evaluate(xmlDoc);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return Err;
    }

    /**
     * Get Book Series
     *
     * @return
     */
    public String GetBookSeries() {
        try {
            return xpath.compile("/FictionBook/description/title-info/sequence/@name").evaluate(xmlDoc) + " " + xpath.compile("/FictionBook/description/title-info/sequence/@number").evaluate(xmlDoc);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return Err;
    }

    public String GetImage() {
        try {
            return xpath.compile("/FictionBook/description/title-info/binary/text()").evaluate(xmlDoc);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return Err;
    }


    // SETTERS

    /**
     *  Set fname
     * @param newParamet
     */
    public void SetFName(String newParamet) {
        try {
            String expression = "/FictionBook/description/title-info/author/first-name";
            Node node = (Node) xpath.evaluate(expression, xmlDoc, XPathConstants.NODE);

            node.setTextContent(newParamet.toString());


            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(xmlDoc), new StreamResult(new File(pathtoxml.toString())));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  Set Mname
     * @param newParamet
     */
    public void SetMName(String newParamet) {
        try {
            String expression = "/FictionBook/description/title-info/author/middle-name";
            Node node = (Node) xpath.evaluate(expression, xmlDoc, XPathConstants.NODE);

            node.setTextContent(newParamet.toString());


            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(xmlDoc), new StreamResult(new File(pathtoxml.toString())));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  Set fname
     * @param newParamet
     */
    public void SetLName(String newParamet) {
        try {
            String expression = "/FictionBook/description/title-info/author/last-name";
            Node node = (Node) xpath.evaluate(expression, xmlDoc, XPathConstants.NODE);

            node.setTextContent(newParamet.toString());


            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(xmlDoc), new StreamResult(new File(pathtoxml.toString())));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  Set fname
     * @param newParamet
     */
    public void SetTitle(String newParamet) {
        try {
            String expression = "/FictionBook/description/title-info/title-book";
            Node node = (Node) xpath.evaluate(expression, xmlDoc, XPathConstants.NODE);

            node.setTextContent(newParamet.toString());


            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(xmlDoc), new StreamResult(new File(pathtoxml.toString())));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  Set fname
     * @param newParamet
     */
    public void SetDate(String newParamet) {
        try {
            String expression = "/FictionBook/description/title-info/date";
            Node node = (Node) xpath.evaluate(expression, xmlDoc, XPathConstants.NODE);

            node.setTextContent(newParamet.toString());


            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(xmlDoc), new StreamResult(new File(pathtoxml.toString())));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set new name of book in serie
     * @param newParamet
     */
    public void SetNameSeries(String newParamet) {
        try {
            String expression = "/FictionBook/description/title-info/sequence/@name";
            Attr result  = (Attr) xpath.evaluate(expression, xmlDoc, XPathConstants.NODE);

            result.setValue(newParamet.toString());


            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(xmlDoc), new StreamResult(new File(pathtoxml.toString())));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set new number of book in serie
     * @param newParamet
     */
    public void SetNumberSeries(String newParamet) {
        try {
            String expression = "/FictionBook/description/title-info/sequence/@number";
            Attr result  = (Attr) xpath.evaluate(expression, xmlDoc, XPathConstants.NODE);

            result.setValue(newParamet.toString());


            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(xmlDoc), new StreamResult(new File(pathtoxml.toString())));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

