Ext.define(App.path('store.BroadCastTask'), {
			extend : 'Ext.data.Store',
			model:App.path('model.BroadCastTask'),
			storeId : 'BroadCastTask',
			proxy : {
				type : 'ajax',
				url : 'getBroadCastTask',
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