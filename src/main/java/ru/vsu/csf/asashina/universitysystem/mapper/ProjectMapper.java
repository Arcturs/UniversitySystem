package ru.vsu.csf.asashina.universitysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.vsu.csf.asashina.universitysystem.model.ProjectEntity;

import java.time.Instant;

@Mapper
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectEntity toEntityFromRequest(String name, Instant startingDate, Instant endDate);
}
