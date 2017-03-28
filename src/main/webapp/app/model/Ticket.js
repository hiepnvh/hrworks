function convert_new_user_type(v, record){
	switch(v)
		{
			case 1 : return 'Admin'; break;
			case 2 : return 'Agent'; break;
			case 4 : return 'Gtel'; break;
		}
}

Ext.define(App.path('model.Ticket'), {
    extend: 'Ext.data.Model',
    fields:[
    		  'ticket_id',
			  'create_date',
			  'status',
			  'create_user_id',
			  'approval_user_id',
			  {name:'new_user_type',convert:convert_new_user_type},
			  'new_user_value',
			  'new_info_id',
			  
			  'staff_id',
			  'create_date_detail',
			  'user_id',
			  'employee_code',
			  'name',
			  'email',
			  'mobile_phone',
			  'position',
			  'supervisor_id',
			  'region_manager_id',
			  'office_manager_id',
			  'active',
			  
			  'agent_id',
			  'create_date',
			  'agent_code',
			  'staff_code',
			  'agent_name',
			  'user_id',
			  'active',
			  'busines_address',
			  'business_district',
			  'business_province',
			  'stock_address',
			  'stock_province',
			  'stock_district',
			  'contact_email',
			  'contact_phone',
			  'contact_mobile',
			  'contact_name',
			  'contact_fax',
			  'tax_code',
			  'stk',
			  'contact_dob',
			  'agent_type',
			  'parent_agent_id',
			  'picture_link1',
			  'picture_link2',
			  'picture_link3',
			  'personal_id',
			  'business_type',
			  'shop_type',
			  'credit_limit',
			  'data'
			]
});