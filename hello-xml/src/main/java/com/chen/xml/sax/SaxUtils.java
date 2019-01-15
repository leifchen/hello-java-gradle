package com.chen.xml.sax;

import org.xml.sax.helpers.AttributesImpl;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Sax解析XML
 * <p>
 * @Author LeifChen
 * @Date 2019-01-14
 */
public class SaxUtils {

    public static void parse(String path) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            SaxParserHandler handler = new SaxParserHandler();
            parser.parse(path, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void create(String path) {
        SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        try {
            TransformerHandler handler = factory.newTransformerHandler();
            Transformer tf = handler.getTransformer();
            tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            Result result = new StreamResult(new FileOutputStream(new File(path)));
            handler.setResult(result);

            handler.startDocument();
            AttributesImpl attr = new AttributesImpl();
            handler.startElement("", "", "bookstore", attr);
            attr.clear();
            attr.addAttribute("", "", "id", "", "1");
            handler.startElement("", "", "book", attr);
            attr.clear();
            handler.startElement("", "", "name", attr);
            handler.startCDATA();
            handler.characters("SAX创建XML".toCharArray(), 0, "SAX创建XML".length());
            handler.endCDATA();
            handler.endElement("", "", "name");
            handler.endElement("", "", "book");
            handler.endElement("", "", "bookstore");
            handler.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
