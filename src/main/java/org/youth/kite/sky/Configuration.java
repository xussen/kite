package org.youth.kite.sky;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.youth.kite.model.Kite;

/**
 * 
 * @author xusensen
 * @date 2013-7-13
 */
public class Configuration {
	
	private Map<String, Kite> kites = new HashMap<String, Kite>();
	
	private List<String> scanPackages = new ArrayList<String>();
	
	public Map<String, Kite> kites() {
		return kites;
	}

	public List<String> scanPackages() {
		return scanPackages;
	}
	
	public void addKites(Map<String, Kite> kites) {
		for (String key : kites.keySet()) {
			kites.put(key, kites.get(key));
		}
	}

}
