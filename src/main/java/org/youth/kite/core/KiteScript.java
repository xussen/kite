package org.youth.kite.core;

import java.util.Map;

import org.youth.kite.Request;
import org.youth.kite.Response;

public interface KiteScript {
	
	Map<String, Object> executeImpl(Request request, Response response);

}
