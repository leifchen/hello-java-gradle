package com.chen.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX 解析处理器
 * <p>
 * @Author LeifChen
 * @Date 2019-01-14
 */
public class SaxParserHandler extends DefaultHandler {

    private static final String BOOK_STORE = "bookstore";
    private static final String BOOK = "book";

    /**
     * 标识解析开始
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("SAX解析开始");
    }

    /**
     * 标识解析结束
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("SAX解析结束");
    }

    /**
     * 遍历xml文件的开始标签
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (BOOK.equals(qName)) {
            for (int i = 0; i < attributes.getLength(); i++) {
                System.out.println("属性: " + attributes.getQName(i) + "--" + attributes.getValue(i));
            }
        } else if (!BOOK_STORE.equals(qName)) {
            System.out.print("节点: " + qName);
        }
    }

    /**
     * 遍历xml文件的结束标签
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (BOOK.equals(qName)) {
            System.out.println("==========");
        }
    }

    /**
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String value = new String(ch, start, length);
        if (!"".equals(value.trim())) {
            System.out.println("--" + value);
        }
    }
}
