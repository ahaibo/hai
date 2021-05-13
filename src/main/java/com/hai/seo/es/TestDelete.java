package com.hai.seo.es;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.junit.Test;

import java.io.IOException;

/**
 * description
 *
 * @author hai
 * @date 2021/5/4 21:06
 */
public class TestDelete extends TestBase {

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

    public void deleteRequest() throws IOException {
        DeleteRequest request = new DeleteRequest();
        request.id("1");
        request.index("idx01");

        //设置超时
        request.timeout(TimeValue.timeValueMinutes(2));
        // 设置刷新策略"wait_for"
        // 保持此请求打开，直到刷新使此请求的内容可以搜索为止。此刷新策略与高索引和搜索吞吐量兼容，但它会导致请求等待响应，直到发生刷新
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);

        //同步删除
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(response.status().getStatus());
        System.out.println(response.getResult().toString());
    }

    //跟进es查询条件删除
    public void deleteByQuery() throws IOException {
        String index = "idx02";
        DeleteByQueryRequest request = new DeleteByQueryRequest(index);

        request.setQuery(QueryBuilders.termQuery("name", "zhangshan"));

        BulkByScrollResponse bulkByScrollResponse = client.deleteByQuery(request, RequestOptions.DEFAULT);
        System.out.println(bulkByScrollResponse.getStatus().getTotal());
    }

}
