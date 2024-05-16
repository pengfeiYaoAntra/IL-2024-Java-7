package org.example;

import java.util.Optional;

public class Student{
    private String firstName;
    private String lastName;
    private Optional<String> phoneNumber;
    public Student(String firstName, String lastName, Optional<String> phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    //getters and setters
    public Optional<String> getPhoneNumber(){
        return phoneNumber;
    }
}