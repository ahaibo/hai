// package com.hai.seo.es;
//
// import org.elasticsearch.action.bulk.BulkRequestBuilder;
// import org.elasticsearch.action.search.SearchRequest;
// import org.elasticsearch.action.search.SearchRequestBuilder;
// import org.elasticsearch.action.search.SearchResponse;
// import org.elasticsearch.action.search.SearchScrollRequest;
// import org.elasticsearch.client.RequestOptions;
// import org.elasticsearch.client.transport.TransportClient;
// import org.elasticsearch.common.settings.Settings;
// import org.elasticsearch.common.unit.TimeValue;
// import org.elasticsearch.index.query.QueryBuilders;
// import org.elasticsearch.search.SearchHit;
// import org.elasticsearch.search.builder.SearchSourceBuilder;
// import org.elasticsearch.search.sort.SortOrder;
// import org.elasticsearch.transport.client.PreBuiltTransportClient;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
//
// import java.io.IOException;
// import java.net.InetAddress;
// import java.net.UnknownHostException;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;
//
// /**
//  * description
//  *
//  * @author hai
//  * @date 2021/5/5 17:07
//  */
// public class TestMigration extends TestBase {
//
//     private static final Logger log = LoggerFactory.getLogger(TestMigration.class);
//
//     /*索引重建迁移大法*/
//     private void indexMigration() throws IOException {
//         String node = "192.168.0.12";
//         int port = 9300;
//         String cn = "my-application";
//
//         Settings settings = Settings.builder()
//                 .put("cluster.name", cn)//设置ES实例的名称
//                 .put("client.transport.sniff", true)// 自动嗅探发现集群节点
//                 .build();
//
//         // TransportClient client = new PreBuiltTransportClient(settings);
//         // client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(node), port));
//         log.info("初始化客户端成功");
//
//
//         List<Map<String, Object>> addList = new ArrayList<>();
//         //指定一个index和type
//         // SearchRequestBuilder search = client.prepareSearch("users2").setTypes("type");
//         // SearchRequestBuilder search = new SearchRequestBuilder("users2").setTypes("type");
//         SearchRequest search = new SearchRequest("users2");
//         SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//         //使用原生排序优化性能
//         //search.addSort("_doc", SortOrder.ASC);
//         //设置每批读取的数据量
//         // search.setSize(100);
//         //默认是查询所有
//         // search.setQuery(QueryBuilders.queryStringQuery("*:*"));
//         //设置 search context 维护1分钟的有效期
//         // search.setScroll(TimeValue.timeValueMinutes(1));
//
//         searchSourceBuilder.sort("_doc", SortOrder.ASC);
//         searchSourceBuilder.size(100);
//         searchSourceBuilder.query(QueryBuilders.queryStringQuery("*:*"));
//
//         search.source(searchSourceBuilder);
//
//         //获得首次的查询结果
//         // SearchResponse scrollResp = search.get();
//         SearchResponse scrollResp = client.search(search, RequestOptions.DEFAULT);
//         //打印命中数量、
//         log.info("命中总数量：" + scrollResp.getHits().getTotalHits());
//         int count = 1;
//         do {
//             log.info("第" + count + "次打印数据：");
//             for (SearchHit hit : scrollResp.getHits().getHits()) {
//                 addList.add(hit.getSourceAsMap());
//             }
//             count++;
//             //将scorllId循环传递
//             scrollResp =
//                     client.scroll(new SearchScrollRequest(scrollResp.getScrollId()))
//                             .setScroll(TimeValue.timeValueMinutes(1)).execute().actionGet();
//
//         } while (scrollResp.getHits().
//
//                 getHits().length != 0);
//         //当searchHits的数组为空的时候结束循环，至此数据全部读取完毕
//         BulkRequestBuilder bulkRequest = client.prepareBulk();
//         for (
//                 int i = 0; i < addList.size(); i++) {
//             bulkRequest.add(client.prepareIndex("users", "type").setSource(addList.get(i)));
//             // 每1000条提交一次
//             if (i % 1000 == 0) {
//                 bulkRequest.execute().actionGet();
//                 bulkRequest = client.prepareBulk();
//             }
//         }
//         bulkRequest.execute().actionGet();
//     }
//
//
//     public void main(String[] args) throws UnknownHostException {
//         indexMigration();
//     }
// }
