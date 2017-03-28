package com.gtel.hrworks.delegate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.gtel.hrworks.dao.HrWorksEmployeeRecordDAO;
import com.gtel.hrworks.model.HrWorksEmployeeRecord;
import com.gtel.hrworks.model.Department;
import com.gtel.hrworks.model.EmployeeRecord;

public class UpdateHRDatabaseDelegate implements JavaDelegate {

	private static final Logger LOGGER = Logger.getLogger(UpdateHRDatabaseDelegate.class.getName());
			
	public void execute(DelegateExecution execution) throws Exception {
		LOGGER.info("Update HR Database ...");
		// TODO Auto-generated method stub
		// LOGGER.info("1="+execution.getVariable("dept").toString());
		
		HrWorksEmployeeRecordDAO dao = new HrWorksEmployeeRecordDAO();
		List<HrWorksEmployeeRecord> eList = new ArrayList<HrWorksEmployeeRecord>();
		
		Department dept  = (Department) execution.getVariable("dept");
		List<EmployeeRecord> ls  = dept.getEmployeeList();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		for (EmployeeRecord e : ls) {
			HrWorksEmployeeRecord record = new HrWorksEmployeeRecord();
			 LOGGER.info(e.getDate());
			Date date = dateFormat.parse(e.getDate());
			 LOGGER.info(date.toString());
			record.set(HrWorksEmployeeRecord.DATE, date);
			record.set(HrWorksEmployeeRecord.EMPLOYEE_ID, e.getEmployee_id());
			record.set(HrWorksEmployeeRecord.EMPLOYEE_NAME, e.getEmployee_name());
			record.set(HrWorksEmployeeRecord.DEPT_NAME, e.getDept_name());
			record.set(HrWorksEmployeeRecord.COME_DEFAULT, e.getCome_default());
			record.set(HrWorksEmployeeRecord.LEAVE_DEFAULT, e.getLeave_default());
			record.set(HrWorksEmployeeRecord.COME_TIME, e.getCome());
			record.set(HrWorksEmployeeRecord.LEAVE_TIME, e.getLeave());
			record.set(HrWorksEmployeeRecord.COME_REASON, e.getCome_reason());
			record.set(HrWorksEmployeeRecord.LEAVE_REASON, e.getLeave_reason());
			record.set(HrWorksEmployeeRecord.LM_AUTO_APPROVED, e.getLm_auto_approved());
			record.set(HrWorksEmployeeRecord.HR_APPROVED, e.getHr_approved());
			eList.add(record);
//		LOGGER.info(e.getEmployee_id() + "|"+e.getCome_reason()+"|"+e.getLeave_reason()+"|"+e.getHr_approved());
		}
		
		try {
			dao.updateHrWorksEmployeeRecord(eList);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info(e.getMessage());
		}
		LOGGER.info("Update HR Database finish " + eList.size() + "(records)");
		
	}

}
