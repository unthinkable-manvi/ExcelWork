package com.assignments.organization.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long deptId;
	private String deptName;

       @OneToMany(fetch = FetchType.LAZY)
	private List<Employee> employee=new ArrayList<>();

	public Department( String deptName){
		  this.deptName=deptName;
	}
	public Department(Long deptId, String deptName){
		this.deptName=deptName;
		this.deptId=deptId;
	}

	public Department(Long deptId) {
		this.deptId=deptId;
	}
}
