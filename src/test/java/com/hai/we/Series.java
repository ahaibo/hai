package com.hai.we;

import lombok.Data;

import java.util.Set;

/**
 * description
 *
 * @author hai
 * @date 2020/12/8 10:46
 */
@Data
public class Series {
    private long id;
    private String tag;
    private String name;
    private String icon;
    private String date;

    private Set<Play> plays;

    @Override
    public String toString() {
        return "Series{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

}
