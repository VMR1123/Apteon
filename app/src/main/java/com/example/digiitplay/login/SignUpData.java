package com.example.digiitplay.login;

public class SignUpData {
    String email, username, password, age;

    public SignUpData() {
    }

    public SignUpData(String email, String username, String password, String age) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
