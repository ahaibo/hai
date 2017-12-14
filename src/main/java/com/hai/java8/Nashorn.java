package com.hai.java8;

import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Random;

/**
 * Created by Administrator on 2017/9/8.
 * JavaScript引擎Nashorn测试
 */
public class Nashorn {

    @Test
    public void test() throws ScriptException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
        System.out.println(scriptEngine.getClass().getName());
        Random random = new Random();
        int num = random.nextInt(1000);
        System.out.println("num: " + num);
        Object result = scriptEngine.eval("function hello(name){return 'hello,'+name} hello(" + num + ")");
        System.out.println(result);
    }
}
