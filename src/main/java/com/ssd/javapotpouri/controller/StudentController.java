package com.ssd.javapotpouri.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssd.javapotpouri.completablefuture.CompletableFutureDemo;
import com.ssd.javapotpouri.config.StudentRequest;
import com.ssd.javapotpouri.config.StudentValidate;
import com.ssd.javapotpouri.data.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class StudentController {

    @Autowired
    CompletableFutureDemo completableFutureDemo;

    @GetMapping("/students")
    public String getStudentsJson() {
        try {
            return completableFutureDemo.demo();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/validate")
    public String validate(@Valid @RequestBody StudentValidate studentRequest) {
        return completableFutureDemo.validate(studentRequest );
    }
}
