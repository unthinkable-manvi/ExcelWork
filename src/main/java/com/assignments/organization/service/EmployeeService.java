package com.assignments.organization.service;

import com.assignments.organization.entity.Employee;
import com.assignments.organization.excelExport.EmployeeExcelExporter;
import com.assignments.organization.exception.ResourceIdNotFoundException;
import com.assignments.organization.repository.DepartmentRepository;
import com.assignments.organization.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
           @Autowired
           EmployeeRepository employeeRepository;
           @Autowired
           DepartmentRepository departmentRepository;

           @Autowired
          EmployeeExcelExporter employeeExcelExporter;



    public Employee getEmployeeById(@PathVariable Long id){
               return employeeRepository.findByEmpIdAndStatus(id, "active");
     	    }
           
           public List<Employee> getAllEmployeeByDeptId(@PathVariable Long  deptId ) {
       		return employeeRepository.findByDepartmentDeptIdAndStatus(deptId,"active");
       	   }
           public Employee addEmployee(@PathVariable Long deptId, @RequestBody Employee employee) {

       		return departmentRepository.findById(deptId).map(dept -> {
       			employee.setDepartment(dept);
       			employee.setStatus("active");
       			return employeeRepository.save(employee);
       		}).orElseThrow(() -> new ResourceIdNotFoundException("post Id is " + deptId + "is not found"));

        	}
           
           public Employee updateTopic(@RequestBody Employee employee ,@PathVariable Long empId){ 
               Employee emp= employeeRepository.findByEmpId(empId);
               if(employee.getEmpAddress()!=null) {
               emp.setEmpAddress(employee.getEmpAddress()) ;
               }
               if(employee.getEmpEmail()!=null) {
                   emp.setEmpEmail(employee.getEmpEmail());
                }
               if(employee.getEmpAddress()!=null) {
                   emp.setEmpAddress(employee.getEmpAddress()) ;
                }
               if(employee.getEmpName()!=null) {
                   emp.setEmpName(employee.getEmpName());
                }       
               employeeRepository.save(emp);
        	   return emp;
        		   
           }
           
           public Employee deleteEmployee(@PathVariable Long empId) {
           	return employeeRepository.findById(empId).map(emp -> {
       		    emp.setStatus("InActive");
       			return employeeRepository.save(emp);
       		}).orElseThrow(() -> new ResourceIdNotFoundException("post Id is " + empId + "is not found"));

       		
           	   
           }

   /* public void export(Long empId) {
        List<Employee> emp=employeeRepository.findAll();
        employeeExcelExporter.export(emp);

    }*/
}
