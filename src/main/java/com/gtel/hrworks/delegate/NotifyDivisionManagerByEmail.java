package com.gtel.hrworks.delegate;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * @author anhta
 *
 */
public class NotifyDivisionManagerByEmail implements JavaDelegate {
	
	private static final Logger LOGGER = Logger.getLogger(NotifyDivisionManagerByEmail.class.getName());

	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("We send email here");
//		execution.setVariable("success", true);
	}

}
