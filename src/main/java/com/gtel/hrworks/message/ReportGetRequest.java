package com.gtel.hrworks.message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;





import org.json.JSONObject;

import com.gtel.hrworks.conf.JsParams;
import com.gtel.hrworks.conf.ServerConfig;
import com.gtel.hrworks.report.EmployeeLateReportBuilder;



public class ReportGetRequest extends JsonRequest {

	/**
	 * @param jObj
	 * @throws Exception 
	 */
	public ReportGetRequest(JSONObject jObj) throws Exception {
		super(jObj);
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		
		if (jObj.has(JsParams.GET_HRWORKS_EMPLOYEE_RECORD_REQUEST.START_DATE)) {
			dateStr = jObj.getString(JsParams.GET_HRWORKS_EMPLOYEE_RECORD_REQUEST.START_DATE);
			_start_date = df.parse(dateStr);
		}
		
		if (jObj.has(JsParams.GET_HRWORKS_EMPLOYEE_RECORD_REQUEST.END_DATE)) {
			dateStr = jObj.getString(JsParams.GET_HRWORKS_EMPLOYEE_RECORD_REQUEST.END_DATE);
			_end_date = df.parse(dateStr);
		}
	}

	String dateStr;
	Date _start_date;
	Date _end_date;
	
	@Override
	public JsonResponse execute() {
		ReportGetResponse resp = new ReportGetResponse();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String dateInFileName = df.format(_start_date).toString();
			String filename = "Report-from-"+dateInFileName + "-to-" +(_start_date!=_end_date ? df.format(_end_date).toString() : "") +".xlsx";
			EmployeeLateReportBuilder builder = new EmployeeLateReportBuilder(_start_date, _end_date);
			builder.buildExcelReport(ServerConfig.getPhysicalDir() +filename);
			String url = ServerConfig.getVirtualDir()+filename;
			resp.setUrl(url);
			resp.setSuccess(true);
		} catch (Exception exc) {
			exc.printStackTrace();
			resp.setSuccess(false);
			resp.setInfo(exc.getMessage());
			
		}
		return resp;
	}

}
