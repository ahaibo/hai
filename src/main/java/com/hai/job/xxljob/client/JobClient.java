package com.hai.job.xxljob.client;

import com.hai.job.xxljob.model.XxlJobInfo;
import com.hai.job.xxljob.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Component
public class JobClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${xxl.job.admin.addresses}")
    private String[] jobAdminUrl;

    private static String add = "/jobinfo/add";
    private static String update = "/jobinfo/update";
    private static String remove = "/jobinfo/remove";
    private static String pause = "/jobinfo/pause";
    private static String resume = "/jobinfo/resume";
    private static String getJobInfoByBiz = "/jobinfo/getJobInfoByBiz";

    public JobClient() {
    }

    public ResponseEntity<String> addJob(XxlJobInfo xxlJobInfo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> xxlJobInfoMap = MapUtil.obj2Map(xxlJobInfo);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(xxlJobInfoMap, headers);
        ResponseEntity<String> response = this.restTemplate.postForEntity(this.getLoadUrl(add), request, String.class, new Object[0]);
        return response;
    }

    public ResponseEntity<String> updateJob(XxlJobInfo xxlJobInfo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> xxlJobInfoMap = MapUtil.obj2Map(xxlJobInfo);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(xxlJobInfoMap, headers);
        ResponseEntity<String> response = this.restTemplate.postForEntity(this.getLoadUrl(update), request, String.class, new Object[0]);
        return response;
    }

    public ResponseEntity<String> removeJob(Integer xxlJobInfoId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<Integer> request = new HttpEntity(xxlJobInfoId, headers);
        ResponseEntity<String> response = this.restTemplate.postForEntity(this.getLoadUrl(remove), request, String.class, new Object[0]);
        return response;
    }

    public ResponseEntity<String> pauseJob(int xxlJobInfoId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<Integer> request = new HttpEntity(xxlJobInfoId, headers);
        ResponseEntity<String> response = this.restTemplate.postForEntity(this.getLoadUrl(pause), request, String.class, new Object[0]);
        return response;
    }

    public ResponseEntity<String> resumeJob(int xxlJobInfoId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<Integer> request = new HttpEntity(xxlJobInfoId, headers);
        ResponseEntity<String> response = this.restTemplate.postForEntity(this.getLoadUrl(resume), request, String.class, new Object[0]);
        return response;
    }

    public ResponseEntity<String> getJobInfoByBizJob(XxlJobInfo xxlJobInfo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> xxlJobInfoMap = MapUtil.obj2Map(xxlJobInfo);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(xxlJobInfoMap, headers);
        ResponseEntity<String> response = this.restTemplate.postForEntity(this.getLoadUrl(getJobInfoByBiz), request, String.class, new Object[0]);
        return response;
    }

    public String getLoadUrl(String method) {
        int length = this.jobAdminUrl.length;
        Random random = new Random();
        int i = random.nextInt(length);
        String url = this.jobAdminUrl[i] + method;
        return url;
    }
}