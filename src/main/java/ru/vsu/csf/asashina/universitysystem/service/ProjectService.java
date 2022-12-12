package ru.vsu.csf.asashina.universitysystem.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.csf.asashina.universitysystem.mapper.ProjectMapper;
import ru.vsu.csf.asashina.universitysystem.model.ProjectEntity;
import ru.vsu.csf.asashina.universitysystem.model.ProjectParticipationEntity;
import ru.vsu.csf.asashina.universitysystem.model.ResearchAssociateEntity;
import ru.vsu.csf.asashina.universitysystem.model.request.ParticipationRequest;
import ru.vsu.csf.asashina.universitysystem.model.request.ProjectRequest;
import ru.vsu.csf.asashina.universitysystem.repository.ProjectParticipationRepository;
import ru.vsu.csf.asashina.universitysystem.repository.ProjectRepository;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectService {

    public static final String PARTICIPANT = "PARTICIPANT";

    private final ProjectRepository repository;
    private final ProjectParticipationRepository projectParticipationRepository;

    private final ProjectMapper mapper;

    private final ResearchAssociateService researchAssociateService;

    private final Clock clock;

    public List<ProjectEntity> getProjects(Authentication authentication) {
        Long participantId = (Long) authentication.getPrincipal();
        try {
            ResearchAssociateEntity researchAssociate = researchAssociateService.findById(participantId);
            return projectParticipationRepository.findByResearchAssociate(researchAssociate).stream()
                    .map(ProjectParticipationEntity::getProject)
                    .collect(Collectors.toList());
        } catch (ClassNotFoundException e) {
            return repository.findAll();
        }
    }

    @SneakyThrows
    public ProjectEntity findProjectById(Long id, Authentication authentication) {
        ProjectEntity project = getProjectById(id);
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        if (roles.contains(PARTICIPANT)) {
            Long participantId = (Long) authentication.getPrincipal();
            ResearchAssociateEntity researchAssociate = researchAssociateService.findById(participantId);
            if (projectParticipationRepository.existsProjectParticipationEntitiesByProjectAndResearchAssociate(project,
                    researchAssociate)) {
                throw new AccessDeniedException("Access denied");
            }
        }
        return project;
    }

    @Transactional
    public void createProject(ProjectRequest request) {
        Instant startingDate = LocalDateTime.of(request.getStartDate(), request.getStartTime())
                .toInstant(clock.getZone().getRules().getOffset(Instant.now(clock)));
        Instant endDate = LocalDateTime.of(request.getEndDate(), request.getEndTime())
                .toInstant(clock.getZone().getRules().getOffset(Instant.now(clock)));
        repository.save(mapper.toEntityFromRequest(request.getName(), startingDate, endDate));
    }

    @SneakyThrows
    @Transactional
    public void enrollResearcher(Long id, ParticipationRequest request) {
        ProjectEntity project = getProjectById(id);
        ResearchAssociateEntity researchAssociate =
                researchAssociateService.findById(request.getParticipantSecuritySocialNumber());
        if (projectParticipationRepository.existsProjectParticipationEntitiesByProjectAndResearchAssociate(project,
                researchAssociate)) {
            throw new IllegalArgumentException("Following researcher already participates in this project");
        }
        projectParticipationRepository.save(new ProjectParticipationEntity(researchAssociate, project,
                request.getHours()));
    }

    @Transactional
    public void deleteProject(Long id) {
        repository.delete(getProjectById(id));
    }

    @SneakyThrows
    @Transactional
    public void unsubscribeParticipant(Long id, Long participantId) {
        ProjectEntity project = getProjectById(id);
        ResearchAssociateEntity researchAssociate = researchAssociateService.findById(participantId);
        projectParticipationRepository.deleteProjectParticipationEntitiesByProjectAndResearchAssociate(project, researchAssociate);
    }

    @SneakyThrows
    private ProjectEntity getProjectById(Long id) {
        return repository.findById(id).orElseThrow(ClassNotFoundException::new);
    }
}
