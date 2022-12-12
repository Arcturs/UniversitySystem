package ru.vsu.csf.asashina.universitysystem.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.csf.asashina.universitysystem.model.request.EmployeeRequest;

@RequestMapping("/employee")
public interface EmployeeController {

    @PostMapping
    ResponseEntity<?> create(@RequestBody @Valid EmployeeRequest request);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long id);
}
