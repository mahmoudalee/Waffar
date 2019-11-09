package com.dell.waffar.model;

public class Courses {
    private String name;
    private int image;

    public Courses(String name, int image){
        this.name = name;
        this.image = image;
    }

    public String getName() {return name;}
    public int getImage() {return image;}
}
