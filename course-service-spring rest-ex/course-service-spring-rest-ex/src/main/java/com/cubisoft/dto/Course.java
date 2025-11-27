package com.cubisoft.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private int courseId;
    private String name;
    private String trainerName;
    private String duration; //days

    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern ="dd-MM-yyyy")
    private String startDate;

    private String courseType;
    private double fees;
    private boolean isCertificateAvailable;
}
