package com.hai.crawler.crawler4j;

import com.alibaba.fastjson.JSONObject;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.parser.ParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.regex.Pattern;

/**
 * 自定义爬虫类需要继承WebCrawler类，决定哪些url可以被爬以及处理爬取的页面信息
 * Created by Administrator on 2017/12/12.
 */
public class MyCrawler extends WebCrawler {
    //正则匹配指定的后缀文件
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g|png|tiff?|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf|rm|smil|wmv|swf|wma|zip|rar|gz))$");
    private final static String DOMAIN = "http://www.java1234.com";

    @Override
    public void init(int id, CrawlController crawlController) throws InstantiationException, IllegalAccessException {
        System.out.println(this.getClass().getName() + ".init...");
        super.init(id, crawlController);
    }

    @Override
    public void onStart() {
        System.out.println(this.getClass().getName() + ".onStart...");
        super.onStart();
    }

    /**
     * 这个方法主要是决定哪些url我们需要抓取，返回true表示是我们需要的，返回false表示不是我们需要的Url
     *
     * @param referringPage 封装了当前爬取的页面信息
     * @param url           封装了当前爬取的页面url信息
     * @return
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        System.out.println(this.getClass().getName() + ".shouldVisit...");
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches() && href.startsWith(DOMAIN);
    }

    /**
     * 当我们爬到我们需要的页面，这个方法会被调用
     *
     * @param page
     */
    @Override
    public void visit(Page page) {
        System.out.println(this.getClass().getName() + ".visit...");
        String url = page.getWebURL().getURL();
        System.out.println("url: " + url);
        ParseData parseData = page.getParseData();
        if (parseData instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) parseData;
//            String html = htmlParseData.getHtml();
//            String text = htmlParseData.getText();
//            String title = htmlParseData.getTitle();
//            Set<WebURL> webURLSet = htmlParseData.getOutgoingUrls();
//            Map<String, String> mataTags = htmlParseData.getMetaTags();
            System.out.println(JSONObject.toJSONString(htmlParseData, true));
        }
    }

    @Override
    public void onBeforeExit() {
        System.out.println(this.getClass().getName() + ".onBeforeExit...");
        super.onBeforeExit();
    }

    @Override
    protected void onUnhandledException(WebURL webUrl, Throwable e) {
        System.out.println(this.getClass().getName() + ".onUnhandledException...");
        super.onUnhandledException(webUrl, e);
    }

    @Override
    protected void onContentFetchError(WebURL webUrl) {
        System.out.println(this.getClass().getName() + ".onContentFetchError...");
        super.onContentFetchError(webUrl);
    }

    @Override
    protected void onPageBiggerThanMaxSize(String urlStr, long pageSize) {
        System.out.println(this.getClass().getName() + ".onPageBiggerThanMaxSize...");
        super.onPageBiggerThanMaxSize(urlStr, pageSize);
    }

    @Override
    protected void onParseError(WebURL webUrl) {
        System.out.println(this.getClass().getName() + ".onParseError...");
        super.onParseError(webUrl);
    }

    @Override
    protected void onRedirectedStatusCode(Page page) {
        System.out.println(this.getClass().getName() + ".onRedirectedStatusCode...");
        super.onRedirectedStatusCode(page);
    }

    @Override
    protected void onUnexpectedStatusCode(String urlStr, int statusCode, String contentType, String description) {
        System.out.println(this.getClass().getName() + ".onUnexpectedStatusCode...");
        super.onUnexpectedStatusCode(urlStr, statusCode, contentType, description);
    }

}
