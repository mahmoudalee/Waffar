package com.dell.waffar.model;

public class CoursesBuilder {

    private String name;
    private int image;

    public CoursesBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public CoursesBuilder setImage(int image){
        this.image = image;
        return this;
    }

    //Build a Rooms Contents and return it
    public Courses addCource(){
        return new Courses(name, image);
    }

}
