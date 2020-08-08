package com.example.registrytolessons.Model;

public class Course {
    private String courseName;
    private String courseLink;

    public Course(String courseName, String courseLink) {
        this.courseName = courseName;
        this.courseLink = courseLink;
    }
    public Course(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseLink() {
        return courseLink;
    }

    public void setCourseLink(String courseLink) {
        this.courseLink = courseLink;
    }
}

