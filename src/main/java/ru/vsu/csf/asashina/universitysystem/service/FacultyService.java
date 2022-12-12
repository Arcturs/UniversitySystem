package ru.vsu.csf.asashina.universitysystem.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.csf.asashina.universitysystem.mapper.FacultyMapper;
import ru.vsu.csf.asashina.universitysystem.model.FacultyEntity;
import ru.vsu.csf.asashina.universitysystem.model.InstituteEntity;
import ru.vsu.csf.asashina.universitysystem.model.ResearchAssociateEntity;
import ru.vsu.csf.asashina.universitysystem.model.request.FacultyRequest;
import ru.vsu.csf.asashina.universitysystem.repository.FacultyRepository;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class FacultyService {

    private final FacultyRepository repository;

    private final FacultyMapper mapper;

    private final ResearchAssociateService researchAssociateService;

    public List<FacultyEntity> getAllFaculties() {
        return repository.findAll();
    }

    @SneakyThrows
    public FacultyEntity getFacultyById(Long id) {
        return repository.findById(id).orElseThrow(ClassNotFoundException::new);
    }

    public Set<InstituteEntity> getAllInstitutesInFaculty(Long id) {
        return getFacultyById(id).getInstitutes();
    }

    @Transactional
    public void createFaculty(FacultyRequest request) {
        repository.save(mapper.toEntity(request));
    }

    @Transactional
    public FacultyEntity updateFaculty(Long id, FacultyRequest request) {
        FacultyEntity entity = getFacultyById(id);
        mapper.updateFaculty(request, entity);
        return repository.save(entity);
    }

    @Transactional
    public void deleteFaculty(Long id) {
        repository.delete(getFacultyById(id));
    }

    @SneakyThrows
    @Transactional
    public void createDean(Long id, Long researcherId) {
        FacultyEntity faculty = getFacultyById(id);
        ResearchAssociateEntity researchAssociate = researchAssociateService.findById(researcherId);
        faculty.setDeanSocialSecurityNumber(researchAssociate.getSocialSecurityNumber());
        repository.save(faculty);
    }
}
