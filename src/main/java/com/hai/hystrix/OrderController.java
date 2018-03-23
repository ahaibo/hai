package com.hai.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2018/3/4.
 */
@RestController
public class OrderController {

    private static AtomicInteger counter = new AtomicInteger(0);
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/createOrder")
    public ResponseEntity<String> createOrder() {
        if (new Random().nextInt(10000) % 2 == 0) {
            System.out.println("error...");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("internal error!");
        }
        System.out.println("创建订单，序号为：" + counter.incrementAndGet());
        return ResponseEntity.ok("successfully");
    }

}
