package com.nhatdo.whatisthis.bean;

import java.io.Serializable;

public class FlashCardDetails implements Serializable {

    private int id;
    private String name;
    private int code;
    private String description;
    private int imageId;
    private int audioId;
    private int idTopic;

    public FlashCardDetails(int id, String name, int code, String description, int imageId, int audioId, int idTopic) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.imageId = imageId;
        this.audioId = audioId;
        this.idTopic = idTopic;
    }

    public FlashCardDetails(String name, int code, String description, int imageId, int audioId, int idTopic) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.imageId = imageId;
        this.audioId = audioId;
        this.idTopic = idTopic;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getAudioId() {
        return audioId;
    }

    public void setAudioId(int audioId) {
        this.audioId = audioId;
    }

    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }
}
