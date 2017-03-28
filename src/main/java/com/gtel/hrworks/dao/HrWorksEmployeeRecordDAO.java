package com.gtel.hrworks.dao;

import java.util.List;

import com.bean.base.BeanFilter;
import com.gtel.hrworks.model.HrWorksEmployeeRecord;

public class HrWorksEmployeeRecordDAO {

	public List<HrWorksEmployeeRecord> getBean(BeanFilter filter) throws Exception {
		DbAdapter dba = new DbAdapter();
		List<HrWorksEmployeeRecord> beans = dba.getBeans(filter);
		dba.close();
		return beans;
	}
	
	public void updateHrWorksEmployeeRecord(HrWorksEmployeeRecord e) throws Exception {
		DbAdapter dba = new DbAdapter();
		dba.processBeans(e);
		dba.close();
	}
	
	public void updateHrWorksEmployeeRecord(List<HrWorksEmployeeRecord> eList) throws Exception {
		DbAdapter dba = new DbAdapter();
		dba.processBeans(eList);
		dba.close();
	}
	
	public static void main(String[] args) throws Exception {
		HrWorksEmployeeRecord record = new HrWorksEmployeeRecord();
		record.set(HrWorksEmployeeRecord.DEPT_NAME, "Cong nghe thong tin");
		HrWorksEmployeeRecordDAO dao = new HrWorksEmployeeRecordDAO();
		dao.updateHrWorksEmployeeRecord(record);
	}
}
