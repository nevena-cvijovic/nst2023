package nst.domaci.converter.impl;

import nst.domaci.converter.DtoEntityConverter;
import nst.domaci.domain.HeadOfDepartmentHistory;
import nst.domaci.domain.SecretaryOfDepartmentHistory;
import nst.domaci.dto.HeadOfDepartmentHistoryDto;
import nst.domaci.dto.SecretaryOfDepartmentHistoryDto;
import org.springframework.stereotype.Component;

@Component
public class HeadOfDepartmentHistoryConverter implements DtoEntityConverter<HeadOfDepartmentHistoryDto, HeadOfDepartmentHistory> {
    private final DepartmentConverter departmentConverter;
    private final MemberConverter memberConverter;

    public HeadOfDepartmentHistoryConverter(DepartmentConverter departmentConverter, MemberConverter memberConverter) {
        this.departmentConverter = departmentConverter;
        this.memberConverter = memberConverter;
    }

    @Override
    public HeadOfDepartmentHistoryDto toDto(HeadOfDepartmentHistory headOfDepartmentHistory) {
        return new HeadOfDepartmentHistoryDto(
                departmentConverter.toDto(headOfDepartmentHistory.getDepartment()),
                memberConverter.toDto(headOfDepartmentHistory.getMember()),
                headOfDepartmentHistory.getStartDate(),
                headOfDepartmentHistory.getEndDate()
        );
    }

    @Override
    public HeadOfDepartmentHistory toEntity(HeadOfDepartmentHistoryDto headOfDepartmentHistoryDto) {
        return new HeadOfDepartmentHistory(
                departmentConverter.toEntity(headOfDepartmentHistoryDto.getDepartmentDto()),
                memberConverter.toEntity(headOfDepartmentHistoryDto.getMemberDto()),
                headOfDepartmentHistoryDto.getStartDate(),
                headOfDepartmentHistoryDto.getEndDate()
        );
    }
}
