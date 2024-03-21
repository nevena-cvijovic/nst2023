package nst.domaci.converter.impl;

import nst.domaci.converter.DtoEntityConverter;
import nst.domaci.domain.Department;
import nst.domaci.dto.DepartmentDto;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter implements DtoEntityConverter<DepartmentDto, Department> {
    @Override
    public DepartmentDto toDto(Department entity) {
        return new DepartmentDto(entity.getId(), entity.getName(), entity.getShortName());
    }

    @Override
    public Department toEntity(DepartmentDto dto) {
        return new Department(dto.getId(), dto.getName(), dto.getShortName());
    }
}
