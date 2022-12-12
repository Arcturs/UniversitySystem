package ru.vsu.csf.asashina.universitysystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.csf.asashina.universitysystem.model.request.EmployeeRequest;
import ru.vsu.csf.asashina.universitysystem.service.ResearchAssociateService;

@RestController
@RequestMapping("/research")
@AllArgsConstructor
public class ResearchAssociateController implements EmployeeController{

    private final ResearchAssociateService service;

    @Override
    public ResponseEntity<?> create(EmployeeRequest request) {
        service.create(request);
        return ResponseEntity.status(201).build();
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
