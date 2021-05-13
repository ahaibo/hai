// package com.hai.seo.es;
//
// public class ESService {
//
//     private static Logger LogUtil = LoggerFactory.getLogger(ESFactory.class);
//
//     @Autowired
//     private ESFactory esFactory;
//
//     /**
//      * 新增
//      * @param index 索引
//      * @param type 类型
//      * @param docId  组件id
//      * @param bodys dsl
//      * @return
//      */
//     public JestResult add(String index, String type,String docId, String... bodys) {
//         JestResult jestResult = null;
//         try {
//             Bulk.Builder bulkBuilder = new Bulk.Builder();
//             for (String body : bodys) {
//                 Index builder = new Index.Builder(body).index(index).type(type).id(docId).build();
//                 bulkBuilder.addAction(builder);
//             }
//             jestResult = esFactory.getClient().execute(bulkBuilder.build());
//             if (jestResult != null) {
//                 LogUtil.debug(
//                         StringUtils.join(
//                                 new String[]{
//                                         "add",
//                                         index,
//                                         type,
//                                         bodys.toString(),
//                                         jestResult.getErrorMessage(),
//                                         String.valueOf(jestResult.getResponseCode()),
//                                         String.valueOf(jestResult.isSucceeded())},
//                                 "------")
//                 );
//             }
//         } catch (Exception ex) {
//             LogUtil.error(ex.getMessage(), ex);
//         }
//         return jestResult;
//     }
//
//     /**
//      * 删除
//      * @param index 索引
//      * @param type 类型
//      * @param id doc-id
//      * @return
//      */
//     public JestResult del(String index, String type, String id) {
//         JestResult jestResult = null;
//         try {
//             Delete builder = new Delete.Builder(id).index(index).type(type).build();
//             jestResult = esFactory.getClient().execute(builder);
//             if (jestResult != null) {
//                 LogUtil.debug(
//                         StringUtils.join(
//                                 new String[]{
//                                         "del",
//                                         index,
//                                         type,
//                                         id,
//                                         jestResult.getErrorMessage(),
//                                         String.valueOf(jestResult.getResponseCode()),
//                                         String.valueOf(jestResult.isSucceeded())},
//                                 "------")
//                 );
//             }
//         } catch (Exception ex) {
//             LogUtil.error(ex.getMessage(), ex);
//         }
//         return jestResult;
//     }
//
//     /**
//      * 默认更新，不重试
//      * @param index 索引
//      * @param type 类型
//      * @param id id
//      * @param upsertScript 更新脚本
//      * @return
//      */
//     public JestResult upsert(String index, String type, String id, String upsertScript) {
//         return upsert(index, type, id, upsertScript, 2);
//     }
//
//     /**
//      *
//      * @param index 索引
//      * @param type 类型
//      * @param id id
//      * @param upsertScript 更新脚本
//      * @param retry_on_conflict 失败重试
//      * @return
//      */
//     public JestResult upsert(String index, String type, String id, String upsertScript, int retry_on_conflict) {
//         JestResult result = null;
//         LogUtil.info("upsert....upsertScript...:index="+index+",type="+type+",id="+id+",upsertScript="+upsertScript);
//         try {
//             Update update = new Update.Builder(upsertScript).index(index).type(type).id(id).setParameter(Parameters.RETRY_ON_CONFLICT, retry_on_conflict).build();
//             result = esFactory.getClient().execute(update);
//             if (!result.isSucceeded()) {
//                 throw new Exception(upsertScript + ":ES " + result.getErrorMessage());
//             }
//
//             if (result != null) {
//                 LogUtil.debug(
//                         StringUtils.join(
//                                 new String[]{
//                                         "upsert",
//                                         index,
//                                         type,
//                                         upsertScript,
//                                         result.getErrorMessage(),
//                                         String.valueOf(result.getResponseCode()),
//                                         String.valueOf(result.isSucceeded())},
//                                 "------"
//                         )
//                 );
//             }
//         } catch (Exception e) {
//             LogUtil.error(e.getMessage(), e);
//         }
//         return result;
//     }
//
//     /**
//      * 自定义查询
//      * @param index 索引
//      * @param type 类型
//      * @param dsl dsl
//      * @return
//      */
//     public SearchResult search(String index, String type, String dsl) {
//         SearchResult searchResult = null;
//         try {
//             Search search = new Search.Builder(dsl)
//                     .addIndex(index)
//                     .addType(type)
//                     .build();
//             searchResult = esFactory.getClient().execute(search);
//             if (searchResult != null) {
// //                LogUtil.info("search...jestResult:" + JSONObject.toJSONString(jestResult));
//                 LogUtil.debug(
//                         StringUtils.join(
//                                 new String[]{
//                                         "query",
//                                         index,
//                                         type,
//                                         dsl,
//                                         searchResult.getErrorMessage(),
//                                         String.valueOf(searchResult.getResponseCode()),
//                                         String.valueOf(searchResult.isSucceeded())},
//                                 "------")
//                 );
//             }
//
//
//         } catch (Exception e) {
//             LogUtil.error(e.getMessage(), e);
//         }
//         return searchResult;
//     }
//
//     public JestResult bulkUpsert(String index, String type, int retry_on_conflict, Map<String, String> upsertScript) {
//         JestResult jestResult = null;
//         if (upsertScript == null || upsertScript.size() == 0) {
//             return jestResult;
//         }
//         try {
//             Bulk.Builder bulkBuilder = new Bulk.Builder();
//             for (Map.Entry<String, String> script : upsertScript.entrySet()) {
//                 Update update = new Update.Builder(script.getValue()).index(index).type(type).id(script.getKey()).setParameter(Parameters.RETRY_ON_CONFLICT, retry_on_conflict).build();
//                 bulkBuilder.addAction(update);
//             }
//             jestResult = esFactory.getClient().execute(bulkBuilder.build());
//
//
//             if (jestResult != null) {
//                 LogUtil.debug(
//                         StringUtils.join(
//                                 new String[]{
//                                         "bulkUpsert",
//                                         index,
//                                         type,
//                                         upsertScript.toString(),
//                                         jestResult.getErrorMessage(),
//                                         String.valueOf(jestResult.getResponseCode()),
//                                         String.valueOf(jestResult.isSucceeded())},
//                                 "------")
//                 );
//             }
//         } catch (Exception ex) {
//             LogUtil.error(ex.getMessage(), ex);
//         }
//         return jestResult;
//     }
//
//     public SearchResult scrollQuery(String queryBody, String index, String type, Integer size) {
//         SearchResult searchResult = null;
//         try {
//             Search search = new Search.Builder(queryBody)
//                     .addIndex(index)
//                     .addType(type)
//                     .setParameter(Parameters.SIZE, size)
//                     .setParameter(Parameters.SCROLL, "1m")
//                     .setSearchType(SearchType.SCAN)
//                     .build();
//
//             searchResult = esFactory.getClient().execute(search);
//             if (searchResult != null) {
//                 LogUtil.debug(
//                         StringUtils.join(
//                                 new String[]{
//                                         "scrollQuery",
//                                         index,
//                                         type,
//                                         queryBody,
//                                         searchResult.getErrorMessage(),
//                                         String.valueOf(searchResult.getResponseCode()),
//                                         String.valueOf(searchResult.isSucceeded())},
//                                 "------")
//                 );
//             }
//         } catch (Exception e) {
//             LogUtil.error(e.getMessage(), e);
//         }
//         return searchResult;
//     }
//
//     public JestResult scrollQuery(String scroll_id, Integer size) {
//         JestResult jestResult = null;
//         try {
//             SearchScroll searchScroll = new SearchScroll.Builder(scroll_id, "1m").build();
//             jestResult = esFactory.getClient().execute(searchScroll);
//
//             if (jestResult != null) {
//                 LogUtil.debug(
//                         StringUtils.join(
//                                 new String[]{
//                                         "scrollQuery",
//                                         scroll_id,
//                                         jestResult.getErrorMessage(),
//                                         String.valueOf(jestResult.getResponseCode()),
//                                         String.valueOf(jestResult.isSucceeded())},
//                                 "------")
//                 );
//             }
//         } catch (Exception e) {
//             LogUtil.error(e.getMessage(), e);
//         }
//         return jestResult;
//     }
//
//     public JestResult delByQuery(String index, String type, String query) {
//         JestResult result = null;
//         try {
//             DeleteByQuery deleteByQuery = new DeleteByQuery.Builder(query)
//                     .addIndex(index)
//                     .addType(type)
//                     .build();
//             result = esFactory.getClient().execute(deleteByQuery);
//
//             if (result != null) {
//                 LogUtil.debug(
//                         StringUtils.join(
//                                 new String[]{
//                                         "delByQuery",
//                                         index,
//                                         type,
//                                         query,
//                                         result.getErrorMessage(),
//                                         String.valueOf(result.getResponseCode()),
//                                         String.valueOf(result.isSucceeded())},
//                                 "------")
//                 );
//             }
//         } catch (Exception ex) {
//             LogUtil.error(ex.getMessage(), ex);
//         }
//         return result;
//     }
//
//     public JestResult searchCount(String index, String type, String dsl) {
//         JestResult jestResult = null;
//         try {
//             Search search = new Search.Builder(dsl)
//                     .setSearchType(SearchType.COUNT)
//                     .addIndex(index)
//                     .addType(type)
//                     .build();
//             jestResult = esFactory.getClient().execute(search);
//
//             if (jestResult != null) {
//                 LogUtil.debug(
//                         StringUtils.join(
//                                 new String[]{
//                                         "query",
//                                         index,
//                                         type,
//                                         dsl,
//                                         jestResult.getErrorMessage(),
//                                         String.valueOf(jestResult.getResponseCode()),
//                                         String.valueOf(jestResult.isSucceeded())},
//                                 "------")
//                 );
//             }
//         } catch (Exception e) {
//             LogUtil.error(e.getMessage(), e);
//         }
//         return jestResult;
//     }
//
//     /**
//      * 建议搜索
//      * @param index
//      * @param type
//      * @param dsl
//      */
//     public SuggestResult suggest(String index, String type,String dsl){
//     	SuggestResult suggestResult = null;
//
//     	try {
//     		Suggest suggest=new Suggest.Builder(dsl).addIndex(index)
//                     //.addType(type)
//                     .build();
//     		suggestResult= esFactory.getClient().execute(suggest);
// 		} catch (IOException e) {
// 			LogUtil.error(e.getMessage(), e);
// 		}
//     	return suggestResult;
//     }
// }