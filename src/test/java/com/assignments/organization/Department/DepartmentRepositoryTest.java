package com.assignments.organization.Department;
import com.assignments.organization.entity.Department;
import com.assignments.organization.repository.DepartmentRepository;
import com.assignments.organization.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfExistsDepartmentByDeptName(){
        String name="manvi";
        Department dept=new Department(
                "manvi"
        );
        underTest.save(dept);
        boolean expected=underTest.existsDepartmentByDeptName(name);
        System.out.println(expected);
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfDepartmentDoesNotExistsByDeptName(){
        String name="manvi";
        boolean expected=underTest.existsDepartmentByDeptName(name);
        System.out.println(expected);
        assertThat(expected).isFalse();
    }


}