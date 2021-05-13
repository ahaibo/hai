// package com.hai.seo.es;
//
// @Component
// public class ESFactory {
//
//     private static JestHttpClient client;
//
//     private static final LinkedHashSet<String> servers = new LinkedHashSet<String>();
// 	//es节点   ip:port,ip:port......
//     @Value("${es.conn}")
//     private String esConn;
//
//     @PostConstruct
//     public void init() {
//         if (client == null) {
//             Collections.addAll(servers, esConn.split(","));
//         }
//     }
//
//     public JestHttpClient getClient() {
//         try {
//             if (client == null) {
//                 synchronized (ESFactory.class) {
//                     if (client == null) {
//                         JestClientFactory factory = new JestClientFactory();
//                         factory.setHttpClientConfig(new HttpClientConfig.Builder(servers).defaultMaxTotalConnectionPerRoute(2000).maxTotalConnection(5000).connTimeout(3000).readTimeout(3000).multiThreaded(true).build());
//                         client = (JestHttpClient) factory.getObject();
//                     }
//                 }
//             }
//             return client;
//         } catch (Exception ex) {
//             ex.printStackTrace();
//         }
//         return null;
//     }
// }