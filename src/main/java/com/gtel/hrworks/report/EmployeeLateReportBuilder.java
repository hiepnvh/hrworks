package com.gtel.hrworks.report;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.base.filter.DateEqualFilter;
import com.base.filter.DateGreaterThanOrEqualFilter;
import com.base.filter.DateLessThanOrEqualFilter;
import com.base.filter.Filter;
import com.base.filter.FilterCriteria;
import com.base.filter.StringLikeFilter;
import com.bean.base.BeanFilter;
import com.gtel.hrworks.dao.HrWorksEmployeeRecordDAO;
import com.gtel.hrworks.dao.HrWorksMapReasonDAO;
import com.gtel.hrworks.model.HrWorksEmployeeRecord;
import com.gtel.hrworks.model.HrWorksMapReason;

public class EmployeeLateReportBuilder extends ReportBuilder {
	
	Date _start_date;
	Date _end_date;
	
	public EmployeeLateReportBuilder(Date _start_date, Date _end_date) {
		this._start_date = _start_date;
		this._end_date = _end_date;
	}

	protected static final Logger LOGGER = Logger.getLogger(EmployeeLateReportBuilder.class.getName());

	@Override
	protected JRDataSource createDataSource() {
		try {
			FilterCriteria dateFilter = new FilterCriteria();
			
			BeanFilter _emFilter = new BeanFilter(HrWorksEmployeeRecord.class);
			if (_start_date!=null)
				dateFilter.addFilter(new DateGreaterThanOrEqualFilter(_start_date));
			if (_end_date!=null)
				dateFilter.addFilter(new DateLessThanOrEqualFilter(_end_date));
				
			_emFilter.setFilterCriteria(HrWorksEmployeeRecord.DATE, dateFilter);
			
			HrWorksEmployeeRecordDAO dao = new HrWorksEmployeeRecordDAO();
			List<HrWorksEmployeeRecord> emRCs = dao.getBean(_emFilter);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
			List<Data> dataList = new ArrayList<Data>();
			
			for(HrWorksEmployeeRecord emRC : emRCs){
								
				Data data = new Data();
				data.date = ((dateFormat.format(emRC.get(HrWorksEmployeeRecord.DATE))));
				data.employee_id = emRC.get(HrWorksEmployeeRecord.EMPLOYEE_ID);
				data.employee_name = emRC.get(HrWorksEmployeeRecord.EMPLOYEE_NAME);
				data.dept_name = emRC.get(HrWorksEmployeeRecord.DEPT_NAME);
				data.come_default = emRC.get(HrWorksEmployeeRecord.COME_DEFAULT).substring(0, 5);
				data.leave_default = emRC.get(HrWorksEmployeeRecord.LEAVE_DEFAULT).substring(0, 5);
				data.come = emRC.get(HrWorksEmployeeRecord.COME_TIME).substring(0, 5);
				data.leave = emRC.get(HrWorksEmployeeRecord.LEAVE_TIME).substring(0, 5);
				data.come_reason = emRC.get(HrWorksEmployeeRecord.COME_REASON);
				data.leave_reason = emRC.get(HrWorksEmployeeRecord.LEAVE_REASON);
				data.lm_auto_approved = emRC.get(HrWorksEmployeeRecord.LM_AUTO_APPROVED);
				data.hr_approved = emRC.get(HrWorksEmployeeRecord.HR_APPROVED);
				data.status = emRC.get(HrWorksEmployeeRecord.STATUS);

				dataList.add(data);
			}
			
			return new JRBeanCollectionDataSource(dataList);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	public class Data {
		private String date;
		private String employee_id;
		private String employee_name;
		private String dept_name;
		private String come_default;
		private String leave_default;
		
		private String come;
		private String come_approve;
		private String come_reason;
		
		private String leave;
		private String leave_approve;
		private String leave_reason;
		
		private Boolean lm_auto_approved;
		private Boolean hr_approved;
		private String status;
		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
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
		public Boolean getHr_approved() {
			return hr_approved;
		}
		public void setHr_approved(Boolean hr_approved) {
			this.hr_approved = hr_approved;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getCome_approve() {
			return come_approve;
		}
		public void setCome_approve(String come_approve) {
			this.come_approve = come_approve;
		}
		public String getLeave_approve() {
			return leave_approve;
		}
		public void setLeave_approve(String leave_approve) {
			this.leave_approve = leave_approve;
		}
		public Boolean getLm_auto_approved() {
			return lm_auto_approved;
		}
		public void setLm_auto_approved(Boolean lm_auto_approved) {
			this.lm_auto_approved = lm_auto_approved;
		}
	}
	
	@Override
	protected JasperReportBuilder buildReport() { 
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		DynamicReports.report();
		return  report()
				.setTemplate(ReportTemplates.reportTemplate)
				.ignorePagination()
				.ignorePageWidth()
				  .columns(
				  	col.column("Ngày", "date", type.stringType()).setWidth(80),
				  	col.column("Mã nhân viên", "employee_id", type.stringType()).setWidth(80),
				  	col.column("Họ tên",   "employee_name",  type.stringType()).setWidth(80),
				  	col.column("Ban/Phòng", "dept_name", type.stringType()).setWidth(100),
				  	col.column("Đến", "come_default", type.stringType()).setWidth(50),
				  	col.column("Về", "leave_default", type.stringType()).setWidth(80),
				  	
				  	col.column("Thời gian đến", "come", type.stringType()).setWidth(100),
				  	col.column("Phê duyệt", "come_approve", type.stringType()).setWidth(80),
				  	col.column("Lý do", "come_reason", type.stringType()).setWidth(80),
				  	
				  	col.column("Thời gian về", "leave", type.stringType()).setWidth(50),
				  	col.column("Phê duyệt", "leave_approve", type.stringType()).setWidth(80),
				  	col.column("Lý do", "leave_reason", type.stringType()).setWidth(80),
				  	
				  	col.column("LM tự động phê duyệt", "lm_auto_approved", type.booleanType()).setWidth(80),
				  	col.column("Trạng thái", "status", type.stringType()).setWidth(80)
				  	
					)
				    .title(ReportTemplates.createTitleComponent("Báo cáo giờ giấc đi/về nhân viên"))
				    .title(ReportTemplates.createTextField("Từ ngày:" + df.format(_start_date).toString()))
				    .title(ReportTemplates.createTextField("Đến ngày:" + df.format(_end_date).toString()));
	}
}