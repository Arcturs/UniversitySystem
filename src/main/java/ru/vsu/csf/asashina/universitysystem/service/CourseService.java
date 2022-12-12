package ru.vsu.csf.asashina.universitysystem.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.csf.asashina.universitysystem.mapper.CourseMapper;
import ru.vsu.csf.asashina.universitysystem.model.CourseEntity;
import ru.vsu.csf.asashina.universitysystem.model.ResearchAssociateEntity;
import ru.vsu.csf.asashina.universitysystem.model.request.CourseRequest;
import ru.vsu.csf.asashina.universitysystem.repository.CourseRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {

    public static final String LECTURER = "LECTURER";

    private final CourseRepository repository;

    private final CourseMapper mapper;

    private final ResearchAssociateService researchAssociateService;

    public List<CourseEntity> getAllCourses() {
        return repository.findAll();
    }

    @SneakyThrows
    public CourseEntity findCourseById(Long id) {
        return repository.findById(id).orElseThrow(ClassNotFoundException::new);
    }

    @Transactional
    public void createCourse(CourseRequest request) {
        repository.save(mapper.toEntityFromRequest(request));
    }

    @SneakyThrows
    @Transactional
    public void enrollLecturer(Long id, Long lecturerId) {
        CourseEntity course = findCourseById(id);
        ResearchAssociateEntity researchAssociate = researchAssociateService.findById(lecturerId);
        if (course.getLecturers().contains(researchAssociate)) {
            throw new IllegalArgumentException("Following lecturer already enrolled on this course");
        }
        course.setLecturers(Set.of(researchAssociate));
        repository.save(course);
    }

    @SneakyThrows
    @Transactional
    public CourseEntity updateCourse(Long id, CourseRequest request, Authentication authentication) {
        CourseEntity course = findCourseById(id);
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        if (roles.contains(LECTURER)) {
            Long participantId = (Long) authentication.getPrincipal();
            ResearchAssociateEntity researchAssociate = researchAssociateService.findById(participantId);
            if (!course.getLecturers().contains(researchAssociate)) {
                throw new AccessDeniedException("Access denied");
            }
        }
        mapper.updateCourse(request, course);
        return repository.save(course);
    }

    @Transactional
    public void deleteCourse(Long id) {
        repository.delete(findCourseById(id));
    }

    @Transactional
    public void unsubscribeLecturer(Long id, Long lecturerId) {
        repository.unsubscribeLecturer(id, lecturerId);
    }
}
