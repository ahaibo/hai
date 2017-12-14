package com.hai.bpm.avtiviti.listener;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;

/**
 * link https://www.activiti.org/userguide/index.html#configuration
 * Created by Administrator on 2017/12/7.
 */
public class MyEventListener implements ActivitiEventListener {
    @Override
    public void onEvent(ActivitiEvent activitiEvent) {
        switch (activitiEvent.getType()) {
            case JOB_EXECUTION_SUCCESS:
                System.out.println("A job well done!");
                break;
            case JOB_EXECUTION_FAILURE:
                System.out.println("A job has failed...");
                break;
            case JOB_CANCELED:
                System.out.println("A job has canceled...");
                break;
            case JOB_RETRIES_DECREMENTED:
                System.out.println("A job has retries decremented...");
                break;
            default:
                System.out.println("Event received: " + activitiEvent.getType());
        }
    }

    @Override
    public boolean isFailOnException() {
        // The logic in the onEvent method of this listener is not critical, exceptions
        // can be ignored if logging fails...
        return false;
    }
}
