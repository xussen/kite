package org.youth.kite.cache;

import java.util.HashMap;
import java.util.Map;

import org.youth.kite.model.Kite;

/**
 * 
 * @author xusensen
 * @date 2013-7-13
 */
public class SimpleCache implements Cache {
	
	private Map<String, Kite> kiteMap = new HashMap<String, Kite>();
	
	public Kite getKite(String key) {
		return kiteMap.get(key);
	}
	
	public void add(String name, Kite kite) {
		kiteMap.put(name, kite);
	}

	public void add(Map<String, Kite> kites) {
		kiteMap.putAll(kites);
	}

}
