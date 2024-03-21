package nst.domaci.service.impl;


import nst.domaci.converter.impl.MemberConverter;
import nst.domaci.domain.*;
import nst.domaci.dto.MemberDto;
import nst.domaci.repository.*;
import nst.domaci.service.MemberService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;
    private final AcademicTitleRepository academicTitleRepository;
    private final EducationTitleRepository educationTitleRepository;
    private final ScientificFieldRepository scientificFieldRepository;
    private final DepartmentRepository departmentRepository;

    public MemberServiceImpl(MemberRepository memberRepository, MemberConverter memberConverter, AcademicTitleRepository academicTitleRepository, EducationTitleRepository educationTitleRepository, ScientificFieldRepository scientificFieldRepository, DepartmentRepository departmentRepository) {
        this.memberRepository = memberRepository;
        this.memberConverter = memberConverter;
        this.academicTitleRepository = academicTitleRepository;
        this.educationTitleRepository = educationTitleRepository;
        this.scientificFieldRepository = scientificFieldRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public MemberDto save(MemberDto memberDto) throws Exception {
        Member member = memberConverter.toEntity(memberDto);

        Optional<AcademicTitle> academicTitleOptional = academicTitleRepository.findById(member.getAcademicTitle().getId());
        if(academicTitleOptional.isEmpty()) throw new Exception("Academic title doesn't exist");
        Optional<EducationTitle> educationTitleOptional = educationTitleRepository.findById(member.getEducationTitle().getId());
        if(educationTitleOptional.isEmpty()) throw new Exception("Education title doesn't exist");
        Optional<ScientificField> scientificFieldOptional = scientificFieldRepository.findById(member.getScientificField().getId());
        if(scientificFieldOptional.isEmpty()) throw new Exception("Scientific field doesn't exist");
        Optional<Department> departmentOptional = departmentRepository.findById(member.getDepartment().getId());
        if(departmentOptional.isEmpty()) throw new Exception("Department doesn't exist");

        return memberConverter.toDto(memberRepository.save(member));
    }

    @Override
    public List<MemberDto> getAll() {
        return memberRepository.findAll().stream().map(memberConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public List<MemberDto> getAll(Pageable pageable) {
        return memberRepository.findAll(pageable).stream().map(memberConverter::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        Optional<Member> member = memberRepository.findById(id);
        if(member.isEmpty())throw new Exception("Member doesn't exist");
        memberRepository.deleteById(id);

    }

    @Override
    public void update(MemberDto memberDto) throws Exception {

    }

    @Override
    public MemberDto findById(Long id) throws Exception {
        Optional<Member> member = memberRepository.findById(id);
        if(member.isEmpty()) throw new Exception("Member doesn't exist");
        return memberConverter.toDto(member.get());
    }
}
