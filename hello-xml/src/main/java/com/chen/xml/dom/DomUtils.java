package com.chen.xml.dom;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Dom解析XML
 * <p>
 * @Author LeifChen
 * @Date 2019-01-14
 */
public class DomUtils {

    public static void parse(String path) {
        Document document;
        try {
            document = getBuilder().parse(path);
            NodeList bookList = document.getElementsByTagName("book");
            for (int i = 0; i < bookList.getLength(); i++) {
                System.out.println("=====开始解析第" + (i + 1) + "本书=====");
                Node book = bookList.item(i);
                NamedNodeMap attrs = book.getAttributes();
                for (int j = 0; j < attrs.getLength(); j++) {
                    Node attr = attrs.item(j);
                    System.out.println("属性: " + attr.getNodeName() + "--" + attr.getNodeValue());
                }

                NodeList childNodes = book.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println("节点: " + childNodes.item(j).getNodeName() + "--" + childNodes.item(j).getTextContent());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void create(String path) {
        Document document = getBuilder().newDocument();
        document.setXmlStandalone(true);
        Element bookStore = document.createElement("bookstore");
        Element book = document.createElement("book");
        book.setAttribute("id", "1");
        Element name = document.createElement("name");
        name.setTextContent("<![CDATA[ DOM创建XML ]]>");
        book.appendChild(name);
        bookStore.appendChild(book);
        document.appendChild(bookStore);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer tf;
        try {
            tf = transformerFactory.newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.transform(new DOMSource(document), new StreamResult(new File(path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DocumentBuilder getBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return builder;
    }
}
