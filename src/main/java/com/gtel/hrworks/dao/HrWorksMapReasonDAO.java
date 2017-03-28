package com.gtel.hrworks.dao;

import java.util.List;

import com.bean.base.BeanFilter;
import com.gtel.hrworks.model.HrWorksEmployeeRecord;
import com.gtel.hrworks.model.HrWorksMapReason;

public class HrWorksMapReasonDAO {

	public List<HrWorksMapReason> getBean(BeanFilter filter) throws Exception {
		DbAdapter dba = new DbAdapter();
		List<HrWorksMapReason> beans = dba.getBeans(filter);
		dba.close();
		return beans;
	}

}
