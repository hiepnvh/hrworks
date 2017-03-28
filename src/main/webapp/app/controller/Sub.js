Ext.define(App.path('controller.Sub'), {
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

    showEmployeeLate:function(){
//    	if (App.Session.isLoggedIn()) {
            var store =App.path('store.EmployeeRecord');
            var view = this.getMainView().activateViewItem('ViewIndex', function() {
               var viewItem= Ext.create(App.path('view.ViewIndex'));
               return viewItem;
            }, this).showWhats('ViewSearchSub', App.path('view.ViewSearchSub'), 'ViewBlank', App.path('view.ViewBlank'),store,'ViewEmployeeLate',App.path('view.ViewEmployeeLate'));
//            }, this).showWhat('ViewSubscriber',App.path('view.ViewSubscriber'));
//    			}, this).showWhat('ViewUserService',App.path('view.ViewUserService'));
               
             this.getWestView().setTitle('Tìm kiếm thông tin');
           view.on('select',function(me, record){
           });
//        }else{
//            Ext.Router.redirect('');
//        }
    }
});