package com.cubisoft.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseCourseDTO {

    private String name;
    private String trainerName;
    private String duration;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private String startDate;
    private String courseType;
    private Double fees;
    private boolean certificateAvailable;
    private String description;
}
