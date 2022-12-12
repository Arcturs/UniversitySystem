package ru.vsu.csf.asashina.universitysystem.service;

import ru.vsu.csf.asashina.universitysystem.model.request.EmployeeRequest;

public interface EmployeeService {

    void create(EmployeeRequest request);

    void delete(Long id);
}
