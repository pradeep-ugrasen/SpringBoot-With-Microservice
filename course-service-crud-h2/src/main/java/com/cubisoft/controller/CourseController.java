package com.cubisoft.controller;

import com.cubisoft.dto.CourseRequestDTO;
import com.cubisoft.dto.CourseResponseDTO;
import com.cubisoft.dto.ServiceResponse;
import com.cubisoft.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Add / create a new course.
     */
    @PostMapping
    public ResponseEntity<ServiceResponse<CourseResponseDTO>> addCourse(@RequestBody CourseRequestDTO courseRequestDTO) {
        CourseResponseDTO courseResponseDTO = courseService.onboardNewCourse(courseRequestDTO);
        ServiceResponse<CourseResponseDTO> response =
                ServiceResponse.success(courseResponseDTO, "Course created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Get all courses available in the system.
     */
    @GetMapping
    public ResponseEntity<ServiceResponse<?>> findAllCourse() {
        ServiceResponse<?> response =
                ServiceResponse.success(courseService.viewAllCourse(), "All courses fetched successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get course by courseId using path variable.
     */
    @GetMapping("/search/{courseId}")
    public ResponseEntity<ServiceResponse<?>> findCourse(@PathVariable int courseId) {
        ServiceResponse<?> response =
                ServiceResponse.success(courseService.findByCourseId(courseId), "Course found successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get course by courseId using request param.
     * Example: /course/search?courseId=1
     */
    @GetMapping("/search")
    public ResponseEntity<ServiceResponse<?>> findCourseUsingRequestParam(@RequestParam Integer courseId) {
        ServiceResponse<?> response =
                ServiceResponse.success(courseService.findByCourseId(courseId), "Course found successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Delete course by courseId.
     */
    @DeleteMapping("/{courseId}")
    public ResponseEntity<ServiceResponse<?>> deleteCourse(@PathVariable int courseId) {
        courseService.deleteCourse(courseId);
        ServiceResponse<?> response =
                ServiceResponse.success(null, "Course deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    /**
     * Update an existing course.
     */
    @PutMapping("/{courseId}")
    public ResponseEntity<ServiceResponse<?>> updateCourse(@PathVariable int courseId,
                                                           @RequestBody CourseRequestDTO courseRequestDTO) {
        ServiceResponse<?> response =
                ServiceResponse.success(courseService.updateCourse(courseId, courseRequestDTO),
                        "Course updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
