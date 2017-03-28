var host="http://10.16.69.83:8080/gbroadcast";
var user_id="1";
var task_id=$v(P1_TASK_ID);
var EVENT_TYPE_SCHEDULE = 1;
document.getElementById("P1_DISPLAY").innerHTML='';
if (task_id != null && task_id != ""){
	$.ajax({
		url:host+"/getbroadcasttask",
		type:"POST",
		crossDomain:true,
		data:{user_id:user_id,task_id:task_id},
		success:function(response){
			var response=JSON.parse(response);
			if (response.success){
				if (response.task_list.length > 0){
					document.getElementById("P1_DISPLAY").innerHTML='<b><span style="color:red">Yêu cầu đang được xử lý ...</span></b>';
					var r=confirm("Chắc chắn dữ liệu gửi đi chỉ có cột MSISDN ?");
					if (r==true){
						var local_url=document.URL;
						var array_url=local_url.split(':');
						var url_file=array_url.splice(0,5).join(':')+':CSV';
						var time1=(new Date()).getTime();
						var xmlhttp;
						if (window.XMLHttpRequest){xmlhttp=new XMLHttpRequest();
						}else{xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");}
						xmlhttp.open("GET",url_file,false);
						xmlhttp.send();
						var time2=(new Date()).getTime();
						var data=xmlhttp.responseText.split("\"").join("");
						var data_header=data.substr(0,7);
						if (data_header.toUpperCase()=="MSISDN"+String.fromCharCode(10)){
							data = data.replace('MSISDN\n','').replace('84994944330','84993333800');
						var boundary="---------------------------tuananh123456";
						var body='--'
								+boundary
								+'\r\n'
								+'Content-Disposition: form-data; name="file";'
								+'filename="list_sub"\r\n'
								+'Content-type: multipart/form-data\r\n\r\n'
								+data+'\r\n'+'--'+boundary+'--';
							$.ajax({
								contentType:"multipart/form-data; boundary="+boundary,
								data:body,
								type:"POST",
								url:host+"/uploadfile",
								crossDomain:true,
								success:function(response){
									var res_data=JSON.parse(response);
									if (res_data.success){
										$.ajax({
											url:host+"/updatebroadcasttask",
											type:"POST",
											crossDomain:true,
											data:{user_id:user_id,task:JSON.stringify({task_id:task_id,file_path:res_data.url})},
											success:function(response){
												var res_data=JSON.parse(response);
												if (res_data.success){
													$.ajax({
														url:host+"/schedulebroadcasttask",
														type:"POST",
														crossDomain:true,
														data:{user_id:user_id,event_type:EVENT_TYPE_SCHEDULE,task_id:task_id},
														success:function(response){
															document.getElementById("P1_TASK_ID").value='';
															document.getElementById("P1_DISPLAY").innerHTML='';
															var res_data=JSON.parse(response);
															if (res_data.success){
																alert("Cập nhật thành công!");
															}
															else{
																alert("Cập nhật thất bại!" + res_data.info);
															}
														},
														error:function(req,err){
														}
													});
												}else{
													alert("Cập nhật thất bại!" + res_data.info);
												}
											},
											error:function(req,err){
											}
										});
									}
								},
								error:function(req,err){
									alert('error');
								}
							});
						}else{
							alert("Dữ liệu không hợp lệ. Sử dụng Menu: Action>Select Columns để chọn gửi đi chỉ gồm 1 cột MSISDN trước khi submit!");
							document.getElementById("P1_DISPLAY").innerHTML='';
						}
					}else{
						document.getElementById("P1_DISPLAY").innerHTML='';
					}
				}else{
					alert("Không tồn tại Task Id="+task_id);
				}
			}
		},
		error:function(req,err){
			alert("Yêu cầu thất bại. Vui lòng thử lại sau");
		}
	});
}else{
	alert("Task Id không được để trống");
}