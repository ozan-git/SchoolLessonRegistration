package com.example.registrytolessons.Model;

public class Section {
    private String sectionCode;
    private String sectionType;
    private String sectionId;
    private String professorName;
    private String professorLink;

    public Section(String sectionCode, String sectionType, String sectionId, String professorName, String professorLink) {
        this.sectionCode = sectionCode;
        this.sectionType = sectionType;
        this.sectionId = sectionId;
        this.professorName = professorName;
        this.professorLink = professorLink;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getProfessorLink() {
        return professorLink;
    }

    public void setProfessorLink(String professorLink) {
        this.professorLink = professorLink;
    }
}
