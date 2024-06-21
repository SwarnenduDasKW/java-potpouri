package com.ssd.javapotpouri.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contact {
    private String email;
    private String phone;
    private String notes;
}

