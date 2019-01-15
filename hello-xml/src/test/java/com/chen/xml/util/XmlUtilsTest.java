package com.chen.xml.util;

import com.chen.xml.dom.DomUtils;
import com.chen.xml.dom4j.Dom4jUtils;
import com.chen.xml.jdom.JdomUtils;
import com.chen.xml.sax.SaxUtils;
import org.junit.Test;

/**
 * XML解析工具类
 * <p>
 * @Author LeifChen
 * @Date 2019-01-14
 */
public class XmlUtilsTest {

    static final String PATH = "src/main/resources/books.xml";
    static final String DOM = "src/main/resources/dom.xml";
    static final String SAX = "src/main/resources/sax.xml";
    static final String JDOM = "src/main/resources/jdom.xml";
    static final String DOM4J = "src/main/resources/dom4j.xml";

    @Test
    public void parse() {
        DomUtils.parse(PATH);
        SaxUtils.parse(PATH);
        JdomUtils.parse(PATH);
        Dom4jUtils.parse(PATH);
    }

    @Test
    public void create() {
        DomUtils.create(DOM);
        SaxUtils.create(SAX);
        JdomUtils.create(JDOM);
        Dom4jUtils.create(DOM4J);
    }
}