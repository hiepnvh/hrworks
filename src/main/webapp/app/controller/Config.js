Ext.define(App.path('controller.Config'), {
    extend: 'Ext.app.Controller',
    refs: [{
            ref: 'MainView',
            selector: '#MainView' //  id:MainView 

        },{
            ref: 'WestView',
            selector: '#west' //  id:west 

        },{
            ref: 'CenterView',
            selector: '#CenterView' //  id:west 

        }],
    showSmsGWConnect: function() {
        if (App.Session.isLoggedIn()) {
            var store = App.path('store.SmsGw');
            this.getMainView().activateViewItem('ViewIndex', function() {
                var viewItem = Ext.create(App.path('view.ViewIndex'));
                return viewItem;
             }, this).showWhats('ViewSmsGWConnect', App.path('view.ViewSmsGWConnect'), 'ViewBlank', App.path('view.ViewBlank'),store,'ViewSmsGWConnectDetail',App.path('view.ViewSmsGWConnectDetail'));
             this.getWestView().setTitle('Danh sách cấu hình');
             view.on('select',function(me, record){             
           });
        } else {
            Ext.Router.redirect('');
        }
    },
    showMORouting: function() {
        if (App.Session.isLoggedIn()) {
            var store = App.path('store.MORouting');
            this.getMainView().activateViewItem('ViewIndex', function() {
                var viewItem = Ext.create(App.path('view.ViewIndex'));
                return viewItem;
             }, this).showWhats('ViewMORouting', App.path('view.ViewMORouting'), 'ViewBlank', App.path('view.ViewBlank'),store,'ViewMORoutingDetail',App.path('view.ViewMORoutingDetail'));
             this.getWestView().setTitle('Danh sách MO Route');
             view.on('select',function(me, record){             
           });
        } else {
            Ext.Router.redirect('');
        }
    },
    showCdr: function() {
        if (App.Session.isLoggedIn()) {
            var store = App.path('store.Cdr');
            this.getMainView().activateViewItem('ViewIndex', function() {
                var viewItem = Ext.create(App.path('view.ViewIndex'));
                return viewItem;
             }, this).showWhats('ViewCdr', App.path('view.ViewCdr'), 'ViewBlank', App.path('view.ViewBlank'),store,'ViewCdrDetail',App.path('view.ViewCdrDetail'));
             this.getWestView().setTitle('Danh sách cấu hình');
             view.on('select',function(me, record){             
           });
        } else {
            Ext.Router.redirect('');
        }
    },
    showMTRouting: function() {
        if (App.Session.isLoggedIn()) {
            var store = App.path('store.MTRouting');
            this.getMainView().activateViewItem('ViewIndex', function() {
                var viewItem = Ext.create(App.path('view.ViewIndex'));
                return viewItem;
             }, this).showWhats('ViewMTRouting', App.path('view.ViewMTRouting'), 'ViewBlank', App.path('view.ViewBlank'),store,'ViewMTRoutingDetail',App.path('view.ViewMTRoutingDetail'));
             this.getWestView().setTitle('Danh sách MT Route');
             view.on('select',function(me, record){             
           });
        } else {
            Ext.Router.redirect('');
        }
    }
});