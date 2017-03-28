function convert_to_ShortDate(v, record){
	return v.substring(0, 10); 
}

function convert_to_ShortTime(v, record){
	return v.substring(0, 5); 
}

function convert_come_reason_id(v, record){
	return App.Constant.ReasonValue(v);
}

function convert_leave_reason_id(v, record){
	return App.Constant.ReasonValue(v);
}

Ext.define(App.path('model.EmployeeRecord'), {
    extend: 'Ext.data.Model',
    fields:[
//			'action_date',
			{name:'action_date',convert:convert_to_ShortDate},
			'employee_id',
			'employee_name',
			'dept_name',
			{name:'come_default',convert:convert_to_ShortTime},
			{name:'leave_default',convert:convert_to_ShortTime},
			{name:'come_time',convert:convert_to_ShortTime},
			{name:'leave_time',convert:convert_to_ShortTime},
			'come_reason',
			'leave_reason',
//			{name:'come_reason_id',convert:convert_come_reason_id},
//			{name:'leave_reason_id',convert:convert_leave_reason_id},
			'lm_auto_approved',
			'status'
			]
});