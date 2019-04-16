package com.hai.job.xxljob.model;

import lombok.Data;

import java.util.Date;

@Data
public class XxlJobInfo {
    private int id;
    private int jobGroup;
    private String jobCron;
    private String jobDesc;
    private Date addTime;
    private Date updateTime;
    private String author;
    private String alarmEmail;
    private String executorRouteStrategy;
    private String executorHandler;
    private String executorParam;
    private String executorBlockStrategy;
    private String executorFailStrategy;
    private int executorTimeout;
    private String glueType;
    private String glueSource;
    private String glueRemark;
    private Date glueUpdatetime;
    private String childJobId;
    private String jobStatus;
    private String appName;
    private Integer bizType;
    private String bizCode;

    public XxlJobInfo() {
    }
}
