package com.assignments.organization.service;
import com.assignments.organization.entity.Department;
import com.assignments.organization.entity.Employee;
import com.assignments.organization.exception.ResourceIdNotFoundException;
import com.assignments.organization.repository.DepartmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {


    @Mock
    private DepartmentRepository departmentRepository;
    private DepartmentService underTest;
    private Department dept1;
    private Department dept2;
    List<Department> departmentList;

    @BeforeEach
    void setUp() {
        underTest=new DepartmentService(departmentRepository);
        departmentList=new ArrayList<>();
        dept1=new Department(1L,"HR");
        dept2=new Department(2L,"IT");
        departmentList.add(dept1);
        departmentList.add(dept2);
    }

    @AfterEach
    void  tearDown() throws  Exception{
        dept1=null;
        dept2=null;
        departmentList=null;
    }

    @Test
    void getAllDepartment() {
        underTest.getAllDepartment();
        verify(departmentRepository).findAll();
    }

    @Test
    void addDepartment() {
        underTest.addDepartment(dept1);
        ArgumentCaptor<Department> departmentArgumentCaptor=
                ArgumentCaptor.forClass(Department.class);
        verify(departmentRepository).save(departmentArgumentCaptor.capture());
        Department capturedDepartment=departmentArgumentCaptor.getValue();
        assertThat(capturedDepartment).isEqualTo(dept1);
    }

    @Test
    void updateDepartment() {

        dept1.setDeptName("legal");
        departmentRepository.save(dept1);
        ArgumentCaptor<Department> departmentArgumentCaptor=
                ArgumentCaptor.forClass(Department.class);
        verify(departmentRepository).save(departmentArgumentCaptor.capture());
        Department capturedDepartment=departmentArgumentCaptor.getValue();

        assertThat(capturedDepartment).isEqualTo(dept1);

    }
}