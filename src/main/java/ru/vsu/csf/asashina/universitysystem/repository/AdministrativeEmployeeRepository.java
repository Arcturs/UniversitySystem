package ru.vsu.csf.asashina.universitysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.csf.asashina.universitysystem.model.AdministrativeEmployeeEntity;

@Repository
public interface AdministrativeEmployeeRepository extends JpaRepository<AdministrativeEmployeeEntity, Long> {
}
