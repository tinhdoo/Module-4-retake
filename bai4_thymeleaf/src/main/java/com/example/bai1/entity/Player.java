package com.example.bai1.entity;

public class Player {
    private String code;
    private String name;
    private String dob;
    private String experience;
    private String position;
    private String image;

    public Player() {
    }

    public Player(String code, String name, String dob, String experience, String position, String image) {
        this.code = code;
        this.name = name;
        this.dob = dob;
        this.experience = experience;
        this.position = position;
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
