package com.hai.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2018/3/4.
 */
@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/user/createOrder")
    public ResponseEntity<String> createOrder() {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://order-service/createOrder", String.class);
        System.out.println("entity: " + entity);
        return entity;
    }

    @RequestMapping("/user/createOrderByHystrix")
    public ResponseEntity<String> createOrderByHystrix() {
        String result = new LimitFllowCommand(restTemplate).execute();
        System.out.println("result: " + result);
        return ResponseEntity.ok(result);
    }

}
