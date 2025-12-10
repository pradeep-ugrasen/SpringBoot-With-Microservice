package com.cubisoft.dao;

import com.cubisoft.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO/Repository interface for Course Entity.
 * Provides CRUD and JPA query support for CourseEntity.
 */
@Repository
public interface CourseDao extends JpaRepository<CourseEntity, Integer> {

    // Custom query methods can be added here if needed
    // Example:
    // Optional<CourseEntity> findByCourseUniqueCode(String courseUniqueCode);
}
