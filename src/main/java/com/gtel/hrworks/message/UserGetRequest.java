package com.gtel.hrworks.message;

import java.util.List;
import java.util.logging.Logger;

import org.json.JSONObject;

import com.gtel.gportal.db.*;
import com.gtel.gportal.model.MenuFunction;
import com.gtel.gportal.model.User;
import com.gtel.hrworks.conf.*;

public class UserGetRequest extends JsonRequest {

	/**
	 * @param jObj
	 * @throws Exception 
	 */
	Integer _webapp_id;
	String _username;
	String _displayName;
	
	private static Logger LOGGER = Logger.getLogger(UserGetRequest.class.getName());
	
	public UserGetRequest(JSONObject jObj) throws Exception {
		super(jObj);
		_webapp_id = Consts.WEBAPP_ID;
//		_webapp_id = jObj.getInt(JsParams.GET_HRWORKS_EMPLOYEE_RECORD_REQUEST.WEBAPP_ID);
		if (jObj.has(JsParams.USER_LOGIN_INFO_GET_REQUEST.USERNAME)) {
			_username = jObj.getString(JsParams.USER_LOGIN_INFO_GET_REQUEST.USERNAME);
		}
		if (jObj.has(JsParams.USER_LOGIN_INFO_GET_REQUEST.DISPLAYNAME)) {
			_displayName = jObj.getString(JsParams.USER_LOGIN_INFO_GET_REQUEST.DISPLAYNAME);
		}
//		if (jObj.has(JsParams.USER_LOGIN_INFO_GET_REQUEST.MOBILE)) {
//			mobile = jObj.getString(JsParams.USER_LOGIN_INFO_GET_REQUEST.MOBILE);
//		}
//		if (jObj.has(JsParams.USER_LOGIN_INFO_GET_REQUEST.MAIL)) {
//			mail = jObj.getString(JsParams.USER_LOGIN_INFO_GET_REQUEST.MAIL);
//		}
		LOGGER.info("username: " + _username + "displayname: " + _displayName + "webid: "+ _webapp_id);

	}

	
	@Override
	public JsonResponse execute() {
		UserGetResponse resp = new UserGetResponse();
		UserDAO uDao = new UserDAO();
		
		try {
			User user = uDao.getUser(_username);
			List<MenuFunction> functions = uDao.getFunctionOfUser(user, _webapp_id);
			resp.setSuccess(true);
			resp.setUserInfo(user,functions);
	} catch (Exception exc) {
		exc.printStackTrace();
		resp.setSuccess(false);
		resp.setInfo(exc.getMessage());
		
	}
	return resp;
	}

}
