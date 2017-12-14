package com.hai.bpm.avtiviti;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/7.
 */
public class LineActiviti extends BaseActiviti {

    @Test
    public void deploy() {
        toDeploy("diagrams/LineProcess.bpmn", "LineProcess", 3);
    }

    @Test
    public void deleteDeploy() {
        repositoryService.deleteDeployment("12501", true);//processId,cascade:默认false，true：级联删除
    }

    @Test
    public void start() {
//        String[] processKeys = {"myProcess", "helloWordProcess", "secondProcess", "lineProcess"};
        String[] processKeys = {"lineProcess"};
        start(processKeys);
    }

    @Test
    public void findProcessDefine() {
        findProcessDefine("lineProcess");
    }

    @Test
    public void processState() {
        processState("50001");
    }

    @Test
    public void findImage() throws IOException {
        findImage("47509", "diagrams/secondProcess.secondProcess.png");
    }

    @Test
    public void findTask() {
        findTask("zhangsan");
        findTask("lisi");
        findTask("wangwu");
    }

    @Test
    public void completeTaskTest() {
        Map<String, Object> variables = new HashMap();
        variables.put("msg", "重要情况");
        completeTask("80004", variables);
    }

    @Test
    public void historyTaskList() {
        historyTaskList("hai");
    }

    @Test
    public void getHistoryProcessInstance() {
        getHistoryProcessInstance("25001");
    }

    @Test
    public void historyActInstanceList() {
        historyActInstanceList("25001");
    }
}
