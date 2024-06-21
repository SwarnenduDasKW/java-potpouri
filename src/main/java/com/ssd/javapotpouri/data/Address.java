package com.ssd.javapotpouri.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String province;
    private String postalCode;

}
