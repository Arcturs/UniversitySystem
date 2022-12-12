package ru.vsu.csf.asashina.universitysystem.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.csf.asashina.universitysystem.mapper.InstituteMapper;
import ru.vsu.csf.asashina.universitysystem.model.FacultyEntity;
import ru.vsu.csf.asashina.universitysystem.model.InstituteEntity;
import ru.vsu.csf.asashina.universitysystem.model.ResearchAssociateEntity;
import ru.vsu.csf.asashina.universitysystem.model.request.InstituteRequest;
import ru.vsu.csf.asashina.universitysystem.repository.InstituteRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InstituteService {

    public static final String DEAN = "DEAN";
    private final InstituteRepository repository;

    private final InstituteMapper mapper;

    private final FacultyService facultyService;

    @SneakyThrows
    public InstituteEntity getInstituteById(Long id) {
        return repository.findById(id).orElseThrow(ClassNotFoundException::new);
    }

    @Transactional
    public void createInstitute(InstituteRequest request, Authentication authentication) {
        FacultyEntity faculty = facultyService.getFacultyById(request.getFacultyId());
        validateDeansSocialSecurityNumber(faculty, authentication);
        repository.save(mapper.fromRequestToEntity(request, faculty));
    }

    @Transactional
    public InstituteEntity updateInstitute(Long id, InstituteRequest request, Authentication authentication) {
        InstituteEntity institute = getInstituteById(id);
        if (!institute.getFaculty().getId().equals(request.getFacultyId())) {
            throw new IllegalArgumentException("Attempt to change faculty");
        }
        validateDeansSocialSecurityNumber(institute.getFaculty(), authentication);
        mapper.updateInstitute(request, institute);
        return repository.save(institute);
    }

    @Transactional
    public void deleteInstitute(Long id, Authentication authentication) {
        InstituteEntity institute = getInstituteById(id);
        validateDeansSocialSecurityNumber(institute.getFaculty(), authentication);
        repository.delete(institute);
    }

    public Set<ResearchAssociateEntity> getAllResearchesInInstitute(Long id) {
        InstituteEntity institute = getInstituteById(id);
        return institute.getResearchAssociates();
    }

    private void validateDeansSocialSecurityNumber(FacultyEntity faculty, Authentication authentication) {
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        if (roles.contains(DEAN)) {
            Long id = (Long) authentication.getPrincipal();
            if (!faculty.getDeanSocialSecurityNumber().equals(id)) {
                throw new AccessDeniedException("Access denied");
            }
        }
    }
}
