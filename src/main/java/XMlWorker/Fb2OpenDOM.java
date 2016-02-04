package XMlWorker;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import Data.Books;
import org.w3c.dom.Document;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Working with Fb2 (DOM method)
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * NOT COMLETED, USE Fb2OpenXPATH
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * GET IMAGE HREF http://stackoverflow.com/questions/15155582/how-to-get-href-value-from-xml-file-on-java-eclipse-dom
 */
public class Fb2OpenDOM {
    private Document Doc;
    private NodeList NList;
    private Books Book;

    public Fb2OpenDOM(String path) throws Exception {
        try {
            File xmlFile = new File(path);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Doc = documentBuilder.parse(xmlFile);
            Doc.getDocumentElement().normalize();

            NList = Doc.getElementsByTagName(Doc.getDocumentElement().getChildNodes().item(1).getNodeName());
           /* System.out.println("Корневой элемент: " + document.getDocumentElement().getNodeName());
            NodeList nodeList = document.getElementsByTagName(document.getDocumentElement().getChildNodes().item(1).getNodeName());
            System.out.println("--------------------");
            for (int tmp = 0; tmp < nodeList.getLength(); tmp++) {
                Node node = nodeList.item(tmp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    // System.out.println("Blog #" + tmp + ":");
                    System.out.println("first-name: " + element.getElementsByTagName("first-name").item(0).getChildNodes().item(0).getNodeValue());
                    System.out.println("last-name: " + element.getElementsByTagName("last-name").item(0).getChildNodes().item(0).getNodeValue());
                    System.out.println("book-title: " + element.getElementsByTagName("book-title").item(0).getChildNodes().item(0).getNodeValue());


                }
            }*/
        }
        catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    /**
     * Получаем все дочернии узлы файла....
     * @return
     * @throws Exception
     */
    public  void getElements(String path) throws IOException, SAXException {

        File xmlFile = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Doc = builder.parse(xmlFile);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Element root = Doc.getDocumentElement();
        NodeList children = root.getChildNodes();
        for (int i  =0; i < children.getLength(); i++)
        {
            Node child = children.item(i);
            if(child instanceof Element)
            {
                Element childElement = (Element) child;
                System.out.print(childElement.getTagName().toString() + "\n");
            }

        }
    }










    public Books getBook() {
        if(NList.getLength() > 0) {
            Node node = NList.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
             /*   Book = new Books(element.getElementsByTagName("book-title").item(0).getChildNodes().item(0).getNodeValue(),
                        element.getElementsByTagName("year").item(0).getChildNodes().item(0).getNodeValue(),
                        element.getElementsByTagName("first-name").item(0).getChildNodes().item(0).getNodeValue() + " " + element.getElementsByTagName("last-name").item(0).getChildNodes().item(0).getNodeValue(),
                        null);*/
                        //element.getElementsByTagName("first-name").item(0).getChildNodes().item(0).getNodeValue());
                return Book;
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    public String getFullName() {
         if(NList.getLength() > 0) {
            Node node = NList.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                return element.getElementsByTagName("first-name").item(0).getChildNodes().item(0).getNodeValue() + " " + element.getElementsByTagName("last-name").item(0).getChildNodes().item(0).getNodeValue();
            }
            else {
                return "No information.";
            }
        }
        else {
             return null;
         }
    }
    public String getTitle() {
    if(NList.getLength() > 0) {
        Node node = NList.item(0);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            return element.getElementsByTagName("book-title").item(0).getChildNodes().item(0).getNodeValue();
        }
        else {
            return "No information.";
        }
    }
    else {
        return null;
    }
}

    public String getYear() {
        if(NList.getLength() > 0) {
            Node node = NList.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                return element.getElementsByTagName("year").item(0).getChildNodes().item(0).getNodeValue();
            }
            else {
                return "No information.";
            }
        }
        else {
            return null;
        }
    }

    public String getAnnotation() {
        if(NList.getLength() > 0) {
            Node node = NList.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                return element.getElementsByTagName("annotation").item(0).getChildNodes().item(0).getNodeValue();
            }
            else {
                return "No information.";
            }
        }
        else {
            return null;
        }
    }

    public void getImage() throws Exception {
        try {

        } catch (Exception exception) {
            String message = "Cant find Image!";
            throw new Exception(message);
        }
    }




}
