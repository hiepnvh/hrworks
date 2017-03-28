package com.gtel.hrworks.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.base.filter.DateEqualFilter;
import com.base.filter.StringEqualFilter;
import com.bean.base.BeanFilter;
import com.gtel.hrworks.dao.HrWorksEmployeeRecordDAO;
import com.gtel.hrworks.model.HrWorksEmployeeRecord;
import com.gtel.hrworks.dao.LdapDAO;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		LdapDAO dao = new LdapDAO();
//		System.out.println(dao.getSAMAccountName("HNL1556"));
//		System.out.println(dao.getCn("HNL1556"));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("13/03/2016");
		HrWorksEmployeeRecordDAO dao = new HrWorksEmployeeRecordDAO();
		HrWorksEmployeeRecord r = new HrWorksEmployeeRecord();
		r.set(HrWorksEmployeeRecord.DATE, date);
		
//		BeanFilter filter = new BeanFilter(HrWorksEmployeeRecord.class);
//		filter.setFilter(HrWorksEmployeeRecord.EMPLOYEE_ID, new StringEqualFilter("BPM1"));
//		filter.setFilter(HrWorksEmployeeRecord.DATE, new DateEqualFilter(date));
//		HrWorksEmployeeRecord record = dao.getBean(filter).get(0);
//		
////		HrWorksEmployeeRecord record = new HrWorksEmployeeRecord();
////		record.set(HrWorksEmployeeRecord.DATE, date);
		System.out.println(r.get(HrWorksEmployeeRecord.DATE));
		
//		dao.updateHrWorksEmployeeRecord(record);

	}

}
