package nst.domaci.controller;

import jakarta.validation.Valid;
import nst.domaci.dto.AcademicTitleHistoryDto;
import nst.domaci.dto.MemberDto;
import nst.domaci.dto.MemberHistoryDto;
import nst.domaci.service.AcademicTitleHistoryService;
import nst.domaci.service.MemberService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final AcademicTitleHistoryService academicTitleHistoryService;

    public MemberController(MemberService memberService,AcademicTitleHistoryService academicTitleHistoryService) {
        this.memberService = memberService;
        this.academicTitleHistoryService = academicTitleHistoryService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody MemberDto memberDto)throws Exception{
        MemberDto dtoMember = memberService.save(memberDto);
        return new ResponseEntity<>(dtoMember, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<MemberDto> memberDtos = memberService.getAll();

        return new ResponseEntity<>(memberDtos, HttpStatus.OK);
    }



    @GetMapping("/paging")
    public ResponseEntity<Object> getAllByPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "4") int pageSize,
            @RequestParam(name = "sortBy",defaultValue = "id") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection){
        Pageable pageable;
        if(sortDirection.equals("asc")){
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).ascending());
        }else{
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        }

        List<MemberDto> memberDtos = memberService.getAll(pageable);
        return new ResponseEntity<>(memberDtos,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public MemberDto findById(@PathVariable("id") Long id) throws Exception{
        return memberService.findById(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) throws Exception{
       memberService.delete(id);
       return new ResponseEntity<>("Member removed", HttpStatus.OK);

    }

    @GetMapping("/history/{id}")
    public ResponseEntity<Object> getHistory(@PathVariable("id") Long id) throws Exception{
        List<AcademicTitleHistoryDto> history = academicTitleHistoryService.getByMember(id);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @GetMapping("/academicTitleHistory")
    public ResponseEntity<Object> getMembersWithHistory() throws Exception{
        List<AcademicTitleHistoryDto> history =new ArrayList<>();
        List<MemberHistoryDto> members = new ArrayList<>();
        List<MemberDto> memberDtos = memberService.getAll();
        for (MemberDto m: memberDtos) {
            MemberHistoryDto member = new MemberHistoryDto();
            member.setId(m.getId());
            member.setFirstName(m.getFirstName());
            member.setLastName(m.getLastName());
            member.setDepartment(m.getDepartment());
            member.setAcademicTitle(m.getAcademicTitle());
            member.setEducationTitle(m.getEducationTitle());
            member.setScientificField(m.getScientificField());
            history = academicTitleHistoryService.getByMember(m.getId());
            member.setHistoryOfAcademicTitles(history);
            members.add(member);
        }

        return new ResponseEntity<>(members, HttpStatus.OK);
    }
}
