package nst.domaci.converter.impl;

import nst.domaci.converter.DtoEntityConverter;
import nst.domaci.domain.AcademicTitle;
import nst.domaci.dto.AcademicTitleDto;
import org.springframework.stereotype.Component;

@Component
public class AcademicTitleConverter implements DtoEntityConverter<AcademicTitleDto, AcademicTitle> {
    @Override
    public AcademicTitleDto toDto(AcademicTitle academicTitle) {
        return new AcademicTitleDto(academicTitle.getId(),academicTitle.getName());
    }

    @Override
    public AcademicTitle toEntity(AcademicTitleDto academicTitleDto) {
        return new AcademicTitle(academicTitleDto.getId(),academicTitleDto.getName());
    }
}
