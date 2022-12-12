package ru.vsu.csf.asashina.universitysystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm", timezone = "GMT")
    private Instant startingDate;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm", timezone = "GMT")
    private Instant endDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "project")
    @JsonIgnore
    private Set<ProjectParticipationEntity> researchAssociates = new HashSet<>();
}
