package com.assignments.organization.repository;
import com.assignments.organization.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	public Employee findByEmpId(Long empId);
	public Employee findByEmpIdAndStatus(Long Id,String status);

	public List<Employee> findByDepartmentDeptIdAndStatus(Long deptId,String status);

}
