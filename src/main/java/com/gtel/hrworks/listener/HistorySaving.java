package com.gtel.hrworks.listener;

import java.util.Set;
import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class HistorySaving implements TaskListener {

	private static String TASK_VARIABLE_PREFIX = "_";
	private static final Logger LOGGER = Logger.getLogger(HistorySaving.class.getName());

	public void notify(DelegateTask delegateTask) {
		/* The 2nd, We get all variables & set to Local */
		LOGGER.info("The 2nd, We get all variables & set to Local");
		Set<String> variablesName = delegateTask.getVariableNames();
		for (String variableName : variablesName) {
			if (variableName.startsWith(TASK_VARIABLE_PREFIX))
				delegateTask.setVariableLocal(variableName, delegateTask.getVariable(variableName));
		}
	}
}
