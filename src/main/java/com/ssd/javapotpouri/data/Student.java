package com.ssd.javapotpouri.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    private String name;
    private Integer age;
    private String grade;
    private Address address;
    private Contact contact;
    private String comments;
}
