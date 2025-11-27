package com.cubisoft.controller;

import com.cubisoft.dto.Course;
import com.cubisoft.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //@RequestMapping(value = "/course", method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course){
       Course newCourse = courseService.onboardNewCourse(course);
       return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAllCourse(){
        return  new ResponseEntity<>(courseService.viewAllCourse(), HttpStatus.OK);
    }

    @GetMapping("/search/request/{courseId}")
    public  ResponseEntity<?> findCourse(@PathVariable  int courseId){
        return new ResponseEntity<>(courseService.findByCourseId(courseId), HttpStatus.OK);
    }

    @GetMapping("/search/request")
    public ResponseEntity<?> findCourseUsingRequestParam(@RequestParam(required =false) Integer courseId) {
        return new ResponseEntity<>(courseService.findByCourseId(courseId), HttpStatus.OK);
    }

    @DeleteMapping
    public  ResponseEntity<?> deleteCourse(@PathVariable int courseId){
        courseService.findByCourseId(courseId);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable int courseId, @RequestBody Course course){
        return new ResponseEntity<>(courseService.updateCourse(courseId, course), HttpStatus.OK);
    }
}
