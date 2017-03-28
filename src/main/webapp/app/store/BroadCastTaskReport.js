Ext.define(App.path('store.BroadCastTaskReport'), {
			extend : 'Ext.data.Store',
			model:App.path('model.BroadCastTaskReport'),
			storeId : 'BroadCastTaskReport',
			proxy : {
				type : 'ajax',
				url : 'getbroadcastreport',
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
					root : 'report_list'
				}
			}
		});