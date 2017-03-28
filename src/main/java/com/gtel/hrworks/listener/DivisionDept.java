package com.gtel.hrworks.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import com.gtel.hrworks.model.Department;
import com.gtel.hrworks.model.EmployeeRecord;

public class DivisionDept implements ExecutionListener {
    
    private final static Logger LOGGER = Logger.getLogger(DivisionDept.class.getName());
   
	public void notify(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Department dept  = (Department) execution.getVariable("dept");
//		Moi department tro thanh 1 list chua cac nhan vien
		execution.setVariable("employeeList", dept.getEmployeeList());
		LOGGER.info(dept.getEmployeeList().get(0).getEmployee_id());
	}
   
  }
