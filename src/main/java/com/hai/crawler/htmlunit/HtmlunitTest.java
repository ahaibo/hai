package com.hai.crawler.htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */
public class HtmlunitTest {
    private static final String DOMAIN = "http://www.java1234.com";

    WebClient client = null;
    HtmlPage page = null;
    HtmlTable htmlTable = null;
    HtmlTableHeader htmlHeader = null;
    HtmlBody htmlBody = null;
    HtmlTableFooter htmlTableFooter = null;

    long start;
    long end;

    @Before
    public void before() {
        start = System.nanoTime();
        client = new WebClient(BrowserVersion.CHROME);//模拟指定浏览器
        //取消css，js支持
        //options();
    }

    protected void options() {
        enableCssAnsJs(false);
    }

    protected void enableCssAnsJs(boolean enable) {
        client.getOptions().setCssEnabled(enable);
        client.getOptions().setJavaScriptEnabled(enable);
    }

    protected void initPage() {
        initPage(null);
    }

    protected void initPage(String url) {
        try {
            page = client.getPage((null == url || url.trim().length() == 0) ? DOMAIN : url);
//            System.out.println("html: \n" + page.asXml());
//            System.out.println("\ntext: \n" + page.asText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //查找指定元素
    @Test
    public void test() {
        initPage();
        HtmlDivision div = page.getHtmlElementById("navMenu");  // 查找指定id的html dom元素
        System.out.println(div.asXml());
        System.out.println("======================");
        DomNodeList<DomElement> aList = page.getElementsByTagName("a"); // 根据tag名称查询所有tag
        for (int i = 0; i < aList.getLength(); i++) {
            DomElement a = aList.get(i);
            System.out.println(a.asXml());
        }
        System.out.println("======================");
        HtmlListItem item = (HtmlListItem) page.getByXPath("//div[@id='navMenu'][1]/ul/li").get(0); // xpath方式
        System.out.println("page.getByXPath:\n" + item.asXml());
    }

    //模拟按钮点击效果
    @Test
    public void mockClick() {
        options();
        try {
            page = client.getPage("http://blog.java1234.com/index.html");
//            System.out.println(page.asXml());
            HtmlForm form = page.getFormByName("myform");
//            System.out.println("form:\n" + form.asXml());
            HtmlTextInput textField = form.getInputByName("q"); // 获取查询文本框
            HtmlSubmitInput button = form.getInputByName("submitButton"); // 获取提交按钮
            textField.setValueAttribute("java"); // 文本框“填入”数据
            HtmlPage searchResultPage = button.click();
            System.out.println("result by search with java:\n" + searchResultPage.asXml());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void table() {
        options();
        try {
            page = client.getPage("http://www.java1234.com/crawler/table02.html");
            htmlTable = (HtmlTable) page.getElementById("table1");
            System.out.println("captionText: " + htmlTable.getCaptionText());
            System.out.println("htmlTable.getCellAt(0, 0).asText(): " + htmlTable.getCellAt(0, 0).asText());
            List<HtmlTableRow> rows = htmlTable.getRows();
            showTableRow(rows, "头信息");
            showTableBodyInfo(htmlTable);
            showTableFooter(htmlTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void showTableRow(List<HtmlTableRow> rows) {
        showTableRow(rows, null);
    }

    protected void showTableRow(List<HtmlTableRow> rows, String msg) {
        if (null != msg && msg.trim().length() != 0) {
            System.out.println(msg);
        }
        for (HtmlTableRow row : rows) {
            List<HtmlTableCell> cells = row.getCells();
            for (HtmlTableCell cell : cells) {
//                System.out.print(cell.asXml());
                System.out.print(cell.asText());
            }
            System.out.println();
        }
    }

    protected void showTableBodyInfo(HtmlTable table) {
        List<HtmlTableBody> bodies = table.getBodies();
        System.out.println("Body Info:");
        for (HtmlTableBody body : bodies) {
            showTableRow(body.getRows());
            System.out.println();
        }
    }

    protected void showTableFooter(HtmlTable table) {
        htmlTableFooter = table.getFooter();
        showTableRow(htmlTableFooter.getRows(), "Footer Info:");
    }

    @Test
    public void crawlerBaiduyun() {
        try {
            page = client.getPage("https://pan.baidu.com/share/home?uk=305605848#category/type=0");
            Thread.sleep(10000);
            System.out.println(page.asXml());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        if (null != client) {
            client.close();
        }
        end = System.nanoTime();
        System.out.println("time: " + (end - start) / 1000);
    }
}
