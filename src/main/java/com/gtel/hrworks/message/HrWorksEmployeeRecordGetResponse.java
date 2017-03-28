package com.gtel.hrworks.message;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bean.json.JsonUtils;
import com.gtel.hrworks.conf.JsParams;
import com.gtel.hrworks.model.HrWorksEmployeeRecord;
import com.gtel.gportal.model.MenuFunction;
import com.gtel.gportal.model.User;

public class HrWorksEmployeeRecordGetResponse extends JsonResponse {

	public HrWorksEmployeeRecordGetResponse() {
	}
	
	public void setEmployeeList(List<HrWorksEmployeeRecord> log) throws Exception {
		JSONArray jArr = JsonUtils.fromBeanListToJsonArray(log);		
		write(JsParams.GET_HRWORKS_EMPLOYEE_RECORD_RESPONSE.EMPLOYEE_LIST, jArr);
	}
	
	public void setResults(Integer results) throws Exception {
		write(JsParams.GET_SMS_HISTORY_RESPONSE.RESULTS, results);
	}

//	public void setUserInfo(User user, List<MenuFunction> functions) throws Exception {
//		JSONObject jsUser = JsonUtils.fromBeanToJson(user);
//		write(JsParams.LOGIN_RESPONSE.USER, jsUser);
//
//		JSONArray  jsonFunctions = JsonUtils.fromBeanListToJsonArray(functions);
//		write(JsParams.LOGIN_RESPONSE.FUNCTION_LIST, jsonFunctions);
//		
//	}
}
