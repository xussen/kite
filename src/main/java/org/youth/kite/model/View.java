package org.youth.kite.model;

import java.io.OutputStream;

/**
 * 
 * @author xusensen
 * @date 2013-7-13
 */
public interface View {
	
	void render(Model model, OutputStream outputStream);

}
