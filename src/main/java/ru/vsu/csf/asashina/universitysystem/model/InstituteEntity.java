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
@Table(name = "institute")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InstituteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "faculty_id")
    private FacultyEntity faculty;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "institute_employees",
            joinColumns = {@JoinColumn(name = "institute_id")},
            inverseJoinColumns = {@JoinColumn(name = "social_security_number")})
    @JsonIgnore
    private Set<ResearchAssociateEntity> researchAssociates = new HashSet<>();
}
