package com.hai.seo.es;

import com.alibaba.fastjson.JSONObject;
import com.hai.common.util.RandomUtil;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestES {
    private static final Logger logger = LoggerFactory.getLogger(TestES.class);
    private String host = "localhost";
    private int port = 9200;
    private String scheme = "http";
    private RestHighLevelClient client;

    private boolean useLocalEvn = false;

    private void init() {
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

    @Test
    public void test() throws IOException {
        existIndex("hai");
    }

    @Test
    public void index() throws IOException {

        CreateIndexRequest request = new CreateIndexRequest("hai2");

        request.setMasterTimeout(TimeValue.timeValueMinutes(1));

        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);

        logger.info("isAcknowledged:{}", response.isAcknowledged());
        logger.info("isShardsAcknowledged:{}", response.isShardsAcknowledged());

    }

    @Test
    public void getIndex() throws Exception {
        GetIndexRequest request = new GetIndexRequest("hai");
        request.indicesOptions().allowNoIndices();
        GetIndexResponse getIndexResponse = client.indices().get(request, RequestOptions.DEFAULT);
//        logger.info(JSONObject.toJSONString(getIndexResponse));
    }

    public void existIndex(String index) throws IOException {
        GetIndexRequest request = new GetIndexRequest("hai");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        logger.info("index: " + index + "; exists: " + exists);
    }

    @Test
    public void deleteIndex() throws IOException {
        deleteIndex("test.hai");
    }

    public void deleteIndex(String index) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        request.timeout(TimeValue.timeValueMinutes(2));
        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));
        AcknowledgedResponse acknowledgedResponse = client.indices().delete(request, RequestOptions.DEFAULT);
        logger.info(JSONObject.toJSONString(acknowledgedResponse));
    }

    //documents
    @Test
    public void insertDocument() throws Exception {
        int length = 10000;
//        int length = 10;
        String index = "hai.bulk.batch.no";
        String type = "goods";
        List<Map<String, Object>> datas = buildData(length);
        datas.add(0, null);
        long start = System.currentTimeMillis();
        for (int i = 1; i <= length; i++) {
            IndexRequest request = new IndexRequest(index, type, i + "");
//            request.source(buildData());
            request.source(datas.get(i));
//            IndexResponse indexResponse = null;
            client.index(request, RequestOptions.DEFAULT);
//            logger.info("response status:{}", JSONObject.toJSONString(indexResponse.status()));
//            logger.info("response result:{}", JSONObject.toJSONString(indexResponse.getResult()));
        }
        long end = System.currentTimeMillis();
        logger.info("insertDocument {} entries used times:{} ms.", length, end - start);
    }

    private List<Map<String, Object>> buildData(Integer length) {
        length = null == length || length <= 0 ? 10 : length;
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(buildData());
        }
        return list;
    }

    private Map<String, Object> buildData() {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", RandomUtil.randomRangeCharacters(8, 16));
        jsonMap.put("title", RandomUtil.randomRangeCharacters(8, 32));
        jsonMap.put("price", RandomUtil.randomNumber(10000, 100));
        jsonMap.put("create_date", "2019-07-05");
        return jsonMap;
    }

    @Test
    public void getDocument() throws IOException {
        GetRequest request = new GetRequest("hai", "goods", "1");
        GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
        logger.info(JSONObject.toJSONString(getResponse));
    }

    @Test
    public void search() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("hai2");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //sort
        searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
//        searchSourceBuilder.sort(new FieldSortBuilder("title").order(SortOrder.ASC));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(20);
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(60));
        searchRequest.source(searchSourceBuilder);

        //search
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits searchHits = searchResponse.getHits();
        SearchHit[] hits = searchHits.getHits();

        for (SearchHit hit : hits) {
            /*Map<String, DocumentField> map = hit.getFields();
            for (Map.Entry<String, DocumentField> entry : map.entrySet()) {
                logger.info(entry.getKey() + ":" + JSONObject.toJSONString(entry.getValue()));
            }*/
            logger.info(hit.getSourceAsString());
//            logger.info(hit.getSourceAsMap().toString());
        }

    }

    @Test
    public void bulkIndex() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        String index = "hai.bulk.batch.yes";
        String type = "goods";
        //批量插入测试
        for (int i = 1; i <= 10000; i++) {
            IndexRequest indexRequest = new IndexRequest(index, type, i + "");
            Map<String, Object> data = buildData();
            indexRequest.source(data);
            bulkRequest.add(indexRequest);
        }
        long start = System.currentTimeMillis();
        client.bulk(bulkRequest, RequestOptions.DEFAULT);
        long end = System.currentTimeMillis();
        logger.info("hai.bulk.batch used times:{} ms.", end - start);
    }

    @Test
    public void testBulk() throws IOException {
        String index = "hai.bulk";
        String type = "goods";
        String id = "1";
        IndexRequest indexRequest = new IndexRequest(index, type, id);
        Map<String, Object> data = buildData();
        indexRequest.source(data);

        UpdateRequest updateRequest = new UpdateRequest(index, type, id);
        data.put("title", RandomUtil.randomRangeCharacters(8, 32));
        updateRequest.doc(data);

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(indexRequest);
        bulkRequest.add(updateRequest);

        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        if (bulkResponse.hasFailures()) {
            logger.error("bulk request has failures:{}", bulkResponse.buildFailureMessage());
            return;
        }
        for (BulkItemResponse itemResponse : bulkResponse.getItems()) {
//            if (itemResponse.getOpType() == DocWriteRequest.OpType.INDEX
//                    || itemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
//                IndexResponse indexResponse = (IndexResponse) itemResponse.;
//            } else if (itemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
//                UpdateResponse updateResponse = (UpdateResponse) itemResponse;
//                System.out.println(updateRequest);
//            } else if (itemResponse.getOpType() == DocWriteRequest.OpType.DELETE) {
//                DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
//                System.out.println(deleteResponse);
//            }
            logger.info(JSONObject.toJSONString(itemResponse));
        }

        /*client.bulkAsync(bulkRequest, RequestOptions.DEFAULT, ActionListener.wrap(() ->
                logger.info("thread.current.name:" + Thread.currentThread().getName()))
        );*/
    }

    @After
    public void after() throws IOException {
        if (null != client) {
            client.close();
        }
    }

}
