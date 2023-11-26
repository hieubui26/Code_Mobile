package com.mad.g1.bui_minh_hieu.code_mobile.Model;
import java.io.Serializable;

public class Matches implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private String date;
    private String level;
    private Boolean status;

    public Matches() {
    }

    public Matches(Integer id, String name, String description, String date, String level, Boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.level =level;
        this.status = status;

    }

    public Matches(String name, String description, String date, String level, Boolean status) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.level =level;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}