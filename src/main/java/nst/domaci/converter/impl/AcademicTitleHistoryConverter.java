package nst.domaci.converter.impl;

import nst.domaci.converter.DtoEntityConverter;
import nst.domaci.domain.AcademicTitleHistory;
import nst.domaci.dto.AcademicTitleHistoryDto;
import org.springframework.stereotype.Component;

@Component
public class AcademicTitleHistoryConverter implements DtoEntityConverter<AcademicTitleHistoryDto, AcademicTitleHistory> {
    private final MemberConverter memberConverter;
    private final AcademicTitleConverter academicTitleConverter;
    private final ScientificFieldConverter scientificFieldConverter;

    public AcademicTitleHistoryConverter(MemberConverter memberConverter, AcademicTitleConverter academicTitleConverter, ScientificFieldConverter scientificFieldConverter) {
        this.memberConverter = memberConverter;
        this.academicTitleConverter = academicTitleConverter;
        this.scientificFieldConverter = scientificFieldConverter;
    }

    @Override
    public AcademicTitleHistoryDto toDto(AcademicTitleHistory academicTitleHistory) {
        return new AcademicTitleHistoryDto(memberConverter.toDto(academicTitleHistory.getMember()),
                academicTitleConverter.toDto(academicTitleHistory.getAcademicTitle()),
                academicTitleHistory.getStartDate(),academicTitleHistory.getEndDate(),
                scientificFieldConverter.toDto(academicTitleHistory.getScientificField()));
    }

    @Override
    public AcademicTitleHistory toEntity(AcademicTitleHistoryDto academicTitleHistoryDto) {
        return new AcademicTitleHistory(memberConverter.toEntity(academicTitleHistoryDto.getMemberDto()),
                academicTitleConverter.toEntity(academicTitleHistoryDto.getAcademicTitleDto()),
                academicTitleHistoryDto.getStartDate(),academicTitleHistoryDto.getEndDate(),
                scientificFieldConverter.toEntity(academicTitleHistoryDto.getScientificFieldDto()));
    }
}
