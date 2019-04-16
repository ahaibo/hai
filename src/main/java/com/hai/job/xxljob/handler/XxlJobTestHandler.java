package com.hai.job.xxljob.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XxlJobTestHandler extends IJobHandler {
    private Logger logger = LoggerFactory.getLogger(XxlJobTestHandler.class);

    @Override
    public ReturnT<String> execute(String param) {
        logger.info(this.getClass().getName() + ".execute. param: %s", param);
        return null;
    }
}
