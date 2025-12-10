package com.cubisoft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO used specifically for course creation/update requests.
 * Extends BaseCourseDTO to include additional request-specific fields.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDTO extends BaseCourseDTO {

    /** Unique identifier of the course (used for update operations) */
    private int courseId;
}
