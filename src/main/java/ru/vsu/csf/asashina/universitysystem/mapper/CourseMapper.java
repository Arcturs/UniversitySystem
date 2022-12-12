package ru.vsu.csf.asashina.universitysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.vsu.csf.asashina.universitysystem.model.CourseEntity;
import ru.vsu.csf.asashina.universitysystem.model.request.CourseRequest;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseEntity toEntityFromRequest(CourseRequest request);

    void updateCourse(CourseRequest request, @MappingTarget CourseEntity entity);
}
