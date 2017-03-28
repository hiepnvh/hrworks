Ext.define(App.path('view.ViewLookUpMsisdn'), {
	extend : 'Ext.form.Panel',
	itemId : 'ViewLookUpMsisdn',
	bodyStyle : 'background-color: transparent',
	border : false,
	autoScroll : true,
	layout : {
		type : 'hbox',
		pack : 'start',
		align : 'stretch'
	},
	config : {
		record : null,
		fieldDefaults : {
			margin : '5 0 0 5'
		},
		items : [{
			flex : 1,
			border : true,
			layout : {
				type : 'vbox',
				pack : 'start',
				align : 'stretch'
			},
			items : [{
				flex : 1,
				height : '100%',
				xtype : 'grid',
				itemId : 'gridtl',
				border : true,
				title : 'D/s cước',
				stripeRows : true,
				columnLines : true,
				autoScroll : true,
				selModel : Ext.create('Ext.selection.RadioModel'),
				store : App.path('store.FileNameXml'),
				columns : [{
							text : 'Số điện thoại',
							dataIndex : 'msisdn',
							flex : 1
						}, {
							text : 'Ngày tạo',
							dataIndex : 'create_date',
							flex : 1
						}, {
							text : 'Tên file',
							dataIndex : 'file_name_xml',
							flex : 1
						}
//								, {
//									xtype : 'checkcolumn',
//									width : 30,
//									sortable : false,
//									dataIndex : 'active',
//									editor : {
//										xtype : 'checkbox'
//									},
//									stopSelection : false,
//									listeners : {
//										checkchange : function(column, recordIndex,
//												ischecked) {
//										}
//									}
//						}
						]
//						dockedItems : [{
//							xtype : 'toolbar',
//							items : [{
//										xtype : 'textfield',
//										itemId : 'msisdn',
//										fieldLabel : 'Số ngoài danh bạ',
//										maskRe : /[0-9,]/
//									}, {
//										itemId : 'addout',
//										text : 'Thêm',
//										iconCls : 'icon-add',
//										handler : function() {
//											var me = this.up('form');
//											if (me.down('#msisdn').getValue().length > 0) {
//												var storeReceiv = me.down('#grid2')
//														.getStore();
//												var list_msisdn = me
//														.down('#msisdn').getValue();
//												var arr_msisdn = list_msisdn
//														.split(',');
//												for (var i = 0; i < arr_msisdn.length; i++) {
//													storeReceiv.add({
//														name : 'Chưa có tên',
//														msisdn : App.ActionMe
//																.FormatMsisdn(arr_msisdn[i]),
//														type : 1
//													});
//												}
//												me.down('#msisdn').setValue('');
//											}
//										}
//									}, {
//										xtype : 'tbfill'
//									}, {
//										itemId : 'delete2',
//										text : 'Xóa',
//										iconCls : 'icon-del',
//										disabled : true,
//										handler : function() {
//											var me = this.up('form');
//											me.Remove2();
//										}
//									}, '-', {
//										xtype : 'checkbox',
//										boxLabel : 'Tất cả',
//										itemId : 'markall2',
//										margin : '0 5 0 0',
//										listeners : {
//											change : function(cbx, newValue,oldValue) {
//												var me = this.up('form');
//												me.MarkAll2(newValue);
//												me.down('#delete2').setDisabled(!newValue);
//											}
//										}
//									
//											}]
//						}],
//						listeners : {
//							itemclick : function(dv, record, item, index, e) {
//								var me = this.up('form');
//								// me.LoadDataFormMember(record.data);
//								// me.LoadGroupMember(record.data.group_name);
//							},
//							selectionchange : function(selModel, selections) {
//								var me = this.up('form');
//								me.down('#delete2')
//										.setDisabled(selections.length === 0);
//							}
//						}
			}
			, {
				border : false,
				layout : 'hbox',
				defaultType : 'textfield',
				items : [{
							xtype : 'tbspacer',
							flex : 1
						}, {
							xtype : 'button',
							cls : 'search_all',
							text : 'Tải về',
							margin : 5,
							handler : function() {
								var me = this.up('form');
								me.GetFile();
							}
						}]
			}]
		}
//				, {
//							flex : 1,
//							border : true,
//							layout : {
//								type : 'vbox'
//							},
//							items : [{
//										margin : '5 5 5 5',
//										xtype : 'textfield',
//										itemId : 'msisdn',
//										fieldLabel : 'Số thuê bao',
//										maskRe: /[0-9]/
//									}, {
//										xtype : 'button',
//										cls : 'search_all',
//										text : 'Nhập lại',
//										margin : 5,
//										handler : function() {
//											var me = this.up('form');
//											me.getForm().reset();
//										}
//									}]
//						}
		]
	},
	activate : function() {
//				this.getForm().reset();
		
		var storeFile = this.down('#gridtl').getStore();
//				storeFile.getProxy().extraParams.user_id = App.Session.user_id;
		storeFile.getProxy().extraParams.username = App.Session.username;
		storeFile.load();
        
	},
	GetFile : function () {
		
		var me = this;
		var value = this.down('#gridtl').getSelectionModel().getSelection();
		
		if(value.length == 0){
			Ext.MessageBox.alert('Thông báo', 'Bạn phải chọn file cần tải');
		} else {
			Ext.get(document.body).mask('Chờ giây lát..');
			
			var filename = value[0].data.file_name_xml
			
			App.Action.DownloadFile(App.Session.username, filename ,
					function(options, success, response) {
//						console.log(response);
						Ext.get(document.body).unmask();
						if (success) {
							response = Ext.decode(response.responseText);
							if (response.success) {
								me.OpenReport(value[0].data.file_name_xml.replace("xml","xls"));
							} else {
								Ext.MessageBox.alert('Thông báo', response.info);
							}
						} else {
							Ext.MessageBox.alert('Thông báo', "Không nhận được kết quả");
						}
						Ext.get(document.body).unmask();
					});
		}
				
		
//		console.log(value[0].data);
		
//				var me = this;
//				var msisdn = App.ActionMe.FormatMsisdn(this.down('#msisdn').getValue().trim());
//				var amount = this.down('#amount').getValue().trim();
//				var date = App.ActionMe.DateToDBDWH3(this.down('#date').getValue());
		
//				if(this.ValidCharge()){
//					Ext.get(document.body).mask('Chờ giây lát..');
//					App.Action.GetRechargeHistory(App.Session.user_id, msisdn, amount, date,
//					function(options, success, response) {
//						console.log(response);
//						Ext.get(document.body).unmask();
//						if (success) {
//							response = Ext.decode(response.responseText);
//							if (response.success) {
//								if(response.result)
//									me.down('#historyradio').setValue({historyradio : true});
//								else
//									me.down('#historyradio').setValue({historyradio : false});
//							} else {
//								Ext.MessageBox.alert('Thông báo', response.info);
//							}
//						} else {
//							Ext.MessageBox.alert('Thông báo', "Không nhận được kết quả");
//						}
//					});
//				} else {
//					Ext.MessageBox.alert('Thông báo', 'Chưa điền đầy đủ thông tin');
//				}
	},
	ValidCdr : function() {
		if(this.down('#msisdn').getValue() == '') {
			Ext.MessageBox.alert('Thông báo', 'Nhập Số thuê bao');
			return false;
		}
//				if(!this.ValidFomartMsisdn(this.down('#msisdn').getValue())) {
//					Ext.MessageBox.alert('Thông báo', 'Số thuê bao không đúng định dạng');
//					return false;
//				}
		return true;
	},
	ValidCharge : function(){
		if(this.down('#msisdn').getValue() == '') {
			Ext.MessageBox.alert('Thông báo', 'Nhập Số thuê bao');
			return false;
		}
		if(this.down('#amount').getValue() == ''){
			Ext.MessageBox.alert('Thông báo', 'Nhập Số tiền');
			return false;
		}
		if(this.down('#date').getValue() == ''){
			Ext.MessageBox.alert('Thông báo', 'Nhập Thời gian');
			return false;
		}
//				if(!this.ValidFomartMsisdn(this.down('#msisdn').getValue())) {
//					Ext.MessageBox.alert('Thông báo', 'Số thuê bao không đúng định dạng');
//					return false;
//				}
		return true;
	},
	ValidFomartMsisdn : function(msisdn){
		var patt = new RegExp("(099|0199|8499|84199)[0-9]{7}");
		var res = patt.test(msisdn);
		return res;
	},
	OpenReport : function(link_url) {
		var linkfile = window.location.href.split("#")[0] +'files/'+ link_url;
		var win = window.open(App.ActionMe.ReplaceAll(linkfile,'undefined/',''), '_blank');
	}
});
