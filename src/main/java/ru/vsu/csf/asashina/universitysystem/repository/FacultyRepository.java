package ru.vsu.csf.asashina.universitysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.csf.asashina.universitysystem.model.FacultyEntity;

@Repository
public interface FacultyRepository extends JpaRepository<FacultyEntity, Long> {
}
