package com.gtel.hrworks.message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.json.JSONObject;

import com.base.filter.DateGreaterThanOrEqualFilter;
import com.base.filter.DateLessThanOrEqualFilter;
import com.base.filter.FilterCriteria;
import com.bean.base.BeanFilter;
import com.gtel.hrworks.conf.JsParams;
import com.gtel.hrworks.dao.HrWorksEmployeeRecordDAO;
import com.gtel.hrworks.model.HrWorksEmployeeRecord;
import com.gtel.hrworks.dao.*;
import com.gtel.gportal.db.UserDAO;
import com.gtel.gportal.model.MenuFunction;
import com.gtel.gportal.model.Profile;
import com.gtel.gportal.model.User;

public class HrWorksEmployeeRecordGetRequest extends JsonRequest {

	/**
	 * @param jObj
	 * @throws Exception 
	 */
//	Integer _webapp_id;
//	String _username;
//	String _displayName;
	
	private static Logger LOGGER = Logger.getLogger(HrWorksEmployeeRecordGetRequest.class.getName());
	
	public HrWorksEmployeeRecordGetRequest(JSONObject jObj) throws Exception {
		super(jObj);
//		_webapp_id = jObj.getInt(JsParams.GET_HRWORKS_EMPLOYEE_RECORD_REQUEST.WEBAPP_ID);
//		_username = jObj.getString("username").replaceAll("\\[\"", "").replaceAll("\"\\]", "");
//		_displayName = jObj.getString("displayName").replaceAll("\\[\"", "").replaceAll("\"\\]", "");
		
		//get functions, profiles of user
//		UserDAO uDao = new UserDAO();
//		List<MenuFunction> functions = uDao.getFunctionOfUser(uDao.getUser(_username), _webapp_id);
//		Profile profile = uDao.getProfileOfUser(uDao.getUser(_username), _webapp_id);
		
		//set filter
		_filter =  new BeanFilter(HrWorksEmployeeRecord.class);
		FilterCriteria dateCriteria = new FilterCriteria();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		
		if (jObj.has(JsParams.GET_HRWORKS_EMPLOYEE_RECORD_REQUEST.START_DATE)) {
			Date fromDate = formatter.parse(jObj.getString(JsParams.GET_HRWORKS_EMPLOYEE_RECORD_REQUEST.START_DATE));
			dateCriteria.addFilter(new DateGreaterThanOrEqualFilter(fromDate));
		}
		if (jObj.has(JsParams.GET_HRWORKS_EMPLOYEE_RECORD_REQUEST.END_DATE)) {
			Date endDate = formatter.parse(jObj.getString(JsParams.GET_HRWORKS_EMPLOYEE_RECORD_REQUEST.END_DATE));
			dateCriteria.addFilter(new DateLessThanOrEqualFilter(endDate));
		}
//		if (profile.get(Profile.NAME).toLowerCase().contains("user"))
//			_filter.setFilter(HrWorksEmployeeRecord.EMPLOYEE_CN, new StringEqualFilter(_username));
		_filter.setFilterCriteria(HrWorksEmployeeRecord.DATE, dateCriteria);
		
		_start = Integer.parseInt(jObj.getString(JsParams.GET_HRWORKS_EMPLOYEE_RECORD_REQUEST.START));
		_limit = Integer.parseInt(jObj.getString(JsParams.GET_HRWORKS_EMPLOYEE_RECORD_REQUEST.LIMIT));
	}
	
	
	Integer _start,_limit,_profile_id;
	BeanFilter _filter;
	BeanFilter _mtFilter;
	
	@Override
	public JsonResponse execute() {
		HrWorksEmployeeRecordGetResponse resp = new HrWorksEmployeeRecordGetResponse();
		UserDAO uDao = new UserDAO();
		
		try {
//			User user = uDao.getUser(_username);
//			List<MenuFunction> functions = uDao.getFunctionOfUser(user, _webapp_id);
			HrWorksEmployeeRecordDAO moDao = new HrWorksEmployeeRecordDAO();
			List<HrWorksEmployeeRecord> beans = moDao.getBean(_filter);	
			int res = beans.size();
			List<HrWorksEmployeeRecord> smsList = new ArrayList<HrWorksEmployeeRecord>(_limit);
			for(int i=_start;i<_start+_limit;i++){
				if(i<res)
					smsList.add(beans.get(i));
				else
					break;
			};
			resp.setEmployeeList(smsList);
			resp.setSuccess(true);
			resp.setResults(beans.size());
//			resp.setUserInfo(user,functions);
	} catch (Exception exc) {
		exc.printStackTrace();
		resp.setSuccess(false);
		resp.setInfo(exc.getMessage());
		
	}
	return resp;
	}

}
