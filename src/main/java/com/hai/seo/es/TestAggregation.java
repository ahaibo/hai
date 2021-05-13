package com.hai.seo.es;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * description
 * <p>
 * https://www.cnblogs.com/xuwujing/p/12385903.html
 *
 * @author hai
 * @date 2021/5/5 11:26
 */
public class TestAggregation extends TestBase {

    @Test
    public void max() throws IOException {
        String buk = "t_grade_max";
        String field = "grade";
        agg(AggregationBuilders.max(buk).field(field), buk);
    }


    public SearchResponse search(AggregationBuilder aggregation) throws IOException {
        SearchRequest searchRequest = new SearchRequest("idx-1");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.explain(false);
        searchSourceBuilder.fetchSource(false);
        searchSourceBuilder.version(false);
        searchSourceBuilder.aggregation(aggregation);

        searchRequest.source(searchSourceBuilder);

        return client.search(searchRequest, RequestOptions.DEFAULT);
    }


    public void agg(AggregationBuilder aggregationBuilder, String buk) throws IOException {
        System.out.println("aggregation by buk:" + buk);
        SearchResponse searchResponse = search(aggregationBuilder);
        if (!RestStatus.OK.equals(searchResponse.status())) {
            System.out.println("fail");
            return;
        }

        Aggregations aggregations = searchResponse.getAggregations();
        Aggregation aggregation = aggregations.get(buk);
        System.out.println(aggregation.getName());
        System.out.println(aggregation.getType());
        Map<String, Object> metadata = aggregation.getMetadata();
        for (Map.Entry<String, Object> entry : metadata.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

}
