package com.hai.bpm.avtiviti;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/7.
 */
public class ExclusiveGatewayActiviti extends BaseActiviti {

    @Test
    public void deploy() {
        toDeploy("diagrams/ExclusiveGatewayProcess.bpmn", "My ExclusiveGatewayProcess", 3);
    }

    @Test
    public void deleteDeploy() {
        repositoryService.deleteDeployment("12501", true);//processId,cascade:默认false，true：级联删除
    }

    @Test
    public void start() {
//        String[] processKeys = {"myProcess", "helloWordProcess", "secondProcess", "lineProcess"};
        String[] processKeys = {"myExclusiveGatewayProcess"};
        start(processKeys);
    }

    @Test
    public void findProcessDefine() {
        findProcessDefine("myExclusiveGatewayProcess");
    }

    @Test
    public void processState() {
        processState("50001");
    }

    @Test
    public void findImage() throws IOException {
        findImage("47509", "diagrams/myExclusiveGatewayProcess.myExclusiveGatewayProcess.png");
    }

    @Test
    public void findTask() {
        findTask("exclusiveGatewayStudent");
        findTask("exclusiveGatewayBanzhang");
        findTask("exclusiveGatewayTeacher");
        findTask("exclusiveGatewayXiaozhang");
    }

    @Test
    public void completeTaskTest() {
        Map<String, Object> variables = new HashMap();
//        variables.put("msg", "重要情况");
//        variables.put("days", "8");
        completeTask("87505", variables);
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
