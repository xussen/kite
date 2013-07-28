package org.youth.kite;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * 
 * @author xusensen
 * @date 2013-7-28
 */
public class NormalResponse implements Response {
	
	final private OutputStream outputStream;
	
	public NormalResponse(OutputStream outputStream) {
		super();
		this.outputStream = outputStream;
	}

	public void response(String msg, String charset) {
		try {
			outputStream.write(msg.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("UnsupportedEncodingException", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("IOException", e);
		} finally {
			try {
				if(outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void response(byte[] data) {
		try {
			outputStream.write(data);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("UnsupportedEncodingException", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("IOException", e);
		} finally {
			try {
				if(outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
