package com.cubisoft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO for returning course details to the client.
 * Extends BaseCourseDTO to include additional information required in responses.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDTO extends BaseCourseDTO {

    /** Unique identifier of the course */
    private int courseId;

    /**
     * Unique system-generated course code used for external display or reference.
     * Example: "JAVA-101", "DSA2024", etc.
     */
    private String courseUniqueCode;
}
