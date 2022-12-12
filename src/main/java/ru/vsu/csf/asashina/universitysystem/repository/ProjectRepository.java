package ru.vsu.csf.asashina.universitysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.csf.asashina.universitysystem.model.ProjectEntity;
import ru.vsu.csf.asashina.universitysystem.model.ProjectParticipationEntity;

import java.util.List;
import java.util.Set;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
}
