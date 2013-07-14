package org.youth.kite.cache;

import java.util.Map;

import org.youth.kite.model.Kite;

/**
 * 
 * @author xusensen
 * @date 2013-7-13
 */
public interface Cache {
	
	Kite getKite(String key);
	
	void add(String name, Kite kite);

	void add(Map<String, Kite> kites);

}
