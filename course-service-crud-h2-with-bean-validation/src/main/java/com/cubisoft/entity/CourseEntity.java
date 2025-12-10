package com.cubisoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entity class that represents the Course table in the database.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int courseId;

    @Column(nullable = false)
    private String name;

    @Column(name = "trainer_name", nullable = false)
    private String trainerName;

    private String duration;

    @Column(name = "start_date")
    private String startDate;  // Better than String for date handling

    @Column(name = "course_type")
    private String courseType;

    private double fees;

    @Column(name = "is_certificate_available")
    private boolean certificateAvailable;

    @Column(length = 1000)
    private String description;

    private String email;

    private String phone;
}
