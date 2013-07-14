package org.youth.kite.model;

import org.youth.kite.Request;
import org.youth.kite.Response;

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
	public void fly(Request request, Response response) {
		//execute script
		Model model = script.executeScript(request.params());
		//render
		view.render(model, response);
	}

}
