package com.ssd.javapotpouri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class LocalizationController {
    @Autowired
    ResourceBundleMessageSource messageSource;

    @GetMapping("/message/welcome")
    public String getMessages() {
        return messageSource.getMessage("welcome",null, Locale.FRANCE);
    }
 }
