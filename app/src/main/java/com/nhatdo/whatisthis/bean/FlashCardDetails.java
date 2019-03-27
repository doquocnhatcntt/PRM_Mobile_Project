package com.nhatdo.whatisthis.bean;

import java.io.Serializable;

public class FlashCardDetails implements Serializable {

    private int id;
    private int idTopic;
    private String name;
    private int code;
    private String description;
    private int imageId;
    private int audioId;
    private int pronounceAudioId;

    public FlashCardDetails(int idTopic, String name, int code, String description, int imageId, int audioId, int pronounceAudioId) {
        this.idTopic = idTopic;
        this.name = name;
        this.code = code;
        this.description = description;
        this.imageId = imageId;
        this.audioId = audioId;
        this.pronounceAudioId = pronounceAudioId;
    }

    public FlashCardDetails(int id, int idTopic, String name, int code, String description, int imageId, int audioId, int pronounceAudioId) {
        this.id = id;
        this.idTopic = idTopic;
        this.name = name;
        this.code = code;
        this.description = description;
        this.imageId = imageId;
        this.audioId = audioId;
        this.pronounceAudioId = pronounceAudioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
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

    public int getPronounceAudioId() {
        return pronounceAudioId;
    }

    public void setPronounceAudioId(int pronounceAudioId) {
        this.pronounceAudioId = pronounceAudioId;
    }
}
