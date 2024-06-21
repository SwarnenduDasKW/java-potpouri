package com.ssd.javapotpouri.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentValidate {
    @Valid
    @JsonProperty("request_params")
    private StudentRequest studentRequest;
}
