package com.gtel.hrworks.message;

import com.gtel.hrworks.conf.JsParams;


public class ReportGetResponse extends JsonResponse {
	
	public ReportGetResponse() {
	}
	
	public void setUrl(String url) throws Exception {
		write(JsParams.GET_REPORT_RESPONSE.URL, url);
	}
}
