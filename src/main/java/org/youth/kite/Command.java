package org.youth.kite;

import java.util.Map;

import org.youth.kite.model.Kite;

/**
 * tell him something, and let him do something
 * 
 * @author xusensen
 * @date 2013-7-13
 */
public class Command {
	
	private Kite kite;
	
	public Command(Kite kite) {
		this.kite = kite;
	}
	
	public void execute(Map<String, Object> params) {
		kite.fly(params);
	}

}
