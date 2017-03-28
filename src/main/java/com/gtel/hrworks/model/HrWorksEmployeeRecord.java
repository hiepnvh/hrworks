package com.gtel.hrworks.model;

import java.util.Date;

import com.bean.annot.*;
import com.bean.base.Bean;
import com.bean.base.BeanProperty;

@Entity(name = "hrworks_employee_record")
public class HrWorksEmployeeRecord extends Bean {
	@Attribute(name = "action_date")
	@ExternalKey
	public static final BeanProperty<Date> DATE =  BeanProperty.dateType();
	@Attribute(name = "employee_id")
	@ExternalKey
	public static final BeanProperty<String> EMPLOYEE_ID =  BeanProperty.stringType();
	@Attribute(name = "employee_name")
	public static final BeanProperty<String> EMPLOYEE_NAME =  BeanProperty.stringType();
	@Attribute(name = "dept_name")
	public static final BeanProperty<String> DEPT_NAME =  BeanProperty.stringType();
	@Attribute(name = "come_default")
	public static final BeanProperty<String> COME_DEFAULT =  BeanProperty.stringType();
	@Attribute(name = "leave_default")
	public static final BeanProperty<String> LEAVE_DEFAULT =  BeanProperty.stringType();
	@Attribute(name = "come_time")
	public static final BeanProperty<String> COME_TIME =  BeanProperty.stringType();
	@Attribute(name = "leave_time")
	public static final BeanProperty<String> LEAVE_TIME =  BeanProperty.stringType();
	@Attribute(name = "come_reason")
	public static final BeanProperty<String> COME_REASON =  BeanProperty.stringType();
	@Attribute(name = "leave_reason")
	public static final BeanProperty<String> LEAVE_REASON =  BeanProperty.stringType();
	@Attribute(name = "lm_auto_approved")
	public static final BeanProperty<Boolean> LM_AUTO_APPROVED =  BeanProperty.boolType();
	@Attribute(name = "hr_approved")
	public static final BeanProperty<Boolean> HR_APPROVED =  BeanProperty.boolType();
	
	@Attribute(name = "status")
	public static final BeanProperty<String> STATUS =  BeanProperty.stringType();
}
