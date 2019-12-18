package com.hai;

import java.io.File;

public class LhtkSql {

    public static void main(String[] args) {
        String path = "C:\\Users\\Administrator\\Downloads\\Telegram Desktop\\aacc2";
        File file = new File(path);
        File[] files = file.listFiles();
        String createTime = "2019-08-29 15:12:27";
//        StringBuilder sql = new StringBuilder("insert into lhc_photo_category(name,parent_id,sort,create_time,deleted) values");
        StringBuilder sql = new StringBuilder();
        for (File f : files) {
            String fileName = f.getName();
            System.out.println(fileName);
//            sql.append("('").append(fileName).append("',0,0,'").append(createTime).append("',0").append(")");
            sql.append(fileName).append(",");
        }
        System.out.println(sql);
    }
}
