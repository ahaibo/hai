package com.hai.seo.es;

import com.alibaba.fastjson.JSONObject;
import com.hai.common.util.RandomUtil;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestES extends TestBase {

    @Test
    public void test() throws IOException {
        existIndex("hai");
    }

    @Test
    public void index() throws IOException {
        String type = "_doc";
        String index = "hai2";
        // setting 的值
        Map<String, Object> setmapping = new HashMap<>();
        // 分区数、副本数、缓存刷新时间
        setmapping.put("number_of_shards", 10);
        setmapping.put("number_of_replicas", 1);
        setmapping.put("refresh_interval", "5s");
        Map<String, Object> keyword = new HashMap<>();
        //设置类型
        keyword.put("type", "keyword");
        Map<String, Object> lon = new HashMap<>();
        //设置类型
        lon.put("type", "long");
        Map<String, Object> date = new HashMap<>();
        //设置类型
        date.put("type", "date");
        date.put("format", "yyyy-MM-dd HH:mm:ss");

        Map<String, Object> jsonMap2 = new HashMap<>();
        Map<String, Object> properties = new HashMap<>();
        //设置字段message信息
        properties.put("uid", lon);
        properties.put("phone", lon);
        properties.put("msgcode", lon);
        properties.put("message", keyword);
        properties.put("sendtime", date);
        Map<String, Object> mapping = new HashMap<>();
        mapping.put("properties", properties);
        jsonMap2.put(type, mapping);

        GetIndexRequest getRequest = new GetIndexRequest(index);
        getRequest.local(false);
        getRequest.humanReadable(true);
        boolean exists = client.indices().exists(getRequest, RequestOptions.DEFAULT);
        //如果存在就不创建了
        if (exists) {
            System.out.println(index + "索引库已经存在!");
            return;
        }

        CreateIndexRequest request = new CreateIndexRequest(index);
        //加载数据类型
        request.settings(setmapping);
        //设置mapping参数
        request.mapping(jsonMap2);
        // request.mapping(type, jsonMap2);
        //设置别名
        request.alias(new Alias("pancm_alias"));
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

}
