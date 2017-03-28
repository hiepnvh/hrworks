Ext.define(App.path('store.ContentTree'), {
    extend: 'Ext.data.TreeStore',
    model: App.path('model.ContentTree'),
    storeId:'AgentTree',
    folderSort: true,
    proxy : {
				type : 'ajax',
				url : 'getcontenttree',
				actionMethods : {
					create : 'POST',
					read : 'POST',
					update : 'POST',
					destroy : 'POST'
				},
				reader : {
					type : 'json',
					root : 'child_list'
				}
			}
});
