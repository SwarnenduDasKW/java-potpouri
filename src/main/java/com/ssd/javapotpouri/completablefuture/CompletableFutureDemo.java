package com.ssd.javapotpouri.completablefuture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ssd.javapotpouri.config.StudentRequest;
import com.ssd.javapotpouri.config.StudentValidate;
import com.ssd.javapotpouri.data.Address;
import com.ssd.javapotpouri.data.Contact;
import com.ssd.javapotpouri.data.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CompletableFutureDemo {

    public String demo() throws ExecutionException, InterruptedException, JsonProcessingException {
        System.out.println("Main - " + Thread.currentThread());

        long startTime = System.currentTimeMillis();
        List<Student> students = new ArrayList<>();

        for(int i=1; i<= 5; i++) {
            Student student = new Student();
            int finalI = i;
            CompletableFuture<String> getStudentFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    getStudent(student, finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return null;
            });

            int finalI1 = i;
            CompletableFuture<String> getAddressFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    getAddress(student, finalI1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return null;
            });

            int finalI2 = i;
            CompletableFuture<String> getContactFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    getContact(student, finalI2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return null;
            });

//        System.out.println(future3.get());
//        System.out.println(getStudent.get());
//        System.out.println(getAddress.get());

            //Combining all futures
//            CompletableFuture<Void> combineFutures = CompletableFuture.allOf(future1, future2, future3);
//            combineFutures.get();
            students.add(student);
            System.out.println(String.format("-------------------- End of student %s ------------------------",i));

            String combined = Stream.of(getStudentFuture,getAddressFuture,getContactFuture)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" || "));

        }
        //Thread.sleep(30000);
        String studentsJson = object2Json(students);
//        System.out.println("Student List:- \n"+ studentsJson);

        long endTime = System.currentTimeMillis();
        System.out.println("Time Taken - " + (endTime - startTime));
//        System.out.println(combined);
        return studentsJson;

    }

    public void getStudent(Student student, int i) throws InterruptedException {
        System.out.println("getStudent - " + i + " - " + Thread.currentThread());
        Thread.sleep(2000);
        student.setName("John Doe - " + i);
        student.setAge(i+10);
        student.setGrade("Ten");
        student.setComments("getStudent - " + Thread.currentThread());
    }

    public void getAddress(Student student, int i) throws InterruptedException {
        System.out.println("getAddress - " + i + " - "  + Thread.currentThread());
        Thread.sleep(4000);
        Address address = new Address();
        address.setAddressLine1(i + " Main St");
        address.setAddressLine2("getAddress - " + Thread.currentThread());
        address.setCity("Kitchener");
        address.setProvince("ON");
        address.setPostalCode("N2E 0B4");
        student.setAddress(address);
    }

    public void getContact(Student student, int i) throws InterruptedException {
        System.out.println("getContact - " + i + " - "  + Thread.currentThread());
        Thread.sleep(500);
        Contact contact  = new Contact();
        contact.setEmail(String.format("john%s.doe%s@gmail.com",i,i));
        contact.setPhone("123-456-7890");
        contact.setNotes("getContact - " + Thread.currentThread());
        student.setContact(contact);
    }

    private String object2Json(Object object) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }

    public String validate(StudentValidate studentRequest) {
        return "Success";
    }

}
