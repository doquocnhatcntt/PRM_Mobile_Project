package com.nhatdo.whatisthis.bean;

import java.io.Serializable;

public class Topics implements Serializable {

    private int id;
    private String name;
    private int registrationNumber;
    private String description;
    private int topicBackground;

    public Topics(String name, int registrationNumber, String description, int topicBackground) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.description = description;
        this.topicBackground = topicBackground;
    }

    public Topics(int id, String name, int registrationNumber, String description, int topicBackground) {
        this.id = id;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.description = description;
        this.topicBackground = topicBackground;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTopicBackground() {
        return topicBackground;
    }

    public void setTopicBackground(int topicBackground) {
        this.topicBackground = topicBackground;
    }
}
