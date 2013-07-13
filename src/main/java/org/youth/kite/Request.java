package org.youth.kite;

import java.util.Locale;
import java.util.Map;

/**
 * 
 * @author xusensen
 * @date 2013-7-13
 */
public interface Request {
	
	abstract Map<String, Object> params();

	/**
	 * client language that can be accepted
	 * @return
	 */
    public Locale getLocale();
    
}
