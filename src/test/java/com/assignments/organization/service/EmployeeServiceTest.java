package com.assignments.organization.service;

import com.assignments.organization.entity.Department;
import com.assignments.organization.entity.Employee;
import com.assignments.organization.repository.DepartmentRepository;
import com.assignments.organization.repository.EmployeeRepository;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

   @Mock
    EmployeeRepository employeeRepository;
   @Mock
   DepartmentRepository departmentRepository;

   EmployeeService underTest;
   private Employee emp1;
   private Employee emp2;
   private Department dept1;
    List<Employee> employeeList;

    /*@BeforeEach
    void setUp() {
        underTest=new EmployeeService(employeeRepository,departmentRepository);
        employeeList=new ArrayList<>();
        dept1=new Department(1L,"HR");
        emp1=new Employee(1L,"manvi","delhi","abc@gmail.com","657494343","active",dept1);
        emp2=new Employee(2L,"hunny","Bly","hunny@gmail.com","987494343","active",dept1);
        employeeList.add(emp1);
        employeeList.add(emp2);
    }*/
    @AfterEach
    void  tearDown() throws  Exception{
        emp1=emp2=null;
     /*   employeeList=null;*/
    }
    @Test
    void getEmployeeById() {
        Mockito.when(employeeRepository.findByEmpIdAndStatus(1L,"active"))
                .thenReturn(emp1);
        assertThat(underTest.getEmployeeById(emp1.getEmpId())).isEqualTo(emp1);
    }

    @Test
    void getAllEmployeeByDeptId() {
        Mockito.when(employeeRepository.findByDepartmentDeptIdAndStatus(1L,"active"))
                .thenReturn(employeeList);
        assertThat(underTest.getAllEmployeeByDeptId(dept1.getDeptId())).isEqualTo(employeeList);
    }


}