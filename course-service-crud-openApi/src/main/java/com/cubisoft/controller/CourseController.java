package com.cubisoft.controller;

import com.cubisoft.dto.CourseRequestDTO;
import com.cubisoft.dto.CourseResponseDTO;
import com.cubisoft.dto.ServiceResponse;
import com.cubisoft.service.CourseService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
@Slf4j
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // ---------------------------------------------------------------------
    // CREATE COURSE
    // ---------------------------------------------------------------------
    @Operation(
            summary = "Create a new course",
            description = "Adds a new course to the system"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Course created successfully",
                    content = @Content(
                            schema = @Schema(implementation = CourseResponseDTO.class),
                            examples = @ExampleObject(
                                    name = "CreateCourseExample",
                                    value = "{\"courseName\":\"Java Basics\",\"coursePrice\":5000}"
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    @PostMapping
    public ResponseEntity<ServiceResponse<CourseResponseDTO>> addCourse(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Course data to create",
                    content = @Content(
                            schema = @Schema(implementation = CourseRequestDTO.class),
                            examples = @ExampleObject(
                                    value = "{\"courseName\":\"Java Spring Boot\",\"coursePrice\":4500}"
                            )
                    )
            )
            @RequestBody @Valid CourseRequestDTO courseRequestDTO) {

        log.info("Creating course: {}", courseRequestDTO);

        CourseResponseDTO courseResponseDTO = courseService.onboardNewCourse(courseRequestDTO);

        ServiceResponse<CourseResponseDTO> response =
                ServiceResponse.success(courseResponseDTO, "Course created successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ---------------------------------------------------------------------
    // GET ALL COURSES
    // ---------------------------------------------------------------------
    @Operation(
            summary = "Get all courses",
            description = "Fetches all available courses from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully fetched all courses"
    )
    @GetMapping
    public ResponseEntity<ServiceResponse<?>> findAllCourse() {
        log.info("Fetching all courses");

        ServiceResponse<?> response =
                ServiceResponse.success(courseService.viewAllCourse(), "All courses fetched successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ---------------------------------------------------------------------
    // GET COURSE BY ID (PathVariable)
    // ---------------------------------------------------------------------
    @Operation(
            summary = "Get course by ID",
            description = "Fetch a specific course using its ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Course found successfully",
                    content = @Content(schema = @Schema(implementation = CourseResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @GetMapping("/search/{courseId}")
    public ResponseEntity<ServiceResponse<?>> findCourse(
            @Parameter(description = "ID of the course to search")
            @PathVariable int courseId) {

        log.info("Searching course with ID {}", courseId);

        ServiceResponse<?> response =
                ServiceResponse.success(courseService.findByCourseId(courseId), "Course found successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ---------------------------------------------------------------------
    // GET COURSE BY ID (RequestParam)
    // ---------------------------------------------------------------------
    @Operation(
            summary = "Find course using request param",
            description = "Fetch course by passing ?courseId=1"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Course found successfully"
    )
    @GetMapping("/search")
    public ResponseEntity<ServiceResponse<?>> findCourseUsingRequestParam(
            @Parameter(description = "Course ID to search", required = true)
            @RequestParam Integer courseId) {

        log.info("Searching course using request param: {}", courseId);

        ServiceResponse<?> response =
                ServiceResponse.success(courseService.findByCourseId(courseId), "Course found successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ---------------------------------------------------------------------
    // DELETE COURSE (HIDDEN)
    // ---------------------------------------------------------------------
    @DeleteMapping("/{courseId}")
    @Hidden  // 🚫 Hides DELETE API in Swagger
    public ResponseEntity<ServiceResponse<?>> deleteCourse(@PathVariable int courseId) {
        log.warn("Deleting course ID {}", courseId);

        courseService.deleteCourse(courseId);

        ServiceResponse<?> response =
                ServiceResponse.success(null, "Course deleted successfully");

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    // ---------------------------------------------------------------------
    // UPDATE COURSE
    // ---------------------------------------------------------------------
    @Operation(
            summary = "Update course",
            description = "Updates course details using courseId"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Course updated successfully"
    )
    @PutMapping("/{courseId}")
    public ResponseEntity<ServiceResponse<?>> updateCourse(
            @Parameter(description = "Course ID to update", required = true)
            @PathVariable int courseId,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated course details",
                    content = @Content(
                            schema = @Schema(implementation = CourseRequestDTO.class),
                            examples = @ExampleObject(
                                    value = "{\"courseName\":\"Advanced Spring Boot\",\"coursePrice\":6500}"
                            )
                    )
            )
            @RequestBody CourseRequestDTO courseRequestDTO) {

        log.info("Updating course {} with {}", courseId, courseRequestDTO);

        ServiceResponse<?> response =
                ServiceResponse.success(courseService.updateCourse(courseId, courseRequestDTO),
                        "Course updated successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
