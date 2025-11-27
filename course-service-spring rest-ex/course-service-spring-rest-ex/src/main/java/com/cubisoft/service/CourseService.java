package com.cubisoft.service;

import com.cubisoft.dto.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CourseService {

    private List<Course> courseDatabase = new ArrayList<>();

    //create course object in DB
    public  Course onboardNewCourse(Course course){
        course.setCourseId(new Random().nextInt(1024));
        courseDatabase.add(course);
        return course;
    }

    //load all the course from database
    public  List<Course> viewAllCourse() {
        return courseDatabase;
    }

    //Filter course by courseId
    public  Course findByCourseId(Integer courseId) {
        return courseDatabase.stream().filter(course -> course.getCourseId() == courseId).findFirst().orElse(null);
    }

    //delete course from database
    public  void deleteCourse(int courseId){
        Course course = findByCourseId(courseId);
        courseDatabase.remove(course);
    }

    //update the course
    public  Course updateCourse(int courseId, Course course){
        Course existingCourse = findByCourseId(courseId);
        courseDatabase.set(courseDatabase.indexOf(existingCourse),course); // Not suggested
        return course;
    }
}
