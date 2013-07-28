package org.youth.kite.model;

import java.io.OutputStream;
import java.util.Map;

import org.youth.kite.NormalRequest;
import org.youth.kite.Request;
import org.youth.kite.script.KiteOutPutStreamScript;

/**
 * 
 * @author xusensen
 * @date 2013-7-28
 */
public class JavaClass02 implements Script {
	
	final private KiteOutPutStreamScript kiteScript;

	public JavaClass02(KiteOutPutStreamScript kiteScript) {
		super();
		this.kiteScript = kiteScript;
	}

	public Model executeScript(Map<String, Object> model) {
		Model model2 = new Model();
		Request request = new NormalRequest(model);
		kiteScript.executeImpl(request, (OutputStream)model.get("outputStream"));
		return model2;
	}

}
