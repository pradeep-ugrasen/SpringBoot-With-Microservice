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
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName(dto.getName());
        courseEntity.setTrainerName(dto.getTrainerName());
        courseEntity.setDuration(dto.getDuration());
        courseEntity.setStartDate(dto.getStartDate());
        courseEntity.setCourseType(dto.getCourseType());
        courseEntity.setFees(dto.getFees());
        courseEntity.setCertificateAvailable(dto.isCertificateAvailable());
        courseEntity.setDescription(dto.getDescription());
        courseEntity.setEmail(dto.getEmail());
        courseEntity.setPhone(dto.getPhone());
        return courseEntity;
    }

    /**
     * Convert CourseEntity to CourseResponseDTO
     */
    public static CourseResponseDTO mapEntityToDTO(CourseEntity entity) {
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setCourseId(entity.getCourseId());
        courseResponseDTO.setName(entity.getName());
        courseResponseDTO.setTrainerName(entity.getTrainerName());
        courseResponseDTO.setDuration(entity.getDuration());
        courseResponseDTO.setStartDate(entity.getStartDate());
        courseResponseDTO.setCourseType(entity.getCourseType());
        courseResponseDTO.setFees(entity.getFees());
        courseResponseDTO.setCertificateAvailable(entity.isCertificateAvailable());
        courseResponseDTO.setDescription(entity.getDescription());
        courseResponseDTO.setEmail(entity.getEmail());
        courseResponseDTO.setPhone(entity.getPhone());
        return courseResponseDTO;
    }
}
