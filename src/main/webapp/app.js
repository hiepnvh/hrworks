Ext.namespace('App');
App.NAME = 'HN';
App.path = function(name) {
    return App.NAME + '.' + name;
};
Ext.application({
    name: App.NAME,
    viewport: {
        autoMaximize: true
    },
    requires: [
        App.path('mlx.Container'),
        App.path('plugins.Localize'),
        App.path('Session'),
        App.path('Setting'),
        App.path('Action'),
        App.path('ActionMe'),
        App.path('Constant'),
        App.path('MapValue')
    ],
    models: [
    ],
    stores: [
    	App.path('store.User'),
    	App.path('store.Profile'),
    	App.path('store.FileNameXml'),
    	App.path('store.EmployeeRecord'),
    	App.path('store.DynamicStore')
    ],
    controllers: [
		App.path('controller.Sub')
    ],
    enableRouter: true,
    routes: {        
        '/': 'Sub#showEmployeeLate'
    },
    launch: function() {
        var me= this;
        Ext.ux.ActivityMonitor.isInactive = function() {
           me.logout();
        };
        Ext.ux.ActivityMonitor.interval = 5 * 1000;
        Ext.ux.ActivityMonitor.maxInactive = 30 * 60 * 1000;
        Ext.ux.ActivityMonitor.init({
            verbose: false
        });
        Ext.ux.Router.on({
            routemissed: function(token) {
                Ext.Msg.show({
                    title: 'Error 404',
                    msg: 'Route not found: ' + token,
                    buttons: Ext.Msg.OK,
                    icon: Ext.Msg.ERROR
                });
            },   
            dispatch: function(token, match, params, controller) {
            }
        });
         
        Ext.create(App.path('view.ViewMain'));
        
//        App.store={
//             SimList:App.path('store.SimList'),
//             AgentList: App.path('store.AgentList'),
//             TxnSearch:App.path('store.TxnSeach'),
//             AgentTree:App.path('store.AgentTree'),
//             InventoryList:App.path('store.InventoryList'),
//             InventorySellList:App.path('store.InventorySellList')
//        };
    }
//    ,
//    logout:function(){
//        App.Session.setAgentId(null);
//        App.Session.setAgentname(null);
//        App.Session.setUsername(null);
//        App.Session.setPermission(null);
//        Ext.Router.redirect('');
//    }
         
});
