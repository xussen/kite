package org.youth.kite.model;

import java.io.OutputStream;
import java.util.Map;

/**
 * i feel relax with it
 * 暂时不考虑带id(key)，保存Kite的干净
 * 
 * @author xusensen
 * @date 2013-7-13
 */
public final class Kite {
	
	private final Script script;
	private final View view;
	
	public Kite(Script script, View view) {
		this.script = script;
		this.view = view;
	}
	
	/**
	 * 
	 */
	public void fly(Map<String, Object> params, OutputStream outputStream) {
		//execute script
		params.put("outputStream", outputStream);
		Model model = script.executeScript(params);
		//render
		view.render(model, outputStream);
	}

}
