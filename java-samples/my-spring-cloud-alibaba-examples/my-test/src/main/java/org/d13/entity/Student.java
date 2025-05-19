package org.d13.entity;

import lombok.Data;

/**
 * 这是为了测试注解：@Import的
 */
@Data
public class Student {
    private Long id;
    private String name;
    private Integer age;
}