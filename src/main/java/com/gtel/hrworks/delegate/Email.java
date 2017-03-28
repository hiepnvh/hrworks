package com.gtel.hrworks.delegate;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import com.gtel.email.EmailClient;
import com.gtel.hrworks.conf.ServerConfig;

public class Email {
	
	private static final Logger LOGGER = Logger.getLogger(Email.class.getName());
	
	/**
	 * Creating emailHelper to send email with param input from, subject, content, recipient
	 * @param execution
	 * @param recipient
	 * @return
	 */
	public static boolean sendEmail(DelegateExecution execution, String recipient){
		try {
			// TODO Auto-generated method stub
			if(ServerConfig.get_SEND_EMAIL())	{
				EmailClient email = new EmailClient();
				
				String from = execution.getVariable("from").toString();
				String subject = execution.getVariable("subject").toString();
				String content = execution.getVariable("content").toString();
				
				
				email.sendEmail(from, subject, content, recipient);
			}
			
			LOGGER.info("Send email to "+ recipient);
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
}
