package org.youth.kite.model;

import java.util.Map;

import org.youth.kite.NormalRequest;
import org.youth.kite.NormalResponse;
import org.youth.kite.Request;
import org.youth.kite.script.KiteScript;

/**
 * 
 * @author xusensen
 * @date 2013-7-28
 */
public class JavaClass implements Script {
	
	final private KiteScript kiteScript;

	public JavaClass(KiteScript kiteScript) {
		super();
		this.kiteScript = kiteScript;
	}

	public Model executeScript(Map<String, Object> model) {
		Model model2 = new Model();
		NormalResponse response = new NormalResponse(System.out);
		Request request = new NormalRequest(model);
		model2.addAllObject(kiteScript.executeImpl(request, response));
		return model2;
	}

}
