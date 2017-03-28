Ext.define(App.path('store.FileNameXml'), {
			extend : 'Ext.data.Store',
			fields : ['msisdn', 'create_date','file_name_xml'],
			storeId : 'Profile',
			proxy : {
				type : 'ajax',
				url : 'getfile',
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
					root : 'file_list'
				}
			}
		});