package com.hai.job.xxljob.config;

import com.xxl.job.core.executor.XxlJobExecutor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.hai.job.xxljob.handler")
@Data
public class XxlJobConfig {
    private Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.executor.appname}")
    private String appName;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;

    public XxlJobExecutor xxlJobExecutor(){
        logger.info("----------- xxl-job config init.");
        XxlJobExecutor xxlJobExecutor=new XxlJobExecutor();
        xxlJobExecutor.setAppName(this.appName);
        xxlJobExecutor.setAdminAddresses(this.adminAddresses);
        xxlJobExecutor.setIp(this.ip);
        xxlJobExecutor.setPort(this.port);
        xxlJobExecutor.setAccessToken(this.accessToken);
        xxlJobExecutor.setLogPath(this.logPath);
        xxlJobExecutor.setLogRetentionDays(this.logRetentionDays);
        return xxlJobExecutor;
    }
}
