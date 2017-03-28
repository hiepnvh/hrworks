Ext.define(App.path('store.UserLimit'), {
			extend : 'Ext.data.Store',
			model:App.path('model.UserLimit'),
			storeId : 'UserLimit',
			proxy : {
				type : 'ajax',
				url : 'getuserlimit',
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
					root : 'user_limit'
				}
			}
		});