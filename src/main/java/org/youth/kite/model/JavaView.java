package org.youth.kite.model;

import java.io.OutputStream;

/**
 * 
 * @author xusensen
 * @date 2013-7-28
 */
public class JavaView implements View {
	
	final private String viewTemplateClassPath;

	public JavaView(String viewTemplateClassPath) {
		this.viewTemplateClassPath = viewTemplateClassPath;
	}

	public void render(Model model, OutputStream outputStream) {
	}

}
