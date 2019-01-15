package com.chen.xml.dom4j;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

/**
 * DOM4J解析XML
 * <p>
 * @Author LeifChen
 * @Date 2019-01-14
 */
public class Dom4jUtils {

    public static void parse(String path) {
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new File(path));
            Element bookStore = document.getRootElement();
            Iterator<Element> iterator = bookStore.elementIterator();
            while (iterator.hasNext()) {
                System.out.println("=====开始遍历一本书=====");
                Element book = iterator.next();
                List<Attribute> attrs = book.attributes();
                for (Attribute attr : attrs) {
                    System.out.println("属性: " + attr.getName() + "--" + attr.getValue());
                }

                Iterator<Element> it = book.elementIterator();
                while (it.hasNext()) {
                    Element bookChild = it.next();
                    System.out.println("节点: " + bookChild.getName() + "--" + bookChild.getStringValue());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void create(String path) {
        Document document = DocumentHelper.createDocument();
        Element bookStore = document.addElement("bookstore");
        bookStore.addAttribute("id", "1");
        Element book = bookStore.addElement("book");
        Element name = book.addElement("name");
        name.setText("<![CDATA[ DOM4J创建XML ]]>");
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        try {
            XMLWriter writer = new XMLWriter(new FileOutputStream(new File(path)), format);
            writer.write(document);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
