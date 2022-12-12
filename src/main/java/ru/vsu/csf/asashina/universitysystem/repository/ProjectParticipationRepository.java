package ru.vsu.csf.asashina.universitysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.csf.asashina.universitysystem.model.ProjectEntity;
import ru.vsu.csf.asashina.universitysystem.model.ProjectParticipationEntity;
import ru.vsu.csf.asashina.universitysystem.model.ResearchAssociateEntity;

import java.util.List;

@Repository
public interface ProjectParticipationRepository extends JpaRepository<ProjectParticipationEntity, Long> {

    List<ProjectParticipationEntity> findByResearchAssociate(ResearchAssociateEntity researchAssociate);

    void deleteProjectParticipationEntitiesByProjectAndResearchAssociate(ProjectEntity project,
                                                                         ResearchAssociateEntity researchAssociate);

    boolean existsProjectParticipationEntitiesByProjectAndResearchAssociate(ProjectEntity project,
                                                                            ResearchAssociateEntity researchAssociate);
}
