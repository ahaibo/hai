package com.hai.cache.ehcache;

import com.alibaba.fastjson.JSONObject;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2017/12/22.
 */
public class EHCacheTest {

    static final String EHCACHE_PATH = "./src/main/resources/cache/ehcache.xml";

    CacheManager cacheManager = null;

    @Before
    public void before() {
        System.out.println("cache manager create by ehcache resources: " + EHCACHE_PATH);
        cacheManager = CacheManager.create(EHCACHE_PATH);
        System.out.println("cache manager info:\n" + JSONObject.toJSONString(cacheManager, true));
    }

    @Test
    public void test() {
        Cache cache = cacheManager.getCache("order");//获取指定cache(ehcache.xml中配置)
        //System.out.println("cache order info:\n" + JSONObject.toJSONString(cache, true));

        addCacheElements(cache);

        Element val = cache.get("booking1");
        System.out.println("cache order element booking1 info:\n" + JSONObject.toJSONString(val, true));
        System.out.println("booking val: " + val.getObjectValue());

        System.out.println("cache flush...");
        cache.flush();//刷新缓存
    }

    private void addCacheElements(Cache cache) {
        for (int i = 1; i <= 100; i++) {
            cache.put(new Element("booking" + i, "val." + i));
        }
    }


    @After
    public void after() {
        if (null != cacheManager) {
            System.out.println("cache manager shutdown...");
            cacheManager.shutdown();//关闭缓存
        }
    }
}
