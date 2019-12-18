//package com.hai.hystrix;
//
//import com.netflix.hystrix.HystrixCommand;
//import com.netflix.hystrix.HystrixCommandGroupKey;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
///**
// * Created by Administrator on 2018/3/4.
// */
//public class LimitFllowCommand extends HystrixCommand<String> {
//
//    private RestTemplate restTemplate;
//
//    protected LimitFllowCommand(HystrixCommandGroupKey group) {
//        super(group);
//    }
//
//    protected LimitFllowCommand(RestTemplate restTemplate) {
//        //hystrix.properties: hystrix.threadpool.orderServiceGroup.${keyName}
//        super(HystrixCommandGroupKey.Factory.asKey("orderServiceGroup"));
//        this.restTemplate = restTemplate;
//    }
//
//    //调用访问方法
//    @Override
//    protected String run() throws Exception {
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://order-service/createOrder", String.class);
//        return responseEntity.getBody();
//    }
//
//    //降级处理：调用备用服务/mock/缓存
//    @Override
//    protected String getFallback() {
//        //根据业务制定自己的降级策略
////        return super.getFallback();
//        return "自定义降级处理";
//    }
//}
