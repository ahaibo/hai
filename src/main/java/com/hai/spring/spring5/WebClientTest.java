package com.hai.spring.spring5;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/11/20.
 */
public class WebClientTest {


    public void test1(HttpServletRequest request, HttpServletResponse response) {
//        webclient
        Mono personMono = WebClient
                .create("http://localhost:8080")
                .get()
                .uri("/person/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .then();
//                .then(response -> response.bodyToMono(Person.class));
    }
}
