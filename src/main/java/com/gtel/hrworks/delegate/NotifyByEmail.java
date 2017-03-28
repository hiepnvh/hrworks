package com.gtel.hrworks.delegate;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import com.gtel.hrworks.model.EmployeeRecord;

public class NotifyByEmail implements JavaDelegate {

	private static final Logger LOGGER = Logger.getLogger(NotifyByEmail.class.getName());
			
	/** Notifying email to employees when they have task 
	 * @see org.camunda.bpm.engine.delegate.JavaDelegate#execute(org.camunda.bpm.engine.delegate.DelegateExecution)
	 */
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		EmployeeRecord employee = (EmployeeRecord) execution.getVariable("employee");
		String recipient =  employee.getMail();
		
		if(employee.getReject_reason() != null){
			execution.setVariableLocal("content", execution.getVariableLocal("content")
					+ "</br><b>" + "From Line Manager:" +employee.getReject_reason() + "</b>");
		}
		
		Email.sendEmail(execution, recipient);
	}
}