package org.youth.kite.loader;

import java.util.Map;

import org.youth.kite.Request;
import org.youth.kite.Response;
import org.youth.kite.model.Model;
import org.youth.kite.model.Script;
import org.youth.kite.script.KiteScript;

public class JavaClass implements Script {
	
	final private KiteScript kiteScript;

	public JavaClass(KiteScript kiteScript) {
		super();
		this.kiteScript = kiteScript;
	}

	public Model executeScript(Map<String, Object> model) {
		Model model2 = new Model();
		model2.addAllObject(kiteScript.executeImpl((Request)model.get("request"), (Response)model.get("response")));
		return model2;
	}

}
