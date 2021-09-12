package com.example.mobileappcska.data;

public class User {

    private String email;
    private String age;
    private String name;
    private String role;


    public User(){
    }

    public User(String email, String age, String name, String role) {
        this.email = email;
        this.age = age;
        this.name = name;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
