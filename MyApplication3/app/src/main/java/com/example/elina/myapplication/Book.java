package com.example.elina.myapplication;

public class Book {
    private String name;
    private String description;
    private int photo;

    public Book(String name, String description, int photo) {
        this.name = name;
        this.description = description;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPhoto() {
        return photo;
    }
}
