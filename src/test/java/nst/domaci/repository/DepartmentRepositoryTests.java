package nst.domaci.repository;

import nst.domaci.domain.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DepartmentRepositoryTests {

    @Autowired
    private DepartmentRepository departmentRepository;

    //save - test
    @Test
    public void saveDepartmentTest(){
        Department department = departmentRepository.save(new Department(22L, "KIS1", "K_IS"));
        //provera da nije null
        assertNotNull(department);
    }



    //getall
    @Test
    public void getAllDepartmentsTest(){
        List<Department> departments = departmentRepository.findAll();
        assertNotNull(departments);
    }

    //delete

    @Test
    public void deleteDepartmentTest(){
        Department department = departmentRepository.save(new Department(23L, "KIS2", "K_IS2"));
        //provera da nije null
        assertNotNull(department);
        //brisanje
        departmentRepository.delete(department);
        Optional<Department> deletedDept = departmentRepository.findById(department.getId());
        assertFalse(deletedDept.isPresent());
    }

    //findbyname
    @Test
    public void findDepartmentByNameTest(){
        Department department = departmentRepository.save(new Department(24L, "KIS3", "K_IS3"));
        //provera da nije null
        assertNotNull(department);
        Optional<Department> savedDept = departmentRepository.findByName(department.getName());
        assertTrue(savedDept.isPresent());
        //provera jesu li to isti departmenti
        assertEquals(department,savedDept.get());
    }


}
