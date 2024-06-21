package com.ssd.javapotpouri.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class AddressValidate {
    @Size(min = 3, max = 30)
    @JsonProperty("address_line_1")
    private String addressLine1;

    private String addressLine2;
    private String city;
    private String province;
    private String postalCode;

}
