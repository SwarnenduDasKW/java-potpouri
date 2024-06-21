package com.ssd.javapotpouri.builder;


public class Student {
    // Required parameters
    private final String name;
    private final String standard;

    // Optional parameters
    private final String favSubject;
    private final String playsVolly;

    private Student(CarBuilder builder) {
        this.name = builder.name;
        this.standard = builder.standard;
        this.favSubject = builder.favSubject;
        this.playsVolly = builder.playsVolly;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getStandard() {
        return standard;
    }

    public String getFavSubject() {
        return favSubject;
    }

    public String getPlaysVolly() {
        return playsVolly;
    }

    // Step 2: Create the Builder Class
    public static class CarBuilder {
        // Required parameters
        private final String name;
        private final String standard;

        // Optional parameters
        private String favSubject;
        private String playsVolly;

        public CarBuilder(String name, String standard) {
            this.name = name;
            this.standard = standard;
        }

        public CarBuilder setFavSubject(String favSubject) {
            this.favSubject = favSubject;
            return this;
        }

        public CarBuilder setPlaysVolly(String playsVolly) {
            this.playsVolly = playsVolly;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
