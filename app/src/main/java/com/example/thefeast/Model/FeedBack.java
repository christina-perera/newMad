package com.example.thefeast.Model;

public class FeedBack {

    String title;
    String Description;
    String id;

    public FeedBack(String title, String description, String id) {
        this.title = title;
        Description = description;
        this.id = id;
    }

    public FeedBack() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
