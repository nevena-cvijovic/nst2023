package nst.domaci.service;


import nst.domaci.domain.SecretaryOfDepartmentHistoryId;
import nst.domaci.dto.SecretaryOfDepartmentHistoryDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SecretaryOfDepartmentHistoryService {

    SecretaryOfDepartmentHistoryDto save (SecretaryOfDepartmentHistoryDto secretaryOfDepartmentHistoryDto) throws Exception;

    List<SecretaryOfDepartmentHistoryDto> getAll(Pageable pageable);
    List<SecretaryOfDepartmentHistoryDto> getAll();

    void delete (Long departmentId, Long memberId) throws Exception;

    void update (SecretaryOfDepartmentHistoryDto secretaryOfDepartmentHistoryDto) throws Exception;

    SecretaryOfDepartmentHistoryDto findById(Long departmentId, Long memberId) throws Exception;
}
