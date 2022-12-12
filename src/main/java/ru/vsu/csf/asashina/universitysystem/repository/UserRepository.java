package ru.vsu.csf.asashina.universitysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.csf.asashina.universitysystem.model.UserInfoEntity;

@Repository
public interface UserRepository extends JpaRepository<UserInfoEntity, Long> {

    boolean existsUserInfoEntityBySocialSecurityNumber(Long socialSecurityNumber);
}
