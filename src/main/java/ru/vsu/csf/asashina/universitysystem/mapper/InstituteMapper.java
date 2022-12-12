package ru.vsu.csf.asashina.universitysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.vsu.csf.asashina.universitysystem.model.FacultyEntity;
import ru.vsu.csf.asashina.universitysystem.model.InstituteEntity;
import ru.vsu.csf.asashina.universitysystem.model.request.InstituteRequest;

@Mapper
public interface InstituteMapper {

    InstituteMapper INSTANCE = Mappers.getMapper(InstituteMapper.class);

    @Mapping(target = "name", source = "request.name")
    InstituteEntity fromRequestToEntity(InstituteRequest request, FacultyEntity faculty);

    void updateInstitute(InstituteRequest request, @MappingTarget InstituteEntity entity);
}
