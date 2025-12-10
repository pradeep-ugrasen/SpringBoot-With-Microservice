package com.cubisoft.service;

import com.cubisoft.dao.CourseDao;
import com.cubisoft.dto.CourseRequestDTO;
import com.cubisoft.dto.CourseResponseDTO;
import com.cubisoft.entity.CourseEntity;
import com.cubisoft.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class CourseService {

    private final CourseDao courseDao;

    /**
     * Creates a new course and stores it in the database.
     * @param courseRequestDTO request payload for creating a course
     * @return saved CourseResponseDTO with generated unique code
     */
    public CourseResponseDTO onboardNewCourse(CourseRequestDTO courseRequestDTO) {

        // Convert DTO to Entity
        CourseEntity courseEntity = AppUtils.mapDTOToEntity(courseRequestDTO);

        // Save entity into DB
        CourseEntity savedEntity = courseDao.save(courseEntity);

        // Convert Entity to Response DTO
        CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDTO(savedEntity);

        // Generate a unique course identifier
        courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);

        return courseResponseDTO;
    }

    /**
     * Fetches all the courses stored in the database.
     * @return list of CourseResponseDTO
     */
    public List<CourseResponseDTO> viewAllCourse() {
        Iterable<CourseEntity> courseEntities = courseDao.findAll();

        return StreamSupport.stream(courseEntities.spliterator(), false)
                .map(AppUtils::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Fetches a course by its courseId.
     * @param courseId ID of the course
     * @return CourseResponseDTO if exists, otherwise throws exception
     */
    public CourseResponseDTO findByCourseId(Integer courseId) {
        CourseEntity courseEntity = courseDao.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course ID " + courseId + " is not valid"));

        return AppUtils.mapEntityToDTO(courseEntity);
    }

    /**
     * Deletes a course from the database using courseId.
     * @param courseId ID of the course to delete
     */
    public void deleteCourse(int courseId) {
        courseDao.deleteById(courseId);
    }

    /**
     * Updates an existing course with the provided data.
     * @param courseId ID of the course to update
     * @param courseRequestDTO updated course payload
     * @return updated CourseResponseDTO
     */
    public CourseResponseDTO updateCourse(int courseId, CourseRequestDTO courseRequestDTO) {
        CourseEntity existingCourseEntity = courseDao.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course ID " + courseId + " is not valid"));

        existingCourseEntity.setName(courseRequestDTO.getName());
        existingCourseEntity.setTrainerName(courseRequestDTO.getTrainerName());
        existingCourseEntity.setDuration(courseRequestDTO.getDuration());
        existingCourseEntity.setStartDate(courseRequestDTO.getStartDate());
        existingCourseEntity.setCourseType(courseRequestDTO.getCourseType());
        existingCourseEntity.setFees(courseRequestDTO.getFees());
        existingCourseEntity.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
        existingCourseEntity.setDescription(courseRequestDTO.getDescription());

        CourseEntity updatedCourseEntity = courseDao.save(existingCourseEntity);
        return AppUtils.mapEntityToDTO(updatedCourseEntity);
    }
}
