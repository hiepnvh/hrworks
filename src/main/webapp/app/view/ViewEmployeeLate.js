Ext.define(App.path('view.ViewEmployeeLate'), {
			extend : 'Ext.grid.Panel',
			border : false,
			itemId : 'ViewEmployeeLate',
			xtype : 'ViewEmployeeLate',
			id : 'ViewEmployeeLate',
			autoScroll : true,
			initComponent : function(arguments) {
				var store = Ext.getStore(App.path('store.EmployeeRecord'));
				var date = null;
//				var status = null;
				var me = this;
				Ext.apply(this, {
							border : false,
							store : store,
							stripeRows : true,
							columnLines : true,
							columns : [{
										text : 'STT',
										xtype : 'rownumberer',
										width : 30
									}, {
										text : 'Ngày',
										flex : 1.2,
										sortable : true,
										name:'action_date',
										dataIndex : 'action_date',
										filter: {
								                type: 'string'
								            }
									}, {
										text : 'Mã nhân viên',
										flex : 1,
										sortable : true,
										name:'employee_id',
										dataIndex : 'employee_id',
										filter: {
								                type: 'string'
								            }
									}, {
										text : 'Họ tên',
										flex : 1,
										sortable : true,
										name:'employee_name',
										dataIndex : 'employee_name',
										filter: {
								                type: 'string'
								            }
								}, {
									text : 'Ban/Phòng',
									flex : 2,
									sortable : true,
									name:'dept_name',
									dataIndex : 'dept_name',
									filter: {
							                type: 'string'
							            }
								}, {
									text : 'Đến(QĐ)',
								flex : 1,
								sortable : true,
								name:'come_default',
								dataIndex : 'come_default',
								filter: {
						                type: 'string'
						            }
								}, {
									text : 'Đến',
									flex : 1,
									sortable : true,
									name:'come_time',
									dataIndex : 'come_time',
									filter: {
							                type: 'string'
					            }}, {
									text : 'Lý do',
									flex : 2,
									sortable : true,
									name:'come_reason',
									dataIndex : 'come_reason',
									filter: {
							                type: 'string'
							            }}, {
								text : 'Về(QĐ)',
								flex : 1,
								sortable : true,
								name:'leave_default',
								dataIndex : 'leave_default',
								filter: {
						                type: 'string'
						            }
								}, {
									text : 'Về',
									flex : 1,
									sortable : true,
									name:'leave_time',
									dataIndex : 'leave_time',
									filter: {
							                type: 'string'
					            }}, {
						         text : 'Lý do',
						         flex : 2,
						         sortable : true,
						         name:'leave_reason',
						         dataIndex : 'leave_reason',
						         filter: {
					                type: 'string'
					            }}, {
							         text : 'LM tự động duyệt',
							         sortable : true,
							         name:'lm_auto_approved',
							         dataIndex : 'lm_auto_approved',
							         filter: {
						                type: 'string'
						            }}, {
								         text : 'Trạng thái',
								         flex : 1,
								         sortable : true,
								         name:'status',
								         dataIndex : 'status',
								         filter: {
							                type: 'string'
							            }}
								], dockedItems: [{
	                    dock: 'bottom',
                    height: 50,
                    border: false,
                    bodyStyle: 'background-color: transparent',
                    items: [{
                            xtype: 'button',
                            text: 'Xuất báo cáo',
                            itemId: 'buttonExport',
                            handler: function() {
                                me.exportXls();
                            }
                        }]
                }],bbar: Ext.create('Ext.PagingToolbar', {
					                store: store,
					                displayInfo: true,
					                pageSize: App.Constant.myPageSize,
					                displayMsg: '',
					                emptyMsg: "Không có dữ liệu để hiển thị"
					        })
						});
				this.callParent(arguments);
			},listeners: {
				cellclick: function(selModel, record, index, options){
//    			this.choosenMsisdn = this.getSelectionModel().getSelection()[0].data.msisdn;
//    			this.choosenLimit = this.getSelectionModel().getSelection()[0].data.new_limit;
//    			var status = this.getSelectionModel().getSelection()[0].data.status;
    			
    		}
    
			},
			activate : function(start_date, end_date) {
				console.log('ss');
				var me = this;
				Ext.get(document.body).unmask();
				this.start_date = start_date;
				this.end_date = end_date;

				var store = this.getStore();
//				console.log(store);
				var user_id = App.Session.user_id;
				store.removeAll();
				
				store.setProxy({
					type : 'ajax',
					url : 'getemployeerecords',
					actionMethods : {
						create : 'POST',
						read : 'POST',
						update : 'POST',
						destroy : 'POST'
					},
					extraParams : {
//						user_id : user_id,
						start_date: App.ActionMe.DateToDBExactly(me.start_date),
						end_date: App.ActionMe.DateToDBExactly(me.end_date)
					},
					reader : {
						type : 'json',
						root : 'employee_list',
						totalProperty : 'results'
					}
				});
				store.load({
					params:{
				        start:0,
				        limit: App.Constant.myPageSize
				    },
					callback : function(records, options, success){
						if (success) {
							Ext.get(document.body).unmask();
//							response = Ext.decode(response.responseText);
//							console.log(response.user.username);
//							App.Session.setUsername(response.user.username);
//	                        App.Session.setName(response.user.displayname);
////	                        App.Session.setPassword(response.user.password);
//	                        App.Session.setFunctionlist(response.function_list);
						}
					}
				});
//				this.down('#buttonExport').setDisabled(true);
//				this.down('#buttonEdit').setDisabled(true);
				for(var i=0;i<App.Session.functionlist.length;i++) {
					this.FunctionMap(App.Session.functionlist[i].function_id);
				}
			},
			FunctionMap : function(itemId) {
//				console.log(itemId);
				switch(itemId)
				{
//					case 11 : this.down('#buttonExport').setDisabled(false); break;
//					case 12 : this.down('#buttonExport').setDisabled(false); break;
//					case 22 : this.down('#buttonExport').setDisabled(false); break;
//					case 21 : this.ShowBoolean('lookupmsisdn',true);  break;
				}
			},
			FunctionMapForEditBtn : function(itemId,status) {
				console.log(itemId);
				console.log(status);
				if(status=='pending'){
					switch(itemId)
					{
						case 11 : this.down('#buttonEdit').setDisabled(false); break;
						case 12 : this.down('#buttonEdit').setDisabled(false); break;
						case 22 : this.down('#buttonEdit').setDisabled(false); break;
					}
				}
				else
					this.down('#buttonEdit').setDisabled(true);
			},
			exportXls : function(){
				Ext.get(document.body).mask('Chờ giây lát..');
				var me = this;
				var user_id = App.Session.user_id;
				var start_date = this.start_date;
				var end_date = this.end_date;
				
				Ext.Ajax.request({
					url : 'getreport',
					actionMethods : {
					create : 'POST',
					read : 'POST',
					update : 'POST',
					destroy : 'POST'
					},
					params : {
						user_id : user_id,
						start_date: App.ActionMe.DateToDBExactly(start_date),
						end_date: App.ActionMe.DateToDBExactly(end_date)
					},
					scope : this,
					timeout : 60000,
					callback : function(options, success, response) {
						if (success) {
							Ext.get(document.body).unmask();
							response = Ext.decode(response.responseText);
							console.log(response.url);
							if (response.success) {
								//Download
								var hiddenIFrameID = 'hiddenDownloader',
						        iframe = document.getElementById(hiddenIFrameID);
							    if (iframe === null) {
							        iframe = document.createElement('iframe');
							        iframe.id = hiddenIFrameID;
							        iframe.style.display = 'none';
							        document.body.appendChild(iframe);
							    }
							    iframe.src = response.url;
							} else {
								Ext.MessageBox.alert('Thông báo',
										'Tải file thất bại '+ response.info);
							}
						} else {
							Ext.get(document.body).unmask();
							Ext.MessageBox.alert('Thông báo',
									'Tải file thất bại. Vui lòng thử sau '+ response.info);
						}
					}
				
				});
				},
				renderDate : function (value){
				    return Date.parseDate(value,"Y-m-d h:i:s").format('d.m.Y H:i:s');
				} 
		});
