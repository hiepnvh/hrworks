package com.gtel.hrworks.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.gtel.hrworks.model.EmployeeRecord;

public class Department{
	
	private final static Logger LOGGER = Logger.getLogger("Department");
	
//	private String department_id;
	private String department_name;
	private List<EmployeeRecord> employeeList = new ArrayList<EmployeeRecord>();
	
//	public String getDepartment_id() {
//		return department_id;
//	}
//	public void setDepartment_id(String department_id) {
//		this.department_id = department_id;
//	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public List<EmployeeRecord> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<EmployeeRecord> employeeList) {
		this.employeeList = employeeList;
	}
	
	/**
	 * update employee list in dept (variable)
	 * @param emRC
	 */
	public synchronized void updateStaffList(EmployeeRecord emRC){
		for (EmployeeRecord employee : this.employeeList) {
			
			if(employee.getEmployee_id().equals(emRC.getEmployee_id()))
				employee.setEmployeeRecord(emRC);
			}
	}
	
//	public String toString() {
//	    return "Department [department_name=" + department_name + ", employeeList=" + employeeList + "]";
//	  }
}
