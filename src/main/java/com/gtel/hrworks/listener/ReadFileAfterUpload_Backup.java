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
public class ReadFileAfterUpload_Backup implements ExecutionListener {
    
    private final static Logger LOGGER = Logger.getLogger(ReadFileAfterUpload_Backup.class.getName());
    private final static String timeFm = "H:mm";
   
	/**
	 * @see org.camunda.bpm.engine.delegate.ExecutionListener#notify(org.camunda.bpm.engine.delegate.DelegateExecution)
	 */
	public void notify(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		LdapDAO ldapDAO = new LdapDAO();
		
		List<Department> deptList = new ArrayList<Department>();
		
		HashMap<String, List<EmployeeRecord>> map = new HashMap<String, List<EmployeeRecord>>();
		
		String fileName = "LateList.xls";
		FileOutputStream fileOuputStream = new FileOutputStream(ServerConfig.get_TEMP_FOLDER()+fileName);
		LOGGER.info(execution.getVariable("employee_list").toString());
	    fileOuputStream.write((byte[])execution.getVariable("employee_list"));
	    fileOuputStream.close();
	    
	    LOGGER.info("Write file XLS done!");
		
		File fi = new File(ServerConfig.get_TEMP_FOLDER()+fileName);
		
		FileInputStream file = new FileInputStream(fi);
		// Get the workbook instance for XLS file
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		// Get first sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(0);

		// Pass first row
		Iterator<Row> rowIterator = sheet.iterator();
		for(int i=0;i<2;i++){
			if (rowIterator.hasNext())
				rowIterator.next();
		}
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			
			EmployeeRecord staff = new EmployeeRecord();
//			#1: date
			HSSFCell cell = (HSSFCell) cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);
			String value = getValueCell(cell);
				staff.setDate(value);
			
//				#2: employee_id
				cell = (HSSFCell) cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);
				value = getValueCell(cell);
				staff.setEmployee_id(value);
				
//				#3: employee_name
				cell = (HSSFCell) cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);
				value = getValueCell(cell);
				staff.setEmployee_name(value);

//				#4: dept_name
				cell = (HSSFCell)cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);
				value = getValueCell(cell);
				String dept_name = value;
				staff.setDept_name(value);

//				#5: come_default
				cell = (HSSFCell)cellIterator.next();
				LOGGER.info(getValueCell(cell));
				Date d = (Date) getDateValue(cell);
//				SimpleDateFormat dateFormat = new SimpleDateFormat(cell.getCellStyle().getDataFormatString());
				SimpleDateFormat dateFormat = new SimpleDateFormat(timeFm);
				value = dateFormat.format(d);
				
				staff.setCome_default(value);

//				#6: leave_default
				cell = (HSSFCell)cellIterator.next();
				d = (Date) getDateValue(cell);
				value = dateFormat.format(d);
				staff.setLeave_default(value);
				
				// #7: come time
				cell = (HSSFCell)cellIterator.next();
				d = (Date) getDateValue(cell);
				value = dateFormat.format(d);
				staff.setCome(value);

				// #8: approve come
				cellIterator.next();
				
				// #9: come reason
				cellIterator.next();
				
				// #10: leave time
				cell = (HSSFCell)cellIterator.next();
				d = (Date) getDateValue(cell);
				value = dateFormat.format(d);
				staff.setLeave(value);
				
				// #11: approve leave
				cellIterator.next();
				
				// #12: leave reason
				cellIterator.next();
				
				if(staff.getEmployee_id() != null && staff.getEmployee_id() != "") {
					User user = LdapDAO.getUser(staff.getEmployee_id());
					if(user != null) {
						staff.setCn(user.getCn());
						staff.setMail(user.getMail());
					}
					
					// add staff to department
					List<EmployeeRecord> ls = new ArrayList<EmployeeRecord>();
					if(map.containsKey(dept_name)) {
						ls = map.get(dept_name);
					}
					LOGGER.info(staff.toString());
					ls.add(staff);
					map.put(dept_name, ls);
				}

		}
		
		for (String dept_name : map.keySet()) {
			Department department = new Department();
			department.setDepartment_name(dept_name);
			department.setEmployeeList(map.get(dept_name));
			deptList.add(department);
		}
		
		file.close();

		execution.setVariable("deptList", deptList);
		LOGGER.info("Set (deptList) done!");
		
		execution.setVariable("ldapDAO", ldapDAO);
		LOGGER.info("Set (ldapDAO) done!");
		
		ServerConfig serverConfig = new ServerConfig();
		execution.setVariable("serverConfig", serverConfig);
		LOGGER.info("Set (serverConfig) done!");
		
		
		
//		execution.setVariable("expTime", getExpTime(1));
//		execution.setVariable("expTime","2015-10-14T16:54:00");
		DateTimeHelper dateTimeHelper = new DateTimeHelper();
		execution.setVariable("dateTimeHelper",dateTimeHelper);
		LOGGER.info("Set (dateTimeHelper) done!");
	}
	
	public static String getValueCell(Cell cell){
		switch (cell.getCellType()) {
		  case Cell.CELL_TYPE_BOOLEAN:
		  	return String.valueOf(cell.getBooleanCellValue());
		  case Cell.CELL_TYPE_NUMERIC:
			return String.valueOf(cell.getNumericCellValue());
		  case Cell.CELL_TYPE_STRING:
			return String.valueOf(cell.getStringCellValue());
		  }
		return "";
	}
	
	public static Object getDateValue(HSSFCell cellDate) 
	{
         double numericDateValue = cellDate.getNumericCellValue();
         Date date = HSSFDateUtil.getJavaDate(numericDateValue);
         return date;
	}
	
//	public static String getExpTime(int numOfDays){
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//		
//		Date date = new Date();
//		
//		Calendar c = Calendar.getInstance();
//		c.setTime(date);
//		c.add(Calendar.DATE, numOfDays);  // number of days to add
//		
//		c.set(Calendar.HOUR_OF_DAY, 0);
//		c.set(Calendar.MINUTE, 0);
//		c.set(Calendar.SECOND, 0);
//		
//		String expTime = sdf.format(c.getTime());
//		
//		return expTime;
//	}
   
  }
