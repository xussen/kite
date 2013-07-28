package org.youth.kite.script;

import java.io.OutputStream;

import org.youth.kite.Request;

/**
 * 
 * @author xusensen
 * @date 2013-7-28
 */
public interface KiteOutPutStreamScript {
	
	void executeImpl(Request request, OutputStream outputStream);

}
