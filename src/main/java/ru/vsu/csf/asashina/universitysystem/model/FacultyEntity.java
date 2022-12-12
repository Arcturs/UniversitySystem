package ru.vsu.csf.asashina.universitysystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "faculty")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FacultyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Long deanSocialSecurityNumber;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "faculty")
    @JsonIgnore
    private Set<InstituteEntity> institutes = new HashSet<>();
}
