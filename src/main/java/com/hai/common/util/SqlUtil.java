package com.hai.common.util;

import java.io.*;

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
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sql.append(quotation).append(line).append(quotation).append(",");
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

}
