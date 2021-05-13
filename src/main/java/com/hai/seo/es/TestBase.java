package com.hai.seo.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * description
 *
 * @author hai
 * @date 2021/5/4 21:03
 */
public class TestBase {

    public static final Logger logger = LoggerFactory.getLogger(TestES.class);
    public String host = "localhost";
    public int port = 9200;
    public String scheme = "http";
    public RestHighLevelClient client;

    public boolean useLocalEvn = true;

    public void init() {
        if (!useLocalEvn) {
            host = "search-es-cptdev-g5yktgl72xbqlkh2lsdomvcsfm.ap-southeast-1.es.amazonaws.com";
            port = 443;
            scheme = "https";
        }
    }


    @Before
    public void before() {
        init();
        client = new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, scheme)));
    }

    @After
    public void after() throws IOException {
        if (null != client) {
            client.close();
        }
    }

}
