package nst.domaci.service.impl;

import nst.domaci.converter.impl.HeadOfDepartmentHistoryConverter;
import nst.domaci.domain.*;
import nst.domaci.dto.HeadOfDepartmentHistoryDto;
import nst.domaci.repository.DepartmentRepository;
import nst.domaci.repository.HeadOfDepartmentHistoryRepository;
import nst.domaci.repository.MemberRepository;
import nst.domaci.service.HeadOfDepartmentHistoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class HeadOfDepartmentHistoryServiceImpl implements HeadOfDepartmentHistoryService {

   private final HeadOfDepartmentHistoryRepository headRepository;
   private final HeadOfDepartmentHistoryConverter headConverter;
    private final DepartmentRepository departmentRepository;
    private final MemberRepository memberRepository;

    public HeadOfDepartmentHistoryServiceImpl(HeadOfDepartmentHistoryRepository headRepository, HeadOfDepartmentHistoryConverter headConverter, DepartmentRepository departmentRepository, MemberRepository memberRepository) {
        this.headRepository = headRepository;
        this.headConverter = headConverter;
        this.departmentRepository = departmentRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public HeadOfDepartmentHistoryDto save(HeadOfDepartmentHistoryDto headOfDepartmentHistoryDto) throws Exception {
        HeadOfDepartmentHistory head = headConverter.toEntity(headOfDepartmentHistoryDto);

        Optional<Member> memberOptional = memberRepository.findById(head.getMember().getId());
        if(memberOptional.isEmpty()) throw new Exception("Member doesn't exist");
        Optional<Department> departmentOptional = departmentRepository.findById(head.getDepartment().getId());
        if(departmentOptional.isEmpty()) throw new Exception("Department doesn't exist");

        Optional<HeadOfDepartmentHistory> headOptional = headRepository.findById(new HeadOfDepartmentHistoryId(head.getDepartment().getId(), head.getMember().getId()));
        //if doesn't exist in database, save and return
        if(headOptional.isEmpty()) return headConverter.toDto(headRepository.save(head));
        //else set values, save and return
        HeadOfDepartmentHistory existingHead = headOptional.get();
        existingHead.setStartDate(head.getStartDate());
        existingHead.setEndDate(head.getEndDate());
        return headConverter.toDto(headRepository.save(existingHead));

    }

    @Override
    public List<HeadOfDepartmentHistoryDto> getAll(Pageable pageable) {
        return headRepository.findAll(pageable).stream().map(headConverter::toDto).collect(Collectors.toList());

    }

    @Override
    public List<HeadOfDepartmentHistoryDto> getAll() {
        return headRepository.findAll().stream().map(headConverter::toDto).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public void delete(Long departmentId, Long memberId) throws Exception {
        Optional<HeadOfDepartmentHistory> head = headRepository.findById(new HeadOfDepartmentHistoryId(departmentId,memberId));
        if(head.isEmpty()) throw new Exception("Head of Department history doesn't exist");
        headRepository.delete(head.get());
    }

    @Override
    public void update(HeadOfDepartmentHistoryDto secretaryOfDepartmentHistoryDto) throws Exception {

    }

    @Override
    public HeadOfDepartmentHistoryDto findById(Long departmentId, Long memberId) throws Exception {
        Optional<HeadOfDepartmentHistory> head = headRepository.findById(new HeadOfDepartmentHistoryId(departmentId,memberId));
        if(head.isEmpty()) throw new Exception("Head of Department history doesn't exist");
        return headConverter.toDto(head.get());
    }
}
