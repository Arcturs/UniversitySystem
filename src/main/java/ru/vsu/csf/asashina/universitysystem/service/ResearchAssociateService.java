package ru.vsu.csf.asashina.universitysystem.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.csf.asashina.universitysystem.mapper.EmployeeMapper;
import ru.vsu.csf.asashina.universitysystem.model.ResearchAssociateEntity;
import ru.vsu.csf.asashina.universitysystem.model.request.EmployeeRequest;
import ru.vsu.csf.asashina.universitysystem.repository.ResearchAssociateRepository;

@Service
@AllArgsConstructor
public class ResearchAssociateService implements EmployeeService{

    private final ResearchAssociateRepository researchAssociateRepository;

    private final EmployeeMapper employeeMapper;

    @SneakyThrows
    @Transactional
    @Override
    public void create(EmployeeRequest request) {
        if (request.getFieldOfStudy().isEmpty()) {
            throw new NoSuchFieldException("Unable to save research associate without field of study");
        }
        ResearchAssociateEntity researchAssociate = (ResearchAssociateEntity) employeeMapper.fromRequestToEntity(request);
        researchAssociate.setFieldOfStudy(request.getFieldOfStudy());
        researchAssociateRepository.save(researchAssociate);
    }

    @SneakyThrows
    @Transactional
    @Override
    public void delete(Long id) {
        researchAssociateRepository.delete(findById(id));
    }

    public ResearchAssociateEntity findById(Long id) throws ClassNotFoundException {
        return researchAssociateRepository.findById(id).orElseThrow(ClassNotFoundException::new);
    }
}
