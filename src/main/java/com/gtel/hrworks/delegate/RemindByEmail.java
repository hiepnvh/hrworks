package com.gtel.hrworks.delegate;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import com.gtel.hrworks.model.EmployeeRecord;

public class RemindByEmail implements JavaDelegate {
	
	private static final Logger LOGGER = Logger.getLogger(RemindByEmail.class.getName());
	
	
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		EmployeeRecord employee = (EmployeeRecord) execution.getVariable("employee");
		String recipient =  employee.getMail();
		
		Email.sendEmail(execution, recipient);
	}

}
