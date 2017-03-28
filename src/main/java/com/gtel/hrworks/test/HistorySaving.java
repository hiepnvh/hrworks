package com.gtel.hrworks.test;

import java.util.Set;
import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class HistorySaving implements TaskListener {
	
	private static String TASK_VARIABLE_PREFIX = "_";
	private static final Logger LOGGER = Logger.getLogger(HistorySaving.class.getName());

	public void notify(DelegateTask delegateTask) {
		String ownerCurrent = String.valueOf(delegateTask.getOwner()).trim();
		if(ownerCurrent == "null" || ownerCurrent == "")
		{
			/*The 1st, We Set Owner of Task*/
			LOGGER.info("The 1st, We Set Owner of Task");
			String owner = (String) delegateTask.getVariable("_owner");
			delegateTask.setOwner(owner);
		} else {
			/*The 2nd, We get all variables & set to Local*/
			LOGGER.info("The 2nd, We get all variables & set to Local");
			Set<String> variablesName = delegateTask.getVariableNames();
			for (String variableName : variablesName) {
				if(variableName.startsWith(TASK_VARIABLE_PREFIX))
					delegateTask.setVariableLocal(variableName, delegateTask.getVariable(variableName));
				}
		}
	}
}
