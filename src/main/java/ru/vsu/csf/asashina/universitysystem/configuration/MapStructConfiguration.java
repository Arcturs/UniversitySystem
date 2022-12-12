package ru.vsu.csf.asashina.universitysystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vsu.csf.asashina.universitysystem.mapper.*;

@Configuration
public class MapStructConfiguration {

    @Bean
    public FacultyMapper facultyMapper() {
        return FacultyMapper.INSTANCE;
    }

    @Bean
    public InstituteMapper instituteMapper() {
        return InstituteMapper.INSTANCE;
    }

    @Bean
    public EmployeeMapper employeeMapper() {
        return EmployeeMapper.INSTANCE;
    }

    @Bean
    public ProjectMapper projectMapper() {
        return ProjectMapper.INSTANCE;
    }

    @Bean
    public CourseMapper courseMapper() {
        return CourseMapper.INSTANCE;
    }
}
