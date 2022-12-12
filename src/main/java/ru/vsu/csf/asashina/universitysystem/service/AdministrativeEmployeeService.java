package ru.vsu.csf.asashina.universitysystem.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.csf.asashina.universitysystem.mapper.EmployeeMapper;
import ru.vsu.csf.asashina.universitysystem.model.AdministrativeEmployeeEntity;
import ru.vsu.csf.asashina.universitysystem.model.request.EmployeeRequest;
import ru.vsu.csf.asashina.universitysystem.repository.AdministrativeEmployeeRepository;

@Service
@AllArgsConstructor
public class AdministrativeEmployeeService implements EmployeeService{

    private final AdministrativeEmployeeRepository administrativeEmployeeRepository;

    private final EmployeeMapper employeeMapper;

    @Transactional
    @Override
    public void create(EmployeeRequest request) {
        AdministrativeEmployeeEntity administrativeEmployee = (AdministrativeEmployeeEntity) employeeMapper.fromRequestToEntity(request);
        administrativeEmployeeRepository.save(administrativeEmployee);
    }

    @Transactional
    @SneakyThrows
    @Override
    public void delete(Long id) {
        administrativeEmployeeRepository.delete(
                administrativeEmployeeRepository.findById(id).orElseThrow(ClassNotFoundException::new)
        );
    }
}
