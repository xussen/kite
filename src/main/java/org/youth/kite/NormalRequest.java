package org.youth.kite;

import java.util.Locale;
import java.util.Map;

/**
 * 
 * @author xusensen
 * @date 2013-7-28
 */
public class NormalRequest implements Request {
	
	final private Map<String, Object> params;
	
	public NormalRequest(Map<String, Object> params) {
		super();
		this.params = params;
	}

	public Map<String, Object> params() {
		return params;
	}

	public Object param(String name) {
		return params.get(name);
	}

	/**
	 * client language that can be accepted
	 * @return
	 */
    public Locale getLocale() {
		return null;
	}
    
}
