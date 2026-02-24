package com.saimiral.usermanagement.dto;

public class UserResponseDTO {

    private Long id;
    private String name;
    private int age;
    private String email;

    public UserResponseDTO(Long id, String name, int age, String email){
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() { return email; }
}
