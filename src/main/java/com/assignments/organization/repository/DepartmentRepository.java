package com.assignments.organization.repository;
import com.assignments.organization.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
	public Department findByDeptId(Long deptId);

	public boolean existsDepartmentByDeptName(String deptName);

}
