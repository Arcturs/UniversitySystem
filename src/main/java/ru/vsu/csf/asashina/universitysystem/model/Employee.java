package ru.vsu.csf.asashina.universitysystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    private Long socialSecurityNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;
}
