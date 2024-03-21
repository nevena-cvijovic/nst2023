package nst.domaci.service;

import nst.domaci.dto.DepartmentDto;

import java.util.List;
import org.springframework.data.domain.Pageable;
public interface DepartmentService {

    DepartmentDto save(DepartmentDto departmentDto) throws Exception;

    List<DepartmentDto> getAll(Pageable pageable);

    List<DepartmentDto> getAll();

    void delete(Long id) throws Exception;

    void update(DepartmentDto department) throws Exception;

    DepartmentDto findById(Long id) throws Exception;
}
