package nst.domaci.converter.impl;

import nst.domaci.converter.DtoEntityConverter;
import nst.domaci.domain.Subject;
import nst.domaci.dto.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectConverter implements DtoEntityConverter<SubjectDto, Subject> {

    @Autowired
    private DepartmentConverter departmentConverter;
    @Override
    public SubjectDto toDto(Subject subject) {
        return new SubjectDto(subject.getId(), subject.getName(), subject.getEspb(), departmentConverter.toDto(subject.getDepartment()));
    }

    @Override
    public Subject toEntity(SubjectDto subjectDto) {
        return new Subject(subjectDto.getId(),subjectDto.getName(),subjectDto.getEspb(), departmentConverter.toEntity(subjectDto.getDepartmentDto()));
    }
}
