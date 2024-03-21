package nst.domaci.converter.impl;

import nst.domaci.converter.DtoEntityConverter;
import nst.domaci.domain.EducationTitle;
import nst.domaci.dto.EducationTitleDto;
import org.springframework.stereotype.Component;

@Component
public class EducationTitleConverter implements DtoEntityConverter<EducationTitleDto, EducationTitle> {
    @Override
    public EducationTitleDto toDto(EducationTitle educationTitle) {
        return new EducationTitleDto(educationTitle.getId(), educationTitle.getName());
    }

    @Override
    public EducationTitle toEntity(EducationTitleDto educationTitleDto) {
        return new EducationTitle(educationTitleDto.getId(), educationTitleDto.getName());
    }
}
