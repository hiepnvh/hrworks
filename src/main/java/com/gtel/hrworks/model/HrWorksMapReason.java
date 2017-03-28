package com.gtel.hrworks.model;



import java.util.Date;

import com.bean.annot.*;
import com.bean.base.Bean;
import com.bean.base.BeanProperty;



@Entity(name = "hrworks_map_reason")
public class HrWorksMapReason extends Bean {
	@Attribute(name = "reason_id")
	public static final BeanProperty<Integer> REASON_ID =  BeanProperty.integerType();
	@Attribute(name = "value")
	public static final BeanProperty<String> VALUE =  BeanProperty.stringType();
}
