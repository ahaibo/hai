package com.hai.javase.network;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

public class GetHttpHeaders {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.shouce.ren");
        URLConnection conn = url.openConnection();

        Map headers = conn.getHeaderFields();
        Set<String> keys = headers.keySet();
        for (String key : keys) {
            String val = conn.getHeaderField(key);
            System.out.println(key + "=" + val);
        }
        System.out.println(conn.getLastModified());

    }
} 