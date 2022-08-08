package com.api2.bookstore.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClientDto {

    @NotBlank
    private String name;

    @NotNull
    private int age;

    @NotBlank
    private String email;

    @NotBlank
    private String city;

    @NotBlank
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
