package com.hai.crawler.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Administrator on 2017/12/13.
 */
public class HttpClientTest {

    /**
     * 代理IP网站：http://www.66ip.cn/
     * <p>
     * HttpClient使用代理IP
     * <p>
     * 在爬取网页的时候，有的目标站点有反爬虫机制，对于频繁访问站点以及规则性访问站点的行为，会采集屏蔽IP措施。这时候，代理IP就派上用场了。
     * <p>
     * 关于代理IP的话 也分几种 透明代理、匿名代理、混淆代理、高匿代理
     * <p>
     * 1、透明代理(Transparent Proxy)
     * REMOTE_ADDR = Proxy IP
     * HTTP_VIA = Proxy IP
     * HTTP_X_FORWARDED_FOR = Your IP
     * 透明代理虽然可以直接“隐藏”你的IP地址，但是还是可以从HTTP_X_FORWARDED_FOR来查到你是谁。
     * <p>
     * 2、匿名代理(Anonymous Proxy)
     * REMOTE_ADDR = proxy IP
     * HTTP_VIA = proxy IP
     * HTTP_X_FORWARDED_FOR = proxy IP
     * 匿名代理比透明代理进步了一点：别人只能知道你用了代理，无法知道你是谁。
     * <p>
     * 3、混淆代理(Distorting Proxies)
     * REMOTE_ADDR = Proxy IP
     * HTTP_VIA = Proxy IP
     * HTTP_X_FORWARDED_FOR = Random IP address
     * 如上，与匿名代理相同，如果使用了混淆代理，别人还是能知道你在用代理，但是会得到一个假的IP地址，伪装的更逼真：-）
     * <p>
     * 4、高匿代理(Elite proxy或High Anonymity Proxy)
     * REMOTE_ADDR = Proxy IP
     * HTTP_VIA = not determined
     * HTTP_X_FORWARDED_FOR = not determined
     * 可以看出来，高匿代理让别人根本无法发现你是在用代理，所以是最好的选择。
     * <p>
     * 一般我们搞爬虫 用的都是 高匿的代理IP；
     * 那代理IP 从哪里搞呢 很简单  百度一下，你就知道 一大堆代理IP站点。  一般都会给出一些免费的，但是花点钱搞收费接口更加方便；
     * 比如 http://www.66ip.cn/
     */
    @Test
    public void proxy() {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.taobao.com");
//        HttpHost proxy = new HttpHost("114.255.212.17", 808);
//        HttpHost proxy = new HttpHost("115.183.11.158", 9999);
//        RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
//        httpGet.setConfig(requestConfig);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");

        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, "utf-8");
            System.out.println("Content:\n" + content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != client) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
