package com.dell.waffar.model;

import com.dell.waffar.R;

import java.util.ArrayList;

public class HomeCourses {

    // this class is responsible for filling the data in home Page
    ArrayList<Courses> courses = new ArrayList<Courses>();
    public ArrayList<Courses> CreateCourses(){
        courses.add(new CoursesBuilder().setName("Room 1").setImage(R.drawable.room).addCource());
        courses.add(new CoursesBuilder().setName("Room 2").setImage(R.drawable.room).addCource());
        courses.add(new CoursesBuilder().setName("Room 3").setImage(R.drawable.room).addCource());
        courses.add(new CoursesBuilder().setName("Room 4").setImage(R.drawable.room).addCource());
        courses.add(new CoursesBuilder().setName("Room 5").setImage(R.drawable.room).addCource());
        courses.add(new CoursesBuilder().setName("Room 6").setImage(R.drawable.room).addCource());
        courses.add(new CoursesBuilder().setName("Room 7").setImage(R.drawable.room).addCource());
        return courses;
    }
}
