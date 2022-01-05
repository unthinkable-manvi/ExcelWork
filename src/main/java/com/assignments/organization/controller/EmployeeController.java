package com.assignments.organization.controller;

import com.assignments.organization.entity.Employee;
import com.assignments.organization.excelExport.EmployeeExcelExporter;
import com.assignments.organization.repository.EmployeeRepository;
import com.assignments.organization.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class EmployeeController {

	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeRepository employeeRepository;
    
	@GetMapping("employee/{id}")
	public Employee getEmployeeById(@PathVariable Long id){
         return  employeeService.getEmployeeById(id);
	}
	
	@GetMapping("department/{deptId}/employe")
	public List<Employee> getAllEmployeeByDeptId(@PathVariable Long  deptId ) {
		return employeeService.getAllEmployeeByDeptId(deptId); 
	}
	@GetMapping("/emp/export/excel")
	public void export(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue="attachment; filename=Employee_Info.xlsx";
		response.setHeader(headerKey, headerValue);
		List<Employee> employeeList=employeeRepository.findAll();
		EmployeeExcelExporter emp=new EmployeeExcelExporter(employeeList);
		emp.export(response);
	}

	
	@PostMapping("dept/{deptId}/employee")
	public Employee addEmployee(@PathVariable Long deptId, @RequestBody Employee employee) {
            return employeeService.addEmployee(deptId, employee);
	}
	
	@PutMapping("employee/{empId}") 
	public Employee updateTopic(@RequestBody Employee employee ,@PathVariable Long empId){ 
       return employeeService.addEmployee(empId, employee);
     }
    @DeleteMapping("/employee/{empId}")
    public Employee deleteEmployee(@PathVariable Long empId) {
    	return employeeService.deleteEmployee(empId);
    	   
    }


}
