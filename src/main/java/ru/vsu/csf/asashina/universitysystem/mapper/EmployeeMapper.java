package ru.vsu.csf.asashina.universitysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.vsu.csf.asashina.universitysystem.model.Employee;
import ru.vsu.csf.asashina.universitysystem.model.request.EmployeeRequest;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee fromRequestToEntity(EmployeeRequest request);
}
