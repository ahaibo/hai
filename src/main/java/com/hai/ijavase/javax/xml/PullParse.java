package com.hai.ijavase.javax.xml;

import org.junit.Test;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/3.
 */
public class PullParse {

    @Test
    public void pullParse() throws XmlPullParserException, IOException {
        XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser pullParser = pullParserFactory.newPullParser();
        pullParser.setInput(new FileInputStream(new File("book.xml")), "utf-8");
        processDocument(pullParser);
    }

    public void processDocument(XmlPullParser xpp) throws XmlPullParserException, IOException {
        int eventType = xpp.getEventType();
        do {
            if (eventType == xpp.START_DOCUMENT) {
                System.out.println("Start document");
            } else if (eventType == xpp.END_DOCUMENT) {
                System.out.println("End document");
            } else if (eventType == xpp.START_TAG) {
                processStartElement(xpp);
            } else if (eventType == xpp.END_TAG) {
                processEndElement(xpp);
            } else if (eventType == xpp.TEXT) {
                processText(xpp);
            }
            eventType = xpp.next();
        } while (eventType != xpp.END_DOCUMENT);
    }

    public void processStartElement(XmlPullParser xpp) {
        String name = xpp.getName();
        String uri = xpp.getNamespace();
        if ("".equals(uri)) {
            System.out.println("Start element: " + name);
        } else {
            System.out.println("Start element: {" + uri + "}" + name);
        }
    }

    public void processEndElement(XmlPullParser xpp) {
        String name = xpp.getName();
        String uri = xpp.getNamespace();
        if ("".equals(uri))
            System.out.println("End element: " + name);
        else
            System.out.println("End element:   {" + uri + "}" + name);
    }

    int holderForStartAndLength[] = new int[2];

    public void processText(XmlPullParser xpp) throws XmlPullParserException {
        char ch[] = xpp.getTextCharacters(holderForStartAndLength);
        int start = holderForStartAndLength[0];
        int length = holderForStartAndLength[1];
        System.out.print("Characters:    \"");
        for (int i = start; i < start + length; i++) {
            switch (ch[i]) {
                case '\\':
                    System.out.print("\\\\");
                    break;
                case '"':
                    System.out.print("\\\"");
                    break;
                case '\n':
                    System.out.print("\\n");
                    break;
                case '\r':
                    System.out.print("\\r");
                    break;
                case '\t':
                    System.out.print("\\t");
                    break;
                default:
                    System.out.print(ch[i]);
                    break;
            }
        }
        System.out.print("\"\n");
    }
}
