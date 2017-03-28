package com.gtel.hrworks.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import com.gtel.hrworks.conf.ServerConfig;
import com.gtel.hrworks.dao.LdapDAO;
import com.gtel.hrworks.model.Department;
import com.gtel.hrworks.model.EmployeeRecord;
import com.gtel.hrworks.util.DateTimeHelper;
import com.gtel.ldap.model.User;

/**
 * @author anhta
 */
public class ReadFileAfterUpload implements ExecutionListener {
    
    private final static Logger LOGGER = Logger.getLogger(ReadFileAfterUpload.class.getName());
    private final static String timeFm = "H:mm";
   
	/**
	 * @see org.camunda.bpm.engine.delegate.ExecutionListener#notify(org.camunda.bpm.engine.delegate.DelegateExecution)
	 */
	public void notify(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss") ;
		String filePath = ServerConfig.get_TEMP_FOLDER()+"LateList"+dateFormat.format(date)+".xls";
		
		FileOutputStream fileOuputStream = new FileOutputStream(filePath);
	    fileOuputStream.write((byte[])execution.getVariable("employee_list"));
	    fileOuputStream.close();
	    
	    execution.setVariable("filePath",filePath);
	    
	    LOGGER.info("Write file XLS done!");
	}
  }
