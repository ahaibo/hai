package com.hai.bpm.avtiviti;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/7.
 */
public class ParallelGatewayActiviti extends BaseActiviti {

    @Test
    public void deploy() {
        toDeploy("diagrams/ParallelGatewayProcess.bpmn", "My Task Listener ParallelGatewayProcess", 2);
    }

    @Test
    public void deleteDeploy() {
        repositoryService.deleteDeployment("122501", true);//processId,cascade:默认false，true：级联删除
    }

    @Test
    public void start() {
//        String[] processKeys = {"myProcess", "helloWordProcess", "secondProcess", "lineProcess"};
        String[] processKeys = {"myVarParallelGatewayProcess"};

        String v = "3";
        HashMap<String, Object> variable = new HashMap<>();
        //设置任务委派人变量
        variable.put("userName", "hai-parallel-" + v);
        variable.put("banzhangName", "banzhang-parallel-" + v);
        variable.put("teacherName", "teacher-parallel-" + v);
        variable.put("xiaozhangName", "xiaozhang-parallel-" + v);

        //变量指定 taskAssignee
        start(processKeys, new HashMap[]{variable});
    }

    @Test
    public void findProcessDefineByKey() {
        findProcessDefine("myVarParallelGatewayProcess");
    }

    @Test
    public void findProcessDefineById() {
        findProcessDefine("127501");
    }

    @Test
    public void processState() {
        processState("92504");
    }

    @Test
    public void findImage() throws IOException {
        findImage("92501", "diagrams/ParallelGatewayProcess.myParallelGatewayProcess.png");
    }

    @Test
    public void findTask() {
//        findTask("hai-parallel");
//        findTaskByProcessInstanceId("110001");
        findTaskByProcessInstanceId("127501");
    }

    @Test
    public void completeTaskTest() {
        Map<String, Object> variables = new HashMap();
//        variables.put("msg", "重要情况");
//        variables.put("days", "8");
        completeTask("127509", variables);
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
