package com.gtel.hrworks.dao;

import java.util.logging.Logger;

import com.base.filter.StringEqualFilter;
import com.bean.base.BeanFilter;
import com.gtel.hrworks.dao.HrWorksEmployeeRecordDAO;
import com.gtel.hrworks.model.HrWorksEmployeeRecord;
import com.gtel.ldap.model.User;
import com.gtel.ldap.bpmn.GLdapHelper;

public class LdapDAO {
	
	private static final Logger LOGGER = Logger.getLogger(LdapDAO.class.getName());

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(getMail("HNL1432"));
//		System.out.println(getCn("HNL1432"));
//		System.out.println(getManagerMail("anhta"));
//		System.out.println(getManagerCn("anhta"));
		System.out.println(getManagerLevelByUser("bpm_thuyl"));
	}
	
	/**
	 * @param employeeID
	 * @return
	 */
	public static User getUser(String employeeID){
		GLdapHelper gLdaper = new GLdapHelper();
		try {
			User user = gLdaper.getUserByEmployeeID(employeeID);
			return user;
		} catch (Exception ex) {
			// TODO: handle exception
			LOGGER.info(ex.toString());
			return null;
		}
	}
	
	public static String getMail(String employeeID){
		
		GLdapHelper gLdaper = new GLdapHelper();
		try {
			User user = gLdaper.getUserByEmployeeID(employeeID);
			return user.getMail();
		} catch (Exception ex) {
			// TODO: handle exception
			LOGGER.info(ex.toString());
			return null;
		}
	}
	
	public static String getCn(String employeeID){
		
		GLdapHelper gLdaper = new GLdapHelper();
		try {
			User user = gLdaper.getUserByEmployeeID(employeeID);
			return user.getCn();
		} catch (Exception ex) {
			// TODO: handle exception
			LOGGER.info(ex.toString());
			return null;
		}
	}
	
public static String getSAMAccountName(String employeeID){
		
		GLdapHelper gLdaper = new GLdapHelper();
		try {
			User user = gLdaper.getUserByEmployeeID(employeeID);
			return user.getSAMAccountName();
		} catch (Exception ex) {
			// TODO: handle exception
			LOGGER.info(ex.toString());
			return null;
		}
	}
	
	public static String getManagerMail(String username){
		
		GLdapHelper gLdaper = new GLdapHelper();
		try {
			User user = gLdaper.getLineManagerOfUser(username);
			return user.getMail();
		} catch (Exception ex) {
			// TODO: handle exception
			LOGGER.info(ex.toString());
			return null;
		}
	}
	
	public static String getManagerCn(String username){
		
		GLdapHelper gLdaper = new GLdapHelper();
		try {
			User user = gLdaper.getLineManagerOfUser(username);
			return user.getCn();
		} catch (Exception ex) {
			// TODO: handle exception
			LOGGER.info(ex.toString());
			return null;
		}
	}
	
	public static String getManagerSAMAccountName(String username){
		
		GLdapHelper gLdaper = new GLdapHelper();
		try {
			User user = gLdaper.getLineManagerOfUser(username);
			return user.getSAMAccountName();
		} catch (Exception ex) {
			// TODO: handle exception
			LOGGER.info(ex.toString());
			return null;
		}
	}
	
	public static int getManagerLevelByUser(String username){
		
		GLdapHelper gLdaper = new GLdapHelper();
		try {
			int level = gLdaper.getManagerLevelByUser(username);
			LOGGER.info("Level of " + username + "="+level);
			return level;
		} catch (Exception ex) {
			// TODO: handle exception
			LOGGER.info(ex.toString());
			return -1;
		}
	}
	
//	public void save(String employeeID){
//		//
//		HrWorksEmployeeRecordDAO dao = new HrWorksEmployeeRecordDAO();
//		BeanFilter filter = new BeanFilter(HrWorksEmployeeRecord.class);
//		filter.setFilter(HrWorksEmployeeRecord.EMPLOYEE_ID,new StringEqualFilter(employeeID));
//		HrWorksEmployeeRecord record = dao.getBean(filter).get(0);
//		if(record!=null){
//			//update record
//			record.set(HrWorksEmployeeRecord.COME_REASON, staff.getCome_reason());
//			record.set(HrWorksEmployeeRecord.LEAVE_REASON, staff.getLeave_reason());
//			record.set(HrWorksEmployeeRecord.LM_AUTO_APPROVED, staff.getLm_auto_approved());
//			record.set(HrWorksEmployeeRecord.HR_APPROVED, staff.getHr_approved());
//		}
//	}
	
}
