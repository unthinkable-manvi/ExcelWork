package com.assignments.organization.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;
	private String empName;
	private String empAddress;
	private String empEmail;
	private String status;
	private String empPhonenum;

	
	  @ManyToOne (cascade = CascadeType.ALL)
	  @JoinColumn(name="deptId",referencedColumnName = "deptId")
	  private Department department;


	public Employee(long empId, String empName, String empAddress, String empEmail, String empPhonenum,String status) {
		this.empId=empId;
		this.empName=empName;
		this.empAddress=empAddress;
		this.empEmail=empEmail;
		this.empPhonenum=empPhonenum;
		this.status=status;
	}
}
