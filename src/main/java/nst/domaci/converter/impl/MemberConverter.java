package nst.domaci.converter.impl;

import nst.domaci.converter.DtoEntityConverter;
import nst.domaci.domain.Member;
import nst.domaci.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter implements DtoEntityConverter<MemberDto, Member> {
    @Autowired
    private AcademicTitleConverter academicTitleConverter;
    @Autowired
    private EducationTitleConverter educationTitleConverter;
    @Autowired
    private ScientificFieldConverter scientificFieldConverter;
    @Autowired
    private DepartmentConverter departmentConverter;


    @Override
    public MemberDto toDto(Member member) {
        return new MemberDto(member.getId(),
                member.getFirstName(),
                member.getLastName(),
                academicTitleConverter.toDto(member.getAcademicTitle()),
                educationTitleConverter.toDto(member.getEducationTitle()),
                scientificFieldConverter.toDto(member.getScientificField()),
                departmentConverter.toDto(member.getDepartment()) );
    }

    @Override
    public Member toEntity(MemberDto memberDto) {
        return new Member(memberDto.getId(),
                memberDto.getFirstName(),
                memberDto.getLastName(),
                academicTitleConverter.toEntity(memberDto.getAcademicTitle()),
                educationTitleConverter.toEntity(memberDto.getEducationTitle()),
                scientificFieldConverter.toEntity(memberDto.getScientificField()),
                departmentConverter.toEntity(memberDto.getDepartment()));
    }
}
