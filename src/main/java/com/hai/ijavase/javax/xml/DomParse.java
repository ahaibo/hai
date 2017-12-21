package com.hai.ijavase.javax.xml;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/6/3.
 */

public class DomParse {

    private Document document;

    @Before
    public void before() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();


        InputStream is = getClass().getClassLoader().getResourceAsStream("xml/star.xml");
        document = builder.parse(is);
        if (null != is) {
            is.close();
        }
    }

    @Test
    public void list() {
        if (null == document) return;
        Element element = document.getDocumentElement();
        System.out.println(element.getNodeName());
        NodeList nodeList = element.getChildNodes();
        iteratorNodeList(nodeList);
    }

    private void iteratorNodeList(NodeList nodeList) {
        if (nodeList.getLength() == 0) {
            return;
        }
//        System.out.print("\t");
        for (int i = 0, j = nodeList.getLength(); i < j; i++) {
            Node node = nodeList.item(i);
//            String nodeName = node.getNodeName();
//            System.out.print(nodeName.equals("#text") ? node.getTextContent() : nodeName);
            System.out.println(node.getTextContent());
            iteratorNodeList(node.getChildNodes());
        }
    }

    @Test
    public void modify() throws TransformerException {
        NodeList nodeList = document.getElementsByTagName("enabled");
        Node node = nodeList.item(0);
        node.setTextContent("true");

        //保存修改
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //数据源
        Source xmlSource = new DOMSource(document);
        //要输出的目的地
        Result outputTarget = new StreamResult("xml/star.xml");
        transformer.transform(xmlSource, outputTarget);
    }
}
