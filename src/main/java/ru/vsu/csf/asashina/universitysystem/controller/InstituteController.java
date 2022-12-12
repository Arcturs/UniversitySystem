package ru.vsu.csf.asashina.universitysystem.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.vsu.csf.asashina.universitysystem.model.request.InstituteRequest;
import ru.vsu.csf.asashina.universitysystem.service.InstituteService;

@RestController
@RequestMapping("/institute")
@AllArgsConstructor
public class InstituteController {

    private final InstituteService service;

    @GetMapping("/{instituteId}")
    public ResponseEntity<?> getInstituteInfo(@PathVariable("instituteId") Long instituteId) {
        return ResponseEntity.ok(service.getInstituteById(instituteId));
    }

    @GetMapping("/{instituteId}/researches")
    public ResponseEntity<?> getAllResearches(@PathVariable("instituteId") Long instituteId) {
        return ResponseEntity.ok(service.getAllResearchesInInstitute(instituteId));
    }

    @PostMapping
    public ResponseEntity<?> createInstitute(@RequestBody @Valid InstituteRequest request,
                                             Authentication authentication) {
        service.createInstitute(request, authentication);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{instituteId}")
    public ResponseEntity<?> updateInstitute(@PathVariable("instituteId") Long instituteId,
                                             @RequestBody @Valid InstituteRequest request,
                                             Authentication authentication) {
        return ResponseEntity.ok(service.updateInstitute(instituteId, request, authentication));
    }

    @DeleteMapping("/{instituteId}")
    public ResponseEntity<?> deleteInstitute(@PathVariable("instituteId") Long instituteId,
                                             Authentication authentication) {
        service.deleteInstitute(instituteId, authentication);
        return ResponseEntity.noContent().build();
    }
}
