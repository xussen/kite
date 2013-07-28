package org.youth.kite;

/**
 * 
 * @author xusensen
 * @date 2013-7-13
 */
public interface Response {
	
	abstract void response(String msg, String charset);

	abstract void response(byte[] data);

}
