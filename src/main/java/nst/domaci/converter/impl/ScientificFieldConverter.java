package nst.domaci.converter.impl;

import nst.domaci.converter.DtoEntityConverter;
import nst.domaci.domain.ScientificField;
import nst.domaci.dto.ScientificFieldDto;
import org.springframework.stereotype.Component;

@Component
public class ScientificFieldConverter implements DtoEntityConverter<ScientificFieldDto, ScientificField> {
    @Override
    public ScientificFieldDto toDto(ScientificField scientificField) {
        return new ScientificFieldDto(scientificField.getId(), scientificField.getName());
    }

    @Override
    public ScientificField toEntity(ScientificFieldDto scientificFieldDto) {
        return new ScientificField(scientificFieldDto.getId(), scientificFieldDto.getName());
    }
}
