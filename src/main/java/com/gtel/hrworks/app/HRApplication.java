package com.gtel.hrworks.app;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;

@ProcessApplication("working-time-checking")
public class HRApplication extends ServletProcessApplication {
}
