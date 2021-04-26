package com.hai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * description
 *
 * @author hai
 * @date 2020/12/11 12:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    private int id;
    private String name;
    private int age;
}
