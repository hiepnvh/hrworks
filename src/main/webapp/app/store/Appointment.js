Ext.define(App.path('store.Appointment'), {
			extend : 'Ext.data.Store',
			model:App.path('model.Appointment'),
			storeId : 'Appointment',
			proxy : {
				type : 'ajax',
				url : 'getAppointment',
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
					root : 'child_list'
				}
			}
		});