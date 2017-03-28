Ext.define(App.path('store.EmployeeRecord'), {
			extend : 'Ext.data.Store',
			model:App.path('model.EmployeeRecord'),
			storeId : 'EmployeeRecord',
			proxy : {
				type : 'ajax',
				url : 'getemployeerecords',
				actionMethods : {
					create : 'POST',
					read : 'POST',
					update : 'POST',
					destroy : 'POST'
				},
				extraParams : {
					format : 'json'
				},
				reader : {
					type : 'json',
					root : 'employee_list'
				}
			}
		});