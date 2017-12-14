package com.hai.ijavase.javax.xml;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/3.
 */
public class SaxParse {

    @Test
    public void saxParse() throws ParserConfigurationException, SAXException, IOException {
//        SAXReader saxReader = new SAXReader(true);
//        saxReader.setDocumentFactory(DocumentFactory.getInstance());

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);

        SAXParser parser = factory.newSAXParser();
        XMLReader reader = parser.getXMLReader();

        reader.setContentHandler(new MyCotentHandler());
        reader.parse("book.xml");
    }

    private class MyCotentHandler extends DefaultHandler {
        @Override
        public void startDocument() throws SAXException {
            System.out.println("document main");
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("document end");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            System.out.println("main element <" + qName + ">");
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            System.out.println("end element </" + qName + ">");
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String text = new String(ch, start, length);
            System.out.println("content text: " + text);
        }
    }
}
