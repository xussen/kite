package org.youth.kite.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author xusensen
 * @date 2013-7-13
 */
public class Model {
	
	private Map<String, Object> dataModel = new HashMap<String, Object>();
	
	public Model addObject(String key, Object data) {
		dataModel.put(key, data);
		return this;
	}

	public Model addAllObject(Map<String, Object> dataModel) {
		dataModel.putAll(dataModel);
		return this;
	}

}
