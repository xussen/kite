package org.youth.kite.loader;

import java.util.List;

import org.youth.kite.model.Kite;

public class JavaLoader implements KiteLoader {
	
	public List<Kite> loadKites() {
		return ClassPathLoader.loadKites(scanPackage);
	}

	private String scanPackage = null;
	
	public void setScanPackage(String scanPackage) {
		this.scanPackage = scanPackage;
	}

}
