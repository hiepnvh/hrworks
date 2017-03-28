package com.gtel.hrworks.listener;

import java.util.Set;
import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class OwnerSaving implements TaskListener {

	private static final Logger LOGGER = Logger.getLogger(OwnerSaving.class.getName());

	public void notify(DelegateTask delegateTask) {
		/* The 1st, We Set Owner of Task */
		LOGGER.info("The 1st, We Set Owner of Task");
		String owner = (String) delegateTask.getVariable("owner");
		delegateTask.setOwner(owner);
	}
}
