package org.youth.kite.model;

import org.youth.kite.Response;

/**
 * 
 * @author xusensen
 * @date 2013-7-13
 */
public interface View {
	
	void render(Model model, Response response);

}
