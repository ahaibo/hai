package com.hai.common.util;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SqlUtil {

    public static String genInString(String filePath, String quotation) {
        File file = new File(filePath);
        StringBuilder sql = new StringBuilder();
        if (file.exists()) {
            BufferedInputStream bis = null;
            FileReader fileReader;
            BufferedReader bufferedReader = null;
            try {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                Set set = new HashSet();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    //sql.append(quotation).append(line).append(quotation).append(",");
                    set.add(line);
                }
                if (set.size() > 0) {
                    Iterator iterator = set.iterator();
                    while (iterator.hasNext()) {
                        sql.append(quotation).append(iterator.next()).append(quotation).append(",");
                    }
                }
                return sql.length() > 1 ? sql.substring(0, sql.length() - 1) : sql.toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != bufferedReader) {
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(genInString("C:\\Users\\Administrator\\Documents\\ordersn.txt", "'"));
    }
}
