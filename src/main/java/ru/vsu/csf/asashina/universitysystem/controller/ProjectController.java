package ru.vsu.csf.asashina.universitysystem.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.vsu.csf.asashina.universitysystem.model.request.ParticipationRequest;
import ru.vsu.csf.asashina.universitysystem.model.request.ProjectRequest;
import ru.vsu.csf.asashina.universitysystem.service.ProjectService;

@RestController
@RequestMapping("/project")
@AllArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @GetMapping
    public ResponseEntity<?> getAllProjects(Authentication authentication) {
        return ResponseEntity.ok(service.getProjects(authentication));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable("id") Long id,
                                            Authentication authentication) {
        return ResponseEntity.ok(service.findProjectById(id, authentication));
    }

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody @Valid ProjectRequest request) {
        service.createProject(request);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/{id}/enroll")
    public ResponseEntity<?> enrollResearcher(@PathVariable("id") Long id,
                                              @RequestBody @Valid ParticipationRequest request) {
        service.enrollResearcher(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id) {
        service.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/unsubscribe/{participantId}")
    public ResponseEntity<?> unsubscribeParticipant(@PathVariable("id") Long id,
                                                    @PathVariable("participantId") Long participantId) {
        service.unsubscribeParticipant(id, participantId);
        return ResponseEntity.noContent().build();
    }
}
