package org.youth.kite.model;

import java.util.Map;

/**
 * 
 * @author xusensen
 * @date 2013-7-13
 */
public interface Script {
	
	public Model executeScript(Map<String, Object> model);

}
