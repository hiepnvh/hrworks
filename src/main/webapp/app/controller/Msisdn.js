Ext.define(App.path('controller.Msisdn'), {
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
    showLookUpMsisdn:function(){
        if (App.Session.isLoggedIn()) {
            this.getMainView().activateViewItem('ViewIndex100', function() {// tuong tu getCmp thay vao day la get vao mot khung view da dung san co id de dai dien cho view do
            	var viewItem = Ext.create(App.path('view.ViewIndex100'));
            	viewItem.activate();
                return viewItem;
            }, this).showWhat('ViewLookUpMsisdn', App.path('view.ViewLookUpMsisdn'));
        } else {
            Ext.Router.redirect('');
        }
    }
});