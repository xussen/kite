package org.youth.kite.model;

import org.youth.kite.Request;
import org.youth.kite.Response;

/**
 * i feel relax with it
 * 
 * @author xusensen
 * @date 2013-7-13
 */
public final class Kite {
	
	final Script script;
	final View view;
	
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
