package nst.domaci.converter.impl;

import nst.domaci.converter.DtoEntityConverter;
import nst.domaci.domain.SecretaryOfDepartmentHistory;
import nst.domaci.dto.SecretaryOfDepartmentHistoryDto;
import org.springframework.stereotype.Component;

@Component
public class SecretaryOfDepartmentHistoryConverter implements DtoEntityConverter<SecretaryOfDepartmentHistoryDto, SecretaryOfDepartmentHistory> {

    private final DepartmentConverter departmentConverter;
    private final MemberConverter memberConverter;

    public SecretaryOfDepartmentHistoryConverter(DepartmentConverter departmentConverter, MemberConverter memberConverter) {
        this.departmentConverter = departmentConverter;
        this.memberConverter = memberConverter;
    }

    @Override
    public SecretaryOfDepartmentHistoryDto toDto(SecretaryOfDepartmentHistory secretaryOfDepartmentHistory) {
        return new SecretaryOfDepartmentHistoryDto(
                departmentConverter.toDto(secretaryOfDepartmentHistory.getDepartment()),
                memberConverter.toDto(secretaryOfDepartmentHistory.getMember()),
                secretaryOfDepartmentHistory.getStartDate(),
                secretaryOfDepartmentHistory.getEndDate()
        );
    }

    @Override
    public SecretaryOfDepartmentHistory toEntity(SecretaryOfDepartmentHistoryDto secretaryOfDepartmentHistoryDto) {
        return new SecretaryOfDepartmentHistory(
                departmentConverter.toEntity(secretaryOfDepartmentHistoryDto.getDepartmentDto()),
                memberConverter.toEntity(secretaryOfDepartmentHistoryDto.getMemberDto()),
                secretaryOfDepartmentHistoryDto.getStartDate(),
                secretaryOfDepartmentHistoryDto.getEndDate()
        );
    }
}
