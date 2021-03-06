package com.hai.javase.util.httpclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHander {

    public static Properties properties = null;

    public static String getPropertiesValue(String key) {
        try {
            if (properties == null) {
                properties = new Properties();
                String path = PropertiesHander.class.getResource("/job_task.properties").getFile();
                FileInputStream in = new FileInputStream(new File(path));
                properties.load(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

}
//job_task.properties
//maxTotal=1000
//defaultMaxPerRoute= 32
//Http_Default_Timeout= 15000
//Http_Default_Keep_Time= 15000