package com.api2.bookstore.dtos;


import javax.validation.constraints.NotBlank;

public class PublisherDto {

    @NotBlank
    private String name;

    @NotBlank
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
