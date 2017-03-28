Ext.define(App.path('store.Schedule'), {
			extend : 'Ext.data.Store',
			model:App.path('model.Schedule'),
			storeId : 'Schedule',
			proxy : {
				type : 'ajax',
				url : 'getSchedule',
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
					root : 'schedule'
				}
			}
		});