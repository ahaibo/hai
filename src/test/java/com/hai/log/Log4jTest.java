package com.hai.log;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * log4j test
 * notes: must use org.apache.log4j.*
 *
 * Created by Administrator on 2017/12/22.
 */
public class Log4jTest {

    Logger logger = Logger.getLogger(Log4jTest.class);
    String className = Log4jTest.class.getName();

    @Test
    public void test() {
        logger.trace(className + ".log4j.trace...");
        logger.debug(className + ".log4j.debug...");
        logger.info(className + ".log4j.info...");
        logger.warn(className + ".log4j.warn..");
        logger.error(className + ".log4j.error...");
        logger.fatal(className + ".log4j.fatal...");
    }
}
