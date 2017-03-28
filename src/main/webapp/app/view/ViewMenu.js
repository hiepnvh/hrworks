Ext.define(App.path('view.ViewMenu'), {
	extend : 'Ext.form.Panel',
	xtype : 'menu',
	itemId : 'Menu',
	bodyStyle : 'background-color: white',
	border : false,
	config : {
		items : [{
			minHeight : 70,
			layout : 'hbox',
			border : false,
			items : [{
				html : '<img src="./resources/img/gmobilelogo-vi.png" alt="logo1" class="logo_img"/>',
				height : '100%',
				width : '20%',
				layout : 'fit',
				border : false,
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext.Router.redirect('');
						}
					}
				}
			}, {
				border : false,
				height : '100%',
				width : '60%',
				html : '<p class="name_title_app"> Hệ thống hỗ trợ Ban/Phòng Gmobile </p>',
				cls : 'title_app'
			}, {
				height : '100%',
				border : false,
				width : '20%',
				layout : 'fit',
				html : '<span style="position: absolute;bottom: 0;">Phát triển bởi IT R&D Gmobile ©</span>'
			}]
		}, {
			layout : 'hbox',
			bodyStyle : 'background-color: white',
			minHeight : 30,
			border : false,
			items : [{
				flex : 1,
				xtype : 'toolbar',
				bodyStyle : 'background-color: white',
				border : false,
				padding : 2,
				items : [{
							text : 'Tra cứu thông tin',
							itemId : 'lookupmsisdn',
							menu : [{
								text : 'Nhân viên đi làm muộn',
								itemId : 'employeelate',
								width : 200
							}]
						}]
			}, {
				border : false,
				bodyStyle : 'background-color: white',
				items : [{
					flex : 1,
					xtype : 'toolbar',
					bodyStyle : 'background-color: white',
					border : false,
					cls : 'account',
					items : [{
								xtype : 'label',
								html : 'Tên đăng nhập:'
							}, {
								text : App.Session.name,
								itemId : 'username',
								menu : [{
									text : 'Thoát',
									name : 'logout',
									itemId : 'logout',
									handler : function() {
										this.up('form').logout();
									}
								}]

							}]
				}]
			}]
		}]
	},
	logout : function() {
		document.location.href='https://accounts.gmobile.vn/sso/UI/Logout';
//		Ext.Router.redirect('https://accounts.gmobile.vn/sso/UI/Logout');
	},
	activate : function() {
//		var req = new XMLHttpRequest();
//		req.open('GET', document.location, false);
//		req.send(null);
//		var headers = req.getAllResponseHeaders().toLowerCase();
		console.log('sss ' + App.Session.name);
//		this.down('#lookupmsisdn').setVisible(false);
//		this.down('#changelimit').setVisible(false);
		
//		this.down('#1').setVisible(false);
//		this.down('#2').setVisible(false);
		
		this.down('#username').setText(App.Session.name);
		for(var i=0;i<App.Session.functionlist.length;i++) {
			this.FunctionMap(App.Session.functionlist[i].function_id);
		}
	},
	FunctionMap : function(itemId) {
		switch(itemId)
		{
			case 11 : this.ShowBoolean('user',true); this.ShowBoolean('1',true); break;
			case 12 : this.ShowBoolean('profile',true); this.ShowBoolean('1',true); break;
			case 22 : this.ShowBoolean('lookupmsisdn',true);/*this.ShowBoolean('changelimit',true); */ break;
			case 21 : this.ShowBoolean('lookupmsisdn',true);  break;
		}
	},
	ShowBoolean : function(itemId, show) {
		var item = '#' + itemId;
		if (show) {
			this.down(item).setVisible(true);
		} else {
			this.down(item).setVisible(false);
		}
	}
});
