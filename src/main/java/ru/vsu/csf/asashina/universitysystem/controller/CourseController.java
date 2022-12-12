package ru.vsu.csf.asashina.universitysystem.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.vsu.csf.asashina.universitysystem.model.request.CourseRequest;
import ru.vsu.csf.asashina.universitysystem.service.CourseService;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
public class CourseController {

    private final CourseService service;

    @GetMapping
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(service.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findCourseById(id));
    }

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody @Valid CourseRequest request) {
        service.createCourse(request);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/{id}/enroll/{lecturerId}")
    public ResponseEntity<?> enrollLecturerToCourse(@PathVariable("id") Long id,
                                                    @PathVariable("lecturerId") Long lecturerId) {
        service.enrollLecturer(id, lecturerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable("id") Long id,
                                          @RequestBody @Valid CourseRequest request,
                                          Authentication authentication) {
        return ResponseEntity.ok().body(service.updateCourse(id, request, authentication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Long id) {
        service.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/unsubscribe/{lecturerId}")
    public ResponseEntity<?> unsubscribeLecturer(@PathVariable("id") Long id,
                                                 @PathVariable("lecturerId") Long lecturerId) {
        service.unsubscribeLecturer(id, lecturerId);
        return ResponseEntity.noContent().build();
    }
}
