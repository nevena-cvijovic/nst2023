package nst.domaci.repository;

import nst.domaci.domain.Department;
import nst.domaci.domain.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SubjectRepositoryTests {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    //save - test
    @Test
    public void saveSubjectTest(){
        Department department = departmentRepository.save(new Department(26L, "KIS5", "K_IS5"));
        Subject subject = subjectRepository.save(new Subject(31L,"nst1",4,department));
        assertNotNull(subject);
    }

    //get all -test
    @Test
    public void getAllSubjectsTest(){
        List<Subject> subjects = subjectRepository.findAll();
        assertNotNull(subjects);
    }

    //delete - test
    @Test
    public void deleteSubjectTest(){
        Department department = departmentRepository.save(new Department(27L, "KIS6", "K_IS6"));
        Subject subject = subjectRepository.save(new Subject(32L,"nst2",4,department));
        assertNotNull(subject);
        //brisanje
        subjectRepository.delete(subject);
        Optional<Subject> deletedSub = subjectRepository.findById(subject.getId());
        assertFalse(deletedSub.isPresent());
    }

    //find by name - test
    @Test
    public void findSubjectbyName(){
        Department department = departmentRepository.save(new Department(28L, "KIS7", "K_IS7"));
        Subject subject = subjectRepository.save(new Subject(33L,"nst3",4,department));
        assertNotNull(subject);
        Optional<Subject> savedSub = subjectRepository.findByName(subject.getName());
        assertTrue(savedSub.isPresent());
        assertEquals(subject, savedSub.get());
    }
}
