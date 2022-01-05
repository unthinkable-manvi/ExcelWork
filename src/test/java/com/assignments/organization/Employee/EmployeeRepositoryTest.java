package com.assignments.organization.Employee;


import com.assignments.organization.entity.Department;
import com.assignments.organization.entity.Employee;
import com.assignments.organization.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository  underTest;



   /* private Long empId;
    private String empName;
    private String empAddress;
    private String empEmail;
    private String empPhonenum;
    private String status;*/
    @Test
    void  whenSaved_thenFindByEmpIdAndStatus(){
        underTest.save(
                new Employee(
                        1L,
                        "manvi",
                        "Delhi",
                        "manvi@gmail.com",
                        "467579748743",
                        "active"

                )
        );
        assertThat(underTest.findByEmpIdAndStatus(1L,"active")).isNotNull();

    }
    @Test
    void whenSaved_thenFindByEmpId(){
        underTest.save(
           new Employee(
                1L,
                "hunny",
                "Delhi",
                "hunny@gmail.com",
                "9879748743",
                "active"
        )
        );
        assertThat(underTest.findByEmpId(1L)).isNotNull();
    }

    @Test
    void whenSaved_thenFindByDepartmentDeptIdAndStatus(){
            underTest.save(
                    new Employee(
                            1L,
                            "hunny",
                            "Delhi",
                            "hunny@gmail.com",
                            "9879748743",
                            "active",
                              new Department(2L)
                    )
            );
        assertThat(underTest.findByDepartmentDeptIdAndStatus(2L,"active")).isNotNull();
    }
}