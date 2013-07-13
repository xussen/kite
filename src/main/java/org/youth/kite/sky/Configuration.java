package org.youth.kite.sky;

import java.util.ArrayList;
import java.util.List;

import org.youth.kite.model.Kite;

/**
 * 
 * @author xusensen
 * @date 2013-7-13
 */
public class Configuration {
	
	private List<Kite> kites = new ArrayList<Kite>();
	
	private List<String> scanPackages = new ArrayList<String>();
	
	public List<Kite> kites() {
		return kites;
	}

	public List<String> scanPackages() {
		return scanPackages;
	}

}
