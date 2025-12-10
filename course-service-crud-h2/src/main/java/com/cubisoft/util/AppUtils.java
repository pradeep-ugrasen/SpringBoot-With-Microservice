package com.cubisoft.util;

import com.cubisoft.dto.CourseRequestDTO;
import com.cubisoft.dto.CourseResponseDTO;
import com.cubisoft.entity.CourseEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppUtils {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Convert CourseRequestDTO to CourseEntity
     */
    public static CourseEntity mapDTOToEntity(CourseRequestDTO dto) {
        CourseEntity entity = new CourseEntity();
        entity.setCourseId(dto.getCourseId());
        entity.setName(dto.getName());
        entity.setTrainerName(dto.getTrainerName());
        entity.setDuration(dto.getDuration());
        entity.setStartDate(dto.getStartDate());
        entity.setCourseType(dto.getCourseType());
        entity.setFees(dto.getFees());
        entity.setCertificateAvailable(dto.isCertificateAvailable());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    /**
     * Convert CourseEntity to CourseResponseDTO
     */
    public static CourseResponseDTO mapEntityToDTO(CourseEntity entity) {
        CourseResponseDTO dto = new CourseResponseDTO();
        dto.setCourseId(entity.getCourseId());
        dto.setName(entity.getName());
        dto.setTrainerName(entity.getTrainerName());
        dto.setDuration(entity.getDuration());
        dto.setStartDate(entity.getStartDate());
        dto.setCourseType(entity.getCourseType());
        dto.setFees(entity.getFees());
        dto.setCertificateAvailable(entity.isCertificateAvailable());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}
