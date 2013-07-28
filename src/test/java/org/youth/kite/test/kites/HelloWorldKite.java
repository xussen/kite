package org.youth.kite.test.kites;

import java.util.HashMap;
import java.util.Map;

import org.youth.kite.Request;
import org.youth.kite.Response;
import org.youth.kite.script.KiteScript;

public class HelloWorldKite implements KiteScript {

	public Map<String, Object> executeImpl(Request request, Response response) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", "xussen");
		return model;
	}

}
