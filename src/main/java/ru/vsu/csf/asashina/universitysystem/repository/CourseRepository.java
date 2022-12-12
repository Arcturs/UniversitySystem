package ru.vsu.csf.asashina.universitysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vsu.csf.asashina.universitysystem.model.CourseEntity;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    @Modifying
    @Query(value = "DELETE FROM lectures WHERE course_id = :courseId AND social_security_number = :lecturerId",
            nativeQuery = true)
    void unsubscribeLecturer(@Param("courseId") Long courseId,
                             @Param("lecturerId") Long lecturerId);
}
