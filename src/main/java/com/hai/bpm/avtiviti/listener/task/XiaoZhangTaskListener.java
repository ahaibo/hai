package com.hai.bpm.avtiviti.listener.task;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * Created by Administrator on 2017/12/10.
 */
public class XiaoZhangTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("assignee-xiaozhang-listener");
    }
}
