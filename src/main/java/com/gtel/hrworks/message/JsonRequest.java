package com.gtel.hrworks.message;


import org.json.JSONObject;

import com.gtel.hrworks.conf.JsParams;
import com.gtel.gportal.db.UserDAO;
import com.gtel.gportal.model.User;

public  abstract class JsonRequest  {
	
	String _webapp_id;
	Boolean _viewOnly;
	
	public JsonRequest(JSONObject jObj) throws Exception {
		if (jObj.has(JsParams.GENERAL_REQUEST.WEBAPP_ID)) {
			_webapp_id = jObj.getString(JsParams.GENERAL_REQUEST.WEBAPP_ID);
//			UserDAO userDAO = new UserDAO();
//			_viewOnly = userDAO.getUser(_userId).get(User.VIEWONLY);
		} else
			_webapp_id = null;
	}
	
	public final String getWebappId()  {
		return _webapp_id;
	}
	
	public abstract JsonResponse execute() ;

	
}
