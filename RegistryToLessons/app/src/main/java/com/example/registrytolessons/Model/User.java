package com.example.registrytolessons.Model;

public class User {
    private String uid;
    private String name;
    private String surname;
    private String email;
    private String type;
    private String section;

    private int grade;
    private int term;


    public User(String uid, String name, String surname, String email, String type, String section, int grade, int term) {
        this.uid = uid;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.type = type;
        this.section = section;
        this.grade = grade;
        this.term = term;
    }

    public User(String uid, String name, String surname, String email, String type) {
        this.uid = uid;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

}

