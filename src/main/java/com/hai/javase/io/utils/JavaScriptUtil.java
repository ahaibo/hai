package com.hai.javase.io.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by Administrator on 2018/4/11.
 */
public class JavaScriptUtil {

    private final static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

    public static Object cal(String expression) throws ScriptException {
        return jse.eval(expression);
    }
}
