package com.cubisoft.service;

import com.cubisoft.dao.CourseDao;
import com.cubisoft.dto.CourseRequestDTO;
import com.cubisoft.dto.CourseResponseDTO;
import com.cubisoft.entity.CourseEntity;
import com.cubisoft.util.AppUtils;
import exception.CourseServiceBusinessException;
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
     */
    public CourseResponseDTO onboardNewCourse(CourseRequestDTO courseRequestDTO) {

        try {
            // Convert DTO to Entity
            CourseEntity courseEntity = AppUtils.mapDTOToEntity(courseRequestDTO);

            // Save entity into DB
            CourseEntity savedEntity = courseDao.save(courseEntity);

            // Convert Entity to Response DTO
            CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDTO(savedEntity);

            // Generate a unique course identifier
            courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);

            return courseResponseDTO;

        } catch (Exception ex) {
            throw new CourseServiceBusinessException("Failed to onboard new course: " + ex.getMessage());
        }
    }

    /**
     * Fetches all the courses stored in the database.
     */
    public List<CourseResponseDTO> viewAllCourse() {
        try {
            Iterable<CourseEntity> courseEntities = courseDao.findAll();

            return StreamSupport.stream(courseEntities.spliterator(), false)
                    .map(AppUtils::mapEntityToDTO)
                    .collect(Collectors.toList());

        } catch (Exception ex) {
            throw new CourseServiceBusinessException("Failed to fetch course list: " + ex.getMessage());
        }
    }

    /**
     * Fetches a course by its courseId.
     */
    public CourseResponseDTO findByCourseId(Integer courseId) {

        CourseEntity courseEntity = courseDao.findById(courseId)
                .orElseThrow(() ->
                        new CourseServiceBusinessException("Course ID " + courseId + " does not exist"));

        return AppUtils.mapEntityToDTO(courseEntity);
    }

    /**
     * Deletes a course from the database using courseId.
     */
    public void deleteCourse(int courseId) {
        try {
            if (!courseDao.existsById(courseId)) {
                throw new CourseServiceBusinessException("Cannot delete course. Course ID " + courseId + " does not exist");
            }
            courseDao.deleteById(courseId);

        } catch (Exception ex) {
            throw new CourseServiceBusinessException("Failed to delete course: " + ex.getMessage());
        }
    }

    /**
     * Updates an existing course with the provided data.
     */
    public CourseResponseDTO updateCourse(int courseId, CourseRequestDTO courseRequestDTO) {

        try {
            CourseEntity existingCourseEntity = courseDao.findById(courseId)
                    .orElseThrow(() ->
                            new CourseServiceBusinessException("Course ID " + courseId + " is not valid"));

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

        } catch (Exception ex) {
            throw new CourseServiceBusinessException("Failed to update course: " + ex.getMessage());
        }
    }
}
