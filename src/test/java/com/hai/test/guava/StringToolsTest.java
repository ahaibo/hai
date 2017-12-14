package com.hai.test.guava;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/29.
 */
public class StringToolsTest {

    @Test
    public void joiner() {
        Joiner joiner = Joiner.on(",");
        String str = joiner.skipNulls().join("嘿", "guava is good!");
        System.out.println(str);

        Map<String, String> map = new HashMap() {
            {
                put("张三", "员工");
                put("李四", "老板");
            }
        };

        System.out.println(Joiner.on("\r\n").withKeyValueSeparator(" 是 ").join(map));
    }
}
