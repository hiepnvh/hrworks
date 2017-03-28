package com.gtel.hrworks.delegate;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import com.gtel.hrworks.model.EmployeeRecord;

public class SetDefaultReasonDelegate implements JavaDelegate {

	private static final Logger LOGGER = Logger.getLogger(SetDefaultReasonDelegate.class.getName());
	
	private static final String default_reason = "1";
			
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		EmployeeRecord e = (EmployeeRecord) execution.getVariable("employee");
		e.setDefaultReason(default_reason);
		execution.setVariable("employee", e);
		LOGGER.info("Set default reason for employee!");
	}

}
