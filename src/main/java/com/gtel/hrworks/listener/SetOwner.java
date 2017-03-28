package com.gtel.hrworks.listener;

import java.util.Set;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class SetOwner implements TaskListener {
    
    private final static Logger LOGGER = Logger.getLogger(SetOwner.class.getName());
   
	public void notify(DelegateTask delegateTask) {
		/*The 1st, We Set Owner of Task*/
		String owner = (String) delegateTask.getVariable("owner");
		delegateTask.setOwner(owner);
		LOGGER.info("owner=" + delegateTask.getOwner());
		
		/*The 2nd, We get all variables & set to Local*/
		Set<String> variablesName = delegateTask.getVariableNames();
		for (String variableName : variablesName) {
			delegateTask.setVariableLocal(variableName, delegateTask.getVariable(variableName));
		}
	}
   
  }
