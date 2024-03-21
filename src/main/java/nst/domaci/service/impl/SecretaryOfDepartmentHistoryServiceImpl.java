package nst.domaci.service.impl;

import nst.domaci.converter.impl.SecretaryOfDepartmentHistoryConverter;
import nst.domaci.domain.*;
import nst.domaci.dto.SecretaryOfDepartmentHistoryDto;
import nst.domaci.repository.DepartmentRepository;
import nst.domaci.repository.MemberRepository;
import nst.domaci.repository.SecretaryOfDepartmentHistoryRepository;
import nst.domaci.service.SecretaryOfDepartmentHistoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class SecretaryOfDepartmentHistoryServiceImpl implements SecretaryOfDepartmentHistoryService {

    private final SecretaryOfDepartmentHistoryRepository secretaryRepository;
    private final SecretaryOfDepartmentHistoryConverter secretaryConverter;
    private final DepartmentRepository departmentRepository;
    private final MemberRepository memberRepository;

    public SecretaryOfDepartmentHistoryServiceImpl(SecretaryOfDepartmentHistoryRepository secretaryRepository, SecretaryOfDepartmentHistoryConverter secretaryConverter, DepartmentRepository departmentRepository, MemberRepository memberRepository) {
        this.secretaryRepository = secretaryRepository;
        this.secretaryConverter = secretaryConverter;
        this.departmentRepository = departmentRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public SecretaryOfDepartmentHistoryDto save(SecretaryOfDepartmentHistoryDto secretaryOfDepartmentHistoryDto) throws Exception {
        SecretaryOfDepartmentHistory secretary = secretaryConverter.toEntity(secretaryOfDepartmentHistoryDto);

        Optional<Member> memberOptional = memberRepository.findById(secretary.getMember().getId());
        if(memberOptional.isEmpty()) throw new Exception("Member doesn't exist");
        Optional<Department> departmentOptional = departmentRepository.findById(secretary.getDepartment().getId());
        if(departmentOptional.isEmpty()) throw new Exception("Department doesn't exist");

        Optional<SecretaryOfDepartmentHistory> secretaryOptional = secretaryRepository.findById(new SecretaryOfDepartmentHistoryId(secretary.getDepartment().getId(), secretary.getMember().getId()));
       //if doesn't exist in database, save and return
        if(secretaryOptional.isEmpty()) return secretaryConverter.toDto(secretaryRepository.save(secretary));
        //else set values, save and return
        SecretaryOfDepartmentHistory existingSecretary = secretaryOptional.get();
        existingSecretary.setStartDate(secretary.getStartDate());
        existingSecretary.setEndDate(secretary.getEndDate());
        return secretaryConverter.toDto(secretaryRepository.save(existingSecretary));

    }

    @Override
    public List<SecretaryOfDepartmentHistoryDto> getAll(Pageable pageable) {
        return secretaryRepository.findAll(pageable).stream().map(secretaryConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public List<SecretaryOfDepartmentHistoryDto> getAll() {
        return secretaryRepository.findAll().stream().map(secretaryConverter::toDto).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public void delete(Long departmentId, Long memberId) throws Exception {
        Optional<SecretaryOfDepartmentHistory> secretary = secretaryRepository.findById(new SecretaryOfDepartmentHistoryId(departmentId,memberId));
        if(secretary.isEmpty()) throw new Exception("Secretary of Department history doesn't exist");
        secretaryRepository.delete(secretary.get());
    }

    @Override
    public void update(SecretaryOfDepartmentHistoryDto secretaryOfDepartmentHistoryDto) throws Exception {

    }

    @Override
    public SecretaryOfDepartmentHistoryDto findById(Long departmentId, Long memberId) throws Exception {
        Optional<SecretaryOfDepartmentHistory> secretary = secretaryRepository.findById(new SecretaryOfDepartmentHistoryId(departmentId,memberId));
        if(secretary.isEmpty()) throw new Exception("Secretary of Department history doesn't exist");
        return secretaryConverter.toDto(secretary.get());
    }
}
