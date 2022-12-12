package ru.vsu.csf.asashina.universitysystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project_participation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectParticipationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "social_security_number")
    private ResearchAssociateEntity researchAssociate;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @Column(nullable = false)
    private Integer hours;

    public ProjectParticipationEntity(ResearchAssociateEntity researchAssociate, ProjectEntity project, Integer hours) {
        this.researchAssociate = researchAssociate;
        this.project = project;
        this.hours = hours;
    }
}
