package org.youth.kite.cache;

import java.util.List;

import org.youth.kite.model.Kite;

/**
 * 
 * @author xusensen
 * @date 2013-7-13
 */
public interface Cache {
	
	Kite getKite(String key);
	
	void add(Kite kite);

	void add(List<Kite> kites);

}
