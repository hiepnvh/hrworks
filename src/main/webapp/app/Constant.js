Ext.define(App.path('Constant'), {
	alternateClassName : 'App.Constant',
	statics : {
	GetMenu : function(menuId)
	{
		switch(menuId)
		{
		case 11 : return 'user';
		case 12 : return 'usergroup';
		
		case 21 : return 'smsgwconnect';
		case 22 : return 'routing';
		case 23 : return 'cdr';
		
		case 31 : return 'contentprovider';
		case 32 : return 'operator';
		
		case 41 : return 'smsrequest';
		case 42 : return 'smsauto';
		
		case 51 : return 'subcriber';
		}
	},
	
		USER_TYPE_ADMIN : 1,
		USER_TYPE_AGENT : 2,
		USER_TYPE_GTEL : 4,
		
		// Event Type
		EVENT_TYPE_IDLE : 0,
		EVENT_TYPE_SUSPEND : 16,
		EVENT_TYPE_START : 2,
		EVENT_TYPE_FINISH : 4,
		EVENT_TYPE_CANCEL : 8,
		EVENT_TYPE_SCHEDULE : 1,
		EVENT_TYPE_PAUSE : 32,
		EVENT_TYPE_ACTIVATE : 64,
		EVENT_TYPE_RESUME : 128,
		
		// Task Status
		TASK_STATUS_IDLE : 0,
		TASK_STATUS_PENDING : 1,
		TASK_STATUS_INITIALZING : 2,
		TASK_STATUS_RUNNING : 4,
		TASK_STATUS_CANCELLED : 8,
		TASK_STATUS_FINISHED : 16,
		TASK_STATUS_SUSPENED : 32,
				
		// date from database
		ConvertDate : function(date_old) // string date
		{
			var dd = date_old.substring(0,2);
			var mm = ('JanFebMarAprMayJunJulAugSepOctNovDec').indexOf(date_old.substring(3,6))/3;
			var yyyy = date_old.substring(7,11);
			var date_new = new Date();
			date_new.setDate(dd);
			date_new.setMonth(mm);
			date_new.setFullYear(yyyy);
			return date_new;
		},
		FormatDate : function(date_old) // date
		{
			var date = new Date(date_old);

			var month_names_short = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];

			var dd = date.getDate();
			var mm = month_names_short[date.getMonth()];					
			var yyyy = date.getFullYear();
			
			if(dd<10){dd='0'+dd;}
			var date_new = dd+'-'+mm+'-'+yyyy;
			
			return date_new;				
		},
		GetDateNow : function()
		{
		    /*Get Date*/
			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth()+1; //January is 0!
			
			var yyyy = today.getFullYear();
			if(dd<10){dd='0'+dd;}
			if(mm<10){mm='0'+mm;} today = dd+'/'+mm+'/'+yyyy;
			return today;
			/**/
		},
		FormatToCurrency : function(num) {
			num = num.toString().replace(/\$|\,/g, '');
			if (isNaN(num))
				num = "0";
			sign = (num == (num = Math.abs(num)));
			num = Math.floor(num * 100 + 0.50000000001);
			cents = num % 100;
			num = Math.floor(num / 100).toString();
			if (cents < 10)
				cents = "0" + cents;
			for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
				num = num.substring(0, num.length - (4 * i + 3)) + ','
						+ num.substring(num.length - (4 * i + 3));
			return (((sign) ? '' : '-') + num);
//					return (((sign) ? '' : '-') + num + '.' + cents);
		},
		ReplaceAll : function(myString,oldString,repString) {  
			return myString.split(oldString).join(repString);
		},
		ReasonValue : function(type)
		{
			switch(type)
			{
			case 0 : return 'Bạn chưa chọn lý do!</option : return ';
			case 1 : return 'Lý do cá nhân';
			case 2 : return 'Quên XNVT';
			case 3 : return 'Nghỉ có hưởng lương chưa nộp đơn xin nghỉ';
			case 4 : return 'Nghỉ không hưởng lương chưa nộp đơn xin nghỉ';
			case 5 : return 'Đi công tác buổi sáng';
			case 6 : return 'Đi công tác buổi sáng quên XNVT';
			case 7 : return 'Đi công tác chưa nộp giấy phê duyệt';
			case 8 : return 'Đi đào tạo chưa nộp giấy phê duyệt';
			case 9 : return 'Lý do khác';
			}
		},
		filters : {
			ftype : 'filters',
			// encode and local configuration options defined previously
			// for easier reuse
			encode : false, // json encode the filter query
			local : true, // defaults to false (remote filtering)
			filters : [{
						type : 'boolean',
						dataIndex : 'visible'
					}]
		}
		
	}
});
