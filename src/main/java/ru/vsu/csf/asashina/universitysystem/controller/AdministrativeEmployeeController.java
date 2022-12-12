package ru.vsu.csf.asashina.universitysystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.csf.asashina.universitysystem.model.request.EmployeeRequest;
import ru.vsu.csf.asashina.universitysystem.service.AdministrativeEmployeeService;

@RestController
@RequestMapping("/administrative")
@AllArgsConstructor
public class AdministrativeEmployeeController implements EmployeeController{

    private final AdministrativeEmployeeService service;

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
