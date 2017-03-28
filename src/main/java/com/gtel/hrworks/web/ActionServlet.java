package com.gtel.hrworks.web;


import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.bean.json.JsonUtils;
import com.gtel.hrworks.conf.Consts;
import com.gtel.hrworks.conf.JsParams;
import com.gtel.hrworks.message.JsonRequest;
import com.gtel.hrworks.message.JsonResponse;





/** * Servlet implementation class LoginServlet */
public class ActionServlet extends HttpServlet {
	protected static final Logger LOGGER = Logger.getLogger(ActionServlet.class
			.getName());

	Map<String,Class> _messageClassMap; 
	
	
	public synchronized void doPost(HttpServletRequest request,
			HttpServletResponse response) {

		_messageClassMap =  new HashMap<String,Class> ();
		Enumeration<String> initNames = this.getInitParameterNames();
		
//		Enumeration<String> headerNames = request.getHeaderNames();
		try{
			String userName = request.getHeader(Consts.HEADER.userName);
			String displayName = request.getHeader(Consts.HEADER.displayName);
			String mobile = request.getHeader(Consts.HEADER.mobile);
			String mail = request.getHeader(Consts.HEADER.mail);
			
//			String userName = "Hoangv";
//			String displayName = "Demo";
			
			LOGGER.info("userName : " + userName);
			if(userName ==null)
				throw new Exception("ERROR: Fail in authentication, null user id");
					
			while (initNames.hasMoreElements()) {
				String initName = initNames.nextElement();
				String messageClassName = this.getInitParameter(initName);
				try {
					_messageClassMap.put(initName, Class.forName(messageClassName));
				} catch (Exception exc) {
					LOGGER.info("Cannot create message class of parameter " + initName +" class =" + messageClassName +" error "+ exc);
				}
			}
			JsonUtils.setDateFormat(Consts.DATE_FORMAT);
			try {
				JsonUtils.setDateFormat(Consts.DATE_FORMAT);
				JSONObject jObj = JsonUtils.fromMapToJson(request.getParameterMap());
				
				jObj.put(JsParams.USER_LOGIN_INFO_GET_REQUEST.USERNAME, String.valueOf(userName));
				jObj.put(JsParams.USER_LOGIN_INFO_GET_REQUEST.DISPLAYNAME, String.valueOf(displayName));
				jObj.put(JsParams.USER_LOGIN_INFO_GET_REQUEST.MOBILE, String.valueOf(mobile));
				jObj.put(JsParams.USER_LOGIN_INFO_GET_REQUEST.MAIL, String.valueOf(mail));
				
				String pathInfo = request.getServletPath();
				LOGGER.info("Servlet "+ pathInfo + " input :"+jObj.toString());
				Class messageClass = _messageClassMap.get(pathInfo);
				if (messageClass==null) {
					LOGGER.info("Cannot find message class for path " + pathInfo);
					throw new Exception("System error");
				}
				Class[] paramsArray = {JSONObject.class};
				Constructor constrc = messageClass.getConstructor(paramsArray);
				Object[] input  = {jObj};
				
				LOGGER.info("input:"+input.toString());
				JsonRequest jRequest = (JsonRequest) constrc.newInstance(input);
				JsonResponse jResponse  =  jRequest.execute();
				response.setHeader("Access-Control-Allow-Origin", "*");
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				LOGGER.info("Output :"+jResponse.getJsonObject().toString());
				response.getWriter().write(jResponse.getJsonObject().toString());

			} catch (Exception theException) {
				LOGGER.info("Action servlet " + theException.getMessage());
				theException.printStackTrace();
			} 
		}catch(Exception exc){
			LOGGER.info("ERROR: Fail in authentication");
		}

	}

}
