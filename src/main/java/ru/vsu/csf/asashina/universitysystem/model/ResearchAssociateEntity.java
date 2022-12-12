package ru.vsu.csf.asashina.universitysystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "research_associate")
@Setter
@Getter
public class ResearchAssociateEntity extends Employee{

    @Column(nullable = false)
    private String fieldOfStudy;

    @ManyToMany(mappedBy = "researchAssociates")
    private Set<InstituteEntity> institutes = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "lectures",
            joinColumns = {@JoinColumn(name = "social_security_number")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    @JsonIgnore
    private Set<CourseEntity> courses = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "researchAssociate")
    @JsonIgnore
    private Set<ProjectParticipationEntity> projects = new HashSet<>();
}
