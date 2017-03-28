package com.gtel.hrworks.delegate;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.gtel.email.EmailClient;
import com.gtel.hrworks.dao.LdapDAO;
import com.gtel.hrworks.model.EmployeeRecord;

/**
 * @author anhta
 *
 */
public class NotifyManagerByEmail implements JavaDelegate {

	private static final Logger LOGGER = Logger.getLogger(NotifyManagerByEmail.class.getName());
	/**
	 * notify manager by Email and set time to send remail email or approve after period of time
	 * @param execution
	 * @return timeRepeatManagerEmail
	 * @return timeAutoApprove
	 * @see org.camunda.bpm.engine.delegate.JavaDelegate#execute(org.camunda.bpm.engine.delegate.DelegateExecution)
	 */
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		EmployeeRecord employee = (EmployeeRecord) execution.getVariable("employee");
		String recipient = LdapDAO.getManagerMail(employee.getCn());
		
		Email.sendEmail(execution, recipient);
		
		LOGGER.info("Send email to "+ recipient);
		
//		execution.setVariableLocal("timeRepeatManagerEmail", );
//		execution.setVariableLocal("timeAutoApprove", );
	}
}