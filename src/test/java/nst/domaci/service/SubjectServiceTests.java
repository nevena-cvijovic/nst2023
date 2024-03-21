package nst.domaci.service;

import nst.domaci.converter.impl.SubjectConverter;
import nst.domaci.domain.Department;
import nst.domaci.domain.Subject;
import nst.domaci.repository.DepartmentRepository;
import nst.domaci.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SubjectServiceTests {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectConverter subjectConverter;

    @MockBean
    private SubjectRepository subjectRepository;
    @MockBean
    private DepartmentRepository departmentRepository;

    //save testovi
    @Test
    public void saveSuccessTest() throws Exception {
        Department department = new Department(33L, "KIS12", "K_IS12");
        Subject subject = new Subject(31L,"nst1",4,department);
        when(subjectRepository.save(subject)).thenReturn(subject);
        when(subjectRepository.findByName(subject.getName())).thenReturn(Optional.empty());
        Subject subj = subjectConverter.toEntity(subjectService.save(subjectConverter.toDto(subject)));
        assertNotNull(subj);
        assertEquals(subj,subject);

    }

    @Test
    public void saveFailureTest(){
        Department department = new Department(34L, "KIS14", "K_IS14");
        Subject subject = new Subject(34L,"nst4",4,department);
        when(subjectRepository.findByName(subject.getName())).thenReturn(Optional.of(subject));
        assertThrows(Exception.class,()->{
            subjectService.save(subjectConverter.toDto(subject));
        });
    }
    //get all - test

    @Test
    public void getAllTest(){
        Department dept1 = new Department(35L, "KIS15", "K_IS15");
        Subject subj1 = new Subject(35L,"nst5",4,dept1);
        Department dept2 = new Department(36L, "KIS16", "K_IS16");
        Subject subj2 = new Subject(36L,"nst6",4,dept1);
        List<Subject> subjects = Arrays.asList(subj1,subj2);
        when(subjectRepository.findAll()).thenReturn(subjects);
        List<Subject> subjectsInDB = subjectService.getAll().stream().map(subjectConverter::toEntity).collect(Collectors.toList());
        assertEquals(subjects.size(),subjectsInDB.size());
        for(int i = 0; i<subjects.size();i++){
            assertEquals(subjects.get(i),subjectsInDB.get((i)));
        }

    }

    //delete testovi
    @Test
    public void deleteSuccessTest() throws Exception {
        Department department = new Department(38L, "KIS18", "K_IS18");
        Subject subject = new Subject(38L,"nst8",4,department);
        when(subjectRepository.findById(subject.getId())).thenReturn(Optional.of(subject));
        subjectService.delete(subject.getId());
        verify(subjectRepository, times(1)).delete(subject);
    }

    @Test
    public void deleteFailureTest() {
        Department department = new Department(39L, "KIS19", "K_IS19");
        Subject subject = new Subject(39L,"nst9",4,department);
        when(subjectRepository.findById(subject.getId())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> {
            subjectService.delete(subject.getId());
        });
    }
    //find by id testovi
    @Test
    public void findByIdSuccessTest() throws Exception {
        Department department = new Department(40L, "KIS20", "K_IS20");
        Subject subject = new Subject(40L,"nst10",4,department);
        when(subjectRepository.findById(subject.getId())).thenReturn(Optional.of(subject));
        Subject existed = subjectConverter.toEntity(subjectService.findById(subject.getId()));
        assertNotNull(existed);
        assertEquals(subject, existed);
    }

    @Test
    public void findByIdFailureTest() {
        Department department = new Department(41L, "KIS21", "K_IS21");
        Subject subject = new Subject(41L,"nst11",4,department);
        when(subjectRepository.findById(subject.getId())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> {
            subjectService.findById(subject.getId());
        });
    }
}