package com.hai.bpm.avtiviti;

import com.alibaba.fastjson.JSONObject;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipInputStream;

/**
 * Created by Administrator on 2017/12/7.
 */
public class BaseActiviti extends ActivitiProcessEngine {


    public void toDeploy(String bpmn, String processName) {
        toDeploy(bpmn, processName, 1);
    }

    public void toDeploy(String bpmn, String processName, int deployCount) {
        for (int i = 0; i < deployCount; i++) {
            deployment = repositoryService
                    .createDeployment()
                    .addClasspathResource(bpmn)
                    .name(processName)
                    .deploy();
            System.out.println(JSONObject.toJSONString(deployment, true) + "\n");
        }
    }

    public void deployWithZip(String resources, String name) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resources);
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        deployment = repositoryService
                .createDeployment()
                .addZipInputStream(zipInputStream)
                .name(name)
                .deploy();
        System.out.println(JSONObject.toJSONString(deployment, true));
    }

    public void start(String[] processKeys) {
        start(processKeys, new String[processKeys.length]);
    }

    public void start(String[] processKeys, String[] businessKey) {
        start(processKeys, businessKey, new HashMap[processKeys.length]);
    }

    public void start(String[] processKeys, Map<String, Object>[] variables) {
        start(processKeys, new String[processKeys.length], variables);
    }

    public void start(String[] processKeys, String[] businessKeys, Map<String, Object>[] variables) {
        for (int i = 0, len = processKeys.length; i < len; i++) {
            ProcessInstance instance = runtimeService.startProcessInstanceByKey(processKeys[i], businessKeys[i], variables[i]);
            System.out.println("ProcessInstance Id: " + instance.getId());
            System.out.println("ProcessDefinitionId Id: " + instance.getProcessDefinitionId() + "\n");
        }
    }

    public void findProcessDefineById(String pid) {
        printProcessDefinition(repositoryService.createProcessDefinitionQuery().processDefinitionId(pid).list());
    }

    public void findProcessDefine(String processKey) {
        printProcessDefinition(repositoryService.createProcessDefinitionQuery().processDefinitionKey(processKey).list());
    }

    private void printProcessDefinition(List<ProcessDefinition> processDefinitions) {
        for (ProcessDefinition processDefinition : processDefinitions) {
            printProcessDefinition(processDefinition);
        }
    }

    private void printProcessDefinition(ProcessDefinition processDefinition) {
        System.out.println("processDefinition Id:\t\t\t" + processDefinition.getId());
        System.out.println("processDefinition Key:\t\t\t" + processDefinition.getKey());
        System.out.println("processDefinition Name:\t\t\t" + processDefinition.getName());
        System.out.println("processDefinition ResourceName:\t" + processDefinition.getResourceName() + "\n");
    }

    @Test
    public void listLastVersionProcess() {
        //一个流程可部署多次，所以可能存在多个版本
        //查询最后版本的流程基本思路：据 version 排序；据 key 放入 map，去重；得到最后版本的流程集合
        List<ProcessDefinition> list = repositoryService
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion()
                .asc()
                .list();

        Map<String, ProcessDefinition> map = new HashMap<>();
        for (ProcessDefinition processDefinition : list) {
            map.put(processDefinition.getKey(), processDefinition);
        }

        List<ProcessDefinition> processDefinitionList = new LinkedList<>(map.values());
        for (ProcessDefinition processDefinition : processDefinitionList) {
            printProcessDefinition(processDefinition);
        }
    }

    public void processState(String processId) {
        ProcessInstance instance = runtimeService
                .createProcessInstanceQuery()
                .processDefinitionId(processId)
                .singleResult();
        System.out.println(null == instance ? "流程正在运行" : "流程已执行结束");
    }


    public void findImage(String deployId, String resourceName) throws IOException {
        InputStream inputStream = repositoryService.getResourceAsStream(deployId, resourceName);
        FileUtils.copyInputStreamToFile(inputStream, new File("D:/Data/test/activiti/" + resourceName));
    }

    public void findTask(String taskAssignee) {
        // 查询并且返回任务即可
        List<Task> taskList = taskService.createTaskQuery()  // 创建任务查询
                .taskAssignee(taskAssignee) // 指定某个人
                .list();
        printTask(taskList);
    }

    public void findTaskByProcessInstanceId(String processInstanceId) {
        printTask(taskService.createTaskQuery().processInstanceId(processInstanceId).list());
    }


    protected void printTask(Task task) {
        printTask(Arrays.asList(task));
    }

    protected void printTask(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println("任务ID:\t\t" + task.getId());
            System.out.println("任务名称：\t" + task.getName());
            System.out.println("任务创建时间：" + task.getCreateTime());
            System.out.println("任务委派人：\t" + task.getAssignee());
            System.out.println("流程实例ID:\t" + task.getProcessInstanceId() + "\n");
//            completeTask(task.getId());
        }
    }


    public void completeTask(String taskId) {
        completeTask(taskId, new Map[]{null});
    }

    public void completeTask(String taskId, Map<String, Object> variables) {
        completeTask(taskId, new Map[]{variables});
    }

    public void completeTask(String taskIds, Map<String, Object>[] variables) {
        //complete task
        String[] ids = taskIds.split(",");
        for (int i = 0, len = ids.length; i < len; i++) {
            System.out.println("Complete Task for：" + ids[i]);
            engine.getTaskService().complete(ids[i], variables[i]);
            System.out.println("Task " + ids[i] + " completed!...\n");
        }
    }


    public void historyTaskList(String taskAssignee) {
        List<HistoricTaskInstance> list = historyService
                .createHistoricTaskInstanceQuery()
                .taskAssignee(taskAssignee)
                .finished()
                .list();
        for (HistoricTaskInstance hti : list) {
            System.out.println(JSONObject.toJSONString(hti));
        }
    }

    public void getHistoryProcessInstance(String processId) {
        HistoricProcessInstance hpi = historyService
                .createHistoricProcessInstanceQuery()
                .processInstanceId(processId)
                .singleResult();
        System.out.println(JSONObject.toJSONString(hpi));
    }

    /**
     * 历史活动查询
     */
    public void historyActInstanceList(String processId) {
        List<HistoricActivityInstance> list = historyService // 历史任务Service
                .createHistoricActivityInstanceQuery() // 创建历史活动实例查询
                .processInstanceId(processId) // 指定流程实例id
                .finished() // 查询已经完成的任务
                .list();
        for (HistoricActivityInstance hai : list) {
            System.out.println("任务ID:" + hai.getId());
            System.out.println("流程实例ID:" + hai.getProcessInstanceId());
            System.out.println("活动名称：" + hai.getActivityName());
            System.out.println("办理人：" + hai.getAssignee());
            System.out.println("开始时间：" + hai.getStartTime());
            System.out.println("结束时间：" + hai.getEndTime());
            System.out.println("===========================");
        }
    }
}
