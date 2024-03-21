package nst.domaci.service;

import nst.domaci.converter.impl.DepartmentConverter;
import nst.domaci.domain.Department;
import nst.domaci.repository.DepartmentRepository;
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
public class DepartmentServiceTests {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentConverter departmentConverter;

    @MockBean
    private DepartmentRepository departmentRepository;

    //save - testovi
    @Test
    public void saveSuccessTest() throws Exception {
        Department department = new Department(29L, "KIS8", "K_IS8");
        when(departmentRepository.save(department)).thenReturn(department);
        //provera da li ga ima u bazi - ako ne onda moze da se unese u bazu
        when(departmentRepository.findByName(department.getName())).thenReturn(Optional.empty());

        Department dept = departmentConverter.toEntity(departmentService.save(departmentConverter.toDto(department)));
        assertNotNull(dept);
        assertEquals(dept,department);
    }

    @Test
    public void saveFailureTest(){
        Department department = new Department(30L, "KIS9", "K_IS9");
        when(departmentRepository.save(department)).thenReturn(department);
        //provera da li ga ima u bazi - ako ne onda moze da se unese u bazu
        when(departmentRepository.findByName(department.getName())).thenReturn(Optional.of(department));

        assertThrows(Exception.class, ()->{
            departmentService.save(departmentConverter.toDto(department));
        });
    }

    //get all - testovi
    @Test
    public void getAllSuccessTest(){
        Department dept1 = new Department(37L, "KIS16", "K_IS16");
        Department dept2 = new Department(38L, "KIS17", "K_IS17");

        List<Department> departments = Arrays.asList(dept1,dept2);
        when(departmentRepository.findAll()).thenReturn(departments);
        List<Department> deptsInDB = departmentService.getAll().stream().map(departmentConverter::toEntity).collect(Collectors.toList());
        assertEquals(departments.size(),deptsInDB.size());
        for(int i = 0;i<departments.size();i++){
            assertEquals(departments.get(i),deptsInDB.get(i));
        }
    }



    //delete - testovi

    @Test
    public void deleteSuccessTest() throws Exception{
        Department department = new Department(31L, "KIS10", "K_IS10");
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        departmentService.delete(department.getId());
        verify(departmentRepository,times(1)).delete(department);
    }

    @Test
    public void deleteFailureTest(){
        Department department = new Department(32L, "KIS11", "K_IS11");
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.empty());

        assertThrows(Exception.class, ()->{
            departmentService.delete(department.getId());
        });
    }
    //find by name - testovi

    @Test
    public void findByIdSuccessTest() throws Exception {
        Department department = new Department(33L, "KIS12", "K_IS12");
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        Department existed = departmentConverter.toEntity(departmentService.findById(department.getId()));
        assertNotNull(existed);
        assertEquals(department, existed);
    }

    @Test
    public void findByIdFailureTest() {
        Department department = new Department(34L, "KIS13", "K_IS13");
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> {
            departmentService.findById(department.getId());
        });}
}
