package nst.domaci.controller;

import jakarta.validation.Valid;
import nst.domaci.dto.SecretaryOfDepartmentHistoryDto;
import nst.domaci.service.SecretaryOfDepartmentHistoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secretaryOfDepartmentHistory")

public class SecretaryHistoryController {


    private final SecretaryOfDepartmentHistoryService secretaryService;

    public SecretaryHistoryController(SecretaryOfDepartmentHistoryService secretaryService) {
        this.secretaryService = secretaryService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody SecretaryOfDepartmentHistoryDto secretaryOfDepartmentHistoryDto) throws Exception{
        SecretaryOfDepartmentHistoryDto dto = secretaryService.save(secretaryOfDepartmentHistoryDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<SecretaryOfDepartmentHistoryDto> dtos = secretaryService.getAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<Object> getAllByPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "2") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "department_id") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {

        Pageable pageable;
        if (sortDirection.equals("asc")) {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        }
        List<SecretaryOfDepartmentHistoryDto> dtos = secretaryService.getAll(pageable);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/query")
    public SecretaryOfDepartmentHistoryDto findById(@RequestParam("departmentId") Long departmentId, @RequestParam("memberId") Long memberId) throws Exception {
        return secretaryService.findById(departmentId,memberId);
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam("departmentId") Long departmentId, @RequestParam("memberId")Long memberId ) throws Exception {
        secretaryService.delete(departmentId,memberId);
        return new ResponseEntity<>("Secretary of Department history removed!", HttpStatus.OK);
    }

}
