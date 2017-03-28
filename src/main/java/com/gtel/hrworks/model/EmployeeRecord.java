package com.gtel.hrworks.model;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import org.camunda.bpm.engine.impl.util.json.JSONObject;

import com.base.filter.DateEqualFilter;
import com.base.filter.StringEqualFilter;
import com.bean.base.BeanFilter;
import com.gtel.hrworks.listener.DivisionDept;
import com.gtel.ldap.model.User;
import com.gtel.hrworks.dao.HrWorksEmployeeRecordDAO;
import com.gtel.hrworks.model.HrWorksEmployeeRecord;

public class EmployeeRecord extends User{
	
	private final static Logger LOGGER = Logger.getLogger(EmployeeRecord.class.getName());

	private String date;
	private String employee_id;
	private String employee_name;
	private String dept_name;
	private String come_default;
	private String leave_default;
	private String come;
	private String leave;
	private String come_reason;
	private String leave_reason;
	private Boolean hr_approved;
	private String come_other_text;
	private String leave_other_text;
	private String reject_reason;
	private Boolean lm_auto_approved;
	private String status;
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getCome_default() {
		return come_default;
	}
	public void setCome_default(String come_default) {
		this.come_default = come_default;
	}
	public String getLeave_default() {
		return leave_default;
	}
	public void setLeave_default(String leave_default) {
		this.leave_default = leave_default;
	}
	public String getCome() {
		return come;
	}
	public void setCome(String come) {
		this.come = come;
	}
	public String getLeave() {
		return leave;
	}
	public void setLeave(String leave) {
		this.leave = leave;
	}
	public Boolean getHr_approved() {
		return hr_approved;
	}
	public void setHr_approved(Boolean hr_approved) {
		this.hr_approved = hr_approved;
	}
	public String getCome_reason() {
		return come_reason;
	}
	public void setCome_reason(String come_reason) {
		this.come_reason = come_reason;
	}
	public String getLeave_reason() {
		return leave_reason;
	}
	public void setLeave_reason(String leave_reason) {
		this.leave_reason = leave_reason;
	}
	public void setDefaultReason(String default_reason){
		this.come_reason = default_reason;
		this.leave_reason = default_reason;
	}
	
//	public String toString() {
//	    return "EmployeeRecord "
//	    		+ "[date=" + date 
//	    		+ ", employee_id=" + employee_id 
//	    		+ ", employee_name=" + employee_name 
//	    		+ ", dept_name=" + dept_name 
//	    		+ ", come_default=" + come_default 
//	    		+ ", leave_default=" + leave_default 
//	    		+ ", come=" + come 
//	    		+ ", leave=" + leave 
//	    		+ ", come_reason=" + come_reason
//	    		+ ", leave_reason=" + leave_reason
//	    		+ ", hr_approved=" + hr_approved +
//	    		"]";
//	  }
	
	public String getCome_other_text() {
		return come_other_text;
	}
	public void setCome_other_text(String come_other_text) {
		this.come_other_text = come_other_text;
	}
	public String getLeave_other_text() {
		return leave_other_text;
	}
	public void setLeave_other_text(String leave_other_text) {
		this.leave_other_text = leave_other_text;
	}
	public String getReject_reason() {
		return reject_reason;
	}
	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}
	public Boolean getLm_auto_approved() {
		return lm_auto_approved;
	}
	public void setLm_auto_approved(Boolean lm_auto_approved) {
		this.lm_auto_approved = lm_auto_approved;
	}
	
//	public void normalizeReason(){
//		if(this.come_other_text!=null)
//			this.come_reason = this.come_other_text;
//		
//		if(this.leave_other_text!=null)
//			this.leave_reason = this.leave_other_text;
//	}
	
	public void setEmployeeRecord(EmployeeRecord emRC){
		if(emRC.getCome_other_text()!=null)
			this.setCome_reason(emRC.getCome_other_text());
		else
			this.setCome_reason(emRC.getCome_reason());
		
		if(emRC.getLeave_other_text()!=null)
			this.setLeave_reason(emRC.getLeave_other_text());
		else
			this.setLeave_reason(emRC.getLeave_reason());
		
		this.setLm_auto_approved(emRC.getLm_auto_approved());
	}
	
	
	public void save() throws Exception{
		HrWorksEmployeeRecordDAO dao = new HrWorksEmployeeRecordDAO();
		BeanFilter filter = new BeanFilter(HrWorksEmployeeRecord.class);
		filter.setFilter(HrWorksEmployeeRecord.EMPLOYEE_ID, new StringEqualFilter(employee_id));
		LOGGER.info(date);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		filter.setFilter(HrWorksEmployeeRecord.DATE, new DateEqualFilter(sdf.parse(date)));
		HrWorksEmployeeRecord record = dao.getBean(filter).get(0);
		record.set(HrWorksEmployeeRecord.COME_REASON, come_reason);
		record.set(HrWorksEmployeeRecord.LEAVE_REASON, leave_reason);
		record.set(HrWorksEmployeeRecord.HR_APPROVED,hr_approved);
		record.set(HrWorksEmployeeRecord.LM_AUTO_APPROVED,lm_auto_approved);
		record.set(HrWorksEmployeeRecord.STATUS,status);
		dao.updateHrWorksEmployeeRecord(record);
	}
	
	public void updateReason(String status) throws Exception{
		HrWorksEmployeeRecordDAO dao = new HrWorksEmployeeRecordDAO();
		BeanFilter filter = new BeanFilter(HrWorksEmployeeRecord.class);
		filter.setFilter(HrWorksEmployeeRecord.EMPLOYEE_ID, new StringEqualFilter(employee_id));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		filter.setFilter(HrWorksEmployeeRecord.DATE, new DateEqualFilter(sdf.parse(date)));
		HrWorksEmployeeRecord record = dao.getBean(filter).get(0);
		record.set(HrWorksEmployeeRecord.COME_REASON, status);
		record.set(HrWorksEmployeeRecord.LEAVE_REASON, status);
//		record.set(HrWorksEmployeeRecord.HR_APPROVED,hr_approved);
//		record.set(HrWorksEmployeeRecord.LM_AUTO_APPROVED,autoapproved);
		dao.updateHrWorksEmployeeRecord(record);
	}
	
	public void updateLmAutoApprove(Boolean autoapproved) throws Exception{
		HrWorksEmployeeRecordDAO dao = new HrWorksEmployeeRecordDAO();
		BeanFilter filter = new BeanFilter(HrWorksEmployeeRecord.class);
		filter.setFilter(HrWorksEmployeeRecord.EMPLOYEE_ID, new StringEqualFilter(employee_id));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		filter.setFilter(HrWorksEmployeeRecord.DATE, new DateEqualFilter(sdf.parse(date)));
		HrWorksEmployeeRecord record = dao.getBean(filter).get(0);
//		record.set(HrWorksEmployeeRecord.COME_REASON, status);
//		record.set(HrWorksEmployeeRecord.LEAVE_REASON, status);
//		record.set(HrWorksEmployeeRecord.HR_APPROVED,hr_approved);
		record.set(HrWorksEmployeeRecord.LM_AUTO_APPROVED,autoapproved);
		dao.updateHrWorksEmployeeRecord(record);
	}
	
	public void updateStatus(String status) throws Exception{
		HrWorksEmployeeRecordDAO dao = new HrWorksEmployeeRecordDAO();
		BeanFilter filter = new BeanFilter(HrWorksEmployeeRecord.class);
		filter.setFilter(HrWorksEmployeeRecord.EMPLOYEE_ID, new StringEqualFilter(employee_id));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		filter.setFilter(HrWorksEmployeeRecord.DATE, new DateEqualFilter(sdf.parse(date)));
		HrWorksEmployeeRecord record = dao.getBean(filter).get(0);
//		record.set(HrWorksEmployeeRecord.COME_REASON, status);
//		record.set(HrWorksEmployeeRecord.LEAVE_REASON, status);
//		record.set(HrWorksEmployeeRecord.HR_APPROVED,hr_approved);
		record.set(HrWorksEmployeeRecord.STATUS,status);
		dao.updateHrWorksEmployeeRecord(record);
	}
	
	public String toJSObjectString(){
		JSONObject jsObject = new JSONObject(this);
		return jsObject.toString();
	}

}
