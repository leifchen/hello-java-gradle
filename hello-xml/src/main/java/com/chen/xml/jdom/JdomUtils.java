package com.chen.xml.jdom;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * JDOM解析XML
 * <p>
 * @Author LeifChen
 * @Date 2019-01-14
 */
public class JdomUtils {

    public static void parse(String path) {
        SAXBuilder builder = new SAXBuilder();
        InputStream is;
        try {
            is = new FileInputStream(path);
            // 转码处理
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            Document document = builder.build(isr);
            Element rootElement = document.getRootElement();
            List<Element> bookList = rootElement.getChildren();
            for (Element book : bookList) {
                System.out.println("=====开始解析第" + (bookList.indexOf(book) + 1) + "本书=====");
                List<Attribute> attrs = book.getAttributes();
                for (Attribute attr : attrs) {
                    System.out.println("属性: " + attr.getName() + "--" + attr.getValue());
                }

                List<Element> bookChildren = book.getChildren();
                for (Element bookChild : bookChildren) {
                    System.out.println("节点: " + bookChild.getName() + "--" + bookChild.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void create(String path) {
        Element bookStore = new Element("bookstore");
        bookStore.setAttribute("id", "1");
        Document document = new Document(bookStore);
        Element book = new Element("book");
        bookStore.addContent(book);
        Element name = new Element("name");
        book.addContent(name);
        name.setText("<![CDATA[ JDOM创建XML ]]>");

        Format format = Format.getCompactFormat();
        format.setIndent("");
        format.setEncoding("UTF-8");
        XMLOutputter outputter = new XMLOutputter(format);
        try {
            outputter.output(document, new FileOutputStream(new File(path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
