Ext.define(App.path('view.ViewIndex100'), {
    extend: 'Ext.container.Container',
    itemId: 'viewindex100',
    id: 'viewindex100',
    layout: 'border',
    requires: App.path('view.ViewMenu'),
    items: [{
            xtype: 'menu',
            itemId: 'menu',
            region: 'north',
            border: true,
            height: 105,
            bodyStyle: 'background-color: transparent'

        }, {
            region: 'center',
            border: false,
            //margin:2,
            layout: 'fit',
            bodyStyle: 'background-color: transparent',
            items: {
                xtype: 'amxcontainer',
                itemId: 'CenterView',
                layout: 'card'
            }
        }],
    reset: function() {
        this.down('#menu').activate();
    },
    showWhat: function(itemIdCenter, CenterView) {
        this.reset();
        this.down('#CenterView').activateViewItem(itemIdCenter, function() {
            return Ext.create(CenterView);
        }, this).activate();
    },
    activate: function() {
    	this.LoadProfile();
    },
    LoadContentProvider : function()
    {
    	var store = Ext.getStore(App.path('store.CP'));
    	store.getProxy().extraParams.user_id = App.Session.user_id;
        store.load();
    },
    LoadProfile : function()
    {
    	var store = Ext.getStore(App.path('store.Profile'));
    	store.getProxy().extraParams.user_id = App.Session.user_id;
        store.getProxy().extraParams.limit = null;
        store.load();
    },
    LoadSmsGW : function()
    {
    	var store = Ext.getStore(App.path('store.SmsGw'));
    	store.getProxy().extraParams.user_id = App.Session.user_id;
        store.getProxy().extraParams.limit = null;
        store.load();
    },
    LoadService : function()
    {
    	var store = Ext.getStore(App.path('store.Service'));
    	store.getProxy().extraParams.user_id = App.Session.user_id;
        store.getProxy().extraParams.limit = null;
        store.load();
    },
    
    
    
    
    LoadProvince : function()
    {
    	var store = Ext.getStore(App.path('store.Province'))
    	store.getProxy().extraParams.user_id = App.Session.user_id;
        store.getProxy().extraParams.limit = null;
        store.load();
    },
    LoadDistrict : function()
    {
    	var store = Ext.getStore(App.path('store.District'));
    	store.getProxy().extraParams.user_id = App.Session.user_id;
        store.getProxy().extraParams.limit = null;
        store.load();
    },
    LoadInventoryGroup : function()
    {
    	var store = Ext.getStore(App.path('store.InventoryGroup'));
    	store.getProxy().extraParams.user_id = App.Session.user_id;
        store.getProxy().extraParams.limit = null;
        store.load();
    },
    LoadGtelStaffListUnder : function()
    {
    	var store = Ext.getStore(App.path('store.GtelStaffListUnder'));
    	store.getProxy().extraParams.user_id = App.Session.user_id;
        store.getProxy().extraParams.limit = null;
        store.load();
    },
    LoadRegionManagerList : function()
    {
    	var store = Ext.getStore(App.path('store.RegionManagerList'));
    	store.getProxy().extraParams.user_id = App.Session.user_id;
    	store.getProxy().extraParams.position = App.Constant.POSITION_REGION;
    	store.getProxy().extraParams.limit = null;
    	store.active = '1';
        store.load();
    },
    LoadOfficeManagerList : function()
    {
    	var store = Ext.getStore(App.path('store.OfficeManagerList'));
    	store.getProxy().extraParams.user_id = App.Session.user_id;
    	store.getProxy().extraParams.position = App.Constant.POSITION_OFFICE_MANAGER;
    	store.getProxy().extraParams.limit = null;
    	store.active = '1';
        store.load();
    },
    LoadSupervisorList : function()
    {
    	var store = Ext.getStore(App.path('store.SupervisorList'));
    	store.getProxy().extraParams.user_id = App.Session.user_id;
    	store.getProxy().extraParams.position = App.Constant.POSITION_SUPERVISOR;
    	store.getProxy().extraParams.limit = null;
    	store.active = '1';
        store.load();
    },
    LoadContentFolderTree : function()
    {
    	var store = Ext.getStore(App.path('store.ContentTree'));
    	store.getProxy().extraParams.user_id = App.Session.user_id;
		store.getProxy().extraParams.limit = null;
        store.load();
    },
    LoadGtelBranch : function()
    {
    	var fields = [];
    	fields.push('agent_id');
    	fields.push('agent_type');
    	fields.push('agent_name');
    	fields.push('agent_code');
    	
    	var store = Ext.getStore(App.path('store.ParentGtelBranch'));
    	store.getProxy().extraParams.user_id = App.Session.user_id;
    	store.getProxy().extraParams.min_agent_type = App.Constant.AGENT_TYPE_GTEL_BRANCH;
    	store.getProxy().extraParams.max_agent_type = App.Constant.AGENT_TYPE_GTEL_BRANCH;
    	store.getProxy().extraParams.fields = Ext.encode(fields);
		store.getProxy().extraParams.limit = null;
        store.load();
    },
    LoadDistributor : function()
    {
    	var fields = [];
    	fields.push('agent_id');
    	fields.push('agent_type');
    	fields.push('agent_name');
    	fields.push('agent_code');
    	
    	var store = Ext.getStore(App.path('store.ParentDistributor'));
    	store.getProxy().extraParams.user_id = App.Session.user_id;
    	store.getProxy().extraParams.min_agent_type = App.Constant.AGENT_TYPE_DISTRIBUTOR;
    	store.getProxy().extraParams.max_agent_type = App.Constant.AGENT_TYPE_DISTRIBUTOR;
    	store.getProxy().extraParams.fields = Ext.encode(fields);
		store.getProxy().extraParams.limit = null;
        store.load();
    },
    LoadDistributionStaff : function()
    {
    	var fields = [];
    	fields.push('agent_id');
    	fields.push('agent_type');
    	fields.push('agent_name');
    	fields.push('agent_code');
    	
    	var store = Ext.getStore(App.path('store.ParentDistributorStaff'));
    	store.getProxy().extraParams.user_id = App.Session.user_id;
    	store.getProxy().extraParams.min_agent_type = App.Constant.AGENT_TYPE_DISTRIBUTOR_STAFF;
    	store.getProxy().extraParams.max_agent_type = App.Constant.AGENT_TYPE_DISTRIBUTOR_STAFF;
    	store.getProxy().extraParams.fields = Ext.encode(fields);
		store.getProxy().extraParams.limit = null;
        store.load();
    },
    MapInventory : function()
    {
    	/*===============Begin Load All Agent and Set in A Map=================*/
        var stoAllInventory = Ext.getStore(App.path('store.InventoryList'));
        	stoAllInventory.getProxy().extraParams.user_id = App.Session.user_id;
        	stoAllInventory.getProxy().extraParams.limit = null;
        	stoAllInventory.load({
        		scope : this,
        		callback : function(records, ops, success)
        		{
        			if(success)
        			{
        				var mapInventory = new Ext.util.HashMap();
        				for(var i=0;i<records.length;i++)
        				{
        					var value = {
								name: records[i].data.name
        					};
        					mapInventory.add(records[i].data.code, value);
        				}
        				App.MapValue.setMapInventory(mapInventory);
        			}
        		}
        	});
        /*===============End Load All Agent and Set in A Map=================*/
    },
    MapAgent : function()
    {
    	 /*===============Begin Load All Agent and Set in A Map=================*/
    	var fields = [];
    	fields.push('agent_id');
    	fields.push('agent_type');
    	fields.push('agent_name');
    	fields.push('agent_code');
    	
        var stoAllAgent = Ext.getStore(App.path('store.AllAgent'));
        	stoAllAgent.getProxy().extraParams.user_id = App.Session.user_id;
        	stoAllAgent.getProxy().extraParams.fields = Ext.encode(fields);
        	stoAllAgent.getProxy().extraParams.limit = null;
//        	stoAllAgent.getProxy().extraParams.active = '1';
        	stoAllAgent.load({
        		scope : this,
        		callback : function(records, ops, success)
        		{
        			if(success)
        			{
        				var mapAgent = new Ext.util.HashMap();
        				for(var i=0;i<records.length;i++)
        				{
        					var value = {
        						agent_id: records[i].data.agent_id,
        						agent_code: records[i].data.agent_code,
								agent_name: records[i].data.agent_name,
								agent_type: records[i].data.agent_type
        					};
        					mapAgent.add(records[i].data.agent_id, value);
        				}
        				App.MapValue.setMapAgent(mapAgent);
        			}
        		}
        	});
        /*===============End Load All Agent and Set in A Map=================*/
    },
    MapStaff : function()
    {
    		var fields = [];
	    	fields.push('staff_id');
	    	fields.push('employee_code');
	    	fields.push('name');
			fields.push('position');
    	 /*===============Begin Load All Staff and Set in A Map=================*/
        var stoAllStaff = Ext.getStore(App.path('store.AllStaff'));
        	stoAllStaff.getProxy().extraParams.user_id = App.Session.user_id;
        	stoAllStaff.getProxy().extraParams.fields = Ext.encode(fields);
        	stoAllStaff.getProxy().extraParams.limit = null;
//        	stoAllStaff.getProxy().extraParams.active = '1';
        	stoAllStaff.load({
        		scope : this,
        		callback : function(records, ops, success)
        		{
        			if(success)
        			{
        				var mapStaff = new Ext.util.HashMap();
        				for(var i=0;i<records.length;i++)
        				{
        					var value = {
        						employee_code : records[i].data.employee_code,
								name : records[i].data.name,
								position : records[i].data.position
        					};
        					mapStaff.add(records[i].data.staff_id, value);
        				}
        				App.MapValue.setMapStaff(mapStaff);
        			}
        		}
        	});
        /*===============End Load All Agent and Set in A Map=================*/
    },
//    BuildTree : function()
//    {
//    	var data = [
//				{
//				    "text": "Chocolate Beverage",
//				    "id": "1",
//				    "parentid": "-1"
//				}, {
//				    "id": "2",
//				    "parentid": "1",
//				    "text": "Hot Chocolate"
//				}, {
//				    "id": "3",
//				    "parentid": "1",
//				    "text": "Peppermint Hot Chocolate"
//				}, {
//				    "id": "4",
//				    "parentid": "1",
//				    "text": "Salted Caramel Hot Chocolate"
//				}, {
//				    "id": "5",
//				    "parentid": "1",
//				    "text": "White Hot Chocolate"
//				}, {
//				    "id": "6",
//				    "text": "Espresso Beverage",
//				    "parentid": "-1"
//				}, {
//				    "id": "7",
//				    "parentid": "6",
//				    "text": "Caffe Americano"
//				}, {
//				    "id": "8",
//				    "text": "Caffe Latte",
//				    "parentid": "6"
//				}, {
//				    "id": "9",
//				    "text": "Caffe Mocha",
//				    "parentid": "6"
//				}, {
//				    "id": "10",
//				    "text": "Cappuccino",
//				    "parentid": "6"
//				}, {
//				    "id": "11",
//				    "text": "Pumpkin Spice Latte",
//				    "parentid": "6"
//				}, {
//				    "id": "12",
//				    "text": "Frappuccino",
//				    "parentid": "-1"
//				}, {
//				    "id": "13",
//				    "text": "Caffe Vanilla Frappuccino",
//				    "parentid": "12"
//				}, {
//				    "id": "15",
//				    "text": "450 calories",
//				    "parentid": "13"
//				}, {
//				    "id": "16",
//				    "text": "16g fat",
//				    "parentid": "13"
//				}, {
//				    "id": "17",
//				    "text": "13g protein",
//				    "parentid": "13"
//				}, {
//				    "id": "14",
//				    "text": "Caffe Vanilla Frappuccino Light",
//				    "parentid": "12"
//				}];
//				
//				
//		var source = [];
//		var items = [];
//		// build hierarchical source.
//		for (i = 0; i < data.length; i++) {
//			var item = data[i];
//			var label = item["text"];
//			var parentid = item["parentid"];
//			var id = item["id"];
//
//			if (items[parentid]) {
//				var item = {
//					parentid : parentid,
//					label : label,
//					item : item,
//					leaf : true
//				};
//				if (!items[parentid].items) {
//					items[parentid].items = [];
//				}
//				items[parentid].items[items[parentid].items.length] = item;
//				items[parentid].leaf = false;
//				items[id] = item;
//			} else {
//				items[id] = {
//					parentid : parentid,
//					label : label,
//					item : item,
//					leaf : true
//				};
//				source[id] = items[id];
//			}
//		}
//    },
    Test : function()
    {
    	var date = new Date();
    	var dd = date.getDate();
    	var mm = date.getMonth();
    	var yyyy = date.getFullYear();
    	
    }
});