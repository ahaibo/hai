package com.hai.bpm.avtiviti;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.form.api.FormRepositoryService;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/12/7.
 */
public class ActivitiProcessEngine {

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ProcessEngine engine = null;
    FormService formService = null;
    org.activiti.form.api.FormService formEngineFormService = null;
    FormRepositoryService formRepositoryService = null;
    DynamicBpmnService dynamicBpmnService = null;
    HistoryService historyService = null;
    IdentityService identityService = null;
    ManagementService managementService = null;
    RuntimeService runtimeService = null;
    RepositoryService repositoryService = null;
    TaskService taskService = null;
    Deployment deployment = null;

    @Before
    public void init() {
        engine = ProcessEngines.getDefaultProcessEngine();
        getService();
    }

    @Test
    public void getService() {
        formService = engine.getFormService();
        formEngineFormService = engine.getFormEngineFormService();
        formRepositoryService = engine.getFormEngineRepositoryService();
        dynamicBpmnService = engine.getDynamicBpmnService();
        historyService = engine.getHistoryService();
        identityService = engine.getIdentityService();
        managementService = engine.getManagementService();
        runtimeService = engine.getRuntimeService();
        repositoryService = engine.getRepositoryService();
        taskService = engine.getTaskService();

        List list = Arrays.asList(formService, formEngineFormService, formRepositoryService, dynamicBpmnService, historyService, identityService, managementService, runtimeService, repositoryService, taskService);
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

    @Test
    public void createTables() {
        ProcessEngineConfiguration config = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        config.setJdbcDriver("com.mysql.jdbc.Driver");
        //高版本 mysql-connector 的URL配置
        config.setJdbcUrl("jdbc:mysql://localhost:3306/db_activiti?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        config.setJdbcUsername("root");
        config.setJdbcPassword("123456");
        /**
         * false 不能自动创建表
         * create-drop 先删除表再创建表
         * true 自动创建和更新表
         */
        config.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        // 获取流程引擎对象：执行此句代码会创建 activiti 相关表[本版本6.0.0是28张]。
        ProcessEngine engine = config.buildProcessEngine();
//        ACT_RE_*: 'RE'表示repository。 这个前缀的表包含了流程定义和流程静态资源 （图片，规则，等等）。
//        ACT_RU_*: 'RU'表示runtime。 这些运行时的表，包含流程实例，任务，变量，异步任务，等运行中的数据。 Activiti只在流程实例执行过程中保存这些数据， 在流程结束时就会删除这些记录。 这样运行时表可以一直很小速度很快。
//        ACT_ID_*: 'ID'表示identity。 这些表包含身份信息，比如用户，组等等。
//        ACT_HI_*: 'HI'表示history。 这些表包含历史数据，比如历史流程实例， 变量，任务等等。
//        ACT_GE_*: 'GE'表示general。通用数据， 用于不同场景下，如存放资源文件。
    }

    //使用xml配置 简化
    @Test
    public void testCreateTableWithXml() {
        // 引擎配置
        ProcessEngineConfiguration pec = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 获取流程引擎对象
        ProcessEngine processEngine = pec.buildProcessEngine();
    }

    //使用默认设置，默认读取 activiti.cfg.xml 配置文件
    @Test
    public void createTablesByConf() {
        //根据 activiti.cfg.xml 创建 ProcessEngine，其配置文件中必须要有 id 为 processEngineConfiguration(org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration) 的配置
        ProcessEngines.getDefaultProcessEngine();
    }

}
