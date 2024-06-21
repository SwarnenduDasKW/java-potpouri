package com.ssd.javapotpouri.controller;

import com.ssd.javapotpouri.builder.BaseCar;
import com.ssd.javapotpouri.builder.CarBuilder;
import com.ssd.javapotpouri.builder.Director;
import com.ssd.javapotpouri.builder.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodStudentController {

    @GetMapping("/good-student")
    public String getStudents() {
//        Student student = new Student.CarBuilder("John", "Nine")
//                .setFavSubject("Chemistry")
//                .setPlaysVolly("Yes")
//                .build();

        Student student = new Student.CarBuilder("Mary", "Nine")
                .setFavSubject("Chemistry")
                .build();

        String output = String.format(" Student Name: %s \n Student Standard: %s \n Student Favourite Subject: %s \n Student Plays Vollyball: %s"
                ,student.getName()
                ,student.getStandard()
                ,student.getFavSubject()
                ,student.getPlaysVolly()
        );

        return output;

    }
}
