/**
 *  Copyright (C) Zhang,Yuexiang (xfeep)
 *
 */
package nginx.clojure.java;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import nginx.clojure.NginxSimpleResponse;

public class NginxJavaResponse extends NginxSimpleResponse {

	protected Object[] response;
	
	public NginxJavaResponse() {
	}
	
	public NginxJavaResponse(Object[] response) {
		super();
		this.response = response;
	}

	@Override
	public int fetchStatus(int defaultStatus) {
		int status = defaultStatus;
		Object statusObj = response[0];
		if (statusObj != null) {
			if (statusObj instanceof Number){
				status = ((Number)statusObj).intValue();
			}else {
				status = Integer.parseInt(statusObj.toString());
			}
		}
		return status;
	}

	@Override
	public Collection<Entry> fetchHeaders() {
		Map headers = (Map)response[1];
		return headers == null ? null : headers.entrySet();
	}

	@Override
	public Object fetchBody() {
		return response[2];
	}

}