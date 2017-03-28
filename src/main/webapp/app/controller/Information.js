Ext.define(App.path('controller.Information'), {
			extend : 'Ext.app.Controller',
			refs : [{
						ref : 'MainView',
						selector : '#MainView' // id:MainView

					}, {
						ref : 'WestView',
						selector : '#west' // id:west

					}, {
						ref : 'CenterView',
						selector : '#CenterView' // id:west

					}],
		showContentProvider : function() {
    	if (App.Session.isLoggedIn()) {
            var store = App.path('store.CP');
            var view = this.getMainView().activateViewItem('ViewIndex', function() {
               var viewItem= Ext.create(App.path('view.ViewIndex'));
               return viewItem;
             }, this).showWhats('ViewContentProvider', App.path('view.ViewContentProvider'), 'ViewBlank', App.path('view.ViewBlank'),store,'ViewContentProviderDetail',App.path('view.ViewContentProviderDetail'));
             this.getWestView().setTitle('Tìm kiếm nhà cung cấp');
           view.on('select',function(me, record){
           });
        }else{
            Ext.Router.redirect('');
        }
    },
		showOperator : function() {
    	if (App.Session.isLoggedIn()) {
            var store = App.path('store.Operator');
            var view = this.getMainView().activateViewItem('ViewIndex', function() {
               var viewItem= Ext.create(App.path('view.ViewIndex'));
               return viewItem;
             }, this).showWhats('ViewOperator', App.path('view.ViewOperator'), 'ViewBlank', App.path('view.ViewBlank'),store,'ViewOperatorDetail',App.path('view.ViewOperatorDetail'));
             this.getWestView().setTitle('Tìm kiếm nhà cung cấp');
           view.on('select',function(me, record){
           });
        }else{
            Ext.Router.redirect('');
        }
    }
});