package ru.vsu.csf.asashina.universitysystem.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.csf.asashina.universitysystem.model.request.FacultyRequest;
import ru.vsu.csf.asashina.universitysystem.service.FacultyService;

@RestController
@RequestMapping("/faculty")
@AllArgsConstructor
public class FacultyController {

    private final FacultyService service;

    @GetMapping
    public ResponseEntity<?> getAllFaculties() {
        return ResponseEntity.ok(service.getAllFaculties());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFacultyById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getFacultyById(id));
    }

    @GetMapping("/{id}/institutes")
    public ResponseEntity<?> getAllInstitutesInFaculty(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getAllInstitutesInFaculty(id));
    }

    @PostMapping
    public ResponseEntity<?> createFaculty(@RequestBody @Valid FacultyRequest request) {
        service.createFaculty(request);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/{id}/dean/{researcherId}")
    public ResponseEntity<?> enrollResearcherAsDean(@PathVariable("id") Long id,
                                                    @PathVariable("researcherId") Long researcherId) {
        service.createDean(id, researcherId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFaculty(@PathVariable("id") Long id,
                                           @RequestBody @Valid FacultyRequest request) {
        return ResponseEntity.ok(service.updateFaculty(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFaculty(@PathVariable("id") Long id) {
        service.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }
}
