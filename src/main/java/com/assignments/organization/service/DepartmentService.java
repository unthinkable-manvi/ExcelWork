package com.assignments.organization.service;

import com.assignments.organization.entity.Department;
import com.assignments.organization.exception.ResourceIdNotFoundException;
import com.assignments.organization.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentService {

	
	@Autowired
	private DepartmentRepository departmentRepository;


	public List<Department> getAllDepartment() {
		return  departmentRepository.findAll();
	}

	public Department addDepartment(Department department) {
		return departmentRepository.save(department);
	}

	public Department updateDepartment(Long deptId, Department department) {
		return departmentRepository.findById(deptId).map(dept->{
            dept.setDeptName(department.getDeptName());			
			   return departmentRepository.save(dept);
		}).orElseThrow(()->new ResourceIdNotFoundException("deapartment id "+deptId+" not found"));
		
	}

		
		
	
	
	
}
