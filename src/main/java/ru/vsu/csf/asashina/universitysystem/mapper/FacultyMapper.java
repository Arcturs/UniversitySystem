package ru.vsu.csf.asashina.universitysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.vsu.csf.asashina.universitysystem.model.FacultyEntity;
import ru.vsu.csf.asashina.universitysystem.model.request.FacultyRequest;

@Mapper
public interface FacultyMapper {

    FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);

    FacultyEntity toEntity(FacultyRequest request);

    void updateFaculty(FacultyRequest request, @MappingTarget FacultyEntity entity);
}
