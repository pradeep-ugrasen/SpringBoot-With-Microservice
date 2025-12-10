package com.cubisoft.dto;

import com.cubisoft.annotation.CourseTypeValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseCourseDTO {

    @NotBlank(message = "Course name cannot be empty")
    private String name;

    @NotBlank(message = "Trainer name cannot be empty")
    private String trainerName;

    @NotBlank(message = "Course duration cannot be empty")
    private String duration;

    @NotBlank(message = "Start date cannot be empty")
    @JsonFormat(pattern = "dd-MM-yyyy")
    //@Past(message = "Start date must be a past date")
    private String startDate;

    @CourseTypeValidation
    @NotBlank(message = "Course type cannot be empty")
    private String courseType;

    @NotNull(message = "Fees cannot be null")
    @Positive(message = "Fees must be greater than 0")
    private Double fees;

    private boolean certificateAvailable;

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 10, max = 300, message = "Description must be between 10 and 300 characters")
    private String description;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Phone number must be 10 digits and start with 6, 7, 8, or 9"
    )
    private String phone;
}
