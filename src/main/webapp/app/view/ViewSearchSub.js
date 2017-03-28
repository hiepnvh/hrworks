Ext.define(App.path('view.ViewSearchSub'), {
	extend : 'Ext.form.Panel',
	itemId : 'ViewSearchSub',
	bodyStyle : 'background-color: transparent',
	border : false,
	layout : 'fit',
	config : {
		record : null,
		fieldDefaults : {
			margin : '5'
		},
		items : [{
			region : 'center',
			border : false,
			items : [{
				border : false,
				items : [{
							border : false,
							layout : 'hbox',
							defaultType : 'textfield',
							items : [{
										flex : 1,
										itemId : 'user_id',
										hidden : true
									}]
						},{
							border : false,
							layout : 'hbox',
							defaultType : 'textfield',
							items : [{
								flex : 1,
								xtype : 'datefield',
								format : 'd/m/Y',
								itemId : '_start_date',
								fieldLabel : 'Ngày bắt đầu',
								value : new Date()
							}]
						}, {
							border : false,
							layout : 'hbox',
							defaultType : 'textfield',
							items : [{
								flex : 1,
								xtype : 'datefield',
								format : 'd/m/Y',
								itemId : '_end_date',
								fieldLabel : 'Ngày kết thúc',
								value : new Date()
							}]
						}]
			}]
		}],
		buttons : [{
					text : 'Search',
					handler : function() {
						var form = this.up('form');
						form.Search();
					}
				}]
	},
	activate : function() {
		Ext.get(document.body).mask('Chờ giây lát..');
		var me = this;
		
//		var data = me.record.data;
		
//		this.down('#user_id').setValue(data.user_id);
//		this.down('#number').setValue(data.active);
//		this.down('#content').setValue(data.profile_id);
//		this.down('#subscriber').setValue(data.username);

		Ext.get(document.body).unmask();
	},
	Search : function() {
		Ext.get(document.body).mask('Chờ giây lát..');
		var me = this;
		var _start_date = this.down('#_start_date').getValue();
		var _end_date = this.down('#_end_date').getValue();
		var view_center = Ext.getCmp('CenterView');
		view_center.activateViewItem('ViewEmployeeLate', function() {
					var viewItem = Ext.create(App
							.path('view.ViewEmployeeLate'));
					return viewItem;
				}).activate(_start_date, _end_date);

	}
});