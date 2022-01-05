package com.assignments.organization.controller;

import com.assignments.organization.entity.Department;
import com.assignments.organization.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DepartmentController {
          
	@Autowired 
	private DepartmentService departmentService;
	
	@GetMapping
	public List<Department> getAllDepartment() {
		return  departmentService.getAllDepartment();
		
	}
	
	
	@PostMapping
	public Department  addDepartment(@RequestBody Department department) {
		return  departmentService.addDepartment(department);
		
	}
	
	@PutMapping("/{deptId}")
	public Department updateDepartment(@PathVariable Long deptId, @RequestBody  Department department) {
		return departmentService.updateDepartment(deptId,department);
		
	}
	
	
	
}
