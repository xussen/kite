package org.youth.kite.loader;

import java.util.HashMap;
import java.util.Map;

import org.youth.kite.Request;
import org.youth.kite.Response;
import org.youth.kite.script.KiteScript;

/**
 * just say hello
 * @author xusensen
 * @date 2013-7-19
 */
public class HelloWordKiteScript implements KiteScript {

	public Map<String, Object> executeImpl(Request request, Response response) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", "xussen");
		return model;
	}

}
