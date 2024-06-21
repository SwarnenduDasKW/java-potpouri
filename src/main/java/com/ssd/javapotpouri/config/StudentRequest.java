package com.ssd.javapotpouri.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssd.javapotpouri.data.Address;
import com.ssd.javapotpouri.data.Contact;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class StudentRequest {
    @Size(min = 3, max = 10)
    @JsonProperty("student_name")
    private String name;

    @NumberFormat
    @JsonProperty("student_age")
    private Integer age;

    @Size(min = 3, max = 5)
    @JsonProperty("student_grade")
    private String grade;

    @Valid
    private AddressValidate address;

    private Contact contact;
    private String c1omments;
}
