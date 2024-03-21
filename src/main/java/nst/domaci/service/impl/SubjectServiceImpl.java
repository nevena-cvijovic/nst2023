package nst.domaci.service.impl;

import nst.domaci.converter.impl.SubjectConverter;
import nst.domaci.domain.Department;
import nst.domaci.domain.Subject;
import nst.domaci.dto.SubjectDto;
import nst.domaci.repository.DepartmentRepository;
import nst.domaci.repository.SubjectRepository;
import nst.domaci.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final DepartmentRepository departmentRepository;
    private final SubjectConverter subjectConverter;

    public SubjectServiceImpl(SubjectRepository subjectRepository, DepartmentRepository departmentRepository, SubjectConverter subjectConverter) {
        this.subjectRepository = subjectRepository;
        this.departmentRepository = departmentRepository;
        this.subjectConverter = subjectConverter;
    }

    @Override
    @Transactional
    public SubjectDto save(SubjectDto subjectDto) throws Exception {
        Optional<Subject> subject = subjectRepository.findByName(subjectDto.getName());
        if (subject.isPresent()) throw new Exception("Subject with this name already exists in database");

        return subjectConverter.toDto(subjectRepository.save(subjectConverter.toEntity(subjectDto)));
    }

    @Override
    public List<SubjectDto> getAll() {
        return subjectRepository.findAll().stream().map(subjectConverter::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        Optional<Subject> subject = subjectRepository.findById(id);
        if(subject.isEmpty()) throw new Exception("Subject does not exist");
        subjectRepository.delete(subject.get());
    }

    @Override
    public void update(SubjectDto subjectDto) throws Exception {

    }

    @Override
    public SubjectDto findById(Long id) throws Exception {
        Optional<Subject> subject = subjectRepository.findById(id);
        if(subject.isEmpty()) throw new Exception("Subject does not exist");
        return subjectConverter.toDto(subject.get());
    }
}
