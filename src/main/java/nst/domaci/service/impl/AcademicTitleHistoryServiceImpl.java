package nst.domaci.service.impl;

import nst.domaci.converter.impl.AcademicTitleHistoryConverter;
import nst.domaci.domain.*;
import nst.domaci.dto.AcademicTitleHistoryDto;
import nst.domaci.repository.AcademicTitleHistoryRepository;
import nst.domaci.repository.AcademicTitleRepository;
import nst.domaci.repository.MemberRepository;
import nst.domaci.repository.ScientificFieldRepository;
import nst.domaci.service.AcademicTitleHistoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AcademicTitleHistoryServiceImpl implements AcademicTitleHistoryService {

    private final AcademicTitleHistoryRepository academicTitleHistoryRepository;
    private final AcademicTitleHistoryConverter academicTitleHistoryConverter;
    private final MemberRepository memberRepository;
    private final AcademicTitleRepository academicTitleRepository;
    private final ScientificFieldRepository scientificFieldRepository;

    public AcademicTitleHistoryServiceImpl(AcademicTitleHistoryRepository academicTitleHistoryRepository, AcademicTitleHistoryConverter academicTitleHistoryConverter, MemberRepository memberRepository, AcademicTitleRepository academicTitleRepository, ScientificFieldRepository scientificFieldRepository) {
        this.academicTitleHistoryRepository = academicTitleHistoryRepository;
        this.academicTitleHistoryConverter = academicTitleHistoryConverter;
        this.memberRepository = memberRepository;
        this.academicTitleRepository = academicTitleRepository;
        this.scientificFieldRepository = scientificFieldRepository;
    }

    @Override
    @Transactional
    public AcademicTitleHistoryDto save(AcademicTitleHistoryDto academicTitleHistoryDto) throws Exception {
        AcademicTitleHistory academicTitleHistory = academicTitleHistoryConverter.toEntity(academicTitleHistoryDto);
        Optional<Member> member = memberRepository.findById(academicTitleHistory.getMember().getId());
        if(member.isEmpty()) throw new Exception("Member doesn't exist");
        Optional<AcademicTitle> academicTitle = academicTitleRepository.findById(academicTitleHistory.getAcademicTitle().getId());
        if(academicTitle.isEmpty()) throw new Exception("Academic title doesn't exist");
        Optional<ScientificField> scientificField = scientificFieldRepository.findById(academicTitleHistory.getScientificField().getId());
        if(scientificField.isEmpty()) throw new Exception("Scientific field doesn't exist");

        //setting values to existing member
        Member m = member.get();
        m.setAcademicTitle(academicTitleHistory.getAcademicTitle());
        m.setScientificField(academicTitleHistory.getScientificField());
        //save member
        memberRepository.save(m);

        //does exist or not in database
        Optional<AcademicTitleHistory> academicTitleHistoryOptional = academicTitleHistoryRepository.findById(
                new AcademicTitleHistoryId(academicTitleHistory.getMember().getId(), academicTitleHistory.getAcademicTitle().getId()));
        //if doesn't exist save and return
        if(academicTitleHistoryOptional.isEmpty()) return academicTitleHistoryConverter.toDto(academicTitleHistoryRepository.save(academicTitleHistory));

        //else set values to existing academic title history, save and return
        AcademicTitleHistory existingAcademicTitleHistory = academicTitleHistoryOptional.get();
        existingAcademicTitleHistory.setStartDate(academicTitleHistory.getStartDate());
        existingAcademicTitleHistory.setEndDate(academicTitleHistory.getEndDate());
        existingAcademicTitleHistory.setScientificField(academicTitleHistory.getScientificField());
        return academicTitleHistoryConverter.toDto(academicTitleHistoryRepository.save(existingAcademicTitleHistory));


    }

    @Override
    public List<AcademicTitleHistoryDto> getAll(Pageable pageable) {
        return academicTitleHistoryRepository.findAll(pageable).stream()
                .map(academicTitleHistoryConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AcademicTitleHistoryDto> getAll() {
         return academicTitleHistoryRepository.findAll().stream()
                .map(academicTitleHistoryConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AcademicTitleHistoryDto> getByMember(Long id) {
        return academicTitleHistoryRepository.findByMemberId(id).stream().map(academicTitleHistoryConverter::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long memberId, Long academicTitleId) throws Exception {
        Optional<AcademicTitleHistory> academicTitleHistory = academicTitleHistoryRepository.findById(new AcademicTitleHistoryId(memberId,academicTitleId));
        if(academicTitleHistory.isEmpty()) throw new Exception("Academic title history doesn't exist");
        academicTitleHistoryRepository.delete(academicTitleHistory.get());
    }

    @Override
    public void update(AcademicTitleHistoryDto academicTitleHistoryDto) throws Exception {

    }

    @Override
    public AcademicTitleHistoryDto findById(Long memberId, Long academicTitleId) throws Exception {
        Optional<AcademicTitleHistory> academicTitleHistory = academicTitleHistoryRepository.findById(new AcademicTitleHistoryId(memberId,academicTitleId));
        if(academicTitleHistory.isEmpty()) throw new Exception("Academic title history doesn't exist");
       return  academicTitleHistoryConverter.toDto(academicTitleHistory.get());
    }
}
