package com.hai.seo.es;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * 查询语句
 * 几个常用的查询API这里就简单的介绍下用法，然后再直接给出所有的查询语句代码。
 * <p>
 * 查询API
 * 等值（term查询：QueryBuilders.termQuery(name,value);
 * 多值(terms)查询:QueryBuilders.termsQuery(name,value,value2,value3...);
 * 范围（range)查询：QueryBuilders.rangeQuery(name).gte(value).lte(value);
 * 存在(exists)查询:QueryBuilders.existsQuery(name);
 * 模糊(wildcard)查询:QueryBuilders.wildcardQuery(name,+value+);
 * 组合（bool）查询: BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
 * <p>
 * 参考: https://www.cnblogs.com/xuwujing/p/11645630.html
 *
 * @author hai
 * @date 2021/5/4 21:03
 */
public class TestSearch extends TestBase {

    @Test
    public void getDocument() throws IOException {
        GetRequest request = new GetRequest("hai", "goods", "1");
        GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
        logger.info(JSONObject.toJSONString(getResponse));
    }

    @Test
    public void searchAll() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits searchHits = searchResponse.getHits();
        SearchHit[] hits = searchHits.getHits();

        for (SearchHit hit : hits) {
            Map<String, DocumentField> map = hit.getFields();
            for (Map.Entry<String, DocumentField> entry : map.entrySet()) {
                logger.info(entry.getKey() + ":" + JSONObject.toJSONString(entry.getValue()));
            }
            logger.info(hit.getSourceAsString());
            logger.info(hit.getSourceAsMap().toString());
        }
    }

    /**
     * 一般查询代码示例
     * 其实就是等值查询，只不过在里面加入了分页、排序、超时、路由等等设置，并且在查询结果里面增加了一些处理。
     *
     * @throws IOException
     */
    @Test
    public void search() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("hai2");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //设置查询条件
        // searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.query(QueryBuilders.termQuery("name", "zhangsan"));

        //sort
        searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        // searchSourceBuilder.sort(new FieldSortBuilder("title").order(SortOrder.ASC));

        //分页
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(20);
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(60));

        // searchRequest.routing("routing");

        // 设置索引库表达式
        // searchRequest.indicesOptions(IndicesOptions.lenientExpandOpen());

        // 查询选择本地分片，默认是集群分片
        // searchRequest.preference("_local");

        // 关闭suorce查询
        // sourceBuilder.fetchSource(false);

        String[] includeFields = new String[]{"title", "user", "innerObject.*"};
        String[] excludeFields = new String[]{"_type"};
        // 包含或排除字段
        // sourceBuilder.fetchSource(includeFields, excludeFields);

        searchRequest.source(searchSourceBuilder);

        //search
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        // HTTP状态代码、执行时间或请求是否提前终止或超时
        RestStatus status = searchResponse.status();
        TimeValue took = searchResponse.getTook();
        Boolean terminatedEarly = searchResponse.isTerminatedEarly();
        boolean timedOut = searchResponse.isTimedOut();

        // 供关于受搜索影响的切分总数的统计信息，以及成功和失败的切分
        int totalShards = searchResponse.getTotalShards();
        int successfulShards = searchResponse.getSuccessfulShards();
        int failedShards = searchResponse.getFailedShards();
        // 失败的原因
        for (ShardSearchFailure failure : searchResponse.getShardFailures()) {
            // failures should be handled here
        }

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

    /**
     * 或查询
     * 其实这个或查询也是bool查询中的一种，这里的查询语句相当于SQL语句中的
     * <p>
     * SELECT * FROM test1 where (uid = 1 or uid =2) and phone = 12345678919
     */
    @Test
    public void orSearch() throws IOException {
        SearchRequest request = new SearchRequest("idx-3");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder queryBuilder1 = new BoolQueryBuilder();
        BoolQueryBuilder queryBuilder2 = new BoolQueryBuilder();
        queryBuilder2.should(QueryBuilders.termQuery("name", "zhangsan"));
        queryBuilder2.should(QueryBuilders.termQuery("name", "lisi"));

        queryBuilder1.must(queryBuilder2);
        queryBuilder1.must(QueryBuilders.termQuery("phone", "13111111111"));

        searchSourceBuilder.query(queryBuilder1);
        System.out.println("或查询语句:" + searchSourceBuilder.toString());
        request.source(searchSourceBuilder);
        // 同步查询
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);

        searchResponse.getHits().forEach(documentFields -> {
            System.out.println("查询结果:" + documentFields.getSourceAsMap());
        });
    }

    @Test
    public void likeSearch() throws IOException {
        SearchRequest request = new SearchRequest("idx-4");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.wildcardQuery("message", "*success*"));

        request.source(searchSourceBuilder);
        // 同步查询
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        searchResponse.getHits().forEach(documentFields -> {
            System.out.println("模糊查询结果:" + documentFields.getSourceAsMap());
        });
    }

    @Test
    public void inSearch() throws IOException {
        SearchRequest request = new SearchRequest("idx-5");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("name", Arrays.asList("zhangsan", "lisi")));

        request.source(searchSourceBuilder);
        // 同步查询
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        searchResponse.getHits().forEach(documentFields -> {
            System.out.println("in查询结果:" + documentFields.getSourceAsMap());
        });
    }

    @Test
    public void existsSearch() throws IOException {
        SearchRequest request = new SearchRequest("idx-6");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.existsQuery("name"));

        request.source(searchSourceBuilder);
        // 同步查询
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        searchResponse.getHits().forEach(documentFields -> {
            System.out.println("exists查询结果:" + documentFields.getSourceAsMap());
        });
    }

    @Test
    public void rangeSearch() throws IOException {
        SearchRequest request = new SearchRequest("idx-7");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.rangeQuery("name").gte("2019-01-01 00:00:00").lte("2019-01-01 23:59:59"));

        request.source(searchSourceBuilder);
        // 同步查询
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        searchResponse.getHits().forEach(documentFields -> {
            System.out.println("range查询结果:" + documentFields.getSourceAsMap());
        });
    }

    @Test
    public void regexpSearch() throws IOException {
        SearchRequest request = new SearchRequest("idx-8");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.regexpQuery("name", "xu[0-9]"));

        request.source(searchSourceBuilder);
        // 同步查询
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        searchResponse.getHits().forEach(documentFields -> {
            System.out.println("regexp查询结果:" + documentFields.getSourceAsMap());
        });
    }

}
