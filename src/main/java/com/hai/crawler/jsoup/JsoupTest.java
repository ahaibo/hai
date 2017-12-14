package com.hai.crawler.jsoup;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


/**
 * jsoup 是一款Java 的HTML解析器，可直接解析某个URL地址、HTML文本内容。它提供了一套非常省力的API，可通过DOM，CSS以及类似于jQuery的操作方法来取出和操作数据。
 * Created by Administrator on 2017/12/12.
 */
public class JsoupTest {

    Logger log = LoggerFactory.getLogger(JsoupTest.class);
    CloseableHttpClient httpClient = null;
    HttpGet httpGet = null;
    CloseableHttpResponse response = null;
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    HttpEntity entity = null;
    Document doc = null;
    String filePath = "D:\\Data\\test\\crawler\\jsoup\\cnblogs.txt";
    String webUrl = "http://www.cnblogs.com";
//    String webUrl = "https://pan.baidu.com/share/home?uk=305605848#category/type=0";

    @Test
    public void test() {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://en.wikipedia.org/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(doc.title());
//        Elements newsHeadlines = doc.select("#mp-itn b a");
        for (Element ele : doc.children()) {
            System.out.println(ele.html());
        }
//        for (Element headline : newsHeadlines) {
//            log.info("%s\n\t%s",
//                    headline.attr("title"), headline.absUrl("href"));
//        }

    }

    @Before
    @Test
    public void httpClientBefore() {
        httpClient = HttpClients.createDefault();
        httpGet = new HttpGet(webUrl);
        try {
            response = httpClient.execute(httpGet);
            Header[] headers = response.getAllHeaders();
            printHeaders(headers);
            entity = response.getEntity();
            System.out.println("Content-type:\t" + entity.getContentType().getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void closeResources() {
        close(httpClient, response, bis, bos);
    }

    @Test
    public void readContent() {
        writeContentToFile();
    }

    private void writeContentToFile() {
        try {
            bis = new BufferedInputStream(entity.getContent());
            bos = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            byte[] buffer = new byte[1024 * 5];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, len, "utf-8"));
                bos.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } /*finally {
            close(httpClient, response, bis, bos);
        }*/
    }

    @Test
    public void parse() {
        try {
            String content = EntityUtils.toString(entity, "utf-8");
            System.out.println(content);
            doc = Jsoup.parse(content);
            System.out.println("title:\t" + doc.title());
            Element navTop = doc.getElementById("site_nav_top");
            System.out.println("nav top:" + navTop.text());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Jsoup使用选择器语法查找DOM元素
    @Test
    public void select() {

        parse();

        Elements linkElements = doc.select(".post_item .post_item_body h3 a"); // 查找所有帖子DOM
        for (Element e : linkElements) {
            System.out.println("博客标题：" + e.text());
            System.out.println("-------------");
        }

        Elements hrefElements = doc.select("a[href]"); // 带有href属性的a元素
        for (Element e : hrefElements) {
            System.out.println(e.toString());
            System.out.println("-------------");
        }

        Elements imgElements = doc.select("img[src$=.png]"); // 查找扩展名为.png的图片DOM节点
        for (Element e : imgElements) {
            System.out.println(e.toString());
            System.out.println("-------------");
        }

        Element element = doc.getElementsByTag("title").first(); // 获取tag是title的所有DOM元素
        String title = element.text(); // 返回元素的文本
        System.out.println("网页标题是：" + title);
    }

    @Test
    public void attr() {
        parse();
        Elements linkElements = doc.select("#post_list .post_item .post_item_body h3 a"); //通过选择器查找所有博客链接DOM
        for (Element e : linkElements) {
            System.out.println("博客标题：" + e.text());
            System.out.println("博客地址：" + e.attr("href"));
            System.out.println("target:" + e.attr("target"));
        }

        Element linkElement = doc.select("#friend_link").first();
        System.out.println("纯文本：" + linkElement.text());
        System.out.println("html：" + linkElement.html());
    }

    protected void printHeaders(Header[] headers) {
        for (Header header : headers) {
            System.out.println(JSONObject.toJSONString(header, true));
        }
    }

    protected void close(CloseableHttpClient httpClient, CloseableHttpResponse response, BufferedInputStream bis, BufferedOutputStream bos) {
        System.out.println("close...");
        if (null != bos) {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != bis) {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != response) {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != httpClient) {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
