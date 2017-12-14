package com.hai.bpm.avtiviti;

import com.hai.bpm.avtiviti.model.Student;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/7.
 */
public class HelloWordActiviti extends BaseActiviti {

    @Test
    public void deploy() {
//        toDeploy("diagrams/helloWord.bpmn", "HelloWordProcess");
        toDeploy("diagrams/secondProcess.bpmn", "SecondProcess", 3);
    }

    @Test
    public void deployWithZip() {
        deployWithZip("diagrams/helloWord.zip", "HelloWordProcessWithZip");
    }

    @Test
    public void deleteDeploy() {
        repositoryService.deleteDeployment("12501", true);//processId,cascade:默认false，true：级联删除
    }

    @Test
    public void start() {
        String[] processKeys = {"myProcess", "helloWordProcess", "secondProcess"};
        start(processKeys);
    }

    @Test
    public void findProcessDefine() {
        findProcessDefine("hello");
    }


    @Test
    public void processState() {
        processState("50001");
    }

    @Test
    public void findImage() throws IOException {
        findImage("47509", "diagrams/secondProcess.secondProcess.png");
    }

    /**
     * 查看任务
     */
    @Test
    public void findTask() {
        findTask("hai");
    }

    @Test
    public void setVariablesValues() {
        String taskId = "50011";
        taskService.setVariableLocal(taskId, "days", 2);
        taskService.setVariable(taskId, "reason", "sick");
        taskService.setVariable(taskId, "student", new Student(1, "hai"));
        taskService.setVariable(taskId, "date", new Date());
    }

    /**
     * 完成指定 ID 的任务
     */
    @Test
    public void completeTaskTest() {
        completeTask("50011");
    }


    @Test
    public void getVariablesValue() {
        String taskId = "50011"; // 任务id
        Integer days = (Integer) taskService.getVariable(taskId, "days");
        Date date = (Date) taskService.getVariable(taskId, "date");
        String reason = (String) taskService.getVariable(taskId, "reason");
        Student student = (Student) taskService.getVariable(taskId, "student");
        System.out.println("请假天数：" + days);
        System.out.println("请假日期：" + dateFormat.format(date));
        System.out.println("请假原因：" + reason);
        System.out.println("请假对象：" + student);
    }

    @Test
    public void historyTaskList() {
        historyTaskList("hai");
    }

    @Test
    public void getHistoryProcessInstance() {
        getHistoryProcessInstance("25001");
    }

    /**
     * 历史活动查询
     */
    @Test
    public void historyActInstanceList() {
        historyActInstanceList("25001");
    }
}
