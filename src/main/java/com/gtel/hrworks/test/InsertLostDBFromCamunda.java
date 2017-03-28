package com.gtel.hrworks.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;

import com.gtel.hrworks.dao.HrWorksEmployeeRecordDAO;
import com.gtel.hrworks.model.HrWorksEmployeeRecord;

public class InsertLostDBFromCamunda {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://10.16.81.27/camundabpm";

	// Database credentials
	static final String USER = "camundabpm";
	static final String PASS = "camundabpm123";
	
	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "select * from `ACT_GE_BYTEARRAY` where NAME_ = 'employee'";
			// sql = "select * from `ACT_GE_BYTEARRAY` where NAME_ = 'dept'";
			// sql = "select * from `ACT_HI_ACTINST` WHERE START_TIME_ >
			// '2016-02-24' limit 1";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			Set<String> set = new HashSet<String>();

			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
			
			HrWorksEmployeeRecordDAO recordDao = new HrWorksEmployeeRecordDAO();
			List<HrWorksEmployeeRecord> records = new ArrayList<HrWorksEmployeeRecord>();

			while (rs.next()) {

				// Retrieve by column name
				byte[] myLongBlob = (byte[]) rs.getBytes("BYTES_");

				// Display values
				// String jsonString = new String(myLongBlob, "UTF-8");
				String jsonString = new String(myLongBlob, "UTF-8");
				// System.out.println(jsonString);

				// JSONObject json = (JSONObject)new JSONParser().parse("");

				JSONObject jsonObj = new JSONObject(jsonString);
				if (jsonObj.has("mail")) {
					String date = (String) jsonObj.get("date");
					Calendar calRecord = Calendar.getInstance();
					calRecord.set(Calendar.DATE, Integer.parseInt(date.substring(0, 2)));
					calRecord.set(Calendar.MONTH, Integer.parseInt(date.substring(3, 5)) - 1);
					calRecord.set(Calendar.YEAR, Integer.parseInt(date.substring(6, 10)));

					calRecord.set(Calendar.HOUR, 0);
					calRecord.set(Calendar.MINUTE, 0);
					calRecord.set(Calendar.SECOND, 0);

					Calendar calFixDay = Calendar.getInstance();
					calFixDay.set(Calendar.DATE, 24);
					calFixDay.set(Calendar.MONTH, 2 - 1);
					calFixDay.set(Calendar.YEAR, 2016);

					calFixDay.set(Calendar.HOUR, 0);
					calFixDay.set(Calendar.MINUTE, 0);
					calFixDay.set(Calendar.SECOND, 0);
					// System.out.println(sdfDate.format(calRecord.getTime()) +
					// "|" + sdfDate.format(calFixDay.getTime()));
					// System.out.println(cal2.getTime());
					
					if (calRecord.getTime().after(calFixDay.getTime())) {
						HrWorksEmployeeRecord record = new HrWorksEmployeeRecord();
						
						record.set(HrWorksEmployeeRecord.DATE, calRecord.getTime());
						record.set(HrWorksEmployeeRecord.EMPLOYEE_ID,(String) jsonObj.get("employee_id"));
						record.set(HrWorksEmployeeRecord.EMPLOYEE_NAME,(String) jsonObj.get("employee_name"));
						record.set(HrWorksEmployeeRecord.COME_REASON,(jsonObj.get("come_reason").toString())=="null"?"":((String) jsonObj.get("come_reason")));
						record.set(HrWorksEmployeeRecord.LEAVE_REASON,(jsonObj.get("leave_reason").toString())=="null"?"":((String) jsonObj.get("leave_reason")));
						record.set(HrWorksEmployeeRecord.DEPT_NAME,(String) jsonObj.get("dept_name"));
						record.set(HrWorksEmployeeRecord.COME_DEFAULT,(String) jsonObj.get("come_default"));
						record.set(HrWorksEmployeeRecord.LEAVE_DEFAULT,(String) jsonObj.get("leave_default"));
						record.set(HrWorksEmployeeRecord.COME_TIME,(String) jsonObj.get("come"));
						record.set(HrWorksEmployeeRecord.LEAVE_TIME,(String) jsonObj.get("leave"));
						
						String print = sdfDate.format(calRecord.getTime()) + "|" + jsonObj.get("employee_name") + "|"
								+ jsonObj.get("come_reason") + "|" + jsonObj.get("leave_reason");
						
						if (!set.contains(print) && (jsonObj.get("come_reason").toString() != "null"
								|| jsonObj.get("leave_reason").toString() != "null")) {
							records.add(record);
							System.out.println(print);
						}
						
					}
					
					
//					if (calRecord.getTime().after(calFixDay.getTime())) {
//						String print = sdfDate.format(calRecord.getTime()) + "|" + jsonObj.get("employee_name") + "|"
//								+ jsonObj.get("come_reason") + "|" + jsonObj.get("leave_reason");
//						if (!set.contains(print) && (jsonObj.get("come_reason").toString() != "null"
//								|| jsonObj.get("leave_reason").toString() != "null")) {
//							set.add(print);
//						}
						// System.out.println(print);

						// System.out.println("mail=" + jsonObj.get("mail"));
						// System.out.println("employee_name=" +
						// jsonObj.get("employee_name"));
						// System.out.println("date=" + jsonObj.get("date"));
						// System.out.println("employee_id=" +
						// jsonObj.get("employee_id"));
						// System.out.println("dept_name=" +
						// jsonObj.get("dept_name"));
						// System.out.println("come_default=" +
						// jsonObj.get("come_default"));
						// System.out.println("leave_default=" +
						// jsonObj.get("leave_default"));
						// System.out.println("come=" + jsonObj.get("come"));
						// System.out.println("leave=" + jsonObj.get("leave"));
						// System.out.println("come_reason=" +
						// jsonObj.get("come_reason"));
						// System.out.println("leave_reason=" +
						// jsonObj.get("leave_reason"));
						// System.out.println("come_other_text=" +
						// jsonObj.get("come_other_text"));
						// System.out.println("leave_other_text=" +
						// jsonObj.get("leave_other_text"));
//					}

				}
			}
			
			recordDao.updateHrWorksEmployeeRecord(records);
			System.out.println("So luong ban ghi=" + records.size());

			for (String string : set) {
				System.out.println(string);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");

	}

}
