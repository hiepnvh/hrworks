/**
 * 
 */
package com.gtel.hrworks.conf;


/**
 * @author QuangN
 *
 */
public class Consts {
	
	public static final String DEFAULT_PASSWORD ="123456";
	public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
	public static final String NUMBER_FORMAT = "(8499|84199)(\\d{7})";
	public static final String CURRENCY_FORMAT = "#.### VND";
	public static final Integer OTP_LEN = 6;
	public static final Integer WEBAPP_ID = 1;
	
	public static class STATE {
		public static final int ACTIVE= 1;
		public static final int INACTIVE= 0;
	}	
	
	public static class AGENT_TYPE {
		public static final int COMVERSE= 1;
	}
	
	public static class ERR_CODE {
		public static final String MSISDN_NOT_EXIST = "msisdn_not_exist";
		
	}
	
	/*HEADER*/
	public static class HEADER{
		public static final String userName = "X-GTEL-UserName";
		public static final String displayName = "X-GTEL-UserDisplayName";
		public static final String mobile = "X-GTEL-UserMobile";
		public static final String mail = "X-GTEL-UserMail";
	}
}
