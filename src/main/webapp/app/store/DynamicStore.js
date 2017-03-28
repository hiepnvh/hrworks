Ext.define(App.path('store.DynamicStore'), {
			extend : 'Ext.data.Store',
			storeId : 'DynamicStore',
			proxy : {
				type : 'ajax',
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
					root : 'account_info'
				}
			}
		});